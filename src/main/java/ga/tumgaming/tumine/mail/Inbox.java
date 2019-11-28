package ga.tumgaming.tumine.mail;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Skull;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.shanerx.mojang.Mojang;
import org.shanerx.mojang.PlayerProfile;

import ga.tumgaming.tumine.mail.util.Config;

public class Inbox {

	private Config inboxes;
	
	public Inbox (Config config) {
		this.inboxes = config;
	}
	
	public String sendMessageToPlayer(Player sender) {
		ItemStack book = sender.getInventory().getItem(sender.getInventory().getHeldItemSlot());
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		String receiver = bookMeta.getTitle().toString();
		
		if(!(inboxes.get(receiver) == null)) {
			// check if space is available
			List<ItemStack> inv = (List<ItemStack>) Config.get(receiver);
			
			if(inv.contains(null)) {
				// free space in inv
				inv.set(inv.indexOf(null), book);
				inboxes.set(receiver, inv);
				return "Message delivered successfully";
			} else {
				// no free space in inv
				return "The inbox of the receiver is full, the message has not been sent!";
			}
		} else {
        	return "The receiver has no inbox, the message has not been sent!";
        }
	}
	
	public String createInbox(Player player, Location loc) {
		// Location to Copy from
		Location skullLoc = new Location(player.getWorld(), 0, 1, 0);
		
		loc.getBlock().setType(skullLoc.getBlock().getType());
		
		inboxes.set(player.getName(), loc.toString());
		
		return "Inbox created!";
	} 
}
