(ns stuartsierra.core
  (:require [com.stuartsierra.component :as component]
            [stuartsierra.db :as d])
  (:use [clojure.pprint]))

(defrecord Database [url]
  component/Lifecycle
  (start [this]
    ;(println "Starting database connection to" url)
    ;; Simulação de inicialização do banco
    (assoc this :connection (str "Connected to " url)))
  (stop [this]
    ;(println "Stopping database connection")
    ;; Simulação de fechamento da conexão
    (assoc this :connection nil)))

(defn new-database [url]
  (map->Database {:url url}))

(defrecord App [db db2]
  component/Lifecycle
  (start [this]
    ;(println "start app" this)
    this)
  (stop [this]
    ;(println "stop app" this)
    this)
  )

(defn new-app []
  (map->App {}))

(def system
  (component/system-map
    :db (new-database "localhost:5432/mydb")
    :db2 (new-database "localhost:5432/mydb2")
    :app (component/using (new-app) [:db :db2])
    :app2 (component/using (new-app) {:db :db :db2 :db2})
    :app3 {:db-url ":db"}
    )
  )

;start system
(def running-system (component/start system))
;(pprint running-system)

;; Para parar o sistema:
;(pprint (component/stop running-system))

;start system with main
;(defn -main [] (component/start system))

;pass context function nested namespace
(d/connect running-system)