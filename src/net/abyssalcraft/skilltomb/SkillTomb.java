package net.abyssalcraft.skilltomb;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import net.abyssalcraft.skilltomb.command.SkillTombCommand;
import net.abyssalcraft.skilltomb.files.Chests;
import net.abyssalcraft.skilltomb.files.SkillsFlatFile;
import net.abyssalcraft.skilltomb.handlers.SkillTombHandler;
import net.abyssalcraft.skilltomb.listener.BookClickListener;
import net.abyssalcraft.skilltomb.listener.ChestInteract;
import net.abyssalcraft.skilltomb.listener.SkillCastedListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkillTomb extends JavaPlugin {

    private static SkillTomb plugin;
    private SkillsFlatFile skillsFlatFile;
    private SkillTombHandler skillTombHandler;
    private Chests chests;
    private WorldEditPlugin worldEdit;


    public void onEnable() {
        plugin = this;

        if (!this.getDataFolder().exists()) this.getDataFolder().mkdir();

        getCommand("skilltomb").setExecutor(new SkillTombCommand());
        getServer().getPluginManager().registerEvents(new ChestInteract(), this);
        getServer().getPluginManager().registerEvents(new BookClickListener(), this);
        getServer().getPluginManager().registerEvents(new SkillCastedListener(), this);

        this.skillsFlatFile = new SkillsFlatFile();
        this.chests = new Chests();
        this.skillTombHandler = new SkillTombHandler();
        worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    }

    public void onDisable() {

        this.chests.saveChestData();

        plugin = null;
    }

    public static SkillTomb getPlugin() {
        return plugin;
    }


    public SkillsFlatFile getSkillsFlatFile() {
       return skillsFlatFile;
    }

    public Chests getChestsData() {
        return chests;
    }

    public SkillTombHandler getSkillTombHandler() {
        return skillTombHandler;
    }

    public WorldEditPlugin getWorldEdit() {
        return worldEdit;
    }
}
