(ns phoenixlib.event
  (:import (org.bukkit Bukkit)
           (org.bukkit.event EventPriority Listener)
           (org.bukkit.plugin EventExecutor Plugin)))

(set! *warn-on-reflection* true)

(defn register-handler
  ([^Plugin plugin event handler ^EventPriority priority]
   (.. Bukkit getPluginManager
       (registerEvent event (reify Listener) priority
                      (reify EventExecutor
                        (execute [_ _ e]
                          (when (= (type e) event) (handler e))))
                      plugin)))
  ([^Plugin plugin event handler] (register-handler plugin event handler EventPriority/NORMAL)))

(defn register-handlers [^Plugin plugin handlers]
  (doseq [[event handler ^EventPriority priority] handlers]
    (register-handler plugin event handler
                      (if priority priority EventPriority/NORMAL))))