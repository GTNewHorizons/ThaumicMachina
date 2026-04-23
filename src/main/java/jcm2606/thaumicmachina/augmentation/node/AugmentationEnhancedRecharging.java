
package jcm2606.thaumicmachina.augmentation.node;

import java.lang.reflect.Field;
import jcm2606.thaumicmachina.core.implement.IAugmentationNode;
import thaumcraft.common.tiles.TileNode;

public class AugmentationEnhancedRecharging
implements IAugmentationNode {
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
        boolean b = true;
        int i = 0;
        for (IAugmentationNode augmentation : augmentationList) {
            if (augmentation == null) continue;
            if (augmentation.getAugmentationName().equals(this.getAugmentationName())) {
                ++i;
            }
            if (!augmentation.getAugmentationName().equals("Dual Charging")) continue;
            b = false;
        }
        if (i > 2) {
            b = false;
        }
        return b;
    }

    public boolean decreaseRechargeTime(TileNode node) {
        boolean b = false;
        try {
            Field field = node.getClass().getDeclaredField("regeneration");
            field.setAccessible(true);
            int f = 400;
            if (node.getNodeModifier() != null) {
                switch (node.getNodeModifier()) {
                    case BRIGHT: {
                        f = 300;
                        break;
                    }
                    case PALE: {
                        f = 600;
                        break;
                    }
                    case FADING: {
                        f = 2400;
                    }
                }
            }
            field.set(node, f);
            b = true;
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return b;
    }
}

