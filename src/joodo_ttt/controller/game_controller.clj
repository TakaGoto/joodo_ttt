(ns joodo_ttt.controller.game-controller
  (:require [compojure.core :refer [POST GET defroutes]]
            [joodo.views :refer [render-template]]
            [ring.util.response :refer [redirect]]))

(defroutes game-controller
  (GET "/game" [] (render-template "game")))
