
package jcm2606.thaumicmachina.core.event;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jcm2606.thaumicmachina.block.metaphysical.BlockMetaphysical;
import jcm2606.thaumicmachina.core.helper.BlockHelper;
import jcm2606.thaumicmachina.wand.WandHelper;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationTaintCapping;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockFluxGas;
import thaumcraft.common.blocks.BlockFluxGoo;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.EntityUtils;

public class PlayerEventHandler {

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack stack = player.getCurrentEquippedItem();
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && !(BlockMetaphysical.hasGoggles(player))) {
            Vec3 vec = BlockHelper.getCentralCoords(event.x, event.y, event.z, event.face);
            if (player.worldObj.getBlock((int) vec.xCoord, (int) vec.yCoord, (int) vec.zCoord) != null
                && player.worldObj
                    .getBlock((int) vec.xCoord, (int) vec.yCoord, (int) vec.zCoord) instanceof BlockMetaphysical
                && player.getHeldItem() != null) {
                player.worldObj.setBlock((int) vec.xCoord, (int) vec.yCoord, (int) vec.zCoord, Blocks.air);
                player.setItemInUse(
                    player.getHeldItem(),
                    player.getHeldItem()
                        .getMaxItemUseDuration());
            }
        }
        if ((event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR
            || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) && stack != null
            && stack.getItem() != null) {
            if (stack.getItem() == ConfigItems.itemEldritchObject && stack.getItemDamage() == 1) {
                String rname = "@CRIMSON_PHILOSOPHY";
                if (!player.worldObj.isRemote
                    && !ResearchManager.isResearchComplete(player.getCommandSenderName(), rname)) {
                    PacketHandler.INSTANCE.sendTo(new PacketResearchComplete(rname), (EntityPlayerMP) player);
                    Thaumcraft.proxy.getResearchManager()
                        .completeResearch(player, rname);
                }
            }
        }
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && stack != null
            && stack.getItem() instanceof ItemWandCasting) {
            for (int i = -2; i < 2; ++i) {
                for (int j = -2; j < 2; ++j) {
                    for (int k = -2; k < 2; ++k) {
                        int x = event.x + i;
                        int y = event.y + j;
                        int z = event.z + k;
                        if (!(player.worldObj.getBlock(x, y, z) instanceof BlockFluxGas)
                            && !(player.worldObj.getBlock(x, y, z) instanceof BlockFluxGoo)) continue;
                        player.setItemInUse(stack, Integer.MAX_VALUE);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onUseItem(PlayerUseItemEvent.Tick event) {
        ItemStack stack = event.item;
        EntityPlayer player = event.entityPlayer;
        if (event.item == null || stack.getItem() == null || !(stack.getItem() instanceof ItemWandCasting wand)) {
            return;
        }
        MovingObjectPosition mop = EntityUtils.getMovingObjectPositionFromPlayer(player.worldObj, player, true);
        if (!WandHelper.hasAugmentation(stack, AugmentationTaintCapping.INSTANCE) || mop == null
            || mop.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
            return;
        }
        for (int i = -2; i < 2; ++i) {
            for (int j = -2; j < 2; ++j) {
                for (int k = -2; k < 2; ++k) {
                    int x = mop.blockX + i;
                    int y = mop.blockY + j;
                    int z = mop.blockZ + k;
                    Block block = player.worldObj.getBlock(x, y, z);
                    if (!(block instanceof BlockFluxGas) && !(block instanceof BlockFluxGoo)) continue;
                    Aspect aspect = Aspect.getPrimalAspects()
                        .get(
                            player.worldObj.rand.nextInt(
                                Aspect.getPrimalAspects()
                                    .size()));
                    if (player.worldObj.rand.nextInt(100) <= 20) {
                        aspect = Aspect.ENTROPY;
                    }
                    if (wand.getAspectsWithRoom(stack)
                        .getAmount(aspect) >= wand.getMaxVis(stack) || player.worldObj.rand.nextInt(100) <= 10)
                        continue;
                    wand.addRealVis(
                        stack,
                        aspect,
                        50 + player.worldObj.rand.nextInt(50) - player.worldObj.rand.nextInt(50),
                        true);
                    if (player.worldObj.rand.nextInt(50) > 0) continue;
                    player.worldObj.setBlock(x, y, z, Blocks.air);
                }
            }
        }
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        if (!(event.target instanceof EntityLivingBase target)) {
            return;
        }
        ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
        if (stack == null || !(stack.getItem() instanceof ItemWandCasting wand)) {
            return;
        }
        AspectList aspectList = wand.getAllVis(stack);
        int aspectCount = 0;
        for (Aspect aspect : aspectList.getAspects()) {
            if (aspectList.getAmount(aspect) <= 15) continue;
            ++aspectCount;
        }
        if (aspectCount >= 6) {
            target.attackEntityFrom(DamageSource.magic, 4.0f);
            for (Aspect aspect : aspectList.getAspects()) {
                wand.consumeVis(stack, event.entityPlayer, aspect, 15, false);
            }
            event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer, "thaumcraft:zap", 1.0f, 1.1f);
        }
    }
}
