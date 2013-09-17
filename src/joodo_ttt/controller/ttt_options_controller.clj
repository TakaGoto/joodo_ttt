(ns joodo_ttt.controller.ttt-options-controller
  (:require [compojure.core :refer [POST GET defroutes]]
            [joodo.views :refer [render-template]]
            [ring.util.response :refer [redirect]]
            [joodo.middleware.request :refer [*request*]]
            [ttt.board :refer [create-board]]
            [joodo_ttt.util.cookie-monster :refer [store-params-to-cookies]]
            [joodo_ttt.util.util :refer [create-ttt-board]]))

(defn- store-data-in-cookies [params]
  (-> (redirect "/game")
      (assoc :cookies (store-params-to-cookies params))))

(defroutes ttt-options-controller
  (GET "/" [] (render-template "index"))
  (POST "/" []
    (store-data-in-cookies (:params *request*))))
