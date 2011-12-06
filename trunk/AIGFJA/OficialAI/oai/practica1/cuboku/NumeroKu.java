//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku;

import oai.excepciones.NumeroKuOutOfRangeException;
import oai.practica1.cuboku.util.Orientacion;

/**
 * Clase que representa un numero de una de las caras
 * 
 * @author José Ángel García Fernández
 * @date 1.0 04/12/2011
 * 
 */
public class NumeroKu {

	/**
	 * El numero de esa casilla
	 */
	private int num;

	/**
	 * La orientacion del numero
	 */
	private Orientacion ori;

	public NumeroKu() {

	}

	/**
	 * Genera un objeto de tipo <code>NumeroKu</code>
	 * 
	 * @param n
	 *            el numero
	 * @param ori
	 *            la orientacion
	 * @throws NumeroKuOutOfRangeException
	 *             si <code>num</code> es menor que 0 o mayor que 9
	 */
	public NumeroKu(int n, Orientacion ori) throws NumeroKuOutOfRangeException {
		if (n < 0 || n > 9)
			throw new NumeroKuOutOfRangeException("Numero erroneo: " + n);
		this.num = n;
		this.ori = ori;
	}

	/**
	 * Genera un objeto de tipo <code>NumeroKu</code> con orientacion UP por
	 * defecto
	 * 
	 * @param num
	 *            el numero
	 * @throws NumeroKuOutOfRangeException
	 *             si <code>num</code> es menor que 0 o mayor que 9
	 */
	public NumeroKu(int num) throws NumeroKuOutOfRangeException {
		this(num, Orientacion.up);
	}

	/**
	 * Gira la orientacion 90 grados hacia la izquierda (90º), inverso reloj
	 */
	public void gira90izq() {
		switch (ori) {
		case down:
			ori = Orientacion.right;
			break;
		case up:
			ori = Orientacion.left;
			break;
		case left:
			ori = Orientacion.down;
			break;
		case right:
			ori = Orientacion.up;
			break;
		}
	}

	/**
	 * Gira la orientacion 90 grados hacia la derecha (-90º), reloj
	 */
	public void gira90der() {
		switch (ori) {
		case down:
			ori = Orientacion.left;
			break;
		case up:
			ori = Orientacion.right;
			break;
		case left:
			ori = Orientacion.up;
			break;
		case right:
			ori = Orientacion.down;
			break;
		}
	
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if ((o == null) || (this.getClass() != o.getClass()))
			return false;

		NumeroKu otro = (NumeroKu) o;

		if ((this.num != otro.num) || this.ori != otro.ori)
			return false;
		else
			return true;
	}

	@Override
	public String toString() {
		String retVal = num + "" + ori;
		return retVal;
	};

	/**
	 * Metodo de acceso para la propiedad num
	 * 
	 * @return el num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Metodo modificador para la propiedad num
	 * 
	 * @param num
	 *            el num a poner
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * Metodo de acceso para la propiedad ori
	 * 
	 * @return el ori
	 */
	public Orientacion getOri() {
		return ori;
	}

	/**
	 * Metodo modificador para la propiedad ori
	 * 
	 * @param ori
	 *            el ori a poner
	 */
	public void setOri(Orientacion ori) {
		this.ori = ori;
	}
}
