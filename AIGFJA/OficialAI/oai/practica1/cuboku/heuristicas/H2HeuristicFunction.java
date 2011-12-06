//
// Universidad Complutense de Madrid
// Ingenier�a Inform�tica
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingenier�a del Conocimiento
//
package oai.practica1.cuboku.heuristicas;


import oai.practica1.cuboku.Cuboku;
import aima.core.search.framework.HeuristicFunction;


/**
 * Heuristica
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 04/12/2011
 */
public class H2HeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		Cuboku board = (Cuboku) state;
		return 0;
	}
	

	@Override
	public String toString() {
		return "h2";
	}

}