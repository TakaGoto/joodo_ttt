(ns joodo_ttt.ui.joodo-ui
  (:require [ttt.ui.ui-protocol :refer :all]
            [joodo.middleware.request :refer [*request*]]
            [joodo.env :refer [*env*]]))

(def message (atom ""))

(defn ui-joodo []
  (reify
    Ui
    (welcome [this] (swap! message (fn [n] "Welcome to Tic Tac Toe!")))
    (ask-play-again [this] (swap! message (fn [n] "play again?")))
    (display-end-game [this result] (swap! message (fn [n] "game over!")))))
