package eu.smjs.afk.events.player;

import eu.smjs.afk.data.AFKTimerStorage;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.Root;

public class PlayerInteract {

    @Listener(order = Order.LAST)
    public void onPlayerInteract(final InteractBlockEvent event, @Root final Player player) {
        AFKTimerStorage.reschedule(player);
    }
}
