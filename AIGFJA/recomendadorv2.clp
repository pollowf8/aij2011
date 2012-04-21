
;;;======================================================
;;;   Recomendador de puestos de trabajo
;;;
;;;     Practica 2 V2
;;;
;;;     Inteligencia Artificial e Ingenier�a del Conocimiento
;;;
;;;     Para uso con JAVA
;;;======================================================


; **************
; FACT TEMPLATES
; **************

; El estado en el que se encuentra el recomendador

; (phase
;    <state>)      ;CURRI,ESTU,WORK

; Informacion complementaria para el paso entre estados

; (lv
;    <nivel>)      ;UNI-FPS-ciencias, UNI-FPS-letras, BACH-FPM, BECARIO, TITULADO

; El template para el curriculum

(deftemplate curriculum "Representa la informacion del curriculum"
    (slot nombre (type SYMBOL) )
    (slot apellidos (type SYMBOL))
    (slot edad (type INTEGER))
    (slot estudios (type SYMBOL) (allowed-values bachCiencias bachLetras ESO UNI))
    (slot acabada (type SYMBOL) (allowed-values SI NO))
    ;para elegir trabajo
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

(deffacts info (curriculum (nombre (fetch "nombre"))(apellidos (fetch "apellidos"))(edad (fetch "edad"))
        (estudios (fetch "estudios"))(acabada (fetch "acabada"))(tipo (fetch "tipo"))(docente (fetch "docente"))
        (investigador (fetch "investigador"))(puesto (fetch "puesto"))(duracion (fetch "duracion"))(empresa (fetch "empresa"))))
; *****
; RULES
; *****

; *************
; FASE Curriculum
; *************

; RULE bachCiencias_uni-fps
; IF
;   La fase es curriculum, tiene menos de 18 a�os y un bachillerato de ciencias
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
;   La fase es curriculum, tiene menos de 18 a�os y un bachillerato de letras
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

; RULE ESO_bach-fpm
; IF
;   La fase es curriculum, tiene menos de 18 a�os y la ESO
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
;   La fase es curriculum, tiene entre 18 y 23 a�os y esta estudiando una carrera
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
;   La fase es curriculum, tiene mas de 21 a�os y ya ha terminado una carrera
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
    (store "resultado" "Ofertas relacionadas con ense�anzas tecnicas de estudios universitarios y formacion profesional de grado superior:

-Universitarios-
Grado en Ciencias Ambientales
Grado en Matem�ticas
Grado en Qu�mica
Grado en Enfermer�a
Grado en Fisioterapia
Grado en Ingenier�a Agr�cola 
Grado en Ingenier�a Electr�nica Industrial
Grado en Ingenier�a Inform�tica
Grado en Ingenier�a Mec�nica
Grado en Ingenier�a Qu�mica Industrial

-Formacion Profesional-
.Electricidad y Electr�nica.
Desarrollo de Productos Electr�nicos
Instalaciones Electrot�cnicas
Sistemas de Regulaci�n y Control Autom�ticos
Sistemas de Telecomunicaci�n e Inform�ticos
Sistemas Electrot�cnicos y Automatizados
Sistemas de Telecomunicaciones e Inform�ticos
Automatizaci�n y Rob�tica Industrial
Mantenimiento Electr�nico

.Fabricaci�n Mec�nica.
Producci�n por fundici�n y pulvimetalurgia
Desarrollo de proyectos mec�nicos
Programaci�n de la producci�n en fabricaci�n mec�nica
Construcciones met�licas
�ptica de Anteojer�a
Dise�o en fabricaci�n mec�nica
Programaci�n de la producci�n en moldeo de metales y pol�meros

.Imagen y Sonido.
Producci�n de audiovisuales, radio y espect�culos
Realizaci�n de audiovisuales y espect�culos
Sonido
Imagen
Animaciones 3D, juegos y entornos interactivos
Iluminaci�n, captaci�n y tratamiento de la imagen
Producci�n de audiovisuales y espect�culos
Realizaci�n de proyectos audiovisuales y espect�culos
Sonido para audiovisuales y espect�culos

.Inform�tica y Comunicaciones.
Administraci�n de sistemas inform�ticos
Desarrollo de aplicaciones inform�ticas
Administraci�n de sistemas inform�ticos en red
Desarrollo de aplicaciones multiplataforma
Desarrollo de Aplicaciones Web

.Qu�mica.
Industrias de proceso de pasta y papel
Pl�sticos y caucho
Laboratorio de an�lisis y de control de calidad
Qu�mica industrial
Qu�mica ambiental
Fabricaci�n de productos farmac�uticos y afines

.Sanidad.
Audiolog�a prot�sica
Anatom�a patol�gica y citolog�a
Diet�tica
Documentaci�n sanitaria
Higiene bucodental
Imagen para el diagn�stico
Laboratorio de diagn�stico cl�nico
Ortoprot�sica
Pr�tesis dentales
Radioterapia
Salud ambiental
Pr�tesis dentales (LOE)"))


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
    (store "resultado" "Ofertas relacionadas con ense�anzas de letras de estudios universitarios y formacion profesional de grado superior

-Universitarios-
Grado en Psicolog�a
Grado en Estudios Ingleses 
Grado en Filolog�a Hisp�nica 
Grado en Historia 
Grado en Humanidades 
Grado de Maestro/a en Educaci�n Infantil 
Grado de Maestro/a en Educaci�n Primaria 
Grado en Administraci�n y Direcci�n de Empresas 
Grado en Ciencias de la Actividad F�sica y del Deporte  
Grado en Derecho 
Grado en Econom�a 
Grado en Educaci�n Social 
Grado en Finanzas y Contabilidad 
Grado en Gesti�n y Administraci�n P�blica 
Grado en Marketing e Investigaci�n de Mercados 
Grado en Relaciones Laborales y Recursos Humanos 
Grado en Trabajo Social 
Grado en Turismo

-Formacion Profesional-
.Administraci�n y Gesti�n.
Administraci�n y Finanzas
Secretariado
Administraci�n y Finanzas (LOE)
Asistencia a la Direcci�n

.Comercio y Marketing.
Comercio Internacional
Gesti�n Comercial y Marketing
Gesti�n del Transporte
Servicios al Consumidor
Marketing y Publicidad
Comercio Internacional (LOE)
Gesti�n de Ventas y Espacios Comerciales
Transporte y Log�stica

.Seguridad y Medio Ambiente.
Educaci�n y control ambiental

.Servicios Socioculturales y a la Comunidad.
Integraci�n social
Educaci�n infantil
Interpretaci�n de la lengua de signos
Animaci�n Sociocultural
Animaci�n sociocultural y tur�stica

.Agraria.
Gesti�n Forestal y del Medio Natural
Gesti�n y Organizaci�n de Empresas Agropecuarias
Gesti�n y Organizaci�n de los Recursos Naturales y Paisaj�sticos
Paisajismo y Medio Rural"))

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
    (store "resultado" "Ofertas relacionadas con ense�anzas de bachillerato y formacion profesional de grado medio

-Bachillerato-
Ciencias Sociales
Ciencias de la Salud
Cientifico-T�cnico
Humanidades

-Formaci�n profesional-
.Actividades F�sicas y Deportivas.
Conducci�n de Actividades F�sico-deportivas en el Medio Natural
Administraci�n y Gesti�n
Gesti�n Administrativa

.Agraria.
Explotaciones Agr�colas Intensivas
Explotaciones Agrarias Extensivas
Explotaciones Ganaderas
Jardiner�a
Trabajos Forestales y Conservaci�n del Medio Natural
Producci�n Agroecol�gica
Producci�n Agropecuaria
Jardiner�a y Florister�a

.Artes Gr�ficas.
Encuadernaci�n y Manipulados de Papel y Cart�n
Impresi�n en Artes Gr�ficas
Preimpresi�n en Artes Gr�ficas
Impresi�n Gr�fica
Preimpresi�n Digital
Postimpresi�n y Acabados Gr�ficos

.Comercio y Marketing.
Comercio
Actividades Comerciales

.Edificaci�n y Obra Civil.
Acabados de Construcci�n
Obras de Alba�iler�a
Obras de Hormig�n
Operaci�n y Mantenimiento de Maquinaria de Construcci�n
Construccion
Obras de Interior, Decoraci�n y Rehabilitaci�n

.Electricidad y Electr�nica.
Equipos Electr�nicos de Consumo
Instalaciones El�ctricas y Autom�ticas
Instalaciones de Telecomunicaciones
Equipos e Instalaciones Electrot�cnicas

.Fabricaci�n Mec�nica.
Conformado por moldeo de metales y pol�meros
Joyer�a
Tratamientos superficiales y t�rmicos
Fundici�n
Soldadura y caldereria
Mecanizado

.Hosteler�a y Turismo.
Servicios en restauraci�n
Cocina y gastronom�a

.Imagen Personal.
Caracterizaci�n
Est�tica personal decorativa
Est�tica y belleza
Peluquer�a
Peluquer�a y cosm�tica capilar

.Imagen y Sonido.
Laboratorio de Imagen
Video Disc-Jockey y Sonido

.Industrias Alimentarias.
Conserver�a vegetal, c�rnica y de pescado
Elaboraci�n de productos l�cteos
Matadero y carnicer�a-charcuter�a
Moliner�a e industrias cerealistas
Panader�a, reposter�a y confiter�a
Aceites de oliva y vinos
Elaboraci�n de productos alimenticios

.Industrias Extractivas.
Excavaciones y sondeos
Piedra natural

.Inform�tica y Comunicaciones.
Sistemas microinform�ticos y redes

.Instalaci�n y Mantenimiento.
Instalaciones frigor�ficas y de climatizaci�n
Instalaciones de producci�n de calor
Instalaci�n y mantenimiento electromec�nico de maquinaria y conducci�n de l�neas
Mantenimiento ferroviario
Montaje y mantenimiento de instalaciones de fr�o, climatizaci�n y producci�n de calor
Mantenimiento electromec�nico

.Madera, Mueble y Corcho.
Transformaci�n de madera y corcho
Instalaci�n y amueblamiento
Fabricaci�n industrial de carpinter�a y mueble
Carpinter�a y mueble

.Mar�timo - Pesquera.
Operaci�n, control y mantenimiento de m�quinas e instalaciones del buque
Cultivos acu�colas
Operaciones de cultivo acu�cola
Buceo de media profundidad
Pesca y transporte mar�timo

.Qu�mica.
Operaciones de fabricaci�n de productos farmac�uticos
Laboratorio
Planta qu�mica
Operaciones de proceso de pasta y papel
Operaciones de transformaci�n de pl�sticos y caucho
Operaciones de laboratorio

.Sanidad.
Cuidados auxiliares de enfermer�a
Farmacia y parafarmacia
Emergencias sanitarias

.Servicios Socioculturales y a la Comunidad.
Atenci�n sociosanitaria
Atenci�n a personas en situaci�n de dependencia

.Textil, Confecci�n y Piel.
Calzado y marroquiner�a
Calzado y complementos de moda
Producci�n de hiladura y tejedur�a de calada
Producci�n de tejidos de punto
Confecci�n y moda
Operaciones de ennoblecimiento textil
Fabricaci�n y ennoblecimiento de productos textiles

.Transporte y Mantenimiento de Veh�culos.
Electromec�nica de veh�culos
Carrocer�a
Electromec�nica de maquinaria
Electromec�nica de veh�culos autom�viles
Conducci�n de Veh�culos de Transporte por Carretera

.Vidrio y Cer�mica.
Operaciones de fabricaci�n de vidrio y transformados
Operaciones de fabricaci�n de productos cer�micos
Fabricaci�n de productos cer�micos"))


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
    (store "resultado" "Ofertas de becario para estudiantes universitarios

.Oferta 1.
Pa�s: Espa�a
Provincia: Ja�n
Localidad: UBEDA
Fecha de Incorporaci�n: 09/07/2012
Duraci�n: 6 meses
Dotaci�n/Mes: 600 euros
Detalle Actividades Diarias: Venta y montaje de material el�ctrico en general, automatizaci�n, telecominicaciones, trabajo cara al p�blico.
Departamento: Ingenier�a
Estudios: Ingeniero T�cnico Industrial, Especialidad en Electricidad
Idiomas: 
INGL�S
Lectura.MEDIO Conversaci�n.MEDIO Escritura.MEDIO
Inform�tica: MS OFFICE USUARIO HABITUAL

.Oferta 2.
Pa�s:Espa�a
Provincia: Almeria
Localidad: Almeria
Fecha de Incorporaci�n: 02/07/2012
Duraci�n: 6 meses
Dotaci�n/Mes 360 euros
Detalle Actividades Diarias: Tomar� contacto con el trabajo diario de operaciones y mantenimiento que se desarrolla en la Sala de Control y en la Estaci�n de recepci�n del gaseoducto.
Departamento: EXPLOTACION
Estudios:
Licenciado en Ciencias. Ciencias Qu�micas
Ingeniero Industrial
Ingeniero Qu�mico
Ingeniero T�cnico Industrial
Idiomas:
INGL�S
Lectura.AVANZADO Conversaci�n.AVANZADO Escritura.MEDIO
Inform�tica:
ACCESS
USUARIO HABITUAL
MS OFFICE
USUARIO HABITUAL
WIMDOWS XP
USUARIO HABITUAL

.Oferta 3.
Pa�s:Espa�a
Provincia: Madrid
Localidad: Alcala de Henares
Fecha de Incorporaci�n: 05/10/2012
Duraci�n: 4 meses
Dotaci�n/Mes 400 euros
Detalle Actividades Diarias: Seguimiento y actualizaci�n de documentaci�n de Calidad. Recopilaci�n datos t�cnicos para homologaci�n materia prima. Seguimiento del cumplimiento de especificaciones t�cnicas de productos. Apoyo general al Dpto. T�cnico/Dpto Calidad (ensayos, b�squeda informaci�n, etc) Preparaci�n y env�o de muestras para clientes.
Departamento: PASCAL-T�CNICO
Estudios:
Licenciado en Qu�mica
Ingeniero T�cnico Industrial, Especialidad en Mec�nica
Ingeniero T�cnico Industrial, Especialidad en Qu�mica Industrial
Ingeniero T�cnico Industrial, Especialidad en Electricidad
Idiomas:
INGL�S
Lectura.AVANZADO Conversaci�n.AVANZADO Escritura.MEDIO
ALEM�N
Lectura.MEDIO Conversaci�n.MEDIO Escritura.MEDIO
FRANC�S
Lectura.MEDIO Conversaci�n.MEDIO Escritura.MEDIO
Inform�tica:
EXCEL
USUARIO HABITUAL
MS OFFICE
USUARIO HABITUAL
POWER POINT
USUARIO HABITUAL
INTERNET EXPLORER
USUARIO HABITUAL
OUTLOOK
USUARIO HABITUAL

.Oferta 4.
Pa�s:Espa�a
Provincia: Alicante
Localidad: Alicante
Fecha de Incorporaci�n: 01/08/2012
Duraci�n: 3 meses
Dotaci�n/Mes 500 euros
Detalle Actividades Diarias: EL PLAN FORMATIVO CONSISTIR� EN CONOCER E INSTRUIRSE EN ATENCI�N AL CLIENTE, SOPORTE A LA OPERATIVA DIARIA DE LA OFICINA: CAJA, INGRESOS, REINTEGROS, PAGOS, OTRAS TRANSFERENCIAS, CONOCER TAREAS ADMINISTRATIVAS DE ARCHIVO, VALIJA, CARGAR DISPENSADOR DEL CAJERO AUTOM�TICO, APERTURA DE CUENTAS, INFORMACI�N SOBRE PRODUCTOS FINANCIEROS, FISCALIDAD (NACIONAL), REALIZACI�N DE PROTOCOLOS.
Estudios:
Licenciado en Administraci�n y Direcci�n de Empresas
Licenciado en Econom�a
Diplomado en Ciencias Empresariales
Grado en Administraci�n y Direcci�n de Empresas
Disponibilidad: MA�ANA"))

; RULE pr_tit_prof
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria sin acabar
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
    (store "resultado" "Ofertas de docencia e investigacion para titulados

.Oferta1.
Pa�s: Espa�a
Provincia: Almeria
Localidad: Almeria
Universidad: Universidad de Almeria
Fecha de Incorporaci�n: 30/09/2012
Duraci�n: 1 a�o
Dotaci�n/Mes 2000 euros
Detalle Actividades Diarias: COMPROBACION, REGISTRO Y CONTROL DE LA NORMATIVA DEL DEPARTAMENTO DE ARQUITECTURA. ENCARGADO DE LABORES ADMINISTRATIVAS.
Departamento: LEGISLACION Y CONTROL
Estudios:
Licenciado en Ciencias Ambientales
M�ster Oficial en Biotecnolog�a Ambiental, Industrial y Alimentaria
Idiomas:
INGL�S
Lectura.AVANZADO Conversaci�n.MEDIO Escritura.MEDIO
Inform�tica:
ACCESS
USUARIO OCASIONAL
EXCEL
USUARIO OCASIONAL
MICROSOFT WORD
USUARIO OCASIONAL

.Oferta2.
Pa�s: Espa�a
Provincia: Barcelona
Localidad: Barcelona
Universidad: Universidad de Barcelona
Fecha de Incorporaci�n: 25/09/2012
Duraci�n: 1 a�o
Dotaci�n/Mes 2200 euros
Detalle Actividades Diarias: Docente para asignaturas relacionadas con el departamento de Lenguajes y Computaci�n. 
Departamento: Lenguajes y Computaci�n
Estudios:
Ingeniero en Informatica
Idiomas:
INGL�S
Lectura.MEDIO Conversaci�n.MEDIO Escritura.MEDIO
Inform�tica:
ACCESS
USUARIO AVANZADO
EXCEL
USUARIO OCASIONAL
MICROSOFT WORD
USUARIO OCASIONAL

.Oferta3.
Pa�s: Espa�a
Provincia: Lugo
Localidad: Lugo
Universidad: Universidad de Lugo
Fecha de Incorporaci�n: 01/10/2012
Duraci�n: 1 a�o
Dotaci�n/Mes 2000 euros
Detalle Actividades Diarias: Colaboraci�n con el departamento de Historia, Geograf�a e Historia del Arte. 
Departamento: Historia, Geograf�a e Historia del Arte.
Estudios:
Licenciado en Historia.
Licenciado en Humanidades
Idiomas:
INGL�S
Lectura.AVANZADO Conversaci�n.MEDIO Escritura.AVANZADO
Inform�tica:
EXCEL
USUARIO AVANZADO
MICROSOFT WORD
USUARIO AVANZADO
Disponibilidad: TODO EL DIA

.Oferta4.
Pa�s: Espa�a
Provincia: Malaga
Localidad: Malaga
Universidad: Universidad de Malaga
Fecha de Incorporaci�n: 02/11/2012
Duraci�n: 8 meses
Dotaci�n/Mes 1500 euros
Detalle Actividades Diarias: Colaboraci�n con el departamento de Econom�a Aplicada.
Departamento: Econom�a Aplicada.
Estudios:
Licenciado en Administraci�n y Direcci�n de Empresas
Licenciado en Econom�a
Licenciado en Ciencias Actuariales y Financieras
Diplomado en Ciencias Empresariales
Idiomas:
INGL�S
Lectura.AVANZADO Conversaci�n.AVANZADO Escritura.AVANZADO
Inform�tica:
EXCEL
USUARIO AVANZADO
MICROSOFT WORD
USUARIO HABITUAL"))

; RULE pr_tit_tec_expBaja_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es tipo tecnico, ha estado trabajando en una empresa peque�a con poca experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia baja y empresa peque�a

(defrule pr_tit_tec_expBaja_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == peque})
    =>
    (retract ?h)
    (store "resultado" "Ofertas para titulados tecnicos con baja experiencia que han trabajado incluso de becario en empresas peque�as

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Din�micas de grupo
Psicot�cnicos
Entrevista profunda
Curriculum Vitae
Pa�s: Espa�a
Provincia: Madrid
Localidad: Madrid
Fecha de Incorporaci�n: 01/06/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: INTEGRACION DE SISTEMAS DE INFORMACI�N DE GESTI�N, IMPLANTACI�N DE HERRAMIENTAS ESPECIALISTAS, DESARROLLO DE APLICACIONES, ESTRATEGIAS DE SISTEMAS.
Puesto: Consultor de Negocio
Estudios:
Ingeniero de Telecomunicaci�n
Licenciado en Inform�tica

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Malaga
Localidad: Campanillas
Fecha de Incorporaci�n: 25/04/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: DISE�O GR�FICO Y WEB. PLANIFICACI�N, DISE�O E IMPLEMENTACI�N DE P�GINAS WEB.
Puesto: Dise�ador grafico
Estudios:
Licenciado en Publicidad y Relaciones P�blicas
Licenciado en Comunicaci�n Audiovisual
Observaciones:CONOCIMIENTOS MEDIO-AVANZADOS DE PHOTOSHOP, FREEHAND, ILUSTRATOR O COREL DRAW Y FLASH, PARA LA REALIZACI�N DE DISE�OS GR�FICOS. USO DE PREMIERE O PROGRAMAS COMPATIBLES CON LA EDICI�N AUDIOVISUAL. CONOCIMIENTOS B�SICOS DE HTML."))

; RULE pr_tit_tec_expMedia_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando en una empresa peque�a con experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia  y empresa peque�a

(defrule pr_tit_tec_expMedia_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && puesto == miembro && duracion > 5 && duracion < 10 && empresa == peque})
    =>
    (retract ?h)
    (store "resultado" "Ofertas para titulados tecnicos con experiencia que han trabajado en empresas peque�as

.Oferta1.
Tipo de Contrato: Indefinido ordinario, desde el inicio
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Din�micas de grupo
Psicot�cnicos
Curriculum Vitae
Pa�s: Espa�a
Provincia: Cadiz
Localidad: Cadiz
Fecha de Incorporaci�n: 22/05/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Entre 960 y 1200 euros
Observaciones Dotacion: Sueldo competitivo, con una politica retributiva por encima del sector Interesante progresi�n salarial.
Detalle Actividades Diarias: Bastantes posibilidades dentro de la Empresa, para promocionar entre los diferentes departamentos existentes en la misma.
Puesto: Consultor de Negocio
Departamento: Tiendas
Estudios:
T�TULO DE NIVEL MEDIO
T�TULO DE NIVEL SUPERIOR

.Oferta2.
Tipo de Contrato: Obra o servicio determinado
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Almeria
Localidad: Santa Maria del Aguia
Fecha de Incorporaci�n: 02/05/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Segun convenio
Detalle Actividades Diarias: POSICIONAMIENTO T�CNICO DE LOS PRODUCTOS DE EMPRESAS DEL SECTOR AGR�COLA, CONTRIBUIR AL DESARROLLO DE LOS MISMOS MEDIANTE RELACIONES CON UNIVERSIDADES Y L�DERES DE OPINI�N, Y ASEGURAR LA FORMACI�N Y LA FUERZA DE VENTAS DE LA EMPRESA Y SUS DISTRIBUIDORES.
Puesto: RESPONSABLE T�CNICO AGRONOMO
Estudios:
Ingeniero Agr�nomo
Idiomas:
INGL�S
Lectura:AVANZADO Conversaci�n:AVANZADO Escritura:AVANZADO
Observaciones:
EXPERIENCIA M�NIMA DE 5 A�OS EN PRODUCCI�N VEGETAL Y BIOCIENCIAS: TRABAJO EN UNIVERSIDADES, CENTROS DE INVESTIGACI�N, DEPARTAMENTOS DE INVESTIGACI�N-DESARROLLO DE EMPRESAS DEL SECTOR, O EN APOYO T�CNICO DE DESARROLLO Y DEMOSTRACIONES. DISPONIBILIDAD PARA VIAJAR, CAPACIDAD DE TRABAJO EN GRUPO Y DOTES DE COMUNICACI�N"))

; RULE pr_tit_tec_expAlta_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico y ha estado trabajando en una empresa peque�a con gran experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado tecnico con experiencia alta y empresa peque�a

(defrule pr_tit_tec_expAlta_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == miembro || puesto == directivo) && duracion > 10 && empresa == peque})
    =>
    (retract ?h)
    (store "resultado" "Ofertas para titulados tecnicos con alta experiencia que han trabajado incluso como directivo en empresas peque�as

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Pa�s: Espa�a
Provincia: Madrid
Localidad: Madrid
Fecha de Incorporaci�n: 10/05/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: - Se valorar� dise�o de tuber�as con experiencia en SPD3 y PDMS. - Dise�o de soportes ( Support modeler, PDS). - Experiencia en Plantas Industriales, ciclo combinado, termosolares, petroqu�micas.
Puesto: Ingeniero PDMS
Estudios:
Ingeniero Industrial

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Pa�s: Espa�a
Provincia: Malaga
Localidad: Torremolinos
Fecha de Incorporaci�n: 24/04/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: ADMINISTRADOR, PROGRAMADOR
Puesto: Programador
Estudios:
Ingeniero en Inform�tica
Ingeniero T�cnico en Inform�tica de Gesti�n
Ingeniero T�cnico en Inform�tica de Sistemas
Observaciones:
ALTO NIVEL DE INGL�S HABLADO Y ESCRITO. LINUX WORKSTATION/SERVER EXPERIENCIE. LAMP (LINUX, APACHE, MYSQL, PHP SETUPS) NETWORK EXPERIENCIE. ACTIONSCRIPT 3 PROGRAMMING. PHP CLASSES KNOWLEDGE. IT ADMINISTRATION; HELPDESK."))


; RULE pr_tit_tec_expBaja
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil tecnico tecnico y ha estado trabajando ya en una empresa media de becario pero con poca experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado con experiencia baja en empresas medianas

(defrule pr_tit_tec_expBaja
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == tecnico && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == media})
    =>
    (retract ?h)
    (store "resultado" "Ofertas para titulados tecnicos con poca experiencia que han trabajado incluso de becario en empresas medias

.Oferta1.
Tipo de Contrato: Eventual por circunstancias de la producci�n
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Almeria
Localidad: ALQUIAN (EL)
Fecha de Incorporaci�n: 08/05/2012
Duraci�n: 6 meses
Dotaci�n/Mes: segun convenio
Detalle Actividades Diarias: IMPARTIR LAS ASIGNATURAS DE MATEM�TICAS, FISICA Y QUIMICA. NIVELES: PRIMARIA, ESO, BACHILLERATO, GRADO MEDIO, GRADO SUPERIOR, ACCESO UNIVERSIDAD Y GRADUADO EN ESO.
Puesto: Profesor de academia
Estudios:
Licenciado en Matem�ticas
Licenciado en Qu�mica
Disponibilidad: Tarde"))

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
    (store "resultado" "Ofertas para titulados tecnicos con experiencia media que han trabajado en empresas medianas

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Madrid
Localidad: Alcobendas
Fecha de Incorporaci�n: 01/06/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: SEGUIMIENTO DE AUDITOR�AS, DEFINICI�N, PUBLICACI�N Y MANTENIMIENTO DE POL�TICAS Y NORMATIVA INTERNA EN MATERIA DE SEGURIDAD DE LA INFORMACI�N, CONTROL Y SEGUIMIENTO DE LOS PRESUPUESTOS DE HOLDING Y DE LOS CISOS LOCALES.
Puesto: ESPECIALISTA IT RISK AND GOVERNANCE
Estudios:
T�TULO DE NIVEL SUPERIOR"))

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
    (store "resultado" "Ofertas para titulados tecnicos con experiencia alta que han trabajado incluso como directivo en empresas medias

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Madrid
Localidad: Madrid
Fecha de Incorporaci�n: 01/08/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: INGENIERIA Y SEGURIDAD EN PROYECTOS, CRIPTOGRAF�A, AUTENTICACI�N Y CONTROL DE ACCESO, TECNOLOG�AS ANTIFRAUDE "INFORMACIONALES".
Puesto: ESPECIALISTA EN TECNOLOG�A DE SEGURIDAD
Estudios:
Ingeniero de Telecomunicaci�n
Ingeniero en Inform�tica

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Cordoba
Localidad: Cordoba
Fecha de Incorporaci�n: 25/04/2012
Duraci�n: 6 meses
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: APOYO AL DEPARTAMENTO DE MANTENIMIENTO EL�CTRICO: CONFECCI�N Y MODIFICACI�N DE ESQUEMAS EL�CTRICOS CON PROGRAMA AUTOCAD. ORGANIZACI�N DE FICHEROS ELECTR�NICOS DE PLANOS, EN REDES INFORM�TICAS DE FYM. ORGANIZACI�N DE CARPETAS DE PLANOS Y ESQUEMAS
Puesto: Soporte
Estudios:
Ingeniero T�cnico Industrial, Especialidad en Electr�nica Industrial
Ingeniero T�cnico Industrial, Especialidad en Electricidad
Observaciones:
MANEJO PROGRAMA AUTOCAD. PERSONA RESPONSABLE. TURNO DE MA�ANA."))

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
    (store "resultado" "Ofertas para titulados tecnicos con poca experiencia que han trabajado incluso de becario en empresas grandes

.Oferta1.
Tipo de Contrato: Eventual por circunstancias de la producci�n
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Holanda
Provincia: Zuid Holland
Localidad: Delfgauw
Fecha de Incorporaci�n: 01/05/2012
Duraci�n: 1 a�o
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: Activities: In the Department of Microbiology various molecular techniques are applied in routine analyses and research projects. The Molecular Biologist/Biochemist is responsible for the routine analyses. The activities are performed according to the ISO 17025 quality assurance system. The candidate will also be involved in the validation and implementation of new methods. This includes optimi- zation and automation of DNA/RNA extractions, develop- ment of bio-PCR and qPCR assays, sequence analysis and other genotyping. Requirements: You are a trained molecular biologist/biochemist at an academic level with extensive experience with DNA-techniques. You are an enthusiastic and accurate professional. You are able to communicate fluently in English, both verbally as in writing.
Puesto: Analista en microbiolog�a
Estudios:
Licenciado en Ciencias. Ciencias Qu�micas
Licenciado en Biolog�a
Licenciado en Biotecnolog�a
Licenciado en Ciencias Qu�micas
Licenciado en Ciencias. Biol�gicas. Biolog�a Fundamental
Idiomas:
INGL�S
Lectura:AVANZADO Conversaci�n:AVANZADO Escritura:AVANZADO"))

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
    (store "resultado" "Ofertas para titulados tecnicos con experiencia media que han trabajado en empresas grandes

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Madrid
Localidad: Alcobendas
Fecha de Incorporaci�n: 01/06/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: GESTI�N GLOBAL DEL FRAUDE PRODUCIDO EN MEDIOS DE PAGO Y BANCA A DISTANCIA, SOLUCIONES PARA CONTENER LA CIFRA DE FRAUDE. CONTABILIZACI�N Y REPORTING DEL FRAUDE. PREPARACI�N DE LA INFRAESTRUCTURA TECNOL�GICA Y ORGANIZATIVA PARA RESPUESTA A INCIDENTES E INVESTIGACIONES.
Puesto: ESPECIALISTA GLOBAL SECURITY CENTER
Estudios:
Ingeniero de Telecomunicaci�n
Licenciado en Matem�ticas
Ingeniero en Inform�tica
Licenciado en Ciencias de Matem�ticas. Estad�stica Matem�tica

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Guadalajara
Localidad: Guadalajara
Fecha de Incorporaci�n: 26/04/2012
Duraci�n: 6 meses
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: PROGRAMACI�N PHP. ADMINISTRACI�N SISTEMAS LINUX/DEBIANA TECNOL�GICA Y ORGANIZATIVA PARA RESPUESTA A INCIDENTES E INVESTIGACIONES.
Puesto: Programador
Estudios:
Ingeniero en Inform�tica
Ingeniero T�cnico en Inform�tica de Gesti�n
Ingeniero T�cnico en Inform�tica de Sistemas"))

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
    (store "resultado" "Ofertas para titulados tecnicos con experiencia alta que han trabajado incluso como directivo en empresas grandes

.Oferta1.
Tipo de Contrato: Obra o servicio determinado
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Alemania
Provincia: norrhein-westfalen
Localidad: K�ln
Fecha de Incorporaci�n: 02/05/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: PRUEBAS FUNCIONALES DE LOS COMPONENTES EL�CTRICOS EN EL SISTEMA GENERAL DEL VEH�CULO, PLANIFICACI�N Y CONTROL DE LAS ACTIVIDADES DE PRUEBA, DESARROLLO DE CONCEPTOS PARA OPTIMIZACI�N, REVISAR LAS ESPECIFICACIONES Y CREACI�N DE INFORMES DE PRUEBAS (PARTE DE IDIOMA EN INGL�S)
Puesto: INGENIERO DE PRUEBAS ELECTR�NICA DEL AUTOM�VIL
Estudios:
Ingeniero de Telecomunicaci�n. Electr�nica
Ingeniero de Telecomunicaci�n. Telem�tica
Ingeniero de Telecomunicaci�n. Se�ales y Radiocomunicaci�n
Ingeniero en Autom�tica y Electr�nica Industrial
Informatica:
AUTOMATAS
Usuario Habitual"))

; RULE pr_tit_let_expBaja_peq
; IF
;   La fase es WORKy el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es tipo de letras y ha estado trabajando en una empresa peque�a con poca experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia baja y empresa peque�a

(defrule pr_tit_let_expBaja_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == peque})
    =>
    (retract ?h)
    (store "resultado" "Ofertas para titulados de letras con baja experiencia que han trabajado incluso de becario en empresas peque�as

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Din�micas de grupo
Psicot�cnicos
Entrevista profunda
Curriculum Vitae
Pa�s: Espa�a
Provincia: Zaragoza
Localidad: Zaragoza
Fecha de Incorporaci�n: 02/11/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: TRATAMIENTO DE DATOS. EVENTOS CREDITICIOS Y COMERCIALES MEDIANTE T�CNICAS MULTIVARIANTES (AN�LISIS CLUSTER, REGRESI�N LINEAL, REGRESI�N LOG�STICA, PROBIT ORDENADO). SERIES TEMPORALES MODELOS ARIMA. MODELOS DE RATING Y SCORING, RAROC Y PAR�METROS DE RIESGO. APOYO MATEM�TICO AL NEGOCIO. DESARROLLO DE ALGORITMOS, ESTAD�STICA Y PROBABILIDAD. PROYECTOS DE I+D. PROGRAMAS SAS, SPSS, CLEMENTINE, MATLAB
Puesto: Consultor de Negocio
Estudios:
Licenciado en Ciencias y T�cnicas Estad�sticas
Licenciado en Ciencias de Matem�ticas. Estad�stica Matem�tica"))

; RULE pr_tit_let_expMedia_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando en una empresa peque�a con experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia  y empresa peque�a

(defrule pr_tit_let_expMedia_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && puesto == miembro && duracion > 5 && duracion < 10 && empresa == peque})
    =>
    (retract ?h)
    (store "resultado" "Ofertas para titulados de letras con experiencia que han trabajado en empresas peque�as

.Oferta1.
Tipo de Contrato: Indefinido ordinario, desde el inicio
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Din�micas de grupo
Psicot�cnicos
Curriculum Vitae
Pa�s: Espa�a
Provincia: Cadiz
Localidad: Cadiz
Fecha de Incorporaci�n: 22/05/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Entre 960 y 1200 euros
Observaciones Dotacion: Sueldo competitivo, con una politica retributiva por encima del sector Interesante progresi�n salarial.
Detalle Actividades Diarias: Bastantes posibilidades dentro de la Empresa, para promocionar entre los diferentes departamentos existentes en la misma.
Puesto: Consultor de Negocio
Departamento: Tiendas
Estudios:
T�TULO DE NIVEL MEDIO
T�TULO DE NIVEL SUPERIOR))

; RULE pr_tit_let_expAlta_peq
; IF
;   La fase es WORK y el nivel se ha obtenido de una carrera universitaria acabada
;	Ademas es perfil de letras y ha estado trabajando en una empresa peque�a con gran experiencia
; THEN
;   elimina fase
;	muestra opciones de titulado de letras con experiencia alta y empresa peque�a

(defrule pr_tit_let_expAlta_peq
    ?h <-(phase WORK)
    (lv TITULADO)
    (curriculum {tipo == letras && (puesto == miembro || puesto == directivo) && duracion > 10 && empresa == peque})
    =>
    (retract ?h)
    (store "resultado" "Ofertas para titulados de letras con alta experiencia que han trabajado incluso como directivo en empresas peque�as

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Din�micas de grupo
Psicot�cnicos
Examen de conocimientos
Entrevista profunda
Curriculum Vitae
Pa�s: Espa�a
Provincia: Madrid
Localidad: Madrid
Fecha de Incorporaci�n: 01/06/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: Incorporaci�n e integraci�n en equipos de trabajo de los diferentes proyectos de consultor�a: Estrategia, Efectividad Comercial y Marketing, Organizaci�n y Procesos, Gesti�n y Control de Riesgos, Informaci�n de Gesti�n y Financiera.
Puesto: Consultor de Negocio
Estudios:
Licenciado en Administraci�n y Direcci�n de Empresas
Licenciado en Administraci�n y Direcci�n de Empresas. Gesti�n financiera.
Licenciado en Ciencias Econ�micas y Empresariales))


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
    (store "resultado" "Ofertas para titulados de letras con poca experiencia que han trabajado incluso de becario en empresas medias

.Oferta1.
Tipo de Contrato: Tiempo Parcial
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Malaga
Localidad: Campillos
Fecha de Incorporaci�n: 01/05/2012
Duraci�n: 6 meses
Dotaci�n/Mes: Entre 480 y 720 euros
Observaciones dotacion: 3 dias a la semana horario de ma�ana o tarde.
Detalle Actividades Diarias: llevar contabilidad nominas y seguros sociales
Puesto: Contabilidad
Estudios:
Licenciado en Administraci�n y Direcci�n de Empresas
Licenciado en Ciencias Actuariales y Financieras
Diplomado en Ciencias Empresariales
Diplomado en Gesti�n y Administraci�n P�blica
Informatica:
CONTAPLUS
CUALQUIER NIVEL
LOGIC WIN GLOBAL
USUARIO HABITUAL"))

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
    (store "resultado" "Ofertas para titulados de letras con experiencia media que han trabajado en empresas medianas

.Oferta1.
Tipo de Contrato: Interinidad para sustituir a los trabajadores durante los per�odos de descanso por maternidad
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista profunda
Curriculum Vitae
IMPRESCINDIBLE TENER DISCAPACIDAD SUPERIOR AL 33%
Pa�s: Espa�a
Provincia: Malaga
Localidad: Malaga
Fecha de Incorporaci�n: 01/06/2012
Duraci�n: 6 meses
Dotaci�n/Mes: Seg�n Convenio
Detalle Actividades Diarias: Apoyo al departamento administrativo de rrhh, funciones generales de la administracion laboral tales como elaboraci�n de n�minas, seguros sociales, distribuci�n de trabajadores, etc.
Puesto: ADMINISTRATIVO DE RRHH
Departamento:RECURSOS HUMANOS
Estudios:
Diplomado en Graduado Social
Diplomado en Relaciones Laborales
Informatica:
NOMINAPLUS
EXPERTO
EXCEL
USUARIO HABITUAL
MS OFFICE
EXPERTO"))

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
    (store "resultado" "Ofertas para titulados de letras con experiencia alta que han trabajado incluso como directivo en empresas medias

.Oferta1.
Tipo de Contrato: Por cuenta propia
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
SE VALORAR� LA EXPERIENCIA PROFESIONAL O PSEUDOPROFESIONAL DE LOS CANDIDATOS, LA EXPRESI�N CORPORAL Y LA CAPACIDAD DE TRANSMISI�N, AS� COMO LA CAPACIDAD DE TRABAJO.
Pa�s: Espa�a
Provincia: Sevilla
Localidad: Sevilla
Fecha de Incorporaci�n: 10/06/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Retribuci�n Mercantil
Detalle Actividades Diarias: ASENTAMIENTO DE CLIENTELA, COMERCIALIZACI�N DE CLIENTELA, INFORMES JUR�DICOS Y ECON�MICOS, DEMANDAS, ARCHIVO, TRANSACCIONES...
Puesto: Gestor
Estudios:
Licenciado en Derecho
Licenciado en Administraci�n y Direcci�n de Empresas
Licenciado en Ciencias del Trabajo
Diplomado en Ciencias Empresariales
Diplomado en Relaciones Laborales
Carnet de Conducir: B
Disponibilidad: Todo el dia"))

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
    (curriculum {tipo == letras && (puesto == becario || puesto == miembro) && duracion < 5 && empresa == grande})
    =>
    (retract ?h)
    (store "resultado" "Ofertas para titulados de letras con poca experiencia que han trabajado incluso de becario en empresas grandes

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Din�micas de grupo
Psicot�cnicos
Examen de conocimientos
Entrevista profunda
Curriculum Vitae
Pa�s: Espa�a
Provincia: Barcelona
Localidad: MONTCADA CENTRE
Fecha de Incorporaci�n: 09/05/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Mas de 1200 euros
Detalle Actividades Diarias: Tras el periodo de formaci�n, las personas que se incorporen ser�n las responsables de la coordinaci�n y direcci�n de una zona asignada (entre 4-6 tiendas ), dentro de una �rea en territorio Nacional, de acuerdo con los objetivos marcados por la empresa y aplicando los principios de simplicidad, eficiencia, eficacia y rentabilidad. Las principales responsabilidades incluyen funciones relativas a la Gesti�n de Mercanc�a, Gesti�n de Costes y Gesti�n de Personal.
Departamento: Ventas
Puesto: Jefe de ventas
Estudios:
Licenciado en Administraci�n y Direcci�n de Empresas
Licenciado en Econom�a
Idiomas
ALEM�N
Lectura:CUALQUIER NIVEL Conversaci�n:CUALQUIER NIVEL Escritura:CUALQUIER NIVEL
INGL�S
Lectura:AVANZADO Conversaci�n:AVANZADO Escritura:AVANZADO
Carnet de Conducir: B
Disponibilidad: Todo el dia

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Granada
Localidad: Granada
Fecha de Incorporaci�n: 06/05/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Mas de 1000 euros
Detalle Actividades Diarias: Organizacion y mantenimiento economia empresa
Departamento: Contabilidad
Puesto: Contable
Estudios:
Licenciado en Administraci�n y Direcci�n de Empresas
Licenciado en Econom�a
Idiomas
NIVEL
INGL�S
Lectura:AVANZADO Conversaci�n:AVANZADO Escritura:AVANZADO
Disponibilidad: MA�ANA"))

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
    (store "resultado" "Ofertas para titulados de letras con experiencia media que han trabajado en empresas grandes

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Din�micas de grupo
Psicot�cnicos
Examen de conocimientos
Entrevista profunda
Curriculum Vitae
Pa�s: Espa�a
Provincia: Barcelona
Localidad: MONTCADA CENTRE
Fecha de Incorporaci�n: 09/05/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Mas de 1200 euros
Detalle Actividades Diarias: Tras el periodo de formaci�n, las personas que se incorporen ser�n las responsables de la coordinaci�n y direcci�n de una zona asignada (entre 4-6 tiendas ), dentro de una �rea en territorio Nacional, de acuerdo con los objetivos marcados por la empresa y aplicando los principios de simplicidad, eficiencia, eficacia y rentabilidad. Las principales responsabilidades incluyen funciones relativas a la Gesti�n de Mercanc�a, Gesti�n de Costes y Gesti�n de Personal.
Departamento: Ventas
Puesto: Jefe de ventas
Estudios:
Licenciado en Administraci�n y Direcci�n de Empresas
Licenciado en Econom�a
Idiomas
ALEM�N
Lectura:CUALQUIER NIVEL Conversaci�n:CUALQUIER NIVEL Escritura:CUALQUIER NIVEL
INGL�S
Lectura:AVANZADO Conversaci�n:AVANZADO Escritura:AVANZADO
Carnet de Conducir: B
Disponibilidad: Todo el dia

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
Pa�s: Espa�a
Provincia: Malaga
Localidad: SAN PEDRO DE ALCANTARA
Fecha de Incorporaci�n: 26/04/2012
Duraci�n: 6 meses
Dotaci�n/Mes: segun convenio
Detalle Actividades Diarias: TRATO CON EL CLIENTE, TAREAS DE MARKETING, ORGANIZACI�N DE EVENTOS.
Departamento: Marketing
Puesto: Responsable Marketing
Estudios:
Licenciado en Publicidad y Relaciones P�blicas
Idiomas
Valorable idiomas"))

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
    (store "resultado" "Ofertas para titulados de letras con experiencia alta que han trabajado incluso como directivo en empresas grandes

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selecci�n: Normal
Selecci�n que aplicar� la empresa a los candidatos:
Entrevista preliminar
Din�micas de grupo
Psicot�cnicos
Examen de conocimientos
Entrevista profunda
Curriculum Vitae
Pa�s: Espa�a
Provincia: Barcelona
Localidad: MONTCADA CENTRE
Fecha de Incorporaci�n: 09/05/2012
Duraci�n: Indefinida
Dotaci�n/Mes: Mas de 1200 euros
Detalle Actividades Diarias: Tras el periodo de formaci�n, las personas que se incorporen ser�n las responsables de la coordinaci�n y direcci�n de una zona asignada (entre 4-6 tiendas ), dentro de una �rea en territorio Nacional, de acuerdo con los objetivos marcados por la empresa y aplicando los principios de simplicidad, eficiencia, eficacia y rentabilidad. Las principales responsabilidades incluyen funciones relativas a la Gesti�n de Mercanc�a, Gesti�n de Costes y Gesti�n de Personal.
Departamento: Ventas
Puesto: Jefe de ventas
Estudios:
Licenciado en Administraci�n y Direcci�n de Empresas
Licenciado en Econom�a
Idiomas
ALEM�N
Lectura:CUALQUIER NIVEL Conversaci�n:CUALQUIER NIVEL Escritura:CUALQUIER NIVEL
INGL�S
Lectura:AVANZADO Conversaci�n:AVANZADO Escritura:AVANZADO
Carnet de Conducir: B
Disponibilidad: Todo el dia"))
