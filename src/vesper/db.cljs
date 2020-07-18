(ns vesper.db
  (:require
   [vesper.chat :as chat]))

(def default-db
  {:name "re-frame"
   :all-recepients #{"Mom" "Dad" "Grandma" "Grandpa"}
   ; ::chat/messages [chat/example-message]
   })
