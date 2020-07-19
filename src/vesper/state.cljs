(ns vesper.state
  (:require
   [clojure.spec.alpha :as s]
   [re-frame.core :as rf]
   [vesper.chat :as chat]
   [vesper.recepient :as recepient]))

(def all-states #{::intro ::settings ::chat ::end})

(s/def ::state all-states)

(rf/reg-event-db
 ::change-state
 (fn [db [_ new-state]]
   (when (s/valid? ::state new-state)
     (assoc db ::state new-state))))

(rf/reg-sub
 ::current-state
 (fn [db]
   (::state db)))

(defn state->view
  [state]
  (when (s/valid? ::state state)
     (case state
       ::intro [:h3 "Intro explaining why and"]
       ::settings [:div
                   [:h3 "Settings: name, method of transmission"]
                   [recepient/SelectRecepient]
                   [:p "✨ magic, 🙏 prayer, 📡 technology, ♥️  love"]]
       ::chat [chat/ChatView]
       ::end [:h3 "finish, now what"])))

(defn StateHelper []
  (let [*current-state (rf/subscribe [::current-state])
        ]
    (fn []
      [:div
       [:p "the current state is: " @*current-state]
       [:div
        (for [state all-states]
          ^{:key state}
          [:button {:on-click #(rf/dispatch [::change-state state])}
           state])]])))
