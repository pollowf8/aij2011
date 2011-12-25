//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.excepciones;

/**
 * Se lanza cuando se intenta crear una accion que no existe en el dominio del
 * problema
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 25/12/2011
 */
public class NoExisteAccionException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Genera una excepcion de tipo NoExisteAccionException
	 * 
	 * @param message
	 *            el mensaje de la excepcion
	 */
	public NoExisteAccionException(String message) {
		super(message);
	}
}
