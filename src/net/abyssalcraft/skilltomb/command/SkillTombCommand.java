package net.abyssalcraft.skilltomb.command;

import com.sk89q.worldedit.bukkit.selections.Selection;
import net.abyssalcraft.skilltomb.SkillTomb;
import net.abyssalcraft.skilltomb.handlers.Rarity;
import net.abyssalcraft.skilltomb.utils.Chat;
import net.abyssalcraft.skilltomb.utils.Cuboid;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class SkillTombCommand implements CommandExecutor {



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You are not a player!");
            return true;
        }

        if (sender.hasPermission("skilltomb.admin")) {
            if (args.length < 1) {
                sender.sendMessage(Chat.c("&5SkillTomb Admin Option(s)"));
                sender.sendMessage(Chat.c("&d/" + label + " setarea"));
                return true;
            }

            Player player = (Player) sender;

            switch (args[0]) {
                case "setarea":
                    Selection selection = SkillTomb.getPlugin().getWorldEdit().getSelection(player);

                    if (selection != null) {

                        Location min = selection.getMinimumPoint();
                        Location max = selection.getMaximumPoint();

                        Cuboid cuboid = new Cuboid(min, max);

                        for (Block block : cuboid) {

                            if (block.getState().getType() == Material.CHEST) {

                                int rare = new Random().nextInt(Rarity.values().length);
                                Rarity rarity = Rarity.values()[rare];

                                SkillTomb.getPlugin().getChestsData().setChest(block.getLocation(), rarity.getName());
                            }
                        }

                        player.sendMessage(Chat.c("All the chest in the area has been assigned a SkillTomb skill!"));

                    } else {
                        player.sendMessage(ChatColor.RED + "Please make a selection before proceeding!");
                    }

                    break;
                case "set":



                    break;
                default:
                    sender.sendMessage(Chat.c("&5SkillTomb Admin Option(s)"));
                    sender.sendMessage(Chat.c("&d/" + label + " setarea"));
                    break;
            }
        }
        return true;
    }
}
