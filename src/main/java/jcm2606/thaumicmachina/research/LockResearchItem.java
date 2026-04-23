
package jcm2606.thaumicmachina.research;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;

public class LockResearchItem extends TMResearchItem {

    public ArrayList<LockResearchItem> boundResearchItems = new ArrayList();

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
        boolean b = false;
        for (LockResearchItem item : this.boundResearchItems) {
            if (b) {
                this.setHidden();
                item.setHidden();
            }
            if (!ThaumcraftApiHelper.isResearchComplete((String) player.getCommandSenderName(), (String) item.key))
                continue;
            b = true;
        }
        return b;
    }
}
