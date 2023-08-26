(ns jsonplayground.app
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.data.json :as json]))

(def cli-version "0.0.1")

(def cli-options
  [["-v" "--version"]
   ["-r" "--read FILE"]
   ["-g" "--get DATA"]])

(defn read-file-to-json [path]
  ;; This will read JSON file
  ;; (println "read-file-to-json path: " path)
  (->> path
       (slurp)
       (json/read-json)))

(defn handle-read [target-path]
  ;; Check if the argument is not empty
  (when (empty? target-path)
    (throw (ex-info "The argument is empty!" {})))

  (read-file-to-json target-path))

(->> "./example/test.json"
     (handle-read)
     (println))

(defn -main [& args]
  (let [parsed-options (parse-opts args cli-options)
        options (:options parsed-options)
        arguments (:arguments parsed-options)]

    ;; Debug
    (println (str "Executing CLI with options: " options))
    ;; --version
    (cond
      (:version options)
      (println (str "Version: " cli-version)))

    ;; --read "/path/to/file.json"
    (cond
      (:read options)
      (println (handle-read (:read options))))

    (println "Hello world!")))

(comment
  (handle-read ["./example/test.json"])
  ;;
  )


