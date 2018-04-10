(ns session2.atomagent)

;; Atom : As we know variables are immutable in clojure, which means we can not modify it once value is assigned to them.
;; Using atom we can change variable's value in clojure.

(defn atomDemo
  [x]
  (def myAtom (atom x))                                     ;;to define atom

  (add-watch myAtom :watcher                                ;;add-watch is optional.
             (fn [key atom old-state new-state]
               (println "myAtom changed from" old-state "to" new-state)))

  (println "Initial value of myAtom: " @myAtom)
  (reset! myAtom 20)                                        ;;to modify atom value
  (println "After reset, value of myAtom: " @myAtom)
  (swap! myAtom inc)                                        ;;to change value using functions
  (println "After swap, value of myAtom: " @myAtom)
  )

;;===================================================
;;===================================================


;;Agent: Like Atoms, agent is also used to change variable's value using functions.
(defn agentDemo
  [x]
  (def myAgent (agent x))
  (println "Initial value of myAgent: " @myAgent)
  (send myAgent + 10) ;;to change value of agent
    ;;send function takes time to update agent value.
  (await-for 1000 myAgent)
  ;;Thus, use await-for to provide wait time for update of variable's value to new value.
  (println "New Value of myAgent: " @myAgent)
  )
        ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defrecord Person [fname lname address])
(defrecord Address [street city state zip])


(defrecord Book [Author Title])
(defn libexample []

(def student (Person. "Vishal" "Mavani"
                      (Address. "172 Clarken Drive"
                                "West Orange"
                                "NJ"
                                07052)))

(println (:lname student))
(println (-> student :fname))

(println (-> student :address :city))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  (def Library (ref []))
(def b1 (Book. "a1" "t1"))
(def b2 (Book. "a2" "t2"))
(def b3 (Book. "a3" "t3"))
(prn "b1 is ==>" b1)
(prn "b2 is ==>" b2)
(prn "b3 is ==>" b3)

(dosync (alter Library conj b1 b2 b3))                      ;;synchronously change state of the reference - dosync and alter
(prn "library is ==>" (deref Library))
(dosync (alter Library conj b1 b2 b3))

(def b4 (Library 0))                                        ;;b4 = b1
(prn "borrowed b4 is b1 ==>" b4)
(def b4 (assoc b4 :Title "a4"))                             ;;we are updating existing b4's title to a4 using association
(prn "modified b4 is ==>" b4)
(dosync (alter Library assoc-in [0] b4))                    ;;dosync will perform synchronously operations across multithreaded environment.
(prn "updated library ==>" (deref Library))
  )