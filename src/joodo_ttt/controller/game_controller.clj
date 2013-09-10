(ns joodo_ttt.controller.game-controller
  (:require [compojure.core :refer [POST GET defroutes]]
            [joodo.views :refer [render-template render-hiccup]]
            [joodo.middleware.request :refer [*request*]]
            [ring.util.response :refer [redirect]]
            [joodo_ttt.presenter.board-presenter :refer [generate-board]]))

(defroutes game-controller
  (GET "/game" []
    (generate-board (:value (:board (:cookies *request*))))))
