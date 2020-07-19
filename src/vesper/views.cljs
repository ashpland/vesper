(ns vesper.views
  (:require
   [re-frame.core :as re-frame]
   [vesper.chat :as chat]
   [vesper.recepient :as recepient]))

(defn main-panel []
  []
  (let [*recepient (re-frame/subscribe [::recepient/current-recepient])]
    (fn []
      [:div
       [recepient/SelectRecepient]
       (when @*recepient
         [:<>
          [:hr]
          [:h3 "Dear " @*recepient]
          [chat/ChatView]])])))
