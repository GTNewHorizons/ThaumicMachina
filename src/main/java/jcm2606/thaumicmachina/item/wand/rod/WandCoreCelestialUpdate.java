
package jcm2606.thaumicmachina.item.wand.rod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public class WandCoreCelestialUpdate implements IWandRodOnUpdate {

    @Override
    public void onUpdate(ItemStack stack, EntityPlayer player) {
        ItemWandCasting wand = (ItemWandCasting) stack.getItem();
        assert wand != null;
        AspectList list = wand.getAllVis(stack);
        for (Aspect aspect : list.getAspects()) {
            if (list.getAmount(aspect) <= 1000 || player.worldObj.rand.nextInt(100) > 10) continue;
            wand.consumeVis(stack, player, aspect, 100, false);
        }
    }
}
