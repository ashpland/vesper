(ns vesper.recepient
  (:require
   [re-frame.core :as rf]
   [vesper.helpers :as h]))

(rf/reg-event-db
 ::set-recepient
 (fn [db [_ recepient]]
   (assoc db ::current-recepient recepient)))

(rf/reg-sub
 ::all-recepients
 (fn [db]
   (::all-recepients db)))

(rf/reg-sub
 ::current-recepient
 (fn [db]
   (::current-recepient db)))

(defn SelectRecepient
  []
  (let [*recepients (rf/subscribe [::all-recepients])]
    (fn []
      [:div
       [:form
        [:p "I would like to send a message to "
         [:select {:on-change
                   (fn [e]
                     (let [value (h/event->value e)]
                       (.log js/console "send to" value)
                       (rf/dispatch [::set-recepient value])))}
          [:option]
          (for [recepient (sort @*recepients)]
            ^{:key recepient}
            [:option recepient])]]]])))
