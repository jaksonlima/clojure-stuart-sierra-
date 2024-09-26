(ns stuartsierra.db
  (:require [stuartsierra.kafka :as k]))

(defn connect [context]
  (println "connect db with 'db.clj'" (:db context))
  (k/consumer context))