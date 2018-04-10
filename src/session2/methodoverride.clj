(ns session2.methodoverride)
(defprotocol Shape
  (area [s] "Calculate the area of a shape")
  (perimeter [s] "Calculate the perimeter of a shape"))
(defrecord Square [side]
  Shape ;
  (area [this] (* side side))
  (perimeter [this] (* 4 side))
  )
;; we are printing the values of area and perimeter by referencing to the shape of square with an argument to match
 (area (->Square 1))
(perimeter (->Square 1))

;; Calculating the area of a parallelogram without defining a record by extending the parent class properties of Shape
;; formula for parallelogram is height * breadth

(area
  (let [breadth 2
        height 3]
    (reify Shape
      (area [this] (* breadth height))
      (perimeter [this] ( * 2 (+ breadth height))))))