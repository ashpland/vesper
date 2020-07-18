(ns user
  (:require
   [figwheel.main.api]))

(defn go []
  (figwheel.main.api/start "dev"))

(println "[dev] evaluate (go) to start figwheel")

