package eu.smjs.afk.events.entity;

import eu.smjs.afk.data.MountedEntityStorage;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.entity.RideEntityEvent;

public class EntityMount {

    @Listener(order = Order.LAST)
    public void onEntityMount(final RideEntityEvent.Mount event) {
        MountedEntityStorage.put(event.getTargetEntity());
    }
}
