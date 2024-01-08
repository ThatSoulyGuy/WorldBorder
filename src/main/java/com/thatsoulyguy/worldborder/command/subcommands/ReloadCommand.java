package com.thatsoulyguy.worldborder.command.subcommands;

import com.thatsoulyguy.worldborder.WorldBorder;
import com.thatsoulyguy.worldborder.command.WBCommand;
import com.thatsoulyguy.worldborder.command.WBCommandRegistration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class ReloadCommand extends WBCommand
{

    @Override
    public int Execute(Player player, String[] args)
    {
        player.sendMessage(ChatColor.YELLOW + "Attempting to reload...");

        WorldBorder.Instance.defaultConfig.Reload();
        WorldBorder.Instance.pigConfig.Reload();

        Bukkit.getWorld("world").getWorldBorder().setSize((int) WorldBorder.Instance.defaultConfig.GetValue("worldborder.size"), TimeUnit.MILLISECONDS, 0);

        player.sendMessage(ChatColor.GREEN + "Successfully Reloaded!");

        return 0;
    }

    @Override
    public WBCommandRegistration Register()
    {
        return WBCommandRegistration.Register("reload", "Reloads the plugin", "/bordersmp reload");
    }
}