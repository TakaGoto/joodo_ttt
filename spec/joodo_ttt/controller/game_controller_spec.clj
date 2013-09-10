(ns joodo_ttt.controller.game-controller-spec
  (:require [speclj.core :refer [describe it should= should-contain
                                 context]]
            [joodo.spec-helpers.controller :refer [with-mock-rendering with-routes
                                                   do-post do-get should-redirect-to]]
            [joodo_ttt.controller.game-controller :refer [game-controller]]))

(describe "game-controller"
  (with-mock-rendering)
  (with-routes game-controller)

  (context "GET '/game'"
    (it "stores user's ttt options in cookies"
      (let [result (do-get "/game")]
        (should= 200 (:status result))
        (should= "game" (:body result))))))
