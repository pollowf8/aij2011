package oai.garrafas;

//package aima.core.environment.eightpuzzle;

import aima.core.search.framework.GoalTest;

/**
 * @author Jos� �ngel Garc�a Fern�ndez
 * 
 */
public class GarrafasGoalTest implements GoalTest {
	Garrafas goal = new Garrafas(2);

	public boolean isGoalState(Object state) {
		Garrafas aguaGarrafas = (Garrafas) state;
		return aguaGarrafas.equalsGoal(goal);
	}
}