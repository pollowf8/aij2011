package oai.jess.ejemplo;

import jess.*;
import java.io.*;

public class Jesster2 { // The inference engine
	private static Rete m_rete;
	// Fichero que almacena el fuente del programa clips que vamos a cargar
	private static String programaFuente;

	// Constructor
	public Jesster2() {
		m_rete = new Rete();
		programaFuente = "cadenas.clp";
		System.out.println("Cargando programa  " + programaFuente + "  ");
		cargaPrograma(programaFuente);

	}

	public static void main(String[] args) {

		Jesster2 jesso;
		jesso = new Jesster2();
		reset();

		String cadena = "7372";
		Value v1 = null;
		try {
			v1 = new Value("7372", RU.STRING);
		} catch (JessException ex) {
		}

		m_rete.store("teclas_pulsadas", v1);

		run();
		listaHechos();

		Value v = m_rete.fetch("palabra_sugerida");
		System.out.println("La palabra sugerida es: ");
		System.out.println(v.toString());

		halt();

	}

	// obtiene e imprime la lista de hechos
	public static void listaHechos() {
		java.util.Iterator iterador; // java.util.Iterator
		iterador = m_rete.listFacts();
		while (iterador.hasNext()) {
			System.out.println(iterador.next());
		}
	}

	public static void halt() {
		try {
			m_rete.halt();
		} catch (JessException je3) {
			System.out.println("Error: no puedo detener programa ");
		}
	}

	public static void cargaPrograma(String nombre) {
		try {
			// m_rete.eval("(batch \"" + nombre + "\")");
			Value v = m_rete.batch(nombre);

			System.out.println("Value " + v);
		} catch (JessException je0) {
			System.out.println("Error: no puedo leer programa " + nombre);
			je0.printStackTrace();
		}
	}

	public static void reset() {
		try {
			m_rete.reset();
		} catch (JessException je2) {
			System.out.println("Error: no puedo resetear ");

			je2.printStackTrace();
		}
	}

	public static void run() {
		try {
			m_rete.run();
		} catch (JessException je4) {
			System.out.println("Error: no puedo ejecutar ");

			je4.printStackTrace();
		}
	}
}
