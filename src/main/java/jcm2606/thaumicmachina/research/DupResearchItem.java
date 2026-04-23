
package jcm2606.thaumicmachina.research;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;

public class DupResearchItem extends ResearchItem {

    public final ResearchItem parentItem;

    public DupResearchItem(String key, String category, String parent, String parentCategory, int x, int y) {
        super("@" + key, category, new AspectList(), x, y, 1, ResearchHelper.getResearchIcon(parent, parentCategory));
        this.parentItem = ((ResearchCategoryList) ResearchCategories.researchCategories.get(parentCategory)).research
            .get(parent);
        this.setDuplicate();
        this.setPages(this.parentItem.getPages());
        this.setStub();
        this.setHidden();
    }

    public DupResearchItem(String key, String category, String parent, String parentCategory, int x, int y,
        boolean useItemAsIcon) {
        super("@" + key, category, new AspectList(), x, y, 1, ResearchHelper.getResearchItem(parent, parentCategory));
        this.parentItem = ((ResearchCategoryList) ResearchCategories.researchCategories.get(parentCategory)).research
            .get(parent);
        this.setDuplicate();
        this.setPages(this.parentItem.getPages());
        this.setStub();
        this.setHidden();
    }

    public void setDuplicate() {
        if (this.parentItem.siblings == null) {
            this.parentItem.setSiblings(this.key);
            return;
        }
        String[] siblings_ = new String[this.parentItem.siblings.length + 1];
        System.arraycopy(this.parentItem.siblings, 0, siblings_, 0, this.parentItem.siblings.length);
        siblings_[this.parentItem.siblings.length] = this.key;
        this.parentItem.setSiblings(siblings_);
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public String getName() {
        return this.parentItem.getName();
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public String getText() {
        return this.parentItem.getName();
    }
}
