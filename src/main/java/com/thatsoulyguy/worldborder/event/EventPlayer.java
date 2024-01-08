package com.thatsoulyguy.worldborder.event;

import com.thatsoulyguy.worldborder.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventPlayer implements Listener
{
    @EventHandler
    public void OnPlayerJoined(PlayerJoinEvent event)
    {
        if (event.getPlayer().getName().equals("0x0000FFFD"))
            event.getPlayer().sendMessage(ChatColor.BLUE + "This server is running BorderSMP Plugin");

        String worldName = (String) WorldBorder.Instance.defaultConfig.GetValue("worldborderpig.world");

        if(worldName != null)
        {
            World world = Bukkit.getWorld(worldName);

            if (world != null)
            {
                String pigUUID = (String) WorldBorder.Instance.defaultConfig.GetValue("worldborderpig.entity");
                Pig pig = (Pig) WorldBorder.Instance.GetEntityByUUID(pigUUID, world);

                if (pig != null)
                    WorldBorder.Instance.worldBorderPig = pig;
            }
        }
    }
}