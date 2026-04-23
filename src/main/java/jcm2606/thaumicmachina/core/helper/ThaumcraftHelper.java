
package jcm2606.thaumicmachina.core.helper;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.ConfigItems;

public class ThaumcraftHelper {

    public static ItemStack getShardStack(Aspect shardType, int stackSize) {
        if (!shardType.isPrimal()) {
            return null;
        }
        return switch (shardType.getName()) {
            case "aer" -> new ItemStack(ConfigItems.itemShard, stackSize, 0);
            case "ignis" -> new ItemStack(ConfigItems.itemShard, stackSize, 1);
            case "aqua" -> new ItemStack(ConfigItems.itemShard, stackSize, 2);
            case "terra" -> new ItemStack(ConfigItems.itemShard, stackSize, 3);
            case "ordo" -> new ItemStack(ConfigItems.itemShard, stackSize, 4);
            case "perditio" -> new ItemStack(ConfigItems.itemShard, stackSize, 5);
            default -> null;
        };
    }
}
