(ns vesper.views
  (:require
   [re-frame.core :as re-frame]
   [vesper.chat :as chat]
   [vesper.events :as events]
   [vesper.subs :as subs]
   ))

(defn event->value
  [event]
  (-> event
      .-target
      .-value))

(defn SelectRecepient
  []
  (let [*recepients (re-frame/subscribe [::subs/all-recepients])]
    (fn []
      [:div
       [:form
        [:p "I would like to send a message to "
         [:select {:on-change
                   (fn [e]
                     (let [value (event->value e)]
                       (.log js/console "send to" value)
                       (re-frame/dispatch [::events/set-recepient value])))}
          [:option]
          (for [recepient (sort @*recepients)]
            ^{:key recepient}
            [:option recepient]
            )]]]])))

(defn main-panel []
  []
  (let [*recepient (re-frame/subscribe [::subs/current-recepient])]
    (fn []
    [:div
     [SelectRecepient]
     (when @*recepient
       [:<>
       [:hr]
       [:h3 "Dear " @*recepient]
       [chat/ChatView]])
     ])))



#_(comment
   (for [slug slugs]
     ^{:key slug}
     [ContactLine slug])


   )
