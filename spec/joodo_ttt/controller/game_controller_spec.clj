(ns joodo_ttt.controller.game-controller-spec
  (:require [speclj.core :refer [describe it should= should-contain
                                 context]]
            [joodo.spec-helpers.controller :refer [with-mock-rendering with-routes
                                                   do-post do-get should-redirect-to
                                                   request rendered-template
                                                   rendered-context]]
            [joodo_ttt.controller.game-controller :refer :all]
            [joodo.middleware.request :refer [*request*]]))

(describe "game-controller"
  (with-mock-rendering)
  (with-routes game-controller)

  (context "GET '/game'"
    (it "stores user's ttt options in cookies"
      (let [result (do-get "/game" :cookies {:p-one {:value "h"}
                                                      :p-two {:value "c"}
                                                      :board-size {:value "3"}
                                                      :board {:value "_________"}})]
      (should= 200 (:status result)))))

  (context "POST '/game'"
    (it "makes a move"
      (let [result (do-post "/game" :cookies {:p-one {:value "h"}
                                              :p-two {:value "c"}
                                              :board-size {:value "3"}
                                              :board {:value "_________"}}
                                    :params {:player-move "5"})]
        (should= "____X____"
          (:value (:board (:cookies result))))))

    (it "keeps the cookies recorded"
      (let [result (do-post "/game" :cookies {:p-one {:value "h"}
                                              :p-two {:value "c"}
                                              :board-size {:value "3"}
                                              :board {:value "_________"}}
                                    :params {:player-move "5"})]
        (should= "h"
          (:value (:p-one (:cookies result))))
        (should= "c"
          (:value (:p-two (:cookies result))))
        (should= "3"
          (:value (:board-size result)))))

    (it "makes a computer move"
      (let [result (do-post "/game" :cookies {:p-one {:value "c"}
                                              :p-two {:value "h"}
                                              :board-size {:value "3"}
                                              :board {:value "OO_XXO___"}})]
        (it "returns a different board"
          (should= "OOX______"
            (:value (:board (:cookies result)))))))))
