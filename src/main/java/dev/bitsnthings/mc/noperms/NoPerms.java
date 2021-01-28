package dev.bitsnthings.mc.noperms;

import dev.bitsnthings.mc.noperms.events.*;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class NoPerms extends JavaPlugin {
  private static PluginManager pluginManager = Bukkit.getPluginManager();
  private final Logger logger = Logger.getLogger("NoPerms");
  private static NoPerms plugin;
  private static Config config;
  @Override
  public void onEnable() {
    plugin = this;
    config = new Config();
    pluginManager.registerEvents(new LoadedEvent(), this);
    pluginManager.registerEvents(new CommandSendEvent(), this);
    pluginManager.registerEvents(new CommandPreprocessEvent(), this);
    this.getCommand("noperms").setExecutor(new NoPermsCommand());
  }
  @Override
  public void onDisable() {

  }
  public Config getInternalConfig() {
    return config;
  }
  public Logger getLogger() {
		return logger;
	}
	public static NoPerms getInstance() {
		return plugin;
	}
}
