(ns session2.trycatch)

(defn trycatchdemo
  []
  (try
    (println "Let's try ..try catch exception handeling")
    (nth "Clojure" 8)
    (catch Exception e
      (println (str "exception occured: " (.getMessage e))) )
    (finally (println "test finished this is will printed always"))
    )
  )

(defn as-int
  [s]
  (try
    (newline)
    (println "this example will try to parse a string as a number")
    (Integer/parseInt s)
    (catch NumberFormatException e
      (.printStackTrace e))
    (finally
      (println "Note the exit code of the program is 0 : Attempted to parse as integer: " s))))

(defn as-int_withmsg
  [s]
  (try
    (newline)
    (println "this example will try to parse a string as a number")
    (Integer/parseInt s)
    (catch Exception e
      (println (str "exception occured: " (.getMessage e))) )
    (finally
      (println "Attempted to parse as integer: " s))))

(defn dividebyzero
  []
  (try
    (/ 1 0)
    (catch java.lang.ArithmeticException e
      (println (str "Exception occured : " (.getMessage e)))
      ))
  )

(defn addTwoNumbers
  [a b]
  (+ a b))

(defn run_examples
  []
  (trycatchdemo)
  (as-int "123456")
  (as-int "No numbers so stack trace will be printed")
  (as-int_withmsg "This string has no numbers")
  (dividebyzero)
  (println (addTwoNumbers 1 2 ))

  )