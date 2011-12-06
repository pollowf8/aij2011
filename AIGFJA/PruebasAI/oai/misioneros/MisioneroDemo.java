package oai.misioneros;

//package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

/**
 * @author José Ángel García Fernández
 * 
 */

public class MisioneroDemo {
	static Misioneros MisionerosInicio = new Misioneros();

	public static void main(String[] args) {
		misionerosDLSDemo();
		misionerosIDLSDemo();

	}
	
	private static void misionerosDLSDemo() {
		System.out.println("\nMisionerosDemo recursive DLS -->");
		try {
			Problem problem = new Problem(MisionerosInicio,
					MisionerosFunctionFactory.getActionsFunction(),
					MisionerosFunctionFactory.getResultFunction(),
					new MisionerosGoalTest());
			Search search = new DepthLimitedSearch(15);
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void misionerosIDLSDemo() {
		System.out.println("\nMisionerosDemo Iterative DLS -->");
		try {
			Problem problem = new Problem(MisionerosInicio,
					MisionerosFunctionFactory.getActionsFunction(),
					MisionerosFunctionFactory.getResultFunction(),
					new MisionerosGoalTest());
			Search search = new IterativeDeepeningSearch();
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
		Misioneros m = new Misioneros();
		System.out.println(m);
		for (int i = 0; i < actions.size(); i++) {
			
			String action = actions.get(i).toString();
			System.out.println(action);
			m.move(actions.get(i));
			System.out.println(m);
		}
	}

}