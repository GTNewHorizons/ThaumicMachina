
package jcm2606.thaumicmachina.research;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jcm2606.thaumicmachina.core.config.Settings;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class TMResearchItem
extends ResearchItem {
    public boolean concept;
    public String name;

    public TMResearchItem(String key) {
        this("@" + key, new AspectList(), 0, 0, 0, new ItemStack(Items.apple), false);
        this.setVirtual();
        this.name = key;
    }

    public TMResearchItem(String key, AspectList tags, int x, int y, int complexity, ItemStack icon, boolean concept) {
        super("@" + key, "THAUMIC_MACHINA_CAT", tags, x, y, complexity, icon);
        if (concept) {
            this.setRound();
            System.out.println(Settings.conceptResearchMode);
            if (Settings.conceptResearchMode == 1) {
                this.setSecondary();
            } else if (Settings.conceptResearchMode == 2) {
                this.setAutoUnlock();
            }
        }
        this.concept = concept;
        this.name = key;
    }

    public TMResearchItem(String key, AspectList tags, int x, int y, int complexity, ResourceLocation icon, boolean concept) {
        super("@" + key, "THAUMIC_MACHINA_CAT", tags, x, y, complexity, icon);
        if (concept) {
            this.setRound();
            System.out.println(Settings.conceptResearchMode);
            if (Settings.conceptResearchMode == 1) {
                this.setSecondary();
            } else if (Settings.conceptResearchMode == 2) {
                this.setAutoUnlock();
            }
        }
        this.concept = concept;
        this.name = key;
    }

    public void setPageCount() {
        ResearchPage[] pageList = new ResearchPage[1];
        for (int i = 1; i <= 255 && !StatCollector.translateToLocal((String)("tm.research.page." + this.name + "." + i)).equals("tm.research.page." + this.name + "." + i); ++i) {
            if (i > pageList.length) {
                ResearchPage[] newList = new ResearchPage[i];
                for (int j = 0; j < pageList.length; ++j) {
                    newList[j] = pageList[j];
                }
                pageList = newList;
            }
            pageList[i - 1] = new ResearchPage("tm.research.page." + this.name + "." + i);
        }
        this.setPages(pageList);
    }

    @SideOnly(value=Side.CLIENT)
    public String getName() {
        return StatCollector.translateToLocal((String)("tm.research.name." + this.name));
    }

    @SideOnly(value=Side.CLIENT)
    public String getText() {
        String s = "[TM] " + StatCollector.translateToLocal((String)("tm.research.desc." + this.name));
        if (this.concept) {
            s = "[TM] [" + StatCollector.translateToLocal((String)"tm.research.prefix.concept") + "] " + StatCollector.translateToLocal((String)("tm.research.desc." + this.name));
        }
        return s;
    }
}

