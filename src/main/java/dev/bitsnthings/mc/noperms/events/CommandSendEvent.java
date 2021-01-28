package dev.bitsnthings.mc.noperms.events;

import dev.bitsnthings.mc.noperms.NoPerms;

import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public class CommandSendEvent implements Listener {
  List<String> hiddenCommands = NoPerms.getInstance().getInternalConfig().hiddenCommands;
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerCommandSend(PlayerCommandSendEvent event) {
    Collection<String> commands = event.getCommands();
    //commands.addAll(Arrays.asList(new String[]{"/?","/help"}));
    if (!event.getPlayer().hasPermission("bukkit.command.plugins")) {
      commands.removeAll(hiddenCommands);
    }
  }
}
/*
for (String command: commands) {
  NoPerms.getInstance().getLogger().info(command);
}
*/
