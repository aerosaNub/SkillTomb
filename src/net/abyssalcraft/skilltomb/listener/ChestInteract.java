package net.abyssalcraft.skilltomb.listener;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.characters.skill.Skill;
import net.abyssalcraft.skilltomb.SkillTomb;
import net.abyssalcraft.skilltomb.files.SkillsFlatFile;
import net.abyssalcraft.skilltomb.handlers.ItemCreation;
import net.abyssalcraft.skilltomb.handlers.Rarity;
import net.abyssalcraft.skilltomb.handlers.SkillTombHandler;
import net.abyssalcraft.skilltomb.utils.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ChestInteract implements Listener {

    @EventHandler
    public void onChestInteractions(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        ItemStack item = e.getItem();

        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (item == null) return;
            if (item.getType() == Material.AIR) return;
            if (player.hasPermission("skilltomb.admin")) {
                if (block == null) return;
                if (block.getType() == Material.AIR) return;

                if (item.getType() == Material.WOOD_HOE) {
                    String[] displayName = item.getItemMeta().getDisplayName().split(" ");
                    String rarityType = displayName[0].substring(2);
                    if (block.getType() == Material.CHEST) {

                        SkillTomb.getPlugin().getChestsData().setChest(block.getLocation(), rarityType);
                        player.sendMessage(Chat.c("&eThat chest is now set to a skilltomb generator! Rarity Type " +  rarityType));
                    } else {
                        player.sendMessage(Chat.c("&8[&4&l!&8] &cThat block is not a chest!"));
                    }
                }
            }
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (block == null) return;
            if (block.getType() == Material.AIR) return;

            if (block.getType() == Material.CHEST) {

                for (String keys : SkillTomb.getPlugin().getChestsData().getConfig().getConfigurationSection("Chests").getKeys(false)) {

                    Location loc = SkillTomb.getPlugin().getChestsData().chestLocation(keys);

                   // if (!loc.equals(block.getLocation())) return;


                    if (loc.equals(block.getLocation())) {


                        if (keys.equals("Empty")) {
                            return;
                        }


                        for (String rareKeys : SkillTomb.getPlugin().getSkillsFlatFile().getConfig().getConfigurationSection("Skills.rarities").getKeys(false)) {


                            boolean isEqual = SkillTomb.getPlugin().getChestsData().getConfig().getString("Chests." + keys + ".rarity").equals(rareKeys);


                            if (isEqual) {
                                for (String skillName : SkillTomb.getPlugin().getSkillsFlatFile().getSkills(rareKeys)) {

                                    List<String> uuid = SkillTomb.getPlugin().getChestsData().getConfig().getStringList("Chests." + keys + ".already_used");

                                    if (uuid.contains(player.getUniqueId().toString())) {
                                        player.sendMessage(Chat.c(Chat.prefix + " &cYou already used this chest so there was no skilltomb for you here!"));
                                        return;
                                    }


                                    Skill skills = Heroes.getInstance().getSkillManager().getSkill(skillName);


                                    if (block.getState() instanceof Chest) {

                                        Chest chest = (Chest) block.getState();

                                        chest.getInventory().addItem(ItemCreation.i().skillBook(skills));

                                        player.sendMessage(Chat.c("&8[&dSkillTomb&8] &aYou found a " + skillName + " skill book in a &d&l" + SkillTomb.getPlugin().getChestsData().getRarity(keys) + " &atype chest!"));

                                        SkillTomb.getPlugin().getChestsData().addUsage(keys, player.getUniqueId());
                                    }
                                }

                            }
                        }
                    }

                }

            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory() == null) return;

        InventoryHolder holder = e.getInventory().getHolder();

        if (holder instanceof Chest || holder instanceof DoubleChest) {

           Chest chest = (Chest) holder;

           Location location = chest.getLocation();
            for (String keys : SkillTomb.getPlugin().getChestsData().getConfig().getConfigurationSection("Chests").getKeys(false)) {

                Location loc = SkillTomb.getPlugin().getChestsData().chestLocation(keys);
                if (location.equals(loc)) {

                    e.getInventory().clear();

                }
            }

        }
    }
}
