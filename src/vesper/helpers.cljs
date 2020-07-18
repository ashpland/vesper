(ns vesper.helpers)

(defn event->value
  [event]
  (-> event
      .-target
      .-value))

