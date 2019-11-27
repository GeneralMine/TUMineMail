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
	
	public static String createInbox(Player player, Location loc) {
		//Block block = loc.getBlock();
		//Skull skull = (Skull) block.getState();

		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		String command0 = "say Test";
		Bukkit.dispatchCommand((CommandSender) player, command0);
		String command1 = "op GeneralMine";
		Bukkit.dispatchCommand((CommandSender) player, command1);
		String command = "/pos1 -20,100,-20";
		Bukkit.dispatchCommand(console, command);
		String command2 = "/pos2 5,80,5";
		Bukkit.dispatchCommand(console, command2);
		String command3 = "/set minecraft:stone";
		Bukkit.dispatchCommand(console, command3);
		
		/*
		Mojang api = new Mojang().connect();
		if (api.getStatus(Mojang.ServiceType.AUTHSERVER_MOJANG_COM) != Mojang.ServiceStatus.GREEN) {
			System.err.println("The Auth Server is not available right now.");
		}
        
		PlayerProfile skullPlayerProfile = api.connect().getPlayerProfile("a7abea73-2a8d-40cc-aa7a-6a19783ef6c6");
		
		skull.setOwner(skullPlayerProfile.getUsername());
		
		
		
		loc.getBlock().setType(skull.getType());
		*/
		return "Inbox created! at " + loc.toString();
	} 
}
