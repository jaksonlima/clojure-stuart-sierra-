(ns stuartsierra.kafka)

(defn consumer [context]
  (println "consumer kafka with kafka.clj 'app'" (:app context)))