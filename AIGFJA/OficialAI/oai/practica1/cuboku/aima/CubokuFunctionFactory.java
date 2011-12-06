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
 * @date 1.0 04/12/2011
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

			actions.add(Cuboku.RIGHT_SUP);

			actions.add(Cuboku.RIGHT_MEDIO);

			actions.add(Cuboku.RIGHT_INF);

			actions.add(Cuboku.DOWN_IZQ);

			actions.add(Cuboku.DOWN_MEDIO);

			actions.add(Cuboku.DOWN_DER);

			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			Cuboku cube = (Cuboku) s;

			if (Cuboku.RIGHT_SUP.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.RIGHT_SUP);
				return newBoard;
			} else if (Cuboku.RIGHT_MEDIO.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.RIGHT_MEDIO);
				return newBoard;
			} else if (Cuboku.RIGHT_INF.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.RIGHT_INF);
				return newBoard;
			} else if (Cuboku.DOWN_IZQ.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.DOWN_IZQ);
				return newBoard;
			} else if (Cuboku.DOWN_MEDIO.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.DOWN_MEDIO);
				return newBoard;
			} else if (Cuboku.DOWN_DER.equals(a)) {
				Cuboku newBoard = new Cuboku(cube);
				newBoard.move(Cuboku.DOWN_DER);
				return newBoard;
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}