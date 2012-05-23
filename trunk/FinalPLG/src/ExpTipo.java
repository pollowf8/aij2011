import java.util.List;

//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//

/**
 * Representa un registro
 * 
 * @version 1.0 22/05/2012
 */
public abstract class ExpTipo {
	
	public CatLexica t(){throw new UnsupportedOperationException("t");};

	public abstract Integer tam();

	// procedimientos
	public abstract List<ExpTipo> params();

	// parametros
	public abstract CatLexica modo();

	// arrays
	public abstract ExpTipo tbase();

	// Para campo
	public abstract String id();

	public abstract ExpTipo tipo();

	public abstract Integer desp();
}
