
package jcm2606.thaumicmachina.client.event;

import static net.minecraft.util.EnumChatFormatting.*;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jcm2606.thaumicmachina.wand.WandHelper;
import thaumcraft.common.items.wands.ItemWandCasting;

public class RenderEventHandler {

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.itemStack;
        if (stack == null || !(stack.getItem() instanceof ItemWandCasting)
            || stack.getTagCompound() == null
            || !WandHelper.hasAugmentations(stack)) {
            return;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
            event.toolTip.add(GOLD + StatCollector.translateToLocal("tm.tooltip.augments_installed"));

            for (String string : WandHelper.getAugmentationNames(stack)) {
                event.toolTip.add(DARK_GRAY + " " + string);
            }
        } else {
            event.toolTip.add(GOLD + StatCollector.translateToLocal("tm.tooltip.augmented"));
            event.toolTip.add(GOLD + StatCollector.translateToLocal("tm.tooltip.hold_control"));
        }
    }
}
