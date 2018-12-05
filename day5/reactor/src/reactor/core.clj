(ns reactor.core
  (:require [clojure.string :as str]))

(defn read-file-and-return-list [filename]
  (str/split (slurp filename) #""))

(defn should-react [a b]
  (or 
    (and (= a (str/upper-case a))
         (= (str/lower-case a) b))
    (and (= a (str/lower-case a))
         (= (str/upper-case a ) b))))

(defn react [poly iteration]
  (if (= iteration 0) 
    (subvec poly 2)
    (vec 
      (concat 
        (subvec poly 0 iteration) 
        (subvec poly (inc iteration)))
        )))

(defn recursive-reactor [poly iteration reactionCount]
  (let [firstUnit (get poly iteration)
        nextUnit (get poly (+ iteration 1))]
        (if (= (count poly) iteration)
          poly
          (if (should-react firstUnit nextUnit) 
            (recursive-reactor (react poly iteration) (if (not (= iteration 0)) (- iteration 1) iteration) (inc reactionCount))
            (recursive-reactor poly (inc iteration) reactionCount)))))

(defn -main []
  (recursive-reactor (read-file-and-return-list "polymer.txt") 0 0))