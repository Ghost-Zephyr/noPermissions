package dev.bitsnthings.mc.noperms.events;

import dev.bitsnthings.mc.noperms.Config;

import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CommandSendEvent implements Listener {
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerCommandSend(PlayerCommandSendEvent event) {
    if (!event.getPlayer().hasPermission("bukkit.command.plugins")) event.getCommands().removeAll(Config.hiddenCommands);
  }
}
