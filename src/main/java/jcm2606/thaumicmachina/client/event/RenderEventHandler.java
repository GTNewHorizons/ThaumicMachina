
package jcm2606.thaumicmachina.client.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jcm2606.thaumicmachina.block.metaphysical.BlockMetaphysical;
import jcm2606.thaumicmachina.wand.WandHelper;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.items.wands.ItemWandCasting;

public class RenderEventHandler {

    @SubscribeEvent
    public void onBlockHightlight(DrawBlockHighlightEvent event) {
        World world = event.player.worldObj;
        Block block = world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
        if (block != null && block instanceof BlockMetaphysical) {
            int slot = 3;
            boolean b = false;
            if (event.player.getCurrentArmor(slot) == null) {
                b = true;
            } else {
                ItemStack stack = event.player.getCurrentArmor(slot);
                if (stack.getItem() == null) {
                    b = true;
                } else {
                    Item item = stack.getItem();
                    if (!(item instanceof IRevealer)) {
                        b = true;
                    }
                }
            }
            if (b) {
                // empty if block
            }
        }
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack;
        if (event.itemStack != null && (stack = event.itemStack).getItem() != null
            && stack.getItem() instanceof ItemWandCasting) {
            ItemWandCasting wandItem = (ItemWandCasting) stack.getItem();
            if (stack.getTagCompound() != null && WandHelper.hasAugmentations(stack)) {
                if (Keyboard.isKeyDown((int) 29)) {
                    String[] slist;
                    event.toolTip.add(EnumChatFormatting.GOLD + "Augmentations installed:");
                    for (String string : slist = WandHelper.getAugmentationNames(stack)) {
                        event.toolTip.add(EnumChatFormatting.DARK_GRAY + (" " + string));
                    }
                } else {
                    event.toolTip.add(EnumChatFormatting.GOLD + "Augmented");
                    event.toolTip.add(EnumChatFormatting.GOLD + "Hold CONTROL");
                }
            }
        }
    }
}
