
package jcm2606.thaumicmachina.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import jcm2606.thaumicmachina.core.helper.ArrayHelper;
import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import jcm2606.thaumicmachina.wand.WandHelper;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TMCommand extends CommandBase {

    public String getCommandName() {
        return "thaumicmachina";
    }

    public List getCommandAliases() {
        ArrayList<String> aliasList = new ArrayList<String>();
        aliasList.add("thaumicmachina");
        aliasList.add("tm");
        return aliasList;
    }

    public String getCommandUsage(ICommandSender commandSender) {
        return "/thaumicmachina <action> [<player> <arguments>]";
    }

    public void processCommand(ICommandSender commandSender, String[] args) {
        if (args.length == 0) {
            commandSender.addChatMessage(
                (IChatComponent) new ChatComponentText(
                    EnumChatFormatting.RED.getFormattingCode() + "Invalid usage of command."));
            return;
        }
        if (args[0].equalsIgnoreCase("help")) {
            commandSender.addChatMessage(
                (IChatComponent) new ChatComponentText(
                    "\u00a7" + EnumChatFormatting.GRAY + "You can also use /tm in place of /thaumimachina."));
            commandSender.addChatMessage(
                (IChatComponent) new ChatComponentText("\u00a7" + EnumChatFormatting.GRAY + "Commands:"));
            commandSender.addChatMessage(
                (IChatComponent) new ChatComponentText(
                    "\u00a7" + EnumChatFormatting.DARK_GREEN
                        + "  /thaumicmachina wandaugmentation <add|remove|reset> <augmentation name>"));
        } else if (args[0].equalsIgnoreCase("wandaugmentation")) {
            this.handleWandAugmentationCommands(commandSender, args);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void handleWandAugmentationCommands(ICommandSender commandSender, String[] args) {
        if (args.length != 3) {
            commandSender.addChatMessage(
                (IChatComponent) new ChatComponentText(
                    "\u00a7" + EnumChatFormatting.RED.getFormattingCode() + "Invalid usage of command."));
            return;
        }
        if (args[1].equalsIgnoreCase("add")) {
            EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer((ICommandSender) commandSender);
            if (player == null) return;
            if (player.getCurrentEquippedItem() == null) {
                commandSender.addChatMessage(
                    (IChatComponent) new ChatComponentText(
                        EnumChatFormatting.RED.getFormattingCode()
                            + "Player must be holding their wand in their hand."));
                return;
            }
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack.getItem() == null) return;
            if (!(stack.getItem() instanceof ItemWandCasting)) {
                commandSender.addChatMessage(
                    (IChatComponent) new ChatComponentText(
                        "\u00a7" + EnumChatFormatting.RED.getFormattingCode()
                            + "Player must be holding their wand in their hand."));
                return;
            }
            ItemWandCasting wand = (ItemWandCasting) stack.getItem();
            if (!WandHelper.augmentationMap.keySet()
                .contains(args[2].replace("_", " "))) {
                commandSender.addChatMessage(
                    (IChatComponent) new ChatComponentText(
                        "\u00a7" + EnumChatFormatting.RED.getFormattingCode() + "That augmentation is not registered"));
                return;
            }
            if (!ArrayHelper.contains(WandHelper.getAugmentationNames(stack), args[2].replace("_", " "))) {
                IAugmentationWand[] augmentationList = new IAugmentationWand[] {
                    WandHelper.augmentationMap.get(args[2].replace("_", " ")) };
                WandHelper.addAugmentationsTo(stack, augmentationList, false);
                return;
            }
            commandSender.addChatMessage(
                (IChatComponent) new ChatComponentText(
                    "\u00a7" + EnumChatFormatting.RED.getFormattingCode()
                        + "The held wand already has that augmentation installed."));
            return;
        }
        if (args[1].equalsIgnoreCase("remove")) {
            EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer((ICommandSender) commandSender);
            if (player == null) return;
            if (player.getCurrentEquippedItem() == null) {
                commandSender.addChatMessage(
                    (IChatComponent) new ChatComponentText(
                        EnumChatFormatting.RED.getFormattingCode()
                            + "Player must be holding their wand in their hand."));
                return;
            }
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack.getItem() == null) return;
            if (!(stack.getItem() instanceof ItemWandCasting)) {
                commandSender.addChatMessage(
                    (IChatComponent) new ChatComponentText(
                        "\u00a7" + EnumChatFormatting.RED.getFormattingCode()
                            + "Player must be holding their wand in their hand."));
                return;
            }
            ItemWandCasting wand = (ItemWandCasting) stack.getItem();
            if (!WandHelper.augmentationMap.keySet()
                .contains(args[2].replace("_", " "))) {
                commandSender.addChatMessage(
                    (IChatComponent) new ChatComponentText(
                        "\u00a7" + EnumChatFormatting.RED.getFormattingCode() + "That augmentation is not registered"));
                return;
            }
            if (ArrayHelper.contains(WandHelper.getAugmentationNames(stack), args[2].replace("_", " "))) {
                IAugmentationWand[] augmentationList = new IAugmentationWand[] {
                    WandHelper.augmentationMap.get(args[2].replace("_", " ")) };
                WandHelper.removeAugmentationsFrom(stack, augmentationList, false);
                return;
            }
            commandSender.addChatMessage(
                (IChatComponent) new ChatComponentText(
                    "\u00a7" + EnumChatFormatting.RED.getFormattingCode()
                        + "The held wand does not have those augmentations installed."));
            return;
        }
        if (args[1].equalsIgnoreCase("reset")) {
            return;
        }
        commandSender.addChatMessage(
            (IChatComponent) new ChatComponentText(
                "\u00a7" + EnumChatFormatting.RED.getFormattingCode() + "Invalid usage of command."));
    }
}
