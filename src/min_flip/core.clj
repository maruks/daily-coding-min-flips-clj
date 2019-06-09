(ns min-flip.core)

(defn- move-idx [str idx delta c]
  (loop [i (+ delta idx)]
    (if (or (neg? i) (>= i (count str)) (= c (str i)))
      i
      (recur (+ delta i)))))

(defn- min-flip [str idx-1 idx-2 flips]
  (if (< idx-2 idx-1)
    flips
    (let [next-idx-1 (move-idx str idx-1 1 \y)
          next-idx-2 (move-idx str idx-2 -1 \x)]
      (min (min-flip str idx-1 next-idx-2 (inc flips))
           (min-flip str next-idx-1 idx-2 (inc flips))))))

(defn flips-rec [s]
  (let [str   (into [] s)
        idx-1 (move-idx str -1 1 \y)
        idx-2 (move-idx str (count str) -1 \x)]
    (min-flip str idx-1 idx-2 0)))

(defn- count-chars [str c num result]
  (if (seq str)
    (recur (rest str) c (if (= (first str) c) (inc num) num) (conj result num))
    result))

(defn flips-2 [str]
  (let [x-nums (count-chars str \y 0 [])
        y-nums (reverse (count-chars (reverse str) \x 0 []))
        sums   (map + x-nums y-nums)]
    (reduce min sums)))
