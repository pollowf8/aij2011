
public class Token {
	private String lexema;
	private CatLexica categoria;
	int fila;
	int col;

	public Token(int fila, int col, CatLexica categoria, String lexema) {
		this.fila = fila;
		this.col = col;
		this.categoria = categoria;
		this.lexema = lexema;
	}

	public Token(int fila, int col, CatLexica categoria) {
		this(fila, col, categoria, null);
	}
	
	  public Token(CatLexica categoria,String lexema) {
	      this(-1,-1,categoria,lexema);
	  }

	public String leeLexema() {
		return lexema;
	}

	public int leeFila() {
		return fila;
	}

	public int leeCol() {
		return col;
	}

	public CatLexica leeCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		return "<cat:" + categoria + ",lex:" + lexema + ">";
	}

}
