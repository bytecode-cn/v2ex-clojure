(ns v2ex-clojure.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [v2ex-clojure.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[v2ex-clojure started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[v2ex-clojure has shut down successfully]=-"))
   :middleware wrap-dev})
