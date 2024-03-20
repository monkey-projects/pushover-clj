(ns monkey.pushover.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [martian
             [core :as mc]
             [test :as mt]]
            [monkey.pushover.core :as sut]))

(deftest make-client
  (testing "creates and initializes martian context"
    (is (map? (sut/make-client {})))))

(deftest post-message
  (testing "invokes `:post-message` route"
    (let [client (-> (sut/make-client {})
                     (mt/respond-with-generated {:post-message :success}))]
      (is (some? (sut/post-message client
                                   {:token "test-token"
                                    :user "test-user"
                                    :message "test-msg"}))))))
