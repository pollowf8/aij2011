//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.heuristicas;

import oai.practica1.cuboku.Cuboku;
import aima.core.search.framework.HeuristicFunction;

/**
 * Da los elementos mal colocados (tiene en cuenta orientacion y repetidos)
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.1 25/12/2011
 */
public class H1HeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		Cuboku cuboku = (Cuboku) state;
		return cuboku.numDescolocados();
	}

	@Override
	public String toString() {
		return "h1";
	}
}