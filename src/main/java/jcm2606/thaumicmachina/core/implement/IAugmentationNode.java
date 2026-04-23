
package jcm2606.thaumicmachina.core.implement;

import thaumcraft.common.tiles.TileNode;

public interface IAugmentationNode {
    public String getAugmentationName();

    public boolean handle(TileNode var1);

    public boolean compatible(IAugmentationNode[] var1);
}

