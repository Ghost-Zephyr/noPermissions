package dev.bitsnthings.mc.noperms.events;

import dev.bitsnthings.mc.noperms.NoPerms;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
//import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import java.util.logging.Logger;
import java.util.UUID;
import java.util.Set;

public class JoinEvent implements Listener {
  Logger log = NoPerms.getInstance().getLogger();
	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    UUID uuid = player.getUniqueId();
    if (!NoPerms.config.gods.contains(uuid.toString())) return;
    Set<Permission> allperms = Bukkit.getPluginManager().getPermissions();
    PermissionAttachment attachment = player.addAttachment(NoPerms.plugin);
    for (Permission perm: allperms) {
      attachment.setPermission(perm, true);
    }
    log.warning(String.format("Granted %s all permissions!", player.getName()));
  }
  private void scuffed(PlayerJoinEvent event) {
    log.info(String.format("Got %s mappings in PermissionAttachment hashmap!", NoPerms.config.perms.size()));
    Player player = event.getPlayer();
    UUID uuid = player.getUniqueId();
    if (!NoPerms.config.gods.contains(uuid.toString())) return;
    String name = player.getName();
    if (!NoPerms.config.perms.containsKey(uuid)) {
      log.info(String.format("Player with name: \"%s\" is a god and is not in the permission attachment hashmap, granting all perms.", name));
      NoPerms.config.perms.put(uuid, player.addAttachment(NoPerms.plugin));
    }
    Set<Permission> allperms = Bukkit.getPluginManager().getPermissions();
    PermissionAttachment attachment = NoPerms.config.perms.get(uuid);
    for (Permission perm: allperms) {
      attachment.setPermission(perm, true);
    }
    log.warning(String.format("Granted %s all permissions!", name));
  }
}



