(ns monkey.pushover.core
  (:require [martian
             [core :as mc]
             [httpkit :as mh]]
            [schema.core :as s]))

(def default-opts
  {:url "https://api.pushover.net/1"})

(defn- in-range [lo hi]
  (fn [v]
    (<= lo v hi)))

(def routes
  [{:route-name :post-message
    :path-parts ["/messages.json"]
    :method :post
    :form-schema {:token s/Str
                  :user s/Str
                  :message s/Str
                  ;; TODO Attachment support
                  (s/optional-key :device) s/Str
                  (s/optional-key :sound) s/Str
                  (s/optional-key :title) s/Str
                  (s/optional-key :url) s/Str
                  (s/optional-key :url_title) s/Str
                  (s/optional-key :html) (s/constrained s/Int #{0 1})
                  (s/optional-key :priority) (s/constrained s/Int (in-range -2 2))
                  (s/optional-key :timestamp) s/Int
                  (s/optional-key :ttl) s/Int}
    :consumes ["application/x-www-form-urlencoded"]
    :produces ["application/json"]}])

(defn make-client [& [opts]]
  (let [opts (merge default-opts opts)]
    ;; TODO Allow specifying the token once when creating the client and use an interceptor
    (mh/bootstrap (:url opts) routes)))

(defn post-message [client msg]
  (mc/response-for client :post-message msg))

;; TODO Other calls
