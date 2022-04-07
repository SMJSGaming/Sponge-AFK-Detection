package eu.smjs.afk.data;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MountedEntityStorage {

    private static final Map<Entity, List<Player>> MOUNTED = new HashMap<>();

    public static void put(final Entity entity) {
        final List<Player> players = entity.getPassengers().stream().filter(
            (passenger) -> passenger.getType().getEntityClass().getName().endsWith("PlayerMP")
        ).map((passenger) -> {
            final Player player = (Player) passenger;

            AFKTimerStorage.reschedule(player);

            return player;
        }).collect(Collectors.toList());

        if (players.size() > 0) {
            MountedEntityStorage.MOUNTED.put(entity, players);
        }
    }

    public static void reschedule(final Entity entity) {
        final List<Player> players = MountedEntityStorage.MOUNTED.get(entity);

        if (players != null) {
            players.forEach(AFKTimerStorage::reschedule);
        }
    }

    public static void remove(final Entity entity) {
        final List<Player> players = MountedEntityStorage.MOUNTED.remove(entity);

        if (players != null) {
            players.forEach(AFKTimerStorage::reschedule);
        }
    }
}
