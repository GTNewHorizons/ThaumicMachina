
package jcm2606.thaumicmachina.core.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import jcm2606.thaumicmachina.client.gui.GuiNodeTransmodifier;
import jcm2606.thaumicmachina.inv.container.ContainerNodeTransmodifier;
import jcm2606.thaumicmachina.tile.TileNodeTransmodifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        ContainerNodeTransmodifier obj = null;
        switch (ID) {
            case 0: {
                TileEntity tile;
                if (world.getTileEntity(x, y, z) == null || !((tile = world.getTileEntity(x, y, z)) instanceof TileNodeTransmodifier)) break;
                obj = new ContainerNodeTransmodifier((TileNodeTransmodifier)tile, player.inventory);
            }
        }
        return obj;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        GuiNodeTransmodifier obj = null;
        switch (ID) {
            case 0: {
                TileEntity tile;
                if (world.getTileEntity(x, y, z) == null || !((tile = world.getTileEntity(x, y, z)) instanceof TileNodeTransmodifier)) break;
                obj = new GuiNodeTransmodifier((TileNodeTransmodifier)tile, player.inventory);
            }
        }
        return obj;
    }
}

