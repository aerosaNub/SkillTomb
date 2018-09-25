package net.abyssalcraft.skilltomb.files;

import net.abyssalcraft.skilltomb.SkillTomb;
import net.abyssalcraft.skilltomb.files.objects.Chest;
import net.abyssalcraft.skilltomb.handlers.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Chests {

    private File file;
    private YamlConfiguration configuration;
    private ArrayList<Chest> chests = new ArrayList<>();

    int id = chests.size();

    public Chests() {
        this.file = new File(SkillTomb.getPlugin().getDataFolder(), "chest.yml");
        this.configuration = YamlConfiguration.loadConfiguration(file);

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
                this.configuration.save(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (getConfig().get("Chests") != null) {
                for (String keys : getConfig().getConfigurationSection("Chests").getKeys(false)) {
                    this.chests.add(new Chest(Rarity.valueOf(getConfig().getString("Chests." + keys + ".rarity")),
                            getConfig().getInt(keys),
                            getConfig().getString("Chests." + keys + ".location.world"),
                            getConfig().getDouble("Chests." + keys + ".location.x"),
                            getConfig().getDouble("Chests." + keys + ".location.y"),
                            getConfig().getDouble("Chests." + keys + ".location.z"),
                            (float) getConfig().getDouble("Chests." + keys + ".location.yaw"),
                            (float) getConfig().getDouble("Chests." + keys + ".location.pitch"),
                            getConfig().getStringList("Chests." + keys + ".already_used")));
                }
            }
        }
    }

    public void addUsage(String keys, UUID uuid) {



            List<String> uuids = getConfig().getStringList("Chests." + keys + ".already_used");

            uuids.add(uuid.toString());

            getConfig().set("Chests." + keys + ".already_used", uuids);

    }

    public void setChest(Location location, String rarityName) {

        Rarity rarity = Rarity.valueOf(rarityName.toUpperCase());
        int newInteger = this.chests.size() + 1;

        Chest createdChest = new Chest(Rarity.valueOf(rarity.getName()), newInteger, location.getWorld().getName(),
                location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), new ArrayList<String>());

        this.chests.add(createdChest);


        getConfig().set("Chests." + newInteger + ".rarity", rarity.getName());
        getConfig().set("Chests." +newInteger + ".location.world", location.getWorld().getName());
        getConfig().set("Chests." +newInteger + ".location.x", location.getX());
        getConfig().set("Chests." +newInteger + ".location.y", location.getY());
        getConfig().set("Chests." +newInteger + ".location.z", location.getZ());
        getConfig().set("Chests." +newInteger + ".location.yaw", location.getYaw());
        getConfig().set("Chests." +newInteger + ".location.pitch", location.getPitch());

        saveChestData();
    }


    public Location chestLocation(String keys) {
        return new Location(Bukkit.getWorld(getConfig().getString("Chests." + keys + ".location.world")),
                getConfig().getDouble("Chests." + keys + ".location.x"),
                getConfig().getDouble("Chests." + keys + ".location.y"),
                getConfig().getDouble("Chests." + keys + ".location.z"),
                (float) getConfig().getDouble("Chests." + keys + ".location.yaw"),
                (float) getConfig().getDouble("Chests." + keys + ".location.pitch"));
    }


    public void saveChestData() {
        try {
            this.getConfig().save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Rarity getRarity(String keys) {
        return Rarity.valueOf(getConfig().getString("Chests." + keys + ".rarity"));
    }

    public ArrayList<Chest> getChests() {
        return chests;
    }

    public FileConfiguration getConfig() {
        return this.configuration;
    }
}
