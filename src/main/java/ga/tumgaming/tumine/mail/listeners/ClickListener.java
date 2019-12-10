package ga.tumgaming.tumine.mail.listeners;

import org.bukkit.Location;
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
import ga.tumgaming.tumine.mail.util.Config;

public class ClickListener implements Listener {
	private Inbox inbox;
	public static boolean waitingForInboxCreationClick = false;

	public ClickListener(Inbox inbox) {
		this.inbox = inbox;
	}

	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		Block block = event.getClickedBlock();
		BlockFace face = event.getBlockFace();
		// Location of private Inbox Skull to Copy from
		Location skullLetterBoxLoc = new Location(player.getWorld(), 1, 1, 0);

		if (waitingForInboxCreationClick && action.equals(Action.RIGHT_CLICK_BLOCK) && block != null
				&& block.getType() == Material.CHEST) {
			// Create Inbox
			// player.sendMessage("Creating Inbox...");
			player.sendMessage(inbox.createInbox(player, event.getClickedBlock().getLocation()));
			waitingForInboxCreationClick = false;
		} else if (block != null
				&& player.getInventory().getItem(player.getInventory().getHeldItemSlot()) == getItemStackFromMaterial(
						Material.WRITTEN_BOOK, 1)
				&& action.equals(Action.RIGHT_CLICK_BLOCK)
				&& block.getType() == skullLetterBoxLoc.getBlock().getType()) {
			// Send Letter
			player.sendMessage(inbox.sendLetter(player));
		}
		
		player.sendMessage(inbox.test(player, skullLetterBoxLoc.getBlock()));

	}

	private ItemStack getItemStackFromMaterial(Material material, int count) {
		ItemStack newItemStack = new ItemStack(material, count);
		return newItemStack;
	}
}
