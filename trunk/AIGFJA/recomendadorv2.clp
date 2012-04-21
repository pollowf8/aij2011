
;;;======================================================
;;;   Recomendador de puestos de trabajo
;;;
;;;     Practica 2 V2
;;;
;;;     Inteligencia Artificial e Ingeniería del Conocimiento
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

; RULE ESO_bach-fpm
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
    (store "resultado" "Ofertas relacionadas con enseñanzas tecnicas de estudios universitarios y formacion profesional de grado superior:

-Universitarios-
Grado en Ciencias Ambientales
Grado en Matemáticas
Grado en Química
Grado en Enfermería
Grado en Fisioterapia
Grado en Ingeniería Agrícola 
Grado en Ingeniería Electrónica Industrial
Grado en Ingeniería Informática
Grado en Ingeniería Mecánica
Grado en Ingeniería Química Industrial

-Formacion Profesional-
.Electricidad y Electrónica.
Desarrollo de Productos Electrónicos
Instalaciones Electrotécnicas
Sistemas de Regulación y Control Automáticos
Sistemas de Telecomunicación e Informáticos
Sistemas Electrotécnicos y Automatizados
Sistemas de Telecomunicaciones e Informáticos
Automatización y Robótica Industrial
Mantenimiento Electrónico

.Fabricación Mecánica.
Producción por fundición y pulvimetalurgia
Desarrollo de proyectos mecánicos
Programación de la producción en fabricación mecánica
Construcciones metálicas
Óptica de Anteojería
Diseño en fabricación mecánica
Programación de la producción en moldeo de metales y polímeros

.Imagen y Sonido.
Producción de audiovisuales, radio y espectáculos
Realización de audiovisuales y espectáculos
Sonido
Imagen
Animaciones 3D, juegos y entornos interactivos
Iluminación, captación y tratamiento de la imagen
Producción de audiovisuales y espectáculos
Realización de proyectos audiovisuales y espectáculos
Sonido para audiovisuales y espectáculos

.Informática y Comunicaciones.
Administración de sistemas informáticos
Desarrollo de aplicaciones informáticas
Administración de sistemas informáticos en red
Desarrollo de aplicaciones multiplataforma
Desarrollo de Aplicaciones Web

.Química.
Industrias de proceso de pasta y papel
Plásticos y caucho
Laboratorio de análisis y de control de calidad
Química industrial
Química ambiental
Fabricación de productos farmacéuticos y afines

.Sanidad.
Audiología protésica
Anatomía patológica y citología
Dietética
Documentación sanitaria
Higiene bucodental
Imagen para el diagnóstico
Laboratorio de diagnóstico clínico
Ortoprotésica
Prótesis dentales
Radioterapia
Salud ambiental
Prótesis dentales (LOE)"))


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
    (store "resultado" "Ofertas relacionadas con enseñanzas de letras de estudios universitarios y formacion profesional de grado superior

-Universitarios-
Grado en Psicología
Grado en Estudios Ingleses 
Grado en Filología Hispánica 
Grado en Historia 
Grado en Humanidades 
Grado de Maestro/a en Educación Infantil 
Grado de Maestro/a en Educación Primaria 
Grado en Administración y Dirección de Empresas 
Grado en Ciencias de la Actividad Física y del Deporte  
Grado en Derecho 
Grado en Economía 
Grado en Educación Social 
Grado en Finanzas y Contabilidad 
Grado en Gestión y Administración Pública 
Grado en Marketing e Investigación de Mercados 
Grado en Relaciones Laborales y Recursos Humanos 
Grado en Trabajo Social 
Grado en Turismo

-Formacion Profesional-
.Administración y Gestión.
Administración y Finanzas
Secretariado
Administración y Finanzas (LOE)
Asistencia a la Dirección

.Comercio y Marketing.
Comercio Internacional
Gestión Comercial y Marketing
Gestión del Transporte
Servicios al Consumidor
Marketing y Publicidad
Comercio Internacional (LOE)
Gestión de Ventas y Espacios Comerciales
Transporte y Logística

.Seguridad y Medio Ambiente.
Educación y control ambiental

.Servicios Socioculturales y a la Comunidad.
Integración social
Educación infantil
Interpretación de la lengua de signos
Animación Sociocultural
Animación sociocultural y turística

.Agraria.
Gestión Forestal y del Medio Natural
Gestión y Organización de Empresas Agropecuarias
Gestión y Organización de los Recursos Naturales y Paisajísticos
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
    (store "resultado" "Ofertas relacionadas con enseñanzas de bachillerato y formacion profesional de grado medio

-Bachillerato-
Ciencias Sociales
Ciencias de la Salud
Cientifico-Técnico
Humanidades

-Formación profesional-
.Actividades Físicas y Deportivas.
Conducción de Actividades Físico-deportivas en el Medio Natural
Administración y Gestión
Gestión Administrativa

.Agraria.
Explotaciones Agrícolas Intensivas
Explotaciones Agrarias Extensivas
Explotaciones Ganaderas
Jardinería
Trabajos Forestales y Conservación del Medio Natural
Producción Agroecológica
Producción Agropecuaria
Jardinería y Floristería

.Artes Gráficas.
Encuadernación y Manipulados de Papel y Cartón
Impresión en Artes Gráficas
Preimpresión en Artes Gráficas
Impresión Gráfica
Preimpresión Digital
Postimpresión y Acabados Gráficos

.Comercio y Marketing.
Comercio
Actividades Comerciales

.Edificación y Obra Civil.
Acabados de Construcción
Obras de Albañilería
Obras de Hormigón
Operación y Mantenimiento de Maquinaria de Construcción
Construccion
Obras de Interior, Decoración y Rehabilitación

.Electricidad y Electrónica.
Equipos Electrónicos de Consumo
Instalaciones Eléctricas y Automáticas
Instalaciones de Telecomunicaciones
Equipos e Instalaciones Electrotécnicas

.Fabricación Mecánica.
Conformado por moldeo de metales y polímeros
Joyería
Tratamientos superficiales y térmicos
Fundición
Soldadura y caldereria
Mecanizado

.Hostelería y Turismo.
Servicios en restauración
Cocina y gastronomía

.Imagen Personal.
Caracterización
Estética personal decorativa
Estética y belleza
Peluquería
Peluquería y cosmética capilar

.Imagen y Sonido.
Laboratorio de Imagen
Video Disc-Jockey y Sonido

.Industrias Alimentarias.
Conservería vegetal, cárnica y de pescado
Elaboración de productos lácteos
Matadero y carnicería-charcutería
Molinería e industrias cerealistas
Panadería, repostería y confitería
Aceites de oliva y vinos
Elaboración de productos alimenticios

.Industrias Extractivas.
Excavaciones y sondeos
Piedra natural

.Informática y Comunicaciones.
Sistemas microinformáticos y redes

.Instalación y Mantenimiento.
Instalaciones frigoríficas y de climatización
Instalaciones de producción de calor
Instalación y mantenimiento electromecánico de maquinaria y conducción de líneas
Mantenimiento ferroviario
Montaje y mantenimiento de instalaciones de frío, climatización y producción de calor
Mantenimiento electromecánico

.Madera, Mueble y Corcho.
Transformación de madera y corcho
Instalación y amueblamiento
Fabricación industrial de carpintería y mueble
Carpintería y mueble

.Marítimo - Pesquera.
Operación, control y mantenimiento de máquinas e instalaciones del buque
Cultivos acuícolas
Operaciones de cultivo acuícola
Buceo de media profundidad
Pesca y transporte marítimo

.Química.
Operaciones de fabricación de productos farmacéuticos
Laboratorio
Planta química
Operaciones de proceso de pasta y papel
Operaciones de transformación de plásticos y caucho
Operaciones de laboratorio

.Sanidad.
Cuidados auxiliares de enfermería
Farmacia y parafarmacia
Emergencias sanitarias

.Servicios Socioculturales y a la Comunidad.
Atención sociosanitaria
Atención a personas en situación de dependencia

.Textil, Confección y Piel.
Calzado y marroquinería
Calzado y complementos de moda
Producción de hiladura y tejeduría de calada
Producción de tejidos de punto
Confección y moda
Operaciones de ennoblecimiento textil
Fabricación y ennoblecimiento de productos textiles

.Transporte y Mantenimiento de Vehículos.
Electromecánica de vehículos
Carrocería
Electromecánica de maquinaria
Electromecánica de vehículos automóviles
Conducción de Vehículos de Transporte por Carretera

.Vidrio y Cerámica.
Operaciones de fabricación de vidrio y transformados
Operaciones de fabricación de productos cerámicos
Fabricación de productos cerámicos"))


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
País: España
Provincia: Jaén
Localidad: UBEDA
Fecha de Incorporación: 09/07/2012
Duración: 6 meses
Dotación/Mes: 600 euros
Detalle Actividades Diarias: Venta y montaje de material eléctrico en general, automatización, telecominicaciones, trabajo cara al público.
Departamento: Ingeniería
Estudios: Ingeniero Técnico Industrial, Especialidad en Electricidad
Idiomas: 
INGLÉS
Lectura.MEDIO Conversación.MEDIO Escritura.MEDIO
Informática: MS OFFICE USUARIO HABITUAL

.Oferta 2.
País:España
Provincia: Almeria
Localidad: Almeria
Fecha de Incorporación: 02/07/2012
Duración: 6 meses
Dotación/Mes 360 euros
Detalle Actividades Diarias: Tomará contacto con el trabajo diario de operaciones y mantenimiento que se desarrolla en la Sala de Control y en la Estación de recepción del gaseoducto.
Departamento: EXPLOTACION
Estudios:
Licenciado en Ciencias. Ciencias Químicas
Ingeniero Industrial
Ingeniero Químico
Ingeniero Técnico Industrial
Idiomas:
INGLÉS
Lectura.AVANZADO Conversación.AVANZADO Escritura.MEDIO
Informática:
ACCESS
USUARIO HABITUAL
MS OFFICE
USUARIO HABITUAL
WIMDOWS XP
USUARIO HABITUAL

.Oferta 3.
País:España
Provincia: Madrid
Localidad: Alcala de Henares
Fecha de Incorporación: 05/10/2012
Duración: 4 meses
Dotación/Mes 400 euros
Detalle Actividades Diarias: Seguimiento y actualización de documentación de Calidad. Recopilación datos técnicos para homologación materia prima. Seguimiento del cumplimiento de especificaciones técnicas de productos. Apoyo general al Dpto. Técnico/Dpto Calidad (ensayos, búsqueda información, etc) Preparación y envío de muestras para clientes.
Departamento: PASCAL-TÉCNICO
Estudios:
Licenciado en Química
Ingeniero Técnico Industrial, Especialidad en Mecánica
Ingeniero Técnico Industrial, Especialidad en Química Industrial
Ingeniero Técnico Industrial, Especialidad en Electricidad
Idiomas:
INGLÉS
Lectura.AVANZADO Conversación.AVANZADO Escritura.MEDIO
ALEMÁN
Lectura.MEDIO Conversación.MEDIO Escritura.MEDIO
FRANCÉS
Lectura.MEDIO Conversación.MEDIO Escritura.MEDIO
Informática:
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
País:España
Provincia: Alicante
Localidad: Alicante
Fecha de Incorporación: 01/08/2012
Duración: 3 meses
Dotación/Mes 500 euros
Detalle Actividades Diarias: EL PLAN FORMATIVO CONSISTIRÁ EN CONOCER E INSTRUIRSE EN ATENCIÓN AL CLIENTE, SOPORTE A LA OPERATIVA DIARIA DE LA OFICINA: CAJA, INGRESOS, REINTEGROS, PAGOS, OTRAS TRANSFERENCIAS, CONOCER TAREAS ADMINISTRATIVAS DE ARCHIVO, VALIJA, CARGAR DISPENSADOR DEL CAJERO AUTOMÁTICO, APERTURA DE CUENTAS, INFORMACIÓN SOBRE PRODUCTOS FINANCIEROS, FISCALIDAD (NACIONAL), REALIZACIÓN DE PROTOCOLOS.
Estudios:
Licenciado en Administración y Dirección de Empresas
Licenciado en Economía
Diplomado en Ciencias Empresariales
Grado en Administración y Dirección de Empresas
Disponibilidad: MAÑANA"))

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
País: España
Provincia: Almeria
Localidad: Almeria
Universidad: Universidad de Almeria
Fecha de Incorporación: 30/09/2012
Duración: 1 año
Dotación/Mes 2000 euros
Detalle Actividades Diarias: COMPROBACION, REGISTRO Y CONTROL DE LA NORMATIVA DEL DEPARTAMENTO DE ARQUITECTURA. ENCARGADO DE LABORES ADMINISTRATIVAS.
Departamento: LEGISLACION Y CONTROL
Estudios:
Licenciado en Ciencias Ambientales
Máster Oficial en Biotecnología Ambiental, Industrial y Alimentaria
Idiomas:
INGLÉS
Lectura.AVANZADO Conversación.MEDIO Escritura.MEDIO
Informática:
ACCESS
USUARIO OCASIONAL
EXCEL
USUARIO OCASIONAL
MICROSOFT WORD
USUARIO OCASIONAL

.Oferta2.
País: España
Provincia: Barcelona
Localidad: Barcelona
Universidad: Universidad de Barcelona
Fecha de Incorporación: 25/09/2012
Duración: 1 año
Dotación/Mes 2200 euros
Detalle Actividades Diarias: Docente para asignaturas relacionadas con el departamento de Lenguajes y Computación. 
Departamento: Lenguajes y Computación
Estudios:
Ingeniero en Informatica
Idiomas:
INGLÉS
Lectura.MEDIO Conversación.MEDIO Escritura.MEDIO
Informática:
ACCESS
USUARIO AVANZADO
EXCEL
USUARIO OCASIONAL
MICROSOFT WORD
USUARIO OCASIONAL

.Oferta3.
País: España
Provincia: Lugo
Localidad: Lugo
Universidad: Universidad de Lugo
Fecha de Incorporación: 01/10/2012
Duración: 1 año
Dotación/Mes 2000 euros
Detalle Actividades Diarias: Colaboración con el departamento de Historia, Geografía e Historia del Arte. 
Departamento: Historia, Geografía e Historia del Arte.
Estudios:
Licenciado en Historia.
Licenciado en Humanidades
Idiomas:
INGLÉS
Lectura.AVANZADO Conversación.MEDIO Escritura.AVANZADO
Informática:
EXCEL
USUARIO AVANZADO
MICROSOFT WORD
USUARIO AVANZADO
Disponibilidad: TODO EL DIA

.Oferta4.
País: España
Provincia: Malaga
Localidad: Malaga
Universidad: Universidad de Malaga
Fecha de Incorporación: 02/11/2012
Duración: 8 meses
Dotación/Mes 1500 euros
Detalle Actividades Diarias: Colaboración con el departamento de Economía Aplicada.
Departamento: Economía Aplicada.
Estudios:
Licenciado en Administración y Dirección de Empresas
Licenciado en Economía
Licenciado en Ciencias Actuariales y Financieras
Diplomado en Ciencias Empresariales
Idiomas:
INGLÉS
Lectura.AVANZADO Conversación.AVANZADO Escritura.AVANZADO
Informática:
EXCEL
USUARIO AVANZADO
MICROSOFT WORD
USUARIO HABITUAL"))

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
    (store "resultado" "Ofertas para titulados tecnicos con baja experiencia que han trabajado incluso de becario en empresas pequeñas

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Dinámicas de grupo
Psicotécnicos
Entrevista profunda
Curriculum Vitae
País: España
Provincia: Madrid
Localidad: Madrid
Fecha de Incorporación: 01/06/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: INTEGRACION DE SISTEMAS DE INFORMACIÓN DE GESTIÓN, IMPLANTACIÓN DE HERRAMIENTAS ESPECIALISTAS, DESARROLLO DE APLICACIONES, ESTRATEGIAS DE SISTEMAS.
Puesto: Consultor de Negocio
Estudios:
Ingeniero de Telecomunicación
Licenciado en Informática

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Malaga
Localidad: Campanillas
Fecha de Incorporación: 25/04/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: DISEÑO GRÁFICO Y WEB. PLANIFICACIÓN, DISEÑO E IMPLEMENTACIÓN DE PÁGINAS WEB.
Puesto: Diseñador grafico
Estudios:
Licenciado en Publicidad y Relaciones Públicas
Licenciado en Comunicación Audiovisual
Observaciones:CONOCIMIENTOS MEDIO-AVANZADOS DE PHOTOSHOP, FREEHAND, ILUSTRATOR O COREL DRAW Y FLASH, PARA LA REALIZACIÓN DE DISEÑOS GRÁFICOS. USO DE PREMIERE O PROGRAMAS COMPATIBLES CON LA EDICIÓN AUDIOVISUAL. CONOCIMIENTOS BÁSICOS DE HTML."))

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
    (store "resultado" "Ofertas para titulados tecnicos con experiencia que han trabajado en empresas pequeñas

.Oferta1.
Tipo de Contrato: Indefinido ordinario, desde el inicio
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Dinámicas de grupo
Psicotécnicos
Curriculum Vitae
País: España
Provincia: Cadiz
Localidad: Cadiz
Fecha de Incorporación: 22/05/2012
Duración: Indefinida
Dotación/Mes: Entre 960 y 1200 euros
Observaciones Dotacion: Sueldo competitivo, con una politica retributiva por encima del sector Interesante progresión salarial.
Detalle Actividades Diarias: Bastantes posibilidades dentro de la Empresa, para promocionar entre los diferentes departamentos existentes en la misma.
Puesto: Consultor de Negocio
Departamento: Tiendas
Estudios:
TÍTULO DE NIVEL MEDIO
TÍTULO DE NIVEL SUPERIOR

.Oferta2.
Tipo de Contrato: Obra o servicio determinado
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Almeria
Localidad: Santa Maria del Aguia
Fecha de Incorporación: 02/05/2012
Duración: Indefinida
Dotación/Mes: Segun convenio
Detalle Actividades Diarias: POSICIONAMIENTO TÉCNICO DE LOS PRODUCTOS DE EMPRESAS DEL SECTOR AGRÍCOLA, CONTRIBUIR AL DESARROLLO DE LOS MISMOS MEDIANTE RELACIONES CON UNIVERSIDADES Y LÍDERES DE OPINIÓN, Y ASEGURAR LA FORMACIÓN Y LA FUERZA DE VENTAS DE LA EMPRESA Y SUS DISTRIBUIDORES.
Puesto: RESPONSABLE TÉCNICO AGRONOMO
Estudios:
Ingeniero Agrónomo
Idiomas:
INGLÉS
Lectura:AVANZADO Conversación:AVANZADO Escritura:AVANZADO
Observaciones:
EXPERIENCIA MÍNIMA DE 5 AÑOS EN PRODUCCIÓN VEGETAL Y BIOCIENCIAS: TRABAJO EN UNIVERSIDADES, CENTROS DE INVESTIGACIÓN, DEPARTAMENTOS DE INVESTIGACIÓN-DESARROLLO DE EMPRESAS DEL SECTOR, O EN APOYO TÉCNICO DE DESARROLLO Y DEMOSTRACIONES. DISPONIBILIDAD PARA VIAJAR, CAPACIDAD DE TRABAJO EN GRUPO Y DOTES DE COMUNICACIÓN"))

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
    (store "resultado" "Ofertas para titulados tecnicos con alta experiencia que han trabajado incluso como directivo en empresas pequeñas

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
País: España
Provincia: Madrid
Localidad: Madrid
Fecha de Incorporación: 10/05/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: - Se valorará diseño de tuberías con experiencia en SPD3 y PDMS. - Diseño de soportes ( Support modeler, PDS). - Experiencia en Plantas Industriales, ciclo combinado, termosolares, petroquímicas.
Puesto: Ingeniero PDMS
Estudios:
Ingeniero Industrial

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
País: España
Provincia: Malaga
Localidad: Torremolinos
Fecha de Incorporación: 24/04/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: ADMINISTRADOR, PROGRAMADOR
Puesto: Programador
Estudios:
Ingeniero en Informática
Ingeniero Técnico en Informática de Gestión
Ingeniero Técnico en Informática de Sistemas
Observaciones:
ALTO NIVEL DE INGLÉS HABLADO Y ESCRITO. LINUX WORKSTATION/SERVER EXPERIENCIE. LAMP (LINUX, APACHE, MYSQL, PHP SETUPS) NETWORK EXPERIENCIE. ACTIONSCRIPT 3 PROGRAMMING. PHP CLASSES KNOWLEDGE. IT ADMINISTRATION; HELPDESK."))


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
Tipo de Contrato: Eventual por circunstancias de la producción
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Almeria
Localidad: ALQUIAN (EL)
Fecha de Incorporación: 08/05/2012
Duración: 6 meses
Dotación/Mes: segun convenio
Detalle Actividades Diarias: IMPARTIR LAS ASIGNATURAS DE MATEMÁTICAS, FISICA Y QUIMICA. NIVELES: PRIMARIA, ESO, BACHILLERATO, GRADO MEDIO, GRADO SUPERIOR, ACCESO UNIVERSIDAD Y GRADUADO EN ESO.
Puesto: Profesor de academia
Estudios:
Licenciado en Matemáticas
Licenciado en Química
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
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Madrid
Localidad: Alcobendas
Fecha de Incorporación: 01/06/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: SEGUIMIENTO DE AUDITORÍAS, DEFINICIÓN, PUBLICACIÓN Y MANTENIMIENTO DE POLÍTICAS Y NORMATIVA INTERNA EN MATERIA DE SEGURIDAD DE LA INFORMACIÓN, CONTROL Y SEGUIMIENTO DE LOS PRESUPUESTOS DE HOLDING Y DE LOS CISOS LOCALES.
Puesto: ESPECIALISTA IT RISK AND GOVERNANCE
Estudios:
TÍTULO DE NIVEL SUPERIOR"))

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
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Madrid
Localidad: Madrid
Fecha de Incorporación: 01/08/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: INGENIERIA Y SEGURIDAD EN PROYECTOS, CRIPTOGRAFÍA, AUTENTICACIÓN Y CONTROL DE ACCESO, TECNOLOGÍAS ANTIFRAUDE "INFORMACIONALES".
Puesto: ESPECIALISTA EN TECNOLOGÍA DE SEGURIDAD
Estudios:
Ingeniero de Telecomunicación
Ingeniero en Informática

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Cordoba
Localidad: Cordoba
Fecha de Incorporación: 25/04/2012
Duración: 6 meses
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: APOYO AL DEPARTAMENTO DE MANTENIMIENTO ELÉCTRICO: CONFECCIÓN Y MODIFICACIÓN DE ESQUEMAS ELÉCTRICOS CON PROGRAMA AUTOCAD. ORGANIZACIÓN DE FICHEROS ELECTRÓNICOS DE PLANOS, EN REDES INFORMÁTICAS DE FYM. ORGANIZACIÓN DE CARPETAS DE PLANOS Y ESQUEMAS
Puesto: Soporte
Estudios:
Ingeniero Técnico Industrial, Especialidad en Electrónica Industrial
Ingeniero Técnico Industrial, Especialidad en Electricidad
Observaciones:
MANEJO PROGRAMA AUTOCAD. PERSONA RESPONSABLE. TURNO DE MAÑANA."))

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
Tipo de Contrato: Eventual por circunstancias de la producción
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: Holanda
Provincia: Zuid Holland
Localidad: Delfgauw
Fecha de Incorporación: 01/05/2012
Duración: 1 año
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: Activities: In the Department of Microbiology various molecular techniques are applied in routine analyses and research projects. The Molecular Biologist/Biochemist is responsible for the routine analyses. The activities are performed according to the ISO 17025 quality assurance system. The candidate will also be involved in the validation and implementation of new methods. This includes optimi- zation and automation of DNA/RNA extractions, develop- ment of bio-PCR and qPCR assays, sequence analysis and other genotyping. Requirements: You are a trained molecular biologist/biochemist at an academic level with extensive experience with DNA-techniques. You are an enthusiastic and accurate professional. You are able to communicate fluently in English, both verbally as in writing.
Puesto: Analista en microbiología
Estudios:
Licenciado en Ciencias. Ciencias Químicas
Licenciado en Biología
Licenciado en Biotecnología
Licenciado en Ciencias Químicas
Licenciado en Ciencias. Biológicas. Biología Fundamental
Idiomas:
INGLÉS
Lectura:AVANZADO Conversación:AVANZADO Escritura:AVANZADO"))

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
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Madrid
Localidad: Alcobendas
Fecha de Incorporación: 01/06/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: GESTIÓN GLOBAL DEL FRAUDE PRODUCIDO EN MEDIOS DE PAGO Y BANCA A DISTANCIA, SOLUCIONES PARA CONTENER LA CIFRA DE FRAUDE. CONTABILIZACIÓN Y REPORTING DEL FRAUDE. PREPARACIÓN DE LA INFRAESTRUCTURA TECNOLÓGICA Y ORGANIZATIVA PARA RESPUESTA A INCIDENTES E INVESTIGACIONES.
Puesto: ESPECIALISTA GLOBAL SECURITY CENTER
Estudios:
Ingeniero de Telecomunicación
Licenciado en Matemáticas
Ingeniero en Informática
Licenciado en Ciencias de Matemáticas. Estadística Matemática

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Guadalajara
Localidad: Guadalajara
Fecha de Incorporación: 26/04/2012
Duración: 6 meses
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: PROGRAMACIÓN PHP. ADMINISTRACIÓN SISTEMAS LINUX/DEBIANA TECNOLÓGICA Y ORGANIZATIVA PARA RESPUESTA A INCIDENTES E INVESTIGACIONES.
Puesto: Programador
Estudios:
Ingeniero en Informática
Ingeniero Técnico en Informática de Gestión
Ingeniero Técnico en Informática de Sistemas"))

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
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: Alemania
Provincia: norrhein-westfalen
Localidad: Köln
Fecha de Incorporación: 02/05/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: PRUEBAS FUNCIONALES DE LOS COMPONENTES ELÉCTRICOS EN EL SISTEMA GENERAL DEL VEHÍCULO, PLANIFICACIÓN Y CONTROL DE LAS ACTIVIDADES DE PRUEBA, DESARROLLO DE CONCEPTOS PARA OPTIMIZACIÓN, REVISAR LAS ESPECIFICACIONES Y CREACIÓN DE INFORMES DE PRUEBAS (PARTE DE IDIOMA EN INGLÉS)
Puesto: INGENIERO DE PRUEBAS ELECTRÓNICA DEL AUTOMÓVIL
Estudios:
Ingeniero de Telecomunicación. Electrónica
Ingeniero de Telecomunicación. Telemática
Ingeniero de Telecomunicación. Señales y Radiocomunicación
Ingeniero en Automática y Electrónica Industrial
Informatica:
AUTOMATAS
Usuario Habitual"))

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
    (store "resultado" "Ofertas para titulados de letras con baja experiencia que han trabajado incluso de becario en empresas pequeñas

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Dinámicas de grupo
Psicotécnicos
Entrevista profunda
Curriculum Vitae
País: España
Provincia: Zaragoza
Localidad: Zaragoza
Fecha de Incorporación: 02/11/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: TRATAMIENTO DE DATOS. EVENTOS CREDITICIOS Y COMERCIALES MEDIANTE TÉCNICAS MULTIVARIANTES (ANÁLISIS CLUSTER, REGRESIÓN LINEAL, REGRESIÓN LOGÍSTICA, PROBIT ORDENADO). SERIES TEMPORALES MODELOS ARIMA. MODELOS DE RATING Y SCORING, RAROC Y PARÁMETROS DE RIESGO. APOYO MATEMÁTICO AL NEGOCIO. DESARROLLO DE ALGORITMOS, ESTADÍSTICA Y PROBABILIDAD. PROYECTOS DE I+D. PROGRAMAS SAS, SPSS, CLEMENTINE, MATLAB
Puesto: Consultor de Negocio
Estudios:
Licenciado en Ciencias y Técnicas Estadísticas
Licenciado en Ciencias de Matemáticas. Estadística Matemática"))

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
    (store "resultado" "Ofertas para titulados de letras con experiencia que han trabajado en empresas pequeñas

.Oferta1.
Tipo de Contrato: Indefinido ordinario, desde el inicio
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Dinámicas de grupo
Psicotécnicos
Curriculum Vitae
País: España
Provincia: Cadiz
Localidad: Cadiz
Fecha de Incorporación: 22/05/2012
Duración: Indefinida
Dotación/Mes: Entre 960 y 1200 euros
Observaciones Dotacion: Sueldo competitivo, con una politica retributiva por encima del sector Interesante progresión salarial.
Detalle Actividades Diarias: Bastantes posibilidades dentro de la Empresa, para promocionar entre los diferentes departamentos existentes en la misma.
Puesto: Consultor de Negocio
Departamento: Tiendas
Estudios:
TÍTULO DE NIVEL MEDIO
TÍTULO DE NIVEL SUPERIOR))

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
    (store "resultado" "Ofertas para titulados de letras con alta experiencia que han trabajado incluso como directivo en empresas pequeñas

.Oferta1.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Dinámicas de grupo
Psicotécnicos
Examen de conocimientos
Entrevista profunda
Curriculum Vitae
País: España
Provincia: Madrid
Localidad: Madrid
Fecha de Incorporación: 01/06/2012
Duración: Indefinida
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: Incorporación e integración en equipos de trabajo de los diferentes proyectos de consultoría: Estrategia, Efectividad Comercial y Marketing, Organización y Procesos, Gestión y Control de Riesgos, Información de Gestión y Financiera.
Puesto: Consultor de Negocio
Estudios:
Licenciado en Administración y Dirección de Empresas
Licenciado en Administración y Dirección de Empresas. Gestión financiera.
Licenciado en Ciencias Económicas y Empresariales))


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
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Malaga
Localidad: Campillos
Fecha de Incorporación: 01/05/2012
Duración: 6 meses
Dotación/Mes: Entre 480 y 720 euros
Observaciones dotacion: 3 dias a la semana horario de mañana o tarde.
Detalle Actividades Diarias: llevar contabilidad nominas y seguros sociales
Puesto: Contabilidad
Estudios:
Licenciado en Administración y Dirección de Empresas
Licenciado en Ciencias Actuariales y Financieras
Diplomado en Ciencias Empresariales
Diplomado en Gestión y Administración Pública
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
Tipo de Contrato: Interinidad para sustituir a los trabajadores durante los períodos de descanso por maternidad
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista profunda
Curriculum Vitae
IMPRESCINDIBLE TENER DISCAPACIDAD SUPERIOR AL 33%
País: España
Provincia: Malaga
Localidad: Malaga
Fecha de Incorporación: 01/06/2012
Duración: 6 meses
Dotación/Mes: Según Convenio
Detalle Actividades Diarias: Apoyo al departamento administrativo de rrhh, funciones generales de la administracion laboral tales como elaboración de nóminas, seguros sociales, distribución de trabajadores, etc.
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
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
SE VALORARÁ LA EXPERIENCIA PROFESIONAL O PSEUDOPROFESIONAL DE LOS CANDIDATOS, LA EXPRESIÓN CORPORAL Y LA CAPACIDAD DE TRANSMISIÓN, ASÍ COMO LA CAPACIDAD DE TRABAJO.
País: España
Provincia: Sevilla
Localidad: Sevilla
Fecha de Incorporación: 10/06/2012
Duración: Indefinida
Dotación/Mes: Retribución Mercantil
Detalle Actividades Diarias: ASENTAMIENTO DE CLIENTELA, COMERCIALIZACIÓN DE CLIENTELA, INFORMES JURÍDICOS Y ECONÓMICOS, DEMANDAS, ARCHIVO, TRANSACCIONES...
Puesto: Gestor
Estudios:
Licenciado en Derecho
Licenciado en Administración y Dirección de Empresas
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
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Dinámicas de grupo
Psicotécnicos
Examen de conocimientos
Entrevista profunda
Curriculum Vitae
País: España
Provincia: Barcelona
Localidad: MONTCADA CENTRE
Fecha de Incorporación: 09/05/2012
Duración: Indefinida
Dotación/Mes: Mas de 1200 euros
Detalle Actividades Diarias: Tras el periodo de formación, las personas que se incorporen serán las responsables de la coordinación y dirección de una zona asignada (entre 4-6 tiendas ), dentro de una área en territorio Nacional, de acuerdo con los objetivos marcados por la empresa y aplicando los principios de simplicidad, eficiencia, eficacia y rentabilidad. Las principales responsabilidades incluyen funciones relativas a la Gestión de Mercancía, Gestión de Costes y Gestión de Personal.
Departamento: Ventas
Puesto: Jefe de ventas
Estudios:
Licenciado en Administración y Dirección de Empresas
Licenciado en Economía
Idiomas
ALEMÁN
Lectura:CUALQUIER NIVEL Conversación:CUALQUIER NIVEL Escritura:CUALQUIER NIVEL
INGLÉS
Lectura:AVANZADO Conversación:AVANZADO Escritura:AVANZADO
Carnet de Conducir: B
Disponibilidad: Todo el dia

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Granada
Localidad: Granada
Fecha de Incorporación: 06/05/2012
Duración: Indefinida
Dotación/Mes: Mas de 1000 euros
Detalle Actividades Diarias: Organizacion y mantenimiento economia empresa
Departamento: Contabilidad
Puesto: Contable
Estudios:
Licenciado en Administración y Dirección de Empresas
Licenciado en Economía
Idiomas
NIVEL
INGLÉS
Lectura:AVANZADO Conversación:AVANZADO Escritura:AVANZADO
Disponibilidad: MAÑANA"))

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
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Dinámicas de grupo
Psicotécnicos
Examen de conocimientos
Entrevista profunda
Curriculum Vitae
País: España
Provincia: Barcelona
Localidad: MONTCADA CENTRE
Fecha de Incorporación: 09/05/2012
Duración: Indefinida
Dotación/Mes: Mas de 1200 euros
Detalle Actividades Diarias: Tras el periodo de formación, las personas que se incorporen serán las responsables de la coordinación y dirección de una zona asignada (entre 4-6 tiendas ), dentro de una área en territorio Nacional, de acuerdo con los objetivos marcados por la empresa y aplicando los principios de simplicidad, eficiencia, eficacia y rentabilidad. Las principales responsabilidades incluyen funciones relativas a la Gestión de Mercancía, Gestión de Costes y Gestión de Personal.
Departamento: Ventas
Puesto: Jefe de ventas
Estudios:
Licenciado en Administración y Dirección de Empresas
Licenciado en Economía
Idiomas
ALEMÁN
Lectura:CUALQUIER NIVEL Conversación:CUALQUIER NIVEL Escritura:CUALQUIER NIVEL
INGLÉS
Lectura:AVANZADO Conversación:AVANZADO Escritura:AVANZADO
Carnet de Conducir: B
Disponibilidad: Todo el dia

.Oferta2.
Tipo de Contrato: Indefinido ordinario
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Curriculum Vitae
País: España
Provincia: Malaga
Localidad: SAN PEDRO DE ALCANTARA
Fecha de Incorporación: 26/04/2012
Duración: 6 meses
Dotación/Mes: segun convenio
Detalle Actividades Diarias: TRATO CON EL CLIENTE, TAREAS DE MARKETING, ORGANIZACIÓN DE EVENTOS.
Departamento: Marketing
Puesto: Responsable Marketing
Estudios:
Licenciado en Publicidad y Relaciones Públicas
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
Modo de Selección: Normal
Selección que aplicará la empresa a los candidatos:
Entrevista preliminar
Dinámicas de grupo
Psicotécnicos
Examen de conocimientos
Entrevista profunda
Curriculum Vitae
País: España
Provincia: Barcelona
Localidad: MONTCADA CENTRE
Fecha de Incorporación: 09/05/2012
Duración: Indefinida
Dotación/Mes: Mas de 1200 euros
Detalle Actividades Diarias: Tras el periodo de formación, las personas que se incorporen serán las responsables de la coordinación y dirección de una zona asignada (entre 4-6 tiendas ), dentro de una área en territorio Nacional, de acuerdo con los objetivos marcados por la empresa y aplicando los principios de simplicidad, eficiencia, eficacia y rentabilidad. Las principales responsabilidades incluyen funciones relativas a la Gestión de Mercancía, Gestión de Costes y Gestión de Personal.
Departamento: Ventas
Puesto: Jefe de ventas
Estudios:
Licenciado en Administración y Dirección de Empresas
Licenciado en Economía
Idiomas
ALEMÁN
Lectura:CUALQUIER NIVEL Conversación:CUALQUIER NIVEL Escritura:CUALQUIER NIVEL
INGLÉS
Lectura:AVANZADO Conversación:AVANZADO Escritura:AVANZADO
Carnet de Conducir: B
Disponibilidad: Todo el dia"))
