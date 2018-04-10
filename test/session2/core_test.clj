(ns session2.core-test
  (:require [clojure.test :refer :all]
            [session2.core :refer :all]
            [session2.trycatch :refer :all]))


(deftest several-tests
  (testing "mathematical operations"
    (testing "addition"
      (is (= 5 (addTwoNumbers 2 3)))
      (is (= 0 (addTwoNumbers -1 1 )))
      (is (= 4 (addTwoNumbers -5 1 ))))
    )
  (testing "exceptions"
    (is (thrown? ArithmeticException (/ 1 0)))
    (is (thrown? ArithmeticException (/ 1 1)))
    (is (thrown-with-msg? ArithmeticException #"Divide by zero"
                          (/ 1 0)))
    (is (thrown-with-msg? ArithmeticException #"memory"
                          (/ 1 0)))))



(run-all-tests)
;(run-tests 'session2.core-test)