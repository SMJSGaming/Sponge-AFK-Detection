package eu.smjs.afk.events.player;

import eu.smjs.afk.data.AFKTimerStorage;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.network.ClientConnectionEvent;

public class PlayerLeave {

    @Listener(order = Order.LAST)
    public void onPlayerLeave(final ClientConnectionEvent.Disconnect event) {
        AFKTimerStorage.remove(event.getTargetEntity());
    }
}
