(ns joodo_ttt.controller.ttt-options-controller
  (:require [compojure.core :refer [POST GET defroutes]]
            [joodo.views :refer [render-template]]
            [ring.util.response :refer [redirect]]))

(defn- store-data-in-cookies [params]
  (assoc
    (redirect "/game")
      :cookies {:p-one {:value (:p-one params) :path "/game"}
                :p-two {:value (:p-two params) :path "/game"}
                :board-size {:value (:board-size params) :path "/game"}}))

(defroutes ttt-options-controller
  (GET "/" [] (render-template "index"))
  (POST "/" {:as request}
    (store-data-in-cookies request)))
