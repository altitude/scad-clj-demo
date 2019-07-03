(ns scad-clj-demo.core
  (:use scad-clj.model)
  (:use scad-clj.scad))

(defn circleify
  [radius count object]
  (let
    [angle (/ (* 2 Math/PI) count)
     points (range count)]
    (map (fn [i]
      (->>
       object
       (translate [0 radius 0])
       (rotate (* i angle) [0 0 1]))) points)))

(def holes
  (->>
    (cylinder 10 10)
    (circleify 50 6)))

(def primitives
  (difference
    (cylinder 100 2)
    holes))

(spit "demo.scad" (write-scad primitives))
