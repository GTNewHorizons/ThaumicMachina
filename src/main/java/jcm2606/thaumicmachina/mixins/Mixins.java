package jcm2606.thaumicmachina.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;

public enum Mixins implements IMixins {

    WAND_AUGMENTATIONS(
        new MixinBuilder().addCommonMixins("thaumcraft.common.items.wands.MixinItemWandCasting_Augmentations")),
    RUNE_RENDERING(new MixinBuilder().addCommonMixins("thaumcraft.client.renderers.models.gear.MixinModelWand_Runes"));

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder.setPhase(Phase.LATE);
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return this.builder;
    }
}
