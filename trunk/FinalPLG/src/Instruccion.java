import java.io.Serializable;
import java.util.Scanner;

abstract public class Instruccion implements Serializable {
	public enum ICOD {
		SUM, MUL, APILA, DESAPILA_DIR, APILA_TRUE, APILA_FALSE, APILA_INT, APILA_DIR, EQ, NEQ, GT, GE, LT, LE, RESTA, OR, DIV, AND, MENOS, NOT, IR_F, IR_A, APILA_IND, DESAPILA_IND, MUEVE, INEW, IDEL, DESAPILA, IESCRITURA, ILECTURA, ICOPIA, IR_IND
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

	abstract public void ejecuta(VM vm);

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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.BooleanPValue(op1.asBoolean() || op2.asBoolean()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.IntPValue(op1.asInt() / op2.asInt()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.BooleanPValue(op1.asBoolean() && op2.asBoolean()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op1 = vm.pop();
			vm.push(new VM.IntPValue(-op1.asInt()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op1 = vm.pop();
			vm.push(new VM.BooleanPValue(!op1.asBoolean()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.BooleanPValue(op1.asInt() == op2.asInt()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.BooleanPValue(op1.asBoolean() != op2.asBoolean()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.BooleanPValue(op1.asInt() > op2.asInt()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.BooleanPValue(op1.asInt() >= op2.asInt()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.BooleanPValue(op1.asInt() < op2.asInt()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.BooleanPValue(op1.asInt() <= op2.asInt()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.IntPValue(op1.asInt() + op2.asInt()));
			vm.incCP();
		}
	}

	public static class IResta extends Instruccion {
		private IResta() {
		}

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.IntPValue(op1.asInt() - op2.asInt()));
			vm.incCP();
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

		public void ejecuta(VM vm) {
			VM.PValue op2 = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.push(new VM.IntPValue(op1.asInt() * op2.asInt()));
			vm.incCP();
		}
	}

	public static class IIr_f extends Instruccion {
		private int etq;

		private IIr_f(int etq) {
			this.etq = Integer.valueOf(etq).intValue();
		}

		public void ejecuta(VM vm) {
			VM.PValue op1 = vm.pop();
			if (op1.asBoolean() == false) {
				vm.setCP(etq);
			} else {
				vm.incCP();
			}
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

		public void ejecuta(VM vm) {
			vm.setCP(etq);
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

	public static class IIr_ind extends Instruccion {

		private IIr_ind() {
		}

		public void ejecuta(VM vm) {
			VM.PValue op1 = vm.pop();
			vm.setCP(op1.asInt());
		}

		public ICOD ci() {
			return ICOD.IR_IND;
		}

		public String toString() {
			return "IR_IND()";
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

		public void ejecuta(VM vm) {
			vm.push(new VM.IntPValue(val));
			vm.incCP();
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
		public IApilaTrue() {
		}

		public ICOD ci() {
			return ICOD.APILA_TRUE;
		}

		public String toString() {
			return "APILA_TRUE()";
		}

		public void ejecuta(VM vm) {
			vm.push(new VM.BooleanPValue(true));
			vm.incCP();
		}
	}

	public static class IApilaFalse extends Instruccion {
		public IApilaFalse() {
		}

		public ICOD ci() {
			return ICOD.APILA_FALSE;
		}

		public String toString() {
			return "APILA_FALSE()";
		}

		public void ejecuta(VM vm) {
			vm.push(new VM.BooleanPValue(false));
			vm.incCP();
		}
	}

	public static class IDesapila extends Instruccion {

		private IDesapila() {

		}

		public void ejecuta(VM vm) {
			vm.pop();
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.DESAPILA;
		}

		public String toString() {
			return "DESAPILA()";
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

		public void ejecuta(VM vm) {
			VM.PValue op1 = vm.pop();
			vm.addValMem(dir, op1.asInt());
			vm.incCP();
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

		public void ejecuta(VM vm) {
			Integer a = vm.getValMem(dir);
			vm.push(new VM.IntPValue(a));
			vm.incCP();
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

	// la cima le dice el origen, la subcima el destino, y moviendo tamMover
	// posiciones
	public static class IMueve extends Instruccion {

		private IMueve(String dir) {
			try {
				this.tamMover = Integer.valueOf(dir).intValue();
			} catch (NumberFormatException e) {
				this.tamMover = 0;
			}
		}

		public void ejecuta(VM vm) {
			VM.PValue origen = vm.pop();
			VM.PValue destino = vm.pop();
			for (int i = 0; i < tamMover - 1; i++) {
				Integer a = vm.getValMem(origen.asInt() + i);
				vm.addValMem(destino.asInt() + i, a);
			}
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.MUEVE;
		}

		public int arg1() {
			return tamMover;
		}

		public String toString() {
			return "APILA_DIR(" + tamMover + ")";
		}

		private int tamMover;

	}

	// Interpreta la cima de la pila como una direccion, la subcima como un
	// valor, y se lleva el valor a la direccion de memoria indicada
	public static class IDesapilaInd extends Instruccion {

		private IDesapilaInd() {
			// try {
			// this.dir = Integer.valueOf(dir).intValue();
			// } catch (NumberFormatException e) {
			// this.dir = 0;
			// }
		}

		public void ejecuta(VM vm) {
			VM.PValue dir = vm.pop();
			VM.PValue op1 = vm.pop();
			vm.addValMem(dir.asInt(), op1.asInt());
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.DESAPILA_IND;
		}

		// public int arg1() {
		// return dir;
		// }

		public String toString() {
			// return "DESAPILA_IND(" + dir + ")";
			return "DESAPILA_IND()";
		}

		// private int dir;

	}

	// Interpreta la cima de la pila como una direccion, la desapila y se trae
	// de memoria el valor referenciado por ella
	public static class IApilaInd extends Instruccion {

		private IApilaInd() {
			// try {
			// this.dir = Integer.valueOf(dir).intValue();
			// } catch (NumberFormatException e) {
			// this.dir = 0;
			// }
		}

		public void ejecuta(VM vm) {
			VM.PValue dir = vm.pop();
			Integer a = vm.getValMem(dir.asInt());
			vm.push(new VM.IntPValue(a));
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.APILA_IND;
		}

		// public int arg1() {
		// return dir;
		// }

		public String toString() {
			// return "APILA_IND(" + dir + ")";
			return "APILA_IND()";
		}

		// private int dir;

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

		public void ejecuta(VM vm) {
			vm.push(new VM.IntPValue(val));
			vm.incCP();
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

	public static class INew extends Instruccion {

		private INew(String tamReserva) {
			try {
				this.tamReserva = Integer.valueOf(tamReserva).intValue();
			} catch (NumberFormatException e) {
				this.tamReserva = 0;
			}
		}

		public void ejecuta(VM vm) {
			Integer poslibre = vm.getPrimeraPosLibre();
			// TODO RESERVA?¿
			vm.push(new VM.IntPValue(poslibre));
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.INEW;
		}

		public int arg1() {
			return tamReserva;
		}

		public String toString() {
			return "NEW(" + tamReserva + ")";
		}

		private int tamReserva;

	}

	public static class IDel extends Instruccion {

		private IDel(String tamReserva) {
			try {
				this.tamReserva = Integer.valueOf(tamReserva).intValue();
			} catch (NumberFormatException e) {
				this.tamReserva = 0;
			}
		}

		public void ejecuta(VM vm) {
			VM.PValue posinicio = vm.pop();
			// TODO LIBERA?¿
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.IDEL;
		}

		public int arg1() {
			return tamReserva;
		}

		public String toString() {
			return "DEL(" + tamReserva + ")";
		}

		private int tamReserva;

	}

	public static class ILectura extends Instruccion {

		private ILectura(CatLexica tipo) {
			this.tipo = tipo;
		}

		public void ejecuta(VM vm) {
			if (sc == null)
				sc = new Scanner(System.in);
			if (tipo == CatLexica.INT) {
				vm.push(new VM.IntPValue(sc.nextInt()));
			} else if(tipo == CatLexica.BOOLEAN){
				vm.push(new VM.BooleanPValue(sc.nextBoolean()));
			}
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.ILECTURA;
		}

		public int arg1() {
			return tipo.ordinal();
		}

		public String toString() {
			return "ILECTURA(" + tipo + ")";
		}
		
		Scanner sc;
		private CatLexica tipo;
	}

	public static class IEscritura extends Instruccion {

		private IEscritura(CatLexica tipo) {
			this.tipo=tipo;
		}

		public void ejecuta(VM vm) {
			VM.PValue p=vm.pop();
			System.out.print(p);
		}

		public ICOD ci() {
			return ICOD.IESCRITURA;
		}

		public int arg1() {
			return tipo.ordinal();
		}

		public String toString() {
			return "IESCRITURA(" + tipo + ")";
		}

		private CatLexica tipo;
	}

	public static class ICopia extends Instruccion {

		private ICopia() {

		}

		public void ejecuta(VM vm) {
			VM.PValue op1 = vm.pop();
			vm.push(op1);
			vm.push(op1);
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.ICOPIA;
		}

		public String toString() {
			return "ICOPIA()";
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

	public static Instruccion nuevaIIr_ind() {
		return new IIr_ind();
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

	public static Instruccion nuevaIDesapila() {
		return new IDesapila();
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

	public static Instruccion nuevaIApilaInd() {
		return new IApilaInd();
	}

	public static Instruccion nuevaIDesApilaInd() {
		return new IDesapilaInd();
	}

	public static Instruccion nuevaIMueve(String tamMover) {
		return new IMueve(tamMover);
	}

	public static Instruccion nuevaINew(String tamReserva) {
		return new INew(tamReserva);
	}

	public static Instruccion nuevaIDel(String tamReserva) {
		return new IDel(tamReserva);
	}

	public static Instruccion nuevaICopia() {
		return new ICopia();
	}
	public static Instruccion nuevaILectura(CatLexica tipo) {
		return new ILectura(tipo);
	}

	public static Instruccion nuevaIEscritura(CatLexica tipo) {
		return new IEscritura(tipo);
	}
}
