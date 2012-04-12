
;;;======================================================
;;;   Recomendador de puestos de trabajo
;;;
;;;     Practica 2
;;;
;;;     Inteligencia Artificial e Ingeniería del Conocimiento
;;;
;;;     Para ejecutar en jess previa carga y definicion de deffacts.
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
    (slot edad (type INTEGER))
    (slot estudios (type SYMBOL) (allowed-values bachCiencias bachLetras ESO UNI))
    (slot acabada (type SYMBOL) (allowed-values SI NO))
	;para trabajo
    (slot tipo (type SYMBOL) (allowed-values tecnico letras))
    (slot docente (type SYMBOL) (allowed-values SI NO))
    (slot investigador (type SYMBOL) (allowed-values SI NO))
    (slot puesto (type SYMBOL) (allowed-values miembro directivo becario))
    (slot duracion (type INTEGER))
    (slot empresa (type SYMBOL) (allowed-values peque media grande)))

; ********
; DEFFACTS
; ********

(deffacts initial-phase
    (phase CURRI))

;Modificar este deffacts para cambiar resultado de ejecucion
(deffacts info (curriculum (nombre Luis)(apellidos Gomez)(edad 15)(estudios bachCiencias)(duracion 0)))

; *****
; RULES
; *****

; *************
; FASE Curriculum
; *************

; RULE bachCiencias_uni-fps
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

; RULE bachLetras_uni-fps
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

; RULE UNI_becario
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

; RULE UNI_titulado
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

; RULE pr_uni-fps_ciencias
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
    ?h <-(phase ESTU)
    (lv UNI-FPS-letras)
    =>
    (retract ?h)
    (printout t "Ofertas relacionadas con enseñanzas de letras de estudios universitarios y formacion profesional de grado superior" crlf))

; RULE pr_bach-fpm
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

; RULE pr_tit_prof
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria
;   ademas ejerce algun tipo de docencia y como investigador
; THEN
;   elimina fase
;	muestra opciones de docencia e investigacion

(defrule pr_tit_prof
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {docente == SI || investigador == SI})
    =>
    (retract ?h)
    (printout t "Ofertas de docencia e investigacion para titulados" crlf))

; RULE pr_tit_tec_expBaja_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es tipo tecnico, ha estado trabajando en una empresa pequeña con poca experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia baja y empresa pequeña

(defrule pr_tit_tec_expBaja_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == peque})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnicos con baja experiencia que han trabajado incluso de becario en empresas pequeñas" crlf))

; RULE pr_tit_tec_expMedia_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando en una empresa pequeña con experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia  y empresa pequeña

(defrule pr_tit_tec_expMedia_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && puesto == miembro && duracion > 5 && duracion < 10 && empresa == peque})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnicos con experiencia que han trabajado en empresas pequeñas" crlf))

; RULE pr_tit_tec_expAlta_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando en una empresa pequeña con gran experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia alta y empresa pequeña

(defrule pr_tit_tec_expAlta_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == miembro || puesto == directivo) && duracion > 10 && empresa == peque})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnicos con alta experiencia que han trabajado incluso como directivo en empresas pequeñas" crlf))


; RULE pr_tit_tec_expBaja
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando ya en una empresa media de becario pero con poca experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia baja en empresas medianas

(defrule pr_tit_tec_expBaja
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == media})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnicos con poca experiencia que han trabajado incluso de becario en empresas medias" crlf))

; RULE pr_tit_tec_expMedia
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando ya en una empresa media y tiene cierta experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia media en empresas medianas

(defrule pr_tit_tec_expMedia
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && puesto == miembro && duracion > 5 && duracion < 10 && empresa == media})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnicos con experiencia media que han trabajado en empresas medianas" crlf))

; RULE pr_tit_tec_expAlta
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando ya en una empresa media incluso como directivo y tiene mucha experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia alta en empresas medianas

(defrule pr_tit_tec_expAlta
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == miembro || puesto == directivo) && duracion > 10 && empresa == media})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnicos con experiencia alta que han trabajado incluso como directivo en empresas medias" crlf))

; RULE pr_tit_tec_expBaja_gran
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando ya en una empresa grande pero con poca experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia baja en grandes empresas

(defrule pr_tit_tec_expBaja_gran
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == grande})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnicos con poca experiencia que han trabajado incluso de becario en empresas grandes" crlf))

; RULE pr_tit_tec_expMedia_gran
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando ya en una empresa grande y tiene cierta experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia media en grandes empresas

(defrule pr_tit_tec_expMedia_gran
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && puesto == miembro && duracion > 5 && duracion < 10 && empresa == grande})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnicos con experiencia media que han trabajado en empresas grandes" crlf))

; RULE pr_tit_tec_expAlta_gran
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando ya en una empresa grande incluso como directivo y tiene mucha experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia alta en grandes empresas

(defrule pr_tit_tec_expAlta_gran
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == miembro || puesto == directivo) && duracion > 10 && empresa == grande})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnico con experiencia alta que han trabajado incluso como directivo en empresas grandes" crlf))

; RULE pr_tit_let_expBaja_peq
; IF
;   La fase es WORKy el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es tipo de letras y ha estado trabajando en una empresa pequeña con poca experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia baja y empresa pequeña

(defrule pr_tit_let_expBaja_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == peque})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados de letras con baja experiencia que han trabajado incluso de becario en empresas pequeñas" crlf))

; RULE pr_tit_let_expMedia_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando en una empresa pequeña con experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia  y empresa pequeña

(defrule pr_tit_let_expMedia_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && puesto == miembro && duracion > 5 && duracion < 10 && empresa == peque})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados tecnicos con experiencia que han trabajado en empresas pequeñas" crlf))

; RULE pr_tit_let_expAlta_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando en una empresa pequeña con gran experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia alta y empresa pequeña

(defrule pr_tit_let_expAlta_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && (puesto == miembro || puesto == directivo) && duracion > 10 && empresa == peque})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados de letras con alta experiencia que han trabajado incluso como directivo en empresas pequeñas" crlf))


; RULE pr_tit_let_expBaja
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando ya en una empresa media de becario pero con poca experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia baja en empresas medianas

(defrule pr_tit_let_expBaja
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == media})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados de letras con poca experiencia que han trabajado incluso de becario en empresas medias" crlf))

; RULE pr_tit_tec_expMedia
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando ya en una empresa media y tiene cierta experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia media en empresas medianas

(defrule pr_tit_let_expMedia
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && puesto == miembro && duracion > 5 && duracion < 10 && empresa == media})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados de letras con experiencia media que han trabajado en empresas medianas" crlf))

; RULE pr_tit_let_expAlta
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando ya en una empresa media incluso como directivo y tiene mucha experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia alta en empresas medianas

(defrule pr_tit_let_expAlta
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && (puesto == miembro || puesto == directivo) && duracion > 10 && empresa == media})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados de letras con experiencia alta que han trabajado incluso como directivo en empresas medias" crlf))

; RULE pr_tit_let_expBaja_gran
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando ya en una empresa grande pero con poca experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia baja en grandes empresas

(defrule pr_tit_let_expBaja_gran
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == grande})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados de letras con poca experiencia que han trabajado incluso de becario en empresas grandes" crlf))

; RULE pr_tit_tec_expMedia_gran
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando ya en una empresa grande y tiene cierta experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia media en grandes empresas

(defrule pr_tit_let_expMedia_gran
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && puesto == miembro && duracion > 5 && duracion < 10 && empresa == grande})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados de letras con experiencia media que han trabajado en empresas grandes" crlf))

; RULE pr_tit_let_expAlta_gran
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando ya en una empresa grande incluso como directivo y tiene mucha experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia alta en grandes empresas

(defrule pr_tit_let_expAlta_gran
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && (puesto == miembro || puesto == directivo) && duracion > 10 && empresa == grande})
    =>
    (retract ?h)
    (printout t "Ofertas para titulados de letras con experiencia alta que han trabajado incluso como directivo en empresas grandes" crlf))

(reset)
(run)
