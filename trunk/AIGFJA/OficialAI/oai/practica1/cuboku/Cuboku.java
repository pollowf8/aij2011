//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import oai.aima.util.AimaUtil;
import oai.excepciones.NumeroKuOutOfRangeException;
import oai.practica1.cuboku.util.Movable;
import oai.practica1.cuboku.util.Orientacion;
import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

/**POSIBLES IMPLEMENTACIONES
 * como cojones implementamos esto, Un array de 6 elementos, donde cada uno es
 * una cara, del 1 al 6, y cada cara tiene 9 elementos, k tienen su valor y la
 * "posicion" con respecto al resto, torcido izquierda, torcido derecha, al
 * reves, correcto
 * Mi cubo sera numerdado, dviendolo de frente, cara 1, giras a derecha cara 2,3,4 hastal legar a 1, y lego 5 la de arriba y 6 abajo
 * 
 /** MOVIMIENTOS tengo k poder girar a la izquierda y derecha x 3 horizontal, y
 arriba y abajo 3 vertical , kizas se puede arreglar para k no haga falta izq o der ni up o down, ya que girando a la izquierda llego a donde llega la derecha pero con 3movs mas
 */
/** Es decir tengo k intercambiar valores de las caras,
 * k esten contiguas, distinguiendo si muevo del centro, es decir de las aristas
 * y central,*/
/** O de los extremos, ya que entonces tambien se ve afecta la
 *  de ese lado
 */
/**
 * Esto es, si muevo horizontal inferior izquierda, la cara 1, se modifica, las caras 2,3,4, y la 6 por ser inferior, como?,
 *  pues los datos de la fila inferior de 1 seran los de 2, los de 2 los de 3, los de 3 los de 4, y, 
 *  en la cara 6, hay una rotacion, k es pasar las filas a columnas , la priemra fila la columna 3, la fila 2 la col 2 y la fila 3 la col 1
 *
 *
 */
/**
 * o en plan feo,
 * 
 * Hacer array de 6 matrices, cada matriz tiene 9 numCuboku, que tienen valor y Orientacion
 * 
 * Operadores
 * Gi
 * 
 * 
 */

/**
 * Clase que representa el cubo del Cuboku
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 04/12/2011
 */
public class Cuboku implements Movable {

	// Movimientos horizontales
	public static Action RIGHT_SUP = new DynamicAction("RightSuperior");

	public static Action RIGHT_INF = new DynamicAction("RightInferior");

	public static Action RIGHT_MEDIO = new DynamicAction("RightMedio");

	// Movimientos Verticales
	public static Action DOWN_IZQ = new DynamicAction("DownIzquierda");

	public static Action DOWN_MEDIO = new DynamicAction("DownMedio");

	public static Action DOWN_DER = new DynamicAction("DownDerecha");

	/**
	 * Representa el cubo con las 6 caras, visto desde alzado, las caras estan
	 * numeradas hacia la derecha y luego arriba y abajo, es decir
	 * <code>1->2->3->4, arriba 5, abajo 6</code
	 */
	private Sudoku[] cube;

	/**
	 * Numero de caras del cubo (6)
	 */
	public static final byte nCaras = 6;

	/**
	 * Nombre para el cuboku
	 */
	private String nombre;

	/**
	 * Genera un objeto de tipo <code>Cuboku</code> aleatoriamente o por
	 * defecto. No se asegura que el cubo sea correcto si se genera
	 * aleatoriamente
	 * 
	 * @param aleatorio
	 *            o no
	 */
	public Cuboku(boolean aleatorio) {
		nombre = aleatorio ? "cubokuAleatorio" : "cubokuDefecto";
		cube = new Sudoku[nCaras];
		for (int i = 0; i < cube.length; i++)
			cube[i] = new Sudoku(aleatorio, "Cara " + i);
	}

	/**
	 * Genera un objeto de tipo <code>Cuboku</code> desde un archivo de texto
	 * 
	 * @param path
	 *            el nombre del archivo
	 */
	public Cuboku(String path) {
		nombre = path;
		fromString(path);
	}

	/**
	 * Genera un objeto de tipo <code>Cuboku</code> a partir de un
	 * <code>Sudoku[]</code>
	 * 
	 * @param oCube
	 *            el array que representa al <code>Cuboku</code>
	 */
	public Cuboku(Sudoku[] oCube) {
		nombre = "cuboku" + System.currentTimeMillis();
		cube = new Sudoku[oCube.length];
		for (int i = 0; i < oCube.length; i++)
			cube[i] = new Sudoku(oCube[i]);
		// System.arraycopy(oCube, 0, cube, 0, cube.length);
	}

	/**
	 * Constructor copia
	 * 
	 * @param copyCube
	 *            el otro Cuboku
	 */
	public Cuboku(Cuboku copyCube) {
		this(copyCube.getCube());
	}

	/**
	 * Devuelve si el estado del cubo es final o no
	 * 
	 * @return false si el estado del cubo es final, true en caso contrario
	 */
	public boolean noFinal() {
		for (int i = 0; i < nCaras; i++) {
			if (cube[i].noFinal())
				return true;
		}
		return false;
	}

	@Override
	public void move(Action where) {

		if (where.equals(RIGHT_SUP)) {
			moveRS();
		}
		if (where.equals(RIGHT_MEDIO)) {
			moveRM();
		}
		if (where.equals(RIGHT_INF)) {
			moveRI();
		}
		if (where.equals(DOWN_IZQ)) {
			moveDI();
		}
		if (where.equals(DOWN_MEDIO)) {
			moveDM();
		}
		if (where.equals(DOWN_DER)) {
			moveDD();
		}

	}

	/**
	 * Metodo que hace el movimiento de girar a la derecha la fila superior
	 */
	private void moveRS() {
		// implicadas, cara 1,2,3,4, y superior la 5
		// proceso de cambio
		// girarDerecha ->fila 1 de cara 1, pasa a ser la de 2, la de 2 la de 3,
		// la de 3 la de 4, la de 4 la de 1
		// RotacionInversaReloj
		// fila 1 es la columna 3 normal pero girando 90ª senInvReloj ori
		// fila 2 es la col 3 normal pero girando 90
		// fila 3 es la col 1 normal
		giraRight(0);
		rotaCaraSentInvReloj(4);
	}

	/**
	 * Metodo que hace el movimiento de girar a la derecha la fila central
	 */
	private void moveRM() {
		// implicadas
		giraRight(1);// cara 1,2,3,4
	}

	/**
	 * Metodo que hace el movimiento de girar a la derecha la fila inferior
	 */
	private void moveRI() {
		// implicadas,
		giraRight(2);// cara 1,2,3,4,
		rotaCaraSentReloj(5);// cara 6 inferior

	}

	/**
	 * Metodo que hace el movimiento de girar hacia abajo la columna izquierda
	 */
	private void moveDI() {
		// implicadas
		giraDown(0); // cara 1,5,3,6,
		rotaCaraSentReloj(3);// cara 4 izq
	}

	/**
	 * Metodo que hace el movimiento de girar hacia abajo la columna central
	 */
	private void moveDM() {
		// implicadas
		giraDown(1);// cara 1,5,3,6

	}

	/**
	 * Metodo que hace el movimiento de girar hacia abajo la columna derecha
	 */
	private void moveDD() {
		// implicadas
		giraDown(2);// cara 1,5,3,6,
		rotaCaraSentInvReloj(1);// cara 2 der
	}

	/**
	 * Metodo que rota la cara c en el sentido contrario del reloj
	 */
	private void rotaCaraSentInvReloj(int c) {
		// col 1 es fila 1 pero al reves, y girando 90 grados izquierda su
		// orientacion
		// col 2 es fila 2 pero al reves y girando 90..
		// col 3 es fila 3 xo al reves y girando 90
		// o
		// fila 1 es la columna 3 normal pero girando 90ª senInvReloj ori
		// fila 2 es la col 2 normal pero girando 90
		// fila 3 es la col 1 normal
		cube[c].rotaCaraSentInvReloj();
	}

	/**
	 * Metodo que rota la cara c en el sentido del reloj
	 */
	private void rotaCaraSentReloj(int c) {
		// col 1 es la fila 3 normal pero girando 90ª senReloj ori
		// col 2 es la fila 2 normal pero girando 90
		// col 3 es la fila 1 normal
		cube[c].rotaCaraSentReloj();
	}

	/**
	 * Metodo que gira el cubo a la derecha en base a la fila f
	 */
	private void giraRight(int f) {
		NumeroKu[] cara4save = cube[3].getFila(f);

		// cara 3 le pongo la 2, cara 2 le pongo la 1...
		for (int i = 3; i > 0; i--) {
			cube[i].setFila(f, cube[i - 1].getFila(f));
		}
		cube[0].setFila(f, cara4save);// cara 1 le pongo la 4
	}

	/**
	 * Metodo que gira el cubo hacia abajo en base a la columna j
	 */
	private void giraDown(int c) {
		NumeroKu[] cara5save = cube[4].getColumna(c);

		cube[4].setColumna(c, cube[2].getColumna(c));// cara 5 le pongo la 3
		cube[2].setColumna(c, cube[5].getColumna(c));// cara 3 le pongo la 6
		cube[5].setColumna(c, cube[0].getColumna(c));// cara 6 le pongo la 1
		cube[0].setColumna(c, cara5save);// cara 1 le pongo la 5

	}

	/**
	 * Obtiene un Cuboku desde un fichero de texto
	 * 
	 * @param path
	 *            la ruta del fichero
	 */
	private void fromString(String path) {
		BufferedReader bf = null;
		ArrayList<NumeroKu> nums = null;
		String str = null;
		StringTokenizer tok = null;
		int k = 0;
		try {
			bf = new BufferedReader(new FileReader(path));
			cube = new Sudoku[nCaras];
			nums = new ArrayList<NumeroKu>();
			while ((str = bf.readLine()) != null) {
				// Si lee un punto acaba de leer una cara completa
				if (str.equals(".")) {
					NumeroKu[] a = new NumeroKu[nums.size()];
					a = nums.toArray(a);
					cube[k++] = new Sudoku(a, "Cara " + k);
					nums.clear();
				} else {
					// Tokeniza los elementos usando el ;
					tok = new StringTokenizer(str, ";");
					while (tok.hasMoreElements()) {
						String nT = tok.nextToken();
						int n = Integer.parseInt(String.valueOf(nT.charAt(0)));
						Orientacion o = Orientacion.getEnum(String.valueOf(nT
								.charAt(1)));
						nums.add(new NumeroKu(n, o));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumeroKuOutOfRangeException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		// String cara4123 = cube[3] + " " + cube[0] + " " + cube[1] + " "
		// + cube[2];
		// String cara5 = cube[4];
		// String cara6 = cube[5];
		// return "\t " + cube[4] + "\n" + cara4123 + "\n" + "\t " + cube[5];
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nCaras; i++) {
			sb.append(cube[i]);
		}
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
		for (int i = 0; i < nCaras; i++) {
			sb.append(cube[i].toStringSerializa());
			if (i == (nCaras - 1))
				sb.append(".");
			else
				sb.append("." + AimaUtil.newLine);
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if ((o == null) || (this.getClass() != o.getClass()))
			return false;

		Cuboku cBoard = (Cuboku) o;

		for (int i = 0; i < nCaras; i++) {
			if (!this.cube[i].equals(cBoard.cube[i]))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 17;
		for (int i = 0; i < 8; i++) {
			int position = i;
			result = 37 * result + position;
		}
		return result;
	}

	/**
	 * Metodo de acceso para la propiedad <code>cube</code>
	 * 
	 * @return el <code>cube</code>
	 */
	public Sudoku[] getCube() {
		return cube;
	}

	/**
	 * Metodo modificador para la propiedad <code>cube</code>
	 * 
	 * @param <code>cube</code> el <code>cube</code> a poner
	 */
	public void setCube(Sudoku[] cube) {
		this.cube = cube;
	}

	public String getNombre() {
		return nombre;
	}

}