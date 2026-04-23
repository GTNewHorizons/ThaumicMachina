
package jcm2606.thaumicmachina.augmentation.node;

import jcm2606.thaumicmachina.core.implement.IAugmentationNode;
import thaumcraft.common.tiles.TileNode;

public class AugmentationDualCharging implements IAugmentationNode {

    @Override
    public String getAugmentationName() {
        return "Dual Charging";
    }

    @Override
    public boolean handle(TileNode node) {
        return false;
    }

    @Override
    public boolean compatible(IAugmentationNode[] augmentationList) {
        for (IAugmentationNode augmentation : augmentationList) {
            if (augmentation == null) continue;
            if (augmentation.getAugmentationName()
                .equals(this.getAugmentationName())) {
                return false;
            }
            if (!augmentation.getAugmentationName()
                .equals("Enhanced Recharging")) continue;
            return false;
        }
        return true;
    }
}
