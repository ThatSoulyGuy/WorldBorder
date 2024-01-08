package com.thatsoulyguy.worldborder.command.subcommands;

import com.thatsoulyguy.worldborder.WorldBorder;
import com.thatsoulyguy.worldborder.command.WBCommand;
import com.thatsoulyguy.worldborder.command.WBCommandRegistration;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;

public class SpawnPigCommand extends WBCommand
{
    @Override
    public int Execute(Player player, String[] args)
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

        return 0;
    }

    @Override
    public WBCommandRegistration Register()
    {
        return WBCommandRegistration.Register("summonpig", "Summons the WorldBorder pig.", "/bordersmp summonpig");
    }
}