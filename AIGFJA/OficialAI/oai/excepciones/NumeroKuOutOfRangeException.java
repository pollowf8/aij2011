//
// Universidad Complutense de Madrid
// Ingenier�a Inform�tica
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingenier�a del Conocimiento
//
package oai.excepciones;

/**
 * Excepcion que se lanza cuando se intenta crear un NumeroKu menor que 0 o
 * mayor que 9
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 04/12/2011
 */
public class NumeroKuOutOfRangeException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Genera una excepcion de tipo NumeroKuOutOfRangeException
	 * 
	 * @param message
	 *            el mensaje de la excepcion
	 */
	public NumeroKuOutOfRangeException(String message) {
		super(message);
	}

}
