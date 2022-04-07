package eu.smjs.afk.events.entity;

import eu.smjs.afk.data.MountedEntityStorage;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.entity.RideEntityEvent;

public class EntityDismount {

    @Listener(order = Order.LAST)
    public void onEntityDismount(final RideEntityEvent.Dismount event) {
        MountedEntityStorage.remove(event.getTargetEntity());
    }
}
