package oai.garrafas;

import aima.core.search.framework.HeuristicFunction;

/**
 * @author José Ángel García Fernández
 * 
 */
public class Garrafas2LHeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		Garrafas garrafas = (Garrafas) state;
		return Math.abs(garrafas.getGarrafa4() - 2);
	}

}