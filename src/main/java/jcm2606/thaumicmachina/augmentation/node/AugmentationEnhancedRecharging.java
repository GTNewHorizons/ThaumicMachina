
package jcm2606.thaumicmachina.augmentation.node;

import java.lang.reflect.Field;

import jcm2606.thaumicmachina.ThaumicMachina;
import jcm2606.thaumicmachina.core.implement.IAugmentationNode;
import thaumcraft.common.tiles.TileNode;

public class AugmentationEnhancedRecharging implements IAugmentationNode {

    @Override
    public String getAugmentationName() {
        return "Enhanced Recharging";
    }

    @Override
    public boolean handle(TileNode node) {
        return this.decreaseRechargeTime(node);
    }

    @Override
    public boolean compatible(IAugmentationNode[] augmentationList) {
        int count = 0;
        for (IAugmentationNode augmentation : augmentationList) {
            if (augmentation == null) continue;
            if (augmentation.getAugmentationName()
                .equals(this.getAugmentationName())) {
                count++;
            }
            if (!augmentation.getAugmentationName()
                .equals("Dual Charging")) continue;
            return false;
        }
        return count <= 2;
    }

    public boolean decreaseRechargeTime(TileNode node) {
        try {
            Field field = node.getClass()
                .getDeclaredField("regeneration");
            field.setAccessible(true);
            int f = 400;
            if (node.getNodeModifier() != null) {
                switch (node.getNodeModifier()) {
                    case BRIGHT -> f = 300;
                    case PALE -> f = 600;
                    case FADING -> f = 2400;
                }
            }
            field.set(node, f);
            return true;
        } catch (SecurityException | IllegalAccessException | NoSuchFieldException | IllegalArgumentException e) {
            ThaumicMachina.log.error(e);
        }
        return false;
    }
}
