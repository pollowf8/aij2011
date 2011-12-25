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
 * Hacer array de 6 matrices, cada matriz tiene 9 numCuboku, que tienen valor y Orientacion
 /** MOVIMIENTOS
 *	
 *  tengo k poder girar a la izquierda y derecha x 3 horizontal, y
 * arriba y abajo 3 vertical , kizas se puede arreglar para k no haga falta izq o der ni up o down, ya que girando a la izquierda llego a donde llega la derecha pero con 3movs mas
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
 */

/**
 * Clase que representa el cubo del Cuboku
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.2 25/12/2011
 */
public class Cuboku implements Movable {

	private static String[] acciones = { "RelojCara1", "RelojCara2",
			"RelojCara3", "RelojCara4", "RelojCara5", "RelojCara6", };
	// Movimientos CARA1 y CARA3
	/**
	 * Gira cara 1 sentido agujas reloj
	 */
	public static Action REL_CARA1 = new DynamicAction("RelojCara1");

	/**
	 * Gira cara 2 sentido agujas reloj o girar hacia arriba la pieza 3 de la
	 * cara 1
	 */
	// UpDerecha
	public static Action REL_CARA2 = new DynamicAction("RelojCara2");
	/**
	 * Gira cara 3 sentido agujas reloj
	 */
	public static Action REL_CARA3 = new DynamicAction("RelojCara3");

	/**
	 * Gira cara 4 sentido agujas reloj o girar hacia abajo la pieza 1 de la
	 * cara 1
	 */
	// DownIzquierda
	public static Action REL_CARA4 = new DynamicAction("RelojCara4");

	/**
	 * Gira cara 5 sentido agujas reloj o girar a la izquierda la pieza 1 de la
	 * cara1
	 */
	// LeftSup
	public static Action REL_CARA5 = new DynamicAction("RelojCara5");

	/**
	 * Gira cara 6 sentido agujas reloj o girar a la derecha la pieza 3 de la
	 * cara1
	 */
	// RightInf
	public static Action REL_CARA6 = new DynamicAction("RelojCara6");

	/**
	 * Gira cara 1 sentido contrario agujas reloj
	 */
	public static Action INV_CARA1 = new DynamicAction("InvCara1");

	/**
	 * Gira cara 2 sentido contrario agujas reloj o girar hacia abajo la pieza 3
	 * de la cara 1
	 */
	// DownDerecha
	public static Action INV_CARA2 = new DynamicAction("InvCara2");

	/**
	 * Gira cara 3 sentido contrario agujas reloj
	 */
	public static Action INV_CARA3 = new DynamicAction("InvCara3");

	/**
	 * Gira cara 4 sentido contrario agujas reloj o girar hacia arriba la pieza
	 * 1 de la cara 1
	 */
	// UpIzquierda
	public static Action INV_CARA4 = new DynamicAction("InvCara4");

	/**
	 * Gira cara 5 sentido contrario agujas reloj o girar a la derecha la pieza
	 * 1 de la cara1
	 */
	// RightSup
	public static Action INV_CARA5 = new DynamicAction("InvCara5");

	/**
	 * Gira cara 6 sentido contrario agujas reloj o girar a la derecha la pieza
	 * 3 de la cara1
	 */
	// LeftInf
	public static Action INV_CARA6 = new DynamicAction("InvCara6");

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
		nombre = path.substring(0, path.indexOf("."));
		fromString(path);
	}

	/**
	 * Genera un objeto de tipo <code>Cuboku</code> a partir de un
	 * <code>Sudoku[]</code> (hace copia de los elementos)
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
	 * Constructor copia (hace copia de los elementos)
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

	/**
	 * Devuelve el numero de NumeroKu descolocados
	 * 
	 * @return el numero de NumeroKu descolocados
	 */
	public int numDescolocados() {
		int numDesc = 0;
		for (int i = 0; i < nCaras; i++)
			numDesc += cube[i].numDescolocados();
		return numDesc;
	}

	/**
	 * Devuelve el numero de NumeroKu repetidos
	 * 
	 * @return el numero de NumeroKu repetidos
	 */
	public int numRepetidos() {
		int numDesc = 0;
		for (int i = 0; i < nCaras; i++)
			numDesc += cube[i].numRepetidos();
		return numDesc;
	}

	/**
	 * Devuelve el numero de NumeroKu mal orientados
	 * 
	 * @return el numero de NumeroKu mal orientados
	 */
	public int numMalOrientados() {
		int numDesc = 0;
		for (int i = 0; i < nCaras; i++)
			numDesc += cube[i].numMalOrientados();
		return numDesc;
	}

	@Override
	public void move(Action where) {

		if (where.equals(REL_CARA1)) {
			moveR1();
		}
		if (where.equals(REL_CARA2)) {
			moveR2();
		}
		if (where.equals(REL_CARA3)) {
			moveR3();
		}
		if (where.equals(REL_CARA4)) {
			moveR4();
		}
		if (where.equals(REL_CARA5)) {
			moveR5();
		}
		if (where.equals(REL_CARA6)) {
			moveR6();
		}
		// if (where.equals(INV_CARA1)) {
		// moveI1();
		// }
		// if (where.equals(INV_CARA2)) {
		// moveI2();
		// }
		// if (where.equals(INV_CARA3)) {
		// moveI3();
		// }
		// if (where.equals(INV_CARA4)) {
		// moveI4();
		// }
		// if (where.equals(INV_CARA5)) {
		// moveI5();
		// }
		// if (where.equals(INV_CARA6)) {
		// moveI6();
		// }

	}

	/**
	 * Metodo que hace el movimiento de girar en el sentido del reloj la cara 1
	 */
	private void moveR1() {
		// implicadas
		// giraRight(1);// cara 1,2,3,4

		// implicadas 1-2,4,5,6
		// cara5 f3<-cara4 col3<-cara6 f1<-cara2 col1<-cara5 f3
		NumeroKu[] cara5save = cube[4].getFila(2);

		cube[4].setFila(2, cube[3].getColumna(2));// cara5 fila 3 <-cara4 col 3
		cube[3].setColumna(2, cube[5].getFila(0));// cara4 col 3 <- cara6 fila 1
		cube[5].setFila(0, cube[1].getColumna(0));// cara6 fila 1 <- cara2 col 1
		cube[1].setColumna(0, cara5save);// cara2 col 1 <- cara5 fila 3

		rotaCaraSentReloj(0);// cara 1 gira sentReloj

	}

	/**
	 * Metodo que hace el movimiento de girar hacia ARRIBA la columna derecha o
	 * girar en el sentido del reloj la cara 2
	 */
	private void moveR2() {
		// implicadas
		giraUp(2);// cara 1,5,3,6,
		rotaCaraSentReloj(1);// cara 2 der
	}

	/**
	 * Metodo que hace el movimiento de girar en el sentido del reloj la cara 3
	 */
	private void moveR3() {
		// implicadas
		// giraDown(1);// cara 1,5,3,6

		// implicadas 3-2,4,5,6
		// cara5 f1->cara4 col1->cara6 f3->cara3 col3->cara5 f1
		NumeroKu[] cara5save = cube[4].getFila(0);

		cube[4].setFila(0, cube[2].getColumna(2));// cara5 fila 1 <-cara3 col 3
		cube[2].setColumna(2, cube[5].getFila(2));// cara3 col 3 <- cara6 fila 3
		cube[5].setFila(2, cube[3].getColumna(0));// cara6 fila 3 <- cara4 col 1
		cube[3].setColumna(0, cara5save);// cara4 col 1 <- cara5 fila 1

		rotaCaraSentReloj(2);// cara 3 gira sentReloj
	}

	/**
	 * Metodo que hace el movimiento de girar hacia abajo la columna izquierda o
	 * girar en el sentido del reloj la cara 4
	 */
	private void moveR4() {
		// implicadas
		giraDown(0); // cara 1,5,3,6,
		rotaCaraSentReloj(3);// cara 4 izq
	}

	/**
	 * Metodo que hace el movimiento de girar a la izquierda la fila superior o
	 * girar en el sentido del reloj la cara 5
	 */
	private void moveR5() {
		// implicadas,  
		giraLeft(0);//cara 1,2,3,4,
		rotaCaraSentReloj(4);//cara 5 superior
	}

	/**
	 * Metodo que hace el movimiento de girar a la derecha la fila inferior o
	 * girar en el sentido del reloj la cara 6
	 */
	private void moveR6() {
		// implicadas,
		giraRight(2);// cara 1,2,3,4,
		rotaCaraSentReloj(5);// cara 6 inferior

	}

	/**
	 * Metodo que hace el movimiento de girar en el sentido contrario del reloj
	 * la cara 1
	 */
	private void moveI1() {
		// implicadas
		// giraRight(1);// cara 1,2,3,4

		// implicadas 1-2,4,5,6
		// cara5 f3->cara4 col3->cara6 f1->cara2 col1->cara5 f3
		NumeroKu[] cara5save = cube[4].getFila(2);

		cube[4].setFila(2, cube[1].getColumna(0));// cara5 fila 3 <-cara2 col 1
		cube[1].setColumna(0, cube[5].getFila(0));// cara2 col 1 <- cara6 fila 1
		cube[5].setFila(0, cube[3].getColumna(2));// cara6 fila 1 <- cara4 col 3
		cube[3].setColumna(2, cara5save);// cara4 col 3 <- cara5 fila 3

		rotaCaraSentInvReloj(0);// cara 1 gira sentInvReloj

	}

	/**
	 * Metodo que hace el movimiento de girar hacia abajo la columna derecha o
	 * girar en el sentido inverso del reloj la cara 2
	 */
	private void moveI2() {
		// implicadas
		giraDown(2);// cara 1,5,3,6,
		rotaCaraSentInvReloj(1);// cara 2 der
	}

	/**
	 * Metodo que hace el movimiento de girar en el sentido contrario del reloj
	 * la cara 3
	 */
	private void moveI3() {
		// implicadas
		// giraDown(1);// cara 1,5,3,6

		// implicadas 3-2,4,5,6
		// cara5 f1<-cara4 col1<-cara6 f3<-cara3 col3<-cara5 f1
		NumeroKu[] cara5save = cube[4].getFila(0);

		cube[4].setFila(0, cube[3].getColumna(0));// cara5 fila 1 <-cara4 col 1
		cube[3].setColumna(0, cube[5].getFila(2));// cara4 col 1 <- cara6 fila 3
		cube[5].setFila(2, cube[2].getColumna(2));// cara6 fila 3 <- cara3 col 3
		cube[3].setColumna(2, cara5save);// cara3 col 3 <- cara5 fila 1

		rotaCaraSentInvReloj(2);// cara 3 gira sentInvReloj

	}

	/**
	 * Metodo que hace el movimiento de girar hacia arriba la columna izquierda
	 * o girar en el sentido contrario del reloj la cara 4
	 */
	private void moveI4() {
		// implicadas
		giraUp(0); // cara 1,5,3,6,
		rotaCaraSentInvReloj(3);// cara 4 izq
	}

	/**
	 * Metodo que hace el movimiento de girar a la derecha la fila superior o
	 * girar en el sentido inverso del reloj la cara 5
	 */
	private void moveI5() {
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
	 * Metodo que hace el movimiento de girar a la izquierda la fila inferior o
	 * girar en el sentido contrario del reloj la cara 6
	 */
	private void moveI6() {
		// implicadas,
		giraLeft(2);// cara 1,2,3,4,
		rotaCaraSentInvReloj(5);// cara 6 inferior

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
	 * Metodo que gira cubo a la derecha en base a la fila f, viendo el cubo
	 * desde la cara 1
	 */
	private void giraRight(int f) {
		NumeroKu[] cara4save = cube[3].getFila(f);

		// cara 1 -> 2, cara 2 -> 3, cara 3 -> 4,
		for (int i = 3; i > 0; i--) {
			cube[i].setFila(f, cube[i - 1].getFila(f));
		}
		cube[0].setFila(f, cara4save);// (cara 4 -> 1)
	}

	/**
	 * Metodo que gira cubo a la izquierda en base a la fila f, viendo el cubo
	 * desde la cara 1
	 */
	private void giraLeft(int f) {
		NumeroKu[] cara1save = cube[0].getFila(f);

		// cara 1 <- 2, cara 2 <- 3, cara 3 <- 4,
		for (int i = 0; i < 3; i++) {
			cube[i].setFila(f, cube[i + 1].getFila(f));
		}

		cube[3].setFila(f, cara1save);// (cara 4 <- 1)
	}

	/**
	 * Metodo que gira el una pieza del cubo hacia abajo en base a la columna j,
	 * viendo el cubo desde la cara 1
	 */
	private void giraDown(int c) {
		NumeroKu[] cara5save = cube[4].getColumna(c);

		cube[4].setColumna(c, cube[2].getColumna(c));// cara 5 le pongo la 3
		cube[2].setColumna(c, cube[5].getColumna(c));// cara 3 le pongo la 6
		cube[5].setColumna(c, cube[0].getColumna(c));// cara 6 le pongo la 1
		cube[0].setColumna(c, cara5save);// cara 1 le pongo la 5

	}

	/**
	 * Metodo que gira el una pieza del cubo hacia arriba en base a la columna
	 * j, viendo el cubo desde la cara 1
	 */
	private void giraUp(int c) {
		NumeroKu[] cara5save = cube[4].getColumna(c);

		cube[4].setColumna(c, cube[0].getColumna(c));// cara 5 le pongo la 1
		cube[0].setColumna(c, cube[5].getColumna(c));// cara 1 le pongo la 6
		cube[5].setColumna(c, cube[2].getColumna(c));// cara 6 le pongo la 3
		cube[2].setColumna(c, cara5save);// cara 3 le pongo la 5

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

	/**
	 * Comprueba si existe la accion en la lista de acciones
	 * 
	 * @param accionStr
	 *            la accion a comprobar
	 * @return devuelve true si existe false en caso contrario
	 */
	public static boolean existeAccion(String accionStr) {
		for (int i = 0; i < acciones.length; i++)
			if (acciones[i].equals(accionStr))
				return true;
		return false;
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
		int suma = 0;
		for (int i = 0; i < cube.length; i++) {
			suma = cube[i].hashCode();
		}
		return suma;
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
	 * @param cube
	 *            el <code>cube</code> a poner
	 */
	public void setCube(Sudoku[] cube) {
		this.cube = cube;
	}

	/**
	 * Metodo de acceso para la propiedad <code>nombre</code>
	 * 
	 * @return el nombre del cuboku
	 */
	public String getNombre() {
		return nombre;
	}
}