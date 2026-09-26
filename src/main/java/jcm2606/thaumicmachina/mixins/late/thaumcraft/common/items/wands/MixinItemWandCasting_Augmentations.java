package jcm2606.thaumicmachina.mixins.late.thaumcraft.common.items.wands;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;

import jcm2606.thaumicmachina.wand.WandHelper;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationChargeBuffer;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationVisChannel;
import thaumcraft.common.items.wands.ItemWandCasting;

@Mixin(value = ItemWandCasting.class, remap = false)
public class MixinItemWandCasting_Augmentations {

    /**
     * @author koolkrafter5
     * @reason Apply Charge Buffer's 25% increase to vis capacity.
     */
    @WrapMethod(method = "getMaxVis")
    public int tm$chargeBuffer(ItemStack stack, Operation<Integer> original) {
        Integer capacity = original.call(stack);
        // * 5 / 4 == *= 1.25 with int math
        return WandHelper.hasAugmentation(stack, AugmentationChargeBuffer.INSTANCE) ? capacity * 5 / 4 : capacity;
    }

    /**
     * @author koolkrafter5
     * @reason Apply Vis Channel's 10% discount to vis cost.
     */
    @ModifyExpressionValue(
        method = "getConsumptionModifier",
        at = @At(value = "INVOKE", target = "Lthaumcraft/api/wands/WandCap;getBaseCostModifier()F"))
    private float tm$baseCostModifier(float modifier, @Local(name = "is", argsOnly = true) ItemStack is) {
        return tm$visChannel(is, modifier);
    }

    /**
     * @author koolkrafter5
     * @reason Apply Vis Channel's 10% discount to vis cost.
     */
    @ModifyExpressionValue(
        method = "getConsumptionModifier",
        at = @At(value = "INVOKE", target = "Lthaumcraft/api/wands/WandCap;getSpecialCostModifier()F"))
    private float tm$specialCostModifier(float modifier, @Local(name = "is", argsOnly = true) ItemStack is) {
        return tm$visChannel(is, modifier);
    }

    @Unique
    private float tm$visChannel(ItemStack stack, float modifier) {
        return WandHelper.hasAugmentation(stack, AugmentationVisChannel.INSTANCE) ? modifier * 0.9f : modifier;
    }

    /**
     * @author koolkrafter5
     * @reason All augmentations apply runes to wands.
     */
    @WrapMethod(method = "hasRunes")
    public boolean hasRunes(ItemStack stack, Operation<Boolean> original) {
        return original.call(stack) || WandHelper.hasAugmentations(stack);
    }
}
