
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
        boolean b = true;
        int i = 0;
        for (IAugmentationNode augmentation : augmentationList) {
            if (augmentation == null) continue;
            if (augmentation.getAugmentationName()
                .equals(this.getAugmentationName())) {
                ++i;
            }
            if (!augmentation.getAugmentationName()
                .equals("Enhanced Recharging")) continue;
            b = false;
        }
        if (i > 1) {
            b = false;
        }
        return b;
    }
}
