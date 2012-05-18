import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;


public class Tiny {

	public static void main(String[] args) throws IOException {
		try {
			if (args.length != 2) {
				System.err
						.println("ERROR: Se precisa indicar el archivo fuente y el archivo objeto");
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
			}
		} catch (Exception e) {
			System.err.println("ERROR:" + e);
			System.exit(1);
		}
	}
}