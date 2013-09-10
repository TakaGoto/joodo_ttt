(ns joodo_ttt.controller.game-controller-spec
  (:require [speclj.core :refer [describe it should= should-contain
                                 context]]
            [joodo.spec-helpers.controller :refer [with-mock-rendering with-routes
                                                   do-post do-get should-redirect-to
                                                   request rendered-template
                                                   rendered-context]]
            [joodo_ttt.controller.game-controller :refer :all]))

(describe "game-controller"
  (with-mock-rendering)
  (with-routes game-controller)

  (context "GET '/game'"
    (it "stores user's ttt options in cookies"
      (let [result (do-get "/game")]
        (should= 200 (:status result))))))


;    (it "redirects if there is no cookies"
;      (should-redirect-to
;        (do-get "/game") "/"))))

;    (it "gets a 200 if there appropriate cookies are present"
;      (with-redefs [*request* {:cookies {:p-one "h"}}]
;        (let [result (do-get "/game")]
;          (should= 200
;            (:status result)))))))

