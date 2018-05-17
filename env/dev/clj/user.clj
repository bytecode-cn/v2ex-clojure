(ns user
  (:require [v2ex-clojure.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [v2ex-clojure.core :refer [start-app]]
            [conman.core :as conman]
            [luminus-migrations.core :as migrations]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'v2ex-clojure.core/repl-server))

(defn stop []
  (mount/stop-except #'v2ex-clojure.core/repl-server))

(defn restart []
  (stop)
  (start))

(defn restart-db []
  (mount/stop #'v2ex-clojure.db.core/*db*)
  (mount/start #'v2ex-clojure.db.core/*db*)
  (binding [*ns* 'v2ex-clojure.db.core]
    (conman/bind-connection v2ex-clojure.db.core/*db* "sql/queries.sql")))

(defn reset-db []
  (migrations/migrate ["reset"] (select-keys env [:database-url])))

(defn migrate []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration [name]
  (migrations/create name (select-keys env [:database-url])))


