package com.rasmushenneken.ragequit.commands;

import com.rasmushenneken.ragequit.Ragequit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class rq implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Get strings from config
        String kickmsg = Ragequit.instance.getConfig().getString("kick_message");
        String rqmsg = Ragequit.instance.getConfig().getString("ragequit_message");
        String noperms = Ragequit.instance.getConfig().getString("no_permission");

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if(player.hasPermission("rq.reload")) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit] " + ChatColor.WHITE + "Reloading...");
                        Ragequit.instance.reloadConfig();
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit] " + ChatColor.WHITE + "Reload complete.");
                    } else {
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit] " + ChatColor.WHITE + "False argument.");
                    }
                } else {
                    if(noperms == null) {
                        return false;
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', noperms));
                    }
                }

            } else {
                if(kickmsg == null) {
                    return false;
                } else {
                    kickmsg = kickmsg.replace("%player%", player.getDisplayName());
                    player.kickPlayer(ChatColor.translateAlternateColorCodes('&', kickmsg));
                if(rqmsg == null) {
                    return false;
                } else {
                    rqmsg = rqmsg.replace("%player%", player.getDisplayName());
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', rqmsg));
                }
                }
            }
        } else {
            ConsoleCommandSender console = Ragequit.instance.getServer().getConsoleSender();
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    console.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit] " + ChatColor.WHITE + "Reloading...");
                    Ragequit.instance.reloadConfig();
                    console.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit] " + ChatColor.WHITE + "Reload complete.");
                } else {
                    console.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit] " + ChatColor.WHITE + "False argument.");
                }
            } else {
                console.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit] " + ChatColor.WHITE + "You can't rq from the console!");
            }
        }
        return true;
    }
}