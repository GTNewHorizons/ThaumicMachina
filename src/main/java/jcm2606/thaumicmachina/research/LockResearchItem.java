
package jcm2606.thaumicmachina.research;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;

public class LockResearchItem extends TMResearchItem {

    public final ArrayList<LockResearchItem> boundResearchItems = new ArrayList<>();

    public LockResearchItem(String key) {
        super(key);
    }

    public LockResearchItem(String key, AspectList tags, int x, int y, int complexity, ItemStack icon,
        boolean concept) {
        super(key, tags, x, y, complexity, icon, concept);
    }

    public LockResearchItem(String key, AspectList tags, int x, int y, int complexity, ResourceLocation icon,
        boolean concept) {
        super(key, tags, x, y, complexity, icon, concept);
    }

    public boolean checkLockout(EntityPlayer player) {
        for (LockResearchItem item : this.boundResearchItems) {
            if (ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), item.key)) {
                this.setHidden();
                item.setHidden();
                return true;
            }
        }
        return false;
    }
}
