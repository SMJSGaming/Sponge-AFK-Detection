package eu.smjs.afk.events.entity;

import eu.smjs.afk.data.AFKTimerStorage;
import eu.smjs.afk.data.MountedEntityStorage;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.entity.MoveEntityEvent;

public class EntityMove {

    @Listener(order = Order.LAST)
    public void onEntityMove(final MoveEntityEvent event) {
        final Entity entity = event.getTargetEntity();

        if (entity.getType().getEntityClass().getName().endsWith("PlayerMP")) {
            AFKTimerStorage.reschedule((Player) entity);
        } else if (entity.getPassengers().size() > 0) {
            MountedEntityStorage.reschedule(entity);
        }
    }
}
