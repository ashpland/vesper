(ns vesper.events
  (:require
   [re-frame.core :as re-frame]
   [vesper.db :as db]
   ))

(.log js/console "events initialized")

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-recepient
 (fn [db [_ recepient]]
   (assoc db :current-recepient recepient)))
