(ns phoenixlib.format)

(defn fmt-char
  [color]
  (str "§"
       (case color
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

(defn component
  [& args]
  (apply str (for [sym args]
               (if (string? sym) sym (fmt-char sym)))))
