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
 * Heuristica de
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 04/12/2011
 */
public class H1HeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		Cuboku board = (Cuboku) state;
		int retVal = 0;
		
		return retVal;
	}
	
	@Override
	public String toString() {
		return "h1";
	}
}