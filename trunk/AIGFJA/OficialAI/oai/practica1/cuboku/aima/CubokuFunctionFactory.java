//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.aima;

import java.util.LinkedHashSet;
import java.util.Set;

import oai.practica1.cuboku.Cuboku;
import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

/**
 * FunctionFactory del cuboku
 * 
 * @author José Ángel García Fernández
 * @version 1.1 25/12/2011
 * 
 */
public class CubokuFunctionFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {

			Set<Action> actions = new LinkedHashSet<Action>();

			actions.add(Cuboku.REL_CARA1);
			actions.add(Cuboku.REL_CARA2);
			actions.add(Cuboku.REL_CARA3);
			actions.add(Cuboku.REL_CARA4);
			actions.add(Cuboku.REL_CARA5);
			actions.add(Cuboku.REL_CARA6);

			// actions.add(Cuboku.INV_CARA1);
			// actions.add(Cuboku.INV_CARA2);
			// actions.add(Cuboku.INV_CARA3);
			// actions.add(Cuboku.INV_CARA4);
			// actions.add(Cuboku.INV_CARA5);
			// actions.add(Cuboku.INV_CARA6);
			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			Cuboku cube = (Cuboku) s;

			if (Cuboku.REL_CARA1.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.REL_CARA1);
				return newBoard;
			} else if (Cuboku.REL_CARA2.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.REL_CARA2);
				return newBoard;
			} else if (Cuboku.REL_CARA3.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.REL_CARA3);
				return newBoard;
			} else if (Cuboku.REL_CARA4.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.REL_CARA4);
				return newBoard;
			} else if (Cuboku.REL_CARA5.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.REL_CARA5);
				return newBoard;
			} else if (Cuboku.REL_CARA6.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.REL_CARA6);
				return newBoard;
			}// INVERSOS
				// } else if (Cuboku.INV_CARA1.equals(a)) {
			// Cuboku newBoard = new Cuboku(cube);
			// newBoard.move(Cuboku.INV_CARA1);
			// return newBoard;
			// } else if (Cuboku.INV_CARA2.equals(a)) {
			// Cuboku newBoard = new Cuboku(cube);
			// newBoard.move(Cuboku.INV_CARA2);
			// return newBoard;
			// } else if (Cuboku.INV_CARA3.equals(a)) {
			// Cuboku newBoard = new Cuboku(cube);
			// newBoard.move(Cuboku.INV_CARA3);
			// return newBoard;
			// } else if (Cuboku.INV_CARA4.equals(a)) {
			// Cuboku newBoard = new Cuboku(cube);
			// newBoard.move(Cuboku.INV_CARA4);
			// return newBoard;
			// } else if (Cuboku.INV_CARA5.equals(a)) {
			// Cuboku newBoard = new Cuboku(cube);
			// newBoard.move(Cuboku.INV_CARA5);
			// return newBoard;
			// } else if (Cuboku.INV_CARA6.equals(a)) {
			// Cuboku newBoard = new Cuboku(cube);
			// newBoard.move(Cuboku.INV_CARA6);
			// return newBoard;
			// }
			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}