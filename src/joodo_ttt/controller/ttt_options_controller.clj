(ns joodo_ttt.controller.ttt-options-controller
  (:require [compojure.core :refer [POST GET defroutes]]
            [joodo.views :refer [render-template]]
            [ring.util.response :refer [redirect]]
            [joodo.middleware.request :refer [*request*]]
            [ttt.board :refer [create-board]]))

(defn- create-ttt-board [board-size]
  (let [size (Integer. board-size)]
  (clojure.string/join ""
    (take (* size size)
      (repeat "_")))))

(defn- store-data-in-cookies [params]
  (-> (redirect "/game")
      (assoc :cookies {:p-one {:value (:p-one params) :path "/game"}
                :p-two {:value (:p-two params) :path "/game"}
                :board-size {:value (:board-size params) :path "/game"}
                :board {:value (create-ttt-board (:board-size params))  :path "/game"}})))

(defroutes ttt-options-controller
  (GET "/" [] (render-template "index"))
  (POST "/" {:as request}
    (store-data-in-cookies (:params request))))
