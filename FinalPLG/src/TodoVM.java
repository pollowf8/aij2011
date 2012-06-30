import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

/**
 * Ejecuta el tiny y el VM de todos los archivos de prueba
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 18/05/2012
 */
public class TodoVM {
	public static void main(String[] args) throws IOException {
		String[] progs = { "casedo.txt", "caseif.txt", "inreg.txt", "invect.txt", "invectpunt.txt",
				"procs.txt", "procsVarsGlobales.txt", "punteroArray.txt", "punteros.txt",
				"punteros2.txt", "pvar_var.txt", "pvar.txt" };
		try {
			if (args.length != 2) {
				System.err
						.println("ERROR: Se precisa indicar el archivo objeto y memoria estatica");
				System.exit(0);
			}
			for (int i = 0; i < progs.length; i++) {
				System.out.println("EJECUTANDO: "+progs[i]);
				InputStream input = new FileInputStream(progs[i]);
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
							new FileOutputStream(args[0]));
					out.writeObject(evaluador.evalua(programa.cod()).val());
					VM vm = new VM(args[0], args[1]);
					vm.run(true);
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR:" + e);
			System.exit(1);
		}
	}
}
