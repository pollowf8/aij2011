
;;;======================================================
;;;   Recomendador de puestos de trabajo
;;;
;;;     Practica 2
;;;
;;;     Inteligencia Artificial e Ingeniería del Conocimiento
;;;
;;;     To execute, merely load, reset and run.
;;;======================================================


; **************
; FACT TEMPLATES
; **************

; El estado en el que se encuentra el recomendador

; (phase
;    <state>)      ;CURRI,ESTU,WORK

; El nivel de estudios a mostrar

; (lv
;    <nivel>)      ;UNI-FPS-ciencias, UNI-FPS-letras, BACH-FPM, BECARIO, TITULADO

; El template para el curriculum

(deftemplate curriculum "Representa la informacion del curriculum"
    (slot nombre (type SYMBOL) )
    (slot apellidos (type SYMBOL))
    (slot edad (type NUMBER))
    (slot estudios (type SYMBOL) (allowed-values bachCiencias bachLetras ESO UNI))
    (slot carrera (type SYMBOL))
    (slot acabada (type SYMBOL) (allowed-values SI NO))
    ;para elegir trabajo
    (slot docente (type SYMBOL) (allowed-values SI NO))
    (slot investigador (type SYMBOL) (allowed-values SI NO))
    (slot puesto (type SYMBOL) (allowed-values miembro directivo becario))
    (slot duracion (type NUMBER))
    (slot empresa (type SYMBOL) (allowed-values peque media grande)))

; ********
; DEFFACTS
; ********

(deffacts initial-phase
    (phase CURRI))

;(deffacts info (curriculum (nombre Pepe)(apellidos Gomez)(edad 15)(estudios bachCiencias)))
(deffacts info (curriculum (nombre Pepe)(apellidos Gomez)(edad 30)
        (estudios UNI)(acabada SI)(puesto miembro)(duracion 8)(empresa media)))
; *****
; RULES
; *****

; *************
; FASE Curriculum
; *************

; RULE bachCiencias-uni-fps
; IF
;   La fase es curriculum, tiene menos de 18 años y un bachillerato de ciencias
; THEN
;   pasa a fase estudios
;   con el nivel de universitario-FP superior en ciencias

(defrule bachCiencias_uni-fps
    ?h <-(phase CURRI)
    (curriculum {edad < 18 && estudios == bachCiencias})
    =>
    (retract ?h)
    (assert (phase ESTU))
    (assert (lv UNI-FPS-ciencias)))

; RULE bachLetras-uni-fps
; IF
;   La fase es curriculum, tiene menos de 18 años y un bachillerato de letras
; THEN
;   pasa a fase estudios
;   con el nivel de universitario-FP superior en letras

(defrule bachLetras_uni-fps
    ?h <-(phase CURRI)
    (curriculum {edad < 18 && estudios == bachLetras})
    =>
    (retract ?h)
    (assert (phase  ESTU))
    (assert (lv UNI-FPS-letras)))

; RULE ESO-bach-fpm
; IF
;   La fase es curriculum, tiene menos de 18 años y la ESO
; THEN
;   pasa a fase estudios
;   con el nivel de universitario-FPM

(defrule ESO_bach-fpm
    ?h <-(phase CURRI)
    (curriculum {edad < 18 && estudios == ESO})
    =>
    (retract ?h)
    (assert (phase  ESTU))
    (assert (lv BACH-FPM)))

; RULE UNI-becario
; IF
;   La fase es curriculum, tiene entre 18 y 23 años y esta estudiando una carrera
; THEN
;   pasa a fase de trabajo
;   con el nivel de becario

(defrule UNI_becario
    ?h <-(phase CURRI)
    (curriculum {edad > 18 && edad < 23 && estudios == UNI && acabada == NO})
    =>
    (retract ?h)
    (assert (phase  WORK))
    (assert (lv BECARIO)))

; RULE UNI-titulado
; IF
;   La fase es curriculum, tiene mas de 21 años y ya ha terminado una carrera
; THEN
;   pasa a fase de trabajo
;   con el nivel de titulado

(defrule UNI_titulado
    ?h <-(phase CURRI)
    (curriculum {edad > 21 && estudios == UNI && acabada == SI})
    =>
    (retract ?h)
    (assert (phase  WORK))
    (assert (lv TITULADO)))

; *************
; FASE Estudios
; *************

; RULE pr_tit-fps_ciencias
; IF
;   La fase es ESTU y el nivel se ha obtenido de un bachillerato de ciencias
; THEN
;   elimina fase
;	muestra opciones de estudios universitarios y de FPS tecnicos 

(defrule pr_uni-fps_ciencias
    ?h <-(phase ESTU)
    (lv UNI-FPS-ciencias)
    =>
    (retract ?h)
    (printout t "Ofertas relacionadas con enseñanzas tecnicas de estudios universitarios y formacion profesional de grado superior" crlf))


; RULE pr_uni-fps_letras
; IF
;   La fase es ESTU y el nivel se ha obtenido de un bachillerato de letras
; THEN
;   elimina fase
;	muestra opciones de estudios universitarios y de FPS de letras 

(defrule pr_uni-fps_letras
    ?h <-(phase WORK)
    (lv UNI-FPS-letras)
    =>
    (retract ?h)
    (printout t "Ofertas relacionadas con enseñanzas letras de estudios universitarios y formacion profesional de grado superior" crlf))

; RULE pr_bach_fpm
; IF
;   La fase es ESTU y el nivel se ha obtenido de la ESO
; THEN
;   elimina fase
;	muestra opciones de bachillerato y de FPM 

(defrule pr_bach-fpm
    ?h <-(phase ESTU)
    (lv BACH-FPM)
    =>
    (retract ?h)
    (printout t "Ofertas relacionadas con enseñanzas de bachillerato y formacion profesional de grado medio" crlf))


; *************
; FASE Work
; *************

; RULE pr_becario
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria sin acabar
; THEN
;   elimina fase
;	muestra opciones de becario

(defrule pr_becario
    ?h <-(phase WORK)
    (lv BECARIO)
    =>
    (retract ?h)
    (printout t "Ofertas de becario para estudiantes universitarios" crlf))

; RULE pr_tit-prof
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria sin acabar
; THEN
;   elimina fase
;	muestra opciones de docencia e investigacion

(defrule pr_tit-prof
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {docente == SI || investigador == SI})
    =>
    (retract ?h)
    (printout t "Ofertas de docencia e investigacion para titulados" crlf))


; RULE pr_tit_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas ha estado trabajando en una empresa pequeña durante bastante tiempo
; THEN
;   elimina fase
;	muestra opciones de titulado con experiencia baja

(defrule pr_tit_peq
   ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {puesto == miembro && duracion > 5 && empresa == peque})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados con experiencia que han trabajado en empresas pequeñas" crlf))


(defrule pr_tit_expBaja
   ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {puesto == becario && duracion < 2 && empresa == media})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados con poca experiencia que han trabajado de becario" crlf))

; RULE pr_tit_expMedia
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas ha estado trabajando ya en una empresa media y tiene cierta experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado con experiencia media

(defrule pr_tit_expMedia
   ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {puesto == miembro && duracion > 5 && empresa == media})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados con experiencia media" crlf))

; RULE pr_tit_expAlta
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas ha estado trabajando ya en una empresa media incluso como directivo y tiene mucha experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado con experiencia alta

(defrule pr_tit_expAlta
   ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {(puesto == miembro || puesto == directivo) && duracion > 10 && empresa == media})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados con experiencia alta" crlf))

; RULE pr_tit_gran
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas ha estado trabajando ya en una empresa grande incluso como directivo y tiene mucha experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado con experiencia alta

(defrule pr_tit_gran
   ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {(puesto == miembro || puesto == directivo) && duracion > 15 && empresa == grande})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados con experiencia en grandes empresas" crlf))

(reset)
(run)
