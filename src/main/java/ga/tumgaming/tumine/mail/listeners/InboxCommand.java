package ga.tumgaming.tumine.mail.listeners;

import ga.tumgaming.tumine.mail.util.Config;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ga.tumgaming.tumine.mail.Inbox;

public class InboxCommand implements CommandExecutor {

	private Inbox inbox;

	public InboxCommand(Inbox inbox) {
		this.inbox = inbox;
	}

	@EventHandler
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (commandSender instanceof Player) {
			Player sender = (Player) commandSender;
			switch (args[0]) {
			case "create": {
				// inbox create
				ClickListener.waitingForInboxCreationClick = true;
				sender.sendMessage("Please right click on the chest to become your Inbox");
				break;
			}
			case "delete": {
				// inbox delete
				sender.sendMessage(inbox.deleteInbox(sender));
				break;
			}
			default: {
				// inbox help
				sender.sendMessage("The TUMineMail Plugin has the following commands:\n"
						+ "/inbox create : Creates your personal inbox after right clicking at a chest\n"
						+ "/inbox delete : Deletes your personal inbox (you won't lose your letters\n"
						+ "/inbox help : Displays help");
				break;
			}
			}
		}
		return false;
	}

}
