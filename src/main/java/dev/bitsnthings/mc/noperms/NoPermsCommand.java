package dev.bitsnthings.mc.noperms;

import org.bukkit.command.*;
import org.bukkit.ChatColor;

public class NoPermsCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length > 0 && args.length < 3) {
      switch (args[0].toLowerCase()) {
        case "get":
          // Try to get permission with name args[1]
          sender.sendMessage(String.format("Niggacat!"));
        break;
        default: return false;
      }
      return true;
    } else return false;
  }
}
