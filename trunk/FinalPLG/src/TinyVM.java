import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

/**
 * Ejecuta el tiny y el VM Toma 3 parametros, archivo fuente, archivo objeto y
 * memoria estatica
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 18/05/2012
 */
public class TinyVM {
	public static void main(String[] args) throws IOException {
		try {
			if (args.length != 3) {
				System.err
						.println("ERROR: Se precisa indicar el archivo fuente,el archivo objeto y memoria estatica");
				System.exit(0);
			}
			InputStream input = new FileInputStream(args[0]);
			AnalizadorLexico analizadorLexico = new AnalizadorLexico(input);
			ConstructorArboles constructorArboles = new ConstructorArboles(
					analizadorLexico);
			GA.Programa programa = constructorArboles.parse();

			Evaluador evaluador = new Evaluador();
			if (evaluador.evalua(programa.err()).val().hayError()) {
				for (String e : programa.err().val().errores())
					System.out.println(e);
			} else {
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(args[1]));
				out.writeObject(evaluador.evalua(programa.cod()).val());
				VM vm = new VM(args[1], args[2]);
				vm.run(true);
			}
		} catch (Exception e) {
			System.err.println("ERROR:" + e);
			System.exit(1);
		}
	}
}
