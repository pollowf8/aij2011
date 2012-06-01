import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TS {
	private Map<String, ArrayList<Object>> tabla;
	private TS padre;

	public TS() {
		tabla = new HashMap<String, ArrayList<Object>>();
		padre=null;
	}
	
	//Para uso en nuevos niveles
	public TS(TS ts) {
		tabla = new HashMap<String, ArrayList<Object>>();
		padre=ts;
	}

	public boolean estaEn(String cte) {
		return tabla.containsKey(cte);
	}

	// 0 TIPO una expresion de tipo
	// 1 DIR
	// 2 CLASE
	// 3 nivel
	public TS aniade(String cte, ArrayList<Object> val) {
		// TS result = new TS(new HashMap<String,ArrayList<Object>>(tabla));
		// result.tabla.put(cte,val);
		// return result;
		tabla.put(cte, val);
		return this;
	}

	public ArrayList<Object> valDe(String cte) {
		return tabla.get(cte);
	}

	public ExpTipo getExpTipo(String cte){
		return (ExpTipo) tabla.get(cte).get(0);
	}
	public String toString() {
		return tabla.toString();
	}
	
	public TS getPadre(){
		return padre;
	}
	
	public void setPadre(TS padre){
		this.padre=padre;
	}
	
	//Meto en una lista los que son declaraciones de tipos y lo devuelvo
	public List<String> getTiposDeclarados(){
		LinkedList<String> decTipos=new LinkedList<String>();
		Iterator<ArrayList<Object>> it =tabla.values().iterator();
		while(it.hasNext()){
			ArrayList<Object> in=(ArrayList<Object>) it.next();
			if(in.get(2).equals(CatLexica.TIPO)){
				decTipos.add((String) in.get(0));
			}
		}
		return decTipos;
	}
	public int getTamRef(String lex) {
		ArrayList<Object> o = tabla.get(lex);
		ExpTipo exp = (ExpTipo) o.get(0);
		return exp.tam();
	}
}
