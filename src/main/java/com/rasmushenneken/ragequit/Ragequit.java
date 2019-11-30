package com.rasmushenneken.ragequit;

import com.rasmushenneken.ragequit.commands.rq;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ragequit extends JavaPlugin {
public static Plugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit]" + ChatColor.WHITE + " Ragequits have been enabled!");

        instance = this;

        // Setup config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Register commands
        getCommand("rq").setExecutor(new rq());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.LIGHT_PURPLE + "[Ragequit]" + ChatColor.WHITE + " Ragequits have been disabled!");
    }
}
