//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.principal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import oai.aima.util.AimaUtil;
import oai.practica1.cuboku.Cuboku;
import aima.core.search.framework.SearchAgent;

/**
 * Clase de prueba para el cuboku
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 04/12/2011
 */
public class CubokuDemo {

	// private static Cuboku cubokuRandom = new Cuboku();

	private static String path = "./cuboIsi";
	private static String ext = ".txt";
	private static Cuboku cubokuBase = new Cuboku(false);
	private static Cuboku cubokuFile = new Cuboku(path + ext);
	private static Cuboku cubokuFile2 = new Cuboku(path + ext);
	private static PrintWriter pw;

	public static void main(String[] args) {
		try {
			pw = new PrintWriter(new FileWriter(path + ".log"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 cubokuBase.move(Cuboku.RIGHT_SUP);
//		 cubokuBase.move(Cuboku.DOWN_IZQ);
//		 pw.print(cubokuBase.toStringSerializa());
		// no informadas
		// cubokuDFSDemo();
		// cubokuBFSDemo();
		SearchAgent agent = AimaUtil.DLSDemo(cubokuFile,6);
		String camino = AimaUtil.printActions(agent.getActions(), new Cuboku(
				cubokuFile));
		String proper=AimaUtil.printInstrumentation(agent.getInstrumentation());
		System.out.print(camino);
		System.out.print(proper);
		pw.print(camino);
		pw.print(proper);
		// cubokuUCDemo();

		// Informadas
		// H1
		/*
		 * H1HeuristicFunction h1 = new H1HeuristicFunction();
		 * cubokuGBFSDemo(h1); cubokuEMPDemo(h1); cubokuAStarDemo(h1); // H2
		 * H1HeuristicFunction h2 = new H1HeuristicFunction();
		 * cubokuGBFSDemo(h2); cubokuEMPDemo(h2); cubokuAStarDemo(h2); // H3
		 * H1HeuristicFunction h3 = new H1HeuristicFunction();
		 * cubokuGBFSDemo(h3); cubokuEMPDemo(h3); cubokuAStarDemo(h3);
		 */
		pw.close();
	}
}