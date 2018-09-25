package net.abyssalcraft.skilltomb.files.objects;

import net.abyssalcraft.skilltomb.handlers.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public class Chest
{
    private Rarity rarity;
    private int id;
    private String world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private static List<String> users;

    public Chest(Rarity rarity, int id, String world, double x, double y, double z, float yaw, float pitch, List<String> userss) {
        this.rarity = rarity;
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
        users = userss;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Location getChestLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public int getChestID() {
        return id;
    }

    public static List<String> getUsers() {
        return users;
    }
}
