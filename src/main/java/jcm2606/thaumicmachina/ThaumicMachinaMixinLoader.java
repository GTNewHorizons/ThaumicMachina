package jcm2606.thaumicmachina;

import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;

import jcm2606.thaumicmachina.mixins.Mixins;

@LateMixin
public class ThaumicMachinaMixinLoader implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.ThaumicMachina.late.json";
    }

    @Nonnull
    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        return IMixins.getLateMixins(Mixins.class, loadedMods);
    }
}
