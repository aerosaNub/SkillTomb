package net.abyssalcraft.skilltomb.listener;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.api.events.SkillUseEvent;
import com.herocraftonline.heroes.characters.skill.ActiveSkill;
import com.herocraftonline.heroes.characters.skill.Skill;
import com.herocraftonline.heroes.characters.skill.SkillManager;
import net.abyssalcraft.skilltomb.SkillTomb;
import net.abyssalcraft.skilltomb.files.SkillsFlatFile;
import net.abyssalcraft.skilltomb.handlers.SkillTombHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

public class BookClickListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        if (e.getItem() == null) return;
        if (e.getItem().getType() == Material.AIR) return;

        if (e.getItem().getType() == Material.BOOK) {
            if (e.getItem().getItemMeta().getDisplayName().contains("Book")) {

                String[] itemName = e.getItem().getItemMeta().getDisplayName().split(" ");
                String skillName = itemName[0];

                runExecute(e.getPlayer(), skillName.substring(2));
                for (String chestType : SkillTomb.getPlugin().getSkillsFlatFile().getConfig().getConfigurationSection("Skills.rarities").getKeys(false)) {
                    for (String perms : SkillTomb.getPlugin().getSkillsFlatFile().getConfig().getConfigurationSection("Skills.rarities." + chestType + ".skills." + skillName + ".permission").getKeys(false)) {
                        PermissionAttachment attachment = e.getPlayer().addAttachment(SkillTomb.getPlugin());
                        attachment.setPermission(perms, true);
                    }
                }

                SkillTombHandler.init().removeSkillBook(e.getPlayer());

                for (String chestType : SkillTomb.getPlugin().getSkillsFlatFile().getConfig().getConfigurationSection("Skills.rarities").getKeys(false)) {
                    for (String perms : SkillTomb.getPlugin().getSkillsFlatFile().getConfig().getConfigurationSection("Skills.rarities." + chestType + ".skills." + skillName + ".permission").getKeys(false)) {
                        PermissionAttachment attachment = e.getPlayer().addAttachment(SkillTomb.getPlugin());
                        attachment.unsetPermission(perms);
                    }
                }
            }
        }
    }

    private void runExecute(Player e, String keys) {

        CommandSender sender = (CommandSender) e;

        String identifier = "skill " + keys;

        String[] args = new String[] {""};

        Heroes.getInstance().getSkillManager().getSkill(keys).execute(sender, identifier, args);

    }
}
