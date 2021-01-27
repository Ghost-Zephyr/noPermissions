package dev.bitsnthings.mc.noperms;

import org.bukkit.command.*;
import org.bukkit.ChatColor;

public class NoPermsCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    sender.sendMessage(ChatColor.RED+"This here command ain't implemented yet!");
    return true;
  }
}
