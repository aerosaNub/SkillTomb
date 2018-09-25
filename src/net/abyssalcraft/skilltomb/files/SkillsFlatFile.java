package net.abyssalcraft.skilltomb.files;
import net.abyssalcraft.skilltomb.SkillTomb;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.List;
import java.util.Set;

public class SkillsFlatFile {

    private File file;
    private YamlConfiguration configuration;


    public SkillsFlatFile() {
        this.file = new File(SkillTomb.getPlugin().getDataFolder(), "skills.yml");
        this.configuration = YamlConfiguration.loadConfiguration(file);


        if (!(this.file.exists())) {
            try {
                file.createNewFile();
                configuration.save(file);
            } catch (Exception e) {
                System.out.println("Unable to create skills.yml!");
            }
        }
    }

    public List<String> getSkills(String keys) {
        return this.configuration.getStringList("Skills.rarities." + keys + ".skills");
    }

    public FileConfiguration getConfig() {
        return this.configuration;
    }

}
