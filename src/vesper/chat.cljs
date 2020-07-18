(ns vesper.chat
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]
   [vesper.helpers :as h]
   ))

(def example-message
  {:value "Hello World"
   :timestamp 123})

(rf/reg-sub
 ::messages
 (fn [db]
   (sort-by :timestamp (::messages db))))

(rf/reg-event-db
 ::new-message
 (fn [db [_ message]]
   (update db ::messages #(conj % {:value message
                                   :timestamp (.getTime (js/Date.))}))))

(defn Compose
  []
  (let [*new-message (reagent/atom "")]
    (fn []
      [:form {:on-submit (fn [e]
                           (.preventDefault e)
                           (rf/dispatch [::new-message @*new-message])
                           (reset! *new-message ""))}
      [:input {:value @*new-message
               :on-change (fn [e]
                            (->> e
                                 h/event->value
                                 (reset! *new-message)))}]]
      )))

(defn Message
  [{:keys [value]}]
  [:p value])

(defn ChatView
  []
  (let [*messages (rf/subscribe [::messages])]
    (fn []
      (let [messages @*messages]
        [:div
         (when messages
             (for [{:keys [timestamp] :as message} messages]
                 ^{:key timestamp}
                 [Message message]
                 ))
         [Compose]
         ]))))
