package oai.garrafas;

//package aima.core.environment.eightpuzzle;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

/**
 * @author José Ángel García Fernández
 */
public class GarrafasFunctionFactory {
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
			Garrafas aguaGarrafas = (Garrafas) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (aguaGarrafas.movimientoValido(Garrafas.llena4)) {
				actions.add(Garrafas.llena4);
			}
			if (aguaGarrafas.movimientoValido(Garrafas.llena3)) {
				actions.add(Garrafas.llena3);
			}
			if (aguaGarrafas.movimientoValido(Garrafas.vacia4)) {
				actions.add(Garrafas.vacia4);
			}
			if (aguaGarrafas.movimientoValido(Garrafas.vacia3)) {
				actions.add(Garrafas.vacia3);
			}
			if (aguaGarrafas.movimientoValido(Garrafas.vierte4)) {
				actions.add(Garrafas.vierte4);
			}
			if (aguaGarrafas.movimientoValido(Garrafas.vierte3)) {
				actions.add(Garrafas.vierte3);
			}
			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			Garrafas aguaGarrafas = (Garrafas) s;

			if (Garrafas.llena4.equals(a)
					&& aguaGarrafas.movimientoValido(Garrafas.llena4)) {
				Garrafas newCampo = new Garrafas(aguaGarrafas);
				newCampo.llenar4();
				return newCampo;
			} else if (Garrafas.llena3.equals(a)
					&& aguaGarrafas.movimientoValido(Garrafas.llena3)) {
				Garrafas newCampo = new Garrafas(aguaGarrafas);
				newCampo.llenar3();
				return newCampo;
			} else if (Garrafas.vacia4.equals(a)
					&& aguaGarrafas.movimientoValido(Garrafas.vacia4)) {
				Garrafas newCampo = new Garrafas(aguaGarrafas);
				newCampo.vaciar4();
				return newCampo;
			} else if (Garrafas.vacia3.equals(a)
					&& aguaGarrafas.movimientoValido(Garrafas.vacia3)) {
				Garrafas newCampo = new Garrafas(aguaGarrafas);
				newCampo.vaciar3();
				return newCampo;
			} else if (Garrafas.vierte4.equals(a)
					&& aguaGarrafas.movimientoValido(Garrafas.vierte4)) {
				Garrafas newCampo = new Garrafas(aguaGarrafas);
				newCampo.verter4();
				return newCampo;
			} else if (Garrafas.vierte3.equals(a)
					&& aguaGarrafas.movimientoValido(Garrafas.vierte3)) {
				Garrafas newCampo = new Garrafas(aguaGarrafas);
				newCampo.verter3();
				return newCampo;
			}
			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}