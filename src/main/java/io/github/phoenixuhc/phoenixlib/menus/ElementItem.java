package io.github.phoenixuhc.phoenixlib.menus;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class ElementItem implements Element {
    private final int slot;

    public ElementItem(int slot) {
        this.slot = slot;
    }

    public int slot() {
        return this.slot;
    }

    public abstract ItemStack item();
    public abstract void onClick(HumanEntity entity);

    public void draw(Inventory inv) {
        inv.setItem(this.slot(), this.item());
    }
}

