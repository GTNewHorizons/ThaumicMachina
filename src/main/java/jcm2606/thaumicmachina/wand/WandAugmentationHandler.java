
package jcm2606.thaumicmachina.wand;

import net.minecraft.item.ItemStack;

import jcm2606.thaumicmachina.wand.augmentation.AugmentationChargeBuffer;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationVisChannel;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.common.items.wands.ItemWandCasting;

public class WandAugmentationHandler {

    public static int handleMaxVisMethod(ItemStack stack) {
        if (stack == null || stack.getItem() == null || !(stack.getItem() instanceof ItemWandCasting wand)) {
            return 0;
        }
        int maxVis = wand.getRod(stack)
            .getCapacity() * (wand.isSceptre(stack) ? 150 : 100);
        if (WandHelper.hasAugmentation(stack, AugmentationChargeBuffer.INSTANCE)) {
            return (int) ((double) maxVis + (double) maxVis * 0.25);
        }
        return maxVis;
    }

    public static float handleConsumptionModifierMethod(ItemStack stack, boolean specialCost) {
        if (stack == null || stack.getItem() == null || !(stack.getItem() instanceof ItemWandCasting wand)) {
            return 1.0f;
        }
        float f = !specialCost ? wand.getCap(stack)
            .getBaseCostModifier()
            : wand.getCap(stack)
                .getSpecialCostModifier();
        if (WandHelper.hasAugmentation(stack, AugmentationVisChannel.INSTANCE)) {
            return (float) ((double) f - (double) f * 0.1);
        }
        return 1.0f;
    }

    public static boolean handleHasRunesMethod(ItemStack stack) {
        return stack != null && WandHelper.hasAugmentations(stack);
    }

    public static double handleRuneRendererDouble(ItemStack stack) {
        ItemWandCasting wand;
        double d = 0.36;
        if (stack != null && stack.getItem() != null
            && stack.getItem() instanceof ItemWandCasting
            && !((wand = (ItemWandCasting) stack.getItem()).getRod(stack) instanceof StaffRod)) {
            d = !wand.isSceptre(stack) ? d - 0.225 : d + 0.05;
        }
        return d;
    }

    public static int handleRuneRendererPasses(ItemStack stack) {
        ItemWandCasting wand;
        int i = 14;
        if (stack != null && stack.getItem() != null
            && stack.getItem() instanceof ItemWandCasting
            && !((wand = (ItemWandCasting) stack.getItem()).getRod(stack) instanceof StaffRod)) {
            i = wand.isSceptre(stack) ? 6 : 8;
        }
        return i;
    }
}
