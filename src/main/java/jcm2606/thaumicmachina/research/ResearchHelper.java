
package jcm2606.thaumicmachina.research;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import jcm2606.thaumicmachina.ThaumicMachina;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigResearch;

public class ResearchHelper {

    public static final String CATEGORY_KEY = "THAUMIC_MACHINA_CAT";
    public static final String RESEARCH_ID_NAME = "tm.research.name.";
    public static final String RESEARCH_ID_DESC = "tm.research.desc.";
    public static final String RESEARCH_ID_PAGE = "tm.research.page.";

    public static void loadResearch() {
        ThaumicMachina.log.info("Loading research...");
        ResearchCategories.registerCategory(
            (String) CATEGORY_KEY,
            (ResourceLocation) new ResourceLocation("ThaumicMachina:textures/research/r_tab.png"),
            (ResourceLocation) new ResourceLocation("thaumcraft:textures/gui/gui_researchback.png"));
        ResearchItem item = new TMResearchItem(
            "INFORMATION_CONCEPTS",
            new AspectList(),
            0,
            0,
            1,
            new ItemStack(Items.book),
            false);
        ResearchHelper.setPageCount(item);
        item.setAutoUnlock();
        item.registerResearchItem();
        item = new DupResearchItem("TMNODES", CATEGORY_KEY, "NODES", "BASICS", 0, 4);
        item.registerResearchItem();
        item = new DupResearchItem("TMTHAUMATURGY", CATEGORY_KEY, "BASICTHAUMATURGY", "THAUMATURGY", -8, 0, true);
        item.registerResearchItem();
        ResearchHelper.loadNodalResearch(item);
        ResearchHelper.loadVisResearch(item);
        ResearchHelper.loadEssentiaResearch(item);
        ResearchHelper.loadWandResearch(item);
        ResearchHelper.loadEntityResearch(item);
        ResearchHelper.loadCrimsonResearch(item);
        ThaumicMachina.log.info("Research loaded");
    }

    private static void loadNodalResearch(ResearchItem item) {
        item = new TMResearchItem(
            "NODAL_STUDIES",
            new AspectList().add(Aspect.ENERGY, 1)
                .add(Aspect.AURA, 2)
                .add(Aspect.MIND, 1),
            -2,
            5,
            1,
            new ResourceLocation("ThaumicMachina:textures/research/r_nodal_studies.png"),
            true);
        ResearchHelper.setPageCount(item);
        item.setParents(new String[] { "@TMNODES" });
        item.registerResearchItem();
        item = new TMResearchItem(
            "AURA_FIELD",
            new AspectList().add(Aspect.AURA, 2)
                .add(Aspect.ENERGY, 1)
                .add(Aspect.VOID, 1)
                .add(Aspect.ORDER, 1),
            -4,
            6,
            2,
            new ResourceLocation("ThaumicMachina:textures/research/r_aura_field.png"),
            true);
        ResearchHelper.setPageCount(item);
        item.setParents(new String[] { "@NODAL_STUDIES", "@VIS_STUDIES" });
        item.setConcealed();
        item.registerResearchItem();
    }

    private static void loadVisResearch(ResearchItem item) {
        item = new TMResearchItem(
            "VIS",
            new AspectList(),
            -1,
            1,
            1,
            new ResourceLocation("thaumcraft:textures/misc/r_nodetap1.png"),
            true);
        ResearchHelper.setPageCount(item);
        item.setParents(new String[] { "@TMNODES" });
        item.setAutoUnlock();
        item.registerResearchItem();
        item = new TMResearchItem(
            "VIS_STUDIES",
            new AspectList().add(Aspect.ENERGY, 1)
                .add(Aspect.MIND, 2),
            -3,
            2,
            1,
            new ResourceLocation("ThaumicMachina:textures/research/r_vis_studies.png"),
            true);
        ResearchHelper.setPageCount(item);
        item.setConcealed();
        item.setParents(new String[] { "@VIS" });
        item.registerResearchItem();
        item = new TMResearchItem(
            "VIS_CHARGE",
            new AspectList().add(Aspect.ENERGY, 2)
                .add(Aspect.ENTROPY, 1),
            -5,
            1,
            1,
            new ResourceLocation("ThaumicMachina:textures/research/r_vis_charge.png"),
            true);
        ResearchHelper.setPageCount(item);
        item.setConcealed();
        item.setParents(new String[] { "@VIS_STUDIES" });
        item.registerResearchItem();
    }

    private static void loadEssentiaResearch(ResearchItem item) {}

    private static void loadWandResearch(ResearchItem item) {
        item = new TMResearchItem(
            "WAND_STUDIES",
            new AspectList().add(Aspect.MIND, 2)
                .add(Aspect.MAGIC, 1)
                .add(Aspect.TOOL, 1),
            -7,
            -2,
            1,
            new ResourceLocation("ThaumicMachina:textures/research/r_wand_studies.png"),
            true);
        ResearchHelper.setPageCount(item);
        item.setParentsHidden(new String[] { "@VIS_CHARGE" });
        item.setParents(new String[] { "@TMTHAUMATURGY" });
        item.registerResearchItem();
        item = new TMResearchItem(
            "WAND_AUGMENTATION",
            new AspectList().add(Aspect.MAGIC, 2)
                .add(Aspect.TOOL, 1)
                .add(Aspect.MECHANISM, 1)
                .add(Aspect.ORDER, 1),
            -9,
            -5,
            3,
            new ResourceLocation("ThaumicMachina:textures/research/r_wand_augmentation.png"),
            false);
        item.setPages(
            new ResearchPage[] { new ResearchPage("tm.research.page.WAND_AUGMENTATION.1"),
                new ResearchPage("tm.research.page.WAND_AUGMENTATION.2"),
                new ResearchPage("tm.research.page.WAND_AUGMENTATION.3"),
                new ResearchPage((IArcaneRecipe) ConfigResearch.recipes.get("TM_WAND_AUGMENTATION_CORE")) });
        item.setParents(new String[] { "@WAND_STUDIES" });
        item.setParentsHidden(new String[] { "INFUSION" });
        item.setSpecial();
        item.registerResearchItem();
        item = new TMResearchItem(
            "WAND_AUGMENTATION_CHARGE_BUFFER",
            new AspectList().add(Aspect.TOOL, 1)
                .add(Aspect.MAGIC, 1)
                .add(Aspect.ENERGY, 1)
                .add(Aspect.VOID, 2),
            -10,
            -7,
            3,
            new ResourceLocation("ThaumicMachina:textures/research/r_wand_augmentation_charge_buffer.png"),
            false);
        item.setPages(
            new ResearchPage[] { new ResearchPage("tm.research.page.WAND_AUGMENTATION_CHARGE_BUFFER.1"),
                new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_CHARGE_BUFFER")) });
        item.setConcealed();
        item.setParents(new String[] { "@WAND_AUGMENTATION" });
        item.registerResearchItem();
        item = new TMResearchItem(
            "WAND_STABILITY",
            null,
            -10,
            -3,
            0,
            new ResourceLocation("ThaumicMachina:textures/research/r_wand_stability.png"),
            false);
        ResearchHelper.setPageCount(item);
        item.setParents(new String[] { "@WAND_AUGMENTATION" });
        item.setStub();
        item.setRound();
        item.registerResearchItem();
        item = new TMResearchItem(
            "WAND_AUGMENTATION_VIS_CHANNEL",
            new AspectList().add(Aspect.TOOL, 1)
                .add(Aspect.MAGIC, 1)
                .add(Aspect.WATER, 2),
            -8,
            -7,
            3,
            new ResourceLocation("ThaumicMachina:textures/research/r_wand_augmentation_vis_channel.png"),
            false);
        item.setPages(
            new ResearchPage[] { new ResearchPage("tm.research.page.WAND_AUGMENTATION_VIS_CHANNEL.1"),
                new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_VIS_CHANNEL")) });
        item.setConcealed();
        item.setParents(new String[] { "@WAND_AUGMENTATION" });
        item.registerResearchItem();
        item = new TMResearchItem(
            "WAND_AUGMENTATION_TAINTED_CORE",
            new AspectList().add(Aspect.TOOL, 1)
                .add(Aspect.MAGIC, 1)
                .add(Aspect.ENTROPY, 1)
                .add(Aspect.TAINT, 2)
                .add(Aspect.DARKNESS, 1),
            -11,
            -5,
            2,
            new ResourceLocation("ThaumicMachina:textures/research/r_wand_augmentation_tainted_core.png"),
            false);
        item.setPages(
            new ResearchPage[] { new ResearchPage("tm.research.page.WAND_AUGMENTATION_TAINTED_CORE.1"),
                new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_TAINTED_CORE")) });
        item.setConcealed();
        item.setParents(new String[] { "@WAND_AUGMENTATION" });
        item.registerResearchItem();
        ThaumcraftApi.addWarpToResearch((String) "@WAND_AUGMENTATION_TAINTED_CORE", (int) 2);
        item = new TMResearchItem(
            "WAND_AUGMENTATION_TAINT_CAPPING",
            new AspectList().add(Aspect.TOOL, 1)
                .add(Aspect.MAGIC, 1)
                .add(Aspect.TAINT, 1)
                .add(Aspect.AURA, 2)
                .add(Aspect.ENERGY, 1),
            -13,
            -6,
            3,
            new ResourceLocation("ThaumicMachina:textures/research/r_wand_augmentation_taint_capping.png"),
            false);
        item.setPages(
            new ResearchPage[] { new ResearchPage("tm.research.page.WAND_AUGMENTATION_TAINT_CAPPING.1"),
                new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_TAINT_CAPPING")),
                new ResearchPage("tm.research.page.WAND_AUGMENTATION_TAINT_CAPPING.3") });
        item.setConcealed();
        item.setParents(new String[] { "@WAND_AUGMENTATION_TAINTED_CORE" });
        item.registerResearchItem();
        item = new TMResearchItem(
            "WAND_AUGMENTATION_CONTACT_DISCHARGE",
            new AspectList().add(Aspect.DARKNESS, 1)
                .add(Aspect.ENERGY, 1)
                .add(Aspect.WEAPON, 2)
                .add(Aspect.LIFE, 1),
            -7,
            -6,
            2,
            new ResourceLocation("ThaumicMachina:textures/research/r_wand_augmentation_contact_discharge.png"),
            false);
        item.setPages(
            new ResearchPage[] { new ResearchPage("tm.research.page.WAND_AUGMENTATION_CONTACT_DISCHARGE.1"),
                new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_CONTACT_DISCHARGE")),
                new ResearchPage("tm.research.page.WAND_AUGMENTATION_CONTACT_DISCHARGE.3") });
        item.setConcealed();
        item.setParents(new String[] { "@WAND_AUGMENTATION" });
        item.setParentsHidden(new String[] { "@CRIMSON_WAND_AUGMENTATION" });
        item.registerResearchItem();
        ThaumcraftApi.addWarpToResearch((String) "@WAND_AUGMENTATION_CONTACT_DISCHARGE", (int) 1);
    }

    private static void loadEntityResearch(ResearchItem item) {}

    private static void loadCrimsonResearch(ResearchItem item) {
        item = new TMResearchItem(
            "CRIMSON_PHILOSOPHY",
            new AspectList(),
            4,
            5,
            0,
            ItemApi.getItem((String) "itemEldritchObject", (int) 1),
            true);
        ResearchHelper.setPageCount(item);
        item.setSpecial();
        item.setStub();
        item.setHidden();
        item.registerResearchItem();
        item = new TMResearchItem(
            "CRIMSON_ASTRONOMY",
            new AspectList().add(Aspect.AURA, 1)
                .add(Aspect.ELDRITCH, 1)
                .add(Aspect.VOID, 2),
            3,
            7,
            2,
            new ResourceLocation("ThaumicMachina:textures/research/r_crimson_astronomy.png"),
            true);
        ResearchHelper.setPageCount(item);
        item.setParents(new String[] { "@CRIMSON_PHILOSOPHY" });
        item.setConcealed();
        item.registerResearchItem();
        item = new TMResearchItem(
            "CRIMSON_REALISATION",
            new AspectList().add(Aspect.ELDRITCH, 1)
                .add(Aspect.MIND, 2)
                .add(Aspect.AURA, 1),
            1,
            8,
            2,
            new ResourceLocation("ThaumicMachina:textures/research/r_crimson_realisation.png"),
            true);
        ResearchHelper.setPageCount(item);
        item.setParents(new String[] { "@CRIMSON_ASTRONOMY" });
        item.setParentsHidden(new String[] { "@AURA_FIELD" });
        item.setConcealed();
        item.registerResearchItem();
        item = new TMResearchItem(
            "CRIMSON_CELESTIAL_FIELD",
            new AspectList().add(Aspect.AURA, 1)
                .add(Aspect.ORDER, 1)
                .add(Aspect.VOID, 2),
            0,
            6,
            3,
            new ResourceLocation("ThaumicMachina:textures/research/r_crimson_celestial_field.png"),
            true);
        item.setParents(new String[] { "@CRIMSON_REALISATION" });
        ResearchHelper.setPageCount(item);
        item.registerResearchItem();
        item = new TMResearchItem(
            "CRIMSON_THAUMATURGY",
            new AspectList().add(Aspect.ELDRITCH, 1)
                .add(Aspect.MAGIC, 1)
                .add(Aspect.TOOL, 2),
            5,
            3,
            2,
            new ResourceLocation("ThaumicMachina:textures/research/r_crimson_thaumaturgy.png"),
            true);
        item.setParents(new String[] { "@CRIMSON_PHILOSOPHY" });
        item.setConcealed();
        ResearchHelper.setPageCount(item);
        item.registerResearchItem();
        item = new TMResearchItem(
            "CRIMSON_WAND_AUGMENTATION",
            new AspectList().add(Aspect.ELDRITCH, 1)
                .add(Aspect.MAGIC, 2)
                .add(Aspect.TOOL, 1)
                .add(Aspect.MECHANISM, 1)
                .add(Aspect.MIND, 1),
            7,
            2,
            3,
            new ResourceLocation("ThaumicMachina:textures/research/r_crimson_wand_augmentation.png"),
            true);
        item.setParents(new String[] { "@CRIMSON_THAUMATURGY" });
        item.setParentsHidden(new String[] { "@WAND_AUGMENTATION" });
        item.setConcealed();
        ResearchHelper.setPageCount(item);
        item.registerResearchItem();
    }

    private static void setPageCount(ResearchItem ritem) {
        if (ritem instanceof TMResearchItem) {
            TMResearchItem tmritem = (TMResearchItem) ritem;
            tmritem.setPageCount();
        }
    }

    protected static ItemStack getResearchItem(String key, String cat) {
        return ((ResearchItem) ((ResearchCategoryList) ResearchCategories.researchCategories.get((Object) cat)).research
            .get((Object) key)).icon_item;
    }

    protected static ResourceLocation getResearchIcon(String key, String cat) {
        return ((ResearchItem) ((ResearchCategoryList) ResearchCategories.researchCategories.get((Object) cat)).research
            .get((Object) key)).icon_resource;
    }
}
