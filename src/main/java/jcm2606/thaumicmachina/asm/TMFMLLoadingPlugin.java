
package jcm2606.thaumicmachina.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import jcm2606.thaumicmachina.asm.TMClassTransformer;

public class TMFMLLoadingPlugin
implements IFMLLoadingPlugin {
    public static File location;

    public String[] getASMTransformerClass() {
        return new String[]{TMClassTransformer.class.getName()};
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        location = (File)data.get("coremodLocation");
    }

    public String getAccessTransformerClass() {
        return null;
    }

    public static void log(String message, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println("[" + dateFormat.format(date) + "] [ThaumicMachina] [" + type + "] " + message);
    }
}

