(ns joodo-ttt.core
  (:use
    [compojure.core :only (defroutes GET)]
    [compojure.route :only (not-found)]
    [joodo.middleware.view-context :only (wrap-view-context)]
    [joodo.views :only (render-template render-html)]
    [joodo.controllers :only (controller-router)]))

(defroutes joodo-ttt-routes
  (GET "/" [] (render-template "index"))
  (controller-router 'joodo-ttt.controller)
  (not-found (render-template "not_found" :template-root "joodo_ttt/view" :ns `joodo-ttt.view.view-helpers)))

(def app-handler
  (->
    joodo-ttt-routes
    (wrap-view-context :template-root "joodo_ttt/view" :ns `joodo-ttt.view.view-helpers)))

