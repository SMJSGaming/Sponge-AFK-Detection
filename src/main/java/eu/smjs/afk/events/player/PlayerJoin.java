package eu.smjs.afk.events.player;

import eu.smjs.afk.data.AFKTimerStorage;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.network.ClientConnectionEvent;

public class PlayerJoin {

    @Listener(order = Order.LAST)
    public void onPlayerJoin(final ClientConnectionEvent.Join event) {
        AFKTimerStorage.put(event.getTargetEntity());
    }
}
