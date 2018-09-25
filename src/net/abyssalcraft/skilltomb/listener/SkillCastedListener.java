package net.abyssalcraft.skilltomb.listener;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.api.SkillResult;
import com.herocraftonline.heroes.api.events.SkillUseEvent;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.classes.ClassSkill;
import com.herocraftonline.heroes.characters.skill.HeroSkill;
import com.herocraftonline.heroes.characters.skill.Skill;
import com.herocraftonline.heroes.characters.skill.SkillManager;
import com.herocraftonline.heroes.util.Messaging;
import com.herocraftonline.items.api.message.Message;
import net.abyssalcraft.skilltomb.SkillTomb;
import net.abyssalcraft.skilltomb.utils.Chat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class SkillCastedListener implements Listener {

    @EventHandler
    public void onSkillCast(SkillUseEvent e) {
        Player p = e.getPlayer();
        Hero hero = e.getHero();
        Skill skill = e.getSkill();


        if (p.getItemInHand() == null) return;
        if (p.getItemInHand().getType() == Material.AIR) return;

        String[] itemName = p.getItemInHand().getItemMeta().getDisplayName().split(" ");
        String skillName = itemName[0];
        String trimName = skillName.substring(2);

            e.setManaCost(0);
            e.setHealthCost(0.0);
            e.setStaminaCost(0);
            e.setReagentCost(null);

            String[] messages = new String[] {
                    Chat.c("&eYou were not charged to use the " + skill.getName() + " Booklet!")
            };

            e.setArgs(messages);
    }
}
