import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class Tiny {

    public static void main(String[] args) throws IOException {
       InputStream input = new FileInputStream(args[0]);   
       AnalizadorLexico analizadorLexico = new AnalizadorLexico(input);
       ConstructorArbolesTiny constructorArboles = 
                   new ConstructorArbolesTiny(analizadorLexico);
       GA.Programa programa = constructorArboles.parse();
	   if (programa.err().hayError()) {
	     for(String e: programa.err().errores())
		    System.out.println(e);
	   }
	   else 
	      System.out.println(programa.cod());
    }		  
 }
