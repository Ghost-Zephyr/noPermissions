package dev.bitsnthings.mc.noperms.events;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;

import dev.bitsnthings.mc.noperms.NoPerms;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import java.util.logging.Logger;

public class CommandPreprocessEvent implements Listener {
  Logger log = NoPerms.getInstance().getLogger();
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    String msg = event.getMessage();
    Command cmd = new JavaPlugin().getCommand(msg);
    log.info(String.format("Command %s has permission node %s", msg, cmd.getPermission()));
    if (!event.getPlayer().hasPermission("bukkit.command.help") && Arrays.asList(new String[]{"/help","/?"}).contains(event.getMessage().split(" ")[0])) event.setMessage("/minecraft:help");
  }
}
