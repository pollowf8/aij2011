package oai.misioneros;

//package aima.core.environment.eightpuzzle;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

/**
 * @author José Ángel García Fernández
 * 
 */
public class Misioneros {

	/**
	 * Acción para mover un misionero
	 */
	public static Action M = new DynamicAction("M");

	/**
	 * Acción para mover dos misionero
	 */
	public static Action MM = new DynamicAction("MM");

	/**
	 * Acción para mover un canibal
	 */
	public static Action C = new DynamicAction("C");

	/**
	 * Acción para mover 2 canibales
	 */
	public static Action CC = new DynamicAction("CC");

	/**
	 * Acción para mover un misionero y un canibal
	 */
	public static Action MC = new DynamicAction("MC");

	/**
	 * Indica el número de misioneros en la orilla izquierda
	 */
	private int nMisioneros;

	/**
	 * Indica el número de canibales en la orilla izquierda
	 */
	private int nCanibales;

	/**
	 * Indica si la barca esta en la orilla izquierda
	 */
	private boolean barcaIzq;

	public Misioneros() {
		this(3, 3, true);
	}

	public Misioneros(Misioneros mc) {
		this(mc.getnMisioneros(), mc.getnCanibales(), mc.isBarcaIzq());
	}

	public Misioneros(int nMisioneros, int nCanibales, boolean barcaIzq) {
		this.nMisioneros = nMisioneros;
		this.nCanibales = nCanibales;
		this.barcaIzq = barcaIzq;
	}

	// Métodos de movimiento
	// Precondiciones y peligro incluidas en movimientoValido(Action)

	public void moveM() {
		// precondicion
		// if ((barcaIzq && nMisioneros > 0) || (!barcaIzq && nMisioneros < 3))
		// {
		// acciones
		if (barcaIzq)
			nMisioneros--;
		else
			nMisioneros++;
		opuesta();
		// }

	}

	public void moveMM() {
		// precondicion
		// if ((barcaIzq && nMisioneros > 1) || (!barcaIzq && nMisioneros < 2))
		// {
		// acciones
		if (barcaIzq)
			nMisioneros -= 2;
		else
			nMisioneros += 2;
		opuesta();
		// }

	}

	public void moveC() {
		// precondicion
		// if ((barcaIzq && nCanibales > 0) || (!barcaIzq && nCanibales < 3)) {
		// acciones
		if (barcaIzq)
			nCanibales--;
		else
			nCanibales++;
		opuesta();
		// }
	}

	public void moveCC() {
		// precondicion
		// if ((barcaIzq && nCanibales > 1) || (!barcaIzq && nCanibales < 2)) {
		// acciones
		if (barcaIzq)
			nCanibales -= 2;
		else
			nCanibales += 2;
		opuesta();
		// }
	}

	public void moveMC() {
		// precondicion
		// if ((barcaIzq && nCanibales > 0 && nMisioneros > 0)
		// || (!barcaIzq && nCanibales < 3 && nMisioneros < 3)) {
		// acciones
		if (barcaIzq) {
			nCanibales--;
			nMisioneros--;
		} else {
			nCanibales++;
			nMisioneros++;
		}
		opuesta();
		// }
	}

	/**
	 * Cambia la posición de la barca
	 * 
	 * @param pos
	 *            posición de la barca
	 */
	private void opuesta() {
		barcaIzq = (barcaIzq ? false : true);
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
		if (where.equals(M)) {
			if (((barcaIzq && nMisioneros > 0) || (!barcaIzq && nMisioneros < 3))
					&& !peligro(M))
				retVal = true;
		} else if (where.equals(MM)) {
			if (((barcaIzq && nMisioneros > 1) || (!barcaIzq && nMisioneros < 2))
					&& !peligro(MM))
				retVal = true;
		} else if (where.equals(C)) {
			if (((barcaIzq && nCanibales > 0) || (!barcaIzq && nCanibales < 3))
					&& !peligro(C))
				retVal = true;
		} else if (where.equals(CC)) {
			if (((barcaIzq && nCanibales > 1) || (!barcaIzq && nCanibales < 2))
					&& !peligro(CC))
				retVal = true;
		} else if (where.equals(MC)) {
			if (((barcaIzq && nCanibales > 0 && nMisioneros > 0) || (!barcaIzq
					&& nCanibales < 3 && nMisioneros < 3))
					&& !peligro(MC))
				retVal = true;
		}
		return retVal;
	}

	/**
	 * Método para comprobar que el estado resultante de aplicar where sea
	 * seguro
	 * 
	 * @param where
	 *            la accion a realizar
	 * @return si se genera o no un estado de peligro
	 */
	private boolean peligro(Action where) {
		Misioneros mcTest = new Misioneros(this);

		mcTest.move(where);

		if (((mcTest.getnMisioneros() < mcTest.getnCanibales()) && mcTest
				.getnMisioneros() != 0)
				|| ((mcTest.getnMisioneros() > mcTest.getnCanibales()) && mcTest
						.getnMisioneros() != 3))
			return true;
		else
			return false;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		Misioneros campo = (Misioneros) o;

		if (nMisioneros == campo.getnMisioneros()
				&& nCanibales == campo.getnCanibales()
				&& barcaIzq == campo.isBarcaIzq())
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return nMisioneros + nCanibales + (barcaIzq ? 1 : 0);
	}

	@Override
	public String toString() {
		String barca = barcaIzq ? "|B~~|" : "|~~B|";

		String retVal = "M " + nMisioneros + barca + (3 - nMisioneros) + "\n"
				+ "C " + nCanibales + barca + (3 - nCanibales) + "\n";
		// + "Barca en la orilla: " + (barcaIzq ? "Izquierda" : "Derecha");
		return retVal;
	}

	/**
	 * Metodo que ejecuta una accion (no comprueba si es segura ni valida)
	 * 
	 * @param where
	 *            la accion a realizar
	 */
	public void move(Action where) {
		if (where.equals(M)) {
			moveM();
		} else if (where.equals(MM)) {
			moveMM();
		} else if (where.equals(C)) {
			moveC();
		} else if (where.equals(CC))
			moveCC();
		else if (where.equals(MC))
			moveMC();
	}

	public int getnMisioneros() {
		return nMisioneros;
	}

	public void setnMisioneros(int nMisioneros) {
		this.nMisioneros = nMisioneros;
	}

	public int getnCanibales() {
		return nCanibales;
	}

	public void setnCanibales(int nCanibales) {
		this.nCanibales = nCanibales;
	}

	public boolean isBarcaIzq() {
		return barcaIzq;
	}

	public void setBarcaIzq(boolean barcaIzq) {
		this.barcaIzq = barcaIzq;
	}
}