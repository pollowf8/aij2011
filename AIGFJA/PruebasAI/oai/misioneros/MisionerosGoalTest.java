package oai.misioneros;

//package aima.core.environment.eightpuzzle;

import aima.core.search.framework.GoalTest;

/**
 * @author Jos� �ngel Garc�a Fern�ndez
 * 
 */
public class MisionerosGoalTest implements GoalTest {
	Misioneros goal = new Misioneros(0, 0, false);

	public boolean isGoalState(Object state) {
		Misioneros campo = (Misioneros) state;
		return campo.equals(goal);
	}
}