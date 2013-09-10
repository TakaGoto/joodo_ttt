(ns joodo_ttt.presenter.board-presenter
  (:require [ttt.board :refer [get-length]]))

(defn- find-slot [board index]
  (let [slot (str (get board index))]
    (if (= slot "_")
      (.indexOf board slot)
      slot)))

(defn- append-slot [index board html-board]
  (let [slot (find-slot board index)]
    (str html-board
         "<form action='/player_move' method='post' style='float:left;'>"
         "<div style='width: 50px; length: 50px;'>"
         (format "<input type='submit' value=%s name='player_move'>" slot)
         "</div>"
         "</form>")))

(defn generate-board [board]
  (let [length (get-length board)]
    (loop [html-board (format "<div style='width: %spx;'>" (* length 50)) x 0]
      (if (= x (* length length))
        (str html-board "</div>")
        (recur (append-slot x board html-board) (inc x))))))
