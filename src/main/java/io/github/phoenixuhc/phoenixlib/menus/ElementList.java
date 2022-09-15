package io.github.phoenixuhc.phoenixlib.menus;

import java.util.List;
import java.util.Optional;

import org.bukkit.inventory.Inventory;

public class ElementList extends Element {
    private final List<ElementItem> items;

    public ElementList(List<ElementItem> items) {
        this.items = items;
    }

    public Optional<ElementItem> find(int slot) {
        return this.items.stream().filter(el -> el.slot() == slot).findAny();
    }

    public void draw(Inventory inv) {
        for (int i = 0; i < this.items.size(); i++) {
            this.items.get(i).draw(inv);
        }
    }
}

