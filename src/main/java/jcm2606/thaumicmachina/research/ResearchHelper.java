
package jcm2606.thaumicmachina.research;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import jcm2606.thaumicmachina.ThaumicMachina;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigResearch;

public class ResearchHelper {

    public static final String CATEGORY_KEY = "THAUMIC_MACHINA_CAT";
    public static final String RESEARCH_ID_NAME = "tm.research.name.";
    public static final String RESEARCH_ID_DESC = "tm.research.desc.";
    public static final String RESEARCH_ID_PAGE = "tm.research.page.";

    public static void loadResearch() {
        ThaumicMachina.log.info("Loading research...");
        ResearchCategories.registerCategory(
            CATEGORY_KEY,
            new ResourceLocation("ThaumicMachina:textures/research/r_tab.png"),
            new ResourceLocation("thaumcraft:textures/gui/gui_researchback.png"));
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
        ResearchHelper.loadNodalResearch();
        ResearchHelper.loadVisResearch();
        ResearchHelper.loadEssentiaResearch();
        ResearchHelper.loadWandResearch();
        ResearchHelper.loadEntityResearch();
        ResearchHelper.loadCrimsonResearch();
        ThaumicMachina.log.info("Research loaded");
    }

    private static void loadNodalResearch() {
        ResearchItem item = new TMResearchItem(
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
        item.setParents("@TMNODES");
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
        item.setParents("@NODAL_STUDIES", "@VIS_STUDIES");
        item.setConcealed();
        item.registerResearchItem();
    }

    private static void loadVisResearch() {
        ResearchItem item = new TMResearchItem(
            "VIS",
            new AspectList(),
            -1,
            1,
            1,
            new ResourceLocation("thaumcraft:textures/misc/r_nodetap1.png"),
            true);
        ResearchHelper.setPageCount(item);
        item.setParents("@TMNODES");
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
        item.setParents("@VIS");
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
        item.setParents("@VIS_STUDIES");
        item.registerResearchItem();
    }

    private static void loadEssentiaResearch() {}

    private static void loadWandResearch() {
        ResearchItem item = new TMResearchItem(
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
        item.setParentsHidden("@VIS_CHARGE");
        item.setParents("@TMTHAUMATURGY");
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
            new ResearchPage("tm.research.page.WAND_AUGMENTATION.1"),
            new ResearchPage("tm.research.page.WAND_AUGMENTATION.2"),
            new ResearchPage((IArcaneRecipe) ConfigResearch.recipes.get("TM_WAND_AUGMENTATION_CORE")));
        item.setParents("@WAND_STUDIES");
        item.setParentsHidden("INFUSION");
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
            new ResearchPage("tm.research.page.WAND_AUGMENTATION_CHARGE_BUFFER.1"),
            new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_CHARGE_BUFFER")));
        item.setConcealed();
        item.setParents("@WAND_AUGMENTATION");
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
            new ResearchPage("tm.research.page.WAND_AUGMENTATION_VIS_CHANNEL.1"),
            new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_VIS_CHANNEL")),
            new ResearchPage("tm.research.page.WAND_AUGMENTATION_VIS_CHANNEL.3"));
        item.setConcealed();
        item.setParents("@WAND_AUGMENTATION");
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
            new ResearchPage("tm.research.page.WAND_AUGMENTATION_TAINTED_CORE.1"),
            new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_TAINTED_CORE")));
        item.setConcealed();
        item.setParents("@WAND_AUGMENTATION");
        item.registerResearchItem();
        ThaumcraftApi.addWarpToResearch("@WAND_AUGMENTATION_TAINTED_CORE", 2);
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
            new ResearchPage("tm.research.page.WAND_AUGMENTATION_TAINT_CAPPING.1"),
            new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_TAINT_CAPPING")),
            new ResearchPage("tm.research.page.WAND_AUGMENTATION_TAINT_CAPPING.3"));
        item.setConcealed();
        item.setParents("@WAND_AUGMENTATION_TAINTED_CORE");
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
            new ResearchPage("tm.research.page.WAND_AUGMENTATION_CONTACT_DISCHARGE.1"),
            new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("TM_WA_CONTACT_DISCHARGE")),
            new ResearchPage("tm.research.page.WAND_AUGMENTATION_CONTACT_DISCHARGE.3"));
        item.setConcealed();
        item.setParents("@WAND_AUGMENTATION");
        item.setParentsHidden("@CRIMSON_WAND_AUGMENTATION");
        item.registerResearchItem();
        ThaumcraftApi.addWarpToResearch("@WAND_AUGMENTATION_CONTACT_DISCHARGE", 1);
    }

    private static void loadEntityResearch() {}

    private static void loadCrimsonResearch() {
        ResearchItem item = new TMResearchItem(
            "CRIMSON_PHILOSOPHY",
            new AspectList(),
            4,
            5,
            0,
            new ItemStack(ConfigItems.itemEldritchObject, 1, 1),
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
        item.setParents("@CRIMSON_PHILOSOPHY");
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
        item.setParents("@CRIMSON_ASTRONOMY");
        item.setParentsHidden("@AURA_FIELD");
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
        item.setParents("@CRIMSON_REALISATION");
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
        item.setParents("@CRIMSON_PHILOSOPHY");
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
        item.setParents("@CRIMSON_THAUMATURGY");
        item.setParentsHidden("@WAND_AUGMENTATION");
        item.setConcealed();
        ResearchHelper.setPageCount(item);
        item.registerResearchItem();
    }

    private static void setPageCount(ResearchItem ritem) {
        if (ritem instanceof TMResearchItem tmritem) {
            tmritem.setPageCount();
        }
    }

    protected static ItemStack getResearchItem(String key, String cat) {
        return ((ResearchCategoryList) ResearchCategories.researchCategories.get(cat)).research.get(key).icon_item;
    }

    protected static ResourceLocation getResearchIcon(String key, String cat) {
        return ((ResearchCategoryList) ResearchCategories.researchCategories.get(cat)).research.get(key).icon_resource;
    }
}
