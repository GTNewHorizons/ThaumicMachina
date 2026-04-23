
package jcm2606.thaumicmachina.wand;

import net.minecraft.item.ItemStack;

import jcm2606.thaumicmachina.wand.augmentation.AugmentationChargeBuffer;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationVisChannel;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.common.items.wands.ItemWandCasting;

public class WandAugmentationHandler {

    public static int handleMaxVisMethod(ItemStack stack) {
        int maxVis = 0;
        if (stack != null && stack.getItem() != null && stack.getItem() instanceof ItemWandCasting) {
            ItemWandCasting wand = (ItemWandCasting) stack.getItem();
            maxVis = wand.getRod(stack)
                .getCapacity() * (wand.isSceptre(stack) ? 150 : 100);
            if (WandHelper.hasAugmentation(stack, new AugmentationChargeBuffer())) {
                int currVis = maxVis;
                maxVis = (int) ((double) maxVis + (double) maxVis * 0.25);
            }
        }
        return maxVis;
    }

    public static float handleConsumptionModifierMethod(ItemStack stack, boolean specialCost) {
        float f = 1.0f;
        if (stack != null && stack.getItem() != null && stack.getItem() instanceof ItemWandCasting) {
            ItemWandCasting wand = (ItemWandCasting) stack.getItem();
            f = !specialCost ? wand.getCap(stack)
                .getBaseCostModifier()
                : wand.getCap(stack)
                    .getSpecialCostModifier();
            if (WandHelper.hasAugmentation(stack, new AugmentationVisChannel())) {
                f = (float) ((double) f - (double) f * 0.1);
            }
        }
        return f;
    }

    public static boolean handleHasRunesMethod(ItemStack stack) {
        boolean b = false;
        if (stack != null && WandHelper.hasAugmentations(stack)) {
            b = true;
        }
        return b;
    }

    public static double handleRuneRendererDouble(ItemStack stack) {
        ItemWandCasting wand;
        double d = 0.36;
        if (stack != null && stack.getItem() != null
            && stack.getItem() instanceof ItemWandCasting
            && !((wand = (ItemWandCasting) stack.getItem()).getRod(stack) instanceof StaffRod)) {
            d = !wand.isSceptre(stack) ? (d -= 0.225) : (d += 0.05);
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
