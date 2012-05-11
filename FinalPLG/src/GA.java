import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GA {
	private int identado;

	public GA() {
		identado = -1;
	}

	public interface Programa {
		public Error err();

		public List<Instruccion> cod();

		public String regla();
	}

	public interface Decs {
		public Error err();

		public int dir();

		public TS ts();

		public String regla();
	}

	public interface Dec {
		public CatLexica tipo();

		public String iden();

		public int fila();

		public int col();

		public String regla();
	}

	public interface Tipo {
		public CatLexica tipo();

		public Error err();

		public String regla();
	}

	// Instrucciones
	abstract public class Insts {
		protected Insts() {
			hay_tsh = false;
			hay_etqh = false;
		}

		abstract public Error err();

		abstract public List<Instruccion> cod();

		abstract public String regla();

		abstract public int etq();

		public TS tsh() {
			if (!hay_tsh) {
				tsh = ctx.tsh();
				hay_tsh = true;
			}
			return tsh;
		}

		public int etqh() {
			if (!hay_etqh) {
				etqh = ctx.etqh();
				hay_etqh = true;
			}
			return etqh;
		}

		public void registraCtx(InstsCtx ctx) {
			this.ctx = ctx;
		}

		public InstsCtx contexto() {
			return ctx;
		}

		private InstsCtx ctx;
		private TS tsh;
		private int etqh;
		private boolean hay_tsh;
		private boolean hay_etqh;

	}

	public interface InstsCtx {
		public TS tsh();

		public String regla();

		public int etqh();
	}

	// Instruccion
	abstract public class Inst {

		protected Inst() {
			hay_tsh = false;
			hay_etqh = false;
		}

		abstract public int etq();

		abstract public Error err();

		abstract public String regla();

		abstract public List<Instruccion> cod();

		public TS tsh() {
			if (!hay_tsh) {
				tsh = ctx.tsh();
				hay_tsh = true;
			}
			return tsh;
		}

		public int etqh() {
			if (!hay_etqh) {
				etqh = ctx.etqh();
				hay_etqh = true;
			}
			return etqh;
		}

		public void registraCtx(InstCtx ctx) {
			this.ctx = ctx;
		}

		public InstCtx contexto() {
			return ctx;
		}

		private InstCtx ctx;
		private TS tsh;
		private int etqh;
		private boolean hay_tsh;
		private boolean hay_etqh;

	}

	public interface InstCtx {
		public TS tsh();

		public int etqh();

		public String regla();
	}

	// Casos
	abstract public class Casos {

		protected Casos() {
			hay_tsh = false;
			hay_etqh = false; 
			hay_irh = false;
		}

		abstract public int etq();

		abstract public Error err();

		abstract public String regla();

		abstract public List<Instruccion> cod();

		public TS tsh() {
			if (!hay_tsh) {
				tsh = ctx.tsh();
				hay_tsh = true;
			}
			return tsh;
		}

		public int etqh() {
			if (!hay_etqh) {
				etqh = ctx.etqh();
				hay_etqh = true;
			}
			return etqh;
		}

		public int irh() {
			if (!hay_irh) {
				irh = ctx.irh();
				hay_irh = true;
			}
			return irh;
		}

		public void registraCtx(CasosCtx ctx) {
			this.ctx = ctx;
		}

		public CasosCtx contexto() {
			return ctx;
		}

		private CasosCtx ctx;
		private TS tsh;
		private int etqh;
		private int irh;
		private boolean hay_tsh;
		private boolean hay_etqh;
		private boolean hay_irh;

	}

	public interface CasosCtx {
		public int etqh();

		public TS tsh();

		public int irh();

		public String regla();
	}

	// Caso
	abstract public class Caso {

		protected Caso() {
			hay_tsh = false;
			hay_etqh = false;
			hay_irh = false;
		}

		abstract public int etq();

		abstract public Error err();

		abstract public String regla();

		abstract public List<Instruccion> cod();

		public TS tsh() {
			if (!hay_tsh) {
				tsh = ctx.tsh();
				hay_tsh = true;
			}
			return tsh;
		}

		public int etqh() {
			if (!hay_etqh) {
				etqh = ctx.etqh();
				hay_etqh = true;
			}
			return etqh;
		}

		public int irh() {
			if (!hay_irh) {
				irh = ctx.irh();
				hay_irh = true;
			}
			return irh;
		}

		public void registraCtx(CasoCtx ctx) {
			this.ctx = ctx;
		}

		public CasoCtx contexto() {
			return ctx;
		}

		private CasoCtx ctx;
		private TS tsh;
		private int etqh;
		private int irh;
		private boolean hay_tsh;
		private boolean hay_etqh;
		private boolean hay_irh;
	}

	/* CONTEXTO DE CASO */
	public interface CasoCtx {
		public int etqh();

		public TS tsh();

		public int irh();

		public String regla();
	}

	abstract public class Exp0 {

		protected Exp0() {
			hay_tsh = false;
			hay_etqh = false;
		}

		abstract public List<Instruccion> cod();

		abstract public int etq();

		abstract public String regla();

		abstract public CatLexica tipo();

		public TS tsh() {
			if (!hay_tsh) {
				tsh = ctx.tsh();
				hay_tsh = true;
			}
			return tsh;
		}

		public void registraCtx(Exp0Ctx ctx) {
			this.ctx = ctx;
		}

		public int etqh() {
			if (!hay_etqh) {
				etqh = ctx.etqh();
				hay_etqh = true;
			}
			return etqh;
		}

		public Exp0Ctx contexto() {
			return ctx;
		}

		private Exp0Ctx ctx;
		private TS tsh;
		private int etqh;
		private boolean hay_etqh;
		private boolean hay_tsh;
	}

	public interface Exp0Ctx {
		public TS tsh();

		public String regla();

		public int etqh();
	}

	// EXP1
	abstract public class Exp1 {
		protected Exp1() {
			hay_tsh = false;
			hay_etqh = false;
		}

		abstract public List<Instruccion> cod();

		abstract public int etq();

		abstract public String regla();

		abstract public CatLexica tipo();

		public TS tsh() {
			if (!hay_tsh) {
				tsh = ctx.tsh();
				hay_tsh = true;
			}
			return tsh;
		}

		public int etqh() {
			if (!hay_etqh) {
				etqh = ctx.etqh();
				hay_etqh = true;
			}
			return etqh;
		}

		public void registraCtx(Exp1Ctx ctx) {
			this.ctx = ctx;
		}

		public Exp1Ctx contexto() {
			return ctx;
		}

		private Exp1Ctx ctx;
		private TS tsh;
		private boolean hay_tsh;
		private boolean hay_etqh;
		private int etqh;
	}

	public interface Exp1Ctx {
		public TS tsh();

		public String regla();

		public int etqh();
	}

	// Exp2
	abstract public class Exp2 {

		protected Exp2() {
			hay_tsh = false;
			hay_etqh = false;
		}

		abstract public List<Instruccion> cod();

		abstract public int etq();

		abstract public String regla();

		abstract public CatLexica tipo();

		public TS tsh() {
			if (!hay_tsh) {
				tsh = ctx.tsh();
				hay_tsh = true;
			}
			return tsh;
		}

		public int etqh() {
			if (!hay_etqh) {
				etqh = ctx.etqh();
				hay_etqh = true;
			}
			return etqh;
		}

		public void registraCtx(Exp2Ctx ctx) {
			this.ctx = ctx;
		}

		public Exp2Ctx contexto() {
			return ctx;
		}

		private Exp2Ctx ctx;
		private TS tsh;
		private boolean hay_tsh;
		private boolean hay_etqh;
		private int etqh;

	}

	public interface Exp2Ctx {
		public TS tsh();

		public String regla();

		public int etqh();
	}

	// Exp3
	abstract public class Exp3 {

		protected Exp3() {
			hay_tsh = false;
			hay_etqh = false;
		}

		abstract public List<Instruccion> cod();

		abstract public int etq();

		abstract public String regla();

		abstract public CatLexica tipo();

		public TS tsh() {
			if (!hay_tsh) {
				tsh = ctx.tsh();
				hay_tsh = true;
			}
			return tsh;
		}

		public int etqh() {
			if (!hay_etqh) {
				etqh = ctx.etqh();
				hay_etqh = true;
			}
			return etqh;
		}

		public void registraCtx(Exp3Ctx ctx) {
			this.ctx = ctx;
		}

		public Exp3Ctx contexto() {
			return ctx;
		}

		private Exp3Ctx ctx;
		private TS tsh;
		private boolean hay_tsh;
		private boolean hay_etqh;
		private int etqh;

	}

	public interface Exp3Ctx {
		public TS tsh();

		public String regla();

		public int etqh();
	}

	abstract public class Exp4 {

		protected Exp4() {
			hay_tsh = false;
			hay_etqh = false;
		}

		abstract public List<Instruccion> cod();

		abstract public int etq();

		abstract public String regla();

		abstract public CatLexica tipo();

		public TS tsh() {
			if (!hay_tsh) {
				tsh = ctx.tsh();
				hay_tsh = true;
			}
			return tsh;
		}

		public int etqh() {
			if (!hay_etqh) {
				etqh = ctx.etqh();
				hay_etqh = true;
			}
			return etqh;
		}

		public void registraCtx(Exp4Ctx ctx) {
			this.ctx = ctx;
		}

		public Exp4Ctx contexto() {
			return ctx;
		}

		private Exp4Ctx ctx;
		private TS tsh;
		private boolean hay_tsh;
		private boolean hay_etqh;
		private int etqh;

	}

	public interface Exp4Ctx {
		public TS tsh();

		public String regla();

		public int etqh();
	}

	public interface OpComparacion {
		public CatLexica op();

		public String regla();

		public List<Instruccion> cod();
	}

	public interface OpAditivo {
		public CatLexica op();

		public String regla();

		public List<Instruccion> cod();
	}

	public interface OpMultiplicativo {
		public CatLexica op();

		public String regla();

		public List<Instruccion> cod();
	}

	public interface OpUnario {
		public CatLexica op();

		public String regla();

		public List<Instruccion> cod();
	}

	// Implementación clases
	/*
	 * Programa ::= Declaraciones & Instrucciones 
	 * Instrucciones.tsh =Declaraciones.ts 
	 * Programa.error = Declaraciones.error or Instrucciones.error 
	 * Programa.cod = Instrucciones.cod
	 */
	public class ProgR1 implements Programa {

		public ProgR1(Decs decs, Insts insts) {
			this.decs = decs;
			this.insts = insts;
			this.hay_err = false;
			this.hay_cod = false;

			insts.registraCtx(new InstsCtx() {
				public TS tsh() {
					return ProgR1.this.decs.ts();
				}

				public String regla() {
					return "Programa ::= Declaraciones & Instrucciones | Instrucciones";
				}

				public int etqh() {
					return 0;
				}
			});
		}

		public String regla() {
			return "Programa ::= Declaraciones & Instrucciones | Programa";
		}

		public Error err() {
			if (!hay_err) {
				err = joinErrors(decs.err(), insts.err());
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = insts.cod();
				hay_cod = true;
			}
			return cod;
		}

		private Decs decs;
		private Insts insts;
		private List<Instruccion> cod;
		private boolean hay_cod;
		private Error err;
		private boolean hay_err;

	}

	public class ProgR1Debug extends ProgR1 {
		public ProgR1Debug(Decs decs, Insts insts) {
			super(decs, insts);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Programa ::= Instrucciones Instrucciones.tsh = creaTS() Programa.error =
	 * Instrucciones.error Instrucciones.etqh=0 Programa.cod = Instrucciones.cod
	 */
	public class ProgR2 implements Programa {

		public ProgR2(Insts insts) {

			this.insts = insts;
			this.hay_err = false;
			this.hay_cod = false;

			insts.registraCtx(new InstsCtx() {
				public TS tsh() {
					return creaTS();
				}

				public int etqh() {
					return 0;
				}

				public String regla() {
					return "Programa ::= Instrucciones | Instrucciones";
				}
			});
		}

		public String regla() {
			return "Programa ::= Instrucciones | Programa";
		}

		public Error err() {
			if (!hay_err) {
				err = insts.err();
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = insts.cod();
				hay_cod = true;
			}
			return cod;
		}

		private Insts insts;
		private List<Instruccion> cod;
		private boolean hay_cod;
		private Error err;
		private boolean hay_err;

	}

	public class ProgR2Debug extends ProgR2 {
		public ProgR2Debug(Insts insts) {
			super(insts);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Declaraciones ::= Declaraciones ; Declaracion Declaraciones(0).ts =
	 * aniadeSimb(Declaraciones(1).ts, Declaracion.iden, Declaracion.tipo,
	 * Declaraciones(1).dir) Declaraciones(0).dir = Declaraciones(1).dir + 1
	 * Declaraciones(0).error = Declaraciones(1).error or
	 * existeSimb(Declaraciones(1).ts,Declaracion.id)
	 */
	public class DecsR1 implements Decs {

		public DecsR1(Decs decs_1, Dec dec) {
			this.dec = dec;
			this.decs_1 = decs_1;
			this.hay_err = false;
			this.hay_ts = false;
		}

		public String regla() {
			return "Declaraciones  ::= Declaraciones  ; Declaracion | Declaraciones(0)";
		}

		public TS ts() {
			if (!hay_ts) {
				ts = aniadeSimb(decs_1.ts(), dec.iden(), dec.tipo(),
						decs_1.dir());
				hay_ts = true;
			}
			return ts;
		}

		public Error err() {
			if (!hay_err) {
				err = joinErrors(
						decs_1.err(),
						errorSi(existeSimb(decs_1.ts(), dec.iden()),
								dec.fila(), dec.col(), "Constante duplicada:"
										+ dec.iden()));
				hay_err = true;
			}
			return err;
		}

		public int dir() {
			return decs_1.dir() + 1;
		}

		private Dec dec;
		private TS ts;
		private Decs decs_1;
		private Error err;
		private boolean hay_err;
		private boolean hay_ts;
	}

	public class DecsR1Debug extends DecsR1 {
		public DecsR1Debug(Decs decs_1, Dec dec) {
			super(decs_1, dec);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public TS ts() {
			requerido(regla() + ".ts");
			return valor(regla() + ".ts", super.ts());
		}
	}

	/*
	 * Decs ::= Dec Declaraciones.ts = aniadeSimb(creaTS(),Declaracion.iden,
	 * Declaracion.tipo,0) Declaraciones.dir = 1 Declaraciones.error = false
	 */
	public class DecsR2 implements Decs {

		public DecsR2(Dec dec) {
			this.dec = dec;
			this.hay_err = false; 
			this.hay_ts = false;
		}

		public String regla() {
			return "Declaraciones  ::= Declaracion | Declaraciones";
		}

		public TS ts() {
			if (!hay_ts) {
				ts = aniadeSimb(creaTS(), dec.iden(), dec.tipo(), 0);
				hay_ts = true;
			}
			return ts;
		}

		public Error err() {
			if (!hay_err) {
				err = noError();
				hay_err = true;
			}
			return err;
		}

		public int dir() {
			return 1;
		}

		private Dec dec;
		private TS ts;

		private Error err;
		private boolean hay_err;
		private boolean hay_ts;
	}

	public class DecsR2Debug extends DecsR2 {
		public DecsR2Debug(Dec dec) {
			super(dec);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public TS ts() {
			requerido(regla() + ".ts");
			return valor(regla() + ".ts", super.ts());
		}
	}

	/*
	 * Declaracion ::= Tipo IDEN Declaracion.tipo = Tipo.tipo Declaracion.iden
	 * =IDEN.lex Dec.fila = id.fila Dec.col = id.col
	 */
	public class DecR1 implements Dec {

		public DecR1(Tipo tipo, Token IDEN) {
			this.tipo = tipo;
			this.iden = IDEN;
			this.hay_fila = false;
			this.hay_col = false;
		}

		public String regla() {
			return "Declaracion ::= Tipo IDEN | Declaracion";
		}

		public String iden() {
			return iden.leeLexema();
		}

		public CatLexica tipo() {
			return tipo.tipo();
		}

		public int fila() {
			if (!hay_fila) {
				fila = iden.leeFila();
				hay_fila = true;
			}
			return fila;

		}

		public int col() {
			if (!hay_col) {
				col = iden.leeCol();
				hay_col = true;
			}
			return col;
		}

		private Token iden;
		private Tipo tipo;

		private boolean hay_fila;
		private boolean hay_col;
		private int fila;
		private int col;
	}

	public class DecR1Debug extends DecR1 {
		public DecR1Debug(Tipo tipo, Token iden) {
			super(tipo, iden);
		}

		public String iden() {
			requerido(regla() + ".cte");
			return valor(regla() + ".cte", super.iden());
		}

		public CatLexica tipo() {
			requerido(regla() + ".tipo");
			return valor(regla() + ".tipo", super.tipo());
		}

		public int fila() {
			requerido(regla() + ".fila");
			return valor(regla() + ".fila", super.fila());
		}

		public int col() {
			requerido(regla() + ".col");
			return valor(regla() + ".col", super.col());
		}
	}

	/*
	 * Tipo := INT
	 */
	public class TipoR1 implements Tipo {

		public TipoR1(Token tDeInt) {
			this.tipo = tDeInt.leeCategoria();
		}

		public CatLexica tipo() {
			return tipo;
		}

		public Error err() {
			if (this.tipo != CatLexica.INT)
				return new Error("No es un INT");
			else
				return noError();
		}

		private CatLexica tipo;

		public String regla() {
			return "Tipo.tipo ::= int ";
		}
	}

	public class TipoR1Debug extends TipoR1 {
		public TipoR1Debug(Token tDeInt) {
			super(tDeInt);
		}

		public CatLexica tipo() {
			requerido(regla() + ".tipo");
			return valor(regla() + ".tipo", super.tipo());
		}
	}

	/*
	 * Tipo := boolean
	 */
	public class TipoR2 implements Tipo {

		public TipoR2(Token tDeBol) {
			this.tipo = tDeBol.leeCategoria();
		}

		public Error err() {
			if (this.tipo != CatLexica.BOOLEAN)
				return new Error("No es un BOOLEAN");
			else
				return noError();
		}

		public String regla() {
			return "Tipo.tipo ::= boolean ";
		}

		public CatLexica tipo() {
			return tipo;
		}

		private CatLexica tipo;
	}

	public class TipoR2Debug extends TipoR2 {
		public TipoR2Debug(Token tDeBol) {
			super(tDeBol);
		}

		public CatLexica tipo() {
			requerido(regla() + ".tipo");
			return valor(regla() + ".tipo", super.tipo());
		}
	}

	/*
	 * Instrucciones ::= Instrucciones ; Instruccion Instrucciones(1).tsh =
	 * Instrucciones(0).tsh Instruccion.tsh = Instrucciones(0).tsh
	 * Instrucciones(0).error = Instrucciones(1).error || Instruccion.error
	 * Instrucciones(1).etqh = Instrucciones(0).etqh Instruccion.etqh =
	 * Instrucciones(1).etq Instrucciones(0).etq = Instruccion.etq
	 * Instrucciones(0).cod = Instrucciones(1).cod || Instruccion.cod
	 */
	public class InstsR1 extends Insts {

		public InstsR1(Insts insts_1, Inst inst) {
			this.insts_1 = insts_1;
			this.inst = inst;
			this.hay_err = false;
			 
			this.hay_cod = false;
			this.hay_etq = false;

			insts_1.registraCtx(new InstsCtx() {
				public TS tsh() {
					return InstsR1.this.tsh();
				}

				public int etqh() {
					return InstsR1.this.etqh();
				}

				public String regla() {
					return "Instrucciones(0) ::= Instrucciones(1) ; Instruccion | Instrucciones(1)";
				}
			});

			inst.registraCtx(new InstCtx() {
				public TS tsh() {
					return InstsR1.this.tsh();
				}

				public int etqh() {
					return InstsR1.this.insts_1.etq();
				}

				public String regla() {
					return "Instrucciones(0) ::= Instrucciones(1) ; Instruccion | Instruccion";
				}
			});

		}

		public String regla() {
			return "Instrucciones(0) ::= Instrucciones(1) ; Instruccion | Instrucciones(0)";
		}

		public Error err() {
			if (!hay_err) {
				err = joinErrors(insts_1.err(), inst.err());
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = concat(insts_1.cod(), inst.cod());
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			 if (!hay_etq) {
			 etq = inst.etq();
			 hay_etq = true;
			 }
			 return etq;

		}

		private Insts insts_1;
		private Inst inst;
		private List<Instruccion> cod;
		private boolean hay_cod;
		private boolean hay_etq;
		private int etq;
		private Error err;
		private boolean hay_err;

	}

	public class InstsR1Debug extends InstsR1 {
		public InstsR1Debug(Insts insts_1, Inst inst) {
			super(insts_1, inst);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Instrucciones ::= Instruccion Instruccion.tsh = Instrucciones.tsh
	 * Instrucciones.error = Instruccion.error Instruccion.etqh =
	 * Instrucciones.etqh Instrucciones.etq = Instruccion.etq Instrucciones.cod
	 * = Instruccion.cod
	 */
	public class InstsR2 extends Insts {

		public InstsR2(Inst inst) {
			this.inst = inst;
			this.hay_err = false;
			 this.hay_cod = false;
			 this.hay_etq = false;
			inst.registraCtx(new InstCtx() {
				public TS tsh() {
					return InstsR2.this.tsh();
				}

				public int etqh() {
					return InstsR2.this.etqh();
				}

				public String regla() {
					return "Instrucciones ::= Instruccion | Instruccion";
				}
			});

		}

		public String regla() {
			return "Instrucciones ::= Instruccion | Instrucciones";
		}

		public Error err() {
			if (!hay_err) {
				err = inst.err();
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = inst.cod();
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			 if (!hay_etq){
			 etq= inst.etq();
			 hay_etq = true;
			 }
			 return etq;
		}

		private Inst inst;
		private Error err;
		private boolean hay_etq;
		private int etq;
		private boolean hay_err;
		private boolean hay_cod;
		private List<Instruccion> cod;
	}

	public class InstsR2Debug extends InstsR2 {
		public InstsR2Debug(Inst inst) {
			super(inst);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Instruccion ::= IAsignacion IAsignacion.tsh = Instruccion.tsh
	 * Instruccion.error = IAsignacion.error IAsignacion.etqh = Instruccion.etqh
	 * Instruccion.etq = IAsignacion.etq Instruccion.cod = IAsignacion.cod
	 */
	public class InstR1 extends Inst {

		public InstR1(Inst iasig) {
			this.iasig = iasig;
			 this.hay_cod = false;
			 this.hay_etq = false;
			iasig.registraCtx(new InstCtx() {
				public TS tsh() {
					return InstR1.this.tsh();
				}

				public int etqh() {
					return InstR1.this.etqh();
				}

				public String regla() {
					return "Instruccion ::= IAsignacion | IAsignacion";
				}
			});
		}

		public String regla() {
			return "Instruccion ::= IAsignacion | Instruccion";
		}

		public int etq() {
			 if (!hay_etq){
			 etq= iasig.etq();
			 hay_etq = true;
			 }
			 return etq;
		}

		public Error err() {
			if (!hay_err) {
				err = iasig.err();
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = iasig.cod();
				hay_cod = true;
			}
			return cod;
		}

		private Error err;
		private boolean hay_err;
		private boolean hay_cod;
		private boolean hay_etq;
		private int etq;
		private List<Instruccion> cod;
		private Inst iasig;

	}

	public class InstR1Debug extends InstR1 {
		public InstR1Debug(Inst iAsig) {
			super(iAsig);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Instruccion ::= IIF IIF.tsh = Instruccion.tsh Instruccion.error =
	 * IIF.error IIF.etqh = Instruccion.etqh Instruccion.etq = IIF.etq
	 * Instruccion.cod = IIF.cod
	 */
	public class InstR2 extends Inst {

		public InstR2(Inst iIF) {
			this.iIF = iIF;
			 this.hay_err = false;
			 this.hay_cod = false;
			 this.hay_etq = false;
			iIF.registraCtx(new InstCtx() {
				public TS tsh() {
					return InstR2.this.tsh();
				}

				public int etqh() {
					return InstR2.this.etqh();
				}

				public String regla() {
					return "Instruccion ::= IIF | IIF";
				}
			});

		}

		public String regla() {
			return "Instruccion ::= IIF | Instruccion";
		}

		public int etq() {
			 if (!hay_etq){
				 etq= iIF.etq();
				 hay_etq = true;
			 }
			 return etq;
		}

		public Error err() {
			if (!hay_err) {
				err = iIF.err();
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = iIF.cod();
				hay_cod = true;
			}
			return cod;
		}

		private Error err;
		private boolean hay_etq;
		private int etq;
		private boolean hay_err;
		private boolean hay_cod;
		private List<Instruccion> cod;
		private Inst iIF;
	}

	public class InstR2Debug extends InstR2 {
		public InstR2Debug(Inst iIF) {
			super(iIF);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Instruccion ::= IDO 
	 * IDO.tsh = Instruccion.tsh 
	 * Instruccion.error = IDO.error 
	 * IDO.etqh = Instruccion.etqh 
	 * Instruccion.etq = IDO.etq
	 * Instruccion.cod = IDO.cod
	 */
	public class InstR3 extends Inst {

		public InstR3(Inst iDO) {
			this.iDO = iDO;
			 this.hay_err = false;
			 this.hay_cod = false;
			 this.hay_etq = false;
			iDO.registraCtx(new InstCtx() {
				public TS tsh() {
					return InstR3.this.tsh();
				}

				public int etqh() {
					return InstR3.this.etqh();
				}

				public String regla() {
					return "Instruccion ::= IDO | IDO";
				}
			});
		}

		public String regla() {
			return "Instruccion ::= IDO | Instruccion";
		}

		public int etq() {
			 if (!hay_etq){
			 etq= iDO.etq();
			 hay_etq = true;
			 }
			 return etq;
		}

		public Error err() {
			if (!hay_err) {
				err = iDO.err();
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = iDO.cod();
				hay_cod = true;
			}
			return cod;
		}

		private Error err;
		private List<Instruccion> cod;
		private boolean hay_err;
		private boolean hay_cod;
		private int etq;
		private boolean hay_etq;
		private Inst iDO;
	}

	public class InstR3Debug extends InstR3 {
		public InstR3Debug(Inst iDO) {
			super(iDO);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * IAsignacion ::= IDEN := Exp0 
	 * Exp0.tsh = IAsignacion.tsh 
	 * IAsignacion.error = not asignacionCorrecta(IDEN.lex,IAsignacion.tsh,Exp0.tipo)
	 * Exp0.etqh = IAsignacion.etqh 
	 * IAsignacion.etq = Exp0.etq + 1 
	 * IAsignacion.cod = Exp0.cod || desapila_dir(dirDe(IDEN.lex,IAsignacion.tsh))
	 */
	public class IAsigR1 extends Inst {

		public IAsigR1(Token iden, Exp0 exp0) {
			this.iden = iden;
			this.exp0 = exp0;
			this.hay_err = false;
			 this.hay_cod = false;
			 this.hay_etq = false;
			exp0.registraCtx(new Exp0Ctx() {
				public TS tsh() {
					return IAsigR1.this.tsh();
				}

				public int etqh() {
					return IAsigR1.this.etqh();
				}

				public String regla() {
					return "IAsignacion ::= IDEN := Exp0 | Exp0";
				}
			});
		}

		public String regla() {
			return "IAsignacion ::= IDEN := Exp0 | IAsignacion";
		}

		public int etq() {
			 
			 if (!hay_etq){
				 etq= exp0.etq()+1;
				 hay_etq = true;
			 }
			 return etq;
		}

		public Error err() {
			 
			 if (!hay_err) {
				 if (!asignacionCorrecta(iden.leeLexema(),tsh(),exp0.tipo())){
					 err = new Error("La asignación no ha sido correcta");
					 hay_err = true;
				 }
				 else 
					 err= new Error();
			 }
			 return err;
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
			 cod =
			 concat(exp0.cod(),desapila(String.valueOf(dirDe(iden.leeLexema(),
			 tsh()))));
			 hay_cod = true;
			 }
			 return cod;
		}

		private Token iden;
		private Exp0 exp0;
		 private int etq;
		 private boolean hay_etq;
		 private List<Instruccion> cod;
		 private boolean hay_cod;
		 private Error err;
		 private boolean hay_err;

	}

	public class IAsigR1Debug extends IAsigR1 {
		public IAsigR1Debug(Token iden, Exp0 exp0) {
			super(iden, exp0);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * IIF ::= if Casos fi 
	 * Casos.tsh = IIF.tsh 
	 * IIF.error = Casos.error
	 * Casos.etqh = IIF.etqh 
	 * Casos.irh = Casos.etq 
	 * IIF.etq = Casos.etq 
	 * IIF.cod =Casos.cod
	 */
	public class IIFR1 extends Inst {

		public IIFR1(Casos casos) {
			this.casos = casos;
			 this.hay_err = false;
			 this.hay_cod = false;
			 this.hay_etq = false;
			casos.registraCtx(new CasosCtx() {
				public TS tsh() {
					return IIFR1.this.tsh();
				}

				public int etqh() {
					return IIFR1.this.etqh();
				}

				public int irh() {
					return IIFR1.this.casos.etq();
				}

				public String regla() {
					return "IIF ::= if Casos fi | Casos";
				}
			});
		}

		public String regla() {
			return " IIF ::= if Casos fi  | IIF";
		}

		public int etq() {
			 if (!hay_etq){
			 etq= casos.etq();
			 hay_etq = true;
			 }
			 return etq;
			 
		}

		public Error err() {
			if (!hay_err) {
				err = casos.err();
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = casos.cod();
				hay_cod = true;
			}
			return cod;
		}

		private int etq;
		private boolean hay_etq;
		private Error err;
		private List<Instruccion> cod;
		private boolean hay_err;
		private boolean hay_cod;
		private Casos casos;
	}

	public class IIFR1Debug extends IIFR1 {
		public IIFR1Debug(Casos casos) {
			super(casos);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * IDO ::= do Casos od
	 * 
	 * Casos.tsh = IDO.tsh 
	 * IDO.error = Casos.error 
	 * Casos.etqh = IDO.etqh
	 * Casos.irh = IDO.etqh 
	 * IDO.etq = Casos.etq 
	 * IDO.cod = Casos.cod
	 */
	public class IDOR1 extends Inst {

		public IDOR1(Casos casos) {
			this.casos = casos;
			this.hay_err = false;
			 this.hay_cod = false;
			 this.hay_etq = false;
			casos.registraCtx(new CasosCtx() {
				public TS tsh() {
					return IDOR1.this.tsh();
				}

				public int etqh() {
					return IDOR1.this.etqh();
				}

				public int irh() {
					return IDOR1.this.etqh();
				}

				public String regla() {
					return "IDO ::= do Casos od | Casos";
				}
			});
		}

		public String regla() {
			return " IDO ::= do Casos od  | IDO";
		}

		public int etq() {
			 if (!hay_etq){
				 etq= casos.etq();
				 hay_etq = true;
			 }
			 return etq;
		}

		public Error err() {
			if (!hay_err) {
				err = casos.err();
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = casos.cod();
				hay_cod = true;
			}
			return cod;
		}

		private Error err;
		private int etq;
		private boolean hay_etq;
		private List<Instruccion> cod;
		private boolean hay_err;
		private boolean hay_cod;
		private Casos casos;
	}

	public class IDOR1Debug extends IDOR1 {
		public IDOR1Debug(Casos casos) {
			super(casos);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
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
	 */
	public class CasosR1 extends Casos {

		public CasosR1(Casos casos, Caso caso) {
			this.casos1 = casos;
			this.caso = caso;
			this.hay_err = false;
			this.hay_cod = false;
			this.hay_etq = false;
			casos1.registraCtx(new CasosCtx() {
				public TS tsh() {
					return CasosR1.this.tsh();
				}

				public int etqh() {
					return CasosR1.this.etqh();
				}

				public int irh() {
					return CasosR1.this.irh();
				}

				public String regla() {
					return "Casos(0)::= Casos(1) [] Caso | Casos(1) ";
				}
			});
			caso.registraCtx(new CasoCtx() {
				public TS tsh() {
					return CasosR1.this.tsh();
				}

				public int etqh() {
					return CasosR1.this.casos1.etq();
				}

				public int irh() {
					return CasosR1.this.irh();
				}

				public String regla() {
					return "Casos(0)::= Casos(1) [] Caso | Caso ";
				}
			});
		}

		public String regla() {
			return "Casos(0)::= Casos(1) [] Caso | Casos(0) ";
		}

		public int etq() {
			 if (!hay_etq){
				 etq= caso.etq();
				 hay_etq = true;
			 }
			 return etq;
		}

		public Error err() {
			if (!hay_err) {
				err = joinErrors(casos1.err(), caso.err());
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = concat(casos1.cod(), caso.cod());
				hay_cod = true;
			}
			return cod;
		}

		private int etq;
		private boolean hay_etq;
		private Error err;
		private List<Instruccion> cod;
		private boolean hay_err;
		private boolean hay_cod;
		private Casos casos1;
		private Caso caso;
	}

	public class CasosR1Debug extends CasosR1 {
		public CasosR1Debug(Casos casos_1, Caso caso) {
			super(casos_1, caso);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
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
	 */
	public class CasosR2 extends Casos {

		public CasosR2(Caso caso) {
			this.caso = caso;
			this.hay_err = false;
			 this.hay_cod = false;
			 this.hay_etq = false;
			caso.registraCtx(new CasoCtx() {
				public TS tsh() {
					return CasosR2.this.tsh();
				}

				public int etqh() {
					return CasosR2.this.etqh();
				}

				public int irh() {
					return CasosR2.this.irh();
				}

				public String regla() {
					return "Casos ::= Caso | Caso";
				}
			});
		}

		public String regla() {
			return " Casos ::= Caso | Casos";
		}

		public int etq() {
			 if (!hay_etq){
				 etq= caso.etq();
				 hay_etq = true;
			 }
			 return etq;
		}

		public Error err() {
			if (!hay_err) {
				err = caso.err();
				hay_err = true;
			}
			return err;
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = caso.cod();
				hay_cod = true;
			}
			return cod;
		}

		private int etq;
		private boolean hay_etq;
		private Error err;
		private List<Instruccion> cod;
		private boolean hay_err;
		private boolean hay_cod;
		private Caso caso;

	}

	public class CasosR2Debug extends CasosR2 {
		public CasosR2Debug(Caso caso) {
			super(caso);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Caso ::= case Exp0 -> Instrucciones
	 * 
	 * Propagaci—n de la tabla de s’mbolos 
	 * Exp0.tsh = Casos.tsh
	 * Instrucciones.tsh = Caso.tsh 
	 * Comprobaci—n de las restricciones contextuales 
	 * Caso.error = Instrucciones.error or not (Exp0.tipo = boolean)
	 * Generaci—n de c—digo 
	 * Exp0.etqh = Caso.etqh 
	 * Instrucciones.etqh =Exp0.etq + 1 
	 * Caso.etq = Instrucciones.etq + 1 
	 * Caso.cod = Exp0.cod || ir_f(Instrucciones.etq + 1) || Instrucciones.cod || ir_a(Caso.irh)
	 */
	public class CasoR1 extends Caso {

		public CasoR1(Exp0 exp0, Insts insts) {
			this.exp0 = exp0;
			this.insts = insts;
			 this.hay_err = false;
			 this.hay_cod = false;
			 this.hay_etq = false;
			exp0.registraCtx(new Exp0Ctx() {
				public TS tsh() {
					return CasoR1.this.tsh();
				}

				public int etqh() {
					return CasoR1.this.etqh();
				}

				public String regla() {
					return "Caso ::= case Exp0 -> Instrucciones | Exp0";
				}
			});
			insts.registraCtx(new InstsCtx() {
				public TS tsh() {
					return CasoR1.this.tsh();
				}

				public int etqh() {
					return CasoR1.this.exp0.etq() + 1;
				}

				public String regla() {
					return "Caso ::= case Exp0 -> Instrucciones | Instrucciones";
				}
			});
		}

		public String regla() {
			return " Caso ::= case Exp0 -> Instrucciones | Caso";
		}

		public int etq() {
			 if (!hay_etq){
				 etq= insts.etq()+1;
				hay_etq = true;
			 }
			 return etq;

		}

		public Error err() {
			 if (!hay_err) {
			 if (exp0.tipo() != CatLexica.BOOLEAN) {
				 Error errExp0 = new Error("Exp0 no es un booleano");
				 err = joinErrors(insts.err(), errExp0);
			 } else
				 err = insts.err();
			 	hay_err = true;
			 }
			 return err;

//			boolean e = (exp0.tipo().compareTo(CatLexica.BOOLEAN) == 0);
//			Error error;
//			if (!e) {
//				error = new Error("Error de tipos");
//			} else
//				error = noError();
//			return joinErrors(insts.err(), error);
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = concat(exp0.cod(), ir_f(insts.etq() + 1));
				cod = concat(cod, insts.cod());
				cod = concat(cod, ir_a(irh()));
				hay_cod = true;
			}
			return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp0 exp0;
		private Insts insts;
		private Error err;
		private boolean hay_err;
		private int etq;
		private boolean hay_etq;
	}

	public class CasoR1Debug extends CasoR1 {
		public CasoR1Debug(Exp0 exp0, Insts insts) {
			super(exp0, insts);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", super.err());
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * 
	 * Exp0 ::= Exp1 OpComparacion Exp1
	 * 
	 * Exp1(0).tsh = Exp0.tsh Exp1(1).tsh = Exp0.tsh Exp0.tipo =
	 * tipoOpBin(OpComparacion.op,Exp1(0).tipo,Exp1(1).tipo) Exp1(0).etqh =
	 * Exp0.etqh Exp1(1).etqh = Exp1(0).etq Exp0.etq = Exp1(1).etq + 1 Exp0.cod
	 * = Exp1(0).cod || Exp1(1).cod ||OpComparacion.cod
	 */
	public class Exp0R1 extends Exp0 {

		public Exp0R1(Exp1 exp1_0, Exp1 exp1_1, OpComparacion opc) {
			this.exp1_0 = exp1_0;
			this.exp1_1 = exp1_1;
			this.opc = opc;
			this.hay_cod = false;
			this.hay_etq = false;

			exp1_0.registraCtx(new Exp1Ctx() {
				public TS tsh() {
					return Exp0R1.this.tsh();
				}

				public int etqh() {
					return Exp0R1.this.etqh();
				}

				public String regla() {
					return " Exp0 ::= Exp1(0) OpComparacion Exp1(1) | Exp1(0)";
				}
			});
			exp1_1.registraCtx(new Exp1Ctx() {
				public TS tsh() {
					return Exp0R1.this.tsh();
				}

				public int etqh() {
					return Exp0R1.this.exp1_0.etq();
				}

				public String regla() {
					return "Exp0 ::= Exp1(0) OpComparacion Exp1(1) | Exp1(1)";
				}
			});
		}

		public String regla() {
			return " Exp0 ::= Exp1(0) OpComparacion Exp1(1) | Exp0";
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = concat(exp1_0.cod(), concat(exp1_1.cod(), opc.cod()));
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq= exp1_1.etq()+1;
				 hay_etq = true;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return tipoOpBin(opc.op(), exp1_0.tipo(), exp1_1.tipo());
		}

		private int etq;
		private boolean hay_etq;
		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp1 exp1_0;
		private Exp1 exp1_1;
		private OpComparacion opc;

	}

	public class Exp0R1Debug extends Exp0R1 {
		public Exp0R1Debug(Exp1 exp1_0, Exp1 exp1_1, OpComparacion op) {
			super(exp1_0, exp1_1, op);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
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
	 */
	public class Exp0R2 extends Exp0 {

		public Exp0R2(Exp1 exp1) {
			this.exp1 = exp1;
			 this.hay_cod = false;
			 this.hay_etq = false;
			exp1.registraCtx(new Exp1Ctx() {
				public TS tsh() {
					return Exp0R2.this.tsh();
				}

				public int etqh() {
					return Exp0R2.this.etqh();
				}

				public String regla() {
					return " Exp0 ::= Exp1 | Exp1";
				}
			});
		}

		public String regla() {
			return " Exp0 ::= Exp1 | Exp0";
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = exp1.cod();
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq = exp1.etq();
				 hay_etq=true;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return exp1.tipo();
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp1 exp1;
		private boolean hay_etq;
		private int etq;

	}

	public class Exp0R2Debug extends Exp0R2 {
		public Exp0R2Debug(Exp1 exp1) {
			super(exp1);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Exp1 ::= Exp1 OpAditivo Exp2
	 * Exp1(1).tsh = Exp1(0).tsh 
	 * Exp2.tsh = Exp1(0).tsh 
	 * Exp1(0).tipo = tipoOpBin(OpAditivo.op,Exp1(1).tipo,Exp2.tipo)
	 * Exp1(1).etqh = Exp1(0).etqh 
	 * Exp2.etqh = Exp1(1).etq 
	 * Exp1(0).etq = Exp2.etq + 1 
	 * Exp1(0).cod = Exp1(1).cod || Exp2.cod || OpAditivo.cod
	 */
	public class Exp1R1 extends Exp1 {

		public Exp1R1(Exp1 exp1, Exp2 exp2, OpAditivo opa) {
			this.exp1 = exp1;
			this.exp2 = exp2;
			this.opa = opa;
			this.hay_cod = false;
			this.hay_etq = false;

			exp1.registraCtx(new Exp1Ctx() {
				public TS tsh() {
					return Exp1R1.this.tsh();
				}

				public int etqh() {
					return Exp1R1.this.etqh();
				}

				public String regla() {
					return " Exp1(0) ::= Exp1(1) OpAditivo Exp2 | Exp1(1)";
				}
			});
			exp2.registraCtx(new Exp2Ctx() {
				public TS tsh() {
					return Exp1R1.this.tsh();
				}

				public int etqh() {
					return Exp1R1.this.exp1.etq();
				}

				public String regla() {
					return "Exp1(0) ::= Exp1(1) OpAditivo Exp2 | Exp2";
				}
			});
		}

		public String regla() {
			return "Exp1(0) ::= Exp1(1) OpAditivo Exp2 | Exp1(0)";
		}

		public List<Instruccion> cod() {

			if (!hay_cod) {
				cod = concat(exp1.cod(), concat(exp2.cod(), opa.cod()));
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			if (!hay_etq){
				etq= exp2.etq()+1;
				hay_etq = true;
			}
			return etq;
		}

		public CatLexica tipo() {
			return tipoOpBin(opa.op(), exp1.tipo(), exp2.tipo());
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp1 exp1;
		private Exp2 exp2;
		private OpAditivo opa;
		private int etq;
		private boolean hay_etq;

	}

	public class Exp1R1Debug extends Exp1R1 {
		public Exp1R1Debug(Exp1 exp1_1, Exp2 exp2, OpAditivo opAdit) {
			super(exp1_1, exp2, opAdit);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}
	/*
	 * Exp1 ::= Exp2
	 * 
	 * Exp2.tsh = Exp1.tsh 
	 * Exp1.tipo = Exp2.tipo 
	 * Exp2.etqh = Exp1.etqh 
	 * Exp1.etq = Exp2.etq 
	 * Exp1.cod = Exp2.cod
	 */
	public class Exp1R2 extends Exp1 {

		public Exp1R2(Exp2 exp2) {
			this.exp2 = exp2;
			 this.hay_cod = false;
			 this.hay_etq = false;

			exp2.registraCtx(new Exp2Ctx() {
				public TS tsh() {
					return Exp1R2.this.tsh();
				}

				public int etqh() {
					return Exp1R2.this.etqh();
				}

				public String regla() {
					return " Exp1 ::= Exp2 | Exp2";
				}
			});
		}

		public String regla() {
			return " Exp1 ::= Exp2 | Exp1";
		}

		public List<Instruccion> cod() {

			if (!hay_cod) {
				cod = exp2.cod();
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq= exp2.etq();
				 hay_etq = true;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return exp2.tipo();
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp2 exp2;
		private int etq;
		private boolean hay_etq;

	}

	public class Exp1R2Debug extends Exp1R2 {
		public Exp1R2Debug(Exp2 exp2) {
			super(exp2);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Exp2 ::= Exp2 OpMultiplicativo Exp3 
	 * Exp2(1).tsh = Exp2(0).tsh 
	 * Exp3.tsh = Exp2(0).tsh 
	 * Exp2(0).tipo = tipoOpBin(OpMultiplicativo.op,Exp2(1).tipo,Exp3.tipo) Exp2(1).etqh = Exp2(0).etqh 
	 * Exp3.etqh = Exp2(1).etq 
	 * Exp2(0).etq = Exp3.etq + 1
	 * Exp2(0).cod = Exp2(1).cod || Exp3.cod || OpMultiplicativo.cod
	 */
	public class Exp2R1 extends Exp2 {

		public Exp2R1(Exp2 exp2, Exp3 exp3, OpMultiplicativo opm) {
			this.exp2 = exp2;
			this.exp3 = exp3;
			this.opm = opm;
			 this.hay_cod = false;
			 this.hay_etq = false;

			exp2.registraCtx(new Exp2Ctx() {
				public TS tsh() {
					return Exp2R1.this.tsh();
				}

				public int etqh() {
					return Exp2R1.this.etqh();
				}

				public String regla() {
					return " Exp2(0) ::= Exp2(1) OpMultiplicativo Exp3 | Exp2(1)";
				}
			});
			exp3.registraCtx(new Exp3Ctx() {
				public TS tsh() {
					return Exp2R1.this.tsh();
				}

				public int etqh() {
					return Exp2R1.this.exp2.etq();
				}

				public String regla() {
					return "Exp2(0) ::= Exp2(1) OpMultiplicativo Exp3 | Exp3";
				}
			});
		}

		public String regla() {
			return "Exp2(0) ::= Exp2(1) OpMultiplicativo Exp3 | Exp2(0)";
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = concat(exp2.cod(), concat(exp3.cod(), opm.cod()));
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq= exp3.etq()+1;
				 hay_etq = true;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return tipoOpBin(opm.op(), exp2.tipo(), exp3.tipo());
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp2 exp2;
		private Exp3 exp3;
		private OpMultiplicativo opm;
		private int etq;
		private boolean hay_etq;

	}

	public class Exp2R1Debug extends Exp2R1 {
		public Exp2R1Debug(Exp2 exp2_1, Exp3 exp3, OpMultiplicativo opMult) {
			super(exp2_1, exp3, opMult);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Exp2 ::= Exp3
	 * 
	 * Propagaci—n de la tabla de s’mbolos 
	 * Exp3.tsh = Exp2.tsh 
	 * Comprobaci—n de las restricciones contextuales 
	 * Exp2.tipo = Exp3.tipo 
	 * Generaci—n de c—digo
	 * Exp3.etqh = Exp2.etqh 
	 * Exp2.etq = Exp3.etq 
	 * Exp2.cod = Exp3.cod
	 */
	public class Exp2R2 extends Exp2 {

		public Exp2R2(Exp3 exp3) {
			this.exp3 = exp3;
			 this.hay_cod = false;
			 this.hay_etq = false;
			exp3.registraCtx(new Exp3Ctx() {
				public TS tsh() {
					return Exp2R2.this.tsh();
				}

				public int etqh() {
					return Exp2R2.this.etqh();
				}

				public String regla() {
					return " Exp2 ::= Exp3 | Exp3";
				}
			});
		}

		public String regla() {
			return " Exp2 ::= Exp3 | Exp2";
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = exp3.cod();
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq= exp3.etq();
				 hay_etq = true;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return exp3.tipo();
		}

		private int etq;
		private boolean hay_etq;
		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp3 exp3;

	}

	public class Exp2R2Debug extends Exp2R2 {
		public Exp2R2Debug(Exp3 exp3) {
			super(exp3);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Exp3 ::= OpUnario Exp3
	 * 
	 * Exp3(1).tsh = Exp3(0).tsh 
	 * Exp3(0).tipo = tipoOpUn(OpUnario.op,Exp3(1).tipo)
	 * Exp3(1).etqh = Exp3(0).etqh
	 * Exp3(0).etq = Exp3(1).etq + 1 
	 * Exp3(0).cod = Exp3(1).cod || OpUnario.cod
	 */
	public class Exp3R1 extends Exp3 {

		public Exp3R1(Exp3 exp3_1, OpUnario opu) {
			this.exp3_1 = exp3_1;
			this.opu = opu;
			 this.hay_cod = false;
			 this.hay_etq = false;
			exp3_1.registraCtx(new Exp3Ctx() {
				public TS tsh() {
					return Exp3R1.this.tsh();
				}

				public int etqh() {
					return Exp3R1.this.etqh();
				}

				public String regla() {
					return " Exp3(0) ::= OpUnaria Exp3(1)  | Exp3(1)";
				}
			});
		}

		public String regla() {
			return "Exp3(0) ::= OpUnaria Exp3(1)  | Exp3(1) | Exp3(0)";
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = concat(exp3_1.cod(), opu.cod());
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq= exp3_1.etq()+1;
				 hay_etq = true;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return tipoOpUnario(opu.op(), exp3_1.tipo());
		}

		private int etq;
		private boolean hay_etq;
		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp3 exp3_1;
		private OpUnario opu;

	}

	public class Exp3R1Debug extends Exp3R1 {
		public Exp3R1Debug(Exp3 exp3_1, OpUnario opUnar) {
			super(exp3_1, opUnar);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Exp3 ::= Exp4
	 * 
	 * Exp4.tsh = Exp3.tsh 
	 * Exp3.tipo = Exp4.tipo 
	 * Exp4.etqh = Exp3.etqh 
	 * Exp3.etq = Exp4.etq 
	 * Exp3.cod = Exp4.cod
	 */
	public class Exp3R2 extends Exp3 {

		public Exp3R2(Exp4 exp4) {
			this.exp4 = exp4;
			 this.hay_cod = false;
			 this.hay_etq = false;

			exp4.registraCtx(new Exp4Ctx() {
				public TS tsh() {
					return Exp3R2.this.tsh();
				}

				public int etqh() {
					return Exp3R2.this.etqh();
				}

				public String regla() {
					return " Exp3 ::= Exp4 | Exp4";
				}
			});
		}

		public String regla() {
			return " Exp3 ::= Exp4 | Exp3";
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				cod = exp4.cod();
				hay_cod = true;
			}
			return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq= exp4.etq();
				 hay_etq = true;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return exp4.tipo();
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp4 exp4;
		private int etq;
		private boolean hay_etq;

	}

	public class Exp3R2Debug extends Exp3R2 {
		public Exp3R2Debug(Exp4 exp4) {
			super(exp4);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Exp4 ::= true
	 * 
	 * Exp4.tipo = boolean 
	 * Exp4.etq = Exp4.etqh + 1 
	 * Exp4.cod = apila_true()
	 */
	public class Exp4R1 extends Exp4 {

		public Exp4R1() {
			 this.hay_cod = false;
			 this.hay_etq = false;
		}

		public String regla() {
			return " Exp4 ::= true | Exp4";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
			 hay_cod = true;
			 cod = single(apila_true());
			 }
			 return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq =this.etqh() + 1;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return CatLexica.BOOLEAN;
		}

		private int etq;
		private boolean hay_etq;
		private List<Instruccion> cod;
		private boolean hay_cod;
	}

	public class Exp4R1Debug extends Exp4R1 {
		public Exp4R1Debug() {
			super();
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Exp4 ::= false 
	 * Exp4.tipo = boolean 
	 * Exp4.etq = Exp4.etqh + 1 
	 * Exp4.cod =apila_false()
	 */
	public class Exp4R2 extends Exp4 {

		public Exp4R2() {
			 this.hay_cod = false;
			 this.hay_etq = false;
		}

		public String regla() {
			return " Exp4 ::= false | Exp4";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod = single(apila_false());
			 }
			 return cod;
//			if (!hay_cod) {
//				cod = new ArrayList<Instruccion>();
//				cod.add(Instruccion.nuevaIApilaFalse());
//				hay_cod = true;
//			}
//			return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq =this.etqh() + 1;
			 }
			 return etq;
		
		}

		public CatLexica tipo() {
			return CatLexica.BOOLEAN;
		}

		private int etq;
		private boolean hay_etq;
		private List<Instruccion> cod;
		private boolean hay_cod;
	}

	public class Exp4R2Debug extends Exp4R2 {
		public Exp4R2Debug() {
			super();
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * 
	 * Exp4 ::= NUM 
	 * Exp4.tipo = int 
	 * Exp4.etq = Exp4.etqh + 1 
	 * Exp4.cod =apila_int(NUM.lex)
	 */
	public class Exp4R3 extends Exp4 {

		public Exp4R3(Token num) {
			this.num = num;
			this.hay_cod = false;
			this.hay_etq = false;
		}

		public String regla() {
			return " Exp4 ::= NUM | Exp4";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
			 hay_cod = true;
			 cod = single(apila_int(num.leeLexema()));
			 }
			 return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq =this.etqh() + 1;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return CatLexica.INT;
		}

		private int etq;
		private boolean hay_etq;
		private List<Instruccion> cod;
		private boolean hay_cod;
		private Token num;
	}

	public class Exp4R3Debug extends Exp4R3 {
		public Exp4R3Debug(Token num) {
			super(num);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Exp4 ::= IDEN 
	 * Exp4.tipo = tipoDe(IDEN.lex,Exp4.tsh) 
	 * Exp4.etq = Exp4.etqh+ 1 
	 * Exp4.cod = apila_dir(dirDe(IDEN.lex,Exp4.tsh))
	 */
	public class Exp4R4 extends Exp4 {

		public Exp4R4(Token iden) {
			this.iden = iden;
			 this.hay_cod = false;
			 this.hay_etq = false;
		}

		public String regla() {
			return " Exp4 ::= IDEN | Exp4";
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
			 hay_cod = true;
			 cod = single(apila_dir(String.valueOf(dirDe(iden.leeLexema(),
			 this.tsh()))));
			 }
			 return cod;
		}

		public int etq() {
			 if (!hay_etq){
				 etq =this.etqh() + 1;
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return tipoDe(iden.leeLexema(), tsh());
		}

		private int etq;
		private boolean hay_etq;
		private List<Instruccion> cod;
		private boolean hay_cod;
		private Token iden;

	}

	public class Exp4R4Debug extends Exp4R4 {
		public Exp4R4Debug(Token iden) {
			super(iden);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	/*
	 * Exp4 ::= ( Exp0 )
	 * 
	 * Exp0.tsh = Exp4.tsh Exp4.tipo = Exp0.tipo 
	 * Exp0.etqh = Exp4.etqh 
	 * Exp4.etq = Exp0.etq 
	 * Exp4.cod = Exp0.cod
	 */
	public class Exp4R5 extends Exp4 {

		public Exp4R5(Exp0 exp0) {
			this.exp0 = exp0;
			this.hay_cod = false;
			this.hay_etq = false;
			exp0.registraCtx(new Exp0Ctx() {
				public TS tsh() {
					return Exp4R5.this.tsh();
				}

				public int etqh() {
					return Exp4R5.this.etqh();
				}

				public String regla() {
					return " Exp4 ::= ( Exp0 ) | Exp0";
				}
			});
		}

		public String regla() {
			return " Exp4 ::= ( Exp0 ) | Exp4";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=exp0.cod();
			 }
			 return cod;
		}

		public int etq() {
			 if (!hay_etq){
			 etq =exp0.etq();
			 }
			 return etq;
		}

		public CatLexica tipo() {
			return exp0.tipo();
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
		private Exp0 exp0;
		private int etq;
		private boolean hay_etq;

	}

	public class Exp4R5Debug extends Exp4R5 {
		public Exp4R5Debug(Exp0 exp0) {
			super(exp0);
		}

		public Error err() {
			requerido(regla() + ".err");
			return valor(regla() + ".err", new Error(tipo().toString()));
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}

	}

	/*
	 * OpComparacion ::= eq 
	 * Comprobaci—n de las restricciones contextuales
	 * OpComparacion.op = eq 
	 * Generaci—n de c—digo 
	 * OpComparacion.cod = eq()
	 */
	public class OpComparacionR1 implements OpComparacion {

		public OpComparacionR1() {
			this.hay_cod = false;
		}

		public String regla() {
			return " OpComparacion ::= eq | OpComparacion";
		}

		public CatLexica op() {
			return CatLexica.EQ;
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
			 hay_cod = true;
			 cod=single(eq());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
	}

	public class OpComparacionR1Debug extends OpComparacionR1 {
		public OpComparacionR1Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpComparacion ::= eq | OpComparacion";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpComparacionR2 implements OpComparacion {

		public OpComparacionR2() {
			this.hay_cod = false;
		}

		public CatLexica op() {
			return CatLexica.NEQ;
		}

		public String regla() {
			return " OpComparacion ::= neq | OpComparacion";
		}

		public List<Instruccion> cod() {
			if (!hay_cod) {
				hay_cod = true;
				cod = single(neq());
			}
			return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
	}

	public class OpComparacionR2Debug extends OpComparacionR2 {
		public OpComparacionR2Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpComparacion ::= neq | OpComparacion";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpComparacionR3 implements OpComparacion {

		public OpComparacionR3() {
			this.hay_cod = false;
		}

		public String regla() {
			return " OpComparacion ::= gt | OpComparacion";
		}

		public CatLexica op() {
			return CatLexica.GT;
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(gt());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
	}

	public class OpComparacionR3Debug extends OpComparacionR3 {
		public OpComparacionR3Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpComparacion ::= gt | OpComparacion";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpComparacionR4 implements OpComparacion {

		public OpComparacionR4() {
			this.hay_cod = false;
		}

		public CatLexica op() {
			return CatLexica.GE;
		}

		public String regla() {
			return " OpComparacion ::= ge | OpComparacion";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(ge());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
	}

	public class OpComparacionR4Debug extends OpComparacionR4 {
		public OpComparacionR4Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpComparacion ::= ge | OpComparacion";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpComparacionR5 implements OpComparacion {

		public OpComparacionR5() {
			this.hay_cod = false;
		}

		public String regla() {
			return " OpComparacion ::= lt | OpComparacion";
		}

		public CatLexica op() {
			return CatLexica.LT;
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(lt());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;

	}

	public class OpComparacionR5Debug extends OpComparacionR5 {
		public OpComparacionR5Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpComparacion ::= lt | OpComparacion";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpComparacionR6 implements OpComparacion {

		public OpComparacionR6() {
			this.hay_cod = false;
		}

		public CatLexica op() {
			return CatLexica.LE;
		}

		public String regla() {
			return " OpComparacion ::= le | OpComparacion";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(le());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
	}

	public class OpComparacionR6Debug extends OpComparacionR6 {
		public OpComparacionR6Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpComparacion ::= le | OpComparacion";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpAditivoR1 implements OpAditivo {

		public OpAditivoR1() {
			this.hay_cod = false;
		}

		public String regla() {
			return " OpAditivo ::= + | OpAditivo";
		}

		public CatLexica op() {
			return CatLexica.MAS;
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(suma());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;

	}

	public class OpAditivoR1Debug extends OpAditivoR1 {
		public OpAditivoR1Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpAditivo ::= + | OpAditivo";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpAditivoR2 implements OpAditivo {

		public OpAditivoR2() {
			this.hay_cod = false;
		}

		public CatLexica op() {
			return CatLexica.MENOS;
		}

		public String regla() {
			return " OpAditivo ::= - | OpAditivo";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(resta());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;

	}

	public class OpAditivoR2Debug extends OpAditivoR2 {
		public OpAditivoR2Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpAditivo ::= - | OpAditivo";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpAditivoR3 implements OpAditivo {

		public OpAditivoR3() {
			this.hay_cod = false;
		}

		public CatLexica op() {
			return CatLexica.OR;
		}

		public String regla() {
			return " OpAditivo ::= or | OpAditivo";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(or());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
	}

	public class OpAditivoR3Debug extends OpAditivoR3 {
		public OpAditivoR3Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpAditivo ::= or | OpAditivo";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpMultiplicativoR1 implements OpMultiplicativo {

		public OpMultiplicativoR1() {
			this.hay_cod = false;
		}

		public String regla() {
			return " OpMultiplicativo ::= * | OpMultiplicativo";
		}

		public CatLexica op() {
			return CatLexica.ASTERISCO;
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(mul());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;

	}

	public class OpMultiplicativoR1Debug extends OpMultiplicativoR1 {
		public OpMultiplicativoR1Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpMultiplicativo ::= * | OpMultiplicativo";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpMultiplicativoR2 implements OpMultiplicativo {

		public OpMultiplicativoR2() {
			this.hay_cod = false;
		}

		public CatLexica op() {
			return CatLexica.BARRA;
		}

		public String regla() {
			return " OpMultiplicativo ::= / | OpMultiplicativo";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
			 hay_cod = true;
			 cod=single(div());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;

	}

	public class OpMultiplicativoR2Debug extends OpMultiplicativoR2 {
		public OpMultiplicativoR2Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpMultiplicativo ::= / | OpMultiplicativo";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpMultiplicativoR3 implements OpMultiplicativo {

		public OpMultiplicativoR3() {
			this.hay_cod = false;
		}

		public CatLexica op() {
			return CatLexica.AND;
		}

		public String regla() {
			return " OpMultiplicativo ::= and | OpMultiplicativo";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(and());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;

	}

	public class OpMultiplicativoR3Debug extends OpMultiplicativoR3 {
		public OpMultiplicativoR3Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpMultiplicativo ::= and | OpMultiplicativo";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpUnarioR1 implements OpUnario {

		public OpUnarioR1() {
			this.hay_cod = false;
		}

		public CatLexica op() {
			return CatLexica.MENOS;
		}

		public String regla() {
			return " OpUnario ::= - | OpUnario";
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(menos());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;
	}

	public class OpUnarioR1Debug extends OpUnarioR1 {
		public OpUnarioR1Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpUnario ::= - | OpUnario";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
		}
	}

	public class OpUnarioR2 implements OpUnario {

		public OpUnarioR2() {
			this.hay_cod = false;
		}

		public String regla() {
			return " OpUnario ::= not | OpUnario";
		}

		public CatLexica op() {
			return CatLexica.NOT;
		}

		public List<Instruccion> cod() {
			 if (!hay_cod) {
				 hay_cod = true;
				 cod=single(not());
			 }
			 return cod;
		}

		private List<Instruccion> cod;
		private boolean hay_cod;

	}

	public class OpUnarioR2Debug extends OpUnarioR2 {
		public OpUnarioR2Debug() {
			super();
		}

		public CatLexica op() {
			return super.op();
		}

		public String regla() {
			return " OpUnario ::= not | OpUnario";
		}

		public List<Instruccion> cod() {
			return valor(regla() + ".cod=", super.cod());
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
					|| op.equals(CatLexica.ASTERISCO) || op.equals(CatLexica.BARRA))
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

	private void requerido(String ctx) {
		identado++;
		identa();
		System.out.println("REQUERIDO " + ctx);
	}

	private <O> O valor(String ctx, O val) {
		identa();
		System.out.println("VALOR DE " + ctx + ":" + val);
		identado--;
		return val;
	}

	private void identa() {
		for (int i = 0; i < identado; i++)
			System.out.print(".");
	}

}
