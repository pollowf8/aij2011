//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku;

import java.util.Random;

import oai.aima.util.AimaUtil;
import oai.excepciones.NumeroKuOutOfRangeException;
import oai.practica1.cuboku.util.Orientacion;

/**
 * Clase que representa una de las caras del Cuboku
 * 
 * @author José Ángel García Fernández
 * @date 04/12/2011 1.0
 * 
 */
public class Sudoku {

	/**
	 * Representa un nombre abtracto de la cara para reconocerla
	 */
	private String nombre;
	/**
	 * Matriz de NumeroKu que representa la cara
	 */
	private NumeroKu[][] s;

	/**
	 * Dimension de la matriz de NumeroKus
	 */
	public static final short dimension = 3;

	/**
	 * Genera un objeto de tipo <code>Sudoku</code> aletatoriamente o por
	 * defecto. No se asegura que el sudoku sea correcto si se genera
	 * aleatorioamente.
	 * 
	 * @param aleatorio
	 *            o no
	 */
	public Sudoku(boolean aleatorio, String nombre) {
		this.nombre = nombre;
		s = new NumeroKu[dimension][dimension];
		Random r = new Random();
		int k = 1;
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				try {
					if (aleatorio)
						s[i][j] = new NumeroKu(r.nextInt(10),
								Orientacion.rand());
					else
						s[i][j] = new NumeroKu(k++);
				} catch (NumeroKuOutOfRangeException e) {
					e.printStackTrace();
				}
	}

	/**
	 * Genera un objeto de tipo <code>Sudoku</code> a partir de una array
	 * 
	 * @param sArray
	 *            el array de dimension*2 elementos
	 */
	public Sudoku(NumeroKu[] sArray, String nombre) {
		this.nombre = nombre;
		s = new NumeroKu[dimension][dimension];
		int k = 0;
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				s[i][j] = sArray[k++];
	}

	/**
	 * Genera un objeto de tipo Sudoku
	 * 
	 * @param s
	 *            la matriz a establecer (hace copia de los elementos)
	 */
	public Sudoku(NumeroKu[][] s, String nombre) {
		this.nombre = nombre;
		this.s = new NumeroKu[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				this.s[i][j] = s[i][j];
			}
		}
	}

	/**
	 * Constructor copia
	 * 
	 * @param copy
	 *            el array que representa al <code>Cuboku</code>
	 */
	public Sudoku(Sudoku copy) {
		this(copy.s, copy.nombre);
	}

	/**
	 * Devuelve una fila
	 * 
	 * @param f
	 *            la fila a devolver
	 * @return la fila f de <code>s</code>
	 */
	public NumeroKu[] getFila(int f) {
		return s[f];
	}

	/**
	 * Devuelve una columna en base al parametro col
	 * 
	 * @param col
	 *            la columna a devolver
	 * @return la columna col de <code>s</code>
	 */
	public NumeroKu[] getColumna(int col) {
		NumeroKu[] a = new NumeroKu[3];
		// Fijo columna y recorro filas de esa columna
		for (int i = 0; i < dimension; i++) {
			a[i] = s[i][col];
		}
		return a;
	}

	/**
	 * Metodo que sustituye la fila f por numsKu
	 * 
	 * @param f
	 *            que fila modificar
	 * @param numsKu
	 *            el array de NumeroKu a poner en f
	 */
	public void setFila(int f, NumeroKu[] numsKu) {
		s[f] = numsKu;
	}

	/**
	 * Metodo que sustituye la columna j por numsKu
	 * 
	 * @param col
	 *            que columna modificar
	 * @param numsKu
	 *            el array de NumeroKu a poner en col
	 */
	public void setColumna(int col, NumeroKu[] numsKu) {
		// Fijo columna y recorro filas de esa columna sustituyendo por numsKu
		for (int i = 0; i < dimension; i++) {
			s[i][col] = numsKu[i];
		}

	}

	/**
	 * Intercambia la fila i y la columna j
	 * 
	 * @param i
	 *            la fila i
	 * @param j
	 *            la columna j
	 */
	public void intercambiaFpC(int i, int j) {
		NumeroKu[] fila = getFila(i);
		setFila(i, getColumna(j));
		setColumna(j, fila);
	}

	/**
	 * Devuelve true si existe algun NumeroKu repetido en la cara o mal
	 * orientado
	 * 
	 * @return true si hay algun numero repetido en la cara o mal orientado,
	 *         false en caso contrario
	 */
	public boolean noFinal() {
		for (int m = 0; m < dimension; m++) {
			for (int n = 0; n < dimension; n++) {
				NumeroKu a = s[m][n];// obtengo el numeroKu
				for (int i = 0; i < dimension; i++) {
					for (int j = 0; j < dimension; j++) {
						if (((a.getNum() == s[i][j].getNum()) || (a.getOri() != s[i][j]
								.getOri())) && (a != s[i][j]))
							return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Rota la cara hacia la izquierda
	 */
	public void rotaCaraSentInvReloj() {
		// fila 1 es la columna 3 normal pero girando 90ª senInvReloj ori
		// fila 2 es la col 2 normal pero girando 90
		// fila 3 es la col 1 normal
		Sudoku aux = new Sudoku(this);

		setFila(0, aux.getColumna(2));
		setFila(1, aux.getColumna(1));
		setFila(2, aux.getColumna(0));

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				s[i][j].gira90izq();
			}
		}
	}

	/**
	 * Rota la cara hacia la derecha
	 */
	public void rotaCaraSentReloj() {
		// col 1 es la fila 3 normal pero girando 90ª senReloj ori
		// col 2 es la fila 2 normal pero girando 90
		// col 3 es la fila 1 normal

		Sudoku aux = new Sudoku(this);

		setColumna(0, aux.getFila(2));
		setColumna(1, aux.getFila(1));
		setColumna(2, aux.getFila(0));

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				s[i][j].gira90der();
			}
		}
	}

	/**
	 * Metodo de acceso para la propiedad s
	 * 
	 * @return el s
	 */
	public NumeroKu[][] getS() {
		return s;
	}

	/**
	 * Metodo modificador para la propiedad s
	 * 
	 * @param s
	 *            el s a poner
	 */
	public void setS(NumeroKu[][] s) {
		this.s = s;
	}

	/**
	 * Metodo de acceso para la propiedad <code>nombre</code>
	 * 
	 * @return el <code>nombre</code>
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo modificador para la propiedad <code>nombre</code>
	 * 
	 * @param <code>nombre</code> el <code>nombre</code> a poner
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if ((o == null) || (this.getClass() != o.getClass()))
			return false;

		Sudoku otroS = (Sudoku) o;

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++)
				if (!this.s[i][j].equals(otroS.s[i][j]))
					return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(nombre + AimaUtil.newLine);
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++)
				if (j == 2)
					sb.append(s[i][j]);
				else
					sb.append(s[i][j] + " ");
			sb.append(AimaUtil.newLine);
		}
		sb.setLength(sb.length());
		return sb.toString();
	}

	/**
	 * Metodo que devuelve un string para guardar en archivo de texto segun el
	 * formato establecido de serializacion
	 * 
	 * @return el objeto como un string
	 */
	public String toStringSerializa() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++)
				if (j == (dimension - 1))
					sb.append(s[i][j]);
				else
					sb.append(s[i][j] + ";");
			sb.append(AimaUtil.newLine);
		}
		sb.setLength(sb.length());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		int suma = 0;
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				suma += s[i][j].hashCode();
		return suma;
	}
}
