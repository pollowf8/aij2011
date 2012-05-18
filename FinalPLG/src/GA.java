import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GA {
	//private int identado;

	public GA() {
		//identado = -1;
	}

	public abstract class Programa {

		protected Programa() {
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		private Atributo<Error> err;
		private Atributo<List<Instruccion>> cod;
	}

	public abstract class Decs {
		protected Decs() {
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			ts = new Atributo<TS>();
			ts.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ts_exp();
				}
			});
			dir = new Atributo<Integer>();
			dir.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return dir_exp();
				}
			});
		}

		public Atributo<Integer> dir() {
			return dir;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<TS> ts() {
			return ts;
		}

		protected abstract Error err_exp();

		protected abstract TS ts_exp();

		protected abstract Integer dir_exp();

		private Atributo<Error> err;
		private Atributo<TS> ts;
		private Atributo<Integer> dir;
	}

	public abstract class Dec {
		protected Dec() {
			iden = new Atributo<String>();
			iden.fijaExpresion(new ExpSem<String>() {
				public String val() {
					return iden_exp();
				}
			});
			tipo = new Atributo<CatLexica>();
			tipo.fijaExpresion(new ExpSem<CatLexica>() {
				public CatLexica val() {
					return tipo_exp();
				}
			});
			fila = new Atributo<Integer>();
			fila.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return fila_exp();
				}
			});
			col = new Atributo<Integer>();
			col.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return col_exp();
				}
			});
		}

		public Atributo<CatLexica> tipo() {
			return tipo;
		}

		public Atributo<String> iden() {
			return iden;
		}

		public Atributo<Integer> fila() {
			return fila;
		}

		public Atributo<Integer> col() {
			return col;
		}

		protected abstract String iden_exp();

		protected abstract Integer fila_exp();

		protected abstract Integer col_exp();

		protected abstract CatLexica tipo_exp();

		private Atributo<String> iden;
		private Atributo<CatLexica> tipo;
		private Atributo<Integer> fila;
		private Atributo<Integer> col;
	}

	public abstract class Tipo {
		protected Tipo() {
//			tipo = new Atributo<CatLexica>();
//			tipo.fijaExpresion(new ExpSem<CatLexica>() {
//				public CatLexica val() {
//					return tipo_exp();
//				}
//			});
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
		}

		protected abstract Error err_exp();

		//protected abstract CatLexica tipo_exp();

		public CatLexica tipo() {
			return tipo;
		}

		public Atributo<Error> err() {
			return err;
		}

		protected CatLexica tipo;
		private Atributo<Error> err;
	}

	// Instrucciones
	abstract public class Insts {
		protected Insts() {
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.etqh_exp();
				}
			});
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<Integer> etqh() {
			return etqh;
		}

		public Atributo<Integer> etq() {
			return etq;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		private Atributo<Error> err;
		private Atributo<List<Instruccion>> cod;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<TS> tsh;
		private InstsCtx ctx;

		public void registraCtx(InstsCtx ctx) {
			this.ctx = ctx;
		}

		public InstsCtx contexto() {
			return ctx;
		}
	}

	public interface InstsCtx {
		public TS tsh_exp();

		public int etqh_exp();
	}

	// Instruccion
	abstract public class Inst {

		protected Inst() {
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.etqh_exp();
				}
			});
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});

		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<Integer> etqh() {
			return etqh;
		}

		public Atributo<Integer> etq() {
			return etq;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		private Atributo<Error> err;
		private Atributo<List<Instruccion>> cod;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<TS> tsh;
		private InstCtx ctx;

		public void registraCtx(InstCtx ctx) {
			this.ctx = ctx;
		}

		public InstCtx contexto() {
			return ctx;
		}
	}

	public interface InstCtx {
		public TS tsh_exp();

		public int etqh_exp();
	}

	// Casos
	abstract public class Casos {

		protected Casos() {
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.etqh_exp();
				}
			});
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			irh = new Atributo<Integer>();
			irh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.irh_exp();
				}
			});
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<Integer> etqh() {
			return etqh;
		}

		public Atributo<Integer> irh() {
			return irh;
		}

		public Atributo<Integer> etq() {
			return etq;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		private Atributo<Error> err;
		private Atributo<List<Instruccion>> cod;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<Integer> irh;
		private Atributo<TS> tsh;
		private CasosCtx ctx;

		public void registraCtx(CasosCtx ctx) {
			this.ctx = ctx;
		}

		public CasosCtx contexto() {
			return ctx;
		}

	}

	public interface CasosCtx {
		public int etqh_exp();

		public TS tsh_exp();

		public int irh_exp();
	}

	// Caso
	abstract public class Caso {

		protected Caso() {

			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.etqh_exp();
				}
			});
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			irh = new Atributo<Integer>();
			irh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.irh_exp();
				}
			});
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<Integer> etqh() {
			return etqh;
		}

		public Atributo<Integer> irh() {
			return irh;
		}

		public Atributo<Integer> etq() {
			return etq;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		private Atributo<Error> err;
		private Atributo<List<Instruccion>> cod;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<Integer> irh;
		private Atributo<TS> tsh;
		private CasoCtx ctx;

		public void registraCtx(CasoCtx ctx) {
			this.ctx = ctx;
		}

		public CasoCtx contexto() {
			return ctx;
		}
	}

	public interface CasoCtx {
		public int etqh_exp();

		public TS tsh_exp();

		public int irh_exp();
	}

	abstract public class Exp {

		protected Exp() {
			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.etqh_exp();
				}
			});
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			tipo = new Atributo<CatLexica>();
			tipo.fijaExpresion(new ExpSem<CatLexica>() {
				public CatLexica val() {
					return tipo_exp();
				}
			});
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<Integer> etqh() {
			return etqh;
		}

		public Atributo<Integer> etq() {
			return etq;
		}

		public Atributo<CatLexica> tipo() {
			return tipo;
		}

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		protected abstract CatLexica tipo_exp();

		private Atributo<List<Instruccion>> cod;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<TS> tsh;
		private Atributo<CatLexica> tipo;
		private ExpCtx ctx;

		public void registraCtx(ExpCtx ctx) {
			this.ctx = ctx;
		}

		public ExpCtx contexto() {
			return ctx;
		}
	}

	public interface ExpCtx {
		public TS tsh_exp();

		public Integer etqh_exp();
	}

	abstract public class Exp0 extends Exp {
	};

	abstract public class Exp1 extends Exp {
	};

	abstract public class Exp2 extends Exp {
	};

	abstract public class Exp3 extends Exp {
	};

	abstract public class Exp4 extends Exp {
	};

	abstract public class Exp5 extends Exp {
	};

	public abstract class Op {
		protected Op() {

			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
			op = new Atributo<CatLexica>();
			op.fijaExpresion(new ExpSem<CatLexica>() {
				public CatLexica val() {
					return op_exp();
				}
			});
		}

		public Atributo<CatLexica> op() {
			return op;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		protected abstract CatLexica op_exp();

		protected abstract List<Instruccion> cod_exp();

		private Atributo<List<Instruccion>> cod;
		private Atributo<CatLexica> op;
	}

	public abstract class OpComparacion extends Op {
	}

	public abstract class OpAditivo extends Op {
	}

	public abstract class OpMultiplicativo extends Op {
	}

	public abstract class OpUnario extends Op {
	}

	// Implementación clases
	/**
	 * <code>
	 * Programa ::= Declaraciones & Instrucciones 
	 * Instrucciones.tsh =Declaraciones.ts 
	 * Programa.error = Declaraciones.error or Instrucciones.error 
	 * Programa.cod = Instrucciones.cod
	 * </code>
	 */
	public class ProgR1 extends Programa {

		public ProgR1(Decs decs, Insts insts) {
			super();
			this.decs = decs;
			this.insts = insts;

			insts.registraCtx(new InstsCtx() {
				public TS tsh_exp() {
					return ProgR1.this.decs.ts().val();
				}

				public int etqh_exp() {
					return 0;
				}
			});

			err().ponDependencias(insts.err(),decs.err());
			cod().ponDependencias(insts.cod());
			insts.tsh().ponDependencias(decs.ts());
		}

		protected final Error err_exp() {
			return joinErrors(decs.err().val(), insts.err().val());
		}

		protected final List<Instruccion> cod_exp() {
			return insts.cod().val();
		}

		private Decs decs;
		private Insts insts;

	}

	public class ProgR1Debug extends ProgR1 {
		private final static String REGLA = "Programa ::= Declaraciones & Instrucciones";

		public ProgR1Debug(Decs decs, Insts insts) {
			super(decs, insts);
			err().fijaDescripcion(REGLA + " | Programa.err");
			cod().fijaDescripcion(REGLA + " | Programa.cod");
			insts.tsh().fijaDescripcion(REGLA + " | Instrucciones.tsh");
		}

	}

	/**
	 * <code>
	 * Programa ::= Instrucciones
	 * Instrucciones.tsh = creaTS() 
	 * Programa.error = Instrucciones.error 
	 * Instrucciones.etqh=0 
	 * Programa.cod = Instrucciones.cod
	 * </code>
	 */
	public class ProgR2 extends Programa {

		public ProgR2(Insts insts) {
			super();
			this.insts = insts;

			insts.registraCtx(new InstsCtx() {
				public TS tsh_exp() {
					return creaTS();
				}

				public int etqh_exp() {
					return 0;
				}
			});
			err().ponDependencias(insts.err());
			cod().ponDependencias(insts.cod());
		}

		protected final Error err_exp() {
			return insts.err().val();
		}

		protected final List<Instruccion> cod_exp() {
			return insts.cod().val();
		}

		private Insts insts;

	}

	public class ProgR2Debug extends ProgR2 {
		private final static String REGLA = "Programa ::= Instrucciones";

		public ProgR2Debug(Insts insts) {
			super(insts);
			err().fijaDescripcion(REGLA + " | Programa.err");
			cod().fijaDescripcion(REGLA + " | Programa.cod");
			insts.tsh().fijaDescripcion(REGLA + " | Instrucciones.tsh");
			insts.etqh().fijaDescripcion(REGLA + " | Instrucciones.etqh");
		}
	}

	/**
	 * <code>
	 * Declaraciones ::= Declaraciones ; Declaracion 
	 * Declaraciones(0).ts =aniadeSimb(Declaraciones(1).ts, Declaracion.iden, Declaracion.tipo,Declaraciones(1).dir) 
	 * Declaraciones(0).dir = Declaraciones(1).dir + 1
	 * Declaraciones(0).error = Declaraciones(1).error or existeSimb(Declaraciones(1).ts,Declaracion.id)
	 * </code>
	 */
	public class DecsR1 extends Decs {

		public DecsR1(Decs decs_1, Dec dec) {
			super();
			this.dec = dec;
			this.decs_1 = decs_1;
			err().ponDependencias(decs_1.ts(),dec.iden(),dec.fila(),dec.col(),decs_1.err());
			dir().ponDependencias(decs_1.dir());
			ts().ponDependencias(decs_1.ts(), decs_1.dir(),dec.iden(),dec.tipo());
		}

		public TS ts_exp() {
			return aniadeSimb(decs_1.ts().val(), dec.iden().val(), dec.tipo()
					.val(), decs_1.dir().val());
		}

		public Error err_exp() {
			return joinErrors(
					decs_1.err().val(),
					errorSi(existeSimb(decs_1.ts().val(), dec.iden().val()),
							dec.fila().val(), dec.col().val(),
							"Constante duplicada:" + dec.iden().val()));
		}

		public Integer dir_exp() {
			return decs_1.dir().val() + 1;
		}

		private Dec dec;
		private Decs decs_1;
	}

	public class DecsR1Debug extends DecsR1 {
		private final static String REGLA = "Declaraciones  ::= Declaraciones  ; Declaracion";

		public DecsR1Debug(Decs decs_1, Dec dec) {
			super(decs_1, dec);
			err().fijaDescripcion(REGLA + " | Declaraciones(0).err");
			dir().fijaDescripcion(REGLA + " | Declaraciones(0).dir");
			ts().fijaDescripcion(REGLA + " | Declaraciones(0).ts");
		}

	}

	/**
	 * <code>
	 * Decs ::= Dec 
	 * Declaraciones.ts = aniadeSimb(creaTS(),Declaracion.iden,Declaracion.tipo,0) 
	 * Declaraciones.dir = 1 
	 * Declaraciones.error = false
	 * </code>
	 */
	public class DecsR2 extends Decs {

		public DecsR2(Dec dec) {
			super();
			this.dec = dec;
			ts().ponDependencias(dec.iden(),dec.tipo());
		}

		public TS ts_exp() {
			return aniadeSimb(creaTS(), dec.iden().val(), dec.tipo().val(), 0);
		}

		public Error err_exp() {
			return noError();
		}

		public Integer dir_exp() {
			return 1;
		}

		private Dec dec;
	}

	public class DecsR2Debug extends DecsR2 {
		private final static String REGLA = "Declaraciones  ::= Declaracion";

		public DecsR2Debug(Dec dec) {
			super(dec);
			ts().fijaDescripcion(REGLA + " | Decs.ts");
			dir().fijaDescripcion(REGLA + " | Decs.dir");
			err().fijaDescripcion(REGLA + " | Decs.error");
		}
	}

	/**
	 * <code>
	 * Declaracion ::= Tipo IDEN 
	 * Declaracion.tipo = Tipo.tipo 
	 * Declaracion.iden =IDEN.lex 
	 * Declaracion.fila = id.fila 
	 * Declaracion.col = id.col
	 * </code>
	 */
	public class DecR1 extends Dec {

		public DecR1(Tipo tipo, Token IDEN) {
			super();
			this.tipo = tipo;
			this.iden = IDEN;
		}

		public String iden_exp() {
			return iden.leeLexema();
		}

		public CatLexica tipo_exp() {
			return tipo.tipo();
		}

		protected final Integer fila_exp() {
			return new Integer(iden.leeFila());
		}

		protected final Integer col_exp() {
			return new Integer(iden.leeCol());
		}

		private Token iden;
		private Tipo tipo;

	}

	public class DecR1Debug extends DecR1 {
		private final static String REGLA = "Declaracion ::= Tipo IDEN";

		public DecR1Debug(Tipo tipo, Token iden) {
			super(tipo, iden);
			iden().fijaDescripcion(REGLA + " | iden.lex");
			tipo().fijaDescripcion(REGLA + " | Declaracion.tipo");
			fila().fijaDescripcion(REGLA + " | Declaracion.fila");
			col().fijaDescripcion(REGLA + " | Declaracion.col");
		}

	}

	/*
	 * Tipo := INT
	 */
	public class TipoR1 extends Tipo {

		public TipoR1(Token tDeInt) {
			super();
			this.tipo = tDeInt.leeCategoria();
		}

		public CatLexica tipo_exp() {
			return tipo;
		}

		public Error err_exp() {
			if (this.tipo != CatLexica.INT)
				return new Error("No es un INT");
			else
				return noError();
		}

		//private CatLexica tipo;
	}

	public class TipoR1Debug extends TipoR1 {
		private final static String REGLA = "Tipo.tipo ::= int";

		public TipoR1Debug(Token tDeInt) {
			super(tDeInt);
			err().fijaDescripcion(REGLA + " | tipo.err");
			//tipo().fijaDescripcion(REGLA + " | tipo.tipo");
		}
	}

	/*
	 * Tipo := boolean
	 */
	public class TipoR2 extends Tipo {

		public TipoR2(Token tDeBol) {
			super();
			this.tipo = tDeBol.leeCategoria();
		}

		public Error err_exp() {
			if (this.tipo != CatLexica.BOOLEAN)
				return new Error("No es un BOOLEAN");
			else
				return noError();
		}

		public CatLexica tipo_exp() {
			return tipo;
		}

		//private CatLexica tipo;
	}

	public class TipoR2Debug extends TipoR2 {
		private final static String REGLA = "Tipo.tipo ::= boolean";

		public TipoR2Debug(Token tDeInt) {
			super(tDeInt);
			err().fijaDescripcion(REGLA + " | tipo.err");
			//tipo().fijaDescripcion(REGLA + " | tipo.tipo");
		}
	}

	/**
	 * <code>
	 * Instrucciones ::= Instrucciones ; Instruccion
	 * Instrucciones(1).tsh =Instrucciones(0).tsh 
	 * Instruccion.tsh = Instrucciones(0).tsh
	 * Instrucciones(0).error = Instrucciones(1).error || Instruccion.error
	 * Instrucciones(1).etqh = Instrucciones(0).etqh 
	 * Instruccion.etqh = Instrucciones(1).etq 
	 * Instrucciones(0).etq = Instruccion.etq
	 * Instrucciones(0).cod = Instrucciones(1).cod || Instruccion.cod
	 * </code>
	 */
	public class InstsR1 extends Insts {

		public InstsR1(Insts insts_1, Inst inst) {
			super();
			this.insts_1 = insts_1;
			this.inst = inst;

			insts_1.registraCtx(new InstsCtx() {
				public TS tsh_exp() {
					return InstsR1.this.tsh().val();
				}

				public int etqh_exp() {
					return InstsR1.this.etqh().val();
				}
			});

			inst.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstsR1.this.tsh().val();
				}

				public int etqh_exp() {
					return InstsR1.this.insts_1.etq().val();
				}
			});
			insts_1.tsh().ponDependencias(tsh());
			insts_1.etqh().ponDependencias(etqh());
			etq().ponDependencias(inst.etq());
			cod().ponDependencias(insts_1.cod(), inst.cod());
			err().ponDependencias(insts_1.err(), inst.err());
			inst.etqh().ponDependencias(insts_1.etq());
			inst.tsh().ponDependencias(tsh());

		}

		public Error err_exp() {
			return joinErrors(insts_1.err().val(), inst.err().val());
		}

		public List<Instruccion> cod_exp() {
			return concat(insts_1.cod().val(), inst.cod().val());
		}

		public Integer etq_exp() {
			return inst.etq().val();

		}

		private Insts insts_1;
		private Inst inst;
	}

	public class InstsR1Debug extends InstsR1 {
		private final static String REGLA = "Instrucciones(0) ::= Instrucciones(1) ; Instruccion";

		public InstsR1Debug(Insts insts_1, Inst inst) {
			super(insts_1, inst);
			insts_1.tsh().fijaDescripcion(REGLA + " | Instrucciones(1).tsh");
			insts_1.etqh().fijaDescripcion(REGLA + " | Instrucciones(1).etqh");
			err().fijaDescripcion(REGLA + " | Instrucciones(0).err");
			cod().fijaDescripcion(REGLA + " | Instrucciones(0).cod");
			etqh().fijaDescripcion(REGLA + " | Instrucciones(0).etqh");
			etq().fijaDescripcion(REGLA + " | Instrucciones(0).etq");
			inst.etqh().fijaDescripcion(REGLA + " | Instruccion.etqh");
			inst.tsh().fijaDescripcion(REGLA + " | Instruccion.tsh");
		}
	}

	/**
	 * <code>
	 * Instrucciones ::= Instruccion 
	 * Instruccion.tsh = Instrucciones.tsh
	 * Instrucciones.error = Instruccion.error 
	 * Instruccion.etqh = Instrucciones.etqh 
	 * Instrucciones.etq = Instruccion.etq 
	 * Instrucciones.cod = Instruccion.cod
	 * </code>
	 */
	public class InstsR2 extends Insts {

		public InstsR2(Inst inst) {
			super();
			this.inst = inst;
			inst.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstsR2.this.tsh().val();
				}

				public int etqh_exp() {
					return InstsR2.this.etqh().val();
				}
			});
			etq().ponDependencias(inst.etq());
			cod().ponDependencias(inst.cod());
			err().ponDependencias(inst.err());
			inst.etqh().ponDependencias(etqh());
			inst.tsh().ponDependencias(tsh());

		}

		public Error err_exp() {
			return inst.err().val();
		}

		public List<Instruccion> cod_exp() {
			return inst.cod().val();
		}

		public Integer etq_exp() {
			return inst.etq().val();
		}

		private Inst inst;
	}

	public class InstsR2Debug extends InstsR2 {
		private final static String REGLA = "Instrucciones ::= Instruccion";

		public InstsR2Debug(Inst inst) {
			super(inst);
			err().fijaDescripcion(REGLA + " | Instrucciones.err");
			cod().fijaDescripcion(REGLA + " | Instrucciones.cod");
			etq().fijaDescripcion(REGLA + " | Instrucciones.etq");
			inst.etqh().fijaDescripcion(REGLA + " | Instruccion.etqh");
			inst.tsh().fijaDescripcion(REGLA + " | Instruccion.tsh");
		}
	}

	/**
	 * <code>
	 * Instruccion ::= IAsignacion
	 * IAsignacion.tsh = Instruccion.tsh
	 * Instruccion.error = IAsignacion.error
	 * IAsignacion.etqh = Instruccion.etqh
	 * Instruccion.etq = IAsignacion.etq
	 * Instruccion.cod = IAsignacion.cod
	 * </code>
	 */
	public class InstR1 extends Inst {

		public InstR1(Inst iasig) {
			super();
			this.iasig = iasig;
			iasig.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstR1.this.tsh().val();
				}

				public int etqh_exp() {
					return InstR1.this.etqh().val();
				}
			});
			etq().ponDependencias(iasig.etq());
			cod().ponDependencias(iasig.cod());
			err().ponDependencias(iasig.err());
			iasig.etqh().ponDependencias(etqh());
			iasig.tsh().ponDependencias(tsh());
		}

		public Integer etq_exp() {
			return iasig.etq().val();

		}

		public Error err_exp() {
			return iasig.err().val();
		}

		public List<Instruccion> cod_exp() {
			return iasig.cod().val();
		}

		private Inst iasig;

	}

	public class InstR1Debug extends InstR1 {
		private final static String REGLA = "Instruccion ::= IAsignacion";

		public InstR1Debug(Inst iAsig) {
			super(iAsig);
			err().fijaDescripcion(REGLA + " | Instrucciones.err");
			cod().fijaDescripcion(REGLA + " | Instrucciones.cod");
			etq().fijaDescripcion(REGLA + " | Instrucciones.etq");
			iAsig.etqh().fijaDescripcion(REGLA + " | Iasignacion.etqh");
			iAsig.tsh().fijaDescripcion(REGLA + " | Iasignacion.tsh");
		}

	}

	/**
	 * <code>
	 * Instruccion ::= IIF 
	 * IIF.tsh = Instruccion.tsh 
	 * Instruccion.error = IIF.error 
	 * IIF.etqh = Instruccion.etqh 
	 * Instruccion.etq = IIF.etq
	 * Instruccion.cod = IIF.cod
	 * </code>
	 */
	public class InstR2 extends Inst {

		public InstR2(Inst iIF) {
			super();
			this.iIF = iIF;
			iIF.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstR2.this.tsh().val();
				}

				public int etqh_exp() {
					return InstR2.this.etqh().val();
				}
			});

			err().ponDependencias(iIF.err());
			etq().ponDependencias(iIF.etq());
			cod().ponDependencias(iIF.cod());
			iIF.tsh().ponDependencias(tsh());
			iIF.etqh().ponDependencias(etqh());
		}

		public Integer etq_exp() {
			return iIF.etq().val();
		}

		public Error err_exp() {

			return iIF.err().val();

		}

		public List<Instruccion> cod_exp() {
			return iIF.cod().val();
		}

		private Inst iIF;
	}

	public class InstR2Debug extends InstR2 {
		private final static String REGLA = "Instruccion ::= IIF ";

		public InstR2Debug(Inst iIF) {
			super(iIF);
			err().fijaDescripcion(REGLA + " | Instruccion.err");
			etq().fijaDescripcion(REGLA + " | Instruccion.etq");
			cod().fijaDescripcion(REGLA + " | Instruccion.cod");
			iIF.tsh().fijaDescripcion(REGLA + " | IIF.tsh");
			iIF.etqh().fijaDescripcion(REGLA + " | IIF.etqh");
		}
	}

	/**
	 * <code>
	 * Instruccion ::= IDO 
	 * IDO.tsh = Instruccion.tsh 
	 * Instruccion.error = IDO.error 
	 * IDO.etqh = Instruccion.etqh 
	 * Instruccion.etq = IDO.etq
	 * Instruccion.cod = IDO.cod
	 * </code>
	 */
	public class InstR3 extends Inst {

		public InstR3(Inst iDO) {
			super();
			this.iDO = iDO;
			iDO.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstR3.this.tsh().val();
				}

				public int etqh_exp() {
					return InstR3.this.etqh().val();
				}

			});
			err().ponDependencias(iDO.err());
			etq().ponDependencias(iDO.etq());
			cod().ponDependencias(iDO.cod());
			iDO.tsh().ponDependencias(tsh());
			iDO.etqh().ponDependencias(etqh());
		}

		public Integer etq_exp() {
			return iDO.etq().val();
		}

		public Error err_exp() {
			return iDO.err().val();
		}

		public List<Instruccion> cod_exp() {
			return iDO.cod().val();
		}

		private Inst iDO;
	}

	public class InstR3Debug extends InstR3 {
		private final static String REGLA = "Instruccion ::= IDO";

		public InstR3Debug(Inst iDO) {
			super(iDO);
			err().fijaDescripcion(REGLA + " | Instruccion.err");
			etq().fijaDescripcion(REGLA + " | Instruccion.etq");
			cod().fijaDescripcion(REGLA + " | Instruccion.cod");
			iDO.tsh().fijaDescripcion(REGLA + " | IDO.tsh");
			iDO.etqh().fijaDescripcion(REGLA + " | IDO.etqh");
		}
	}

	/**
	 * <code>
	 * IAsignacion ::= IDEN := Exp0 
	 * Exp0.tsh = IAsignacion.tsh 
	 * IAsignacion.error = not asignacionCorrecta(IDEN.lex,IAsignacion.tsh,Exp0.tipo)
	 * Exp0.etqh = IAsignacion.etqh 
	 * IAsignacion.etq = Exp0.etq + 1 
	 * IAsignacion.cod = Exp0.cod || desapila_dir(dirDe(IDEN.lex,IAsignacion.tsh))
	 * </code>
	 */
	public class IAsigR1 extends Inst {

		public IAsigR1(Token iden, Exp0 exp0) {
			super();
			this.iden = iden;
			this.exp0 = exp0;
			exp0.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return IAsigR1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return IAsigR1.this.etqh().val();
				}
			});
			err().ponDependencias(tsh(),exp0.tipo());
			etq().ponDependencias(exp0.etq());
			cod().ponDependencias(exp0.cod(), tsh());
			exp0.tsh().ponDependencias(tsh());
			exp0.etqh().ponDependencias(etqh());

		}

		public Integer etq_exp() {
			return exp0.etq().val() + 1;
		}

		public Error err_exp() {
			if (!asignacionCorrecta(iden.leeLexema(), tsh().val(), exp0.tipo()
					.val())) {
				return new Error("La asignación no ha sido correcta");
			} else
				return new Error();
		}

		public List<Instruccion> cod_exp() {
			return concat(exp0.cod().val(), desapila(String.valueOf(dirDe(
					iden.leeLexema(), tsh().val()))));

		}

		private Token iden;
		private Exp0 exp0;

	}

	public class IAsigR1Debug extends IAsigR1 {
		private final static String REGLA = "IAsignacion ::= IDEN := Exp0";

		public IAsigR1Debug(Token iden, Exp0 exp0) {
			super(iden, exp0);
			err().fijaDescripcion(REGLA + " | IAsignacion.err");
			etq().fijaDescripcion(REGLA + " | IAsignacion.etq");
			cod().fijaDescripcion(REGLA + " | IAsignacion.cod");
			exp0.tsh().fijaDescripcion(REGLA + " | Exp0.tsh");
			exp0.etqh().fijaDescripcion(REGLA + " | Exp0.etqh");
		}
	}

	/**
	 * <code>
	 * IIF ::= if Casos fi 
	 * Casos.tsh = IIF.tsh 
	 * IIF.error = Casos.error
	 * Casos.etqh = IIF.etqh 
	 * Casos.irh = Casos.etq 
	 * IIF.etq = Casos.etq 
	 * IIF.cod =Casos.cod
	 * </code>
	 */
	public class IIFR1 extends Inst {

		public IIFR1(Casos casos) {
			super();
			this.casos = casos;
			casos.registraCtx(new CasosCtx() {
				public TS tsh_exp() {
					return IIFR1.this.tsh().val();
				}

				public int etqh_exp() {
					return IIFR1.this.etqh().val();
				}

				public int irh_exp() {
					return IIFR1.this.casos.etq().val();
				}
			});
			err().ponDependencias(casos.err());
			etq().ponDependencias(casos.etq());
			cod().ponDependencias(casos.cod());
			casos.tsh().ponDependencias(tsh());
			casos.etqh().ponDependencias(etqh());
			casos.irh().ponDependencias(casos.etq());
		}

		public Integer etq_exp() {
			return casos.etq().val();
		}

		public Error err_exp() {

			return casos.err().val();

		}

		public List<Instruccion> cod_exp() {
			return casos.cod().val();
		}

		private Casos casos;
	}

	public class IIFR1Debug extends IIFR1 {
		private final static String REGLA = "IIF ::= if Casos fi";

		public IIFR1Debug(Casos casos) {
			super(casos);
			err().fijaDescripcion(REGLA + " | IIF.err");
			etq().fijaDescripcion(REGLA + " | IIF.etq");
			cod().fijaDescripcion(REGLA + " | IIF.cod");
			casos.tsh().fijaDescripcion(REGLA + " | Casos.tsh");
			casos.etqh().fijaDescripcion(REGLA + " | Casos.etqh");
			casos.irh().fijaDescripcion(REGLA + " | Casos.irh");
		}
	}

	/**
	 * <code>
	 * IDO ::= do Casos od
	 * 
	 * Casos.tsh = IDO.tsh 
	 * IDO.error = Casos.error 
	 * Casos.etqh = IDO.etqh
	 * Casos.irh = IDO.etqh 
	 * IDO.etq = Casos.etq 
	 * IDO.cod = Casos.cod
	 * </code>
	 */
	public class IDOR1 extends Inst {

		public IDOR1(Casos casos) {
			super();
			this.casos = casos;

			casos.registraCtx(new CasosCtx() {
				public TS tsh_exp() {
					return IDOR1.this.tsh().val();
				}

				public int etqh_exp() {
					return IDOR1.this.etqh().val();
				}

				public int irh_exp() {
					return IDOR1.this.etqh().val();
				}
			});
			err().ponDependencias(casos.err());
			etq().ponDependencias(casos.etq());
			cod().ponDependencias(casos.cod());
			casos.tsh().ponDependencias(tsh());
			casos.etqh().ponDependencias(etqh());
			casos.irh().ponDependencias(etqh());
		}

		public Integer etq_exp() {

			return casos.etq().val();
		}

		public Error err_exp() {
			return casos.err().val();

		}

		public List<Instruccion> cod_exp() {

			return casos.cod().val();

		}

		private Casos casos;
	}

	public class IDOR1Debug extends IDOR1 {
		private final static String REGLA = "IDO ::= do Casos od";

		public IDOR1Debug(Casos casos) {
			super(casos);
			err().fijaDescripcion(REGLA + " | IDO.err");
			etq().fijaDescripcion(REGLA + " | IDO.etq");
			cod().fijaDescripcion(REGLA + " | IDO.cod");
			casos.tsh().fijaDescripcion(REGLA + " | Casos.tsh");
			casos.etqh().fijaDescripcion(REGLA + " | Casos.etqh");
			casos.irh().fijaDescripcion(REGLA + " | Casos.irh");
		}
	}

	/**
	 * <code>
	 * Casos ::= Casos [] Caso 
	 * Casos(1).tsh = Casos(0).tsh 
	 * Caso.tsh=Casos(0).tsh 
	 * Casos(0).error = Casos(1).error or Caso.error 
	 * Casos(1).etqh = Casos(0).etqh 
	 * Caso.etqh = Casos(1).etq
	 * Casos(0).etq = Caso.etq
	 * Casos(1).irh = Casos(0).irh 
	 * Caso.irh = Casos(0).irh 
	 * Casos(0).cod =Casos(1).cod || Caso.cod
	 * </code>
	 */
	public class CasosR1 extends Casos {

		public CasosR1(Casos casos1, Caso caso) {
			super();
			this.casos1 = casos1;
			this.caso = caso;

			casos1.registraCtx(new CasosCtx() {
				public TS tsh_exp() {
					return CasosR1.this.tsh().val();
				}

				public int etqh_exp() {
					return CasosR1.this.etqh().val();
				}

				public int irh_exp() {
					return CasosR1.this.irh().val();
				}

			});
			caso.registraCtx(new CasoCtx() {
				public TS tsh_exp() {
					return CasosR1.this.tsh().val();
				}

				public int etqh_exp() {
					return CasosR1.this.casos1.etq().val();
				}

				public int irh_exp() {
					return CasosR1.this.irh().val();
				}
			});
			caso.tsh().ponDependencias(tsh());
			caso.etqh().ponDependencias(casos1.etq());
			caso.irh().ponDependencias(irh());
			casos1.tsh().ponDependencias(tsh());
			casos1.etqh().ponDependencias(etqh());
			casos1.irh().ponDependencias(irh());
			err().ponDependencias(casos1.err(), caso.err());
			cod().ponDependencias(casos1.cod(), caso.cod());
			etq().ponDependencias(caso.etq());
		}

		public Integer etq_exp() {
			return caso.etq().val();

		}

		public Error err_exp() {
			return joinErrors(casos1.err().val(), caso.err().val());
		}

		public List<Instruccion> cod_exp() {
			return concat(casos1.cod().val(), caso.cod().val());

		}

		private Casos casos1;
		private Caso caso;
	}

	public class CasosR1Debug extends CasosR1 {
		private final static String REGLA = "Casos(0)::= Casos(1) [] Caso";

		public CasosR1Debug(Casos casos_1, Caso caso) {
			super(casos_1, caso);
			caso.tsh().fijaDescripcion(REGLA + " | Caso.tsh");
			caso.etqh().fijaDescripcion(REGLA + " | Caso.etqh");
			caso.irh().fijaDescripcion(REGLA + " | Caso.irh");
			casos_1.tsh().fijaDescripcion(REGLA + " | Casos1.tsh");
			casos_1.etqh().fijaDescripcion(REGLA + " | Casos1.etqh");
			casos_1.irh().fijaDescripcion(REGLA + " | Casos1.irh");
			err().fijaDescripcion(REGLA + " | Casos0.err");
			cod().fijaDescripcion(REGLA + " | Casos0.cod");
			etq().fijaDescripcion(REGLA + " | Casos0.etq");
		}
	}

	/**
	 * <code>
	 * Casos ::= Caso
	 * 
	 * Propagaci—n de la tabla de s’mbolos 
	 * Caso.tsh = Casos.tsh 
	 * Comprobaci—n de las restricciones contextuales 
	 * Casos.error = Caso.error 
	 * Generaci—n de c—digo
	 * Caso.etqh = Casos.etqh 
	 * Casos.etq = Caso.etq 
	 * Caso.irh = Casos.irh
	 * Casos.cod = Caso.cod
	 * </code>
	 */
	public class CasosR2 extends Casos {

		public CasosR2(Caso caso) {
			super();
			this.caso = caso;

			caso.registraCtx(new CasoCtx() {
				public TS tsh_exp() {
					return CasosR2.this.tsh().val();
				}

				public int etqh_exp() {
					return CasosR2.this.etqh().val();
				}

				public int irh_exp() {
					return CasosR2.this.irh().val();
				}
			});
			caso.tsh().ponDependencias(tsh());
			caso.etqh().ponDependencias(etqh());
			caso.irh().ponDependencias(irh());
			err().ponDependencias(caso.err());
			cod().ponDependencias(caso.cod());
			etq().ponDependencias(caso.etq());
		}

		public Integer etq_exp() {
			return caso.etq().val();
		}

		public Error err_exp() {
			return caso.err().val();
		}

		public List<Instruccion> cod_exp() {
			return caso.cod().val();

		}

		private Caso caso;

	}

	public class CasosR2Debug extends CasosR2 {
		private final static String REGLA = "Casos ::= Caso | Caso";

		public CasosR2Debug(Caso caso) {
			super(caso);
			caso.tsh().fijaDescripcion(REGLA + " | Caso.tsh");
			caso.etqh().fijaDescripcion(REGLA + " | Caso.etqh");
			caso.irh().fijaDescripcion(REGLA + " | Caso.irh");
			err().fijaDescripcion(REGLA + " | Casos.err");
			cod().fijaDescripcion(REGLA + " | Casos.cod");
			etq().fijaDescripcion(REGLA + " | Casos.etq");
		}
	}

	/**
	 * <code>
	 * Caso ::= case Exp0 -> Instrucciones
	 * 
	 * Propagaci—n de la tabla de s’mbolos 
	 * Exp0.tsh = Caso.tsh
	 * Instrucciones.tsh = Caso.tsh 
	 * Comprobaci—n de las restricciones contextuales 
	 * Caso.error = Instrucciones.error or not (Exp0.tipo = boolean)
	 * Generaci—n de c—digo 
	 * Exp0.etqh = Caso.etqh 
	 * Instrucciones.etqh =Exp0.etq + 1 
	 * Caso.etq = Instrucciones.etq + 1 
	 * Caso.cod = Exp0.cod || ir_f(Instrucciones.etq + 1) || Instrucciones.cod || ir_a(Caso.irh)
	 * </code>
	 */
	public class CasoR1 extends Caso {

		public CasoR1(Exp0 exp0, Insts insts) {
			super();
			this.exp0 = exp0;
			this.insts = insts;

			exp0.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return CasoR1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return CasoR1.this.etqh().val();
				}
			});
			insts.registraCtx(new InstsCtx() {
				public TS tsh_exp() {
					return CasoR1.this.tsh().val();
				}

				public int etqh_exp() {
					return CasoR1.this.exp0.etq().val() + 1;
				}
			});
			
			insts.tsh().ponDependencias(tsh());
			exp0.tsh().ponDependencias(tsh());
			exp0.etqh().ponDependencias(etqh());
			insts.etqh().ponDependencias(exp0.etq());
			etq().ponDependencias(insts.etq());
			err().ponDependencias(insts.err(), exp0.tipo());
			cod().ponDependencias(exp0.cod(), insts.etq(), insts.cod(), irh());

		}

		public Integer etq_exp() {
			return insts.etq().val() + 1;
		}

		public Error err_exp() {
			if (exp0.tipo().val() != CatLexica.BOOLEAN) {
				Error errExp0 = new Error("Exp0 no es un booleano");
				return joinErrors(insts.err().val(), errExp0);
			} else {
				return insts.err().val();
			}
		}

		public List<Instruccion> cod_exp() {
			List<Instruccion> c = new LinkedList<Instruccion>();
			c = concat(exp0.cod().val(), ir_f(insts.etq().val() + 1));
			c = concat(c, insts.cod().val());
			c = concat(c, ir_a(irh().val()));
			return c;
		}

		private Exp0 exp0;
		private Insts insts;
	}

	public class CasoR1Debug extends CasoR1 {
		private final static String REGLA = "Caso ::= case Exp0 -> Instrucciones";

		public CasoR1Debug(Exp0 exp0, Insts insts) {
			super(exp0, insts);
			insts.etqh().fijaDescripcion(REGLA + " | insts.etqh");
			insts.tsh().fijaDescripcion(REGLA + " | insts.tsh");
			exp0.etqh().fijaDescripcion(REGLA + " | exp0.etqh");
			exp0.tsh().fijaDescripcion(REGLA + " | exp0.tsh");
			etq().fijaDescripcion(REGLA + " | insts.etq");
			err().fijaDescripcion(REGLA + " | insts.err");
			cod().fijaDescripcion(REGLA + " | insts.cod");
		}

	}

	/**
	 * <code>
	 * 
	 * Exp0 ::= Exp1 OpComparacion Exp1
	 * 
	 * Exp1(0).tsh = Exp0.tsh 
	 * Exp1(1).tsh = Exp0.tsh 
	 * Exp0.tipo =tipoOpBin(OpComparacion.op,Exp1(0).tipo,Exp1(1).tipo) 
	 * Exp1(0).etqh =Exp0.etqh 
	 * Exp1(1).etqh = Exp1(0).etq 
	 * Exp0.etq = Exp1(1).etq + 1 
	 * Exp0.cod= Exp1(0).cod || Exp1(1).cod || OpComparacion.cod
	 * </code>
	 */
	public class Exp0R1 extends Exp0 {

		public Exp0R1(Exp1 exp1_0, Exp1 exp1_1, OpComparacion opc) {
			super();
			this.exp1_0 = exp1_0;
			this.exp1_1 = exp1_1;
			this.opc = opc;

			exp1_0.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp0R1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp0R1.this.etqh().val();
				}
			});
			exp1_1.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp0R1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp0R1.this.exp1_0.etq().val();
				}
			});
			exp1_0.tsh().ponDependencias(tsh());
			exp1_1.tsh().ponDependencias(tsh());
			exp1_0.etqh().ponDependencias(etqh());
			exp1_1.etqh().ponDependencias(exp1_0.etq());
			cod().ponDependencias(exp1_0.cod(), exp1_1.cod(), opc.cod());
			etq().ponDependencias(exp1_1.etq());
			tipo().ponDependencias(opc.op(), exp1_0.tipo(), exp1_1.tipo());

		}

		public List<Instruccion> cod_exp() {
			return concat(exp1_0.cod().val(),
					concat(exp1_1.cod().val(), opc.cod().val()));

		}

		public Integer etq_exp() {
			return exp1_1.etq().val() + 1;

		}

		public CatLexica tipo_exp() {
			return tipoOpBin(opc.op().val(), exp1_0.tipo().val(), exp1_1.tipo()
					.val());
		}

		private Exp1 exp1_0;
		private Exp1 exp1_1;
		private OpComparacion opc;

	}

	public class Exp0R1Debug extends Exp0R1 {
		private final static String REGLA = "Exp0 ::= Exp1(0) OpComparacion Exp1(1)";

		public Exp0R1Debug(Exp1 exp1_0, Exp1 exp1_1, OpComparacion op) {
			super(exp1_0, exp1_1, op);
			exp1_0.tsh().fijaDescripcion(REGLA + " | exp1_0.tsh");
			exp1_0.etqh().fijaDescripcion(REGLA + " | exp1_0.etqh");
			exp1_1.etqh().fijaDescripcion(REGLA + " | exp1_1.etqh");
			exp1_1.tsh().fijaDescripcion(REGLA + " | exp1_1.tsh");
			cod().fijaDescripcion(REGLA + " | exp0.cod");
			etq().fijaDescripcion(REGLA + " | exp0.etq");
			tipo().fijaDescripcion(REGLA + " | exp0.tipo");
		}
	}

	/**
	 * <code>
	 * Exp0 ::= Exp1
	 * 
	 * Propagaci—n de la tabla de s’mbolos 
	 * Exp1.tsh = Exp0.tsh 
	 * Comprobaci—n de las restricciones contextuales
	 * Exp0.tipo = Exp1.tipo 
	 * Generaci—n de c—digo
	 * Exp1.etqh = Exp0.etqh 
	 * Exp0.etq = Exp1.etq 
	 * Exp0.cod = Exp1.cod
	 * </code>
	 */
	public class Exp0R2 extends Exp0 {

		public Exp0R2(Exp1 exp1) {
			super();
			this.exp1 = exp1;

			exp1.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp0R2.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp0R2.this.etqh().val();
				}
			});
			exp1.tsh().ponDependencias(tsh());
			exp1.etqh().ponDependencias(etqh());
			cod().ponDependencias(exp1.cod());
			etq().ponDependencias(exp1.etq());
			tipo().ponDependencias(exp1.tipo());
		}

		public List<Instruccion> cod_exp() {
			return exp1.cod().val();
		}

		public Integer etq_exp() {
			return exp1.etq().val();
		}

		public CatLexica tipo_exp() {
			return exp1.tipo().val();
		}

		private Exp1 exp1;
	}

	public class Exp0R2Debug extends Exp0R2 {
		private final static String REGLA = "Exp0 ::= Exp1";

		public Exp0R2Debug(Exp1 exp1) {
			super(exp1);
			exp1.tsh().fijaDescripcion(REGLA + " | exp1.tsh");
			exp1.etqh().fijaDescripcion(REGLA + " | exp1.etqh");
			cod().fijaDescripcion(REGLA + " | exp0.cod");
			etq().fijaDescripcion(REGLA + " | exp0.etq");
			tipo().fijaDescripcion(REGLA + " | exp0.tipo");
		}

	}

	/**
	 * <code>
	 * Exp1 ::= Exp1 OpAditivo Exp2
	 * Exp1(1).tsh = Exp1(0).tsh 
	 * Exp2.tsh = Exp1(0).tsh 
	 * Exp1(0).tipo = tipoOpBin(OpAditivo.op,Exp1(1).tipo,Exp2.tipo)
	 * Exp1(1).etqh = Exp1(0).etqh 
	 * Exp2.etqh = Exp1(1).etq 
	 * Exp1(0).etq = Exp2.etq + 1 
	 * Exp1(0).cod = Exp1(1).cod || Exp2.cod || OpAditivo.cod
	 * </code>
	 */
	public class Exp1R1 extends Exp1 {

		public Exp1R1(Exp1 exp1, Exp2 exp2, OpAditivo opa) {
			super();
			this.exp1 = exp1;
			this.exp2 = exp2;
			this.opa = opa;

			exp1.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp1R1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp1R1.this.etqh().val();
				}

			});
			exp2.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp1R1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp1R1.this.exp1.etq().val();
				}
			});
			exp1.tsh().ponDependencias(tsh());
			exp2.tsh().ponDependencias(tsh());
			exp1.etqh().ponDependencias(etqh());
			exp2.etqh().ponDependencias(exp1.etq());
			cod().ponDependencias(exp1.cod(), exp2.cod(), opa.cod());
			etq().ponDependencias(exp2.etq());
			tipo().ponDependencias(opa.op(), exp1.tipo(), exp2.tipo());

		}

		public List<Instruccion> cod_exp() {
			return concat(exp1.cod().val(),
					concat(exp2.cod().val(), opa.cod().val()));

		}

		public Integer etq_exp() {
			return exp2.etq().val() + 1;
		}

		public CatLexica tipo_exp() {
			return tipoOpBin(opa.op().val(), exp1.tipo().val(), exp2.tipo()
					.val());
		}

		private Exp1 exp1;
		private Exp2 exp2;
		private OpAditivo opa;

	}

	public class Exp1R1Debug extends Exp1R1 {
		private final static String REGLA = "Exp1(0) ::= Exp1(1) OpAditivo Exp2";

		public Exp1R1Debug(Exp1 exp1_1, Exp2 exp2, OpAditivo opAdit) {
			super(exp1_1, exp2, opAdit);
			exp1_1.tsh().fijaDescripcion(REGLA + " | exp1_1.tsh");
			exp1_1.etqh().fijaDescripcion(REGLA + " | exp1_1.etqh");
			exp2.etqh().fijaDescripcion(REGLA + " | exp2.etqh");
			exp2.tsh().fijaDescripcion(REGLA + " | exp2.tsh");
			cod().fijaDescripcion(REGLA + " | exp1_0.cod");
			etq().fijaDescripcion(REGLA + " | exp1_0.etq");
			tipo().fijaDescripcion(REGLA + " | exp1_0.tipo");
		}

	}

	/**
	 * <code>
	 * Exp1 ::= Exp2
	 * 
	 * Exp2.tsh = Exp1.tsh 
	 * Exp1.tipo = Exp2.tipo 
	 * Exp2.etqh = Exp1.etqh 
	 * Exp1.etq = Exp2.etq 
	 * Exp1.cod = Exp2.cod
	 * </code>
	 */
	public class Exp1R2 extends Exp1 {

		public Exp1R2(Exp2 exp2) {
			super();
			this.exp2 = exp2;

			exp2.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp1R2.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp1R2.this.etqh().val();
				}
			});
			exp2.tsh().ponDependencias(tsh());
			exp2.etqh().ponDependencias(etqh());
			cod().ponDependencias(exp2.cod());
			etq().ponDependencias(exp2.etq());
			tipo().ponDependencias(exp2.tipo());
		}

		public List<Instruccion> cod_exp() {

			return exp2.cod().val();
		}

		public Integer etq_exp() {

			return exp2.etq().val();

		}

		public CatLexica tipo_exp() {
			return exp2.tipo().val();
		}

		private Exp2 exp2;
	}

	public class Exp1R2Debug extends Exp1R2 {
		private final static String REGLA = "Exp1 ::= Exp2";

		public Exp1R2Debug(Exp2 exp2) {
			super(exp2);
			exp2.tsh().fijaDescripcion(REGLA + " | exp2.tsh");
			exp2.etqh().fijaDescripcion(REGLA + " | exp2.etqh");
			cod().fijaDescripcion(REGLA + " | exp1.cod");
			etq().fijaDescripcion(REGLA + " | exp1.etq");
			tipo().fijaDescripcion(REGLA + " | exp1.tipo");
		}
	}

	/**
	 * <code>
	 * Exp2 ::= Exp2 OpMultiplicativo Exp3 
	 * Exp2(1).tsh = Exp2(0).tsh 
	 * Exp3.tsh = Exp2(0).tsh 
	 * Exp2(0).tipo = tipoOpBin(OpMultiplicativo.op,Exp2(1).tipo,Exp3.tipo)
	 * Exp2(1).etqh = Exp2(0).etqh 
	 * Exp3.etqh = Exp2(1).etq 
	 * Exp2(0).etq = Exp3.etq + 1
	 * Exp2(0).cod = Exp2(1).cod || Exp3.cod || OpMultiplicativo.cod
	 * </code>
	 */
	public class Exp2R1 extends Exp2 {

		public Exp2R1(Exp2 exp2, Exp3 exp3, OpMultiplicativo opm) {
			super();
			this.exp2 = exp2;
			this.exp3 = exp3;
			this.opm = opm;

			exp2.etqh().ponDependencias(etqh());
			exp2.tsh().ponDependencias(tsh());
			exp3.tsh().ponDependencias(tsh());
			exp3.etqh().ponDependencias(exp2.etq());
			cod().ponDependencias(exp2.cod(), exp3.cod(), opm.cod());
			etq().ponDependencias(exp3.etq());
			tipo().ponDependencias(opm.op(), exp2.tipo(), exp3.tipo());

			exp2.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp2R1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp2R1.this.etqh().val();
				}
			});
			exp3.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp2R1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp2R1.this.exp2.etq().val();
				}

			});
		}

		public List<Instruccion> cod_exp() {

			return concat(exp2.cod().val(),
					concat(exp3.cod().val(), opm.cod().val()));

		}

		public Integer etq_exp() {
			return exp3.etq().val() + 1;

		}

		public CatLexica tipo_exp() {
			return tipoOpBin(opm.op().val(), exp2.tipo().val(), exp3.tipo()
					.val());
		}

		private Exp2 exp2;
		private Exp3 exp3;
		private OpMultiplicativo opm;
	}

	public class Exp2R1Debug extends Exp2R1 {
		private final static String REGLA = "Exp2(0) ::= Exp2(1) OpMultiplicativo Exp3";

		public Exp2R1Debug(Exp2 exp2_1, Exp3 exp3, OpMultiplicativo opMult) {
			super(exp2_1, exp3, opMult);
			exp2_1.tsh().fijaDescripcion(REGLA + " | exp2_1.tsh");
			exp2_1.etqh().fijaDescripcion(REGLA + " | exp2_1.etqh");
			exp3.etqh().fijaDescripcion(REGLA + " | exp3.etqh");
			exp3.tsh().fijaDescripcion(REGLA + " | exp3.tsh");
			cod().fijaDescripcion(REGLA + " | exp2_0.cod");
			etq().fijaDescripcion(REGLA + " | exp2_0.etq");
			tipo().fijaDescripcion(REGLA + " | exp12_0.tipo");
		}
	}

	/**
	 * <code>
	 * Exp2 ::= Exp3
	 * 
	 * Propagaci—n de la tabla de s’mbolos 
	 * Exp3.tsh = Exp2.tsh 
	 * Comprobaci—n de las restricciones contextuales 
	 * Exp2.tipo = Exp3.tipo
	 * Generación de código
	 * Exp3.etqh = Exp2.etqh
	 * Exp2.etq = Exp3.etq
	 * Exp2.cod = Exp3.cod
	 * </code>
	 */
	public class Exp2R2 extends Exp2 {

		public Exp2R2(Exp3 exp3) {
			super();
			this.exp3 = exp3;

			exp3.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp2R2.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp2R2.this.etqh().val();
				}

			});
			exp3.tsh().ponDependencias(tsh());
			exp3.etqh().ponDependencias(etqh());
			cod().ponDependencias(exp3.cod());
			etq().ponDependencias(exp3.etq());
			tipo().ponDependencias(exp3.tipo());
		}

		public List<Instruccion> cod_exp() {
			return exp3.cod().val();
		}

		public Integer etq_exp() {
			return exp3.etq().val();
		}

		public CatLexica tipo_exp() {
			return exp3.tipo().val();
		}

		private Exp3 exp3;

	}

	public class Exp2R2Debug extends Exp2R2 {
		private final static String REGLA = "Exp2 ::= Exp3";

		public Exp2R2Debug(Exp3 exp3) {
			super(exp3);
			exp3.tsh().fijaDescripcion(REGLA + " | exp3.tsh");
			exp3.etqh().fijaDescripcion(REGLA + " | exp3.etqh");
			cod().fijaDescripcion(REGLA + " | exp2.cod");
			etq().fijaDescripcion(REGLA + " | exp2.etq");
			tipo().fijaDescripcion(REGLA + " | exp2.tipo");
		}
	}

	/**
	 * <code>
	 * Exp3 ::= OpUnario Exp3
	 * 
	 * Exp3(1).tsh = Exp3(0).tsh 
	 * Exp3(0).tipo = tipoOpUn(OpUnario.op,Exp3(1).tipo)
	 * Exp3(1).etqh = Exp3(0).etqh
	 * Exp3(0).etq = Exp3(1).etq + 1 
	 * Exp3(0).cod = Exp3(1).cod || OpUnario.cod
	 * </code>
	 */
	public class Exp3R1 extends Exp3 {

		public Exp3R1(Exp3 exp3_1, OpUnario opu) {
			super();
			this.exp3_1 = exp3_1;
			this.opu = opu;

			exp3_1.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp3R1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp3R1.this.etqh().val();
				}
			});
			exp3_1.tsh().ponDependencias(tsh());
			exp3_1.etqh().ponDependencias(etqh());
			cod().ponDependencias(exp3_1.cod(), opu.cod());
			etq().ponDependencias(exp3_1.etq());
			tipo().ponDependencias(opu.op(), exp3_1.tipo());
		}

		public List<Instruccion> cod_exp() {

			return concat(exp3_1.cod().val(), opu.cod().val());

		}

		public Integer etq_exp() {
			return exp3_1.etq().val() + 1;
		}

		public CatLexica tipo_exp() {
			return tipoOpUnario(opu.op().val(), exp3_1.tipo().val());
		}

		private Exp3 exp3_1;
		private OpUnario opu;

	}

	public class Exp3R1Debug extends Exp3R1 {
		private final static String REGLA = "Exp3(0) ::= OpUnaria Exp3(1)";

		public Exp3R1Debug(Exp3 exp3_1, OpUnario opUnar) {
			super(exp3_1, opUnar);
			exp3_1.tsh().fijaDescripcion(REGLA + " | exp3_1.tsh");
			exp3_1.etqh().fijaDescripcion(REGLA + " | exp3_1.etqh");
			cod().fijaDescripcion(REGLA + " | exp3.cod");
			etq().fijaDescripcion(REGLA + " | exp3.etq");
			tipo().fijaDescripcion(REGLA + " | exp3.tipo");
		}
	}

	/**
	 * <code>
	 * Exp3 ::= Exp4
	 * 
	 * Exp4.tsh = Exp3.tsh 
	 * Exp3.tipo = Exp4.tipo 
	 * Exp4.etqh = Exp3.etqh 
	 * Exp3.etq = Exp4.etq 
	 * Exp3.cod = Exp4.cod
	 * </code>
	 */
	public class Exp3R2 extends Exp3 {

		public Exp3R2(Exp4 exp4) {
			super();
			this.exp4 = exp4;

			exp4.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp3R2.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp3R2.this.etqh().val();
				}
			});
			exp4.tsh().ponDependencias(tsh());
			exp4.etqh().ponDependencias(etqh());
			cod().ponDependencias(exp4.cod());
			etq().ponDependencias(exp4.etq());
			tipo().ponDependencias(exp4.tipo());
		}

		public List<Instruccion> cod_exp() {
			return exp4.cod().val();

		}

		public Integer etq_exp() {
			return exp4.etq().val();
		}

		public CatLexica tipo_exp() {
			return exp4.tipo().val();
		}

		private Exp4 exp4;
	}

	public class Exp3R2Debug extends Exp3R2 {
		private final static String REGLA = "Exp3 ::= Exp4";

		public Exp3R2Debug(Exp4 exp4) {
			super(exp4);
			exp4.tsh().fijaDescripcion(REGLA + " | exp4.tsh");
			exp4.etqh().fijaDescripcion(REGLA + " | exp4.etqh");
			cod().fijaDescripcion(REGLA + " | exp3.cod");
			etq().fijaDescripcion(REGLA + " | exp3.etq");
			tipo().fijaDescripcion(REGLA + " | exp3.tipo");
		}

	}

	/**
	 * <code>
	 * Exp4 ::= true
	 * 
	 * Exp4.tipo = boolean 
	 * Exp4.etq = Exp4.etqh + 1 
	 * Exp4.cod = apila_true()
	 * </code>
	 */
	public class Exp4R1 extends Exp4 {

		public Exp4R1() {
			super();
			etq().ponDependencias(etqh());
		}

		public List<Instruccion> cod_exp() {
			return single(apila_true());

		}

		public Integer etq_exp() {
			return etqh().val() + 1;

		}

		public CatLexica tipo_exp() {
			return CatLexica.BOOLEAN;
		}

	}

	public class Exp4R1Debug extends Exp4R1 {
		private final static String REGLA = "Exp4 ::= true";

		public Exp4R1Debug() {
			super();
			cod().fijaDescripcion(REGLA + " | exp4.cod");
			etq().fijaDescripcion(REGLA + " | exp4.etq");
			tipo().fijaDescripcion(REGLA + " | exp4.tipo");
		}

	}

	/**
	 * <code>
	 * Exp4 ::= false 
	 * Exp4.tipo = boolean 
	 * Exp4.etq = Exp4.etqh + 1 
	 * Exp4.cod =apila_false()
	 * </code>
	 */
	public class Exp4R2 extends Exp4 {

		public Exp4R2() {
			super();
			etq().ponDependencias(etqh());
		}

		public List<Instruccion> cod_exp() {
			return single(apila_false());
		}

		public Integer etq_exp() {
			return this.etqh().val() + 1;
		}

		public CatLexica tipo_exp() {
			return CatLexica.BOOLEAN;
		}

	}

	public class Exp4R2Debug extends Exp4R2 {
		private final static String REGLA = "Exp4 := false";

		public Exp4R2Debug() {
			super();
			cod().fijaDescripcion(REGLA + " | exp4.cod");
			etq().fijaDescripcion(REGLA + " | exp4.etq");
			tipo().fijaDescripcion(REGLA + " | exp4.tipo");
		}
	}

	/**
	 * <code>
	 * Exp4 ::= NUM 
	 * Exp4.tipo = int 
	 * Exp4.etq = Exp4.etqh + 1 
	 * Exp4.cod =apila_int(NUM.lex)
	 * </code>
	 */
	public class Exp4R3 extends Exp4 {

		public Exp4R3(Token num) {
			super();
			this.num = num;
			etq().ponDependencias(etqh());
		}

		public List<Instruccion> cod_exp() {
			return single(apila_int(num.leeLexema()));
		}

		public Integer etq_exp() {
			return etqh().val() + 1;
		}

		public CatLexica tipo_exp() {
			return CatLexica.INT;
		}

		private Token num;
	}

	public class Exp4R3Debug extends Exp4R3 {
		private final static String REGLA = "Exp4 ::= NUM";

		public Exp4R3Debug(Token num) {
			super(num);
			cod().fijaDescripcion(REGLA + " | exp4.cod");
			etq().fijaDescripcion(REGLA + " | exp4.etq");
			tipo().fijaDescripcion(REGLA + " | exp4.tipo");
		}

	}

	/**
	 * <code>
	 * Exp4 ::= IDEN 
	 * Exp4.tipo = tipoDe(IDEN.lex,Exp4.tsh) 
	 * Exp4.etq = Exp4.etqh+ 1 
	 * Exp4.cod = apila_dir(dirDe(IDEN.lex,Exp4.tsh))
	 * </code>
	 */
	public class Exp4R4 extends Exp4 {

		public Exp4R4(Token iden) {
			this.iden = iden;
			tipo().ponDependencias(tsh());
			etq().ponDependencias(etqh());
			cod().ponDependencias(tsh());
		}

		public List<Instruccion> cod_exp() {
			return single(apila_dir(String.valueOf(dirDe(iden.leeLexema(), this
					.tsh().val()))));
		}

		public Integer etq_exp() {
			return etqh().val() + 1;

		}

		public CatLexica tipo_exp() {
			return tipoDe(iden.leeLexema(), tsh().val());
		}

		private Token iden;

	}

	public class Exp4R4Debug extends Exp4R4 {
		private final static String REGLA = "Exp4 ::= IDEN";

		public Exp4R4Debug(Token iden) {
			super(iden);
			cod().fijaDescripcion(REGLA + " | exp4.cod");
			etq().fijaDescripcion(REGLA + " | exp4.etq");
			tipo().fijaDescripcion(REGLA + " | exp4.tipo");
		}
	}

	/**
	 * <code>
	 * Exp4 ::= ( Exp0 )
	 * 
	 * Exp0.tsh = Exp4.tsh
	 * Exp4.tipo = Exp0.tipo 
	 * Exp0.etqh = Exp4.etqh 
	 * Exp4.etq = Exp0.etq 
	 * Exp4.cod = Exp0.cod
	 * </code>
	 */
	public class Exp4R5 extends Exp4 {

		public Exp4R5(Exp0 exp0) {
			super();
			this.exp0 = exp0;

			exp0.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return Exp4R5.this.tsh().val();
				}

				public Integer etqh_exp() {
					return Exp4R5.this.etqh().val();
				}
			});
			exp0.etqh().ponDependencias(etqh());
			exp0.tsh().ponDependencias(tsh());
			tipo().ponDependencias(exp0.tipo());
			etq().ponDependencias(exp0.etq());
			cod().ponDependencias(exp0.cod());
		}

		public List<Instruccion> cod_exp() {
			return exp0.cod().val();

		}

		public Integer etq_exp() {
			return exp0.etq().val();
		}

		public CatLexica tipo_exp() {
			return exp0.tipo().val();
		}

		private Exp0 exp0;
	}

	public class Exp4R5Debug extends Exp4R5 {
		private final static String REGLA = "Exp4 ::= ( Exp0 )";

		public Exp4R5Debug(Exp0 exp0) {
			super(exp0);
			exp0.etqh().fijaDescripcion(REGLA + " | exp0.etqh");
			exp0.tsh().fijaDescripcion(REGLA + " | exp0.tsh");
			tipo().fijaDescripcion(REGLA + " | exp4.tipo");
			etq().fijaDescripcion(REGLA + " | exp4.etq");
			cod().fijaDescripcion(REGLA + " | exp4.cod");
		}
	}

	/**
	 * <code>
	 * OpComparacion ::= eq 
	 * Comprobaci—n de las restricciones contextuales
	 * OpComparacion.op = eq 
	 * Generaci—n de c—digo 
	 * OpComparacion.cod = eq()
	 * </code>
	 */
	public class OpComparacionR1 extends OpComparacion {

		public OpComparacionR1() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.EQ;
		}

		public List<Instruccion> cod_exp() {
			return single(eq());
		}
	}

	public class OpComparacionR1Debug extends OpComparacionR1 {
		private final static String REGLA = "OpComparacion ::= eq";

		public OpComparacionR1Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpComparacion.op");
			cod().fijaDescripcion(REGLA + " | OpComparacion.cod");

		}
	}

	public class OpComparacionR2 extends OpComparacion {

		public OpComparacionR2() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.NEQ;
		}

		public List<Instruccion> cod_exp() {
			return single(neq());
		}
	}

	public class OpComparacionR2Debug extends OpComparacionR2 {

		private final static String REGLA = "OpComparacion ::= neq";

		public OpComparacionR2Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpComparacion.op");
			cod().fijaDescripcion(REGLA + " | OpComparacion.cod");
		}

	}

	public class OpComparacionR3 extends OpComparacion {

		public OpComparacionR3() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.GT;
		}

		public List<Instruccion> cod_exp() {
			return single(gt());

		}
	}

	public class OpComparacionR3Debug extends OpComparacionR3 {
		private final static String REGLA = "OpComparacion ::= gt";

		public OpComparacionR3Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpComparacion.op");
			cod().fijaDescripcion(REGLA + " | OpComparacion.cod");
		}
	}

	public class OpComparacionR4 extends OpComparacion {

		public OpComparacionR4() {

		}

		public CatLexica op_exp() {
			return CatLexica.GE;
		}

		public List<Instruccion> cod_exp() {
			return single(ge());
		}

	}

	public class OpComparacionR4Debug extends OpComparacionR4 {
		private final static String REGLA = "OpComparacion ::= ge";

		public OpComparacionR4Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpComparacion.op");
			cod().fijaDescripcion(REGLA + " | OpComparacion.cod");
		}
	}

	public class OpComparacionR5 extends OpComparacion {

		public OpComparacionR5() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.LT;
		}

		public List<Instruccion> cod_exp() {

			return single(lt());

		}

	}

	public class OpComparacionR5Debug extends OpComparacionR5 {
		private final static String REGLA = "OpComparacion ::= lt";

		public OpComparacionR5Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpComparacion.op");
			cod().fijaDescripcion(REGLA + " | OpComparacion.cod");
		}
	}

	public class OpComparacionR6 extends OpComparacion {

		public OpComparacionR6() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.LE;
		}

		public List<Instruccion> cod_exp() {
			return single(le());

		}
	}

	public class OpComparacionR6Debug extends OpComparacionR6 {
		private final static String REGLA = "OpComparacion ::= le";

		public OpComparacionR6Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpComparacion.op");
			cod().fijaDescripcion(REGLA + " | OpComparacion.cod");
		}
	}

	public class OpAditivoR1 extends OpAditivo {

		public OpAditivoR1() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.MAS;
		}

		public List<Instruccion> cod_exp() {
			return single(suma());
		}
	}

	public class OpAditivoR1Debug extends OpAditivoR1 {
		private final static String REGLA = "OpAditivo := +";

		public OpAditivoR1Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpAditivo.op");
			cod().fijaDescripcion(REGLA + " | OpAditivo.cod");
		}
	}

	public class OpAditivoR2 extends OpAditivo {

		public OpAditivoR2() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.MENOS;
		}

		public List<Instruccion> cod_exp() {
			return single(resta());
		}

	}

	public class OpAditivoR2Debug extends OpAditivoR2 {
		private final static String REGLA = "OpAditivo := -";

		public OpAditivoR2Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpAditivo.op");
			cod().fijaDescripcion(REGLA + " | OpAditivo.cod");
		}

	}

	public class OpAditivoR3 extends OpAditivo {

		public OpAditivoR3() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.OR;
		}

		public List<Instruccion> cod_exp() {
			return single(or());
		}

	}

	public class OpAditivoR3Debug extends OpAditivoR3 {
		private final static String REGLA = "OpAditivo := or";

		public OpAditivoR3Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpAditivo.op");
			cod().fijaDescripcion(REGLA + " | OpAditivo.cod");
		}
	}

	public class OpMultiplicativoR1 extends OpMultiplicativo {

		public OpMultiplicativoR1() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.ASTERISCO;
		}

		public List<Instruccion> cod_exp() {
			return single(mul());
		}
	}

	public class OpMultiplicativoR1Debug extends OpMultiplicativoR1 {
		private final static String REGLA = "OpMultiplicativo:= *";

		public OpMultiplicativoR1Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpMultiplicativo.op");
			cod().fijaDescripcion(REGLA + " | OpMultiplicativo.cod");
		}
	}

	public class OpMultiplicativoR2 extends OpMultiplicativo {

		public OpMultiplicativoR2() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.BARRA;
		}

		public List<Instruccion> cod_exp() {
			return single(div());
		}
	}

	public class OpMultiplicativoR2Debug extends OpMultiplicativoR2 {
		private final static String REGLA = "OpMultiplicativo:= /";

		public OpMultiplicativoR2Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpMultiplicativo.op");
			cod().fijaDescripcion(REGLA + " | OpMultiplicativo.cod");
		}
	}

	public class OpMultiplicativoR3 extends OpMultiplicativo {

		public OpMultiplicativoR3() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.AND;
		}

		public List<Instruccion> cod_exp() {
			return single(and());
		}

	}

	public class OpMultiplicativoR3Debug extends OpMultiplicativoR3 {
		private final static String REGLA = "OpMultiplicativo:= and";

		public OpMultiplicativoR3Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpMultiplicativo.op");
			cod().fijaDescripcion(REGLA + " | OpMultiplicativo.cod");
		}
	}

	public class OpUnarioR1 extends OpUnario {

		public OpUnarioR1() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.MENOS;
		}

		public List<Instruccion> cod_exp() {
			return single(menos());

		}

	}

	public class OpUnarioR1Debug extends OpUnarioR1 {
		private final static String REGLA = "OpUnario:= -";

		public OpUnarioR1Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpUnario.op");
			cod().fijaDescripcion(REGLA + " | OpUnario.cod");
		}
	}

	public class OpUnarioR2 extends OpUnario {

		public OpUnarioR2() {
			super();
		}

		public CatLexica op_exp() {
			return CatLexica.NOT;
		}

		public List<Instruccion> cod_exp() {
			return single(not());
		}

	}

	public class OpUnarioR2Debug extends OpUnarioR2 {
		private final static String REGLA = "OpUnario:= not";

		public OpUnarioR2Debug() {
			super();
			op().fijaDescripcion(REGLA + " | OpUnario.op");
			cod().fijaDescripcion(REGLA + " | OpUnario.cod");
		}
	}

	// FUNCIONES DE AYUDA

	public TS aniadeSimb(TS ts, String iden, CatLexica tipo, int dir) {
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(tipo);
		a.add(String.valueOf(dir));
		return ts.aniade(iden, a);
	}

	public boolean estaEn(String cte, TS ts) {
		return ts.estaEn(cte);
	}

	public CatLexica tipoDe(String cte, TS ts) {
		return (CatLexica) ts.valDe(cte).get(0);
	}

	public int dirDe(String lex, TS ts) {
		return Integer.valueOf((String) ts.valDe(lex).get(1));
	}

	public TS creaTS() {
		return new TS();
	}

	public List<Instruccion> concat(List<Instruccion> c1, List<Instruccion> c2) {
		List<Instruccion> result = new LinkedList<Instruccion>(c1);
		result.addAll(c2);
		return result;
	}

	public List<Instruccion> single(Instruccion i) {
		List<Instruccion> is = new LinkedList<Instruccion>();
		is.add(i);
		return is;
	}

	public List<Instruccion> concat(List<Instruccion> c1, Instruccion i) {
		List<Instruccion> result = new LinkedList<Instruccion>(c1);
		result.add(i);
		return result;
	}

	public CatLexica tipoOpBin(CatLexica op, CatLexica tipo2, CatLexica tipo3) {
		if (tipo2.equals(tipo3)) {
			if (op.equals(CatLexica.GT) || op.equals(CatLexica.EQ)
					|| op.equals(CatLexica.NEQ) || op.equals(CatLexica.GE)
					|| op.equals(CatLexica.LT) || op.equals(CatLexica.LE))
				return CatLexica.BOOLEAN;
			else if (op.equals(CatLexica.MAS) || op.equals(CatLexica.MENOS)
					|| op.equals(CatLexica.ASTERISCO)
					|| op.equals(CatLexica.BARRA))
				return CatLexica.INT;

		}
		return CatLexica.ERROR;
	}

	public CatLexica tipoOpUnario(CatLexica op, CatLexica tipo) {
		if (op.equals(CatLexica.MENOS) && tipo.equals(CatLexica.INT))
			return tipo;
		else if (op.equals(CatLexica.NOT) && tipo.equals(CatLexica.BOOLEAN))
			return tipo;
		else
			return CatLexica.ERROR;
	}

	public Instruccion suma() {
		return Instruccion.nuevaISuma();
	}

	public Instruccion mul() {
		return Instruccion.nuevaIMul();
	}

	public Instruccion resta() {
		return Instruccion.nuevaIResta();
	}

	public Instruccion not() {
		return Instruccion.nuevaINot();
	}

	public Instruccion menos() {
		return Instruccion.nuevaIMenos();
	}

	public Instruccion and() {
		return Instruccion.nuevaIAnd();
	}

	public Instruccion div() {
		return Instruccion.nuevaIDiv();
	}

	public Instruccion or() {
		return Instruccion.nuevaIOr();
	}

	public Instruccion le() {
		return Instruccion.nuevaILe();
	}

	public Instruccion lt() {
		return Instruccion.nuevaILt();
	}

	public Instruccion ge() {
		return Instruccion.nuevaIGe();
	}

	public Instruccion gt() {
		return Instruccion.nuevaIGt();
	}

	public Instruccion neq() {
		return Instruccion.nuevaINeq();
	}

	public Instruccion eq() {
		return Instruccion.nuevaIEq();
	}

	public Instruccion apila_dir(String arg) {
		return Instruccion.nuevaIApilaDir(arg);
	}

	public Instruccion apila(String arg) {
		return Instruccion.nuevaIApila(arg);
	}

	public static Instruccion desapila(String val) {
		return Instruccion.nuevaIDesapilaDir(val);
	}

	public Instruccion apila_int(String arg) {
		return Instruccion.nuevaIApila(arg);
	}

	public Instruccion apila_true() {
		return Instruccion.nuevaIApilaTrue();
	}

	public Instruccion apila_false() {
		return Instruccion.nuevaIApilaFalse();
	}

	public Instruccion ir_f(int ir) {
		return Instruccion.nuevaIIr_f(ir);
	}

	public Instruccion ir_a(int ir) {
		return Instruccion.nuevaIIr_a(ir);
	}

	public Error noError() {
		return new Error();
	}

	public Error joinErrors(Error e1, Error e2) {
		return new Error(e1, e2);
	}

	public Error errorSi(boolean condicion, int fila, int col, String msg) {
		if (condicion) {
			return new Error("(" + fila + "," + col + ") ERROR CONTEXTUAL: "
					+ msg);
		} else
			return new Error();
	}

	private boolean existeSimb(TS ts2, String iden) {
		return ts2.estaEn(iden);
	}

	private boolean asignacionCorrecta(String leeLexema, TS tsh, CatLexica tipo) {
		return (tipoDe(leeLexema, tsh).compareTo(tipo) == 0);
	}

//	private void requerido(String ctx) {
//		identado++;
//		identa();
//		System.out.println("REQUERIDO " + ctx);
//	}
//
//	private <O> O valor(String ctx, O val) {
//		identa();
//		System.out.println("VALOR DE " + ctx + ":" + val);
//		identado--;
//		return val;
//	}
//
//	private void identa() {
//		for (int i = 0; i < identado; i++)
//			System.out.print(".");
//	}
}
