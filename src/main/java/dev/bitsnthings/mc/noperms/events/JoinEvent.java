package dev.bitsnthings.mc.noperms.events;

import dev.bitsnthings.mc.noperms.NoPerms;
import dev.bitsnthings.mc.noperms.Config;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
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
    if (!Config.gods.contains(uuid.toString())) return;
    Set<Permission> allperms = Bukkit.getPluginManager().getPermissions();
    PermissionAttachment attachment = player.addAttachment(NoPerms.getInstance().plugin);
    for (Permission perm: allperms) {
      attachment.setPermission(perm, true);
    }
    log.warning(String.format("Granted %s all permissions!", player.getName()));
  }
  private void scuffed(PlayerJoinEvent event) {
    log.info(String.format("Got %s mappings in PermissionAttachment hashmap!", Config.perms.size()));
    Player player = event.getPlayer();
    UUID uuid = player.getUniqueId();
    if (!Config.gods.contains(uuid.toString())) return;
    String name = player.getName();
    if (!Config.perms.containsKey(uuid)) {
      log.info(String.format("Player with name: \"%s\" is a god and is not in the permission attachment hashmap, granting all perms.", name));
      Config.perms.put(uuid, player.addAttachment(NoPerms.getInstance().plugin));
    }
    Set<Permission> allperms = Bukkit.getPluginManager().getPermissions();
    PermissionAttachment attachment = NoPerms.getInstance().getInternalConfig().perms.get(uuid);
    for (Permission perm: allperms) {
      attachment.setPermission(perm, true);
    }
    log.warning(String.format("Granted %s all permissions!", name));
  }
}



