package oai.garrafas;

//package aima.core.environment.eightpuzzle;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

/**
 * @author José Ángel García Fernández
 * 
 */
public class Garrafas {

	/**
	 * Acción para llenar garrafa 4L
	 */
	public static Action llena4 = new DynamicAction("llena4");

	/**
	 * Acción para llenar garrafa 3L
	 */
	public static Action llena3 = new DynamicAction("llena3");

	/**
	 * Acción para vaciar garrafa 4L
	 */
	public static Action vacia4 = new DynamicAction("vacia4");

	/**
	 * Acción para vaciar garrafa 3L
	 */
	public static Action vacia3 = new DynamicAction("vacia3");

	/**
	 * Accion para verter la garrafa de 4L en la de 3L
	 */
	public static Action vierte4 = new DynamicAction("vierte4");

	/**
	 * Accion para verter la garrafa de 3L en la de 4L
	 */
	public static Action vierte3 = new DynamicAction("vierte3");

	/**
	 * Indica la cantidad de agua en la garrafa de 4L
	 */
	private int garrafa4;

	/**
	 * Indica la cantidad de agua en la garrafa de 3L
	 */
	private int garrafa3;

	public Garrafas() {
		this(0, 0);
	}

	public Garrafas(int garrafa4, int garrafa3) {
		this.garrafa4 = garrafa4;
		this.garrafa3 = garrafa3;
	}

	public Garrafas(Garrafas otro) {
		this.garrafa4 = otro.garrafa4;
		this.garrafa3 = otro.garrafa3;
	}

	public Garrafas(int garrafa4Goal) {
		this.garrafa4 = garrafa4Goal;
	}

	// Métodos de movimiento
	// Precondiciones y peligro incluidas en movimientoValido(Action)

	public void llenar4() {
		garrafa4 = 4;
	}

	public void llenar3() {
		garrafa3 = 3;
	}

	public void vaciar4() {
		garrafa4 = 0;
	}

	public void vaciar3() {
		garrafa3 = 0;
	}

	public void verter4() {
		int newGarrafa3 = Math.min(3, garrafa3 + garrafa4);
		garrafa4 = garrafa4 - (newGarrafa3 - garrafa3);
		garrafa3 = newGarrafa3;
	}

	public void verter3() {
		int newGarrafa4 = Math.min(4, garrafa3 + garrafa4);
		garrafa3 = garrafa3 - (newGarrafa4 - garrafa4);
		garrafa4 = newGarrafa4;
	}

	/**
	 * Metodo para comprobar si una accion es valida
	 * 
	 * @param where
	 *            accion destino
	 * @return true si es valida, false en caso contrario
	 */
	public boolean movimientoValido(Action where) {
		boolean retVal = false;
		if (where.equals(llena4)) {
			if (garrafa4 < 4)
				retVal = true;
		} else if (where.equals(llena3)) {
			if (garrafa3 < 3)
				retVal = true;
		} else if (where.equals(vacia4)) {
			if (garrafa4 > 0)
				retVal = true;
		} else if (where.equals(vacia3)) {
			if (garrafa3 > 0)
				retVal = true;
		} else if (where.equals(vierte4)) {
			if (garrafa4 > 0 && garrafa3 < 3)
				retVal = true;
		} else if (where.equals(vierte3)) {
			if (garrafa3 > 0 && garrafa4 < 4)
				retVal = true;
		}
		return retVal;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		Garrafas aguaGarrafas = (Garrafas) o;

		if (garrafa4 == aguaGarrafas.garrafa4
				&& garrafa3 == aguaGarrafas.garrafa3)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return garrafa4 + garrafa3;
	}

	@Override
	public String toString() {
		String garrafas = "| |\t| |\n" + "|" + garrafa4 + "|\t|" + garrafa3
				+ "|";
		// String garrafa4Str = "| |\n| |\n|" + garrafa4 + "|";
		// String garrafa3Str = "| |\n| |\n|" + garrafa3 + "|";

		return garrafas;
	}

	/**
	 * Metodo que ejecuta una accion (no comprueba si es segura ni valida)
	 * 
	 * @param where
	 *            la accion a realizar
	 */
	public void move(Action where) {
		if (where.equals(llena4)) {
			llenar4();
		} else if (where.equals(llena3)) {
			llenar3();
		} else if (where.equals(vacia4)) {
			vaciar4();
		} else if (where.equals(vacia3)) {
			vaciar3();
		} else if (where.equals(vierte4)) {
			verter4();
		} else if (where.equals(vierte3)) {
			verter3();
		}
	}

	public int getGarrafa4() {
		return garrafa4;
	}

	public void setGarrafa4(int garrafa4) {
		this.garrafa4 = garrafa4;
	}

	public int getGarrafa3() {
		return garrafa3;
	}

	public void setGarrafa3(int garrafa3) {
		this.garrafa3 = garrafa3;
	}

	/**
	 * Método equals que no tiene en cuenta la variable garrafa3
	 * 
	 * @param o
	 *            otro Garrafas
	 * @return si o es o no goal
	 */
	public boolean equalsGoal(Garrafas o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		Garrafas aguaGarrafas = (Garrafas) o;

		if (garrafa4 == aguaGarrafas.garrafa4)
			return true;
		return false;
	}
}