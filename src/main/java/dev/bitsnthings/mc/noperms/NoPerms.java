package dev.bitsnthings.mc.noperms;

import org.bukkit.plugin.java.JavaPlugin;

public class NoPerms extends JavaPlugin {
  @Override
  public void onEnable() {
    this.getCommand("noperms").setExecutor(new NoPermsCommand());
  }
  @Override
  public void onDisable() {

  }
}

