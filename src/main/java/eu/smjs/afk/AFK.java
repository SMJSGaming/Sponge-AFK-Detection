package eu.smjs.afk;

import com.google.inject.Inject;
import eu.smjs.afk.events.entity.EntityDismount;
import eu.smjs.afk.events.entity.EntityMount;
import eu.smjs.afk.events.entity.EntityMove;
import eu.smjs.afk.events.player.PlayerInteract;
import eu.smjs.afk.events.player.PlayerJoin;
import eu.smjs.afk.events.player.PlayerLeave;
import eu.smjs.afk.events.player.PlayerMessage;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(
    version = "1.0.0",
    id = "afk",
    name = "AFK",
    authors = {
        "SMJS"
    },
    description = "An universal AFK detection plugin"
)
public class AFK {

    public static Logger LOGGER;

    @Inject
    AFK(final PluginContainer container, final Logger logger) {
        final EventManager manager = Sponge.getEventManager();
        AFK.LOGGER = logger;

        logger.info("Starting the AFK detection");

        manager.registerListeners(container, new EntityDismount());
        manager.registerListeners(container, new EntityMount());
        manager.registerListeners(container, new EntityMove());
        manager.registerListeners(container, new PlayerInteract());
        manager.registerListeners(container, new PlayerJoin());
        manager.registerListeners(container, new PlayerLeave());
        manager.registerListeners(container, new PlayerMessage());
    }
}
