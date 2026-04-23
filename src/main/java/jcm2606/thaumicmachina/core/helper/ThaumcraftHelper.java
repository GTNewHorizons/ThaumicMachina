
package jcm2606.thaumicmachina.core.helper;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;

public class ThaumcraftHelper {
    public static ItemStack getShardStack(Aspect shardType, int stackSize) {
        ItemStack stack = null;
        if (shardType.isPrimal()) {
            switch (shardType.getName()) {
                case "aer": {
                    stack = ItemApi.getItem((String)"itemShard", (int)0);
                }
                case "ignis": {
                    stack = ItemApi.getItem((String)"itemShard", (int)1);
                }
                case "aqua": {
                    stack = ItemApi.getItem((String)"itemShard", (int)2);
                }
                case "terra": {
                    stack = ItemApi.getItem((String)"itemShard", (int)3);
                }
                case "ordo": {
                    stack = ItemApi.getItem((String)"itemShard", (int)4);
                }
                case "perditio": {
                    stack = ItemApi.getItem((String)"itemShard", (int)5);
                }
            }
        }
        return stack;
    }
}

