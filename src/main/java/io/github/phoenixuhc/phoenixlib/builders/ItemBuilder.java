package io.github.phoenixuhc.phoenixlib.builders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Utility builder class to create pretty items.
 */
public class ItemBuilder {
    private final Material material;
    private int amount = 1;
    private byte mask = 0;

    private BaseComponent[] name = {};
    private BaseComponent[][] lore = {};

    private Map<Enchantment, Integer> enchantments = new HashMap<>();

    private Function<ItemMeta, ItemMeta> meta = m -> m;

    /**
     * @param material the {@link Material} for this stack.
     */
    public ItemBuilder(Material material) {
        this.material = material;
    }

    /**
     * Specify the amount of items in the stack.
     *
     * @param amount the new amount, default is 1.
     * @return this.
     */
    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Add an enchantment and hide it from the stack, making it glow.
     * This is not useful if the stack has at least one enchantment.
     *
     * @return this.
     */
    public ItemBuilder glow() {
        this.mask = (byte) (this.mask | 0x01);
        return this;
    }

    /**
     * Make this item unbreakable.
     *
     * @return this.
     */
    public ItemBuilder unbreakable() {
        this.mask = (byte) (this.mask | 0x02);
        return this;
    }

    /**
     * Specify a display name for the stack.
     *
     * @param name the new name.
     * @return this.
     */
    public ItemBuilder name(BaseComponent[] name) {
        this.name = name;
        return this;
    }

    /**
     * Specify a lore for the stack.
     *
     * @param lore the new lore
     * @return this.
     */
    public ItemBuilder lore(BaseComponent[]... lore) {
        this.lore = lore;
        return this;
    }

    /**
     * Add an enchantment to the stack.
     *
     * @param enchantment an enchantment.
     * @param level the level for the given enchantment.
     * @return this.
     */
    public ItemBuilder enchant(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
        return this;
    }

    /**
     * Specifies a custom hook that modifies the meta.
     *
     * @param meta a function that takes the built meta and returns a custom meta.
     * @return this.
     */
    public ItemBuilder overrideMeta(Function<ItemMeta, ItemMeta> meta) {
        this.meta = meta;
        return this;
    }

    /**
     * Builds an item stack from the specified parameters.
     *
     * @return the new {@link ItemStack}.
     */
    public ItemStack build() {
        ItemStack is = new ItemStack(this.material, this.amount);
        ItemMeta im = is.getItemMeta();

        // Glow
        if ((this.mask & 0x01) == 0x01) {
            im.addEnchant(Enchantment.DURABILITY, 0, true);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        // Unbreakable
        if ((this.mask & 0x02) == 0x02) {
            im.spigot().setUnbreakable(true);
            im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }

        // Display
        im.setDisplayName(new TextComponent(this.name).toLegacyText());
        im.setLore(Arrays.asList(this.lore).stream().map(component -> new TextComponent(component).toPlainText()).collect(Collectors.toList()));

        // Enchants
        this.enchantments.forEach((enchant, level) -> im.addEnchant(enchant, level, true));

        // Hook
        is.setItemMeta(this.meta.apply(im));
        return is;
    }
}

