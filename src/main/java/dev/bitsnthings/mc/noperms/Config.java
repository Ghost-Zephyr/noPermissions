package dev.bitsnthings.mc.noperms;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

public final class Config {
  private static FileConfiguration config;
  private static HashMap<String, List<String>> defaults = new HashMap<String, List<String>>();

  public static List<String> hiddenCommands;
  public static List<String> defaultFalse;
  public static List<String> defaultTrue;
  public static List<String> defaultOp;

	public Config() {
    config = NoPerms.getInstance().getConfig();
		setDefaults();
		loadConfig();
	}

  public void loadConfig() {
    hiddenCommands = config.getStringList("hiddenCommands");
		defaultFalse = config.getStringList("defaults.false");
		defaultTrue = config.getStringList("defaults.true");
		defaultOp = config.getStringList("defaults.op");
  }
  /*
	public void reloadConfig(){
		loadConfig();
  }
  */
	public void setDefaults() {
    defaults.put("false", Arrays.asList(new String[] {
      "bukkit.command.reload","minecraft.command.op","minecraft"
    }));
    defaults.put("true", Arrays.asList(new String[] {
      "minecraft.command.kill","minecraft.command.seed","minecraft.command.list"
    }));
    defaults.put("op", Arrays.asList(new String[] {
      "bukkit.command.plugins","bukkit.command.version","bukkit.command.help"
    }));
    for (String key: defaults.keySet()) {
      config.addDefault(String.format("defaults.%s", key), defaults.get(key));
    }
    config.addDefault("hiddenCommands", Arrays.asList(new String[] {
      //"?","help",
      "minecraft:help","minecraft:list","minecraft:me","minecraft:msg","minecraft:teammsg","minecraft:tell","minecraft:tm","minecraft:trigger","minecraft:w",
      "bukkit:?","bukkit:about","bukkit:help","bukkit:pl","bukkit:plugins","bukkit:ver","bukkit:version",
      "about","pl","plugins","ver","version"
    }));
    config.options().copyDefaults(true);
    NoPerms.getInstance().saveConfig();
  }
	public void saveConfig() {
    NoPerms.getInstance().saveConfig();
	}
}
