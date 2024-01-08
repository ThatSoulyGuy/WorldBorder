package com.thatsoulyguy.worldborder.command;

public class WBCommandRegistration
{
    public String name;
    public String description;
    public String syntax;

    public static WBCommandRegistration Register(String name, String description, String syntax)
    {
        WBCommandRegistration out = new WBCommandRegistration();

        out.name = name;
        out.description = description;
        out.syntax = syntax;

        return out;
    }
}
