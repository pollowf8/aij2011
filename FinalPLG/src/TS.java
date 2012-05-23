import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TS {
	private Map<String, ArrayList<Object>> tabla;

	public TS() {
		tabla = new HashMap<String, ArrayList<Object>>();
	}

	private TS(Map<String, ArrayList<Object>> tabla) {
		this.tabla = tabla;
	}

	public boolean estaEn(String cte) {
		return tabla.containsKey(cte);
	}

	// 0 TIPO una expresion de tipo
	// 1 DIR
	// 2 CLASE
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

	public String toString() {
		return tabla.toString();
	}

	public int getTamRef(String lex) {
		ArrayList<Object> o = tabla.get(lex);
		ExpTipo exp = (ExpTipo) o.get(0);
		return exp.tam();
	}
}
