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
 * Da los elementos mal colocados (tiene en cuenta solo orientacion)
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.2 16/01/2012
 */
public class H3HeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		Cuboku cuboku = (Cuboku) state;
		return cuboku.numMalOrientados()/12;
	}

	@Override
	public String toString() {
		return "h3";
	}
}