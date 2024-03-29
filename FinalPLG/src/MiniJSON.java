
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class MiniJSON {

	public static void main(String[] args) throws IOException {
		InputStream input = new FileInputStream(args[0]);
		AnalizadorLexico analizadorLexico = new AnalizadorLexico(input);
		ConstructorArboles analizadorSintactico = new ConstructorArboles(
				analizadorLexico);
		analizadorSintactico.parse();
		input.close();
	}
}