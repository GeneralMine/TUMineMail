package ga.tumgaming.tumine.mail;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import ga.tumgaming.tumine.mail.listeners.BlockBreakListener;
import ga.tumgaming.tumine.mail.listeners.ClickListener;
import ga.tumgaming.tumine.mail.listeners.InboxCommand;
import ga.tumgaming.tumine.mail.listeners.JoinListener;
import ga.tumgaming.tumine.mail.util.*;

public class TUMail extends JavaPlugin {

	private static Config inboxes;
	private static Config letters;
	private static Inbox inboxManager;
	private static Plugin plugin;

	@Override
	public void onEnable() {
		TUMail.plugin = this;
		
		letters = new Config(plugin, "letters");
		inboxes = new Config(plugin, "inboxes");
		inboxManager = new Inbox(inboxes, letters);
		

	    // Register our command "createInbox" (set an instance of your command class as executor)
	    this.getCommand("inbox").setExecutor(new InboxCommand(inboxManager));
		
		registerEvents();

		log("Plugin Version " + getDescription().getVersion() + " erfolgreich geladen");
	}

	/**
	 * logs a String in the console
	 *
	 * @param str logged String
	 */
	public void log(String str) {
		Logger.getLogger(str);
	}

	private static void registerEvents() {
		PluginManager pluginManager = Bukkit.getPluginManager();

		pluginManager.registerEvents(new JoinListener(inboxManager), plugin);
		pluginManager.registerEvents(new ClickListener(inboxManager), plugin);
		pluginManager.registerEvents(new BlockBreakListener(inboxManager), plugin);
	}

	public static Config getInboxesConfig() {
		return inboxes;
	}

	public static Plugin getPlugin() {
		return plugin;
	}

}
