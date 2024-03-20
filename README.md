# Pushover Clj

This is a Clojure library to talk to the [Pushover API](https://pushover.net/api).  It uses
[Martian](https://github.com/oliyh/martian) to build the requests.

# Usage

Include it in your project:

```clojure
{:deps {com.monkeyprojects/pushover-clj {:mvn/version 0.1.0-SNAPSHOT}}}
```

The functionality can be found in namespace `monkey.pushover.core`.  First create a client,
then invoke the `post-message` function:

```clojure
(require '[monkey.pushover.core :as p])

;; Create a client with default opts
(def client (p/make-client {}))

;; Post a message
(p/post-message client {:token "secret-app-token"
                        :user "dest-user-id"
			:message "Hi, this is a message from pushover-clj"!})
```

# License

Copyright (c) 2024 by [Monkey Projects BV](https://www.monkey-projects.be)
[GPL v3 License](LICENSE)