
package jcm2606.thaumicmachina.core.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

    public Configuration config;
    protected static final String CATEGORY_RESEARCH = "RESEARCH";

    public Config(FMLPreInitializationEvent event) {
        this.config = new Configuration(event.getSuggestedConfigurationFile());
    }

    public void loadConfig() {
        Property conceptResearchModeProp = this.config.get(CATEGORY_RESEARCH, "research.concept.mode", 0);
        conceptResearchModeProp.comment = "Concept research mode. 0 = Normal, 1 = Easy (purchased), 2 = Auto-Unlock.";
        Settings.conceptResearchMode = conceptResearchModeProp.getInt(0);
    }
}
