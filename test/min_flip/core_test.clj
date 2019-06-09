(ns min-flip.core-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [min-flip.core :refer :all]))

(deftest flips-test
  (is (= 2 (flips-rec "xyxxxyxyy")))
  (is (= 2 (flips-2 "xyxxxyxyy"))))

(defspec flips-gen-test
  100
  (prop/for-all [v (gen/vector (gen/elements [\x \y]) 1 30)]
    (= (flips-rec v) (flips-2 v))))
