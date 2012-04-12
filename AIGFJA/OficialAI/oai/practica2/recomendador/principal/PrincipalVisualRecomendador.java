//
// Universidad Complutense de Madrid
// Ingenier�a Inform�tica
//
// PRACTICA : Practica 2
// ASIGNATURA : Inteligencia Artificial e Ingenier�a del Conocimiento
//
package oai.practica2.recomendador.principal;

import jess.JessException;
import oai.practica2.recomendador.visual.VentanaAI;

/**
 * Clase de ejecucion para VentanaAI
 * 
 * @author Jos� �ngel Garc�a Fern�ndez
 * @version 1.0 06/04/2012
 */
public class PrincipalVisualRecomendador {

	public static void main(String[] args) {

		VentanaAI ventana;
		try {
			ventana = new VentanaAI();
			ventana.setLocationRelativeTo(null);
			ventana.setVisible(true);
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
