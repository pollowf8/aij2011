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

	public CatLexica t() {
		throw new UnsupportedOperationException("t");
	};

	public Integer tam() {
		throw new UnsupportedOperationException("tam");
	};

	public ExpTipo tipo() {
		throw new UnsupportedOperationException("tipo");
	};
	
	// procedimientos
	public List<ExpTipo> params() {
		throw new UnsupportedOperationException("params");
	};

	// parametros
	public CatLexica modo() {
		throw new UnsupportedOperationException("modo");
	};

	// arrays
	public ExpTipo tbase() {
		throw new UnsupportedOperationException("tbase");
	};

	//Para registros
	public List<ExpTipo> campos() {
		throw new UnsupportedOperationException("campos");
	};
	// Para campo de registros
	public String id() {
		throw new UnsupportedOperationException("id");
	};
	public Integer desp() {
		throw new UnsupportedOperationException("desp");
	};

	public static class ExpTipoError extends ExpTipo {
		private ExpTipoError() {
		}

		public CatLexica t() {
			return CatLexica.ERROR;
		}

		public String toString() {
			return "<t:ERROR>";
		}
	}
	
	public static class ExpTipoInt extends ExpTipo {
		private ExpTipoInt() {
		}

		public CatLexica t() {
			return CatLexica.INT;
		}

		public Integer tam() {
			return 1;
		}

		public String toString() {
			return "<t:int,tam:1>";
		}
	}

	public static class ExpTipoBoolean extends ExpTipo {
		private ExpTipoBoolean() {
		}

		public CatLexica t() {
			return CatLexica.BOOLEAN;
		}

		public Integer tam() {
			return 1;
		}

		public String toString() {
			return "<t:boolean,tam:1>";
		}
	}

	public static class ExpTipoParam extends ExpTipo {
		private ExpTipoParam(ExpTipo tipo, CatLexica modo) {
			this.tipo = tipo;
			this.modo = modo;
		}

		public ExpTipo tipo() {
			return tipo;
		}

		public CatLexica modo() {
			return modo;
		}

		public String toString() {
			return "<tipo:" + tipo + ",modo:" + modo + ">";
		}

		private ExpTipo tipo;
		private CatLexica modo;
	}

	public static class ExpTipoCampo extends ExpTipo {
		private ExpTipoCampo(String iden, ExpTipo tipo, int desp) {
			this.id = iden;
			this.tipo = tipo;
			this.desp = desp;
		}

		@Override
		public String id() {
			return id;
		}

		@Override
		public ExpTipo tipo() {
			return tipo;
		}

		public Integer desp() {
			return desp;
		}

		public String toString() {
			return "<id:" + id + ", tipo:" + tipo + ",desplazamiento:" + desp
					+ ">";
		}

		private String id;
		private ExpTipo tipo;
		private int desp;
	}

	public static class ExpTipoRef extends ExpTipo {
		private ExpTipoRef(CatLexica ref, String iden, int tam) {
			this.ref = ref;
			this.id = iden;
			this.tam = tam;
		}

		@Override
		public String id() {
			return id;
		}

		@Override
		public CatLexica t() {
			return ref;
		}

		public Integer tam() {
			return tam;
		}

		public String toString() {
			return "<t:" + ref + ", iden:" + id + ",tam:" + tam + ">";
		}

		private CatLexica ref;
		private String id;
		private int tam;
	}

	public static class ExpTipoRegistro extends ExpTipo {
		private ExpTipoRegistro(CatLexica reg, List<ExpTipo> campos, int tam) {
			this.reg = reg;
			this.campos = campos;
			this.tam = tam;
		}

		@Override
		public CatLexica t() {
			return reg;
		}
		@Override
		public Integer tam() {
			return tam;
		}
		@Override
		public List<ExpTipo> campos() {
			return campos;
		}

		public String toString() {
			return "<t:" + reg + ", campos:" + campos + ",tam:" + tam + ">";
		}

		private CatLexica reg;
		private List<ExpTipo> campos;
		private int tam;
	}

	public static class ExpTipoArray extends ExpTipo {
		private ExpTipoArray(CatLexica array, ExpTipo tbase, int tam) {
			this.array = array;
			this.tbase = tbase;
			this.tam = tam;
		}

		@Override
		public CatLexica t() {
			return array;
		}

		public Integer tam() {
			return tam;
		}

		public ExpTipo tbase() {
			return tbase;
		}

		public String toString() {
			return "<t:" + array + ", tbase:" + tbase + ",tam:" + tam + ">";
		}

		private CatLexica array;
		private ExpTipo tbase;
		private int tam;
	}

	public static class ExpTipoProc extends ExpTipo {
		private ExpTipoProc(CatLexica proc, List<ExpTipo> params) {
			this.proc = proc;
			this.params = params;
		}

		@Override
		public CatLexica t() {
			return proc;
		}

		public List<ExpTipo> params() {
			return params;
		}

		public String toString() {
			return "<t:" + proc + ", params:" + params + ">";
		}

		private CatLexica proc;
		private List<ExpTipo> params;
	}
	public static class ExpTipoPuntero extends ExpTipo {
		private ExpTipoPuntero(CatLexica puntero, ExpTipo tbase) {
			this.puntero = puntero;
			this.tbase = tbase;
		}

		@Override
		public CatLexica t() {
			return puntero;
		}

		public Integer tam() {
			return 1;
		}

		public ExpTipo tbase() {
			return tbase;
		}

		public String toString() {
			return "<t:" + puntero + ", tbase:" + tbase + ",tam:" + 1 + ">";
		}

		private CatLexica puntero;
		private ExpTipo tbase;
	}

	public static ExpTipo nuevaExpTipoError() {
		return new ExpTipoError();
	}
	
	public static ExpTipo nuevaExpTipoInt() {
		return new ExpTipoInt();
	}

	public static ExpTipo nuevaExpTipoBoolean() {
		return new ExpTipoBoolean();
	}

	public static ExpTipo nuevaExpTipoParam(ExpTipo tipo, CatLexica modo) {
		return new ExpTipoParam(tipo, modo);
	}

	public static ExpTipo nuevaExpTipoCampo(String iden, ExpTipo tipo, int desp) {
		return new ExpTipoCampo(iden, tipo, desp);
	}

	public static ExpTipo nuevaExpTipoRef(CatLexica ref, String iden, int tam) {
		return new ExpTipoRef(ref, iden, tam);
	}

	public static ExpTipo nuevaExpTipoRegistro(CatLexica reg,
			List<ExpTipo> campos, int tam) {
		return new ExpTipoRegistro(reg, campos, tam);
	}

	public static ExpTipo nuevaExpTipoArray(CatLexica array, ExpTipo tbase,
			int tam) {
		return new ExpTipoArray(array, tbase, tam);
	}
	public static ExpTipo nuevaExpTipoPuntero(CatLexica puntero, ExpTipo tbase) {
		return new ExpTipoPuntero(puntero, tbase);
	}

	public static ExpTipo nuevaExpTipoProc(CatLexica proc, List<ExpTipo> params) {
		return new ExpTipoProc(proc, params);
	}
}
