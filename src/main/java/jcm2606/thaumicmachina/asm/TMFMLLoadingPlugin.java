
package jcm2606.thaumicmachina.asm;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class TMFMLLoadingPlugin implements IFMLLoadingPlugin {

    public static File location;

    @Override
    public String[] getASMTransformerClass() {
        return new String[] { TMClassTransformer.class.getName() };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        location = (File) data.get("coremodLocation");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public static void log(String message, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println("[" + dateFormat.format(date) + "] [ThaumicMachina] [" + type + "] " + message);
    }
}
