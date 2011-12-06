package oai.garrafas;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

/**
 * @author José Ángel García Fernández
 * 
 */

public class GarrafasDemo {
	static Garrafas garrafasInicio = new Garrafas();

	public static void main(String[] args) {
		garrafasDLSDemo();
		garrafasIDLSDemo();
		garrafasGreedyBestFirstDemo();
		garrafasAStarDemo();
	}

	private static void garrafasDLSDemo() {
		System.out.println("\nGarrafasDemo recursive DLS -->");
		try {
			Problem problem = new Problem(garrafasInicio,
					GarrafasFunctionFactory.getActionsFunction(),
					GarrafasFunctionFactory.getResultFunction(),
					new GarrafasGoalTest());
			Search search = new DepthLimitedSearch(15);
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void garrafasIDLSDemo() {
		System.out.println("\nGarrafasDemo Iterative DLS -->");
		try {
			Problem problem = new Problem(garrafasInicio,
					GarrafasFunctionFactory.getActionsFunction(),
					GarrafasFunctionFactory.getResultFunction(),
					new GarrafasGoalTest());
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void garrafasGreedyBestFirstDemo() {
		System.out
				.println("\nGarrafasDemo Greedy Best First Search -->");
		try {
			Problem problem = new Problem(garrafasInicio,
					GarrafasFunctionFactory.getActionsFunction(),
					GarrafasFunctionFactory.getResultFunction(),
					new GarrafasGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(),
					new Garrafas2LHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void garrafasAStarDemo() {
		System.out
				.println("\nGarrafasDemo AStar Search -->");
		try {
			Problem problem = new Problem(garrafasInicio, GarrafasFunctionFactory
					.getActionsFunction(), GarrafasFunctionFactory
					.getResultFunction(), new GarrafasGoalTest());
			Search search = new AStarSearch(new GraphSearch(),
					new Garrafas2LHeuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}
	}

	private static void printActions(List<Action> actions) {
		Garrafas m = new Garrafas();
		System.out.println(m);
		for (int i = 0; i < actions.size(); i++) {

			String action = actions.get(i).toString();
			System.out.println(action);
			m.move(actions.get(i));
			System.out.println(m);
		}
	}
}