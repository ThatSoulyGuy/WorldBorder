package com.thatsoulyguy.worldborder.command;

import com.thatsoulyguy.worldborder.WorldBorder;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;

public class SpawnPig implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (sender.isOp())
            {
                Location location = player.getLocation();

                Pig pig = (Pig) player.getWorld().spawnEntity(location, EntityType.PIG);

                pig.setInvulnerable(true);
                pig.setCustomName("§d§lT§5§lH§d§lE §5§lB§d§lO§5§lR§d§lD§5§lE§d§lR §5§lP§d§lI§5§lG");
                pig.setCustomNameVisible(true);
                pig.setSaddle(true);
                pig.setGlowing(true);

                WorldBorder.Instance.defaultConfig.SetValue("worldborderpig.entity", pig.getUniqueId().toString());
                WorldBorder.Instance.defaultConfig.SetValue("worldborderpig.world", pig.getWorld().getName());

                WorldBorder.Instance.worldBorderPig = pig;

                return true;
            }
            else
            {
                player.sendMessage("You do not have permission to use this command.");
                return false;
            }
        }
        else
        {
            sender.sendMessage("This command can only be used by a player.");
            return false;
        }
    }
}