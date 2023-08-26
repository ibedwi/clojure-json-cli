(ns jsonplayground.playground)

(defn extract-core [node]

  (select-keys node [:id
                     :name]))

;; This will read JSON file
(->> "test.json"
     (slurp)
     (json/read-json)
     (extract-core))

(comment

  (json/read-str "{\"a\":1,\"b\":2}")
  (slurp "test.json")

  (->> "test.json"
       (slurp) ;; read a file
       (json/read-json) ;; read the file as JSON
       (:name)) ;; get the value of "name"

  (let [x {:a "a" :b "b" :c "c"}]
    (select-keys x [:a])))

(defn filter-keyword [data keyword]
  (if (map? data)
    (->> data
         (remove #(= (key %) keyword))
         (map #(vector (key %) (filter-keyword (val %) keyword)))
         (into {}))
    data))

(def sample-data
  {:a 1
   :b {:c 2
       :d {:e 3
           :keyword "filtered"
           :f {:keyword "filtered"}}}})

(defn multiply [arg1 & argrest]
  (* arg1))
(multiply 1 2 3 4 5)

(def test-vector [1 2 3])
(def test-list '(1 2 3))

(println (map test-vector))
(println (list? test-list))

(def filtered-data (filter-keyword sample-data :keyword))
(println filtered-data)