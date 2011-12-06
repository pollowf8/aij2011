//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.util;

/**
 * Enumeracion que representa las distintas busquedas
 * 
 * @author José Ángel García Fernández
 * @date 05/12/2011 1.0
 * 
 */
public enum Algoritmo {
	PROFUNDIDAD {
		@Override
		public String toString() {
			return "Primero en profundidad";
		}
	},
	ANCHURA {
		@Override
		public String toString() {
			return "Primero en anchura";
		}
	},
	PROFUNDIDADLIM {
		@Override
		public String toString() {
			return "Profundidad Limitada";
		}
	},
	UNIFORME {
		@Override
		public String toString() {
			return "Coste Uniforme";
		}
	},
	VORAZ {
		@Override
		public String toString() {
			return "Voraz";
		}
	},
	A {
		@Override
		public String toString() {
			return "A Estrella";
		}
	},
	ESCALADAMAXPEND {
		@Override
		public String toString() {
			return "Escalada Maxima Pendiente";
		}
	},
	;
	/**
	 * Obtiene la enumeracion a partir de un entero
	 * 
	 * @param a
	 *            el entero
	 * @return la enumeracion que representa <code>a</code>
	 */
	public static Algoritmo getEnum(int a) {
		return values()[a];
	}
}
