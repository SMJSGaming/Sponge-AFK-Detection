package eu.smjs.afk.data;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AFKTimerObject {

    private final ScheduledExecutorService service;

    private final ScheduledFuture<?> messageScheduler;

    private final ScheduledFuture<?> kickScheduler;

    public AFKTimerObject(final Player player) {
        this.service = new ScheduledThreadPoolExecutor(2);
        this.messageScheduler = this.service.schedule(() -> Sponge.getServer().getBroadcastChannel().send(
            Text.builder("* " + player.getName() + " has gone AFK")
                .color(TextColors.GRAY)
                .build()
        ), 5, TimeUnit.MINUTES);
        this.kickScheduler = this.service.schedule(() -> player.kick(), 15, TimeUnit.MINUTES);
    }

    public ScheduledExecutorService getService() {
        return this.service;
    }

    public ScheduledFuture<?> getMessageScheduler() {
        return this.messageScheduler;
    }

    public ScheduledFuture<?> getKickScheduler() {
        return this.kickScheduler;
    }
}
