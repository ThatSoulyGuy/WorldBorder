package com.thatsoulyguy.worldborder;

import com.thatsoulyguy.worldborder.command.CommandManager;
import com.thatsoulyguy.worldborder.event.EventPlayer;
import com.thatsoulyguy.worldborder.util.WBConfig;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class WorldBorder extends JavaPlugin
{
    public WBConfig pigConfig = new WBConfig();
    public WBConfig defaultConfig = new WBConfig();

    private List<Runnable> updateCalls = new ArrayList<>();

    public Pig worldBorderPig = null;
    public Logger logger = getLogger();

    public static WorldBorder Instance = null;

    @Override
    public void onEnable()
    {
        logger.log(Level.INFO, "Initializing Border SMP Plugin...");

        Instance = this;

        defaultConfig.Initialize("config.yml");

        Bukkit.getWorld("world").getWorldBorder().setSize((int)defaultConfig.GetValue("worldborder.size"), TimeUnit.MILLISECONDS, 0);
        CommandManager.Initialize();

        getCommand("bordersmp").setExecutor(new CommandManager());
        getServer().getPluginManager().registerEvents(new EventPlayer(), this);

        pigConfig.Initialize("pig.yml");

        Instance = this;

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                Update();
            }
        }.runTaskTimer(this, 0L, 20L);

        logger.log(Level.INFO, "Successfully initialized Border SMP Plugin!");
    }

    private void Update()
    {
        for(Runnable function : updateCalls)
            function.run();

        if(worldBorderPig == null)
            return;

        worldBorderPig.getWorld().getWorldBorder().setCenter(worldBorderPig.getLocation());
    }

    public Entity GetEntityByUUID(String uuidString, World world)
    {
        UUID uuid;

        try
        {
            uuid = UUID.fromString(uuidString);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }

        for (Entity entity : world.getEntities())
        {
            if (entity.getUniqueId().equals(uuid))
                return entity;
        }

        return null;
    }

    @Override
    public void onDisable()
    {
        logger.log(Level.INFO, "Disabling BorderSMP plugin...");
        logger.log(Level.INFO, "Disabled BorderSMP plugin!");
    }
}
