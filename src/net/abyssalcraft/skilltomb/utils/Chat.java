package net.abyssalcraft.skilltomb.utils;

import org.bukkit.ChatColor;

public class Chat {

    public static String prefix = "&8[&dSkillTomb&8]";
    public static String important = "&8[&4&l!&8]";

    public static String c(String msg ) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
