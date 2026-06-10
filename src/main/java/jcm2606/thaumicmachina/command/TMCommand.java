
package jcm2606.thaumicmachina.command;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import com.google.common.collect.Iterables;

import jcm2606.thaumicmachina.core.helper.ArrayHelper;
import jcm2606.thaumicmachina.core.helper.NBTHelper;
import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import jcm2606.thaumicmachina.wand.WandHelper;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TMCommand extends CommandBase {

    private static final List<String> ALIASES = Arrays.asList("thaumicmachina", "tm");
    private static final String[] SUBCOMMANDS = { "help", "wandaugmentation" };
    private static final String[] OPERATIONS = { "add", "remove", "reset" };

    @Override
    public String getCommandName() {
        return "thaumicmachina";
    }

    @Override
    public List<String> getCommandAliases() {
        return ALIASES;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return "tm.command.thaumicmachina.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            throw new WrongUsageException(getCommandUsage(sender));
        }
        if ("help".equals(args[0])) {
            sender.addChatMessage(new ChatComponentTranslation("tm.command.thaumicmachina.hint_alias"));
            sender.addChatMessage(new ChatComponentTranslation("tm.command.thaumicmachina.command_list"));
            sender.addChatMessage(
                new ChatComponentText("  ")
                    .appendSibling(new ChatComponentTranslation("tm.command.thaumicmachina.wandaugmentation.usage"))
                    .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
        } else if ("wandaugmentation".equals(args[0])) {
            this.handleWandAugmentationCommands(sender, args);
        } else {
            throw new WrongUsageException(getCommandUsage(sender));
        }
    }

    public void handleWandAugmentationCommands(ICommandSender commandSender, String[] args) {
        if (args.length < 2 || !ArrayHelper.contains(OPERATIONS, args[1])) {
            throw new WrongUsageException("tm.command.thaumicmachina.wandaugmentation.usage");
        }

        EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer(commandSender);
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack == null || !(stack.getItem() instanceof ItemWandCasting)) {
            throw new CommandException("tm.command.thaumicmachina.error.no_wand_held");
        }

        if ("reset".equals(args[1])) {
            if (args.length != 2) throw new WrongUsageException("tm.command.thaumicmachina.wandaugmentation.usage");

            NBTHelper.getCompoundFor(stack)
                .removeTag("Augmentations");
        } else {
            if (args.length != 3) throw new WrongUsageException("tm.command.thaumicmachina.wandaugmentation.usage");

            final String augment = args[2].replace('_', ' ');
            if (!WandHelper.augmentationMap.containsKey(augment)) {
                throw new CommandException("tm.command.thaumicmachina.error.unknown_augment");
            }

            final String[] wandAugments = WandHelper.getAugmentationNames(stack);

            if ("add".equals(args[1])) {
                if (ArrayHelper.contains(wandAugments, augment)) {
                    throw new CommandException("tm.command.thaumicmachina.error.is_present");
                }

                IAugmentationWand[] augmentationList = { WandHelper.augmentationMap.get(augment) };
                WandHelper.addAugmentationsTo(stack, augmentationList, false);
            } else if ("remove".equals(args[1])) {
                if (!ArrayHelper.contains(wandAugments, augment)) {
                    throw new CommandException("tm.command.thaumicmachina.error.is_absent");
                }

                IAugmentationWand[] augmentationList = { WandHelper.augmentationMap.get(augment) };
                WandHelper.removeAugmentationsFrom(stack, augmentationList, false);
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, SUBCOMMANDS);
        } else if (args.length >= 2 && "wandaugmentation".equals(args[0])) {
            if (args.length == 2) {
                return getListOfStringsMatchingLastWord(args, OPERATIONS);
            } else if (args.length == 3 && ("add".equals(args[1]) || "remove".equals(args[1]))) {
                return getListOfStringsFromIterableMatchingLastWord(
                    args,
                    Iterables.transform(WandHelper.augmentationMap.keySet(), name -> name.replace(' ', '_')));
            }
        }
        return null;
    }
}
