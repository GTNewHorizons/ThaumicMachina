
package jcm2606.thaumicmachina.core.implement;

import thaumcraft.common.tiles.TileNode;

public interface IAugmentationNode {

    String getAugmentationName();

    boolean handle(TileNode var1);

    boolean compatible(IAugmentationNode[] var1);
}
