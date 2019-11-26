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

public class CommandListener implements CommandExecutor {

	private Inbox inbox;
	private Config config;
	
	public CommandListener(Config conf) {
		this.config = conf;
		inbox =  new Inbox(config);
	}
	@EventHandler
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (commandSender instanceof Player) {
			String message = "";

			for (int i = 2; i < args.length; i++) {
				message += args[i];
			}
			Player receiver = Bukkit.getPlayer(args[1]);
			Player sender = (Player) commandSender;
			
			String result = inbox.sendMessageToPlayer(sender);
			
			if (result == null) {
				sender.sendMessage("Message delivery successful");
			} else {
				sender.sendMessage(result);
			}
		}

		return false;
	}

}
