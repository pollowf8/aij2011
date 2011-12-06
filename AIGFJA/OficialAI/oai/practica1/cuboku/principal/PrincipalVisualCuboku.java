//
// Universidad de Almer�a
// Ingenier�a T�cnica de Inform�tica de Sistemas
// Fuente Java seg�n Plantilla
//
// PROYECTO : Practica 5 Juego
// ASIGNATURA : Programacion Orientada a Objetos
//
package oai.practica1.cuboku.principal;

import oai.practica1.cuboku.visual.VentanaAI;

/**
 * Clase de ejecucion para VentanaAI
 * 
 * @author Jos� �ngel Garc�a Fern�ndez
 * @version 1.0 03/12/2010
 */
public class PrincipalVisualCuboku {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		VentanaAI ventana = new VentanaAI();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

}
