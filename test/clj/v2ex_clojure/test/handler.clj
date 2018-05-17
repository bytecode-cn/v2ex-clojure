(ns v2ex-clojure.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [v2ex-clojure.handler :refer :all]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'v2ex-clojure.config/env
                 #'v2ex-clojure.handler/app)
    (f)))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= 404 (:status response))))))
