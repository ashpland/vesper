(ns ^:figwheel-hooks vesper.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [vesper.db :as db]
   [vesper.views :as views]
   [vesper.config :as config]))

(js/console.log "Hello vesper")

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::db/initialize-db])
  (dev-setup)
  (mount-root))

(defonce start-up (do (init) true))
