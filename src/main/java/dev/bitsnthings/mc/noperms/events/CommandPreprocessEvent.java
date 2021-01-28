package dev.bitsnthings.mc.noperms.events;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;

import java.util.Arrays;

import dev.bitsnthings.mc.noperms.NoPerms;
import java.util.logging.Logger;

public class CommandPreprocessEvent implements Listener {
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    if (!event.getPlayer().hasPermission("bukkit.command.help") && Arrays.asList(new String[]{"/help","/?"}).contains(event.getMessage())) event.setMessage("/minecraft:help");
  }
}
