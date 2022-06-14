
(clear-all)

(defun doit ()
  (load "c:/Users/mpmatess/Desktop/ACT-R/learning-rules.lisp")
  (setf w-l-g-lis nil)
  (setf nw-l-g-lis nil)
  (setf w-h-g-lis nil)
  (setf nw-h-g-lis nil)
  (run 10)
  (show)
)

(defun show ()
  (format t "warn low gps:~&")
  (dolist (i (reverse w-l-g-lis)) (print i))
  (format t "no warn low gps:~&")
  (dolist (i (reverse nw-l-g-lis)) (print i))
  (format t "warn high gps:~&")
  (dolist (i (reverse w-h-g-lis)) (print i))
  (format t "no warn high gps:~&")
  (dolist (i (reverse nw-h-g-lis)) (print i))
)

(defun show ()
  (format t "Warn Low~aNoWarn Low~aWarn High~aNoWarn High~&" #\tab #\tab #\tab)
  (dotimes (i (1- (length w-l-g-lis)))
    (format t "~a~a~a~a~a~a~a~a~&" 
	    (nth i (reverse w-l-g-lis)) #\tab
	    (nth i (reverse nw-l-g-lis)) #\tab
	    (nth i (reverse w-h-g-lis)) #\tab
	    (nth i (reverse nw-h-g-lis)) #\tab
	    )))

(defun avg (lis)
  (float (/ (apply '+ (remove nil lis)) (length (remove nil lis)))))

(define-model learn

(sgp :v t :esc t 
:egs 3
:show-focus t :ul t :ult t :needs-mouse t)

(chunk-type eval-sensor state)

(define-chunks 
    (goal isa eval-sensor state low))

(p warn-low-gps
   =goal>
   isa eval-sensor
   state low
==>
   =goal>
   state warn-low
)

(p no-warn-low-gps
   =goal>
   isa eval-sensor
   state low
==>
   =goal>
   state do-not-warn-low
)

(p reward-no-warn-low-gps
   =goal>
   isa eval-sensor
   state do-not-warn-low
==>
!eval! (push (floor (caar (spp warn-low-gps :u))) w-l-g-lis)
!eval! (push (floor (caar (spp no-warn-low-gps :u))) nw-l-g-lis)
!eval! (trigger-reward 'none)
   =goal>
   state high
)

(p no-reward-warn-low-gps
   =goal>
   isa eval-sensor
   state warn-low
==>
!eval! (push (floor (caar (spp warn-low-gps :u))) w-l-g-lis)
!eval! (push (floor (caar (spp no-warn-low-gps :u))) nw-l-g-lis)
   =goal>
   state high
)

(p warn-high-gps
   =goal>
   isa eval-sensor
   state high
==>
   =goal>
   state warn-high
)

(p no-warn-high-gps
   =goal>
   isa eval-sensor
   state high
==>
   =goal>
   state do-not-warn-high
)

(p reward-warn-high-gps
   =goal>
   isa eval-sensor
   state warn-high
==>
!eval! (push (floor (caar (spp warn-high-gps :u))) w-h-g-lis)
!eval! (push (floor (caar (spp no-warn-high-gps :u))) nw-h-g-lis)
   =goal>
   state low
)

(p no-reward-no-warn-high-gps 
   =goal>
   isa eval-sensor
   state do-not-warn-high
==>
!eval! (push (floor (caar (spp warn-high-gps :u))) w-h-g-lis)
!eval! (push (floor (caar (spp no-warn-high-gps :u))) nw-h-g-lis)
!eval! (trigger-reward 'none)
   =goal>
   state low
)

(goal-focus goal)

(spp warn-low-gps :u 50)
(spp no-warn-low-gps :u 50)
(spp warn-high-gps :u 50)
(spp no-warn-high-gps :u 50)

; pilot gives no feedback if not warned
(spp no-reward-warn-low-gps :reward 0)
(spp reward-warn-high-gps :reward 100)


)
