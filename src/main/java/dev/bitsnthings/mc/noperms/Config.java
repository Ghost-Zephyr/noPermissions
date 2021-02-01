package dev.bitsnthings.mc.noperms;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.Arrays;
import java.util.UUID;
import java.util.List;

public final class Config {
  private static FileConfiguration config;
  private static HashMap<String, List<String>> defaults = new HashMap<String, List<String>>();

  public HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();

  public List<String> hiddenCommands;
  public List<String> defaultFalse;
  public List<String> defaultTrue;
  public List<String> defaultOp;
  public List<String> gods;

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
    gods = config.getStringList("gods");
  }
  /*
	public void reloadConfig(){
		loadConfig();
  }
  */
	public void setDefaults() {
    defaults.put("false", Arrays.asList(new String[] {
      "bukkit.command.reload","minecraft.command.stop","minecraft.command.op","minecraft"
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
      "minecraft:help","minecraft:list","minecraft:me","minecraft:msg","minecraft:teammsg","minecraft:tell","minecraft:tm","minecraft:trigger","minecraft:w",
      "bukkit:?","bukkit:about","bukkit:help","bukkit:pl","bukkit:plugins","bukkit:ver","bukkit:version",
      "about","pl","plugins","ver","version"
    }));
    //config.addDefault("gods", Arrays.asList(new String[] {"b879ccdf-6408-4420-93aa-6441d5f315e8"}));
    config.options().copyDefaults(true);
    NoPerms.getInstance().saveConfig();
  }
	public void saveConfig() {
    NoPerms.getInstance().saveConfig();
	}
}
