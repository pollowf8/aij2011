package oai.garrafas;

//package aima.core.environment.eightpuzzle;

import aima.core.search.framework.GoalTest;

/**
 * @author José Ángel García Fernández
 * 
 */
public class GarrafasGoalTest implements GoalTest {
	Garrafas goal = new Garrafas(2);

	public boolean isGoalState(Object state) {
		Garrafas aguaGarrafas = (Garrafas) state;
		return aguaGarrafas.equalsGoal(goal);
	}
}