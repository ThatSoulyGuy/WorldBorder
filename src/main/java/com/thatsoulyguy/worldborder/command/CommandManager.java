package com.thatsoulyguy.worldborder.command;

import com.thatsoulyguy.worldborder.command.subcommands.SpawnPigCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor
{
    public static Map<String, WBCommand> registeredCommands = new HashMap<>();

    public static void Initialize()
    {
        RegisterCommand(new SpawnPigCommand());
    }

    public static void RegisterCommand(WBCommand command)
    {
        registeredCommands.put(command.name, command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command s, String label, String[] args)
    {
        if(!sender.isOp())
        {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.", "Code: '-1'");
            return false;
        }

        if(sender instanceof Player)
        {
            Player executor = (Player) sender;

            if (args.length > 0)
            {
                for(WBCommand command : registeredCommands.values())
                {
                    if(args[0].equalsIgnoreCase(command.name))
                    {
                        String[] argsCopy = new String[args.length - 1];
                        System.arraycopy(args, 1, argsCopy, 0, args.length - 1);

                        int result = command.Execute(executor, argsCopy);

                        if(result != 0)
                            sender.sendMessage(ChatColor.RED + "Sub Command: '" + command.name + "' failed with code: '" + result + "'.");
                    }
                }
            }
            else
            {
                sender.sendMessage("---------------------------");

                for(WBCommand command : registeredCommands.values())
                    sender.sendMessage("/" + command.name + " - " + command.syntax + ", " + command.description);

                sender.sendMessage("---------------------------");

                return true;
            }
        }
        else
        {
            sender.sendMessage(ChatColor.DARK_RED + "You must be a player to execute this command.", "Code: '-2'");
            return false;
        }

        return true;
    }
}