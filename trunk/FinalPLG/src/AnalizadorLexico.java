import java.io.InputStream;
import java.io.IOException;

enum EstadoLexico {

	INICIO, RECNUM, RECDIG, RECPUNTO, RECCOMA, RECACENTO, RECCERO, RECIDEN, RECMAS, RECMENOS, RECASTERISCO, RECBARRA, RECPAP, RECPCIERRE, INITIGUAL, DOSPUNTOS, RECIGUAL, RECMAYOR, RECCAP, RECCIERRE, RECCORCHETE, RECPUNTOCOMA, RECAMPERSAND, RECEOF, RECCOM, RECLLAP, RECLLCIERRE
};

public class AnalizadorLexico {

	private final static int EOF = -1;
	private int caract;
	private EstadoLexico estado;
	private InputStream input;
	private String lexema;
	private int fila;
	private int col;
	private int filaInicio;
	private int colInicio;

	public AnalizadorLexico(InputStream input) throws IOException {
		this.input = input;
		fila = 0;
		col = -1;
		caract = sigCar();
	}

	public Token sigToken() throws IOException {
		estado = EstadoLexico.INICIO;
		filaInicio = fila;
		colInicio = col;
		lexema = "";
		while (true) {
			switch (estado) {
			case INICIO:
				if ((caract == '_') || esLetra(caract)) {
					transita(EstadoLexico.RECIDEN);
				} else if (esDigPos(caract)) {
					transita(EstadoLexico.RECNUM);
				} else if (esZero(caract)) {
					transita(EstadoLexico.RECCERO);
				} else if (caract == '^') {
					transita(EstadoLexico.RECACENTO);
				} else if (caract == '.') {
					transita(EstadoLexico.RECPUNTO);
				} else if (caract == '+') {
					transita(EstadoLexico.RECMAS);
				} else if (caract == '-') {
					transita(EstadoLexico.RECMENOS);
				} else if (caract == '*') {
					transita(EstadoLexico.RECASTERISCO);
				} else if (caract == '/') {
					transita(EstadoLexico.RECBARRA);
				} else if (caract == '(') {
					transita(EstadoLexico.RECPAP);
				} else if (caract == ')') {
					transita(EstadoLexico.RECPCIERRE);
				} else if (caract == ':') {
					transita(EstadoLexico.DOSPUNTOS);
				} else if (caract == '[') {
					transita(EstadoLexico.RECCAP);
				} else if (caract == ']') {
					transita(EstadoLexico.RECCIERRE);
				} else if (caract == ';') {
					transita(EstadoLexico.RECPUNTOCOMA);
				} else if (caract == ',') {
					transita(EstadoLexico.RECCOMA);
				} else if (caract == '&') {
					transita(EstadoLexico.RECAMPERSAND);
				} else if (caract == EOF) {
					transita(EstadoLexico.RECEOF);
				} else if (caract == '@') {
					transitaIgnorando(EstadoLexico.RECCOM);
				} else if (esSep(caract)) {
					transitaIgnorando(EstadoLexico.INICIO);
				} else if (caract == '{') {
					transita(EstadoLexico.RECLLAP);
				} else if (caract == '}') {
					transita(EstadoLexico.RECLLCIERRE);
				} else {
					errorLexico();
				}
				break;
			case RECMAS:
				return new Token(filaInicio, colInicio, CatLexica.MAS);
			case RECASTERISCO:
				return new Token(filaInicio, colInicio, CatLexica.ASTERISCO);
			case RECBARRA:
				return new Token(filaInicio, colInicio, CatLexica.BARRA);
			case RECPUNTOCOMA:
				return new Token(filaInicio, colInicio, CatLexica.PUNTOCOMA);
			case RECAMPERSAND:
				return new Token(filaInicio, colInicio, CatLexica.AMPERSAND);
			case RECPAP:
				return new Token(filaInicio, colInicio, CatLexica.PAP);
			case RECCOMA:
				return new Token(filaInicio, colInicio, CatLexica.COMA);
			case RECPCIERRE:
				return new Token(filaInicio, colInicio, CatLexica.PCIERRE);
			case RECLLAP:
				return new Token(filaInicio, colInicio, CatLexica.LLAVEAP);
			case RECLLCIERRE:
				return new Token(filaInicio, colInicio, CatLexica.LLAVECIERRE);
			case RECPUNTO:
				return new Token(filaInicio, colInicio, CatLexica.PUNTO);
			case RECACENTO:
				return new Token(filaInicio, colInicio, CatLexica.ACENTO);
			case DOSPUNTOS:
				if (caract == '=') {
					transita(EstadoLexico.RECIGUAL);
				} else {
					errorLexico();
				}
				break;
			case RECIGUAL:
				return new Token(filaInicio, colInicio, CatLexica.IGUAL);
			case RECMENOS:
				if (caract == '>') {
					transita(EstadoLexico.RECMAYOR);
				} else {
					return new Token(filaInicio, colInicio, CatLexica.MENOS);
				}
				break;
			case RECMAYOR:
				return new Token(filaInicio, colInicio, CatLexica.FLECHA);
			case RECCIERRE:
				return new Token(filaInicio, colInicio, CatLexica.CCIERRE);
			case RECCAP:
				if (caract == ']') {
					transita(EstadoLexico.RECCORCHETE);
				} else if (esDigito(caract) || esLetra(caract)) {
					// lexema = "";
					// transita(EstadoLexico.RECNUM);
					return new Token(filaInicio, colInicio, CatLexica.CAP);
				} else {
					errorLexico();
				}
				break;
			case RECCORCHETE:
				return new Token(filaInicio, colInicio, CatLexica.CORCHETES);
			case RECNUM:
				if (esDigito(caract)) {
					transita(EstadoLexico.RECNUM);
				} else if (caract == ']') {
					return new Token(filaInicio, colInicio, CatLexica.NUM,
							lexema);
				} else {
					return new Token(filaInicio, colInicio, CatLexica.NUM,
							lexema);
				}
				break;
			case RECCERO:
				return new Token(filaInicio, colInicio, CatLexica.NUM, lexema);
			case RECIDEN:
				if (esLetra(caract) || esDigito(caract) || caract == '_') {
					transita(EstadoLexico.RECIDEN);
				} else {
					return tokenSimbolo();
				}
				break;
			case RECEOF:
				return new Token(filaInicio, colInicio, CatLexica.EOF);
			case RECCOM:
				if (caract != '\n' && caract != EOF) {
					transitaIgnorando(EstadoLexico.RECCOM);
				} else {
					transitaIgnorando(EstadoLexico.INICIO);
				}
				break;
			}
		}
	}

	private void transita(EstadoLexico aEstado) throws IOException {
		lexema = lexema + (char) caract;
		transitaIgnorando(aEstado);
	}

	private void transitaIgnorando(EstadoLexico aEstado) throws IOException {
		estado = aEstado;
		filaInicio = fila;
		colInicio = col;
		caract = sigCar();
	}

	private int sigCar() throws IOException {
		caract = input.read();
		if (caract == '\n') {
			col = -1;
			fila++;
		} else {
			col++;
		}
		return caract;
	}

	private boolean esLetra(int car) {
		return (car >= 'a' && car <= 'z') || (car >= 'A' && car <= 'Z');
	}

	private boolean esDigPos(int car) {
		return (car >= '1' && car <= '9');
	}

	private boolean esZero(int car) {
		return (car == '0');
	}

	private boolean esDigito(int car) {
		return (car >= '0' && car <= '9');
	}

	private boolean esSep(int car) {
		return car == ' ' || car == '\t' || car == '\n' || car == '\r'
				|| car == '\b';
	}

	private void errorLexico() {
		System.err.println("(" + fila + "," + col + ")"
				+ "ERROR LEXICO: caracter desconocido:" + (char) caract);
		System.exit(1);
	}

	;

	private Token tokenSimbolo() {
		if (lexema.equals("true")) {
			return new Token(filaInicio, colInicio, CatLexica.TRUE);
		} else if (lexema.equals("false")) {
			return new Token(filaInicio, colInicio, CatLexica.FALSE);
		} else if (lexema.equals("and")) {
			return new Token(filaInicio, colInicio, CatLexica.AND);
		} else if (lexema.equals("or")) {
			return new Token(filaInicio, colInicio, CatLexica.OR);
		} else if (lexema.equals("not")) {
			return new Token(filaInicio, colInicio, CatLexica.NOT);
		} else if (lexema.equals("eq")) {
			return new Token(filaInicio, colInicio, CatLexica.EQ);
		} else if (lexema.equals("neq")) {
			return new Token(filaInicio, colInicio, CatLexica.NEQ);
		} else if (lexema.equals("lt")) {
			return new Token(filaInicio, colInicio, CatLexica.LT);
		} else if (lexema.equals("le")) {
			return new Token(filaInicio, colInicio, CatLexica.LE);
		} else if (lexema.equals("gt")) {
			return new Token(filaInicio, colInicio, CatLexica.GT);
		} else if (lexema.equals("ge")) {
			return new Token(filaInicio, colInicio, CatLexica.GE);
		} else if (lexema.equals("if")) {
			return new Token(filaInicio, colInicio, CatLexica.IF);
		} else if (lexema.equals("fi")) {
			return new Token(filaInicio, colInicio, CatLexica.FI);
		} else if (lexema.equals("do")) {
			return new Token(filaInicio, colInicio, CatLexica.DO);
		} else if (lexema.equals("od")) {
			return new Token(filaInicio, colInicio, CatLexica.OD);
		} else if (lexema.equals("case")) {
			return new Token(filaInicio, colInicio, CatLexica.CASE);
		} else if (lexema.equals("int")) {
			return new Token(filaInicio, colInicio, CatLexica.INT);
		} else if (lexema.equals("boolean")) {
			return new Token(filaInicio, colInicio, CatLexica.BOOLEAN);
		} else if (lexema.equals("escribir")) {
			return new Token(filaInicio, colInicio, CatLexica.ESCRIBIR);
		} else if (lexema.equals("leer")) {
			return new Token(filaInicio, colInicio, CatLexica.LEER);
		} else if (lexema.equals("tipo")) {
			return new Token(filaInicio, colInicio, CatLexica.TIPO);
		} else if (lexema.equals("proc")) {
			return new Token(filaInicio, colInicio, CatLexica.PROC);
		} else if (lexema.equals("new")) {
			return new Token(filaInicio, colInicio, CatLexica.NEW);
		} else if (lexema.equals("delete")) {
			return new Token(filaInicio, colInicio, CatLexica.DELETE);
		} else if (lexema.equals("tabla")) {
			return new Token(filaInicio, colInicio, CatLexica.TABLA);
		} else if (lexema.equals("registro")) {
			return new Token(filaInicio, colInicio, CatLexica.REG);
		} else if (lexema.equals("puntero")) {
			return new Token(filaInicio, colInicio, CatLexica.PUNTERO);
		} else if (lexema.equals("de")) {
			return new Token(filaInicio, colInicio, CatLexica.DE);
		} else if (lexema.equals("var")) {
			return new Token(filaInicio, colInicio, CatLexica.VAR);
		} else {
			return new Token(filaInicio, colInicio, CatLexica.IDEN, lexema);
		}
	}

}
