(ns celebrity-name-smash.core
  (:require [clojure.string :as str]))

;; celebrites have 2 names, seperated by a space
(def celebs ["Brad Pitt" "Angelina Jolie"])


(defn name-split
  "Splits a celebrity name into their first & last names"
  [name]
  (str/split name #" "))

;; split in action
(clojure.string/split "Clojure is awesome!" #" ")

(name-split "Brad Pitt")

;; =>["Brad" "Pitt"]


;; sub-dividing a name

;; (count "Brad")

(take 2 "Brad")
;; => (\B \r)

(take (/ (count "Brad") 2) "Brad")
;; => (\B \r)

(apply str (take (/ (count "Brad") 2) "Brad"))
;; => "Br"

(count "Bradley")
;; => 7

(/ (count "Bradley") 2)
;; => 7/2

(take (/ (count "Bradley") 2) "Bradley")
;; => (\B \r \a \d)

(apply str (take (/ (count "Bradley") 2) "Bradley"))
;; => "Brad"



(str (take 2 "Brad"))
;; => "clojure.lang.LazySeq@c31"
;; - returns lazy sequence, need to apply or reduce

(apply str (take 2 "Brad"))

(reduce str (take 2 "Brad"))

;; Although this works it seems a little cumbersome and a little more involved to read.
;; lets use a substring instead
(subs "Brad" 0 2)
;; => "Br"

(subs "Brad" 2)
;; => "ad"

(subs "Bradley" 0 (/ (count "Bradley") 2))
;; => "Bra"

#_(defn first-celeb-subname [name]
  (let [end (/ (count name) 2)]
    (subs name 0 end)))

#_(defn first-celeb-subname [name]
    (subs name 0 (+ 1 (rand-int (count name)))))

#_(defn first-celeb-subname [name]
  (let [end (+ 1 (rand-int (count name)))]
    (subs name 0 end)))

(defn first-celeb-subname [name]
  (subs name 0 (inc (rand-int (count name)))))




(first-celeb-subname "Bradley")
;; => "Bra"


;; (first-subname "Brad" (quot (count "Brad") 2))

(quot (count "Bradley") 2)
;; => 3

#_(defn second-celeb-subname [name]
  (subs name (+ 1 (rand-int (count name)))))

#_(defn second-celeb-subname [name]
  (subs name (inc (rand-int (count name)))))

(defn second-celeb-subname [name]
  (let [start (inc (rand-int (count name)))]
    (subs name start)))

;; (second-subname "Angelina")
;; (second-subname "Angelina" (quot (count "Angelina") 2))


#_(defn celeb-name-smash
  "Smashes to celebrity names together"
  [celeb-one celeb-two]
  (str (first-celeb-subname (first (name-split celeb-one)))
       (second-celeb-subname (first (name-split celeb-two)))
       " "
       (first-celeb-subname (last (name-split celeb-one)))
       (second-celeb-subname (last (name-split celeb-two)))))

;; call the celeb-name-smash function with example data
#_(name-smash-strings "Brad Pitt" "Angelina Jolie")
;; => "Brana Pitie"
;; => "Brelina Pie"
;; => "Brelina Piolie"
;; => "Brlina Pi"


;; Creating something for random celebrity couples

(defn celeb-name-smash
  "Smashes to celebrity names together, given a vector of celebrity pairs"
  [celebs]
  (let [celeb-one (first celebs)
        celeb-two (last celebs)]
    (str (first-celeb-subname (first (name-split celeb-one)))
         (second-celeb-subname (first (name-split celeb-two)))
         " "
         (first-celeb-subname (last (name-split celeb-one)))
         (second-celeb-subname (last (name-split celeb-two))))))

;; a collection of celebrity couples we can randomly select from
(def celeb-couples [["Brad Pitt" "Angelina Jolie"]
                    ["Johnny Depp" "Amber Heard"]])

;; return a random element from a collection
(rand-nth celeb-couples)

(celeb-name-smash (rand-nth celeb-couples))
;; => "Bina Pitt"
;; => "Braa Pitolie"
;; => "Jmber Deppeard"
;; => "Jer Depeard"

;; Call the celebrity name smash with a specific couple
#_(celeb-name-smash ["Sheldon Cooper" "Amy Farrah-Fowler"])
