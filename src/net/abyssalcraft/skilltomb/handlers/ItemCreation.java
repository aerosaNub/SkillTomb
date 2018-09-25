package net.abyssalcraft.skilltomb.handlers;

import com.herocraftonline.heroes.characters.skill.Skill;
import net.abyssalcraft.skilltomb.SkillTomb;
import net.abyssalcraft.skilltomb.utils.Chat;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemCreation {

    private static ItemCreation ic;

    public static ItemCreation i() {
        if (ic == null) {
            ic = new ItemCreation();
        }
        return ic;
    }

    public ItemStack skillBook(Skill skill) {
        ItemStack item = new ItemStack(Material.BOOK);

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.RED + skill.getName() + " Book");
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Right-Click to apply skill!"));
            item.setItemMeta(meta);


        return item;
    }

    public void giveWands(Player p) {
        ItemStack item = new ItemStack(Material.WOOD_HOE);
        for (Rarity rarities : Rarity.values()) {
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Chat.c("&e" + rarities.getName() + " SetChest Wands"));
            item.setItemMeta(meta);

            p.getInventory().addItem(item);
        }
    }
}
