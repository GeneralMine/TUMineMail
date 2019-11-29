package ga.tumgaming.tumine.mail.listeners;

import ga.tumgaming.tumine.mail.Inbox;
import ga.tumgaming.tumine.mail.util.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

	private Inbox inbox;

	public JoinListener(Inbox inbox) {
		this.inbox = inbox;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
	}

}
