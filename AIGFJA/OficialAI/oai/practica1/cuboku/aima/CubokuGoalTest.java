//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.aima;

import oai.practica1.cuboku.Cuboku;
import aima.core.search.framework.GoalTest;

/**
 * Clase que comprueba si es un estado final o no
 * 
 * @author José Ángel García Fernández
 * @date 1.0 04/12/2011
 * 
 */
public class CubokuGoalTest implements GoalTest {

	public boolean isGoalState(Object state) {
		Cuboku cube = (Cuboku) state;
		return !cube.noFinal();
	}
}