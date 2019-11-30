package com.rasmushenneken.ragequit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ragequit extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit]" + ChatColor.WHITE + " Ragequits have been enabled!");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit]" + ChatColor.WHITE + " Ragequits have been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("rq")) {
            if(sender instanceof Player) {
                String rqmsg = getConfig().getString("ragequit_message");
                String kickmsg = getConfig().getString("kick_message");
                Player player = (Player) sender;

                if(kickmsg == null) {
                    return false;
                }else {
                    player.kickPlayer(ChatColor.translateAlternateColorCodes('&', kickmsg));
                }

                if(rqmsg == null) {
                    return false;
                }else {
                    rqmsg = rqmsg.replace("%player%", player.getDisplayName());
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', rqmsg));
                }

            }else {
                ConsoleCommandSender console = getServer().getConsoleSender();
                console.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit]" + ChatColor.WHITE + " You can't rq from the console!");
            }
        }
        return false;
    }
}
