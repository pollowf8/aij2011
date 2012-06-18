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
		padre = null;
	}

	// Para uso en nuevos niveles
	public TS(TS ts) {
		tabla = new HashMap<String, ArrayList<Object>>();
		padre = ts;
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
		if (!tabla.containsKey(cte))// xa ke no modificara el proc
			tabla.put(cte, val);
		return this;
	}

	private ArrayList<Object> checkPadres(String cte) {
		TS sig = padre;
		while (sig != null) {
			if (sig.tabla.containsKey(cte))
				return tabla.get(cte);
			else
				sig = sig.padre;
		}
		return null;
	}

	public ArrayList<Object> valDe(String cte) {
		if (tabla.containsKey(cte)) {
			return tabla.get(cte);
		} else {
			return checkPadres(cte);
		}
	}

	public ExpTipo getExpTipo(String cte) {
		return (ExpTipo) tabla.get(cte).get(0);
	}

	public Integer getNivel(String cte) {
		return (Integer) tabla.get(cte).get(3);
	}

	public CatLexica getClase(String cte) {
		return (CatLexica) tabla.get(cte).get(2);
	}

	public String toString() {
		return tabla.toString();
	}

	public TS getPadre() {
		return padre;
	}

	public void setPadre(TS padre) {
		this.padre = padre;
	}

	public void setDir(String lex, int dir) {
		ArrayList<Object> o = tabla.get(lex);
		ArrayList<Object> oNew = new ArrayList<Object>();
		oNew.add(o.get(0));
		oNew.add(dir);
		oNew.add(o.get(2));
		oNew.add(o.get(3));
		tabla.put(lex, oNew);
	}

	// Meto en una lista los que son declaraciones de tipos y lo devuelvo
	public List<String> getTiposDeclarados() {
		LinkedList<String> decTipos = new LinkedList<String>();
		Iterator<String> itlex = tabla.keySet().iterator();
		while (itlex.hasNext()) {
			String id = itlex.next();
			ArrayList<Object> in = (ArrayList<Object>) tabla.get(id);
			if (in.get(2).equals(CatLexica.TIPO)) {
				decTipos.add(id);
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
