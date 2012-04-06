//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 2
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica2.recomendador;

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

	public Recomendador(String file) throws JessException {
		m_rete = new Rete();
		this.file = file;
		// valores por defecto
		setDefecto();
	}

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
	private Value nombre, apellidos, edad, estudios, acabada, docente,
			investigador, puesto, duracion, empresa;

	/**
	 * Claves para usar en store-fetch
	 */
	public static final String Knombre = "nombre", Kapellidos = "apellidos",
			Kedad = "edad", Kestudios = "estudios", Kacabada = "acabada",
			Kdocente = "docente", Kinvestigador = "investigador",
			Kpuesto = "puesto", Kduracion = "duracion", Kempresa = "empresa";
	/**
	 * Clave de vuelta
	 */
	public static final String Kvuelta = "resultado";

	/**
	 * Método que obtiene el resultado en un string
	 * 
	 * @return la recomendacion
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Metodo que realizar la ejecucion
	 */
	public void ejecucion() {
		storeCurriculum();
		run();
		halt();
		result = m_rete.fetch(Kvuelta).toString();
	}

	/**
	 * Guarda la informacion del curriculum en el MR
	 */
	private void storeCurriculum() {
		m_rete.store(Knombre, nombre);
		m_rete.store(Kapellidos, apellidos);
		m_rete.store(Kedad, edad);
		m_rete.store(Kestudios, estudios);
		m_rete.store(Kacabada, acabada);
		m_rete.store(Kdocente, docente);
		m_rete.store(Kinvestigador, investigador);
		m_rete.store(Kpuesto, puesto);
		m_rete.store(Kduracion, duracion);
		m_rete.store(Kempresa, empresa);
	}

	/**
	 * Establece por defecto valores del curriculum
	 * 
	 * @throws JessException
	 */
	public void setDefecto() throws JessException {
		setNombre("Default");
		setApellidos("Default Ape");
		setEdad(21);
		setEstudios("UNI");
		setAcabada("SI");
		setDocente("NO");
		setInvestigador("NO");
		setPuesto("miembro");
		setDuracion(6);
		setEmpresa("media");
	}

	/**
	 * Obtiene e imprime la lista de hechos
	 */
	public void listaHechos() {
		java.util.Iterator iterador; // java.util.Iterator
		iterador = m_rete.listFacts();
		while (iterador.hasNext()) {
			System.out.println(iterador.next());
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
	 * Carga el programa en el motor de reglas
	 * 
	 * @param nombre
	 *            del programa
	 */
	public void cargaPrograma() {
		try {
			// m_rete.eval("(batch \"" + nombre + "\")");
			Value v = m_rete.batch(file);
			System.out.println("Value " + v);
		} catch (JessException je0) {
			System.out.println("Error: no puedo leer programa " + nombre);
			je0.printStackTrace();
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
