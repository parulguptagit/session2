(ns session2.core
  (:require [session2.trycatch :as trycatch]
            [session2.atomagent :as atm])
  (:gen-class)
)

(defprotocol Shape
  (area [s] "Calculate the area of a shape")
  (perimeter [s] "Calculate the perimeter of a shape"))
;; Define a concrete Shape, the Rectangle
(defrecord Rectangle [length width]
  Shape
  (area [this] (* length width))
  (perimeter [this] (+ (* 2 length)
                       (* 2 width))))
(defrecord Triangle [base height ]
  Shape
  (area [this] (* 0.5  (* base height)))
  (perimeter [this] (+ (* 2 base)
                       (* 2 height))))
(->Rectangle 2 4)

(println (area (->Rectangle 2 4)) )
(println (area (->Triangle 2 4)) )

;; defmulti

(defmulti area
          "Calculate the area of a shape"
          :type)
(defmethod area :rectangle [shape]
  (* (:length shape) (:width shape)))
(println (area {:type :rectangle :length 4 :width 8}))

;; Trying to get the area of a new shape...
;;-------------------------------------------------(area {:type :circle :radius 1})
;; -> IllegalArgumentException No method in multimethod 'area' for
;; dispatch value: :circle ...
(defmethod area :circle [shape]
  (* (. Math PI) (:radius shape) (:radius shape)))
(println (area {:type :circle :radius 10}))
;;Better, but things start to fall apart if you want to add new geometric functions like
;;perimeter. With multimethods you’ll need to repeat dispatch logic for each function and
;;write a combinatorial explosion of implementations to suit.
;;----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

;;extend one of the built-in types with your own functions


;;In this example, you will add a first-name and a last-name function to String

(defprotocol Person
  "Represents the name of a person."
  (first-name [person])
  (last-name [person]))
;; Extend the type to the java.lang.String class:
(extend-type String
  Person
  (first-name [s] (first (clojure.string/split s #" ")))
  (last-name [s] (second (clojure.string/split s #" "))))
;; Now you can invoke your functions on strings:
(println (first-name "Robert john") )
(println (last-name "john smith"))

;; Define a concrete Shape, the Rectangle

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")

  (println args)
  (println (first args))
  (println (rest args))

  ;;;;;;;;;;;;;;;;;;;;;;Demo for method overriding ;;;;;;;;;;;;;;;;;;;

  ;; formula for area is side * side = a2
  ;;shape is pulled from defprotocol mentioned outside the main function as we are implementing polymorphism
  ;; this is used to refer to the current argument passed for method square






  ;;;;;;;;;;;;;;;;;;Demo for atoms agents and refs ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  (atm/atomDemo 10)
   (atm/agentDemo 5)
   (atm/libexample)
  ;;;;;;;;;;;;Demo for try catch error exception handling ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
   (trycatch/run_examples)
  )