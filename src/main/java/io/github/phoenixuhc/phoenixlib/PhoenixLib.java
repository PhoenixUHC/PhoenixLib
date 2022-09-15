package io.github.phoenixuhc.phoenixlib;

import org.bukkit.plugin.Plugin;

import io.github.phoenixuhc.phoenixlib.menus.Menu;

public class PhoenixLib {
    public static void init(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Menu.MenuListener(), plugin);
    }
}

