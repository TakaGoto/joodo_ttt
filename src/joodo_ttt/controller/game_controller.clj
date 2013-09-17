(ns joodo_ttt.controller.game-controller
  (:require [compojure.core :refer [POST GET defroutes]]
            [joodo.views :refer [render-template render-hiccup]]
            [joodo.middleware.request :refer [*request*]]
            [ring.util.response :refer [redirect]]
            [joodo_ttt.presenter.board-presenter :refer [generate-board]]
            [joodo_ttt.ui.joodo-ui :refer [ui-joodo]]
            [ttt.game :refer [play]]))

(defn convert-board [board]
  (subvec (clojure.string/split board #"") 1))

(defn- join-slots [board]
  (clojure.string/join "" board))

(defn get-cookie [k]
  (:value (k (:cookies *request*))))

(defn- play-game [& [player-move]]
  (play ui-joodo
        {:p-one (get-cookie :p-one)
         :p-two (get-cookie :p-two)
         :board (convert-board (get-cookie :board))
         :board-size (get-cookie :board-size)}
        player-move))

(defn redirect-and-make-move [player-move]
  (-> (redirect "/game")
      (assoc :cookies {:p-one {:value (get-cookie :p-one) :path "/game"}
                :p-two {:value (get-cookie :p-two) :path "/game"}
                :board {:value (join-slots (play-game player-move)) :path "/game"}}
                :board-size {:value (get-cookie :board-size) :path "/game"})))

(defn- render-board [board]
  {:status 200
   :headers {}
   :body (generate-board board)
   :cookies {:board {:value (join-slots board) :path "/game"}}})

(defroutes game-controller
  (GET "/game" [] (render-board (play-game)))
  (POST "/game" {:as request}
        (redirect-and-make-move (:player-move (:params request)))))
