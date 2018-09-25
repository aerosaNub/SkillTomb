package net.abyssalcraft.skilltomb.handlers;

import net.abyssalcraft.skilltomb.SkillTomb;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SkillTombHandler {

    private static SkillTombHandler sth;

    public static SkillTombHandler init() {
        if (sth == null) {
            sth = new SkillTombHandler();
        }
        return sth;
    }


    public void removeSkillBook(Player player) {
        ItemStack item = player.getItemInHand();

        if (item == null) return;
        if (item.getType() != Material.BOOK) return;


        if (item.hasItemMeta()) {
            if (item.getAmount() > 1) {
                item.setAmount(item.getAmount() - 1);
                return;
            } else {
                item.setAmount(0);
            }

        }
    }
}
