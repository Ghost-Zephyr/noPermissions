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
import java.util.List;

public class LoadedEvent implements Listener {
  Logger log = NoPerms.getInstance().getLogger();
  Config config = NoPerms.getInstance().getInternalConfig();
  PluginManager pluginManager = Bukkit.getPluginManager();
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(ServerLoadEvent event) {
    log.info("Starting permission magic.");
    setDefaultPerms(config.defaultFalse, PermissionDefault.FALSE);
    setDefaultPerms(config.defaultTrue, PermissionDefault.TRUE);
    setDefaultPerms(config.defaultOp, PermissionDefault.OP);
    log.info("Done messing with default permissions!");
  }
  private void setDefaultPerms(List<String> perms, PermissionDefault permissionDefault) {
    for (String permName: perms) {
      Permission perm = pluginManager.getPermission(permName);
      perm.setDefault(permissionDefault);
      pluginManager.recalculatePermissionDefaults(perm);
    }
  }
}
/*
import java.util.Set;

    Set<Permission> allperms = Bukkit.getPluginManager().getPermissions();
    for(Permission perm: allperms) {
      log.info(String.format("%s: %s", perm.getName(), perm.getDefault()));
      if (perm.getDefault() == PermissionDefault.TRUE) log.info("^ Has PermissionDefault.TRUE");
    }
*/
