package ga.tumgaming.tumine.mail;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Skull;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.MetadataValue;
import org.shanerx.mojang.Mojang;
import org.shanerx.mojang.PlayerProfile;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import ga.tumgaming.tumine.mail.listeners.ClickListener;
import ga.tumgaming.tumine.mail.util.Config;

public class Inbox {

	private Config inboxes;
	private Config letters;

	public Inbox(Config inboxes, Config letters) {
		this.inboxes = inboxes;
		this.letters = letters;
	}

	public String sendLetter(Player sender) {
		ItemStack book = sender.getInventory().getItem(sender.getInventory().getHeldItemSlot());
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		String receiver = bookMeta.getTitle().toString();

		// check if receiver has inbox
		if (!(inboxes.get(receiver) == null)) {
			// check if space is available
			List<ItemStack> inv = (List<ItemStack>) letters.get(receiver);

			if (inv.contains(null)) {
				// free space in inv
				inv.set(inv.indexOf(null), book);
				letters.set(receiver, inv);
				return "Message delivered successfully";
			} else {
				// no free space in inv
				return "The inbox of the receiver is full, the message has not been sent!";
			}
		} else {
			return "The receiver has no inbox, the message has not been sent!";
		}
	}

	public Material getSkullInboxMaterial(Player player) {
		return new Location(Bukkit.getWorld("build_world"), 0, 1, 0).getBlock().getType();
	}

	public Material getSkullLetterBoxMaterial(Player player) {
		return new Location(Bukkit.getWorld("build_world"), 1, 1, 0).getBlock().getType();
	}

	public String createInbox(Player player, Location loc) {
		if (inboxes.get(player.getUniqueId().toString()) == null) {
			// Location of private Inbox Skull to Copy from
			
			
			
			Block skullBlock = loc.getBlock();
			skullBlock.setType(Material.PLAYER_HEAD);
			BlockState state = skullBlock.getState();
			Skull skull = (Skull) state;
			UUID uuid = player.getUniqueId();
			skull.setOwningPlayer(Bukkit.getServer().getOfflinePlayer(uuid));
			GameProfile profile = new GameProfile(UUID.randomUUID(), null);
			profile.getProperties().put("textures", new Property("textures", url));
			skull.update();

			Inventory inv = Bukkit.createInventory(null, 27, player.getName() + "'s Inbox!");
			inboxes.set(player.getUniqueId().toString(), loc + "," + inv);

			return "Inbox created!";
		} else {
			return "You already have an Inbox!";
		}
	}

	public String deleteInbox(Player player) {
		if (inboxes.get(player.getName()) != null) {
			// Location of private Inbox Skull to Copy from
			Location inboxLocation = (Location) inboxes.get(player.getName());
			inboxLocation.getBlock().setType(Material.AIR);
			// Chest chest = (Chest) inboxLocation.getBlock();
			// Inventory chestInventory = chest.getInventory();
			// chestInventory.setContents((ItemStack[]) letters.get(player.getName()));
			player.sendMessage(inboxes.test(player) + " test");
			inboxes.delete(player.getName());
			return "Inbox destroyed!";
		} else {
			return "Error: trying to destroy inbox, but there is no";
		}
	}

	public String test(Player player, Block block) {
		letters.set(player.getName() + " at " + player.getLocation().toString(), block);
		return player.getName() + " breaked " + block.toString();
	}

	public Location getInboxLocation(Player player) {
		return (Location) inboxes.get(player.getName());
	}

}
