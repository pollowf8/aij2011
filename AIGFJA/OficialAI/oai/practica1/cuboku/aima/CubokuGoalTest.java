//
// Universidad Complutense de Madrid
// Ingenier�a Inform�tica
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingenier�a del Conocimiento
//
package oai.practica1.cuboku.aima;

import oai.practica1.cuboku.Cuboku;
import aima.core.search.framework.GoalTest;

/**
 * Clase que comprueba si es un estado final o no
 * 
 * @author Jos� �ngel Garc�a Fern�ndez
 * @date 1.0 04/12/2011
 * 
 */
public class CubokuGoalTest implements GoalTest {

	public boolean isGoalState(Object state) {
		Cuboku cube = (Cuboku) state;
		return !cube.noFinal();
	}
}