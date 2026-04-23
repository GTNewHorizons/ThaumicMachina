
package jcm2606.thaumicmachina.item.wand.rod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public class WandCoreCelestialUpdate
implements IWandRodOnUpdate {
    public void onUpdate(ItemStack stack, EntityPlayer player) {
        Aspect[] aspects;
        ItemWandCasting wand = (ItemWandCasting)stack.getItem();
        AspectList list = wand.getAllVis(stack);
        for (Aspect aspect : aspects = list.getAspects()) {
            if (list.getAmount(aspect) <= 1000 || player.worldObj.rand.nextInt(100) > 10) continue;
            wand.consumeVis(stack, player, aspect, 100, false);
        }
    }
}

