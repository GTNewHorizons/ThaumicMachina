
package jcm2606.thaumicmachina.wand.augmentation;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import thaumcraft.api.wands.IWandTriggerManager;

public class FluxWandTrigger implements IWandTriggerManager {

    public boolean performTrigger(World world, ItemStack stack, EntityPlayer player, int x, int y, int z, int arg6,
        int arg7) {
        System.out.println();
        return true;
    }
}
