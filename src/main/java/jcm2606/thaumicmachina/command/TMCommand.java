
package jcm2606.thaumicmachina.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import jcm2606.thaumicmachina.core.helper.ArrayHelper;
import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import jcm2606.thaumicmachina.wand.WandHelper;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TMCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "thaumicmachina";
    }

    @Override
    public List<String> getCommandAliases() {
        ArrayList<String> aliasList = new ArrayList<>();
        aliasList.add("thaumicmachina");
        aliasList.add("tm");
        return aliasList;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return "/thaumicmachina <action> [<player> <arguments>]";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args) {
        if (args.length == 0) {
            commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid usage of command."));
            return;
        }
        if (args[0].equalsIgnoreCase("help")) {
            commandSender.addChatMessage(
                new ChatComponentText(EnumChatFormatting.GRAY + "You can also use /tm in place of /thaumimachina."));
            commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Commands:"));
            commandSender.addChatMessage(
                new ChatComponentText(
                    EnumChatFormatting.DARK_GREEN
                        + "  /thaumicmachina wandaugmentation <add|remove|reset> <augmentation name>"));
        } else if (args[0].equalsIgnoreCase("wandaugmentation")) {
            this.handleWandAugmentationCommands(commandSender, args);
        }
    }

    public void handleWandAugmentationCommands(ICommandSender commandSender, String[] args) {
        if (args.length != 3) {
            commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid usage of command."));
            return;
        }
        if (args[1].equalsIgnoreCase("add")) {
            EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer(commandSender);
            if (player.getCurrentEquippedItem() == null) {
                commandSender.addChatMessage(
                    new ChatComponentText(EnumChatFormatting.RED + "Player must be holding their wand in their hand."));
                return;
            }
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack.getItem() == null) return;
            if (!(stack.getItem() instanceof ItemWandCasting)) {
                commandSender.addChatMessage(
                    new ChatComponentText(EnumChatFormatting.RED + "Player must be holding their wand in their hand."));
                return;
            }
            if (!WandHelper.augmentationMap.containsKey(args[2].replace("_", " "))) {
                commandSender.addChatMessage(
                    new ChatComponentText(EnumChatFormatting.RED + "That augmentation is not registered"));
                return;
            }
            if (!ArrayHelper.contains(WandHelper.getAugmentationNames(stack), args[2].replace("_", " "))) {
                IAugmentationWand[] augmentationList = new IAugmentationWand[] {
                    WandHelper.augmentationMap.get(args[2].replace("_", " ")) };
                WandHelper.addAugmentationsTo(stack, augmentationList, false);
                return;
            }
            commandSender.addChatMessage(
                new ChatComponentText(
                    EnumChatFormatting.RED + "The held wand already has that augmentation installed."));
            return;
        }
        if (args[1].equalsIgnoreCase("remove")) {
            EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer(commandSender);
            if (player.getCurrentEquippedItem() == null) {
                commandSender.addChatMessage(
                    new ChatComponentText(EnumChatFormatting.RED + "Player must be holding their wand in their hand."));
                return;
            }
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack.getItem() == null) return;
            if (!(stack.getItem() instanceof ItemWandCasting)) {
                commandSender.addChatMessage(
                    new ChatComponentText(EnumChatFormatting.RED + "Player must be holding their wand in their hand."));
                return;
            }
            if (!WandHelper.augmentationMap.containsKey(args[2].replace("_", " "))) {
                commandSender.addChatMessage(
                    new ChatComponentText(EnumChatFormatting.RED + "That augmentation is not registered"));
                return;
            }
            if (ArrayHelper.contains(WandHelper.getAugmentationNames(stack), args[2].replace("_", " "))) {
                IAugmentationWand[] augmentationList = new IAugmentationWand[] {
                    WandHelper.augmentationMap.get(args[2].replace("_", " ")) };
                WandHelper.removeAugmentationsFrom(stack, augmentationList, false);
                return;
            }
            commandSender.addChatMessage(
                new ChatComponentText(
                    EnumChatFormatting.RED + "The held wand does not have those augmentations installed."));
            return;
        }
        if (args[1].equalsIgnoreCase("reset")) {
            return;
        }
        commandSender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid usage of command."));
    }
}
