(ns phoenixlib.format
  (:import (net.md_5.bungee.api ChatColor)
           (net.md_5.bungee.api.chat BaseComponent TextComponent)))

(set! *warn-on-reflection* true)

(defn legacy-char
  [fmt]
  (str "§"
       (case fmt
         :black "0"
         :dark_blue "1"
         :dark_green "2"
         :dark_aqua "3"
         :dark_red "4"
         :dark_purple "5"
         :gold "6"
         :gray "7"
         :dark_gray "8"
         :blue "9"
         :green "a"
         :aqua "b"
         :red "c"
         :light_purple "d"
         :yellow "e"
         :white "f"
         :obfuscated "k"
         :bold "l"
         :strikethrough "m"
         :underline "n"
         :italic "o"
         "r")))

(defn fmt-char
  ^ChatColor [fmt]
  (case fmt
    :black ChatColor/BLACK
    :dark_blue ChatColor/DARK_BLUE
    :dark_green ChatColor/DARK_GREEN
    :dark_aqua ChatColor/DARK_AQUA
    :dark_red ChatColor/DARK_RED
    :dark_purple ChatColor/DARK_PURPLE
    :gold ChatColor/GOLD
    :gray ChatColor/GRAY
    :dark_gray ChatColor/DARK_GRAY
    :blue ChatColor/BLUE
    :green ChatColor/GREEN
    :aqua ChatColor/AQUA
    :red ChatColor/RED
    :light-purple ChatColor/LIGHT_PURPLE
    :yellow ChatColor/YELLOW
    :white ChatColor/WHITE
    ChatColor/RESET))

(defn legacy-text
  [& args]
  (apply str (for [sym args]
               (if (string? sym) sym (legacy-char sym)))))

(defn component
  ^BaseComponent [{:keys [text color format]}]
  (let [cmp (TextComponent. ^String text)]
    (.setColor cmp (fmt-char color))
               (doseq [c format]
                 (case c
                   :bold (.setBold cmp true)
                   :italic (.setItalic cmp true)
                   :obfuscated (.setObfuscated cmp true)
                   :strikethrough (.setStrikethrough cmp true)
                   :underline (.setUnderlined cmp true)))
               cmp))

(defn text
  ^"[Lnet.md_5.bungee.api.chat.BaseComponent;" [& components]
  (into-array (for [cmp components] (component cmp))))
