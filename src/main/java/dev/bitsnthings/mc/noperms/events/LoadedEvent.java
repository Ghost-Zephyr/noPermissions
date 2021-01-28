package dev.bitsnthings.mc.noperms.events;

import dev.bitsnthings.mc.noperms.NoPerms;
import dev.bitsnthings.mc.noperms.Config;

import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;

import java.util.logging.Logger;
import java.util.Set;

public class LoadedEvent implements Listener {
  Logger log = NoPerms.getInstance().getLogger();
  Config config = NoPerms.getInstance().getInternalConfig();
  PluginManager pluginManager = Bukkit.getPluginManager();
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(ServerLoadEvent event) {
    log.info("Starting permission magic.");
    for (String permName: config.defaultFalse) {
      Permission perm = pluginManager.getPermission(permName);
      perm.setDefault(PermissionDefault.FALSE);
      pluginManager.recalculatePermissionDefaults(perm);
    }
    for (String permName: config.defaultTrue) {
      Permission perm = pluginManager.getPermission(permName);
      perm.setDefault(PermissionDefault.TRUE);
      pluginManager.recalculatePermissionDefaults(perm);
    }
    for (String permName: config.defaultOp) {
      Permission perm = pluginManager.getPermission(permName);
      perm.setDefault(PermissionDefault.OP);
      pluginManager.recalculatePermissionDefaults(perm);
    }
    log.info("Done messing with default permissions!");
  }
}
/*
    Set<Permission> allperms = Bukkit.getPluginManager().getPermissions();
    for(Permission perm: allperms) {
      log.info(String.format("%s: %s", perm.getName(), perm.getDefault()));
      if (perm.getDefault() == PermissionDefault.TRUE) log.info("^ Has PermissionDefault.TRUE");
    }
*/
