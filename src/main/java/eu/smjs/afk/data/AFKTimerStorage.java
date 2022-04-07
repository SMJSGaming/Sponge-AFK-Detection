package eu.smjs.afk.data;

import eu.smjs.afk.AFK;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.HashMap;
import java.util.Map;

public class AFKTimerStorage {

    private static final Map<Player, AFKTimerObject> SCHEDULERS = new HashMap<>();

    public static void put(final Player player) {
        AFKTimerStorage.SCHEDULERS.put(player, new AFKTimerObject(player));

        AFK.LOGGER.info("Scheduled " + player.getName());
    }

    public static void reschedule(final Player player) {
        if (AFKTimerStorage.SCHEDULERS.get(player).getMessageScheduler().isDone()) {
            Sponge.getServer().getBroadcastChannel().send(
                Text.builder("* " + player.getName() + " is no longer AFK")
                    .color(TextColors.GRAY)
                    .build()
            );
        }

        AFKTimerStorage.remove(player);
        AFKTimerStorage.put(player);
    }

    public static void remove(final Player player) {
        final AFKTimerObject timerObject = AFKTimerStorage.SCHEDULERS.remove(player);

        timerObject.getMessageScheduler().cancel(true);
        timerObject.getKickScheduler().cancel(true);

        try {
            timerObject.getService().shutdownNow();
        } catch (SecurityException exception) {
            AFK.LOGGER.error(exception.toString());

            timerObject.getService().shutdown();
        }

        AFK.LOGGER.info("Unscheduled " + player.getName());
    }
}
