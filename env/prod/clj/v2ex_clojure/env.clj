(ns v2ex-clojure.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[v2ex-clojure started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[v2ex-clojure has shut down successfully]=-"))
   :middleware identity})
