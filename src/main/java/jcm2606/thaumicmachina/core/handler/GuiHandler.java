
package jcm2606.thaumicmachina.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;
import jcm2606.thaumicmachina.client.gui.GuiNodeTransmodifier;
import jcm2606.thaumicmachina.inv.container.ContainerNodeTransmodifier;
import jcm2606.thaumicmachina.tile.TileNodeTransmodifier;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (!(te instanceof TileNodeTransmodifier transmodifier)) return null;
            return new ContainerNodeTransmodifier(transmodifier, player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (!(te instanceof TileNodeTransmodifier transmodifier)) return null;
            return new GuiNodeTransmodifier(transmodifier, player.inventory);
        }
        return null;
    }
}
