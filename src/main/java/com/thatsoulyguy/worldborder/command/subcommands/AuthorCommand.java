package com.thatsoulyguy.worldborder.command.subcommands;

import com.thatsoulyguy.worldborder.command.WBCommand;
import com.thatsoulyguy.worldborder.command.WBCommandRegistration;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AuthorCommand extends WBCommand
{

    @Override
    public int Execute(Player player, String[] args)
    {
        player.sendMessage(ChatColor.YELLOW + "This plugin was created by ThatSoulyGuy, also known as 0x0000FFFD.");

        return 0;
    }

    @Override
    public WBCommandRegistration Register()
    {
        return WBCommandRegistration.Register("author", "Tells you who made this plugin.", "/bordersmp author");
    }
}