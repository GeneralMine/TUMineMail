package ga.tumgaming.tumine.mail;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import ga.tumgaming.tumine.mail.listeners.CommandListener;
import ga.tumgaming.tumine.mail.listeners.JoinListener;
import ga.tumgaming.tumine.mail.util.*;
public class TUMail extends JavaPlugin {

    private static Config inboxes;
    private static Inbox inboxManager;
    private static Plugin plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        inboxes = new Config(this, "inboxes");
        CommandListener commandListener = new CommandListener(inboxes);
        registerEvents();

        log("Plugin erfolgreich geladen");
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
    }

    public static Config getInboxesConfig() {
        return inboxes;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}
