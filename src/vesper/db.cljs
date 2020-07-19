(ns vesper.db
  (:require
   [re-frame.core :as rf]))

(def default-db
  {:name "re-frame"
   :vesper.recepient/all-recepients #{"Mom" "Dad" "Grandma" "Grandpa"}
   })

(rf/reg-event-db
 ::initialize-db
 (fn [_ _]
   default-db))
