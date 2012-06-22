import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class VM {
	private Stack<PValue> pilaEvaluacion;
	private Instruccion[] programa;
	private int cp;
	private PValue stop;
	// private ArrayList<Integer> memoria;
	private PValue[] memoria;
	private int memStatica;

	// para una m√°quina m√°s complicada existir√° tambi√©n una memoria de
	// evaluaci√≥n. Puede representarse, por ejemplo, mediante un array,
	// o mediante Vector (aunque Vector, como Stack, suponen una carga
	// importante para el programa).

	public static abstract class PValue {
		public int asInt() {
			throw new UnsupportedOperationException("asInt");
		}

		// puede introducirse un nuevo m√©todo para cada tipo de valor basico de
		// la
		// maquina P
		public boolean asBoolean() {
			throw new UnsupportedOperationException("asBoolean");
		}
	}

	// Puede proporcionarse una subclase de PValue para cada tipo de valor
	// basico
	// de la m√°quina P.
	public static class IntPValue extends PValue {
		private int value;

		public IntPValue(int value) {
			this.value = value;
		}

		public int asInt() {
			return value;
		}

		public String toString() {
			return String.valueOf(value);
		}
	}

	public static class BooleanPValue extends PValue {
		private boolean value;

		public BooleanPValue(boolean value) {
			this.value = value;
		}

		public boolean asBoolean() {
			return value;
		}

		public String toString() {
			return new Boolean(value).toString();
		}
	}
	
	public static class ReservaPValue extends PValue {

		public ReservaPValue() {
		}

		public String toString() {
			return String.valueOf("R");
		}
	}

	public VM(String fprograma, String statica) {
		try {
			int sizeMem = Integer.parseInt(statica);
			memoria = new PValue[sizeMem];// AÒadido
			memStatica = (int) (0.75 * sizeMem);
			pilaEvaluacion = new Stack<PValue>();
			List<Instruccion> instrucciones = (List<Instruccion>) new ObjectInputStream(
					new FileInputStream(fprograma)).readObject();
			programa = new Instruccion[instrucciones.size()];
			int j = 0;
			for (Instruccion i : instrucciones)
				programa[j++] = i;
			cp = 0;
			stop = new IntPValue(j - 1);
		} catch (Exception e) {
			System.err.println("Error al cargar el programa:" + e);
			System.exit(1);
		}
	}

	public void run(boolean traza) {
		if (traza)
			System.out.println(pilaEvaluacion);
		try {
			while (cp < programa.length) {
				if (traza)
					System.out.print(programa[cp] + "=>");
				programa[cp].ejecuta(this);
				if (traza) {
					System.out.println(pilaEvaluacion);
					System.out.println(memTostring());
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("FIN DE PROGRAMA");
		}
	}

	public void push(PValue value) {
		pilaEvaluacion.push(value);
	}

	public PValue pop() {
		return pilaEvaluacion.pop();
	}
	
	public PValue peek() {
		return pilaEvaluacion.peek();
	}

	public void incCP() {
		cp++;
	}

	public void setCP(int newCp) {
		cp = newCp;
	}

	public static void main(String args[]) {
		if (args.length != 1) {
			System.err.println("ERROR - debe indicarse archivo a interpretar");
			System.exit(1);
		}
		VM vm = new VM(args[0], args[1]);
		vm.run(true);
	}

	// aÒadido
	public void addValMem(int dir, PValue op1) {
		// try {
		// memoria.ensureCapacity(dir + 1);
		// memoria.set(dir, val);
		// } catch (IndexOutOfBoundsException e) {
		// for (int i = 0; i < dir; i++) {
		// if (memoria.get(i) == -1)
		// memoria.set(i, -1);
		// }
		// }
		//
		ensureCap(dir + 1);
		memoria[dir] = op1;
	}

	private void ensureCap(int minCapacity) {
		int oldCapacity = memoria.length;
		if (minCapacity > oldCapacity) {
			Object oldData[] = memoria;
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			// minCapacity is usually close to size, so this is a win:
			memoria = Arrays.copyOf(memoria, newCapacity);
		}
	}

	private String memTostring() {
		StringBuilder sb = new StringBuilder("[");
		for (PValue i : memoria) {
			sb.append(i + ",");
		}
		sb.setLength(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

	public PValue getValMem(int dir) {
		try {
			PValue a = memoria[dir];
			return a;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public Integer reserva(int tamReserva) {
		int i = memStatica-1;//donde se inicia el heap
		for (int j = 0; j < memoria.length-memStatica; j++) {
			for (; reservado(i); i++)
				;// primera pos

			//probar tamaÒo reserva
			int z = i;
			int y=0;
			for (; y < tamReserva &&  !reservado(z) ; z++,y++)
				;
			if (y >= tamReserva){
				z = i;
				for (y=0; y < tamReserva &&  !reservado(z) ; z++,y++)
					memoria[z]=new ReservaPValue();
				return i;// suficiente tamaÒo
			}
		}
		return null;
	}

	/**
	 * @param z
	 * @return
	 */
	private boolean reservado(int z) {
		return memoria[z] instanceof ReservaPValue;
	}
	

	public void libera(int posInicio, int tamReserva) {
		for (int j = posInicio; j < posInicio+tamReserva; j++)
			memoria[j] = null;
	}

	public void setMemStatica(int s) {
		this.memStatica = s;
	}

	public PValue getStop() {
		return stop;
	}

	public void parar(String msg) {
		programa[stop.asInt()].ejecuta(this);
		System.err.print(msg);
	}

}
