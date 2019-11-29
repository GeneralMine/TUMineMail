package ga.tumgaming.tumine.mail.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import ga.tumgaming.tumine.mail.Inbox;

public class ClickListener implements Listener {
	public boolean searchForInbox = false;
	
	private Inbox inbox;
	public ClickListener(Inbox inbox) {
		this.inbox = inbox;
	}
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    Action action = event.getAction();
	    Block block = event.getClickedBlock();
	    BlockFace face = event.getBlockFace();

	     if ( searchForInbox && action.equals( Action.RIGHT_CLICK_BLOCK ) && block != null && block.getType() == Material.CHEST ) {
	    	 String message = inbox.createInbox(player, event.getClickedBlock().getLocation());
	    	 player.sendMessage(message);
	     }
	}
}
