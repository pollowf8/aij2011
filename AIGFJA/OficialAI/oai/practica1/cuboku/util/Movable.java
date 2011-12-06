//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.util;

import aima.core.agent.Action;

/**
 * Interfaz para un objeto movible
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 05/12/2011
 */
public interface Movable {

	/**
	 * Metodo que realiza la accion where
	 * 
	 * @param where
	 *            la accion a realizar
	 */
	public void move(Action where);

}
