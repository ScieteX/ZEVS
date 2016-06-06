(deftemplate av  (slot a) (slot v))
(deffunction ask-question (?question $?allowed-values)
   (printout t ?question crlf) 
   (bind ?answer (read)) 
   (if (lexemep ?answer)
      then
         (bind ?answer (lowcase ?answer)))
    (if (= ?answer \х)
        then
        (printout t "ОТМЕНЕНО ПОЛЬЗОВАТЕЛЕМ!!!" crlf)
            (halt)
            (reset)
            (clear)
        else
   (while (not (member$ ?answer ?allowed-values)) do
       (if (= ?answer \х)
        then
       (halt)
       (reset)
       (clear)
       (printout t "ОТМЕНЕНО ПОЛЬЗОВАТЕЛЕМ!!!" crlf)
       (break))
      (printout t ?question crlf)
      (bind ?answer (read))
      (if (lexemep ?answer)
         then
          (bind ?answer (lowcase ?answer)))))
     ?answer
)

(deffunction yes-or-no-p (?question)
(bind ?response (ask-question ?question да нет))
(if (or (eq ?response да) (eq ?response y)) 
then
TRUE 
else
FALSE)
)
(defrule answer-1
  (declare (salience 4))
 (not (av (a voltage   ) (v ?)))
  (not (result ?)) 
=>
(if (yes-or-no-p  "Напряжение больше 1000В? (да/нет) ? ")
then
(assert (av (a voltage) (v voltage_high)))
else 
(assert (av (a voltage) (v voltage_low)))
))

(defrule answer-2
  (declare (salience 6))
  (not (av (a work   ) (v ?)))
  (not (result ?)) 
=>
(assert (av (a work   ) (v (ask-question  "Работа должна выполняться по (наряду или распоряжению или тэ)? "  наряду  распоряжению тэ))))
)



(defrule answer-3
  (declare (salience -1))
 (not (av (a work_inst  ) (v ?)))
  (not (result ?)) 
=>
(if (yes-or-no-p  "Распоряжение на работу  оформлено? (да/нет) ? ")
then
(assert (av (a work_inst ) (v work_inst_yes)))
else 
(assert (av (a work_inst ) (v work_inst_no)))
))

(defrule answer-4
  (declare (salience 5))
 (not (av (a work_livery ) (v ?)))
  (not (result ?)) 
=>
(if (yes-or-no-p  "Наряд на работу оформлен? (да/нет) ? ")
then
(assert (av (a work_livery) (v work_livery_yes)))
else 
(assert (av (a work_livery) (v work_livery_no)))
))

(defrule answer-5
  (declare (salience 7))
 (not (av (a watch_man   ) (v ?)))
  (not (result ?)) 
=>
(if (yes-or-no-p  "Есть наблюдающий человек? (да/нет) ? ")
then
(assert (av (a watch_man) (v watch_man_yes)))
else 
(assert (av (a watch_man) (v watch_man_no)))
))

(defrule answer-6
  (declare (salience -1))
 (not (av (a director  ) (v ?)))
  (not (result ?)) 
=>
(if (yes-or-no-p  "Есть руководитель? (да/нет) ? ")
then
(assert (av (a director) (v director_yes)))
else 
(assert (av (a director) (v director_no)))
))

(defrule answer-7
  (declare (salience 8))
 (not (av (a target_briefing  ) (v ?)))
  (not (result ?)) 
=>
(if (yes-or-no-p  "У каждого работника есть отметка о целевом инструктаже? (да/нет) ? ")
then
(assert (av (a target_briefing) (v target_briefing_yes)))
else 
(assert (av (a target_briefing) (v target_briefing_no)))
))

(defrule answer-8
  (declare (salience 9))
 (not (av (a work_end_time  ) (v ?)))
  (not (result ?)) 
=>
(if (yes-or-no-p  "Не для каждого работника просрочена дата проверки знаний по охране труда? (да/нет) ? ")
then
(assert (av (a work_end_time) (v work_end_time_yes)))
else 
(assert (av (a work_end_time) (v work_end_time_no)))
))

(defrule result-1
       (and(av (a voltage) (v voltage_high))
           (av (a work   ) (v тэ))
           (av (a work_inst ) (v work_inst_no))
           (av (a work_livery) (v work_livery_no)))
        (not (result ?))
         =>
(assert (result "Работы проводить нельзя!!!"))
)

(defrule result-2
       (and(av (a voltage) (v voltage_high))
           (av (a work   ) (v наряду))
           (av (a watch_man) (v watch_man_no)))
        (not (result ?))
         =>
(assert (result "Работы проводить нельзя!!!"))
)
(defrule result-3
       (and(av (a voltage) (v voltage_high))
           (av (a work   ) (v наряду))
           (av (a director) (v director_no)))
        (not (result ?))
         =>
(assert (result "Работы проводить нельзя!!!"))
)

(defrule result-4
       (and(av (a work   ) (v наряду))
           (av (a work_livery) (v work_livery_no)))
        (not (result ?))
         =>
(assert (result "Работы проводить нельзя!!!"))
)

(defrule result-5
       (and(av (a work   ) (v распоряжению))
           (av (a watch_man) (v watch_man_no)))
        (not (result ?))
         =>
(assert (result "Работы проводить нельзя!!!"))
)

(defrule result-6
       (and(av (a work   ) (v распоряжению))
           (av (a work_inst ) (v work_inst_no)))
        (not (result ?))
         =>
(assert (result "Работы проводить нельзя!!!"))
)

(defrule result-7
       (exists(av (a target_briefing) (v target_briefing_no)))
        (not (result ?))
         =>
    (assert (result "Работы проводить нельзя!!!"))
)

(defrule result-8
       (exists(av (a work_end_time) (v work_end_time_yes)))
        (not (result ?))
         =>
    (assert (result "Работы проводить нельзя!!!"))
)
(defrule ok-result ""
    (declare (salience -10))
   (not (result ?)) 
   =>
   (assert (result "Всё в порядке. Необходимые работы можно выполнять."))
)


(defrule print-result ""
   (declare (salience 10))
   (result ?item)
    =>
   (printout	t	"Конечный результат:")
   (printout	t	crlf)
   (format t	"	%s%n%n%n" ?item)
)
(defrule system-banner "" 
(declare (salience 10) )
 => 
(printout t crlf crlf)
(printout t "***********************************************************" crlf) 
(printout t "          *Консультация по электробезопасности*" crlf) 
(printout t "***********************************************************" crlf) 
(printout t "Если необходимо завершить консультацию введите: \\х" crlf) 
(printout t crlf crlf)
)