[:form {:action "/" :method "POST"}
 [:small "player one"]
 [:select {:name "p-one" :size "1"}
  [:option {:value "human"} "human"]
  [:option {:value "computer"} "computer"]]

 [:br]

 [:small "player two"]
 [:select {:name "p-two" :size "1"}
  [:option {:value "human"} "human"]
  [:option {:value "computer"} "computer"]]

 [:br]

 [:small "board size"]
 [:select {:name "board-size" :size "1"}
  [:option {:value "3"} "3"]
  [:option {:value "4"} "4"]]

 [:br]

 [:div
  [:input {:id "start_game" :type "submit" :value "Start Game"}]]]
