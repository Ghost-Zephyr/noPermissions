package dev.bitsnthings.mc.noperms.events;

import dev.bitsnthings.mc.noperms.NoPerms;

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
  PluginManager pluginManager = Bukkit.getPluginManager();
	@EventHandler(priority=EventPriority.HIGH)
	public void onServerLoaded(ServerLoadEvent event) {
    /* Set default permissions from config. */
    log.info("Starting permission magic.");
    setDefaultPerms(NoPerms.config.defaultFalse, PermissionDefault.FALSE);
    setDefaultPerms(NoPerms.config.defaultTrue, PermissionDefault.TRUE);
    setDefaultPerms(NoPerms.config.defaultOp, PermissionDefault.OP);
    log.info("Done messing with default permissions.");
  }
  private void setDefaultPerms(List<String> perms, PermissionDefault permissionDefault) {
    for (String permName: perms) {
      Permission perm = pluginManager.getPermission(permName);
      if (perm == null) log.warning(String.format("Could not get permission with name %s!", permName));
      else {
        perm.setDefault(permissionDefault);
        pluginManager.recalculatePermissionDefaults(perm);
      }
    }
  }
}

