package ga.tumgaming.tumine.mail.listeners;

import ga.tumgaming.tumine.mail.Inbox;
import ga.tumgaming.tumine.mail.util.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private Config config;
    private Inbox inbox;

    public JoinListener(Config config, Inbox inbox) {
        this.config = config;
        this.inbox = inbox;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        player.sendMessage(inbox.createInbox(player, player.getLocation()));
    }

}
