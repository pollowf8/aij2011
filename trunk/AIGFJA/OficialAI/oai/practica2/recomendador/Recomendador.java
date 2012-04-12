//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 2
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica2.recomendador;

import java.util.Iterator;

import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;

/**
 * Clase que conecta con el motor de jess
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 06/04/2012
 */
public class Recomendador {

	/**
	 * Motor de reglas
	 */
	private Rete m_rete;

	/**
	 * Archivo de reglas
	 */
	private String file;

	/**
	 * Resultado de la ejecucion
	 */
	private String result;

	/**
	 * Values para jess
	 */
	private Value nombre = null, apellidos = null, edad = null,
			estudios = null, acabada = null, tipo = null, docente = null,
			investigador = null, puesto = null, duracion = null,
			empresa = null;

	/**
	 * Claves para usar en store-fetch
	 */
	public static final String Knombre = "nombre", Kapellidos = "apellidos",
			Kedad = "edad", Kestudios = "estudios", Kacabada = "acabada",
			Ktipo = "tipo", Kdocente = "docente",
			Kinvestigador = "investigador", Kpuesto = "puesto",
			Kduracion = "duracion", Kempresa = "empresa";
	/**
	 * Clave de vuelta
	 */
	public static final String Kvuelta = "resultado";

	/**
	 * Genera un objeto de tipo <code>Recomendador</code>
	 * 
	 * @param file
	 *            el nombre del archivo
	 * @throws JessException
	 */
	public Recomendador(String file) throws JessException {
		m_rete = new Rete();
		this.file = file;
		// valores por defecto basicos
		setBasico();
	}

	/**
	 * Resetea values del Recomendador
	 */
	public void clearValues() {
		nombre = apellidos = edad = estudios = acabada = tipo = docente = investigador = puesto = duracion = empresa = null;
	}

	/**
	 * Guarda la informacion del curriculum en el MR
	 */
	private void storeCurriculum() {
		if (nombre != null)
			m_rete.store(Knombre, nombre);
		if (apellidos != null)
			m_rete.store(Kapellidos, apellidos);
		if (edad != null)
			m_rete.store(Kedad, edad);
		if (estudios != null)
			m_rete.store(Kestudios, estudios);
		if (acabada != null)
			m_rete.store(Kacabada, acabada);
		if (tipo != null)
			m_rete.store(Ktipo, tipo);
		if (docente != null)
			m_rete.store(Kdocente, docente);
		if (investigador != null)
			m_rete.store(Kinvestigador, investigador);
		if (puesto != null)
			m_rete.store(Kpuesto, puesto);
		if (duracion != null)
			m_rete.store(Kduracion, duracion);
		if (empresa != null)
			m_rete.store(Kempresa, empresa);
	}

	/**
	 * Método que obtiene el resultado en un string
	 * 
	 * @return la recomendacion
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Metodo que realiza la ejecucion
	 */
	public void ejecucion() {
		cargaPrograma();
		storeCurriculum();
		reset();
		run();
		// listaHechos();
		Value v = m_rete.fetch(Kvuelta);
		if (v != null)
			result = v.toString();
		halt();
	}

	/**
	 * Establece por defecto valores del curriculum basicos
	 * 
	 * @throws JessException
	 */
	private void setBasico() throws JessException {
		setNombre("defaultNom");
		setApellidos("defaultApe");
		setEstudios("bachCiencias");
		setEdad(17);
		setDuracion(0);
	}

	/**
	 * Obtiene e imprime la lista de hechos
	 */
	public void listaHechos() {
		Iterator iterador; // java.util.Iterator
		iterador = m_rete.listFacts();
		while (iterador.hasNext()) {
			System.out.println(iterador.next());
		}
	}

	/**
	 * Carga el programa en el motor de reglas
	 * 
	 */
	public void cargaPrograma() {
		try {
			// m_rete.eval("(batch \"" + nombre + "\")");
			Value v = m_rete.batch(file);
			// System.out.println("Value " + v);
		} catch (JessException je0) {
			System.out.println("Error: no puedo leer programa " + nombre);
			je0.printStackTrace();
		}
	}

	/**
	 * Ejecuta el motor de reglas
	 */
	public void run() {
		try {
			m_rete.run();
		} catch (JessException je4) {
			System.out.println("Error: no puedo ejecutar ");
			je4.printStackTrace();
		}
	}

	/**
	 * Resetea el motor de reglas
	 */
	public void reset() {
		try {
			m_rete.reset();
		} catch (JessException je2) {
			System.out.println("Error: no puedo resetear ");
			je2.printStackTrace();
		}
	}

	/**
	 * Detiene el motor de reglas
	 */
	public void halt() {
		try {
			m_rete.halt();
		} catch (JessException je3) {
			System.out.println("Error: no puedo detener programa ");
		}
	}

	/**
	 * Limpia el motor de reglas
	 */
	public void clear() {
		try {
			m_rete.clear();
			clearValues();
		} catch (JessException je3) {
			System.out.println("Error: no puedo clear programa ");
		}
	}

	// getter and setter
	public Value getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws JessException {
		this.nombre = new Value(nombre, RU.SYMBOL);
	}

	public Value getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) throws JessException {
		this.apellidos = new Value(apellidos, RU.SYMBOL);
	}

	public Value getEdad() {
		return edad;
	}

	public void setEdad(int edad) throws JessException {
		this.edad = new Value(edad, RU.INTEGER);
	}

	public Value getEstudios() {
		return estudios;
	}

	public void setEstudios(String estudios) throws JessException {
		this.estudios = new Value(estudios, RU.SYMBOL);
	}

	public Value getAcabada() {
		return acabada;
	}

	public void setAcabada(String acabada) throws JessException {
		this.acabada = new Value(acabada, RU.SYMBOL);
	}

	public Value getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) throws JessException {
		this.tipo = new Value(tipo, RU.SYMBOL);
	}

	public Value getDocente() {
		return docente;
	}

	public void setDocente(String docente) throws JessException {
		this.docente = new Value(docente, RU.SYMBOL);
	}

	public Value getInvestigador() {
		return investigador;
	}

	public void setInvestigador(String investigador) throws JessException {
		this.investigador = new Value(investigador, RU.SYMBOL);
	}

	public Value getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) throws JessException {
		this.puesto = new Value(puesto, RU.SYMBOL);
	}

	public Value getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) throws JessException {
		this.duracion = new Value(duracion, RU.INTEGER);
	}

	public Value getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) throws JessException {
		this.empresa = new Value(empresa, RU.SYMBOL);
	}
}
