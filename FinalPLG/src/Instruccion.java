abstract public class Instruccion {
	public enum ICOD {
		SUM, MUL, APILA, DESAPILA_DIR, APILA_TRUE,APILA_FALSE,APILA_INT,
		APILA_DIR,EQ,NEQ,GT,GE,LT,LE,RESTA,OR,DIV,AND,MENOS,NOT,IR_F,IR_A
	};
	
	private static ISuma iSuma = null;
	private static IResta iResta = null;
	private static IMul iMul = null;
	private static IEq iEq = null;
	private static INeq iNeq = null;
	private static IGt iGt = null;
	private static IGe iGe = null;
	private static ILt iLt = null;
	private static ILe iLe = null;
	private static IOr iOr = null;
	private static IDiv iDiv = null;
	private static IAnd iAnd = null;
	private static IMenos iMenos = null;
	private static INot iNot = null;
	
	
	abstract public ICOD ci();

	public int arg1() {
		throw new UnsupportedOperationException("arg1");
	}
	
	public static class IOr extends Instruccion {
		private IOr() {
		}
		public ICOD ci() {
			return ICOD.OR;
		}
		public String toString() {
			return "OR()";
		}
	}
	
	public static class IDiv extends Instruccion {
		private IDiv() {
		}
		public ICOD ci() {
			return ICOD.DIV;
		}
		public String toString() {
			return "DIV()";
		}
	}
	
	public static class IAnd extends Instruccion {
		private IAnd() {
		}
		public ICOD ci() {
			return ICOD.AND;
		}
		public String toString() {
			return "AND()";
		}
	}
	
	public static class IMenos extends Instruccion {
		private IMenos() {
		}
		public ICOD ci() {
			return ICOD.MENOS;
		}
		public String toString() {
			return "MENOS()";
		}
	}
	
	public static class INot extends Instruccion {
		private INot() {
		}
		public ICOD ci() {
			return ICOD.NOT;
		}
		public String toString() {
			return "NOT()";
		}
	}
	
	
	public static class IEq extends Instruccion {
		private IEq() {
		}
		public ICOD ci() {
			return ICOD.EQ;
		}
		public String toString() {
			return "EQ()";
		}
	}
	
	public static class INeq extends Instruccion {
		private INeq() {
		}
		public ICOD ci() {
			return ICOD.NEQ;
		}
		public String toString() {
			return "NEQ()";
		}
	}
	
	public static class IGt extends Instruccion {
		private IGt() {
		}
		public ICOD ci() {
			return ICOD.GT;
		}
		public String toString() {
			return "GT()";
		}
	}
	
	public static class IGe extends Instruccion {
		private IGe() {
		}
		public ICOD ci() {
			return ICOD.GE;
		}
		public String toString() {
			return "GE()";
		}
	}
	
	public static class ILt extends Instruccion {
		private ILt() {
		}
		public ICOD ci() {
			return ICOD.LT;
		}
		public String toString() {
			return "LT()";
		}
	}
	
	public static class ILe extends Instruccion {
		private ILe() {
		}
		public ICOD ci() {
			return ICOD.LE;
		}
		public String toString() {
			return "LE()";
		}
	}
	

	

	public static class ISuma extends Instruccion {
		private ISuma() {
		}
		public ICOD ci() {
			return ICOD.SUM;
		}
		public String toString() {
			return "suma()";
		}
	}
	
	public static class IResta extends Instruccion {
		private IResta() {
		}
		public ICOD ci() {
			return ICOD.RESTA;
		}
		public String toString() {
			return "resta()";
		}
	}

	public static class IMul extends Instruccion {
		private IMul() {
		}
		public ICOD ci() {
			return ICOD.MUL;
		}
		public String toString() {
			return "mul()";
		}
	}
	
	public static class IIr_f extends Instruccion {
		private int etq;
		private IIr_f(int etq) {
		     this.etq = Integer.valueOf(etq).intValue();		
		}

		public ICOD ci() {
			return ICOD.IR_F;
		}
		public int arg1() {
			return etq;
		}
		public String toString() {
			return "IR_F(" + etq + ")";
		}	
	}
	
	public static class IIr_a extends Instruccion {
		private int etq;
		private IIr_a(int etq) {
				this.etq = etq;	
		}
		public ICOD ci() {
			return ICOD.IR_A;
		}
		public int arg1() {
			return etq;
		}
		public String toString() {
			return "IR_A(" + etq + ")";
		}	
	}

	public static class IApila extends Instruccion {
		private int val;
		private IApila(String val) {
			try {
				this.val = Integer.valueOf(val).intValue();
			} catch (NumberFormatException e) {
				this.val = 0;
			}
		}

		public ICOD ci() {
			return ICOD.APILA;
		}
		public int arg1() {
			return val;
		}
		public String toString() {
			return "APILA(" + val + ")";
		}	
	}
	
	public static class IApilaTrue extends Instruccion {
		public IApilaTrue(){}
		public ICOD ci() {
			return ICOD.APILA_TRUE;
		}
		public String toString() {
			return "APILA_TRUE()";
		}	
	}
	
	public static class IApilaFalse extends Instruccion {
		public IApilaFalse(){}
		public ICOD ci() {
			return ICOD.APILA_FALSE;
		}
		public String toString() {
			return "APILA_FALSE()";
		}	
	}
	

	public static class IDesapilaDir extends Instruccion {

		private IDesapilaDir(String dir) {
			try {
				this.dir = Integer.valueOf(dir).intValue();
			} catch (NumberFormatException e) {
				this.dir = 0;
			}
		}

		public ICOD ci() {
			return ICOD.DESAPILA_DIR;
		}
		public int arg1() {
			return dir;
		}
		public String toString() {
			return "DESAPILA_DIR(" + dir + ")";
		}
		private int dir;

	}
	
	public static class IApilaDir extends Instruccion {

		private IApilaDir(String dir) {
			try {
				this.dir = Integer.valueOf(dir).intValue();
			} catch (NumberFormatException e) {
				this.dir = 0;
			}
		}

		public ICOD ci() {
			return ICOD.APILA_DIR;
		}
		public int arg1() {
			return dir;
		}
		public String toString() {
			return "APILA_DIR(" + dir + ")";
		}
		private int dir;

	}
	
	public static class IApilaInt extends Instruccion {
		private int val;
		private IApilaInt(String val) {
			try {
				this.val = Integer.valueOf(val).intValue();
			} catch (NumberFormatException e) {
				this.val = 0;
			}
		}

		public ICOD ci() {
			return ICOD.APILA_INT;
		}
		public int arg1() {
			return val;
		}
		public String toString() {
			return "APILA_INT(" + val + ")";
		}
	}
	
	public static Instruccion nuevaIOr() {
		if (iOr == null) {
			iOr = new IOr();
		}
		return iOr;
	}
	
	public static Instruccion nuevaIDiv() {
		if (iDiv == null) {
			iDiv = new IDiv();
		}
		return iDiv;
	}
	
	public static Instruccion nuevaIAnd() {
		if (iAnd == null) {
			iAnd = new IAnd();
		}
		return iAnd;
	}
	
	public static Instruccion nuevaIMenos() {
		if (iMenos == null) {
			iMenos = new IMenos();
		}
		return iMenos;
	}
	
	public static Instruccion nuevaINot() {
		if (iNot == null) {
			iNot = new INot();
		}
		return iNot;
	}

	public static Instruccion nuevaIEq() {
		if (iEq == null) {
			iEq = new IEq();
		}
		return iEq;
	}
	
	public static Instruccion nuevaINeq() {
		if (iNeq == null) {
			iNeq = new INeq();
		}
		return iNeq;
	}
	
	public static Instruccion nuevaIGt() {
		if (iGt == null) {
			iGt = new IGt();
		}
		return iGt;
	}
	
	public static Instruccion nuevaIGe() {
		if (iGe == null) {
			iGe = new IGe();
		}
		return iGe;
	}
	
	public static Instruccion nuevaILt() {
		if (iLt == null) {
			iLt = new ILt();
		}
		return iLt;
	}
	
	public static Instruccion nuevaILe() {
		if (iLe == null) {
			iLe = new ILe();
		}
		return iLe;
	}
	
	public static Instruccion nuevaISuma() {
		if (iSuma == null) {
			iSuma = new ISuma();
		}
		return iSuma;
	}
	
	public static Instruccion nuevaIResta() {
		if (iResta == null) {
			iResta = new IResta();
		}
		return iResta;
	}

	public static Instruccion nuevaIMul() {
		if (iMul == null) {
			iMul = new IMul();
		}
		return iMul;
	}

	public static Instruccion nuevaIIr_f(int etq) {
		return new IIr_f(etq);
	}	
	public static Instruccion nuevaIIr_a(int etq) {
		return new IIr_a(etq);
	}
	public static Instruccion nuevaIApila(String val) {
		return new IApila(val);
	}	
	public static Instruccion nuevaIApilaDir(String dir) {
		return new IApilaDir(dir);
	}	
	public static Instruccion nuevaIDesapilaDir(String dir) {
		return new IDesapilaDir(dir);
	}	
	public static Instruccion nuevaIApilaTrue() {
		return new IApilaTrue();
	}	
	public static Instruccion nuevaIApilaFalse() {
		return new IApilaFalse();
	}	
	public static Instruccion nuevaIApilaInt(String val) {
		return new IApilaInt(val);
	}
}
