
import java.util.Stack;

public class Evaluador {
	public Evaluador() {
		atributos = new Stack<Atributo>();
	}

	public <Val> Atributo<Val> evalua(Atributo<Val> atributo) {
		atributos.push(atributo);
		while (!atributos.isEmpty()) {
			Atributo top = atributos.peek();
			Atributo newTop = top.siguienteDependencia();
			if (newTop == null) {
				if (Config.DEBUG)
					System.err.print("VALOR DE " + top.descripcion() + "\n");
				top.calcula();
				if (Config.DEBUG)
					System.err.println(top.val());
				atributos.pop();
			} else
				atributos.push(newTop);
		}
		return atributo;
	}

	private Stack<Atributo> atributos;
}
