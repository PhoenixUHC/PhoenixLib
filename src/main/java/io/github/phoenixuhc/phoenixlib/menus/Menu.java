package io.github.phoenixuhc.phoenixlib.menus;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public abstract class Menu {
    public static class MenuListener implements Listener {
        private static final Map<UUID, Menu> MENUS = new HashMap<>();

        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
            Menu menu = MENUS.get(e.getWhoClicked().getUniqueId());
            if (menu == null) return;

            e.setCancelled(true);

            menu.find(e.getSlot()).ifPresent(el -> {
                el.onClick(e.getWhoClicked());
                el.draw(e.getInventory());
            });
        }

        @EventHandler
        public void onInventoryClose(InventoryCloseEvent e) {
            MENUS.remove(e.getPlayer().getUniqueId());
        }
    }

    private final HumanEntity entity;
    private final String title;

    public abstract BaseComponent[] title();
    public abstract Collection<Element> elements();
    public abstract int size();

    public void draw() {
        Inventory inv = this.entity.getOpenInventory().getTopInventory();
        this.elements().forEach(element -> element.draw(inv));
    }

    public Menu(HumanEntity entity) {
        this.entity = entity;
        this.title = new TextComponent(this.title()).toLegacyText();
    }

    public void open() {
        Inventory inv = Bukkit.createInventory(null, this.size(), this.title);
        MenuListener.MENUS.put(this.entity.getUniqueId(), this);
        this.entity.openInventory(inv);
        this.draw();
    }

    private Optional<ElementItem> find(int slot) {
        return this.elements().stream().map(el -> {
            if (el instanceof ElementItem && ((ElementItem) el).slot() == slot) return (ElementItem) el;
            if (el instanceof ElementList) {
                Optional<ElementItem> item = ((ElementList) el).find(slot);
                if (item.isPresent()) return item.get();
            }
            return null;
        }).filter(el -> el != null).findAny();
    }
}

