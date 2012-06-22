import java.io.Serializable;
import java.util.Scanner;

abstract public class Instruccion implements Serializable {
	public enum ICOD {
		SUM, MUL, APILA, DESAPILA_DIR, APILA_TRUE, APILA_FALSE, APILA_INT, APILA_DIR, EQ, NEQ, GT, GE, LT, LE, RESTA, OR, DIV, AND, MENOS, NOT, IR_F, IR_A, APILA_IND, DESAPILA_IND, MUEVE, INEW, IDEL, DESAPILA, IESCRITURA, ILECTURA, ICOPIA, IR_IND, STOP, ISEG
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
	private static IIr_ind iIr_ind= null;
	private static IApilaTrue iApilaTrue= null;
	private static IDesapila iDesapila= null;
	private static IApilaFalse iApilaFalse= null;
	private static IApilaInd iApilaInd;
	private static IDesapilaInd iDesapilaInd;
	private static ICopia iCopia;
	private static IStop iStop;

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

		public void set_arg1(int e) {
			this.etq = e;
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
			vm.addValMem(dir, op1);
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
			VM.PValue a = vm.getValMem(dir);
			vm.push(a);
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
			for (int i = 0; i < tamMover; i++) {
				VM.PValue a = vm.getValMem(origen.asInt() + i);
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
			return "MUEVE(" + tamMover + ")";
		}

		private int tamMover;

	}

	// Interpreta la cima de la pila como un valor, la subcima como una
	// dir, y se lleva el valor a la direccion de memoria indicada
	public static class IDesapilaInd extends Instruccion {

		private IDesapilaInd() {

		}

		public void ejecuta(VM vm) {
			VM.PValue op1 = vm.pop();
			VM.PValue dir = vm.pop();
			vm.addValMem(dir.asInt(), op1);
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.DESAPILA_IND;
		}

		public String toString() {
			return "DESAPILA_IND()";
		}

	}

	// Interpreta la cima de la pila como una direccion, la desapila y se trae
	// de memoria el valor referenciado por ella
	public static class IApilaInd extends Instruccion {

		private IApilaInd() {
		}

		public void ejecuta(VM vm) {
			VM.PValue dir = vm.pop();
			VM.PValue a = vm.getValMem(dir.asInt());
			// sino definido pone posicion de fin, MEJORABLE
			//para solventar problema de ir_ind sin dir de retorno
			vm.push((a == null ? vm.getStop() : a));
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.APILA_IND;
		}

		public String toString() {
			return "APILA_IND()";
		}

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

	// Reserva en el heap tamReserva, apilando la direccion de comienzo de esta
	// reserva en la cima de la pila
	public static class INew extends Instruccion {

		private INew(String tamReserva) {
			try {
				this.tamReserva = Integer.valueOf(tamReserva).intValue();
			} catch (NumberFormatException e) {
				this.tamReserva = 0;
			}
		}

		public void ejecuta(VM vm) {
			Integer posLibre = vm.reserva(tamReserva);
			if (posLibre == null)
				vm.parar("No espacio en el heap para: " + tamReserva);
			else {
				vm.push(new VM.IntPValue(posLibre));
				vm.incCP();
			}
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

	//Modifica la asignacion de memoria de la maquina P, asignando para la memoria estatica sizeMemStatica
	public static class ISeg extends Instruccion {

		private ISeg(String sizeMemEstatica) {
			try {
				this.sizeMemEstatica = Integer.valueOf(sizeMemEstatica)
						.intValue();
			} catch (NumberFormatException e) {
				this.sizeMemEstatica = 0;
			}
		}

		public void ejecuta(VM vm) {
			vm.setMemStatica(sizeMemEstatica);
		}

		public ICOD ci() {
			return ICOD.ISEG;
		}

		public int arg1() {
			return sizeMemEstatica;
		}

		public String toString() {
			return "ISeg(" + sizeMemEstatica + ")";
		}

		private int sizeMemEstatica;

	}

	//Libera tamReserva posiciones a partir de una direccion de comienzo que encuentra en la cima de la pila
	public static class IDel extends Instruccion {

		private IDel(String tamReserva) {
			try {
				this.tamReserva = Integer.valueOf(tamReserva).intValue();
			} catch (NumberFormatException e) {
				this.tamReserva = 0;
			}
		}

		public void ejecuta(VM vm) {
			VM.PValue posInicio = vm.pop();
			vm.libera(posInicio.asInt(), tamReserva);
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

	//Lee un valor de teclado, sea de tipo int o boolean, y apila su valor en la cima de la pila
	public static class ILectura extends Instruccion {

		private ILectura(CatLexica tipo) {
			this.tipo = tipo;
		}

		public void ejecuta(VM vm) {
			if (sc == null)
				sc = new Scanner(System.in);
			if (tipo == CatLexica.INT) {
				vm.push(new VM.IntPValue(sc.nextInt()));
			} else if (tipo == CatLexica.BOOLEAN) {
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
			return "LEER(" + tipo + ")";
		}

		Scanner sc;
		private CatLexica tipo;
	}

	//Escribe un valor en pantalla obteniendolo de la cima de la pila
	public static class IEscritura extends Instruccion {

		private IEscritura(CatLexica tipo) {
			this.tipo = tipo;
		}

		public void ejecuta(VM vm) {
			VM.PValue p = vm.pop();
			System.out.print(p);
			vm.incCP();
		}

		public ICOD ci() {
			return ICOD.IESCRITURA;
		}

		public int arg1() {
			return tipo.ordinal();
		}

		public String toString() {
			return "ESCRIBIR(" + tipo + ")";
		}

		private CatLexica tipo;
	}

	//Coge el valor de la cima de la pila y lo apila de nuevo
	public static class ICopia extends Instruccion {

		private ICopia() {

		}

		public void ejecuta(VM vm) {
			VM.PValue op1 = vm.peek();
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

	//Para la ejecucion de la maquina p, poniendo una posicion no valida
	public static class IStop extends Instruccion {

		private IStop() {

		}

		public void ejecuta(VM vm) {
			vm.setCP(-1);
		}

		public ICOD ci() {
			return ICOD.STOP;
		}

		public String toString() {
			return "STOP()";
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
		if (iIr_ind == null) {
			iIr_ind = new IIr_ind();
		}
		return iIr_ind;
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
		if (iDesapila == null) {
			iDesapila = new IDesapila();
		}
		return iDesapila;
	}

	public static Instruccion nuevaIApilaTrue() {
		if (iApilaTrue == null) {
			iApilaTrue = new IApilaTrue();
		}
		return iApilaTrue;
	}

	public static Instruccion nuevaIApilaFalse() {
		if (iApilaFalse == null) {
			iApilaFalse = new IApilaFalse();
		}
		return iApilaFalse;
	}

	public static Instruccion nuevaIApilaInt(String val) {
		return new IApilaInt(val);
	}

	public static Instruccion nuevaIApilaInd() {
		if (iApilaInd == null) {
			iApilaInd = new IApilaInd();
		}
		return iApilaInd;
	}

	public static Instruccion nuevaIDesApilaInd() {
		if (iDesapilaInd == null) {
			iDesapilaInd = new IDesapilaInd();
		}
		return iDesapilaInd;
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
		if (iCopia == null) {
			iCopia = new ICopia();
		}
		return iCopia;
	}
	public static Instruccion nuevaIStop() {
		if (iStop == null) {
			iStop = new IStop();
		}
		return iStop;
	}
	public static Instruccion nuevaILectura(CatLexica tipo) {
		return new ILectura(tipo);
	}

	public static Instruccion nuevaIEscritura(CatLexica tipo) {
		return new IEscritura(tipo);
	}

	
}
