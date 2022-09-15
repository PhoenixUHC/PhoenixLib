package io.github.phoenixuhc.phoenixlib.menus;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

public abstract class ElementCheckbox extends ElementItem {
    private boolean enabled = false;

    public abstract ItemStack enabledItem();
    public abstract ItemStack disabledItem();

    public ElementCheckbox(int slot) {
        super(slot);
    }

    public ElementCheckbox(int slot, boolean enabled) {
        super(slot);
        this.enabled = enabled;
    }

    public boolean enabled() {
        return this.enabled;
    }

    @Override
    public ItemStack item() {
        return this.enabled ? this.enabledItem() : this.disabledItem();
    }

    @Override
    public void onClick(HumanEntity entity) {
        this.enabled = !this.enabled;
    }
}

