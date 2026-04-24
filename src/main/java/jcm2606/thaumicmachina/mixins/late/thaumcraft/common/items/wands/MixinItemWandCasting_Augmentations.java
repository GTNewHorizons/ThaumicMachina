package jcm2606.thaumicmachina.mixins.late.thaumcraft.common.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import jcm2606.thaumicmachina.wand.WandHelper;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationChargeBuffer;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationVisChannel;
import thaumcraft.api.aspects.Aspect;
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
        if (WandHelper.hasAugmentation(stack, AugmentationChargeBuffer.INSTANCE)) {
            capacity = capacity * 5 / 4; // *= 1.25
        }
        return capacity;
    }

    /**
     * @author koolkrafter5
     * @reason Apply Vis Channel's 10% discount to vis cost.
     */
    @WrapMethod(method = "getConsumptionModifier")
    public float tm$visChannel(ItemStack stack, EntityPlayer player, Aspect aspect, boolean crafting,
        Operation<Float> original) {
        Float modifier = original.call(stack, player, aspect, crafting);
        if (WandHelper.hasAugmentation(stack, AugmentationVisChannel.INSTANCE)) {
            modifier = Math.max(0.1f, modifier * 0.9f);
        }
        return modifier;
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
