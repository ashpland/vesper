(ns vesper.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::all-recepients
 (fn [db]
   (:all-recepients db)))

(re-frame/reg-sub
 ::current-recepient
 (fn [db]
   (:current-recepient db)))
