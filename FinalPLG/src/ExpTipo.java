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
public abstract class ExpTipo  {
protected abstract CatLexica t();	
	
	protected abstract Integer tam();
	
	//procedimientos
	protected abstract List<ExpTipo> params();
	
	//parametros
	protected abstract CatLexica modo();
	
	//arrays
	protected abstract ExpTipo tbase();
	//Para campo
	protected abstract String id();
	
	protected abstract ExpTipo tipo();
	
	protected abstract Integer desp();
}
