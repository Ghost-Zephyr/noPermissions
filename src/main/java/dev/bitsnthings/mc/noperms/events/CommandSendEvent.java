package dev.bitsnthings.mc.noperms.events;

import dev.bitsnthings.mc.noperms.NoPerms;

import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/* This code runs beafore the server sends the client the command tablist. */

public class CommandSendEvent implements Listener {
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerCommandSend(PlayerCommandSendEvent event) {
    /* Let's hide commands from config. */
    if (!event.getPlayer().hasPermission("bukkit.command.plugins")) event.getCommands().removeAll(NoPerms.config.hiddenCommands);
  }
}
