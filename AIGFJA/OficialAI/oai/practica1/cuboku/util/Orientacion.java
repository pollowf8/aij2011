//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.util;

import java.util.Random;

/**
 * Enumeracion que representa la orientacion del numero en su cara
 * 
 * @author José Ángel García Fernández
 * @date 04/12/2011 1.0
 * 
 */
public enum Orientacion {
	left {
		@Override
		public String toString() {
			return "L";
		}
	},
	right {
		@Override
		public String toString() {
			return "R";
		}
	},
	up {
		@Override
		public String toString() {
			return "U";
		}
	},
	down {
		@Override
		public String toString() {
			return "D";
		}
	};

	/**
	 * Obtiene la enumeracion a partir de un entero
	 * 
	 * @param a
	 *            el entero
	 * @return la enumeracion que representa <code>a</code>
	 */
	public static Orientacion getEnum(int a) {
		return values()[a];
	}
	
	/**
	 * Obtiene la enumeracion a partir de un String
	 * 
	 * @param a
	 *            el string
	 * @return la enumeracion que representa <code>a</code>
	 */
	public static Orientacion getEnum(String a) {
		for(int i=0;i<length();i++){
			if(a.equals(getEnum(i).toString()))
			return getEnum(i);
		}
		//no es encontro
		return null;
		
	}

	/**
	 * Metodo para obtener la longitud de la enumeracion (numero de elementos)
	 * 
	 * @return la longitud de la enumeracion
	 */
	public static int length() {
		return values().length;
	}

	/**
	 * Obtiene una enumeracion aleatoria
	 * 
	 * @return la enumeracion generada aletaoriamente
	 */
	public static Orientacion rand() {
		Random r = new Random();
		return (Orientacion) getEnum((r.nextInt(length())));
	}

}
