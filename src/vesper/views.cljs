(ns vesper.views
  (:require
   [re-frame.core :as rf]
   [vesper.state :as state]))

(defn main-panel []
  (let [*current-state (rf/subscribe [::state/current-state])]
    (fn []
      [:<>
       [state/StateHelper]
       (state/state->view @*current-state)])))
