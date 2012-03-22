(deftemplate tecleado
	(slot teclas)
)

(deftemplate sugerencia
	(slot palabra)
)

(deftemplate solucion
	(slot palabra)
)

;;--------------------- Reglas de palabras ---------------------

(defrule vacia
=>
(assert (tecleado (teclas (fetch "teclas_pulsadas"))))
)

(defrule sugerencias-7372
    (tecleado (teclas "7372"))
    =>
    (assert (sugerencia (palabra "sera")))
    (assert (sugerencia (palabra "pera")))
    (assert (sugerencia (palabra "sepa")))
    (assert (sugerencia (palabra "pesa")))
    (assert (sugerencia (palabra "pepa")))
)

;;....................................
(defrule solucion
    (sugerencia (palabra ?p))
    =>
    (assert (solucion (palabra ?p)))
    (store "palabra_sugerida" ?p)	
)