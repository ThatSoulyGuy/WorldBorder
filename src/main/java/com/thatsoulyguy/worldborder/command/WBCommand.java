package com.thatsoulyguy.worldborder.command;

import org.bukkit.entity.Player;

public abstract class WBCommand
{
    public String name;

    public String description;

    public String syntax;

    public WBCommand()
    {
        WBCommandRegistration registration = Register();

        name = registration.name;
        description = registration.description;
        syntax = registration.syntax;
    }

    public abstract int Execute(Player player, String[] args);

    public abstract WBCommandRegistration Register();
}