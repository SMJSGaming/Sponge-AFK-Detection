package eu.smjs.afk.events.player;

import eu.smjs.afk.data.AFKTimerStorage;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;

public class PlayerMessage {

    @Listener(order = Order.LAST)
    public void onPlayerMessage(final MessageChannelEvent.Chat event, @Root final Player player) {
        AFKTimerStorage.reschedule(player);
    }
}
