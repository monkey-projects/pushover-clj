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
  (let [client (-> (sut/make-client {})
                   (mt/respond-with-generated {:post-message :random}))]
    (testing "invokes `:post-message` route"
      (is (nil? (-> (sut/post-message client
                                      {:token "test-token"
                                       :user "test-user"
                                       :message "test-msg"})
                    deref))))

    (testing "fails when no user given"
      (is (thrown? clojure.lang.ExceptionInfo
                   (sut/post-message client
                                     {:token "test-token"
                                      :message "test-msg"}))))))

;; TODO Add integration tests
