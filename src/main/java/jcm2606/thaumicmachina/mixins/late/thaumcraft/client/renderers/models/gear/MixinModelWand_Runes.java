package jcm2606.thaumicmachina.mixins.late.thaumcraft.client.renderers.models.gear;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import thaumcraft.api.wands.StaffRod;
import thaumcraft.client.renderers.models.gear.ModelWand;
import thaumcraft.common.items.wands.ItemWandCasting;

@Mixin(value = ModelWand.class, remap = false)
public class MixinModelWand_Runes {

    /**
     * @author koolkrafter5
     * @reason Fix runes slightly floating off of the surface of wands and scepters
     */
    @ModifyConstant(method = "render", constant = @Constant(doubleValue = -0.08D))
    private double changeRuneHeight(double original, ItemStack stack) {
        if (stack != null && stack.getItem() instanceof ItemWandCasting wand
            && !(wand.getRod(stack) instanceof StaffRod)) {
            return -0.063D;
        }
        return original;
    }

    /**
     * @author koolkrafter5
     * @reason Correctly position runes on the rod of wands and scepters
     */
    @ModifyConstant(method = "render", constant = @Constant(doubleValue = 0.36D))
    private double changeRuneStart(double original, ItemStack stack) {
        if (stack != null && stack.getItem() instanceof ItemWandCasting wand
            && !(wand.getRod(stack) instanceof StaffRod)) {
            return wand.isSceptre(stack) ? 0.41 : 0.135;
        }
        return original;
    }

    /**
     * @author koolkrafter5
     * @reason Draw the correct number of runes on wands and scepters
     */
    @ModifyConstant(method = "render", constant = @Constant(intValue = 14))
    private int setNumberOfRunes(int original, ItemStack stack) {
        if (stack != null && stack.getItem() instanceof ItemWandCasting wand
            && !(wand.getRod(stack) instanceof StaffRod)) {
            return wand.isSceptre(stack) ? 6 : 8;
        }
        return original;
    }
}
