public enum CatLexica {

	NUM, IDEN, MAS {

		public String toString() {
			return "+";
		}
	},
	MENOS {
		public String toString() {
			return "-";
		}
	},
	ASTERISCO {
		public String toString() {
			return "*";
		}
	},
	BARRA {
		public String toString() {
			return "/";
		}
	},
	AND {
		public String toString() {
			return "and";
		}
	},
	OR {
		public String toString() {
			return "or";
		}
	},
	NOT {
		public String toString() {
			return "not";
		}
	},
	EQ {
		public String toString() {
			return "eq";
		}
	},
	NEQ {
		public String toString() {
			return "neq";
		}
	},
	LT {
		public String toString() {
			return "lt";
		}
	},
	LE {
		public String toString() {
			return "le";
		}
	},
	GT {
		public String toString() {
			return "gt";
		}
	},
	GE {
		public String toString() {
			return "ge";
		}
	},
	PAP {
		public String toString() {
			return "(";
		}
	},
	PCIERRE {
		public String toString() {
			return ")";
		}
	},
	IGUAL {
		public String toString() {
			return "=";
		}
	},
	DOSPUNTOS {
		public String toString() {
			return ":";
		}
	},
	FLECHA {
		public String toString() {
			return "->";
		}
	},
	CAP {
		public String toString() {
			return "[]";
		}
	},
	PUNTOCOMA {
		public String toString() {
			return ";";
		}
	},
	IF {
		public String toString() {
			return "if";
		}
	},
	FI {
		public String toString() {
			return "fi";
		}
	},
	DO {
		public String toString() {
			return "do";
		}
	},
	OD {
		public String toString() {
			return "od";
		}
	},
	CASE {
		public String toString() {
			return "case";
		}
	},
	TRUE {
		public String toString() {
			return "true";
		}
	},
	FALSE {
		public String toString() {
			return "false";
		}
	},
	INT {
		public String toString() {
			return "int";
		}
	},
	BOOLEAN {
		public String toString() {
			return "boolean";
		}
	},
	ERROR {

		@Override
		public String toString() {
			return "error";
		}
	},
	AMPERSAND {
		public String toString() {
			return "&";
		}
	},
	EOF,
	// NUEVAS CATEGORIAS LÉXICAS
	TABLA {
		public String toString() {
			return "tabla";
		}
	},
	DE {
		public String toString() {
			return "de";
		}
	},
	REG {
		public String toString() {
			return "registro";
		}
	},
	PUNTERO {
		public String toString() {
			return "puntero";
		}
	},
	TIPO {
		public String toString() {
			return "tipo";
		}
	},
	VAR {
		public String toString() {
			return "var";// PASO DE PARAMETRO POR VARIABLE
		}
	},
	PVAR {
		public String toString() {
			return "pvar";// PUNTERO A VARIABLE
		}
	},
	REF {
		public String toString() {
			return "ref";// Referencia a un tipo
		}
	},
	VALOR {
		public String toString() {
			return "VALOR";//PASO DE PARAMETRO POR VALOR
		}
	},
	ARRAY {
		public String toString() {
			return "array";
		}
	},
	PROC {
		public String toString() {
			return "proc";
		}
	},
	NEW {
		public String toString() {
			return "new";
		}
	},
	DELETE {
		public String toString() {
			return "delete";
		}
	},
	LEER {
		public String toString() {
			return "leer";
		}
	},
	ESCRIBIR {
		public String toString() {
			return "escribir";
		}
	}
};
