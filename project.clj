(defproject phoenixlib "0.1.0"
  :description "Utility library for writing Minecraft plugins in Clojure"
  :license {:name "GNU GPLv3"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :repositories [["spigotmc" "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"]
                 ["snapshots" "https://oss.sonatype.org/content/repositories/snapshots"]]
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :profiles {:provided {:dependencies [[org.spigotmc/spigot-api "1.8.8-R0.1-SNAPSHOT" :scope "runtime"]]}}
  :repl-options {:init-ns test.core}
  :aot :all)