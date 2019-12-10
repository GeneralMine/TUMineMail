package ga.tumgaming.tumine.mail.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;

import ga.tumgaming.tumine.mail.Inbox;
import ga.tumgaming.tumine.mail.util.Config;

public class BlockBreakListener implements Listener {
	private Inbox inbox;
	public static boolean waitingForInboxCreationClick = false;

	public BlockBreakListener(Inbox inbox) {
		this.inbox = inbox;
	}

	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (inbox.getInboxLocation(player) == event.getBlock().getLocation()) {
			player.sendMessage(inbox.deleteInbox(player));
		}
	}
}
	