package jcm2606.thaumicmachina.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;

public enum Mixins implements IMixins {

    WAND_AUGMENTATIONS(new TMBuilder().setRequired()
        .addCommonMixins("thaumcraft.common.items.wands.MixinItemWandCasting_Augmentations")),
    RUNE_RENDERING(new TMBuilder().setRequired()
        .addCommonMixins("thaumcraft.client.renderers.models.gear.MixinModelWand_Runes"));

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return this.builder;
    }

    static class TMBuilder extends MixinBuilder {

        public TMBuilder() {
            setPhase(Phase.LATE);
        }

        public MixinBuilder setRequired() {
            return super.setApplyIf(() -> true);
        }

    }
}
