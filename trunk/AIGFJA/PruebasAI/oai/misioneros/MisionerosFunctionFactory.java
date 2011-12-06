package oai.misioneros;

//package aima.core.environment.eightpuzzle;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

/**
 * @author José Ángel García Fernández
 */
public class MisionerosFunctionFactory {
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
			Misioneros campo = (Misioneros) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (campo.movimientoValido(Misioneros.M)) {
				actions.add(Misioneros.M);
			}
			if (campo.movimientoValido(Misioneros.MM)) {
				actions.add(Misioneros.MM);
			}
			if (campo.movimientoValido(Misioneros.C)) {
				actions.add(Misioneros.C);
			}
			if (campo.movimientoValido(Misioneros.CC)) {
				actions.add(Misioneros.CC);
			}
			if (campo.movimientoValido(Misioneros.MC)) {
				actions.add(Misioneros.MC);
			}
			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			Misioneros campo = (Misioneros) s;

			if (Misioneros.M.equals(a)
					&& campo.movimientoValido(Misioneros.M)) {
				Misioneros newCampo = new Misioneros(campo);
				newCampo.moveM();
				return newCampo;
			} else if (Misioneros.MM.equals(a)
					&& campo.movimientoValido(Misioneros.MM)) {
				Misioneros newCampo = new Misioneros(campo);
				newCampo.moveMM();
				return newCampo;
			} else if (Misioneros.C.equals(a)
					&& campo.movimientoValido(Misioneros.C)) {
				Misioneros newCampo = new Misioneros(campo);
				newCampo.moveC();
				return newCampo;
			} else if (Misioneros.CC.equals(a)
					&& campo.movimientoValido(Misioneros.CC)) {
				Misioneros newCampo = new Misioneros(campo);
				newCampo.moveCC();
				return newCampo;
			} else if (Misioneros.MC.equals(a)
					&& campo.movimientoValido(Misioneros.MC)) {
				Misioneros newCampo = new Misioneros(campo);
				newCampo.moveMC();
				return newCampo;
			}
			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}