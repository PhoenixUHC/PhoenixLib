(ns phoenixlib.util
  (:import (org.bukkit.enchantments Enchantment)
           (org.bukkit.potion PotionEffect PotionEffectType)
           (org.bukkit.inventory ItemStack ItemFlag)))

(set! *warn-on-reflection* true)

(defn effect
  (^PotionEffect [^PotionEffectType t ^long duration ^long amplifier] (PotionEffect. t (* 20 duration) amplifier))
  (^PotionEffect [^PotionEffectType t ^long duration] (effect t duration 0))
  (^PotionEffect [^PotionEffectType t] (effect t 1000000 0)))

(defn glow
  "Makes the given item stack glow by adding the lure enchant and the hide enchants item flag."
  ^ItemStack [^ItemStack it]
  (.setItemMeta
    it
    (doto (.getItemMeta it)
      (.addEnchant Enchantment/LURE 0 true)
      (.addItemFlags (into-array ItemFlag [ItemFlag/HIDE_ENCHANTS]))))
  it)