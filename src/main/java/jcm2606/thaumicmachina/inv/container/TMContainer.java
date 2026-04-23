
package jcm2606.thaumicmachina.inv.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public abstract class TMContainer
extends Container {
    public InventoryPlayer playerInv;
    int xs;
    int ys;

    public TMContainer(InventoryPlayer playerInv) {
        this.playerInv = playerInv;
        this.xs = 8;
        this.ys = 84;
    }

    public void loadPlayerInvSlots() {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)this.playerInv, j + i * 9 + 9, this.xs + j * 18, this.ys + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.playerInv, i, this.xs + i * 18, this.ys + 58));
        }
    }

    public void setStartPos(int xs, int ys) {
        this.xs = xs;
        this.ys = ys;
    }

    public void setStartPosX(int xs) {
        this.setStartPos(xs, this.ys);
    }

    public void setStartPosY(int ys) {
        this.setStartPos(this.xs, ys);
    }

    public void offsetStartPosX(int offset) {
        this.setStartPosX(this.xs + offset);
    }

    public void offsetStartPosY(int offset) {
        this.setStartPosY(this.ys + offset);
    }
}

