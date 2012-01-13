//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.aima.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import oai.practica1.cuboku.Cuboku;
import oai.practica1.cuboku.aima.CubokuFunctionFactory;
import oai.practica1.cuboku.aima.CubokuGoalTest;
import oai.practica1.cuboku.util.Movable;
import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.HillClimbingSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.UniformCostSearch;

/**
 * Clase con metodos y propiedades de utilidad para usar algoritmos de la
 * libreria AIMA
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.3 13/01/2012
 */
public class AimaUtil {

	/**
	 * Indica en AIMA que no se han realizado operaciones (ya es solucion)
	 */
	public static final String NO_OP = "NoOp";

	/**
	 * Indica en AIMA cuando no se ha alcanzado solucion <code>(P > L)</code>
	 */
	public static final String CUT_OFF = "CutOff";

	// Nueva linea
	public static final String newLine = System.getProperty("line.separator");

	// Indica el file seleccionado
	public static final String keyFile = "file";

	// Indica el algoritmo seleccionado
	public static final String keyAlgoritmo = "algoritmo";

	// Indica la heuristica seleccioanda
	public static final String keyH = "heuristica";

	// Indica la PROFUNDIDAD seleccionada
	public static final String keyProf = "profundidad";

	/**
	 * Ejecuta Depth First Search
	 * 
	 * @param initialState
	 *            el estado inicial
	 * @return el SearchAgent con la informacion del resultado o null en caso de
	 *         excepcion
	 */
	public static SearchAgent DFSDemo(Object initialState) {
		// System.out.println("\nDemo recursive DFS -->");
		try {
			Problem problem = new Problem(initialState,
					CubokuFunctionFactory.getActionsFunction(),
					CubokuFunctionFactory.getResultFunction(),
					new CubokuGoalTest());
			Search search = new DepthFirstSearch(new GraphSearch());
			SearchAgent agent = new SearchAgent(problem, search);
			return agent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ejecuta Breath First Search
	 * 
	 * @param initialState
	 *            el estado inicial
	 * @return el SearchAgent con la informacion del resultado o null en caso de
	 *         excepcion
	 */
	public static SearchAgent BFSDemo(Object initialState) {
		// System.out.println("\nDemo BFS -->");
		try {
			Problem problem = new Problem(initialState,
					CubokuFunctionFactory.getActionsFunction(),
					CubokuFunctionFactory.getResultFunction(),
					new CubokuGoalTest());
			Search search = new BreadthFirstSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			return agent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ejecuta Depth Limited Searh
	 * 
	 * @param initialState
	 *            el estado inicial
	 * @param limit
	 *            PROFUNDIDAD limite
	 * @return el SearchAgent con la informacion del resultado o null en caso de
	 *         excepcion
	 */
	public static SearchAgent DLSDemo(Object initialState, int limit) {
		// System.out.println("\nDemo recursive DLS -->");
		try {
			Problem problem = new Problem(initialState,
					CubokuFunctionFactory.getActionsFunction(),
					CubokuFunctionFactory.getResultFunction(),
					new CubokuGoalTest());
			Search search = new DepthLimitedSearch(limit);
			SearchAgent agent = new SearchAgent(problem, search);
			return agent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ejecuta Uniform Cost Search
	 * 
	 * @param initialState
	 *            el estado inicial
	 * @return el SearchAgent con la informacion del resultado o null en caso de
	 *         excepcion
	 */
	public static SearchAgent UCDemo(Object initialState) {
		// System.out.println("\nDemo UCS -->");
		try {
			Problem problem = new Problem(initialState,
					CubokuFunctionFactory.getActionsFunction(),
					CubokuFunctionFactory.getResultFunction(),
					new CubokuGoalTest());
			Search search = new UniformCostSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			return agent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ejecuta GreedyBestFirstSearch
	 * 
	 * @param initialState
	 *            el estado inicial
	 * @param h
	 *            la heuristica a usar
	 * @return el SearchAgent con la informacion del resultado o null en caso de
	 *         excepcion
	 */
	public static SearchAgent GBFSDemo(Object initialState, HeuristicFunction h) {
		// System.out.println("\nDemo GBFS con " + h + " -->");
		try {
			Problem problem = new Problem(initialState,
					CubokuFunctionFactory.getActionsFunction(),
					CubokuFunctionFactory.getResultFunction(),
					new CubokuGoalTest());
			Search search = new GreedyBestFirstSearch(new GraphSearch(), h);
			SearchAgent agent = new SearchAgent(problem, search);
			return agent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ejecuta Escalada Maxima Pendiente
	 * 
	 * @param initialState
	 *            el estado inicial
	 * @param h
	 *            la heuristica a usar
	 * @return el SearchAgent con la informacion del resultado o null en caso de
	 *         excepcion
	 */
	public static SearchAgent EMPDemo(Object initialState, HeuristicFunction h) {
		// System.out.println("\nDemo EMP con " + h + " -->");
		try {
			Problem problem = new Problem(initialState,
					CubokuFunctionFactory.getActionsFunction(),
					CubokuFunctionFactory.getResultFunction(),
					new CubokuGoalTest());
			Search search = new HillClimbingSearch(h);
			SearchAgent agent = new SearchAgent(problem, search);
			return agent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Ejecuta A* search
	 * 
	 * @param initialState
	 *            el estado inicial
	 * @param h
	 *            la heuristica a usar
	 * @return el SearchAgent con la informacion del resultado o null en caso de
	 *         excepcion
	 */
	public static SearchAgent AStarDemo(Object initialState, HeuristicFunction h) {
		// System.out.println("\nDemo A* con " + h + " -->");
		try {
			Problem problem = new Problem(initialState,
					CubokuFunctionFactory.getActionsFunction(),
					CubokuFunctionFactory.getResultFunction(),
					new CubokuGoalTest());
			Search search = new AStarSearch(new GraphSearch(), h);
			SearchAgent agent = new SearchAgent(problem, search);
			return agent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo de utilidad que genera un Cuboku y lo guarda en un archivo de
	 * texto a partir de una lista de acciones
	 * 
	 * @param actions
	 *            la lista de acciones a realizar
	 */
	public static int generarCubo(List<Action> actions, String path) {
		Cuboku a = new Cuboku(false);
		int nMovs = actions.size();
		if (actions.size() != 0)
			for (int i = 0; i < actions.size(); i++)
				a.move(actions.get(i));
		else {
			Random r = new Random();
			nMovs = (r.nextInt() % 5)+5;
			for (int i = 0; i < nMovs; i++)
				a.move(new DynamicAction(Cuboku.acciones[r.nextInt(18)]));
		}
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(path));
			pw.print(a.toStringSerializa());
			pw.close();
			return nMovs;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Metodo de utilidad que devuelve un String con las propiedades de la
	 * busqueda
	 * 
	 * @param properties
	 *            las propiedades a procesar
	 * @return el string con las propiedades
	 */
	public static String printInstrumentation(Properties properties) {
		StringBuffer sb = new StringBuffer("Propiedades:" + newLine);
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			sb.append(key + " : " + property + newLine);
		}
		return sb.toString();
	}

	/**
	 * Metodo de utilidad que devuelve el valor de una propiedad que contega de
	 * key k
	 * 
	 * @param properties
	 *            las propiedades a procesar
	 * @param k
	 *            la clave a comprobar
	 * @return el string con la propiedad buscada o null si no lo encuentra
	 */
	public static String getPropertie(Properties properties, String k) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			if (key.contains(k))
				return properties.getProperty(key);
		}
		return null;
	}

	/**
	 * Metodo de utilidad que devuelve un String con la lista de acciones
	 * realizadas
	 * 
	 * @param actions
	 *            la lista de acciones realizada
	 * @return el string con las acciones
	 */
	public static String printActions(List<Action> actions) {
		StringBuffer sb = new StringBuffer("Lista de acciones:" + newLine);
		for (int i = 0; i < actions.size(); i++)
			sb.append(actions.get(i).toString() + newLine);
		return sb.toString();
	}

	/**
	 * Metodo de utilidad que devuelve un String con la lista de acciones
	 * realizadas y el estado del Movable en cada momento
	 * 
	 * @param actions
	 *            la lista de acciones realizada
	 * @param movable
	 *            el objeto movido por las acciones
	 * @return el string con las acciones
	 */
	public static String printActions(List<Action> actions, Movable movable) {
		StringBuffer sb = new StringBuffer("Estado Inicial" + newLine + movable);
		for (int i = 0; i < actions.size(); i++) {
			Action a = actions.get(i);
			movable.move(a);
			sb.append(a.toString() + newLine + movable);
		}
		return sb.toString();
	}
}
