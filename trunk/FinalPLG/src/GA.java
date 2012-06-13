import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GA {
	// private int identado;

	public GA() {
		// identado = -1;
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

	// NUEVA CLASE PARA BLOQUE (NUEVA ESPECIF)
	abstract public class Bloque {
		protected Bloque() {
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
			dirh = new Atributo<Integer>();
			dirh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.dirh_exp();
				}
			});
			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.nivelh_exp();
				}
			});
			dirInicio = new Atributo<Integer>();
			dirInicio.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return dirInicio_exp();
				}
			});
			dirSalto = new Atributo<Integer>();
			dirSalto.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return dirSalto_exp();
				}
			});
			llamadasPendientes = new Atributo<List<Integer>>();
			llamadasPendientes.fijaExpresion(new ExpSem<List<Integer>>() {
				public List<Integer> val() {
					return llamadasPendientes_exp();
				}
			});
			anidamiento = new Atributo<Integer>();
			anidamiento.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return anidamiento_exp();
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

		public Atributo<Integer> dirh() {
			return dirh;
		}

		public Atributo<Integer> nivelh() {
			return nivelh;
		}

		public Atributo<Integer> dirInicio() {
			return dirInicio;
		}

		public Atributo<Integer> dirSalto() {
			return dirSalto;
		}

		public Atributo<List<Integer>> llamadasPendientes() {
			return llamadasPendientes;
		}

		public Atributo<Integer> anidamiento() {
			return anidamiento;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		protected abstract Integer dirInicio_exp();

		protected abstract Integer dirSalto_exp();

		protected abstract List<Integer> llamadasPendientes_exp();

		protected abstract Integer anidamiento_exp();

		private Atributo<Error> err;
		private Atributo<List<Instruccion>> cod;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<Integer> dirh;
		private Atributo<Integer> nivelh;
		private Atributo<Integer> dirInicio;
		private Atributo<Integer> dirSalto;
		private Atributo<List<Integer>> llamadasPendientes;
		private Atributo<Integer> anidamiento;
		private Atributo<TS> tsh;
		private BloqueCtx ctx;

		public void registraCtx(BloqueCtx ctx) {
			this.ctx = ctx;
		}

		public BloqueCtx contexto() {
			return ctx;
		}
	}

	public interface BloqueCtx {
		public TS tsh_exp();

		public int etqh_exp();

		public int nivelh_exp();

		public int dirh_exp();
	}

	public abstract class Decs {
		protected Decs() {
			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
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
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			anidamiento = new Atributo<Integer>();
			anidamiento.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return anidamiento_exp();
				}
			});
			refsAChequear = new Atributo<List<ExpTipo>>();
			refsAChequear.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return refsAChequear_exp();
				}
			});
			dirh = new Atributo<Integer>();
			dirh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.dirh_exp();
				}
			});
			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.nivelh_exp();
				}
			});
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.etqh_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
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

		public Atributo<Integer> etq() {
			return etq;
		}

		public Atributo<Integer> anidamiento() {
			return anidamiento;
		}

		public Atributo<List<ExpTipo>> refsAChequear() {
			return refsAChequear;
		}

		// Heredados
		public Atributo<Integer> etqh() {
			return etqh;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<Integer> dirh() {
			return dirh;
		}

		public Atributo<Integer> nivelh() {
			return nivelh;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		protected abstract List<Instruccion> cod_exp();

		protected abstract Error err_exp();

		protected abstract TS ts_exp();

		protected abstract Integer etq_exp();

		protected abstract Integer dir_exp();

		protected abstract Integer anidamiento_exp();

		protected abstract List<ExpTipo> refsAChequear_exp();

		private Atributo<Integer> nivelh;
		private Atributo<Integer> dirh;
		private Atributo<Integer> etqh;
		private Atributo<TS> tsh;

		private Atributo<List<Instruccion>> cod;
		private Atributo<List<ExpTipo>> refsAChequear;
		private Atributo<Integer> anidamiento;
		private Atributo<Integer> etq;
		private Atributo<Error> err;
		private Atributo<TS> ts;
		private Atributo<Integer> dir;
		private DecsCtx ctx;

		public void registraCtx(DecsCtx ctx) {
			this.ctx = ctx;
		}

		public DecsCtx contexto() {
			return ctx;
		}
	}

	public interface DecsCtx {
		public TS tsh_exp();

		public int etqh_exp();

		public int nivelh_exp();

		public int dirh_exp();
	}

	public abstract class Dec {
		protected Dec() {
			iden = new Atributo<String>();
			iden.fijaExpresion(new ExpSem<String>() {
				public String val() {
					return iden_exp();
				}
			});
			tipo = new Atributo<ExpTipo>();
			tipo.fijaExpresion(new ExpSem<ExpTipo>() {
				public ExpTipo val() {
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
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			tam = new Atributo<Integer>();
			tam.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return tam_exp();
				}
			});
			refsAChequear = new Atributo<List<ExpTipo>>();
			refsAChequear.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return refsAChequear_exp();
				}
			});
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
			clase = new Atributo<CatLexica>();
			clase.fijaExpresion(new ExpSem<CatLexica>() {
				public CatLexica val() {
					return clase_exp();
				}
			});
			anidamiento = new Atributo<Integer>();
			anidamiento.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return anidamiento_exp();
				}
			});
			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.nivelh_exp();
				}
			});
			etqh = new Atributo<Integer>();
			etqh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.etqh_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
		}

		public Atributo<ExpTipo> tipo() {
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

		public Atributo<Integer> tam() {
			return tam;
		}

		public Atributo<List<ExpTipo>> refsAChequear() {
			return refsAChequear;
		}

		public Atributo<Integer> etq() {
			return etq;
		}

		public Atributo<Integer> anidamiento() {
			return anidamiento;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<CatLexica> clase() {
			return clase;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		public Atributo<Integer> nivelh() {
			return nivelh;
		}

		public Atributo<Integer> etqh() {
			return etqh;
		}

		protected abstract String iden_exp();

		protected abstract Integer fila_exp();

		protected abstract Integer col_exp();

		protected abstract ExpTipo tipo_exp();

		protected abstract Integer tam_exp();

		protected abstract List<ExpTipo> refsAChequear_exp();

		protected abstract Integer etq_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer anidamiento_exp();

		protected abstract Error err_exp();

		protected abstract CatLexica clase_exp();

		private Atributo<String> iden;
		private Atributo<ExpTipo> tipo;
		private Atributo<Integer> fila;
		private Atributo<Integer> col;
		private Atributo<Integer> tam;
		private Atributo<List<ExpTipo>> refsAChequear;
		private Atributo<Error> err;
		private Atributo<Integer> etq;
		private Atributo<List<Instruccion>> cod;
		private Atributo<Integer> anidamiento;
		private Atributo<CatLexica> clase;

		private Atributo<Integer> nivelh;
		private Atributo<TS> tsh;
		private Atributo<Integer> etqh;
		private DecCtx ctx;

		public void registraCtx(DecCtx ctx) {
			this.ctx = ctx;
		}

		public DecCtx contexto() {
			return ctx;
		}
	}

	public interface DecCtx {
		public TS tsh_exp();

		public int etqh_exp();

		public int nivelh_exp();
	}

	public abstract class ParamsFormales {
		protected ParamsFormales() {
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
			refsAChequear = new Atributo<List<ExpTipo>>();
			refsAChequear.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return refsAChequear_exp();
				}
			});
			params = new Atributo<List<ExpTipo>>();
			params.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return params_exp();
				}
			});

			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.nivelh_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
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

		public Atributo<List<ExpTipo>> refsAChequear() {
			return refsAChequear;
		}

		public Atributo<List<ExpTipo>> params() {
			return params;
		}

		public Atributo<Integer> nivelh() {
			return nivelh;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		protected abstract Error err_exp();

		protected abstract TS ts_exp();

		protected abstract Integer dir_exp();

		protected abstract List<ExpTipo> params_exp();

		protected abstract List<ExpTipo> refsAChequear_exp();

		private Atributo<Integer> nivelh;
		private Atributo<TS> tsh;

		private Atributo<List<ExpTipo>> params;
		private Atributo<List<ExpTipo>> refsAChequear;
		private Atributo<Error> err;
		private Atributo<TS> ts;
		private Atributo<Integer> dir;
		private ParamsFormalesCtx ctx;

		public void registraCtx(ParamsFormalesCtx ctx) {
			this.ctx = ctx;
		}

		public ParamsFormalesCtx contexto() {
			return ctx;
		}
	}

	public interface ParamsFormalesCtx {
		public TS tsh_exp();

		public int nivelh_exp();
	}

	public abstract class ListaParamsFormales {
		protected ListaParamsFormales() {
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
			refsAChequear = new Atributo<List<ExpTipo>>();
			refsAChequear.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return refsAChequear_exp();
				}
			});
			params = new Atributo<List<ExpTipo>>();
			params.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return params_exp();
				}
			});

			nivelh = new Atributo<Integer>();
			nivelh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.nivelh_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
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

		public Atributo<List<ExpTipo>> params() {
			return params;
		}

		public Atributo<List<ExpTipo>> refsAChequear() {
			return refsAChequear;
		}

		public Atributo<Integer> nivelh() {
			return nivelh;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		protected abstract Error err_exp();

		protected abstract TS ts_exp();

		protected abstract Integer dir_exp();

		protected abstract List<ExpTipo> params_exp();

		protected abstract List<ExpTipo> refsAChequear_exp();

		private Atributo<Integer> nivelh;
		private Atributo<TS> tsh;

		private Atributo<List<ExpTipo>> params;
		private Atributo<List<ExpTipo>> refsAChequear;
		private Atributo<Error> err;
		private Atributo<TS> ts;
		private Atributo<Integer> dir;
		private ListaParamsFormalesCtx ctx;

		public void registraCtx(ListaParamsFormalesCtx ctx) {
			this.ctx = ctx;
		}

		public ListaParamsFormalesCtx contexto() {
			return ctx;
		}
	}

	public interface ListaParamsFormalesCtx {
		public TS tsh_exp();

		public int nivelh_exp();
	}

	public abstract class ParamFormal {
		protected ParamFormal() {
			iden = new Atributo<String>();
			iden.fijaExpresion(new ExpSem<String>() {
				public String val() {
					return iden_exp();
				}
			});
			tipo = new Atributo<ExpTipo>();
			tipo.fijaExpresion(new ExpSem<ExpTipo>() {
				public ExpTipo val() {
					return tipo_exp();
				}
			});
			tam = new Atributo<Integer>();
			tam.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return tam_exp();
				}
			});
			refsAChequear = new Atributo<List<ExpTipo>>();
			refsAChequear.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return refsAChequear_exp();
				}
			});
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			param = new Atributo<ExpTipo>();
			param.fijaExpresion(new ExpSem<ExpTipo>() {
				public ExpTipo val() {
					return param_exp();
				}
			});
			clase = new Atributo<CatLexica>();
			clase.fijaExpresion(new ExpSem<CatLexica>() {
				public CatLexica val() {
					return clase_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
		}

		public Atributo<ExpTipo> tipo() {
			return tipo;
		}

		public Atributo<String> iden() {
			return iden;
		}

		public Atributo<Integer> tam() {
			return tam;
		}

		public Atributo<List<ExpTipo>> refsAChequear() {
			return refsAChequear;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<ExpTipo> param() {
			return param;
		}

		public Atributo<CatLexica> clase() {
			return clase;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		protected abstract String iden_exp();

		protected abstract ExpTipo tipo_exp();

		protected abstract ExpTipo param_exp();

		protected abstract Integer tam_exp();

		protected abstract List<ExpTipo> refsAChequear_exp();

		protected abstract Error err_exp();

		protected abstract CatLexica clase_exp();

		private Atributo<String> iden;
		private Atributo<ExpTipo> tipo;
		private Atributo<Integer> tam;
		private Atributo<List<ExpTipo>> refsAChequear;
		private Atributo<Error> err;
		private Atributo<ExpTipo> param;
		private Atributo<CatLexica> clase;

		private Atributo<TS> tsh;
		private ParamFormalCtx ctx;

		public void registraCtx(ParamFormalCtx ctx) {
			this.ctx = ctx;
		}

		public ParamFormalCtx contexto() {
			return ctx;
		}
	}

	public interface ParamFormalCtx {
		public TS tsh_exp();
	}

	public abstract class ParamsReales {
		protected ParamsReales() {
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			nparams = new Atributo<Integer>();
			nparams.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return nparams_exp();
				}
			});
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
			subProgramah = new Atributo<String>();
			subProgramah.fijaExpresion(new ExpSem<String>() {
				public String val() {
					return ctx.subprogramah_exp();
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
		}

		public Atributo<Integer> nparams() {
			return nparams;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<Integer> etq() {
			return etq;
		}

		public Atributo<String> subProgramah() {
			return subProgramah;
		}

		public Atributo<Integer> etqh() {
			return etqh;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		protected abstract Error err_exp();

		protected abstract Integer nparams_exp();

		protected abstract Integer etq_exp();

		protected abstract List<Instruccion> cod_exp();

		private Atributo<String> subProgramah;
		private Atributo<TS> tsh;
		private Atributo<Integer> etqh;

		private Atributo<List<Instruccion>> cod;
		private Atributo<Error> err;
		private Atributo<Integer> etq;
		private Atributo<Integer> nparams;
		private ParamsRealesCtx ctx;

		public void registraCtx(ParamsRealesCtx ctx) {
			this.ctx = ctx;
		}

		public ParamsRealesCtx contexto() {
			return ctx;
		}
	}

	public interface ParamsRealesCtx {
		public TS tsh_exp();

		public String subprogramah_exp();

		public int etqh_exp();
	}

	public abstract class ListaParamsReales {
		protected ListaParamsReales() {
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			nparams = new Atributo<Integer>();
			nparams.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return nparams_exp();
				}
			});
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			cod = new Atributo<List<Instruccion>>();
			cod.fijaExpresion(new ExpSem<List<Instruccion>>() {
				public List<Instruccion> val() {
					return cod_exp();
				}
			});
			subProgramah = new Atributo<String>();
			subProgramah.fijaExpresion(new ExpSem<String>() {
				public String val() {
					return ctx.subprogramah_exp();
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
		}

		public Atributo<Integer> nparams() {
			return nparams;
		}

		public Atributo<Integer> etq() {
			return etq;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<String> subProgramah() {
			return subProgramah;
		}

		public Atributo<Integer> etqh() {
			return etqh;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		protected abstract Error err_exp();

		protected abstract Integer nparams_exp();

		protected abstract Integer etq_exp();

		protected abstract List<Instruccion> cod_exp();

		private Atributo<String> subProgramah;
		private Atributo<TS> tsh;
		private Atributo<Integer> etqh;

		private Atributo<List<Instruccion>> cod;
		private Atributo<Error> err;
		private Atributo<Integer> etq;
		private Atributo<Integer> nparams;
		private ListaParamsRealesCtx ctx;

		public void registraCtx(ListaParamsRealesCtx ctx) {
			this.ctx = ctx;
		}

		public ListaParamsRealesCtx contexto() {
			return ctx;
		}
	}

	public interface ListaParamsRealesCtx {
		public TS tsh_exp();

		public String subprogramah_exp();

		public int etqh_exp();
	}

	// Tipo
	public abstract class Tipo {
		protected Tipo() {
			tipo = new Atributo<ExpTipo>();
			tipo.fijaExpresion(new ExpSem<ExpTipo>() {
				public ExpTipo val() {
					return tipo_exp();
				}
			});
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			refsAChequear = new Atributo<List<ExpTipo>>();
			refsAChequear.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return refsAChequear_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
		}

		protected abstract Error err_exp();

		protected abstract List<ExpTipo> refsAChequear_exp();

		protected abstract ExpTipo tipo_exp();

		public Atributo<List<ExpTipo>> refsAChequear() {
			return refsAChequear;
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<ExpTipo> tipo() {
			return tipo;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		protected Atributo<ExpTipo> tipo;
		private Atributo<Error> err;
		private Atributo<List<ExpTipo>> refsAChequear;
		private Atributo<TS> tsh;
		private TipoCtx ctx;

		public void registraCtx(TipoCtx ctx) {
			this.ctx = ctx;
		}

		public TipoCtx contexto() {
			return ctx;
		}
	}

	public interface TipoCtx {
		public TS tsh_exp();
	}

	public abstract class Campos {
		protected Campos() {
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			refsAChequear = new Atributo<List<ExpTipo>>();
			refsAChequear.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return refsAChequear_exp();
				}
			});
			tam = new Atributo<Integer>();
			tam.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return tam_exp();
				}
			});
			campos = new Atributo<List<ExpTipo>>();
			campos.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return campos_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
		}

		protected abstract Error err_exp();

		protected abstract List<ExpTipo> refsAChequear_exp();

		protected abstract Integer tam_exp();

		protected abstract List<ExpTipo> campos_exp();

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<Integer> tam() {
			return tam;
		}

		public Atributo<List<ExpTipo>> campos() {
			return campos;
		}

		public Atributo<List<ExpTipo>> refsAChequear() {
			return refsAChequear;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		protected Atributo<List<ExpTipo>> campos;

		private Atributo<Error> err;
		private Atributo<List<ExpTipo>> refsAChequear;
		private Atributo<Integer> tam;

		private Atributo<TS> tsh;
		private CamposCtx ctx;

		public void registraCtx(CamposCtx ctx) {
			this.ctx = ctx;
		}

		public CamposCtx contexto() {
			return ctx;
		}
	}

	public interface CamposCtx {
		public TS tsh_exp();

		// public Integer desplazamientoh_exp();
	}

	public abstract class Campo {
		protected Campo() {
			err = new Atributo<Error>();
			err.fijaExpresion(new ExpSem<Error>() {
				public Error val() {
					return err_exp();
				}
			});
			refsAChequear = new Atributo<List<ExpTipo>>();
			refsAChequear.fijaExpresion(new ExpSem<List<ExpTipo>>() {
				public List<ExpTipo> val() {
					return refsAChequear_exp();
				}
			});
			tam = new Atributo<Integer>();
			tam.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return tam_exp();
				}
			});
			campo = new Atributo<ExpTipo>();
			campo.fijaExpresion(new ExpSem<ExpTipo>() {
				public ExpTipo val() {
					return campo_exp();
				}
			});
			desplazamientoh = new Atributo<Integer>();
			desplazamientoh.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return ctx.desplazamientoh_exp();
				}
			});
			tsh = new Atributo<TS>();
			tsh.fijaExpresion(new ExpSem<TS>() {
				public TS val() {
					return ctx.tsh_exp();
				}
			});
			iden = new Atributo<String>();
			iden.fijaExpresion(new ExpSem<String>() {
				public String val() {
					return iden_exp();
				}
			});

		}

		protected abstract Error err_exp();

		protected abstract List<ExpTipo> refsAChequear_exp();

		protected abstract Integer tam_exp();

		protected abstract ExpTipo campo_exp();

		protected abstract String iden_exp();

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<Integer> tam() {
			return tam;
		}

		public Atributo<ExpTipo> campo() {
			return campo;
		}

		public Atributo<List<ExpTipo>> refsAChequear() {
			return refsAChequear;
		}

		public Atributo<String> iden() {
			return iden;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		public Atributo<Integer> desplazamientoh() {
			return desplazamientoh;
		}

		protected Atributo<ExpTipo> campo;
		private Atributo<Error> err;
		private Atributo<List<ExpTipo>> refsAChequear;
		private Atributo<Integer> tam;
		private Atributo<String> iden;

		private Atributo<Integer> desplazamientoh;
		private Atributo<TS> tsh;
		private CampoCtx ctx;

		public void registraCtx(CampoCtx ctx) {
			this.ctx = ctx;
		}

		public CampoCtx contexto() {
			return ctx;
		}
	}

	public interface CampoCtx {
		public TS tsh_exp();

		public Integer desplazamientoh_exp();
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
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			llamadasPendientes = new Atributo<List<Integer>>();
			llamadasPendientes.fijaExpresion(new ExpSem<List<Integer>>() {
				public List<Integer> val() {
					return llamadasPendientes_exp();
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
		}

		public Atributo<Error> err() {
			return err;
		}

		public Atributo<List<Instruccion>> cod() {
			return cod;
		}

		public Atributo<Integer> etq() {
			return etq;
		}

		public Atributo<List<Integer>> llamadasPendientes() {
			return llamadasPendientes;
		}

		public Atributo<Integer> etqh() {
			return etqh;
		}

		public Atributo<TS> tsh() {
			return tsh;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		protected abstract List<Integer> llamadasPendientes_exp();

		private Atributo<Error> err;
		private Atributo<List<Instruccion>> cod;
		private Atributo<List<Integer>> llamadasPendientes;
		private Atributo<Integer> etq;
		private Atributo<Integer> etqh;
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
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			llamadasPendientes = new Atributo<List<Integer>>();
			llamadasPendientes.fijaExpresion(new ExpSem<List<Integer>>() {
				public List<Integer> val() {
					return llamadasPendientes_exp();
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

		public Atributo<List<Integer>> llamadasPendientes() {
			return llamadasPendientes;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		protected abstract List<Integer> llamadasPendientes_exp();

		private Atributo<List<Integer>> llamadasPendientes;
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
			etq = new Atributo<Integer>();
			etq.fijaExpresion(new ExpSem<Integer>() {
				public Integer val() {
					return etq_exp();
				}
			});
			llamadasPendientes = new Atributo<List<Integer>>();
			llamadasPendientes.fijaExpresion(new ExpSem<List<Integer>>() {
				public List<Integer> val() {
					return llamadasPendientes_exp();
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

		public Atributo<List<Integer>> llamadasPendientes() {
			return llamadasPendientes;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		protected abstract List<Integer> llamadasPendientes_exp();

		private Atributo<List<Integer>> llamadasPendientes;
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
			llamadasPendientes = new Atributo<List<Integer>>();
			llamadasPendientes.fijaExpresion(new ExpSem<List<Integer>>() {
				public List<Integer> val() {
					return llamadasPendientes_exp();
				}
			});
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

		public Atributo<List<Integer>> llamadasPendientes() {
			return llamadasPendientes;
		}

		protected abstract Error err_exp();

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		protected abstract List<Integer> llamadasPendientes_exp();

		private Atributo<List<Integer>> llamadasPendientes;
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
			tipo = new Atributo<ExpTipo>();
			tipo.fijaExpresion(new ExpSem<ExpTipo>() {
				public ExpTipo val() {
					return tipo_exp();
				}
			});

			esDesignador = new Atributo<Boolean>();
			esDesignador.fijaExpresion(new ExpSem<Boolean>() {
				public Boolean val() {
					return esDesignador_exp();
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

		public Atributo<ExpTipo> tipo() {
			return tipo;
		}

		public Atributo<Boolean> esDesignador() {
			return esDesignador;
		}

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		protected abstract ExpTipo tipo_exp();

		protected abstract Boolean esDesignador_exp();

		private Atributo<List<Instruccion>> cod;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<TS> tsh;
		private Atributo<ExpTipo> tipo;
		private Atributo<Boolean> esDesignador;
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

	abstract public class Mem {

		protected Mem() {
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
			tipo = new Atributo<ExpTipo>();
			tipo.fijaExpresion(new ExpSem<ExpTipo>() {
				public ExpTipo val() {
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

		public Atributo<ExpTipo> tipo() {
			return tipo;
		}

		protected abstract List<Instruccion> cod_exp();

		protected abstract Integer etq_exp();

		protected abstract ExpTipo tipo_exp();

		private Atributo<List<Instruccion>> cod;
		private Atributo<Integer> etqh;
		private Atributo<Integer> etq;
		private Atributo<TS> tsh;
		private Atributo<ExpTipo> tipo;
		private MemCtx ctx;

		public void registraCtx(MemCtx ctx) {
			this.ctx = ctx;
		}

		public MemCtx contexto() {
			return ctx;
		}
	}

	public interface MemCtx {
		public TS tsh_exp();

		public Integer etqh_exp();
	}

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
	 * Programa ::= Bloque
	 *  Bloque.tsh = creaTS()
	    Bloque.nivelh = 0
		Programa.error = Bloque.error
		Bloque.etqh=numeroInstruccionesActivacionProgramaPrincipal()
		Bloque.dirh=0
		Programa.cod = codigoActivacionProgramaPrincipal(Bloque.dirInicio, Bloque.anidamiento,Bloque.dirSalto) || Bloque.cod
	 * </code>
	 */
	public class ProgR1 extends Programa {

		public ProgR1(Bloque bloque) {
			super();
			this.bloque = bloque;

			bloque.registraCtx(new BloqueCtx() {
				public TS tsh_exp() {
					return creaTS();
				}

				public int etqh_exp() {
					// return 0;
					return numeroInstruccionesActivacionProgramaPrincipal();
				}

				@Override
				public int nivelh_exp() {
					return 0;
				}

				@Override
				public int dirh_exp() {
					return 0;
					// return numeroInstruccionesActivacionProgramaPrincipal();
				}
			});

			err().ponDependencias(bloque.err());
			cod().ponDependencias(bloque.dirInicio(), bloque.dirSalto(),
					bloque.anidamiento(), bloque.cod());
		}

		protected final Error err_exp() {
			return bloque.err().val();
		}

		protected final List<Instruccion> cod_exp() {
			return concat(
					codigoActivacionProgramaPrincipal(bloque.dirInicio().val(),
							bloque.anidamiento().val(), bloque.dirSalto().val()),
					bloque.cod().val());
		}

		private Bloque bloque;

	}

	public class ProgR1Debug extends ProgR1 {
		private final static String REGLA = "Programa ::= Bloque";

		public ProgR1Debug(Bloque bloque) {
			super(bloque);
			err().fijaDescripcion(REGLA + " | Programa.err");
			cod().fijaDescripcion(REGLA + " | Programa.cod");
			bloque.tsh().fijaDescripcion(REGLA + " | Bloque.tsh");
			bloque.dirh().fijaDescripcion(REGLA + " | Bloque.dirh");
			bloque.etqh().fijaDescripcion(REGLA + " | Bloque.eqth");
			bloque.nivelh().fijaDescripcion(REGLA + " | Bloque.nivelh");
		}

	}

	/**
	 * <code>
	 * Bloque ::= Declaraciones & Instrucciones
	 *  Declaraciones.tsh = Bloque.tsh
	 * 	Declaraciones.dirh = Bloque.dirh
	 * 	Declaraciones.nivelh = Bloque.nivelh
	 * 	Instrucciones.tsh = Declaraciones.ts
	 * 	Bloque.error = Declaraciones.error or Instrucciones.error or 
	 * 				tiposNoDeclarados(Declaraciones.refsAChequear,Declaraciones.ts)
	 * 	Declaraciones.etqh = Bloque.etqh
	 * 	Instrucciones.etqh=Declaraciones.etq + numeroInstruccionesPrologo()
	 * 	Bloque.etq = Instrucciones.etq + numeroInstruccionesEpilogo()
	 * 	Bloque.dirInicio = Declaraciones.dir
	 *  Bloque.dirSalto = Declaraciones.etq
	 * 	Bloque.cod = Declaraciones.cod || codigoPrologo(Declaraciones.dir,Bloque.nivelh) || Instrucciones.cod ||
	 * 				codigoEpilogo(Declaraciones.dir,Bloque.nivelh)
	 * 	Bloque.llamadasPendientes = Instrucciones.llamadasPendientes
	 * 	Bloque.anidamiento = Declaraciones.anidamiento+1
	 * </code>
	 */
	public class BloqueR1 extends Bloque {

		public BloqueR1(Decs decs, Insts insts) {
			super();
			this.decs = decs;
			this.insts = insts;

			insts.registraCtx(new InstsCtx() {
				public TS tsh_exp() {
					return BloqueR1.this.decs.ts().val();
				}

				public int etqh_exp() {
					return BloqueR1.this.decs.etq().val()
							+ numeroInstruccionesPrologo();
				}
			});
			decs.registraCtx(new DecsCtx() {
				public TS tsh_exp() {
					return BloqueR1.this.tsh().val();
				}

				public int etqh_exp() {
					return BloqueR1.this.etqh().val();
				}

				@Override
				public int nivelh_exp() {
					return BloqueR1.this.nivelh().val();
				}

				@Override
				public int dirh_exp() {
					return BloqueR1.this.dirh().val();
				}
			});

			// TODO cambie orden de decs.ts antes que dec.err
			err().ponDependencias(insts.err(), decs.ts(), decs.err(),
					decs.refsAChequear());
			cod().ponDependencias(decs.cod(), decs.dir(), nivelh(), insts.cod());
			etq().ponDependencias(insts.etq());
			llamadasPendientes().ponDependencias(insts.llamadasPendientes());
			anidamiento().ponDependencias(decs.anidamiento());

			// dirInicio().ponDependencias(decs.etq());
			dirInicio().ponDependencias(decs.dir());
			dirSalto().ponDependencias(decs.etq());
			decs.tsh().ponDependencias(tsh());
			decs.dirh().ponDependencias(dirh());
			decs.nivelh().ponDependencias(nivelh());
			decs.etqh().ponDependencias(etqh());
			insts.tsh().ponDependencias(decs.ts());
			insts.etqh().ponDependencias(decs.etq());
		}

		protected final Error err_exp() {
			Error e = tiposNoDeclarados(decs.refsAChequear().val(), decs.ts()
					.val());
			e = joinErrors(e, insts.err().val());
			return joinErrors(e, decs.err().val());
		}

		protected final List<Instruccion> cod_exp() {
			return concat(decs.cod().val(),
					codigoPrologo(decs.dir().val(), nivelh().val()), insts
							.cod().val(),
					codigoEpilogo(decs.dir().val(), nivelh().val()));
		}

		@Override
		protected Integer etq_exp() {
			return insts.etq().val() + numeroInstruccionesEpilogo();
		}

		@Override
		protected Integer dirInicio_exp() {
			return decs.dir().val();
		}

		@Override
		protected Integer dirSalto_exp() {
			return decs.etq().val();
		}

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return insts.llamadasPendientes().val();
		}

		@Override
		protected Integer anidamiento_exp() {
			return decs.anidamiento().val() + 1;
		}

		private Decs decs;
		private Insts insts;

	}

	public class BloqueR1Debug extends BloqueR1 {
		private final static String REGLA = "Bloque ::= Declaraciones & Instrucciones";

		public BloqueR1Debug(Decs decs, Insts insts) {
			super(decs, insts);
			err().fijaDescripcion(REGLA + " | Bloque.err");
			cod().fijaDescripcion(REGLA + " | Bloque.cod");
			etq().fijaDescripcion(REGLA + " | Bloque.etq");
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Bloque.llamadasPendientes");
			anidamiento().fijaDescripcion(REGLA + " | Bloque.anidamiento");
			dirInicio().fijaDescripcion(REGLA + " | Bloque.dirInicio");
			dirSalto().fijaDescripcion(REGLA + " | Bloque.dirSalto");
			decs.tsh().fijaDescripcion(REGLA + " | decs.tsh");
			decs.dirh().fijaDescripcion(REGLA + " | decs.dirh");
			decs.nivelh().fijaDescripcion(REGLA + " | decs.nivelh");
			decs.etqh().fijaDescripcion(REGLA + " | decs.etqh");
			insts.tsh().fijaDescripcion(REGLA + " | insts.tsh");
			insts.etqh().fijaDescripcion(REGLA + " | insts.etqh");
		}
	}

	/**
	 * <code>
	 * Bloque ::= Instrucciones
			Instrucciones.tsh = Bloque.tsh
			Bloque.error = Instrucciones.error
			Instrucciones.etqh = Bloque.etqh + numeroInstruccionesPrologo()
			Bloque.etq = Instrucciones.etq + numeroInstruccionesEpilogo()
			Bloque.dirInicio = Bloque.dirh
			Bloque.dirSalto = Bloque.etqh
			Bloque.cod = codigoPrologo(Bloque.dirh,Bloque.nivelh) || Instrucciones.cod ||
							codigoEpilogo(Bloque.dirh,Bloque.nivelh)
			Bloque.llamadasPendientes = Instrucciones.llamadasPendientes
			Bloque.anidamiento = 1
	 * </code>
	 */
	public class BloqueR2 extends Bloque {

		public BloqueR2(Insts insts) {
			super();
			this.insts = insts;

			insts.registraCtx(new InstsCtx() {
				public TS tsh_exp() {
					return BloqueR2.this.tsh().val();
				}

				public int etqh_exp() {
					return BloqueR2.this.etqh().val()
							+ numeroInstruccionesPrologo();
				}
			});
			err().ponDependencias(insts.err());
			cod().ponDependencias(dirh(), nivelh(), insts.cod());
			etq().ponDependencias(insts.etq());
			llamadasPendientes().ponDependencias(insts.llamadasPendientes());
			dirInicio().ponDependencias(dirh());
			dirSalto().ponDependencias(etqh());
			insts.tsh().ponDependencias(tsh());
			insts.etqh().ponDependencias(etqh());
		}

		protected final Error err_exp() {
			return insts.err().val();
		}

		protected final List<Instruccion> cod_exp() {
			return insts.cod().val();
		}

		@Override
		protected final Integer etq_exp() {
			return insts.etq().val() + numeroInstruccionesEpilogo();
		}

		@Override
		protected Integer dirInicio_exp() {
			return dirh().val();
		}

		@Override
		protected Integer dirSalto_exp() {
			return etqh().val();
		}

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return insts.llamadasPendientes().val();
		}

		@Override
		protected Integer anidamiento_exp() {
			return 1;
		}

		private Insts insts;
	}

	public class BloqueR2Debug extends BloqueR2 {
		private final static String REGLA = "Programa ::= Instrucciones";

		public BloqueR2Debug(Insts insts) {
			super(insts);
			err().fijaDescripcion(REGLA + " | Bloque.err");
			cod().fijaDescripcion(REGLA + " | Bloque.cod");
			etq().fijaDescripcion(REGLA + " | Bloque.etq");
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Bloque.llamadasPendientes");
			anidamiento().fijaDescripcion(REGLA + " | Bloque.anidamiento");
			dirInicio().fijaDescripcion(REGLA + " | Bloque.dirInicio");
			insts.tsh().fijaDescripcion(REGLA + " | insts.tsh");
			insts.etqh().fijaDescripcion(REGLA + " | insts.etqh");
		}
	}

	/**
	 * <code>
	 * Declaraciones ::= Declaraciones ; Declaracion
	 * Construcción de la tabla de símbolos
	 * 	Declaraciones(1).tsh = Declaraciones(0).tsh
	 * 	Declaraciones(1).nivelh = Declaraciones(0).nivelh
		Declaraciones(1).dirh = Declaraciones(0).dirh
		Declaracion.nivelh = Declaraciones(0).nivelh
		Declaraciones(0).ts = aniadeSimb(Declaraciones(1).ts, Declaracion.iden,
		Declaracion.clase, Declaracion.tipo,
		Declaraciones(1).dir, Declaraciones(0).nivelh)
		Declaraciones(0).dir = Declaraciones(1).dir + Declaracion.tam
		Propagación de la tabla de símbolos
		Declaracion.tsh = Declaraciones(1).ts
		Comprobación de las restricciones contextuales
		Declaraciones(0).error = Declaraciones(1).error or Declaracion.error or
		existeSimbEnUltimoNivel(Declaraciones(1).ts,Declaracion.iden,Declaraciones(0).nivelh)
		Declaraciones(0).refsAChequear = Declaraciones(1).refsAChequear  Declaracion.refsAChequear
		Generación de código
		Declaraciones(1).etqh = Declaraciones(0).etqh
		Declaracion.etqh = Declaraciones(1).etq
		Declaraciones(0).etq = Declaracion.etq
		Declaraciones(0).cod = Declaraciones(1).cod || Declaracion.cod
		Declaraciones(0).anidamiento = max(Declaraciones(1).anidamiento,Declaracion.anidamiento)
	 * </code>
	 */
	public class DecsR1 extends Decs {

		public DecsR1(Decs decs_1, Dec dec) {
			super();
			this.dec = dec;
			this.decs_1 = decs_1;

			dec.registraCtx(new DecCtx() {
				@Override
				public int nivelh_exp() {
					return DecsR1.this.nivelh().val();
				}

				@Override
				public TS tsh_exp() {
					return DecsR1.this.decs_1.ts().val();
				}

				@Override
				public int etqh_exp() {
					return DecsR1.this.decs_1.etq().val();
				}
			});

			decs_1.registraCtx(new DecsCtx() {
				@Override
				public int nivelh_exp() {
					return DecsR1.this.nivelh().val();
				}

				@Override
				public TS tsh_exp() {
					return DecsR1.this.tsh().val();
				}

				@Override
				public int etqh_exp() {
					return DecsR1.this.etqh().val();
				}

				@Override
				public int dirh_exp() {
					return DecsR1.this.dirh().val();
				}
			});

			err().ponDependencias(decs_1.ts(), dec.iden(), dec.fila(),
					dec.col(), dec.err(), decs_1.err(), nivelh());
			dir().ponDependencias(decs_1.dir(), dec.tam());
			ts().ponDependencias(decs_1.ts(), decs_1.dir(), dec.iden(),
					dec.tipo(), dec.clase(), nivelh());
			etq().ponDependencias(dec.etq());
			cod().ponDependencias(decs_1.cod(), dec.cod());
			refsAChequear().ponDependencias(decs_1.refsAChequear(),
					dec.refsAChequear());
			anidamiento().ponDependencias(decs_1.anidamiento(),
					dec.anidamiento());
			decs_1.tsh().ponDependencias(tsh());
			decs_1.nivelh().ponDependencias(nivelh());
			decs_1.dirh().ponDependencias(dirh());
			decs_1.etqh().ponDependencias(etqh());
			dec.nivelh().ponDependencias(nivelh());
			dec.etqh().ponDependencias(decs_1.etq());
			dec.tsh().ponDependencias(decs_1.ts());
		}

		public TS ts_exp() {
			return aniadeSimb(decs_1.ts().val(), dec.iden().val(), dec.clase()
					.val(), dec.tipo().val(), decs_1.dir().val(), nivelh()
					.val());
		}

		public Error err_exp() {
			Error e = joinErrors(decs_1.err().val(), dec.err().val());
			return joinErrors(
					e,
					existeSimbEnUltimoNivel(decs_1.ts().val(),
							dec.iden().val(), nivelh().val()));
		}

		public Integer dir_exp() {
			return decs_1.dir().val() + dec.tam().val();
		}

		private Dec dec;
		private Decs decs_1;

		@Override
		protected List<Instruccion> cod_exp() {
			return concat(decs_1.cod().val(), dec.cod().val());
		}

		@Override
		protected Integer etq_exp() {
			return dec.etq().val();
		}

		@Override
		protected Integer anidamiento_exp() {
			return Math
					.max(decs_1.anidamiento().val(), dec.anidamiento().val());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return union(decs_1.refsAChequear().val(), dec.refsAChequear()
					.val());
		}
	}

	public class DecsR1Debug extends DecsR1 {
		private final static String REGLA = "Declaraciones  ::= Declaraciones  ; Declaracion";

		public DecsR1Debug(Decs decs_1, Dec dec) {
			super(decs_1, dec);
			err().fijaDescripcion(REGLA + " | Decs(0).err");
			dir().fijaDescripcion(REGLA + " | Decs(0).dir");
			ts().fijaDescripcion(REGLA + " | Decs(0).ts");
			etq().fijaDescripcion(REGLA + " | Decs(0).etq");
			cod().fijaDescripcion(REGLA + " | Decs(0).cod");
			refsAChequear().fijaDescripcion(REGLA + " | Decs(0).refsAChequear");
			anidamiento().fijaDescripcion(REGLA + " | Decs(0).anidamiento");
			decs_1.tsh().fijaDescripcion(REGLA + " | Decs(1).tsh");
			decs_1.nivelh().fijaDescripcion(REGLA + " | Decs(1).nivelh");
			decs_1.dirh().fijaDescripcion(REGLA + " | Decs(1).dirh");
			decs_1.etqh().fijaDescripcion(REGLA + " | Decs(1).etqh");
			dec.nivelh().fijaDescripcion(REGLA + " | Decs.nivelh");
			dec.etqh().fijaDescripcion(REGLA + " | Decs.etqh");
			dec.tsh().fijaDescripcion(REGLA + " | Decs.tsh");
			dec.col().fijaDescripcion(REGLA + " | Dec.col");
			dec.fila().fijaDescripcion(REGLA + " | Dec.fila");
			dec.tsh().fijaDescripcion(REGLA + " | Decs.tsh");
		}

	}

	/**
	 * <code>
	 * Declaraciones ::= Declaracion
	 * Construcción de la tabla de símbolos
	 * Declaraciones.ts = aniadeSimb(Declaraciones.tsh,Declaracion.iden, Declaracion.clase,
	 * Declaracion.tipo,Declaraciones.dirh,Declaraciones.nivelh)
	 * Declaracion.nivelh = Declaraciones.nivelh
	 * Declaraciones.dir = Declaraciones.dirh + Declaracion.tam
	 * Propagación de la tabla de símbolos
	 * Declaracion.tsh = Declaraciones.tsh
	 * Comprobación de las restricciones contextuales
	 * Declaraciones.error = Declaracion.error or existeSimbEnUltimoNivel(Declaraciones.tsh,Declaracion.iden,Declaraciones.nivelh)
	 * Declaraciones.refsAChequear = Declaracion.refsAChequear
	 * Generación de código
	 * Declaracion.etqh = Declaraciones.etqh
	 * Declaraciones.etq = Declaracion.etq
	 * Declaraciones.cod = Declaracion.cod
	 * Declaraciones.anidamiento = Declaracion.anidamiento
	 * </code>
	 */
	public class DecsR2 extends Decs {

		public DecsR2(Dec dec) {
			super();
			this.dec = dec;
			dec.registraCtx(new DecCtx() {
				@Override
				public int nivelh_exp() {
					return DecsR2.this.nivelh().val();
				}

				@Override
				public TS tsh_exp() {
					return DecsR2.this.tsh().val();
				}

				@Override
				public int etqh_exp() {
					return DecsR2.this.etqh().val();
				}
			});

			err().ponDependencias(tsh(), dec.iden(), dec.fila(), dec.col(),
					dec.err(), nivelh());
			dir().ponDependencias(dirh(), dec.tam());
			ts().ponDependencias(tsh(), dirh(), dec.iden(), dec.tipo(),
					dec.clase(), nivelh());
			etq().ponDependencias(dec.etq());
			cod().ponDependencias(dec.cod());
			refsAChequear().ponDependencias(dec.refsAChequear());
			anidamiento().ponDependencias(dec.anidamiento());
			dec.nivelh().ponDependencias(nivelh());
			dec.etqh().ponDependencias(etqh());
			dec.tsh().ponDependencias(tsh());
		}

		public TS ts_exp() {
			return aniadeSimb(tsh().val(), dec.iden().val(), dec.clase().val(),
					dec.tipo().val(), dirh().val(), nivelh().val());
		}

		public Error err_exp() {
			return joinErrors(
					dec.err().val(),
					existeSimbEnUltimoNivel(tsh().val(), dec.iden().val(),
							nivelh().val()));
		}

		public Integer dir_exp() {
			return dirh().val() + dec.tam().val();
		}

		@Override
		protected List<Instruccion> cod_exp() {
			return dec.cod().val();
		}

		@Override
		protected Integer etq_exp() {
			return dec.etq().val();
		}

		@Override
		protected Integer anidamiento_exp() {
			return dec.anidamiento().val();
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return dec.refsAChequear().val();
		}

		private Dec dec;
	}

	public class DecsR2Debug extends DecsR2 {
		private final static String REGLA = "Declaraciones  ::= Declaracion";

		public DecsR2Debug(Dec dec) {
			super(dec);
			err().fijaDescripcion(REGLA + " | Decs.err");
			dir().fijaDescripcion(REGLA + " | Decs.dir");
			ts().fijaDescripcion(REGLA + " | Decs.ts");
			etq().fijaDescripcion(REGLA + " | Decs.etq");
			cod().fijaDescripcion(REGLA + " | Decs.cod");
			refsAChequear().fijaDescripcion(REGLA + " | Decs.refsAChequear");
			anidamiento().fijaDescripcion(REGLA + " | Decs.anidamiento");
			dec.nivelh().fijaDescripcion(REGLA + " | Dec.nivelh");
			dec.etqh().fijaDescripcion(REGLA + " | Dec.etqh");
			dec.tsh().fijaDescripcion(REGLA + " | Dec.tsh");
			dec.fila().fijaDescripcion(REGLA + " | Dec.fila");
			dec.col().fijaDescripcion(REGLA + " | Dec.columna");
		}
	}

	/**
	 * <code>
	 * Declaracion ::= Tipo IDEN
	 * Construcción de la tabla de símbolos
	 * Declaracion.clase = var
	 * Declaracion.tipo = Tipo.tipo
	 * Declaracion.tam = Tipo.tipo.tam
	 * Declaracion.iden = IDEN.lex
	 * Propagación de la tabla de símbolos
	 * Tipo.tsh = Declaracion.tsh
	 * Comprobación de las restricciones contextuales
	 * Declaracion.refsAChequear = Tipo.refsAChequear
	 * Declaracion.error = Tipo.error or tipoRefIncorrecto(Declaracion.tsh,Tipo.tipo)
	 * Generación de código
	 * Declaracion.etq = Declaracion.etqh
	 * Declaracion.cod = programaVacio()
	 * Declaracion.anidamiento=0
	 * </code>
	 */
	public class DecR1 extends Dec {

		public DecR1(Tipo tipo, Token IDEN) {
			super();
			this.tipo = tipo;
			this.iden = IDEN;
			tipo.registraCtx(new TipoCtx() {

				@Override
				public TS tsh_exp() {
					return DecR1.this.tsh().val();
				}
			});

			tam().ponDependencias(tipo.tipo());
			tipo().ponDependencias(tipo.tipo());
			err().ponDependencias(tipo.err(), tsh(), tipo.tipo());// TODO?tipo.tipo()
			etq().ponDependencias(etqh());
			// ni clase ni iden dependencias creo
			refsAChequear().ponDependencias(tipo.refsAChequear());
			tipo.tsh().ponDependencias(tsh());
		}

		public String iden_exp() {
			return iden.lex();
		}

		public ExpTipo tipo_exp() {
			return tipo.tipo_exp();
		}

		protected final Integer fila_exp() {
			return new Integer(iden.leeFila());
		}

		protected final Integer col_exp() {
			return new Integer(iden.leeCol());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return tipo.refsAChequear().val();
		}

		@Override
		protected Integer etq_exp() {
			return etqh().val();
		}

		@Override
		protected List<Instruccion> cod_exp() {
			return programaVacio();
		}

		@Override
		protected Integer anidamiento_exp() {
			return 0;
		}

		@Override
		protected Error err_exp() {
			return joinErrors(tipo.err().val(),
					tipoRefIncorrecto(tsh().val(), tipo.tipo_exp()));
		}

		@Override
		protected CatLexica clase_exp() {
			return CatLexica.VAR;
		}

		@Override
		protected Integer tam_exp() {
			return tipo.tipo_exp().tam();
		}

		private Token iden;
		private Tipo tipo;
	}

	public class DecR1Debug extends DecR1 {
		private final static String REGLA = "Declaracion ::= Tipo IDEN";

		public DecR1Debug(Tipo tipo, Token iden) {
			super(tipo, iden);
			cod().fijaDescripcion(REGLA + " | Dec.cod");
			tipo().fijaDescripcion(REGLA + " | Dec.tipo");
			err().fijaDescripcion(REGLA + " | Dec.err");
			etq().fijaDescripcion(REGLA + " | Dec.etq");
			clase().fijaDescripcion(REGLA + " | Dec.clase");
			tam().fijaDescripcion(REGLA + " | Dec.tam");
			iden().fijaDescripcion(REGLA + " | Dec.iden");
			refsAChequear().fijaDescripcion(REGLA + " | Dec.refsAChequear");
			tipo.tsh().fijaDescripcion(REGLA + " | Tipo.tsh");
			anidamiento().fijaDescripcion(REGLA + " | Dec.anidamiento");

		}
	}

	/**
	 * <code>
	 * Declaracion ::= tipo Tipo IDEN
	 * Construcción de la tabla de símbolos
	 * Declaracion.clase = tipo
	 * Declaracion.tipo = Tipo.tipo
	 * Declaracion.tam = 0
	 * Declaracion.iden = IDEN.lex
	 * Propagación de la tabla de símbolos
	 * Tipo.tsh = Declaracion.tsh
	 * Comprobación de las restricciones contextuales
	 * Declaracion.refsAChequear = Tipo.refsAChequear
	 * Declaracion.error = Tipo.error or tipoRefIncorrecto(Declaracion.tsh,Tipo.tipo)
	 * Generación de código
	 * Declaracion.etq = Declaracion.etqh
	 * Declaracion.cod = programaVacio()
	 * Declaracion.anidamiento=0
	 * </code>
	 */
	public class DecR2 extends Dec {

		public DecR2(Tipo tipo, Token IDEN) {
			super();
			this.tipo = tipo;
			this.iden = IDEN;
			tipo.registraCtx(new TipoCtx() {
				@Override
				public TS tsh_exp() {
					return DecR2.this.tsh().val();
				}
			});

			tipo().ponDependencias(tipo.tipo());
			err().ponDependencias(tipo.err(), tsh(), tipo.tipo());// TODO?
			etq().ponDependencias(etqh());
			// ni clase ni tam ni iden dependencias creo
			refsAChequear().ponDependencias(tipo.refsAChequear());
			tipo.tsh().ponDependencias(tsh());
		}

		public String iden_exp() {
			return iden.lex();
		}

		public ExpTipo tipo_exp() {
			return tipo.tipo_exp();
		}

		protected final Integer fila_exp() {
			return new Integer(iden.leeFila());
		}

		protected final Integer col_exp() {
			return new Integer(iden.leeCol());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return tipo.refsAChequear().val();
		}

		@Override
		protected Integer etq_exp() {
			return etqh().val();
		}

		@Override
		protected List<Instruccion> cod_exp() {
			return programaVacio();
		}

		@Override
		protected Integer anidamiento_exp() {
			return 0;
		}

		@Override
		protected Error err_exp() {
			return joinErrors(tipo.err().val(),
					tipoRefIncorrecto(tsh().val(), tipo.tipo_exp()));
		}

		@Override
		protected CatLexica clase_exp() {
			return CatLexica.TIPO;
		}

		@Override
		protected Integer tam_exp() {
			return 0;
		}

		private Token iden;
		private Tipo tipo;

	}

	public class DecR2Debug extends DecR2 {
		private final static String REGLA = "Declaracion ::= tipo Tipo IDEN";

		public DecR2Debug(Tipo tipo, Token iden) {
			super(tipo, iden);
			err().fijaDescripcion(REGLA + " | Dec.err");
			etq().fijaDescripcion(REGLA + " | Dec.etq");
			tipo().fijaDescripcion(REGLA + " | Dec.tipo");
			iden().fijaDescripcion(REGLA + " | Dec.iden");
			refsAChequear().fijaDescripcion(REGLA + " | Dec.refsAChequear");
			tipo.tsh().fijaDescripcion(REGLA + " | Tipo.tsh");
		}

	}

	/**
	 * <code>
	 * Declaracion ::= proc IDEN ( ParametrosFormales ) { Bloque }
	 * Construcción de la tabla de símbolos
	 * Declaracion.clase = proc
	 * Declaracion.tipo = <t:proc, params: ParametrosFormales.params>
	 * Declaracion.tam = 0
	 * Declaracion.iden = IDEN.lex
	 * ParametrosFormales.nivelh = Declaracion.nivelh + 1
	 * Bloque.nivelh = Declaracion.nivelh + 1
	 * ParametrosFormales.tsh = añadeSimb(creaNivel(Declaracion.tsh),IDEN.lex,proc,
	 * <t:proc, params: ParametrosFormales.params>,?, Declaracion.nivelh+1)
	 * Bloque.tsh = ParametrosFormales.ts
	 * Bloque.dirh = ParametrosFormales.dir
	 * Comprobación de las restricciones contextuales
	 * Declaracion.refsAChequear = ParametrosFormales.refsAChequear
	 * Declaracion.error = ParametrosFormales.error or Bloque.error
	 * Generación de código
	 * Bloque.etqh = Declaracion.etqh
	 * Declaracion.etq = Bloque.etq
	 * Declaracion.cod = fijaLlamadasPendientes(Bloque.cod,Bloque.llamadasPendientes,Bloque.dirInicio)
	 * Declaracion.anidamiento = Bloque.anidamiento
	 * </code>
	 */
	public class DecR3 extends Dec {

		public DecR3(Token iden, ParamsFormales paramsForms, Bloque bloque) {
			super();
			this.iden = iden;
			this.paramsForms = paramsForms;
			this.bloque = bloque;

			paramsForms.registraCtx(new ParamsFormalesCtx() {
				@Override
				public TS tsh_exp() {
					// TODO crear ExpTipo para proc
					return aniadeSimb(creaNivel(tsh()), DecR3.this.iden.lex(),
							CatLexica.PROC, ExpTipo.nuevaExpTipoProc(
									CatLexica.PROC, DecR3.this.paramsForms
											.params().val()), -1, nivelh()
									.val() + 1);
				}

				@Override
				public int nivelh_exp() {
					return nivelh().val() + 1;
				}
			});

			bloque.registraCtx(new BloqueCtx() {
				@Override
				public TS tsh_exp() {
					return DecR3.this.paramsForms.ts().val();
				}

				@Override
				public int nivelh_exp() {
					return nivelh().val() + 1;
				}

				@Override
				public int etqh_exp() {
					return DecR3.this.paramsForms.dir().val();
				}

				@Override
				public int dirh_exp() {
					return DecR3.this.etqh().val();
				}
			});

			err().ponDependencias(paramsForms.err(), bloque.err());
			etq().ponDependencias(bloque.etq());
			cod().ponDependencias(bloque.cod(), bloque.llamadasPendientes(),
					bloque.dirInicio());
			refsAChequear().ponDependencias(paramsForms.refsAChequear());

			paramsForms.nivelh().ponDependencias(nivelh());
			paramsForms.tsh().ponDependencias(tsh(), paramsForms.params(),
					nivelh());

			bloque.nivelh().ponDependencias(nivelh());
			bloque.etqh().ponDependencias(etqh());
			bloque.tsh().ponDependencias(paramsForms.ts());
			bloque.dirh().ponDependencias(paramsForms.dir());
		}

		public String iden_exp() {
			return iden.lex();
		}

		protected ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoProc(CatLexica.PROC, paramsForms
					.params().val());
		}

		protected final Integer fila_exp() {
			return new Integer(iden.leeFila());
		}

		protected final Integer col_exp() {
			return new Integer(iden.leeCol());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return paramsForms.refsAChequear().val();
		}

		@Override
		protected Integer etq_exp() {
			return bloque.etq().val();
		}

		@Override
		protected List<Instruccion> cod_exp() {
			return fijaLlamadasPendientes(bloque.cod.val(), bloque
					.llamadasPendientes().val(), bloque.dirInicio().val());
		}

		@Override
		protected Integer anidamiento_exp() {
			return bloque.anidamiento().val();
		}

		@Override
		protected Error err_exp() {
			return joinErrors(paramsForms.err().val(), bloque.err().val());
		}

		@Override
		protected CatLexica clase_exp() {
			return CatLexica.PROC;
		}

		@Override
		protected Integer tam_exp() {
			return 0;
		}

		private Token iden;
		private ParamsFormales paramsForms;
		private Bloque bloque;

	}

	public class DecR3Debug extends DecR3 {
		private final static String REGLA = "Declaracion ::= proc IDEN ( ParametrosFormales ) { Bloque }";

		public DecR3Debug(Token iden, ParamsFormales paramsForms, Bloque bloque) {
			super(iden, paramsForms, bloque);
			err().fijaDescripcion(REGLA + " | Dec.err");
			etq().fijaDescripcion(REGLA + " | Dec.etq");
			cod().fijaDescripcion(REGLA + " | Dec.cod");
			refsAChequear().fijaDescripcion(REGLA + " | Dec.refsAChequear");

			paramsForms.nivelh().fijaDescripcion(
					REGLA + " | ParamsForms.nivelh");
			paramsForms.tsh().fijaDescripcion(REGLA + " | ParamsForms.tsh");

			bloque.nivelh().fijaDescripcion(REGLA + " | Bloque.nivelh");
			bloque.etqh().fijaDescripcion(REGLA + " | Bloque.etqh");
			bloque.tsh().fijaDescripcion(REGLA + " | Bloque.tsh");
			bloque.dirh().fijaDescripcion(REGLA + " | Bloque.dirh");
		}

	}

	/**
	 * <code>
	 * ParametrosFormales ::= ListaParametrosFormales
	 * Construcción de la tabla de símbolos
	 * ListaParametrosFormales.tsh = ParametrosFormales.tsh
	 * ListaParametrosFormales.nivelh = ParametrosFormales.nivelh
	 * ParametrosFormales.params = ListaParametrosFormales.params
	 * ParametrosFormales.ts = ListaParametrosFormales.ts
	 * ParametrosFormales.dir = ListaParametrosFormales.dir
	 * Comprobación de las restricciones contextuales
	 * ParametrosFormales.error = ListaParametrosFormales.error
	 * ParametrosFormales.refsAChequear = ListaParametrosFormales.refsAChequear
	 * </code>
	 */
	public class ParamsFormalesR1 extends ParamsFormales {

		public ParamsFormalesR1(ListaParamsFormales listaParamsForms) {
			super();
			this.listaParamsForms = listaParamsForms;

			listaParamsForms.registraCtx(new ListaParamsFormalesCtx() {
				@Override
				public TS tsh_exp() {
					return ParamsFormalesR1.this.tsh().val();
				}

				@Override
				public int nivelh_exp() {
					return ParamsFormalesR1.this.nivelh().val();
				}
			});
			params().ponDependencias(listaParamsForms.params());
			ts().ponDependencias(listaParamsForms.ts());
			dir().ponDependencias(listaParamsForms.dir());
			err().ponDependencias(listaParamsForms.err());
			refsAChequear().ponDependencias(listaParamsForms.refsAChequear());

			listaParamsForms.tsh().ponDependencias(tsh());
			listaParamsForms.nivelh().ponDependencias(nivelh());
		}

		protected final Error err_exp() {
			return listaParamsForms.err().val();
		}

		private ListaParamsFormales listaParamsForms;

		@Override
		protected TS ts_exp() {
			return listaParamsForms.ts().val();
		}

		@Override
		protected Integer dir_exp() {
			return listaParamsForms.dir().val();
		}

		@Override
		protected List<ExpTipo> params_exp() {
			return listaParamsForms.params().val();
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return listaParamsForms.refsAChequear().val();
		}
	}

	public class ParamsFormalesR1Debug extends ParamsFormalesR1 {
		private final static String REGLA = "ParametrosFormales ::= ListaParametrosFormales";

		public ParamsFormalesR1Debug(ListaParamsFormales listaParamsForms) {
			super(listaParamsForms);
			params().fijaDescripcion(REGLA + " | ParametrosFormales.params");
			ts().fijaDescripcion(REGLA + " | ParametrosFormales.ts");
			dir().fijaDescripcion(REGLA + " | ParametrosFormales.dir");
			err().fijaDescripcion(REGLA + " | ParametrosFormales.err");
			refsAChequear().fijaDescripcion(
					REGLA + " | ParametrosFormales.refsAChequear");

			listaParamsForms.tsh().fijaDescripcion(
					REGLA + " | ListaParametrosFormales.tsh");
			listaParamsForms.nivelh().fijaDescripcion(
					REGLA + " | ListaParametrosFormales.nivelh");
		}
	}

	/**
	 * <code>
	 * ParametrosFormales ::= lambda
	 * Construcción de la tabla de símbolos
	 * ParametrosFormales.ts = ParametrosFormales.tsh
	 * ParametrosFormales.params = listaVacia()
	 * ParametrosFormales.dir = 0
	 * Comprobación de las restricciones contextuales
	 * ParametrosFormales.error = false
	 * ParametrosFormales.refsAChequear = vacio
	 * </code>
	 */
	public class ParamsFormalesR2 extends ParamsFormales {

		public ParamsFormalesR2() {
			super();
			ts().ponDependencias(tsh());
		}

		protected final Error err_exp() {
			return noError();
		}

		@Override
		protected TS ts_exp() {
			return tsh().val();
		}

		@Override
		protected Integer dir_exp() {
			return 0;
		}

		@Override
		protected List<ExpTipo> params_exp() {
			return listaExpTipoVacia();
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return listaExpTipoVacia();
		}
	}

	public class ParamsFormalesR2Debug extends ParamsFormalesR2 {
		private final static String REGLA = "ParametrosFormales ::= lambda";

		public ParamsFormalesR2Debug() {
			super();
			params().fijaDescripcion(REGLA + " | ParametrosFormales.params");
			ts().fijaDescripcion(REGLA + " | ParametrosFormales.ts");
			dir().fijaDescripcion(REGLA + " | ParametrosFormales.dir");
			err().fijaDescripcion(REGLA + " | ParametrosFormales.err");
			refsAChequear().fijaDescripcion(
					REGLA + " | ParametrosFormales.refsAChequear");
		}
	}

	/**
	 * <code>
	 * ListaParametrosFormales ::= ListaParametrosFormales , ParametroFormal
	 * Construcción de la tabla de símbolos
	 * ListaParametrosFormales(1).tsh = ListaParametrosFormales(0).tsh
	 * ListaParametrosFormales(1).nivelh = ListaParametrosFormales(0).nivelh
	 * ListaParametrosFormales(0).params = añadeA(ListaParametrosFormales(1).params, ParametroFormal.param)
	 * ListaParametrosFormales(0).ts = añadeSimb(ListaParametrosFormales(1).ts,
	 * ParametroFormal.iden, ParametroFormal.clase,
	 * ParametroFormal.tipo, ListaParametrosFormales(1).dir,
	 * ListaParametrosFormales(0).nivelh)
	 * ListaParametrosFormales(0).dir = ListaParametrosFormales(1).dir + ParametroFormal.tam
	 * Propagación de la tabla de símbolos
	 * ParametroFormal.tsh = ListaParametrosFormales(1).ts
	 * Comprobación de las restricciones contextuales
	 * ListaParametrosFormales(0).error =ListaParametrosFormales(1).error orParametroFormal.error or
	 * existeSimbEnUltimoNivel(ListaParametrosFormales(1).ts,ParametroFormal.iden,ListaParametrosFormales(0).nivelh)
	 * ListaParametrosFormales(0).refsAChequear = ListaParametrosFormales(1).refsAChequear U
	 * ParametroFormal.refsAChequear </code>
	 */
	public class ListaParamsFormalesR1 extends ListaParamsFormales {

		public ListaParamsFormalesR1(ListaParamsFormales listaParamsForms_1,
				ParamFormal paramFormal) {
			super();
			this.listaParamsForms_1 = listaParamsForms_1;
			this.paramFormal = paramFormal;
			listaParamsForms_1.registraCtx(new ListaParamsFormalesCtx() {
				@Override
				public TS tsh_exp() {
					return ListaParamsFormalesR1.this.tsh().val();
				}

				@Override
				public int nivelh_exp() {
					return ListaParamsFormalesR1.this.nivelh().val();
				}
			});
			paramFormal.registraCtx(new ParamFormalCtx() {
				@Override
				public TS tsh_exp() {
					return ListaParamsFormalesR1.this.listaParamsForms_1.ts()
							.val();
				}
			});
			params().ponDependencias(listaParamsForms_1.params(),
					paramFormal.param());
			ts().ponDependencias(listaParamsForms_1.ts(), paramFormal.iden(),
					paramFormal.clase(), paramFormal.tipo(),
					listaParamsForms_1.dir(), listaParamsForms_1.nivelh());
			dir().ponDependencias(listaParamsForms_1.dir(), paramFormal.tam());
			err().ponDependencias(listaParamsForms_1.err(), paramFormal.err(),
					listaParamsForms_1.ts(), paramFormal.iden());
			refsAChequear().ponDependencias(listaParamsForms_1.refsAChequear(),
					paramFormal.refsAChequear());

			paramFormal.tsh().ponDependencias(listaParamsForms_1.ts());
			listaParamsForms_1.tsh().ponDependencias(tsh());
			listaParamsForms_1.nivelh().ponDependencias(nivelh());
		}

		protected final Error err_exp() {
			Error e = joinErrors(listaParamsForms_1.err().val(), paramFormal
					.err().val());
			return joinErrors(
					e,
					existeSimbEnUltimoNivel(listaParamsForms_1.ts().val(),
							paramFormal.iden().val(), nivelh().val()));
		}

		@Override
		protected TS ts_exp() {
			return aniadeSimb(listaParamsForms_1.ts().val(), paramFormal.iden()
					.val(), paramFormal.clase().val(),
					paramFormal.tipo().val(), listaParamsForms_1.dir().val(),
					nivelh().val());
		}

		@Override
		protected Integer dir_exp() {
			return listaParamsForms_1.dir().val() + paramFormal.tam().val();
		}

		@Override
		protected List<ExpTipo> params_exp() {
			return aniadeA(listaParamsForms_1.params().val(), paramFormal
					.param().val());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return union(listaParamsForms_1.refsAChequear().val(), paramFormal
					.refsAChequear().val());
		}

		private ListaParamsFormales listaParamsForms_1;
		private ParamFormal paramFormal;
	}

	public class ListaParamsFormalesR1Debug extends ListaParamsFormalesR1 {
		private final static String REGLA = "ListaParametrosFormales(0) ::= ListaParametrosFormales(1) , ParametroFormal";

		public ListaParamsFormalesR1Debug(
				ListaParamsFormales listaParamsForms_1, ParamFormal paramFormal) {
			super(listaParamsForms_1, paramFormal);
			params().fijaDescripcion(
					REGLA + " | ListaParametrosFormales(0).params");
			ts().fijaDescripcion(REGLA + " | ListaParametrosFormales(0).ts");
			dir().fijaDescripcion(REGLA + " | ListaParametrosFormales(0).dir");
			err().fijaDescripcion(REGLA + " | ListaParametrosFormales(0).err");
			refsAChequear().fijaDescripcion(
					REGLA + " | ListaParametrosFormales(0).refsAChequear");

			paramFormal.tsh().fijaDescripcion(REGLA + " | ParametroFormal.tsh");
			listaParamsForms_1.tsh().fijaDescripcion(
					REGLA + " | ListaParametrosFormales(1).tsh");
			listaParamsForms_1.nivelh().fijaDescripcion(
					REGLA + " | ListaParametrosFormales(1).nivelh");
		}
	}

	/**
	 * <code>
	 * ListaParametrosFormales ::= ParametroFormal
	 * Construcción de la tabla de símbolos
	 * ListaParametrosFormales.params = nuevaLista(ParametroFormal.param)
	 * ListaParametrosFormales.ts = añadeSimb(ListaParametrosFormales.tsh,ParametroFormal.iden, ParametroFormal.clase,
	 * ParametroFormal.tipo, 0, ListaParametrosFormales.nivelh)
	 * ListaParametrosFormales.dir = ParametroFormal.tam
	 * Propagación de la tabla de símbolos
	 * ParametroFormal.tsh = ListaParametrosFormales.tsh
	 * Comprobación de las restricciones contextuales
	 * ListaParametrosFormales.error = ParametroFormal.error or
	 * existeSimbEnUltimoNivel(ListaParametrosFormales.tsh,ParametroFormal.iden,ListaParametrosFormales.nivelh)
	 * ListaParametrosFormales.refsAChequear = ParametroFormal.refsAChequear </code>
	 */
	public class ListaParamsFormalesR2 extends ListaParamsFormales {

		public ListaParamsFormalesR2(ParamFormal paramFormal) {
			super();
			this.paramFormal = paramFormal;
			paramFormal.registraCtx(new ParamFormalCtx() {
				@Override
				public TS tsh_exp() {
					return ListaParamsFormalesR2.this.tsh().val();
				}
			});
			params().ponDependencias(paramFormal.param());
			ts().ponDependencias(tsh(), paramFormal.iden(),
					paramFormal.clase(), paramFormal.tipo(), nivelh());
			dir().ponDependencias(paramFormal.tam());
			err().ponDependencias(paramFormal.err(), tsh(), paramFormal.iden());
			refsAChequear().ponDependencias(paramFormal.refsAChequear());

			paramFormal.tsh().ponDependencias(tsh());
		}

		protected final Error err_exp() {
			return joinErrors(
					paramFormal.err().val(),
					existeSimbEnUltimoNivel(tsh().val(), paramFormal.iden()
							.val(), nivelh().val()));
		}

		@Override
		protected TS ts_exp() {
			return aniadeSimb(tsh().val(), paramFormal.iden().val(),
					paramFormal.clase().val(), paramFormal.tipo().val(), 0,
					nivelh().val());
		}

		@Override
		protected Integer dir_exp() {
			return paramFormal.tam().val();
		}

		@Override
		protected List<ExpTipo> params_exp() {
			return nuevaLista(paramFormal.param().val());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return paramFormal.refsAChequear().val();
		}

		private ParamFormal paramFormal;
	}

	public class ListaParamsFormalesR2Debug extends ListaParamsFormalesR2 {
		private final static String REGLA = "ListaParametrosFormales ::= ParametroFormal";

		public ListaParamsFormalesR2Debug(ParamFormal paramFormal) {
			super(paramFormal);
			params().fijaDescripcion(
					REGLA + " | ListaParametrosFormales.params");
			ts().fijaDescripcion(REGLA + " | ListaParametrosFormales.ts");
			dir().fijaDescripcion(REGLA + " | ListaParametrosFormales.dir");
			err().fijaDescripcion(REGLA + " | ListaParametrosFormales.err");
			refsAChequear().fijaDescripcion(
					REGLA + " | ListaParametrosFormales.refsAChequear");

			paramFormal.tsh().fijaDescripcion(REGLA + " | paramFormal.tsh");
		}
	}

	/**
	 * <code>
	 * ParametroFormal ::= Tipo IDEN
	 * Construcción de la tabla de símbolos
	 * ParametroFormal.clase = var
	 * ParametroFormal.tipo = Tipo.tipo
	 * ParametroFormal.tam = Tipo.tipo.tam
	 * ParametroFormal.iden = IDEN.lex
	 * ParametroFormal.param = <tipo: Tipo.tipo, modo: valor>
	 * Propagación de la tabla de símbolos
	 * Tipo.tsh = ParametroFormal.tsh
	 * Comprobación de las restricciones contextuales
	 * ParametroFormal.refsAChequear = Tipo.refsAChequear
	 * ParametroFormal.error = Tipo.error or tipoRefIncorrecto(ParametroFormal.tsh,Tipo.tipo)
	 * </code>
	 */
	public class ParamFormalR1 extends ParamFormal {

		public ParamFormalR1(Tipo tipo, Token IDEN) {
			super();
			this.tipo = tipo;
			this.iden = IDEN;
			tipo.registraCtx(new TipoCtx() {

				@Override
				public TS tsh_exp() {
					return ParamFormalR1.this.tsh().val();
				}
			});
			tam().ponDependencias(tipo.tipo());
			param().ponDependencias(tipo.tipo());
			tipo().ponDependencias(tipo.tipo());
			err().ponDependencias(tipo.err(), tsh(), tipo.tipo());// TODO?tipo.tipo()
			// ni clase ni tipo ni tam ni iden dependencias creo
			refsAChequear().ponDependencias(tipo.refsAChequear());
			tipo.tsh().ponDependencias(tsh());
		}

		public String iden_exp() {
			return iden.lex();
		}

		public ExpTipo tipo_exp() {
			return tipo.tipo_exp();
		}

		protected final Integer fila_exp() {
			return new Integer(iden.leeFila());
		}

		protected final Integer col_exp() {
			return new Integer(iden.leeCol());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return tipo.refsAChequear().val();
		}

		@Override
		protected Error err_exp() {
			return joinErrors(tipo.err().val(),
					tipoRefIncorrecto(tsh().val(), tipo.tipo_exp()));
		}

		@Override
		protected CatLexica clase_exp() {
			return CatLexica.VAR;
		}

		@Override
		protected Integer tam_exp() {
			return tipo.tipo_exp().tam();
		}

		@Override
		protected ExpTipo param_exp() {
			return ExpTipo
					.nuevaExpTipoParam(tipo.tipo().val(), CatLexica.VALOR);
		}

		private Token iden;
		private Tipo tipo;
	}

	public class ParamFormalR1Debug extends ParamFormalR1 {
		private final static String REGLA = "ParametroFormal ::= Tipo IDEN";

		public ParamFormalR1Debug(Tipo tipo, Token iden) {
			super(tipo, iden);
			tam().fijaDescripcion(REGLA + " | ParametroFormal.tam");
			param().fijaDescripcion(REGLA + " | ParametroFormal.param");
			tipo().fijaDescripcion(REGLA + " | ParametroFormal.tipo");
			err().fijaDescripcion(REGLA + " | ParametroFormal.err");
			iden().fijaDescripcion(REGLA + " | ParametroFormal.iden");
			// ni clase ni tipo ni tam ni iden dependencias creo
			refsAChequear().fijaDescripcion(
					REGLA + " | ParametroFormal.refsAChequear");
			tipo.tsh().fijaDescripcion(REGLA + " | Tipo.tsh");
		}
	}

	/**
	 * <code>
	 * ParametroFormal ::= Tipo IDEN
	 * Construcción de la tabla de símbolos
	 * ParametroFormal.clase = pvar
	 * ParametroFormal.tipo = Tipo.tipo
	 * ParametroFormal.tam = 1
	 * ParametroFormal.iden = IDEN.lex
	 * ParametroFormal.param = <tipo: Tipo.tipo, modo: var>
	 * Propagación de la tabla de símbolos
	 * Tipo.tsh = ParametroFormal.tsh
	 * Comprobación de las restricciones contextuales
	 * ParametroFormal.refsAChequear = Tipo.refsAChequear
	 * ParametroFormal.error = Tipo.error or tipoRefIncorrecto(ParametroFormal.tsh,Tipo.tipo)
	 * </code>
	 */
	public class ParamFormalR2 extends ParamFormal {

		public ParamFormalR2(Tipo tipo, Token IDEN) {
			super();
			this.tipo = tipo;
			this.iden = IDEN;
			tipo.registraCtx(new TipoCtx() {

				@Override
				public TS tsh_exp() {
					return ParamFormalR2.this.tsh().val();
				}
			});

			param().ponDependencias(tipo.tipo());
			tipo().ponDependencias(tipo.tipo());
			err().ponDependencias(tipo.err(), tsh(), tipo.tipo());// TODO?tipo.tipo()
			// ni clase ni tam ni iden dependencias creo
			refsAChequear().ponDependencias(tipo.refsAChequear());
			tipo.tsh().ponDependencias(tsh());
		}

		public String iden_exp() {
			return iden.lex();
		}

		public ExpTipo tipo_exp() {
			return tipo.tipo_exp();
		}

		protected final Integer fila_exp() {
			return new Integer(iden.leeFila());
		}

		protected final Integer col_exp() {
			return new Integer(iden.leeCol());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return tipo.refsAChequear().val();
		}

		@Override
		protected Error err_exp() {
			return joinErrors(tipo.err().val(),
					tipoRefIncorrecto(tsh().val(), tipo.tipo_exp()));
		}

		@Override
		protected CatLexica clase_exp() {
			return CatLexica.PVAR;
		}

		@Override
		protected Integer tam_exp() {
			return 1;
		}

		@Override
		protected ExpTipo param_exp() {
			return ExpTipo.nuevaExpTipoParam(tipo.tipo().val(), CatLexica.VAR);
		}

		private Token iden;
		private Tipo tipo;
	}

	public class ParamFormalR2Debug extends ParamFormalR2 {
		private final static String REGLA = "ParametroFormal ::= Tipo IDEN";

		public ParamFormalR2Debug(Tipo tipo, Token iden) {
			super(tipo, iden);
			param().fijaDescripcion(REGLA + " | ParametroFormal.param");
			tipo().fijaDescripcion(REGLA + " | ParametroFormal.tipo");
			err().fijaDescripcion(REGLA + " | ParametroFormal.err");
			// ni clase ni tam ni iden dependencias creo
			refsAChequear().fijaDescripcion(
					REGLA + " | ParametroFormal.refsAChequear");
			tipo.tsh().fijaDescripcion(REGLA + " | Tipo.tsh");
		}
	}

	/**
	 * <code>
	 * Tipo ::= int
	 * Construcción de la tabla de símbolos
	 * Tipo.tipo = <t:int,tam:1> 
	 * Comprobación de las restricciones contextuales 
	 * Tipo.error = false 
	 * Tipo.refsAChequear = VACIA
	 * </code>
	 */
	public class TipoR1 extends Tipo {

		public TipoR1() {
			super();

		}

		@Override
		public ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoInt();
		}

		@Override
		public Error err_exp() {
			return noError();
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return listaExpTipoVacia();
		}
	}

	public class TipoR1Debug extends TipoR1 {
		private final static String REGLA = "Tipo ::= int";

		public TipoR1Debug() {
			super();
			err().fijaDescripcion(REGLA + " | tipo.err");
			tipo().fijaDescripcion(REGLA + " | Tipo.tipo");
			refsAChequear().fijaDescripcion(REGLA + " | Tipo.refsAChequear");
		}
	}

	/**
	 * <code>
	 * Tipo ::= boolean
	 * Construcción de la tabla de símbolos
	 * Tipo.tipo = <t:boolean,tam:1> 
	 * Comprobación de las restricciones contextuales 
	 * Tipo.error = false 
	 * Tipo.refsAChequear = VACIA
	 * </code>
	 */
	public class TipoR2 extends Tipo {

		public TipoR2() {
			super();
		}

		@Override
		public ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoBoolean();
		}

		@Override
		public Error err_exp() {
			return noError();
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return listaExpTipoVacia();
		}
	}

	public class TipoR2Debug extends TipoR2 {
		private final static String REGLA = "Tipo ::= boolean";

		public TipoR2Debug() {
			super();
			err().fijaDescripcion(REGLA + " | tipo.err");
			tipo().fijaDescripcion(REGLA + " | Tipo.tipo");
			refsAChequear().fijaDescripcion(REGLA + " | Tipo.refsAChequear");
		}
	}

	/**
	 * <code>
	 * Tipo ::= tabla [NUM] de Tipo
	 * Construcción de la tabla de símbolos
	 * Tipo(0).tipo = <t:array, tbase: Tipo(1).tipo, tam:Tipo(1).tipo.tam*aEntero(NUM.lex)>
	 * Propagación de la tabla de símbolos
	 * Tipo(1).tsh = Tipo(0).tsh
	 * Comprobación de las restricciones contextuales
	 * Tipo(0).error = Tipo(1).error or tipoRefIncorrecto(Tipo(0).tsh,Tipo(1).tipo)
	 * Tipo(0).refsAChequear = Tipo(1).refsAChequear
	 * </code>
	 */
	public class TipoR3 extends Tipo {

		public TipoR3(Token num, Tipo tipo_1) {
			super();
			this.NUM = num;
			this.tipo_1 = tipo_1;
			tipo().ponDependencias(tipo_1.tipo());
			err().ponDependencias(tipo_1.err(), tsh(), tipo_1.tipo());// TODO
																		// tipo.tipo?
			refsAChequear().ponDependencias(tipo_1.refsAChequear());
			tipo_1.tsh().ponDependencias(tsh());
			tipo_1.registraCtx(new TipoCtx() {

				@Override
				public TS tsh_exp() {
					return TipoR3.this.tsh().val();
				}
			});
		}

		@Override
		public ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoArray(CatLexica.ARRAY, tipo_1.tipo()
					.val(), tipo_1.tipo().val().tam() * aEntero(NUM.lex()));
		}

		@Override
		public Error err_exp() {
			return joinErrors(tipo_1.err().val(),
					tipoRefIncorrecto(tsh().val(), tipo().val()));
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return tipo_1.refsAChequear().val();
		}

		private Tipo tipo_1;
		private Token NUM;
	}

	public class TipoR3Debug extends TipoR3 {
		private final static String REGLA = "Tipo(0) ::= tabla [NUM] de Tipo(1)";

		public TipoR3Debug(Token NUM, Tipo tipo_1) {
			super(NUM, tipo_1);
			tipo().fijaDescripcion(REGLA + " | Tipo(0).tipo");
			tipo_1.tsh().fijaDescripcion(REGLA + " | Tipo(1).tsh");
			err().fijaDescripcion(REGLA + " | Tipo(0).err");
			refsAChequear().fijaDescripcion(REGLA + " | Tipo(0).refsAChequear");
		}
	}

	/**
	 * <code>
	 * Tipo ::= registro{ Campos }
	 * Construcción de la tabla de símbolos
	 * Tipo.tipo = <t:reg, campos: Campos.campos, tam:Campos.tam>
	 * Propagación de la tabla de símbolos
	 * Campos.tsh = Tipo.tsh
	 * Comprobación de las restricciones contextuales
	 * Tipo.error = Campos.error
	 * Tipo.refsAChequear = Campos.refsAChequear
	 * </code>
	 */
	public class TipoR4 extends Tipo {

		public TipoR4(Campos campos) {
			super();
			this.campos = campos;
			campos.registraCtx(new CamposCtx() {

				@Override
				public TS tsh_exp() {
					return TipoR4.this.tsh().val();
				}
			});
			tipo().ponDependencias(campos.campos(), campos.tam());// TODO
																	// necesario?
			campos.tsh().ponDependencias(tsh());
			err().ponDependencias(campos.err());
			refsAChequear().ponDependencias(campos.refsAChequear());
		}

		@Override
		public ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoRegistro(CatLexica.REG, campos.campos()
					.val(), campos.tam().val());
		}

		@Override
		public Error err_exp() {
			return campos.err().val();
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return campos.refsAChequear().val();
		}

		private Campos campos;
	}

	public class TipoR4Debug extends TipoR4 {
		private final static String REGLA = "Tipo ::= registro{ Campos }";

		public TipoR4Debug(Campos campos) {
			super(campos);
			campos.tsh().fijaDescripcion(REGLA + " | Campos.tsh");
			err().fijaDescripcion(REGLA + " | Tipo.err");
			refsAChequear().fijaDescripcion(REGLA + " | tipo(0).refsAChequear");
			tipo().fijaDescripcion(REGLA + " | Tipo.tipo");
		}
	}

	/**
	 * <code>
	 * Tipo ::= puntero Tipo
	 * Construcción de la tabla de símbolos
	 * Tipo(0).tipo = <t:puntero, tbase: Tipo(1).tipo, tam:1>
	 * Propagación de la tabla de símbolos
	 * Tipo(1).tsh = Tipo(0).tsh
	 * Comprobación de las restricciones contextuales
	 * Tipo(0).error = Tipo(1).error
	 * Tipo(0).refsAChequear = Tipo(1).refsAChequear U aChequear(Tipo(0).tsh,Tipo(1).tipo)
	 * </code>
	 */
	public class TipoR5 extends Tipo {

		public TipoR5(Tipo tipo_1) {
			super();
			this.tipo_1 = tipo_1;
			tipo().ponDependencias(tipo_1.tipo());// TODO tipo o no
			tipo_1.tsh().ponDependencias(tsh());
			err().ponDependencias(tipo_1.err());
			refsAChequear().ponDependencias(tipo_1.refsAChequear(), tsh(),
					tipo_1.tipo());// TODO
			// tipo_1.tipo()?
			tipo_1.registraCtx(new TipoCtx() {
				@Override
				public TS tsh_exp() {
					return TipoR5.this.tsh().val();
				}
			});
		}

		@Override
		public ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoPuntero(CatLexica.PUNTERO, tipo_1.tipo()
					.val());
		}

		@Override
		public Error err_exp() {
			return tipo_1.err().val();
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return union(tipo_1.refsAChequear().val(),
					aChequear(tsh().val(), tipo_1.tipo().val()));
		}

		private Tipo tipo_1;
	}

	public class TipoR5Debug extends TipoR5 {
		private final static String REGLA = "Tipo(0) ::= puntero Tipo(1)";

		public TipoR5Debug(Tipo tipo_1) {
			super(tipo_1);
			tipo().fijaDescripcion(REGLA + " | Tipo(0).tipo");
			tipo_1.tsh().fijaDescripcion(REGLA + " | Tipo(1).tsh");
			err().fijaDescripcion(REGLA + " | Tipo(0).err");
			refsAChequear().fijaDescripcion(REGLA + " | Tipo(0).refsAChequear");
		}
	}

	/**
	 * <code>
	 * Tipo ::= IDEN
	 * Construcción de la tabla de símbolos
	 * Tipo.tipo = <t:ref, iden: IDEN.lex, tam:tamañoDeTipoRef(Tipo.tsh,IDEN.lex)>
	 * Comprobación de las restricciones contextuales
	 * Tipo.refsAChequear = vacio
	 * Tipo.error = false </code>
	 */
	public class TipoR6 extends Tipo {

		public TipoR6(Token iden) {
			super();
			this.iden = iden.lex();
			tipo().ponDependencias(tsh());
		}

		@Override
		public ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoRef(CatLexica.REF, iden,
					tamanioDeTipoRef(tsh().val(), iden));
		}

		@Override
		public Error err_exp() {
			return noError();
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return listaExpTipoVacia();
		}

		private String iden;
	}

	public class TipoR6Debug extends TipoR6 {
		private final static String REGLA = "Tipo ::= IDEN";

		public TipoR6Debug(Token iden) {
			super(iden);
			// TODO tipo si es atrib k calcular
			tipo().fijaDescripcion(REGLA + " | Tipo.tipo");
		}
	}

	/**
	 * <code>
	 * Campos ::= Campos ; Campo
	 * Construcción de la tabla de símbolos
	 * Campos(0).campos = añadeA(Campos(1).campos, Campo.campo)
	 * Campos(0).tam = Campos(1).tam + Campo.tam
	 * Campo.desplazamientoh = Campos(1).tam
	 * Propagación de la tabla de símbolos
	 * Campos(1).tsh = Campos(0).tsh
	 * Campo.tsh = Campos(0).tsh
	 * Comprobación de las restricciones contextuales
	 * Campos(0).error = Campos(1).error or campoDuplicado(Campo.iden,Campos(1).campos)
	 * Campos(0).refsAChequear = Campos(1).refsAChequear U Campo.refsAChequear </code>
	 */
	public class CamposR1 extends Campos {

		public CamposR1(Campos campos_1, Campo campo) {
			super();
			this.campos_1 = campos_1;
			this.campo = campo;
			campos_1.registraCtx(new CamposCtx() {

				@Override
				public TS tsh_exp() {
					return CamposR1.this.tsh().val();
				}
			});
			campo.registraCtx(new CampoCtx() {

				@Override
				public TS tsh_exp() {
					return CamposR1.this.tsh().val();
				}

				@Override
				public Integer desplazamientoh_exp() {
					return CamposR1.this.campos_1.tam().val();
				}
			});
			tam().ponDependencias(campos_1.tam(), campo.tam());
			campos().ponDependencias(campos_1.campos(), campo.campo());
			err().ponDependencias(campos_1.err(), campo.iden(),
					campos_1.campos());
			refsAChequear().ponDependencias(campos_1.refsAChequear(),
					campo.refsAChequear());

			campos_1.tsh().ponDependencias(tsh());

			campo.tsh().ponDependencias(tsh());
			campo.desplazamientoh().ponDependencias(campos_1.tam());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return union(campos_1.refsAChequear().val(), campo.refsAChequear()
					.val());
		}

		@Override
		protected Error err_exp() {
			return joinErrors(campos_1.err().val(),
					campoDuplicado(campo.iden().val(), campos_1.campos().val()));
		}

		@Override
		protected Integer tam_exp() {
			return campos_1.tam().val() + campo.tam().val();
		}

		private Campos campos_1;
		private Campo campo;

		@Override
		protected List<ExpTipo> campos_exp() {
			return aniadeA(campos_1.campos().val(), campo.campo().val());
		}
	}

	public class CamposR1Debug extends CamposR1 {
		private final static String REGLA = "Campos(0) ::= Campos(1) ; Campo";

		public CamposR1Debug(Campos campos_1, Campo campo) {
			super(campos_1, campo);
			tam().fijaDescripcion(REGLA + " | Campos(0).tam");
			campos().fijaDescripcion(REGLA + " | Campos(0).campos");
			err().fijaDescripcion(REGLA + " | Campos(0).err");
			refsAChequear().fijaDescripcion(
					REGLA + " | Campos(0).refsAChequear");

			campos_1.tsh().fijaDescripcion(REGLA + " | Campos(1).tsh");

			campo.tsh().fijaDescripcion(REGLA + " | Campo.tsh");
			campo.desplazamientoh().fijaDescripcion(
					REGLA + " | Campo.desplazamientoh");
		}
	}

	/**
	 * <code>
	 * Campos ::= Campo
	 * Construcción de la tabla de símbolos
	 * Campos.campos = nuevaLista(Campo.campo)
	 * Campos.tam = Campo.tam
	 * Campo.desplazamientoh = 0
	 * Propagación de la tabla de símbolos
	 * Campo.tsh = Campos.tsh
	 * Comprobación de las restricciones contextuales
	 * Campos.error = Campo.error
	 * Campos.refsAChequear = Campo.refsAChequear
	 */
	public class CamposR2 extends Campos {

		public CamposR2(Campo campo) {
			super();
			this.campo = campo;
			campo.registraCtx(new CampoCtx() {

				@Override
				public TS tsh_exp() {
					return CamposR2.this.tsh().val();
				}

				@Override
				public Integer desplazamientoh_exp() {
					return 0;
				}
			});
			tam().ponDependencias(campo.tam());
			campos().ponDependencias(campo.campo());
			err().ponDependencias(campo.err());
			refsAChequear().ponDependencias(campo.refsAChequear());

			campo.tsh().ponDependencias(tsh());
		}

		@Override
		protected List<ExpTipo> refsAChequear_exp() {
			return campo.refsAChequear().val();
		}

		@Override
		protected Error err_exp() {
			return campo.err().val();
		}

		@Override
		protected Integer tam_exp() {
			return campo.tam().val();
		}

		@Override
		protected List<ExpTipo> campos_exp() {
			return nuevaLista(campo.campo().val());
		}

		private Campo campo;
	}

	public class CamposR2Debug extends CamposR2 {
		private final static String REGLA = "Campos ::= Campo";

		public CamposR2Debug(Campo campo) {
			super(campo);
			tam().fijaDescripcion(REGLA + " | Campos.tam");
			campos().fijaDescripcion(REGLA + " | Campos.campos");
			err().fijaDescripcion(REGLA + " | Campos.err");
			refsAChequear().fijaDescripcion(REGLA + " | Campos.refsAChequear");

			campo.tsh().fijaDescripcion(REGLA + " | Campo.tsh");
			campo.desplazamientoh().fijaDescripcion(
					REGLA + " | Campo.desplazamientoh");
		}
	}

	/**
	 * <code>
	 * Campo ::= Tipo IDEN
	 * Construcción de la tabla de símbolos
	 * Campo.campo = <id: IDEN.lex, tipo: Tipo.tipo, desplazamiento: Campo.desplazamientoh>
	 * Campo.tam = Tipo.tipo.tam
	 * Propagación de la tabla de símbolos
	 * Tipo.tsh = Campo.tsh
	 * Comprobación de las restricciones contextuales
	 * Campo.iden = IDEN.lex
	 * Campo.error = Tipo.error or tipoRefIncorrecto(Campo.tsh,Tipo.tipo)
	 * Campo.refsAChequear = Tipo.refsAChequear
	 * </code>
	 */
	public class CampoR1 extends Campo {

		public CampoR1(Tipo tipo, Token IDEN) {
			super();
			this.tipo = tipo;
			this.iden = IDEN;
			tipo.registraCtx(new TipoCtx() {

				@Override
				public TS tsh_exp() {
					return CampoR1.this.tsh().val();
				}
			});
			campo().ponDependencias(desplazamientoh(), tipo.tipo());// TODO?tipo.tipo()
			err().ponDependencias(tipo.err(), tsh(), tipo.tipo());// ?tipo.tipo()
			tam().ponDependencias(tipo.tipo());// ?¿
			// ni clase ni tipo ni tam ni iden dependencias creo
			refsAChequear().ponDependencias(tipo.refsAChequear());
			tipo.tsh().ponDependencias(tsh());
		}

		protected final String iden_exp() {
			return iden.lex();
		}

		protected final Integer fila_exp() {
			return new Integer(iden.leeFila());
		}

		protected final Integer col_exp() {
			return new Integer(iden.leeCol());
		}

		@Override
		protected final List<ExpTipo> refsAChequear_exp() {
			return tipo.refsAChequear().val();
		}

		@Override
		protected final Error err_exp() {
			return joinErrors(tipo.err().val(),
					tipoRefIncorrecto(tsh().val(), tipo.tipo_exp()));
		}

		@Override
		protected Integer tam_exp() {
			return tipo.tipo_exp().tam();
		}

		@Override
		protected ExpTipo campo_exp() {
			return ExpTipo.nuevaExpTipoCampo(iden.lex(), tipo.tipo().val(),
					desplazamientoh().val());
		}

		private Token iden;
		private Tipo tipo;
	}

	public class CampoR1Debug extends CampoR1 {
		private final static String REGLA = "Campo ::= Tipo IDEN";

		public CampoR1Debug(Tipo tipo, Token iden) {
			super(tipo, iden);
			iden().fijaDescripcion(REGLA + " | Campo.iden");
			campo().fijaDescripcion(REGLA + " | Campo.campo");
			err().fijaDescripcion(REGLA + " | Campo.err");
			tam().fijaDescripcion(REGLA + " | Campo.tam");
			refsAChequear().fijaDescripcion(REGLA + " | Campo.refsAChequear");
			tipo.tsh().fijaDescripcion(REGLA + " | Tipo.tsh");
		}
	}

	/**
	 * <code>
	 * Instrucciones ::= Instrucciones ; Instruccion
	 * Propagación de la tabla de símbolos
	 * Instrucciones(1).tsh = Instrucciones(0).tsh
	 * Instruccion.tsh = Instrucciones(0).tsh
	 * Comprobación de las restricciones contextuales
	 * Instrucciones(0).error = Instrucciones(1).error or Instruccion.error
	 * Generación de código
	 * Instrucciones(1).etqh = Instrucciones(0).etqh
	 * Instruccion.etqh = Instrucciones(1).etq
	 * Instrucciones(0).etq = Instruccion.etq
	 * Instrucciones(0).cod = Instrucciones(1).cod || Instruccion.cod
	 * Instrucciones(0).llamadasPendientes = Instrucciones(1).llamadasPendientes U
	 * Instruccion.llamadasPendientes </code>
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
			llamadasPendientes().ponDependencias(insts_1.llamadasPendientes(),
					inst.llamadasPendientes());
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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return listaIntegerVacia();
		}

	}

	public class InstsR1Debug extends InstsR1 {
		private final static String REGLA = "Instrucciones(0) ::= Instrucciones(1) ; Instruccion";

		public InstsR1Debug(Insts insts_1, Inst inst) {
			super(insts_1, inst);
			insts_1.tsh().fijaDescripcion(REGLA + " | Instrucciones(1).tsh");
			insts_1.etqh().fijaDescripcion(REGLA + " | Instrucciones(1).etqh");
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Instrucciones(0).llamadasPendientes");
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
	 * Instrucciones.llamadasPendientes = Instruccion.llamadasPendientes
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
			llamadasPendientes().ponDependencias(inst.llamadasPendientes());

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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return listaIntegerVacia();
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
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Instrucciones.llamadasPendientes");
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
	 * Instrucciones.llamadasPendientes = Instruccion.llamadasPendientes
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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return listaIntegerVacia();
		}

		private Inst iasig;

	}

	public class InstR1Debug extends InstR1 {
		private final static String REGLA = "Instruccion ::= IAsignacion";

		public InstR1Debug(Inst iAsig) {
			super(iAsig);
			err().fijaDescripcion(REGLA + " | Instruccion.err");
			cod().fijaDescripcion(REGLA + " | Instruccion.cod");
			etq().fijaDescripcion(REGLA + " | Instruccion.etq");
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
	 * Instruccion.llamadasPendientes = IIF.llamadasPendientes
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
			llamadasPendientes().ponDependencias(iIF.llamadasPendientes());
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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return iIF.llamadasPendientes().val();
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
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Instruccion.llamadasPendientes");
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
	 * Instruccion.llamadasPendientes = IDO.llamadasPendientes
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
			llamadasPendientes().ponDependencias(iDO.llamadasPendientes());
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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return iDO.llamadasPendientes().val();
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
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Instruccion.llamadasPendientes");
			iDO.tsh().fijaDescripcion(REGLA + " | IDO.tsh");
			iDO.etqh().fijaDescripcion(REGLA + " | IDO.etqh");
		}
	}

	/**
	 * <code>
	 * Instruccion ::= INew
	 * INew.tsh = Instruccion.tsh
	 * Instruccion.error = INew.error
	 * INew.etqh = Instruccion.etqh
	 * Instruccion.etq = INew.etq
	 * Instruccion.cod = INew.cod
	 * Instrucciones.llamadasPendientes = vacio
	 * </code>
	 */
	public class InstR4 extends Inst {

		public InstR4(Inst iNew) {
			super();
			this.iNew = iNew;
			iNew.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstR4.this.tsh().val();
				}

				public int etqh_exp() {
					return InstR4.this.etqh().val();
				}
			});
			etq().ponDependencias(iNew.etq());
			cod().ponDependencias(iNew.cod());
			err().ponDependencias(iNew.err());
			iNew.etqh().ponDependencias(etqh());
			iNew.tsh().ponDependencias(tsh());
		}

		public Integer etq_exp() {
			return iNew.etq().val();

		}

		public Error err_exp() {
			return iNew.err().val();
		}

		public List<Instruccion> cod_exp() {
			return iNew.cod().val();
		}

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return listaIntegerVacia();
		}

		private Inst iNew;

	}

	public class InstR4Debug extends InstR4 {
		private final static String REGLA = "Instruccion ::= INew";

		public InstR4Debug(Inst iNew) {
			super(iNew);
			err().fijaDescripcion(REGLA + " | Instrucciones.err");
			cod().fijaDescripcion(REGLA + " | Instrucciones.cod");
			etq().fijaDescripcion(REGLA + " | Instrucciones.etq");
			iNew.etqh().fijaDescripcion(REGLA + " | INew.etqh");
			iNew.tsh().fijaDescripcion(REGLA + " | INew.tsh");
		}
	}

	/**
	 * <code>
	 * Instruccion ::= IDispose
	 * IDispose.tsh = Instruccion.tsh
	 * Instruccion.error = IDispose.error
	 * IDispose.etqh = Instruccion.etqh
	 * Instruccion.etq = IDispose.etq
	 * Instruccion.cod = IDispose.cod
	 * Instrucciones.llamadasPendientes = vacio
	 * </code>
	 */
	public class InstR6 extends Inst {

		public InstR6(Inst iDispose) {
			super();
			this.iDispose = iDispose;
			iDispose.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstR6.this.tsh().val();
				}

				public int etqh_exp() {
					return InstR6.this.etqh().val();
				}
			});
			etq().ponDependencias(iDispose.etq());
			cod().ponDependencias(iDispose.cod());
			err().ponDependencias(iDispose.err());
			iDispose.etqh().ponDependencias(etqh());
			iDispose.tsh().ponDependencias(tsh());
		}

		public Integer etq_exp() {
			return iDispose.etq().val();

		}

		public Error err_exp() {
			return iDispose.err().val();
		}

		public List<Instruccion> cod_exp() {
			return iDispose.cod().val();
		}

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return listaIntegerVacia();
		}

		private Inst iDispose;
	}

	public class InstR6Debug extends InstR6 {
		private final static String REGLA = "Instruccion ::= IDispose";

		public InstR6Debug(Inst iDispose) {
			super(iDispose);
			err().fijaDescripcion(REGLA + " | Instrucciones.err");
			cod().fijaDescripcion(REGLA + " | Instrucciones.cod");
			etq().fijaDescripcion(REGLA + " | Instrucciones.etq");
			iDispose.etqh().fijaDescripcion(REGLA + " | IDispose.etqh");
			iDispose.tsh().fijaDescripcion(REGLA + " | IDispose.tsh");
		}
	}

	/**
	 * <code>
	 * Instruccion ::= ILectura
	 * ILectura.tsh = Instruccion.tsh
	 * Instruccion.error = ILectura.error
	 * ILectura.etqh = Instruccion.etqh
	 * Instruccion.etq = ILectura.etq
	 * Instruccion.cod = ILectura.cod
	 * Instrucciones.llamadasPendientes = vacio
	 * </code>
	 */
	public class InstR7 extends Inst {

		public InstR7(Inst iLectura) {
			super();
			this.iLectura = iLectura;
			iLectura.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstR7.this.tsh().val();
				}

				public int etqh_exp() {
					return InstR7.this.etqh().val();
				}
			});
			etq().ponDependencias(iLectura.etq());
			cod().ponDependencias(iLectura.cod());
			err().ponDependencias(iLectura.err());
			iLectura.etqh().ponDependencias(etqh());
			iLectura.tsh().ponDependencias(tsh());
		}

		public Integer etq_exp() {
			return iLectura.etq().val();

		}

		public Error err_exp() {
			return iLectura.err().val();
		}

		public List<Instruccion> cod_exp() {
			return iLectura.cod().val();
		}

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return listaIntegerVacia();
		}

		private Inst iLectura;

	}

	public class InstR7Debug extends InstR7 {
		private final static String REGLA = "Instruccion ::= ILectura";

		public InstR7Debug(Inst iLectura) {
			super(iLectura);
			err().fijaDescripcion(REGLA + " | Instrucciones.err");
			cod().fijaDescripcion(REGLA + " | Instrucciones.cod");
			etq().fijaDescripcion(REGLA + " | Instrucciones.etq");
			iLectura.etqh().fijaDescripcion(REGLA + " | ILectura.etqh");
			iLectura.tsh().fijaDescripcion(REGLA + " | ILectura.tsh");
		}
	}

	/**
	 * <code>
	 * Instruccion ::= ILectura
	 * ILectura.tsh = Instruccion.tsh
	 * Instruccion.error = ILectura.error
	 * ILectura.etqh = Instruccion.etqh
	 * Instruccion.etq = ILectura.etq
	 * Instruccion.cod = ILectura.cod
	 * Instrucciones.llamadasPendientes = vacio
	 * </code>
	 */
	public class InstR8 extends Inst {

		public InstR8(Inst iEscritura) {
			super();
			this.iEscritura = iEscritura;
			iEscritura.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstR8.this.tsh().val();
				}

				public int etqh_exp() {
					return InstR8.this.etqh().val();
				}
			});
			etq().ponDependencias(iEscritura.etq());
			cod().ponDependencias(iEscritura.cod());
			err().ponDependencias(iEscritura.err());
			iEscritura.etqh().ponDependencias(etqh());
			iEscritura.tsh().ponDependencias(tsh());
		}

		public Integer etq_exp() {
			return iEscritura.etq().val();

		}

		public Error err_exp() {
			return iEscritura.err().val();
		}

		public List<Instruccion> cod_exp() {
			return iEscritura.cod().val();
		}

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return listaIntegerVacia();
		}

		private Inst iEscritura;

	}

	public class InstR8Debug extends InstR8 {
		private final static String REGLA = "Instruccion ::= IEscritura";

		public InstR8Debug(Inst iEscritura) {
			super(iEscritura);
			err().fijaDescripcion(REGLA + " | Instrucciones.err");
			cod().fijaDescripcion(REGLA + " | Instrucciones.cod");
			etq().fijaDescripcion(REGLA + " | Instrucciones.etq");
			iEscritura.etqh().fijaDescripcion(REGLA + " | IEscritura.etqh");
			iEscritura.tsh().fijaDescripcion(REGLA + " | IEscritura.tsh");
		}
	}

	/**
	 * <code>
	 * Instruccion ::= ILlamada 
	 * ILlamada.tsh = Instruccion.tsh 
	 * Instruccion.error = ILlamada.error 
	 * ILlamada.etqh = Instruccion.etqh 
	 * Instruccion.etq = ILlamada.etq
	 * Instruccion.cod = ILlamada.cod
	 * Instruccion.llamadasPendientes = ILlamada.llamadasPendientes
	 * </code>
	 */
	public class InstR9 extends Inst {

		public InstR9(Inst iLlamada) {
			super();
			this.iLlamada = iLlamada;
			iLlamada.registraCtx(new InstCtx() {
				public TS tsh_exp() {
					return InstR9.this.tsh().val();
				}

				public int etqh_exp() {
					return InstR9.this.etqh().val();
				}

			});
			err().ponDependencias(iLlamada.err());
			etq().ponDependencias(iLlamada.etq());
			cod().ponDependencias(iLlamada.cod());
			iLlamada.tsh().ponDependencias(tsh());
			iLlamada.etqh().ponDependencias(etqh());
			llamadasPendientes().ponDependencias(iLlamada.llamadasPendientes());
		}

		public Integer etq_exp() {
			return iLlamada.etq().val();
		}

		public Error err_exp() {
			return iLlamada.err().val();
		}

		public List<Instruccion> cod_exp() {
			return iLlamada.cod().val();
		}

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return iLlamada.llamadasPendientes().val();
		}

		private Inst iLlamada;
	}

	public class InstR9Debug extends InstR9 {
		private final static String REGLA = "Instruccion ::= ILlamada";

		public InstR9Debug(Inst iLlamada) {
			super(iLlamada);
			err().fijaDescripcion(REGLA + " | Instruccion.err");
			etq().fijaDescripcion(REGLA + " | Instruccion.etq");
			cod().fijaDescripcion(REGLA + " | Instruccion.cod");
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Instruccion.llamadasPendientes");
			iLlamada.tsh().fijaDescripcion(REGLA + " | ILlamada.tsh");
			iLlamada.etqh().fijaDescripcion(REGLA + " | ILlamada.etqh");
		}
	}

	/**
	 * <code>
	 * ILlamada ::= IDEN ( ParametrosReales )
	 * Propagación de la tabla de símbolos
	 * ParametrosReales.tsh = ILlamada.tsh
	 * Comprobación de las restricciones contextuales
	 * ILlamada.error = not llamadaCorrecta(ILlamada.tsh,IDEN.lex,ParametrosReales.nparams)
	 * ParametrosReales.subprogramah = IDEN.lex
	 * Generación de código
	 * ParametrosReales.etqh = ILlamada.etqh
	 * ILlamada.etq = ParametrosReales.etq + numeroInstruccionesFinLLamada()
	 * ILlamada.cod = ParametrosReales.cod ||
	 * codigoFinLlamada(IDEN.lex,ILlamada.tsh,ParametrosReales.etq + numeroInstruccionesFinLLamada())
	 * ILlamada.llamadasPendientes = direccionSiSaltoIndefinido(IDEN.lex,ILlamada.tsh,ParametrosReales.etq) </code>
	 */
	public class ILlamadaR1 extends Inst {

		public ILlamadaR1(Token iden, ParamsReales paramsReales) {
			super();
			this.iden = iden;
			this.paramsReales = paramsReales;
			paramsReales.registraCtx(new ParamsRealesCtx() {
				public TS tsh_exp() {
					return ILlamadaR1.this.tsh().val();
				}

				public int etqh_exp() {
					return ILlamadaR1.this.etqh().val();
				}

				@Override
				public String subprogramah_exp() {
					return ILlamadaR1.this.iden.lex();
				}

			});
			err().ponDependencias(tsh(), paramsReales.nparams());
			etq().ponDependencias(paramsReales.etq());
			cod().ponDependencias(paramsReales.cod(), tsh(), paramsReales.etq());
			llamadasPendientes().ponDependencias(tsh(), paramsReales.etq());
			paramsReales.tsh().ponDependencias(tsh());
		}

		public Integer etq_exp() {
			return paramsReales.etq().val() + numeroInstruccionesFinLlamada();
		}

		public Error err_exp() {
			if (llamadaCorrecta(tsh().val(), iden.lex(), paramsReales.nparams()
					.val()))
				return noError();
			else
				return new Error("Error de llamada a " + iden.lex()
						+ " con nparams: " + paramsReales.nparams().val());
		}

		public List<Instruccion> cod_exp() {
			return concat(
					paramsReales.cod().val(),
					codigoFinLlamada(iden.lex(), tsh().val(), paramsReales
							.etq().val() + numeroInstruccionesFinLlamada()));
		}

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return direccionSiSaltoIndefinido(iden.lex(), tsh().val(),
					paramsReales.etq().val());
		}

		private Token iden;
		private ParamsReales paramsReales;
	}

	public class ILlamadaR1Debug extends ILlamadaR1 {
		private final static String REGLA = "ILlamada ::= IDEN ( ParametrosReales )";

		public ILlamadaR1Debug(Token iden, ParamsReales paramsReales) {
			super(iden, paramsReales);
			err().fijaDescripcion(REGLA + " | ILlamada.err");
			etq().fijaDescripcion(REGLA + " | ILlamada.etq");
			cod().fijaDescripcion(REGLA + " | ILlamada.cod");
			llamadasPendientes().fijaDescripcion(
					REGLA + " | ILlamada.llamadasPendientes");
			paramsReales.tsh().fijaDescripcion(
					REGLA + " | ParametrosReales.tsh");
		}
	}

	/**
	 * <code>
	 * ParametrosReales ::= ListaParametrosReales
	 * Propagación de la tabla de símbolos
	 * ListaParametrosReales.tsh = ParametrosReales.tsh
	 * Comprobación de las restricciones contextuales
	 * ParametrosReales.error = ListaParametrosReales.error
	 * ListaParametrosReales.subprogramah = ParametrosReales.subprogramah
	 * ParametrosReales.nparams = ListaParametrosReales.nparams
	 * Generación de código
	 * ListaParametrosReales.etqh = ParametrosReales.etqh + numeroInstruccionesInicioLlamada()
	 * ParametrosReales.etq = ListaParametrosReales.etq + 1
	 * ParametrosReales.cod = codigoInicioLlamada(nivelDe(ParametrosReales.subprogramah,
	 * ParametrosReales.tsh),*ParametrosReales.etq*) || ListaParametrosReales.cod || desapila() </code>
	 */
	public class ParamsRealesR1 extends ParamsReales {

		public ParamsRealesR1(ListaParamsReales listaParamsReales) {
			super();
			this.listaParamsReales = listaParamsReales;
			listaParamsReales.registraCtx(new ListaParamsRealesCtx() {
				public TS tsh_exp() {
					return ParamsRealesR1.this.tsh().val();
				}

				public int etqh_exp() {
					return ParamsRealesR1.this.etqh().val()
							+ numeroInstruccionesInicioLlamada();
				}

				@Override
				public String subprogramah_exp() {
					return ParamsRealesR1.this.subProgramah().val();
				}

			});
			err().ponDependencias(listaParamsReales.err());
			etq().ponDependencias(listaParamsReales.etq());
			cod().ponDependencias(subProgramah(), tsh(),
					listaParamsReales.cod());
			listaParamsReales.tsh().ponDependencias(tsh());
			listaParamsReales.etqh().ponDependencias(etqh());
			listaParamsReales.subProgramah().ponDependencias(subProgramah());
		}

		public Integer etq_exp() {
			return listaParamsReales.etq().val() + 1;
		}

		public Error err_exp() {
			return listaParamsReales.err().val();
		}

		public List<Instruccion> cod_exp() {
			List<Instruccion> c = concat(
					codigoInicioLlamada(
							nivelDe(subProgramah().val(), tsh().val()), etq()
									.val()), listaParamsReales.cod().val());
			return concat(c, desapila());
		}

		@Override
		protected Integer nparams_exp() {
			return listaParamsReales.nparams().val();
		}

		private ListaParamsReales listaParamsReales;

	}

	public class ParamsRealesR1Debug extends ParamsRealesR1 {
		private final static String REGLA = "ParametrosReales ::= ListaParametrosReales";

		public ParamsRealesR1Debug(ListaParamsReales listaParamsReales) {
			super(listaParamsReales);
			err().fijaDescripcion(REGLA + " | ParametrosReales.err");
			etq().fijaDescripcion(REGLA + " | ParametrosReales.etq");
			cod().fijaDescripcion(REGLA + " | ParametrosReales.cod");
			listaParamsReales.tsh().fijaDescripcion(
					REGLA + " | ListaParametrosReales.tsh");
			listaParamsReales.etqh().fijaDescripcion(
					REGLA + " | ParametrosReales.etqh");
			listaParamsReales.subProgramah().fijaDescripcion(
					REGLA + " | ParametrosReales.subProgramah");
		}
	}

	/**
	 * <code>
	 * 	ParametrosReales ::= ListaParametrosReales
	 * Propagación de la tabla de símbolos
	 * ListaParametrosReales.tsh = ParametrosReales.tsh
	 * Comprobación de las restricciones contextuales
	 * ParametrosReales.error = ListaParametrosReales.error
	 * ListaParametrosReales.subprogramah = ParametrosReales.subprogramah
	 * ParametrosReales.nparams = ListaParametrosReales.nparams
	 * Generación de código
	 * ListaParametrosReales.etqh = ParametrosReales.etqh + numeroInstruccionesInicioLlamada()
	 * ParametrosReales.etq = ListaParametrosReales.etq + 1
	 * ParametrosReales.cod = codigoInicioLlamada(nivelDe(ParametrosReales.subprogramah,
	 * ParametrosReales.tsh)) || ListaParametrosReales.cod || desapila() </code>
	 */
	public class ParamsRealesR2 extends ParamsReales {

		public ParamsRealesR2() {
			super();
			etq().ponDependencias(etqh());
		}

		public Integer etq_exp() {
			return etqh().val();
		}

		public Error err_exp() {
			return noError();
		}

		public List<Instruccion> cod_exp() {
			return programaVacio();
		}

		@Override
		protected Integer nparams_exp() {
			return 0;
		}

	}

	public class ParamsRealesR2Debug extends ParamsRealesR2 {
		private final static String REGLA = "ParametrosReales ::= lambda";

		public ParamsRealesR2Debug() {
			super();
			etq().fijaDescripcion(REGLA + " | ParametrosReales.etq");
		}
	}

	/**
	 * <code>
	 * ListaParametrosReales ::= ListaParametrosReales , Exp0
	 * Propagación de la tabla de símbolos
	 * ListaParametrosReales(1).tsh = ListaParametrosReales(0).tsh
	 * Exp0.tsh = ListaParametrosReales(0).tsh
	 * Comprobación de las restricciones contextuales
	 * ListaParametrosReales(0).error = ListaParametrosReales(1).error or tipoError(Exp0.tipo) 
	 * or not parametroCorrecto(ListaParametrosReales(0).subprogramah,ListaParametrosReales(1).nparams + 1,
	 * Exp0.tipo, Exp0.esDesignador,ListaParametrosReales(0).tsh)
	 * ListaParametrosReales(1).subprogramah = ListaParametrosReales(0).subprogramah
	 * ListaParametrosReales(0).nparams = ListaParametrosReales(1).nparams+1
	 * ListaParametrosReales(0).cod = ListaParametrosReales(1).cod || copia() || Exp0.cod ||
	 * codigoPaso(ListaParametrosReales(0).subprogramah, ListaParametrosReales(1).nparams, Exp0.tipo, Exp0.esDesignador,
	 * ListaParametrosReales(0).tsh)
	 */
	public class ListaParamsRealesR1 extends ListaParamsReales {

		public ListaParamsRealesR1(ListaParamsReales listaParamsReales_1,
				Exp0 exp0) {
			super();
			this.listaParamsReales_1 = listaParamsReales_1;
			this.exp0 = exp0;
			listaParamsReales_1.registraCtx(new ListaParamsRealesCtx() {
				public TS tsh_exp() {
					return ListaParamsRealesR1.this.tsh().val();
				}

				@Override
				public String subprogramah_exp() {
					return ListaParamsRealesR1.this.subProgramah().val();
				}

				@Override
				public int etqh_exp() {
					return ListaParamsRealesR1.this.etq().val();
				}
			});
			exp0.registraCtx(new ExpCtx() {

				@Override
				public TS tsh_exp() {
					return ListaParamsRealesR1.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return ListaParamsRealesR1.this.listaParamsReales_1.etq()
							.val();
				}
			});
			// TODO añadir?exp0.tipo(),exp0.esDesignador() en estas 2
			err().ponDependencias(listaParamsReales_1.err(), exp0.tipo(),
					subProgramah(), listaParamsReales_1.nparams(), tsh());
			cod().ponDependencias(subProgramah(), tsh(), exp0.tipo(),
					listaParamsReales_1.cod(), exp0.cod(), subProgramah(),
					listaParamsReales_1.nparams(), tsh());
			nparams().ponDependencias(listaParamsReales_1.nparams());
			etq().ponDependencias(exp0.etq());

			exp0.tsh().ponDependencias(tsh());
			exp0.etqh().ponDependencias(listaParamsReales_1.etq());

			listaParamsReales_1.tsh().ponDependencias(tsh());
			listaParamsReales_1.etqh().ponDependencias(etq());
			listaParamsReales_1.subProgramah().ponDependencias(subProgramah());
		}

		public Integer etq_exp() {
			return exp0.etq().val() + numeroInstruccionesCodigoPaso();
		}

		public Error err_exp() {
			Error e = joinErrors(listaParamsReales_1.err().val(),
					tipoError(exp0.tipo().val()));
			if (parametroCorrecto(subProgramah().val(), listaParamsReales_1
					.nparams().val() + 1, exp0.tipo().val(), exp0
					.esDesignador().val(), tsh().val()))
				return e;
			else
				return joinErrors(e, new Error("Parametro incorrecto en: "
						+ subProgramah()));
		}

		public List<Instruccion> cod_exp() {
			List<Instruccion> o = concat(listaParamsReales_1.cod().val(),
					copia());
			o = concat(o, exp0.cod().val());
			return concat(
					o,
					codigoPaso(subProgramah().val(), listaParamsReales_1
							.nparams().val(), exp0.tipo().val(), exp0
							.esDesignador().val(), tsh().val()));
		}

		@Override
		protected Integer nparams_exp() {
			return listaParamsReales_1.nparams().val() + 1;
		}

		private ListaParamsReales listaParamsReales_1;
		private Exp0 exp0;
	}

	public class ListaParamsRealesR1Debug extends ListaParamsRealesR1 {
		private final static String REGLA = "ListaParametrosReales(0) ::= ListaParametrosReales(1) , Exp0";

		public ListaParamsRealesR1Debug(ListaParamsReales listaParamsReales_1,
				Exp0 exp0) {
			super(listaParamsReales_1, exp0);
			err().fijaDescripcion(REGLA + " | ParametrosReales.err");
			etq().fijaDescripcion(REGLA + " | ParametrosReales.etq");
			cod().fijaDescripcion(REGLA + " | ParametrosReales.cod");
			listaParamsReales_1.tsh().fijaDescripcion(
					REGLA + " | ListaParametrosReales.tsh");
			listaParamsReales_1.etqh().fijaDescripcion(
					REGLA + " | ParametrosReales.etqh");
			listaParamsReales_1.subProgramah().fijaDescripcion(
					REGLA + " | ParametrosReales.subProgramah");
		}
	}

	/**
	 * <code>
	 * ListaParametrosReales ::= Exp0
	 * Propagación de la tabla de símbolos
	 * Exp0.tsh = ListaParametrosReales.tsh
	 * Comprobación de las restricciones contextuales
	 * ListaParametrosReales.error = tipoError(Exp0.tipo) or
	 * not parametroCorrecto(ListaParametrosReales.subprogramah
	 * ListaParametrosReales(0).nparams = 1
	 * Generación de código
	 * Exp0.etqh = ListaParametrosReales.etqh + 1
	 * ListaParametrosReales.etq = Exp0.etq + numeroInstruccionesCodigoPaso()
	 * ListaParametrosReales.cod = copia() || Exp0.cod ||
	 * codigoPaso(ListaParametrosReales.subprogramah,1,Exp0.tipo, Exp0.esDesignador, ListaParametrosReales.tsh)
	 */
	public class ListaParamsRealesR2 extends ListaParamsReales {

		public ListaParamsRealesR2(Exp0 exp0) {
			super();
			this.exp0 = exp0;
			exp0.registraCtx(new ExpCtx() {

				@Override
				public TS tsh_exp() {
					return ListaParamsRealesR2.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return ListaParamsRealesR2.this.etqh().val() + 1;
				}
			});
			// TODO añadir?exp0.tipo(),exp0.esDesignador() en estas 2
			err().ponDependencias(exp0.tipo(), exp0.esDesignador(),
					subProgramah(), tsh());
			cod().ponDependencias(subProgramah(), tsh(), exp0.tipo(),
					exp0.cod(), subProgramah());
			etq().ponDependencias(exp0.etq());

			exp0.tsh().ponDependencias(tsh());
			exp0.etqh().ponDependencias(etqh());
		}

		public Integer etq_exp() {
			return exp0.etq().val() + numeroInstruccionesCodigoPaso();
		}

		public Error err_exp() {
			Error e = tipoError(exp0.tipo().val());
			if (parametroCorrecto(subProgramah().val(), 1, exp0.tipo().val(),
					exp0.esDesignador().val(), tsh().val()))
				return e;
			else
				return joinErrors(e, new Error("Parametro incorrecto en: "
						+ subProgramah()));
		}

		public List<Instruccion> cod_exp() {
			List<Instruccion> o = concat(copia(), exp0.cod().val());
			return concat(
					o,
					codigoPaso(subProgramah().val(), 1, exp0.tipo().val(), exp0
							.esDesignador().val(), tsh().val()));
		}

		@Override
		protected Integer nparams_exp() {
			return 1;
		}

		private Exp0 exp0;
	}

	public class ListaParamsRealesR2Debug extends ListaParamsRealesR2 {
		private final static String REGLA = "ListaParametrosReales(0) ::= ListaParametrosReales(1) , Exp0";

		public ListaParamsRealesR2Debug(Exp0 exp0) {
			super(exp0);
			err().fijaDescripcion(REGLA + " | ListaParametrosReales.err");
			etq().fijaDescripcion(REGLA + " | ListaParametrosReales.etq");
			cod().fijaDescripcion(REGLA + " | ListaParametrosReales.cod");
			exp0.tsh().fijaDescripcion(REGLA + " | exp0.tsh");
			exp0.etqh().fijaDescripcion(REGLA + " | exp0.etqh");
		}
	}

	/**
	 * <code>
	 * IAsignacion ::= Mem := Exp0 
	 * Propagación de la tabla de símbolos
	 * Mem.tsh = IAsignacion.tsh
	 * Exp0.tsh = IAsignacion.tsh
	 * Comprobación de las restricciones contextuales
	 * IAsignacion.error = not asignacionCorrecta(Mem.tipo,Exp0.tipo)
	 * Generación de código
	 * Mem.etqh = IAsignacion.etqh
	 * Exp0.etqh = Mem.etq + 1
	 * IAsignacion.etq = Exp0.etq + 1
	 * IAsignacion.cod = codigoAsignacion(Mem.cod,Exp0.cod,Exp0.esDesignador)* </code>
	 */
	public class IAsigR1 extends Inst {

		public IAsigR1(Mem mem, Exp0 exp0) {
			super();
			this.mem = mem;
			this.exp0 = exp0;
			exp0.registraCtx(new ExpCtx() {
				public TS tsh_exp() {
					return IAsigR1.this.tsh().val();
				}

				public Integer etqh_exp() {
					return IAsigR1.this.mem.etq().val();
				}
			});
			mem.registraCtx(new MemCtx() {

				@Override
				public TS tsh_exp() {
					return IAsigR1.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return IAsigR1.this.etqh().val();
				}
			});
			err().ponDependencias(mem.tipo(), exp0.tipo());
			etq().ponDependencias(exp0.etq());
			cod().ponDependencias(mem.cod(), exp0.cod(), exp0.esDesignador());

			mem.tsh().ponDependencias(tsh());
			mem.etqh().ponDependencias(etqh());

			exp0.tsh().ponDependencias(tsh());
			exp0.etqh().ponDependencias(mem.etq());
		}

		public Integer etq_exp() {
			return exp0.etq().val() + 1;
		}

		public Error err_exp() {
			if (!asignacionCorrecta(mem.tipo().val(), exp0.tipo().val())) {
				return new Error("La asignación no ha sido correcta");
			} else
				return new Error();
		}

		public List<Instruccion> cod_exp() {
			return codigoAsignacion(mem.cod().val(), exp0.cod().val(), exp0
					.esDesignador().val(), mem.tipo().val().tam());
		}

		private Mem mem;
		private Exp0 exp0;

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			// TODO NO USADO ni aqui ni en ninguna produccion que viene de inst,
			// pero se hace asi para no crear una nueva clase para cada una
			return null;
		}

	}

	public class IAsigR1Debug extends IAsigR1 {
		private final static String REGLA = "IAsignacion ::= Mem := Exp0";

		public IAsigR1Debug(Mem mem, Exp0 exp0) {
			super(mem, exp0);
			err().fijaDescripcion(REGLA + " | IAsignacion.err");
			etq().fijaDescripcion(REGLA + " | IAsignacion.etq");
			cod().fijaDescripcion(REGLA + " | IAsignacion.cod");
			exp0.tsh().fijaDescripcion(REGLA + " | Exp0.tsh");
			exp0.etqh().fijaDescripcion(REGLA + " | Exp0.etqh");
			mem.tsh().fijaDescripcion(REGLA + " | Mem.tsh");
			mem.etqh().fijaDescripcion(REGLA + " | Mem.etqh");
		}
	}

	/**
	 * <code>
	 * INew ::= new Mem
	 * Propagación de la tabla de símbolos
	 * Mem.tsh = INew.tsh
	 * Comprobación de las restricciones contextuales
	 * INew.error = not reservaMemoriaCorrecta(Mem.tipo)
	 * Generación de código
	 * Mem.etqh = INew.etqh
	 * INew.etq = Mem.etq + 2
	 * INew.cod = Mem.cod || reserva(tamañoObjetoApuntado(Mem.tipo,INew.tsh)) || desapila_ind()
	 */
	public class INewR1 extends Inst {

		public INewR1(Mem mem) {
			super();
			this.mem = mem;
			mem.registraCtx(new MemCtx() {

				@Override
				public TS tsh_exp() {
					return INewR1.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return INewR1.this.etqh().val();
				}
			});
			err().ponDependencias(mem.tipo());
			etq().ponDependencias(mem.etq());
			cod().ponDependencias(mem.cod(), mem.tipo(), tsh());

			mem.tsh().ponDependencias(tsh());
			mem.etqh().ponDependencias(etqh());
		}

		public Integer etq_exp() {
			return mem.etq().val() + 2;
		}

		public Error err_exp() {
			if (!reservaMemoriaCorrecta(mem.tipo().val())) {
				return new Error("La reserva de memoria no ha sido correcta");
			} else
				return new Error();
		}

		public List<Instruccion> cod_exp() {
			List<Instruccion> o = concat(
					mem.cod().val(),
					reserva(tamañoObjetoApuntado(mem.tipo().val(), tsh().val())));
			return concat(o, desapila_ind());

		}

		private Mem mem;

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return null;
		}

	}

	public class INewR1Debug extends INewR1 {
		private final static String REGLA = "INew ::= new Mem";

		public INewR1Debug(Mem mem) {
			super(mem);
			err().fijaDescripcion(REGLA + " | INew.err");
			etq().fijaDescripcion(REGLA + " | INew.etq");
			cod().fijaDescripcion(REGLA + " | INew.cod");

			mem.tsh().fijaDescripcion(REGLA + " | Mem.tsh");
			mem.etqh().fijaDescripcion(REGLA + " | Mem.etqh");
		}
	}

	/**
	 * <code>
	 * 	IDispose ::= delete Mem
	 * Propagación de la tabla de símbolos
	 * Mem.tsh = IDispose.tsh
	 * Comprobación de las restricciones contextuales
	 * IDispose.error = not liberacionMemoriaCorrecta(Mem.tipo)
	 * Generación de código
	 * Mem.etqh = IDispose.etqh
	 * IDispose.etq = Mem.etq + 1
	 * IDispose.cod = Mem.cod || libera(tamañoObjetoApuntado(Mem.tipo,IDispose.tsh))
	 */
	public class IDisposeR1 extends Inst {

		public IDisposeR1(Mem mem) {
			super();
			this.mem = mem;
			mem.registraCtx(new MemCtx() {

				@Override
				public TS tsh_exp() {
					return IDisposeR1.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return IDisposeR1.this.etqh().val();
				}
			});
			err().ponDependencias(mem.tipo());
			etq().ponDependencias(mem.etq());
			cod().ponDependencias(mem.cod(), mem.tipo(), tsh());

			mem.tsh().ponDependencias(tsh());
			mem.etqh().ponDependencias(etqh());
		}

		public Integer etq_exp() {
			return mem.etq().val() + 2;
		}

		public Error err_exp() {
			if (!liberaMemoriaCorrecta(mem.tipo().val())) {
				return new Error("La liberacion de memoria no ha sido correcta");
			} else
				return new Error();
		}

		public List<Instruccion> cod_exp() {
			List<Instruccion> o = concat(mem.cod().val(),
					libera(tamañoObjetoApuntado(mem.tipo().val(), tsh().val())));
			return concat(o, desapila_ind());

		}

		private Mem mem;

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return null;
		}

	}

	public class IDisposeR1Debug extends IDisposeR1 {
		private final static String REGLA = "IDispose ::= delete Mem";

		public IDisposeR1Debug(Mem mem) {
			super(mem);
			err().fijaDescripcion(REGLA + " | IDisposeR1Debug.err");
			etq().fijaDescripcion(REGLA + " | IDisposeR1Debug.etq");
			cod().fijaDescripcion(REGLA + " | IDisposeR1Debug.cod");

			mem.tsh().fijaDescripcion(REGLA + " | Mem.tsh");
			mem.etqh().fijaDescripcion(REGLA + " | Mem.etqh");
		}
	}

	/**
	 * <code>
	 * 	ILectura ::= leer Mem
	 * Propagación de la tabla de símbolos
	 * Mem.tsh = IDispose.tsh
	 * Comprobación de las restricciones contextuales
	 * ILectura.error = not lecturaCorrecta(Mem.tipo)
	 * Generación de código
	 * Mem.etqh = ILectura.etqh
	 * ILectura.etq = Mem.etq + 2
	 * ILectura.cod = Mem.cod || codigoLectura(Mem.tipo) || desapila_ind()
	 */
	public class ILecturaR1 extends Inst {

		public ILecturaR1(Mem mem) {
			super();
			this.mem = mem;
			mem.registraCtx(new MemCtx() {

				@Override
				public TS tsh_exp() {
					return ILecturaR1.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return ILecturaR1.this.etqh().val();
				}
			});
			err().ponDependencias(mem.tipo());
			etq().ponDependencias(mem.etq());
			cod().ponDependencias(mem.cod(), mem.tipo());

			mem.tsh().ponDependencias(tsh());
			mem.etqh().ponDependencias(etqh());
		}

		public Integer etq_exp() {
			return mem.etq().val() + 2;
		}

		public Error err_exp() {
			if (!lecturaCorrecta(mem.tipo().val())) {
				return new Error("La lectura no ha sido correcta");
			} else
				return new Error();
		}

		public List<Instruccion> cod_exp() {
			List<Instruccion> o = concat(mem.cod().val(), codigoLectura(mem
					.tipo().val()));
			return concat(o, desapila_ind());

		}

		private Mem mem;

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return null;
		}

	}

	public class ILecturaR1Debug extends ILecturaR1 {
		private final static String REGLA = "ILectura ::= leer Mem";

		public ILecturaR1Debug(Mem mem) {
			super(mem);
			err().fijaDescripcion(REGLA + " | ILectura.err");
			etq().fijaDescripcion(REGLA + " | ILectura.etq");
			cod().fijaDescripcion(REGLA + " | ILectura.cod");

			mem.tsh().fijaDescripcion(REGLA + " | Mem.tsh");
			mem.etqh().fijaDescripcion(REGLA + " | Mem.etqh");
		}
	}

	/**
	 * <code>
	 * IEscritura ::= escribir Exp0
	 * Propagación de la tabla de símbolos
	 * Exp0.tsh = IEscritura.tsh
	 * Comprobación de las restricciones contextuales
	 * IEscritura.error = not escrituraCorrecta(Exp0.tipo)
	 * Generación de código
	 * Exp0.etqh = IEscritura.etqh
	 * IEscritura.etq = Exp0.etq + 1
	 * IEscritura.cod = Exp0.cod || codigoEscritura(Exp0.tipo)
	 */
	public class IEscrituraR1 extends Inst {

		public IEscrituraR1(Exp0 exp0) {
			super();
			this.exp0 = exp0;
			exp0.registraCtx(new ExpCtx() {

				@Override
				public TS tsh_exp() {
					return IEscrituraR1.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return IEscrituraR1.this.etqh().val();
				}
			});
			err().ponDependencias(exp0.tipo());
			etq().ponDependencias(exp0.etq());
			cod().ponDependencias(exp0.cod(), exp0.tipo());

			exp0.tsh().ponDependencias(tsh());
			exp0.etqh().ponDependencias(etqh());
		}

		public Integer etq_exp() {
			return exp0.etq().val() + 1;
		}

		public Error err_exp() {
			if (!escrituraCorrecta(exp0.tipo().val())) {
				return new Error("La escritura no ha sido correcta");
			} else
				return new Error();
		}

		public List<Instruccion> cod_exp() {
			return concat(exp0.cod().val(), codigoEscritura(exp0.tipo().val()));

		}

		private Exp0 exp0;

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return null;
		}

	}

	public class IEscrituraR1Debug extends IEscrituraR1 {
		private final static String REGLA = "IEscritura ::= escribir Mem";

		public IEscrituraR1Debug(Exp0 exp0) {
			super(exp0);
			err().fijaDescripcion(REGLA + " | IEscrituraR1.err");
			etq().fijaDescripcion(REGLA + " | IEscrituraR1.etq");
			cod().fijaDescripcion(REGLA + " | IEscrituraR1.cod");
			exp0.tsh().fijaDescripcion(REGLA + " | exp0.tsh");
			exp0.etqh().fijaDescripcion(REGLA + " | exp0.etqh");
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
	 * IIF.llamadasPendientes = Casos.llamadasPendientes
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
			llamadasPendientes().ponDependencias(casos.llamadasPendientes());
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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return casos.llamadasPendientes().val();
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
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Casos.llamadasPendientes");
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
	 * IDO.llamadasPendientes = Casos.llamadasPendientes
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
			llamadasPendientes().ponDependencias(casos.llamadasPendientes());
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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return casos.llamadasPendientes().val();
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
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Casos.llamadasPendientes");
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
	 * Casos(0).llamadasPendientes = Casos(1).llamadasPendientes U Caso.llamadasPendientes
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
			llamadasPendientes().ponDependencias(casos1.llamadasPendientes(),
					caso.llamadasPendientes());
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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return unionInt(casos1.llamadasPendientes().val(), caso
					.llamadasPendientes().val());
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
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Casos0.llamadasPendientes");
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
	 * Casos.llamadasPendientes = Caso.llamadasPendientes
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
			llamadasPendientes().ponDependencias(caso.llamadasPendientes());
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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return caso.llamadasPendientes().val();
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
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Casos.llamadasPendientes");
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
	 * Caso.llamadasPendientes = Instrucciones.llamadasPendientes
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
			llamadasPendientes().ponDependencias(insts.llamadasPendientes());

		}

		public Integer etq_exp() {
			return insts.etq().val() + 1;
		}

		public Error err_exp() {
			if (noEsbooleano(exp0.tipo().val())) {
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

		@Override
		protected List<Integer> llamadasPendientes_exp() {
			return insts.llamadasPendientes().val();
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
			llamadasPendientes().fijaDescripcion(
					REGLA + " | Caso.llamadasPendientes");
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
	 * Exp0(0).esDesignador = false 
	 * Exp1(0).etqh = Exp0.etqh
	 * Exp1(1).etqh = Exp1(0).etq + unoSiCierto(Exp1(0).esDesignador)
	 * Exp0.etq = Exp1(1).etq + unoSiCierto(Exp1(1).esDesignador) + 1
	 * Exp0.cod = codigoOpComparacion(Exp1(0).cod, Exp1(1).cod, OpComparacion.cod,
	 * Exp1(0).esDesignador,Exp1(1).esDesignador)
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
					return Exp0R1.this.exp1_0.etq().val()
							+ unoSiCierto(Exp0R1.this.esDesignador().val()) + 1;
				}
			});
			exp1_0.tsh().ponDependencias(tsh());
			exp1_1.tsh().ponDependencias(tsh());
			exp1_0.etqh().ponDependencias(etqh());
			exp1_1.etqh().ponDependencias(exp1_0.etq(), esDesignador());
			cod().ponDependencias(exp1_0.cod(), exp1_1.cod(), opc.cod(),
					exp1_0.esDesignador(), exp1_1.esDesignador());
			etq().ponDependencias(exp1_1.etq(), exp1_1.esDesignador());
			tipo().ponDependencias(opc.op(), exp1_0.tipo(), exp1_1.tipo());

		}

		public List<Instruccion> cod_exp() {
			return codigoOpComparacion(exp1_0.cod().val(), exp1_1.cod().val(),
					opc.cod().val(), exp1_0.esDesignador().val(), exp1_1
							.esDesignador().val());
		}

		public Integer etq_exp() {
			return exp1_1.etq().val()
					+ unoSiCierto(exp1_1.esDesignador().val()) + 1;
		}

		public ExpTipo tipo_exp() {
			return tipoOpBin(opc.op().val(), exp1_0.tipo().val(), exp1_1.tipo()
					.val());
		}

		@Override
		protected Boolean esDesignador_exp() {
			return false;
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
			esDesignador().fijaDescripcion(REGLA + " | exp0.esDesignador");
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
	 * Exp0.esDesignador = Exp1.esDesignador
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
			esDesignador().ponDependencias(exp1.esDesignador());
		}

		public List<Instruccion> cod_exp() {
			return exp1.cod().val();
		}

		public Integer etq_exp() {
			return exp1.etq().val();
		}

		public ExpTipo tipo_exp() {
			return exp1.tipo().val();
		}

		@Override
		protected Boolean esDesignador_exp() {
			return exp1.esDesignador().val();
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
			esDesignador().fijaDescripcion(REGLA + " | exp0.esDesignador");
		}

	}

	/**
	 * <code>
	 * Exp1 ::= Exp1 OpAditivo Exp2
	 * Propagación de la tabla de símbolos
	 * Exp1(1).tsh = Exp1(0).tsh
	 * Exp2.tsh = Exp1(0).tsh
	 * Comprobación de las restricciones contextuales
	 * Exp1(0).tipo = tipoOpBin(OpAditivo.op,Exp1(1).tipo,Exp2.tipo)
	 * Exp1.esDesignador = false
	 * Generación de código
	 * Exp1(1).etqh = Exp1(0).etqh
	 * Exp2.etqh = Exp1(1).etq + unoSiCierto(Exp1(1).esDesignador)
	 * Exp1(0).etq = Exp2.etq + unoSiCierto(Exp2.esDesignador) + 1
	 * Exp1(0).cod = codigoOpAditivo(Exp1(1).cod, Exp2.cod, OpAditivo.cod,
	 * Exp1(1).esDesignador,Exp2.esDesignador)
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
					return Exp1R1.this.exp1.etq().val()
							+ unoSiCierto(Exp1R1.this.exp1.esDesignador().val());
				}
			});
			exp1.tsh().ponDependencias(tsh());
			exp2.tsh().ponDependencias(tsh());
			exp1.etqh().ponDependencias(etqh());
			exp2.etqh().ponDependencias(exp1.etq(), exp1.esDesignador());
			cod().ponDependencias(exp1.cod(), exp2.cod(), opa.cod(),
					exp1.esDesignador(), exp2.esDesignador());
			etq().ponDependencias(exp2.etq(), exp2.esDesignador());
			tipo().ponDependencias(opa.op(), exp1.tipo(), exp2.tipo());

		}

		public List<Instruccion> cod_exp() {
			return codigoOpAditivo(exp1.cod().val(), exp2.cod().val(), opa
					.cod().val(), exp1.esDesignador().val(), exp2
					.esDesignador().val());
		}

		public Integer etq_exp() {
			return exp2.etq().val() + 1
					+ unoSiCierto(exp2.esDesignador().val());
		}

		public ExpTipo tipo_exp() {
			return tipoOpBin(opa.op().val(), exp1.tipo().val(), exp2.tipo()
					.val());
		}

		@Override
		protected Boolean esDesignador_exp() {
			return false;
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
	 * Exp1.esDesignador = Exp2.esDesignador 
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
			esDesignador().ponDependencias(exp2.esDesignador());
		}

		public List<Instruccion> cod_exp() {

			return exp2.cod().val();
		}

		public Integer etq_exp() {
			return exp2.etq().val();
		}

		public ExpTipo tipo_exp() {
			return exp2.tipo().val();
		}

		@Override
		protected Boolean esDesignador_exp() {
			return exp2.esDesignador().val();
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
			esDesignador().fijaDescripcion(REGLA + " | exp1.esDesignador");
		}
	}

	/**
	 * <code>
	 * Exp2 ::= Exp2 OpMultiplicativo Exp3 
	 * Exp2(1).tsh = Exp2(0).tsh 
	 * Exp3.tsh = Exp2(0).tsh 
	 * Exp2(0).tipo = tipoOpBin(OpMultiplicativo.op,Exp2(1).tipo,Exp3.tipo)
	 * Exp2(1).etqh = Exp2(0).etqh 
	 * Exp3.etqh = Exp2(1).etq + unoSiCierto(Exp2(1).esDesignador) 
	 * Exp2(0).etq = Exp3.etq + unoSiCierto(Exp3.esDesignador) + 1
	 * Exp2(0).cod = codigoOpMultiplicativo(Exp2(1).cod, Exp3.cod, OpMultiplicativo.cod,
	 * Exp2(1).esDesignador,Exp3.esDesignador)
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
			exp3.etqh().ponDependencias(exp2.etq(), exp2.esDesignador());
			cod().ponDependencias(exp2.cod(), exp3.cod(), opm.cod(),
					exp2.esDesignador(), exp3.esDesignador());
			etq().ponDependencias(exp3.etq(), exp3.esDesignador());
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
					return Exp2R1.this.exp2.etq().val()
							+ unoSiCierto(Exp2R1.this.exp2.esDesignador().val());
				}
			});
		}

		public List<Instruccion> cod_exp() {
			return codigoOpMultiplicativo(exp2.cod().val(), exp3.cod().val(),
					opm.cod().val(), exp2.esDesignador().val(), exp3
							.esDesignador().val());
		}

		public Integer etq_exp() {
			return exp3.etq().val() + 1
					+ unoSiCierto(exp3.esDesignador().val());
		}

		public ExpTipo tipo_exp() {
			return tipoOpBin(opm.op().val(), exp2.tipo().val(), exp3.tipo()
					.val());
		}

		@Override
		protected Boolean esDesignador_exp() {
			return false;
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
	 * Exp2.esDesignador = Exp3.esDesignador
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
			esDesignador().ponDependencias(exp3.esDesignador());
		}

		public List<Instruccion> cod_exp() {
			return exp3.cod().val();
		}

		public Integer etq_exp() {
			return exp3.etq().val();
		}

		public ExpTipo tipo_exp() {
			return exp3.tipo().val();
		}

		private Exp3 exp3;

		@Override
		protected Boolean esDesignador_exp() {
			return exp3.esDesignador().val();
		}

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
			esDesignador().fijaDescripcion(REGLA + " | exp2.esDesignador");
		}
	}

	/**
	 * <code>
	 * Exp3 ::= OpUnario Exp3
	 * 
	 * Exp3(1).tsh = Exp3(0).tsh 
	 * Exp3(0).tipo = tipoOpUn(OpUnario.op,Exp3(1).tipo)
	 * Exp3(0).esDesignador = false
	 * Exp3(1).etqh = Exp3(0).etqh
	 * Exp3(0).etq = Exp3(1).etq + numeroInstruccionesOpUnario(Exp3(1).esDesignador)
	 * Exp3(0).cod = codigoOpUnario(Exp3(1).cod, OpUnario.cod, Exp3(1).esDesignador)
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
			return codigoOpUnario(exp3_1.cod().val(), opu.cod().val(), exp3_1
					.esDesignador().val());
		}

		public Integer etq_exp() {
			return exp3_1.etq().val()
					+ numeroInstruccionesOpUnario(exp3_1.esDesignador().val());
		}

		public ExpTipo tipo_exp() {
			return tipoOpUnario(opu.op().val(), exp3_1.tipo().val());
		}

		@Override
		protected Boolean esDesignador_exp() {
			return false;
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
	 * Exp3.esDesignador = Exp4.esDesignador
	 * Exp4.etqh = Exp3.etqh 
	 * Exp3.etq = Exp4.etq 
	 * Exp3.cod = Exp4.cod
	 * 
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
			esDesignador().ponDependencias(exp4.esDesignador());
		}

		public List<Instruccion> cod_exp() {
			return exp4.cod().val();
		}

		public Integer etq_exp() {
			return exp4.etq().val();
		}

		public ExpTipo tipo_exp() {
			return exp4.tipo().val();
		}

		@Override
		protected Boolean esDesignador_exp() {
			return exp4.esDesignador().val();
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
			esDesignador().fijaDescripcion(REGLA + " | exp3.esDesignador");
		}

	}

	/**
	 * <code>
	 * Exp4 ::= true
	 * 
	 * Exp4.tipo = boolean 
	 * Exp4.etq = Exp4.etqh + 1 
	 * Exp4.cod = apila_true()
	 * Exp4.esDesignador = false
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

		public ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoBoolean();
		}

		@Override
		protected Boolean esDesignador_exp() {
			return false;
		}

	}

	public class Exp4R1Debug extends Exp4R1 {
		private final static String REGLA = "Exp4 ::= true";

		public Exp4R1Debug() {
			super();
			cod().fijaDescripcion(REGLA + " | exp4.cod");
			etq().fijaDescripcion(REGLA + " | exp4.etq");
			tipo().fijaDescripcion(REGLA + " | exp4.tipo");
			esDesignador().fijaDescripcion(REGLA + " | exp4.esDesignador");
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

		public ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoBoolean();
		}

		@Override
		protected Boolean esDesignador_exp() {
			return false;
		}

	}

	public class Exp4R2Debug extends Exp4R2 {
		private final static String REGLA = "Exp4 := false";

		public Exp4R2Debug() {
			super();
			cod().fijaDescripcion(REGLA + " | exp4.cod");
			etq().fijaDescripcion(REGLA + " | exp4.etq");
			tipo().fijaDescripcion(REGLA + " | exp4.tipo");
			esDesignador().fijaDescripcion(REGLA + " | exp4.esDesignador");
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
			return single(apila_int(Integer.parseInt(num.lex())));
		}

		public Integer etq_exp() {
			return etqh().val() + 1;
		}

		public ExpTipo tipo_exp() {
			return ExpTipo.nuevaExpTipoInt();
		}

		@Override
		protected Boolean esDesignador_exp() {
			return false;
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
			esDesignador().fijaDescripcion(REGLA + " | exp4.esDesignador");
		}
	}

	/**
	 * <code>
	 * Exp4 ::= Mem
	 * Propagación de la tabla de símbolos
	 * Mem.tsh = Exp4.tsh
	 * Comprobación de las restricciones contextuales
	 * Exp4.tipo = Mem.tipo
	 * Exp4.esDesignador = true
	 * Generación de código
	 * Mem.etqh = Exp4.etqh
	 * Exp4.etq = Mem.etq
	 * Exp4.cod = Mem.cod </code>
	 */
	public class Exp4R4 extends Exp4 {

		public Exp4R4(Mem mem) {
			super();
			this.mem = mem;
			etq().ponDependencias(etqh());
			mem.tsh().ponDependencias(tsh());
			mem.etqh().ponDependencias(etqh());
			tipo().ponDependencias(mem.tipo());
			etq().ponDependencias(mem.etq());
			cod().ponDependencias(mem.cod());
			mem.registraCtx(new MemCtx() {

				@Override
				public TS tsh_exp() {
					return Exp4R4.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return Exp4R4.this.etqh().val();
				}
			});
		}

		public List<Instruccion> cod_exp() {
			return mem.cod().val();
		}

		public Integer etq_exp() {
			return mem.etq().val();
		}

		public ExpTipo tipo_exp() {
			return mem.tipo().val();
		}

		@Override
		protected Boolean esDesignador_exp() {
			return true;
		}

		private Mem mem;
	}

	public class Exp4R4Debug extends Exp4R4 {
		private final static String REGLA = "Exp4 ::= Mem";

		public Exp4R4Debug(Mem mem) {
			super(mem);
			cod().fijaDescripcion(REGLA + " | exp4.cod");
			etq().fijaDescripcion(REGLA + " | exp4.etq");
			tipo().fijaDescripcion(REGLA + " | exp4.tipo");
			mem.tsh().fijaDescripcion(REGLA + " | mem.tsh");
			mem.etqh().fijaDescripcion(REGLA + " | mem.etqh");
			esDesignador().fijaDescripcion(REGLA + " | exp4.esDesignador");
		}
	}

	/**
	 * <code>
	 * Exp4 ::= ( Exp0 )
	 * 
	 * Exp0.tsh = Exp4.tsh
	 * Exp4.tipo = Exp0.tipo 
	 * Exp4.esDesignador = Exp0.esDesignador
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

		public ExpTipo tipo_exp() {
			return exp0.tipo().val();
		}

		@Override
		protected Boolean esDesignador_exp() {
			return false;
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
			esDesignador().fijaDescripcion(REGLA + " | exp4.esDesignador");
		}
	}

	/**
	 * <code>
	 * Mem ::= IDEN
	 * Comprobación de las restricciones contextuales
	 * Mem.tipo = tipoDe(IDEN.lex,Mem.tsh)
	 * Generación de código
	 * Mem.etq = Mem.etqh + numeroInstruccionesAccesoVar(IDEN.lex,Mem.tsh)
	 * Mem.cod = codigoAccesoVar(IDEN.lex,Mem.tsh) </code>
	 */
	public class MemR1 extends Mem {

		public MemR1(Token iden) {
			this.iden = iden;
			tipo().ponDependencias(tsh());
			etq().ponDependencias(etqh(), tsh());
			cod().ponDependencias(tsh());
		}

		public List<Instruccion> cod_exp() {
			return codigoAccesoVar(iden.lex(), tsh().val());
		}

		public Integer etq_exp() {
			return etqh().val()
					+ numeroInstruccionesAccesoVar(iden.lex(), tsh().val());
		}

		public ExpTipo tipo_exp() {
			return tipoDe(iden.lex(), tsh().val());
		}

		private Token iden;
	}

	public class MemR1Debug extends MemR1 {
		private final static String REGLA = "Mem ::= IDEN";

		public MemR1Debug(Token iden) {
			super(iden);
			cod().fijaDescripcion(REGLA + " | mem.cod");
			etq().fijaDescripcion(REGLA + " | mem.etq");
			tipo().fijaDescripcion(REGLA + " | mem.tipo");
		}
	}

	/**
	 * <code>
	 * 	Mem ::= Mem.IDEN
	 * Propagación de la tabla de símbolos
	 * Mem(1).tsh = Mem(0).tsh
	 * Comprobación de las restricciones contextuales
	 * Mem(0).tipo = tipoDeSelectorCampo(Mem(1).tipo,IDEN.lex)
	 * Generación de código
	 * Mem(1).etqh = Mem(0).etqh
	 * Mem(0).etq = Mem(1).etq + 2
	 * Mem(0).cod = Mem(1).cod || apila(desplazamientoDeCampo(Mem(1).tipo,IDEN.lex)) || suma</code>
	 */
	public class MemR2 extends Mem {

		public MemR2(Mem mem1, Token iden) {
			this.mem1 = mem1;
			this.iden = iden;
			mem1.tsh().ponDependencias(tsh());
			mem1.etqh().ponDependencias(etqh());
			tipo().ponDependencias(mem1.tipo());
			etq().ponDependencias(mem1.etq());
			cod().ponDependencias(mem1.cod(), mem1.tipo());
			mem1.registraCtx(new MemCtx() {

				@Override
				public TS tsh_exp() {
					return MemR2.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return MemR2.this.etqh().val();
				}
			});
		}

		public List<Instruccion> cod_exp() {
			List<Instruccion> o = concat(mem1.cod().val(),
					apila(desplazamientoDeCampo(mem1.tipo().val(), iden.lex())));
			return concat(o, suma());
		}

		public Integer etq_exp() {
			return mem1.etq().val() + 2;
		}

		public ExpTipo tipo_exp() {
			return tipoDeSelectorCampo(mem1.tipo().val(), iden.lex());
		}

		private Token iden;
		private Mem mem1;

	}

	public class MemR2Debug extends MemR2 {
		private final static String REGLA = "Mem(0) ::= Mem(1).IDEN";

		public MemR2Debug(Mem mem1, Token iden) {
			super(mem1, iden);
			cod().fijaDescripcion(REGLA + " | mem(0).cod");
			etq().fijaDescripcion(REGLA + " | mem(0).etq");
			tipo().fijaDescripcion(REGLA + " | mem(0).tipo");
			mem1.tsh().fijaDescripcion(REGLA + " | mem(1).tsh");
			mem1.etqh().fijaDescripcion(REGLA + " | mem(1).etqh");
		}
	}

	/**
	 * <code>
	 * Mem ::= Mem^
	 * Propagación de la tabla de símbolos
	 * Mem(1).tsh = Mem(0).tsh
	 * Comprobación de las restricciones contextuales
	 * Mem(0).tipo = tipoDeIndireccion(Mem(1).tipo)
	 * Generación de código
	 * Mem(1).etqh = Mem(0).etqh
	 * Mem(0).etq = Mem(1).etq+1
	 * Mem(0).cod = Mem(1).cod || apila_ind
	 */
	public class MemR3 extends Mem {

		public MemR3(Mem mem1) {
			this.mem1 = mem1;

			mem1.tsh().ponDependencias(tsh());
			mem1.etqh().ponDependencias(etqh());
			tipo().ponDependencias(mem1.tipo());
			etq().ponDependencias(mem1.etq());
			cod().ponDependencias(mem1.cod());
			mem1.registraCtx(new MemCtx() {

				@Override
				public TS tsh_exp() {
					return MemR3.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return MemR3.this.etqh().val();
				}
			});
		}

		public List<Instruccion> cod_exp() {
			return concat(mem1.cod().val(), apila_ind());
		}

		public Integer etq_exp() {
			return mem1.etq().val() + 1;
		}

		public ExpTipo tipo_exp() {
			return tipoDeIndireccion(mem1.tipo().val());
		}

		private Mem mem1;
	}

	public class MemR3Debug extends MemR3 {
		private final static String REGLA = "Mem(0) ::= Mem(1)^";

		public MemR3Debug(Mem mem1) {
			super(mem1);
			cod().fijaDescripcion(REGLA + " | mem(0).cod");
			etq().fijaDescripcion(REGLA + " | mem(0).etq");
			tipo().fijaDescripcion(REGLA + " | mem(0).tipo");
			mem1.tsh().fijaDescripcion(REGLA + " | mem(1).tsh");
			mem1.etqh().fijaDescripcion(REGLA + " | mem(1).etqh");
		}
	}

	/**
	 * <code>
	 * Mem ::= Mem[Exp0]
	 * Propagación de la tabla de símbolos
	 * Mem(1).tsh = Mem(0).tsh
	 * Exp0.tsh = Mem(0).tsh
	 * Comprobación de las restricciones contextuales
	 * Mem(0).tipo = tipoDeIndexacion(Mem(1).tipo,Exp0.tipo,*Mem(0).tsh()*)
	 * Generación de código
	 * Mem(1).etqh = Mem(0).etqh
	 * Exp0.etqh = Mem(1).etq
	 * Mem(0).etq = Exp0.etq + numeroInstruccionesIndexacion(Exp0.esDesignador)
	 * Mem(0).cod = Mem(1).cod || Exp0.cod || codigoIndexacion(Mem(1).tipo,Exp0.esDesignador*,Mem(0).tsh()*))
	 */
	public class MemR4 extends Mem {

		public MemR4(Mem mem1, Exp0 exp0) {
			this.mem1 = mem1;
			this.exp0 = exp0;
			exp0.tsh().ponDependencias(tsh());
			exp0.etqh().ponDependencias(mem1.etq());
			mem1.tsh().ponDependencias(tsh());
			mem1.etqh().ponDependencias(etqh());
			tipo().ponDependencias(mem1.tipo(), exp0.tipo());
			etq().ponDependencias(exp0.etq(), exp0.esDesignador());
			cod().ponDependencias(mem1.cod(), exp0.cod(), mem1.tipo(),
					exp0.esDesignador());
			mem1.registraCtx(new MemCtx() {

				@Override
				public TS tsh_exp() {
					return MemR4.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return MemR4.this.etqh().val();
				}
			});
			exp0.registraCtx(new ExpCtx() {

				@Override
				public TS tsh_exp() {
					return MemR4.this.tsh().val();
				}

				@Override
				public Integer etqh_exp() {
					return MemR4.this.mem1.etq().val();
				}
			});
		}

		public List<Instruccion> cod_exp() {
			List<Instruccion> cod;
			cod = concat(mem1.cod().val(), exp0.cod().val());
			return concat(
					cod,
					codigoIndexacion(mem1.tipo().val(), exp0.esDesignador()
							.val(),tsh().val()));
		}

		public Integer etq_exp() {
			return exp0.etq().val()
					+ numeroInstruccionesIndexacion(exp0.esDesignador().val());
		}

		public ExpTipo tipo_exp() {
			return tipoDeIndexacion(mem1.tipo().val(), exp0.tipo().val(), tsh()
					.val());
		}

		private Mem mem1;
		private Exp0 exp0;
	}

	public class MemR4Debug extends MemR4 {
		private final static String REGLA = "Mem(0) ::= Mem(1)[Exp0]";

		public MemR4Debug(Mem mem1, Exp0 exp) {
			super(mem1, exp);
			cod().fijaDescripcion(REGLA + " | mem(0).cod");
			etq().fijaDescripcion(REGLA + " | mem(0).etq");
			tipo().fijaDescripcion(REGLA + " | mem(0).tipo");
			mem1.tsh().fijaDescripcion(REGLA + " | mem(1).tsh");
			mem1.etqh().fijaDescripcion(REGLA + " | mem(1).etqh");
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
	// TODO CONTROLAR LO DE QUE EXISTAN VARIOS TS
	public TS aniadeSimb(TS ts, String iden, CatLexica clase, ExpTipo expTipo,
			int dir, int nivelh) {
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(expTipo);
		a.add(String.valueOf(dir));
		a.add(clase);
		a.add(nivelh);
		return ts.aniade(iden, a);
	}

	public boolean estaEn(String cte, TS ts) {
		return ts.estaEn(cte);
	}

	public ExpTipo tipoDe(String cte, TS ts) {
		return (ExpTipo) ts.valDe(cte).get(0);
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

	public List<Instruccion> concat(Instruccion c1, List<Instruccion> c2) {
		List<Instruccion> result = new LinkedList<Instruccion>();
		result.add(c1);
		result.addAll(c2);
		return result;
	}

	public List<Instruccion> concat(Instruccion c1, Instruccion c2) {
		List<Instruccion> result = new LinkedList<Instruccion>();
		result.add(c1);
		result.add(c2);
		return result;
	}

	public List<Instruccion> concat(List<Instruccion>... cs) {
		List<Instruccion> result = new LinkedList<Instruccion>();
		for (List<Instruccion> c : cs)
			result.addAll(c);
		return result;
	}

	public List<Instruccion> concat(Instruccion... cs) {
		List<Instruccion> result = new LinkedList<Instruccion>();
		for (Instruccion c : cs)
			result.add(c);
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

	public ExpTipo tipoOpBin(CatLexica op, ExpTipo expTipo, ExpTipo expTipo2) {
		if (expTipo.t().equals(expTipo2.t())) {
			if (op.equals(CatLexica.GT) || op.equals(CatLexica.EQ)
					|| op.equals(CatLexica.NEQ) || op.equals(CatLexica.GE)
					|| op.equals(CatLexica.LT) || op.equals(CatLexica.LE))
				return ExpTipo.nuevaExpTipoBoolean();
			else if (op.equals(CatLexica.MAS) || op.equals(CatLexica.MENOS)
					|| op.equals(CatLexica.ASTERISCO)
					|| op.equals(CatLexica.BARRA))
				return ExpTipo.nuevaExpTipoInt();

		}
		return ExpTipo.nuevaExpTipoError();
	}

	public ExpTipo tipoOpUnario(CatLexica op, ExpTipo expTipo) {
		if (op.equals(CatLexica.MENOS) && expTipo.equals(CatLexica.INT))
			return ExpTipo.nuevaExpTipoBoolean();
		else if (op.equals(CatLexica.NOT) && expTipo.equals(CatLexica.BOOLEAN))
			return ExpTipo.nuevaExpTipoInt();
		else
			return ExpTipo.nuevaExpTipoError();
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

	public Instruccion apila_dir(int val) {
		return Instruccion.nuevaIApilaDir(String.valueOf(val));
	}

	public Instruccion desapila_dir(int val) {
		return Instruccion.nuevaIDesapilaDir(String.valueOf(val));
	}

	public Instruccion apila(String arg) {
		return Instruccion.nuevaIApila(arg);
	}

	public Instruccion desapila() {
		return Instruccion.nuevaIDesapila();
	}

	public Instruccion apila_int(int i) {
		return Instruccion.nuevaIApila(String.valueOf(i));
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

	private Instruccion ir_ind() {
		return Instruccion.nuevaIIr_ind();
	}

	private Instruccion apila_ind() {
		return Instruccion.nuevaIApilaInd();
	}

	private Instruccion copia() {
		return Instruccion.nuevaICopia();
	}

	private Instruccion desapila_ind() {
		return Instruccion.nuevaIDesApilaInd();
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

	private boolean asignacionCorrecta(ExpTipo memTipo, ExpTipo expTipo) {
		return memTipo.t().equals(expTipo.t());
	}

	// TODO NUEVOS METODOS AUXILIARES
	private Error tiposNoDeclarados(List<ExpTipo> refsAcheck, TS tsDecs) {
		String nodeclarados = "";// acumulador
		boolean declarado = false;// bandera
		// obtener tiposdeclarados
		List<String> tiposDeclarados = tsDecs.getTiposDeclarados();
		Iterator<String> itdeclarados = tiposDeclarados.iterator();

		// calculo de declarados
		Iterator<ExpTipo> itacheck = refsAcheck.iterator();
		while (itacheck.hasNext()) {
			ExpTipo acheck = itacheck.next();
			while (itdeclarados.hasNext()) {
				String declarada = itdeclarados.next();
				if (declarada.equals(acheck.id())) {
					declarado = true;
				}
			}
			// mirarSi declarados o no
			if (declarado) {
				declarado = false;
			} else {
				nodeclarados += acheck.id() + ", ";
			}
			itdeclarados = tiposDeclarados.iterator();// reseteo iterador
		}
		return nodeclarados.equals("") ? noError() : new Error(nodeclarados);
	}

	private List<ExpTipo> union(List<ExpTipo> val, List<ExpTipo> val2) {
		// Hace union de conjuntos
		for (ExpTipo expTipo : val2)
			if (!val.contains(expTipo))
				val.add(expTipo);

		return val;
	}

	private List<Integer> unionInt(List<Integer> val, List<Integer> val2) {
		// Hace union de conjuntos de tipo Integer
		for (Integer expTipo : val2)
			if (!val.contains(expTipo))
				val.add(expTipo);

		return val;
	}

	private List<Instruccion> programaVacio() {
		return new LinkedList<Instruccion>();
	}

	private Error tipoRefIncorrecto(TS ts, ExpTipo tipo) {
		// si tipo es ref y el tipo al que hace referencia no existe en la tabla
		// de simbolos
		boolean declarado = false;
		if (tipo.t() == CatLexica.REF) {
			Iterator<String> it = ts.getTiposDeclarados().iterator();
			while (it.hasNext()) {
				if (it.next().equals(tipo.id()))
					declarado = true;
			}
			return declarado ? noError() : new Error(
					"Tipo referenciado no declarado: " + tipo.id());
		} else {
			return noError();
		}
	}

	// TODO crea una nueva tabla de simbolos con padre la actual
	private TS creaNivel(Atributo<TS> tsh) {
		return new TS(tsh.val());
	}

	private List<ExpTipo> listaExpTipoVacia() {
		return new LinkedList<ExpTipo>();
	}

	private List<ExpTipo> aniadeA(List<ExpTipo> params, ExpTipo param) {
		List<ExpTipo> result = new LinkedList<ExpTipo>(params);
		result.add(param);
		return result;
	}

	private List<ExpTipo> nuevaLista(ExpTipo param) {
		List<ExpTipo> result = new LinkedList<ExpTipo>();
		result.add(param);
		return result;
	}

	// TODO dudas si tipo no existe en ts,añadirlo a check
	private List<ExpTipo> aChequear(TS ts, ExpTipo tipo) {
		if(tipo.t()!=CatLexica.REF)return listaExpTipoVacia();
		LinkedList<ExpTipo> a = new LinkedList<ExpTipo>();
		boolean declarado = false;
		Iterator<String> it = ts.getTiposDeclarados().iterator();
		while (it.hasNext()) {
			if (it.next().equals(tipo.id()))
				declarado = true;
		}
		a.push(tipo);
		return declarado ? listaExpTipoVacia() : a;

	}

	private int tamanioDeTipoRef(TS tsh, String lex) {
		return tsh.getTamRef(lex);
	}

	private Error campoDuplicado(String idCampo, List<ExpTipo> campos) {
		Iterator<ExpTipo> itcampos = campos.iterator();
		boolean duplicado = false;
		while (itcampos.hasNext()) {
			if (idCampo.equals(itcampos.next().id()))
				duplicado = true;
		}
		return duplicado ? new Error("Campo duplicado: " + idCampo) : noError();
	}

	private List<Integer> listaIntegerVacia() {
		return new LinkedList<Integer>();
	}

	private boolean llamadaCorrecta(TS ts, String lex, Integer nparams) {
		if (!estaEn(lex, ts))
			return false;
		if (ts.getClase(lex) != CatLexica.PROC)
			return false;
		ExpTipo metodo = ts.getExpTipo(lex);
		return metodo.params().size() == nparams ? true : false;
	}

	private Integer nivelDe(String subProgh, TS ts) {
		ArrayList<Object> o = ts.valDe(subProgh);
		return (Integer) o.get(3);
	}

	// TODO
	// que coincida el tipo del parametro nparams con la definicion del metodo
	// en caso de que sea designador(puntero), se mira el modo del parametro y
	// luego se mira el tipo del dato al que apunta
	private boolean parametroCorrecto(String subProg, int nParams,
			ExpTipo tipo, Boolean designador, TS tsh) {
		boolean correcto;
		ExpTipo metodo = tsh.getExpTipo(subProg);
		ExpTipo param = metodo.params().get(nParams);
		if (!designador) {
			if (tipo.t() == param.tipo().t())
				correcto = true;
			else
				correcto = false;
		} else {
			if (param.modo() == CatLexica.VALOR)
				correcto = false;
			else {
				ExpTipo referenciada = tsh.getExpTipo(tipo.id());
				if (referenciada.t() == param.tipo().t()) {
					correcto = true;
				} else
					correcto = false;
			}
		}
		return correcto;
	}

	// sacao lo de tam de transparencias
	private List<Instruccion> codigoAsignacion(List<Instruccion> memcod,
			List<Instruccion> exp0cod, Boolean exp0EsDesig, int tamMover) {
		List<Instruccion> cod;
		if (exp0EsDesig) {
			cod = concat(memcod, concat(exp0cod, mueve(tamMover)));
		} else {
			cod = concat(memcod, concat(exp0cod, desapila_ind()));
		}
		return cod;
	}

	private Instruccion mueve(int tamMover) {
		return Instruccion.nuevaIMueve(String.valueOf(tamMover));
	}

	// TODO asi directamente :?
	private int tamañoObjetoApuntado(ExpTipo tipo, TS ts) {
		if (tipo.t() == CatLexica.REF) {
			return ts.getTamRef(tipo.id());
		} else {
			return tipo.tbase().tam();
		}
	}

	private Instruccion reserva(int tamañoObjetoApuntado) {
		return Instruccion.nuevaINew(String.valueOf(tamañoObjetoApuntado));
	}

	private Instruccion libera(int tamañoObjetoApuntado) {
		return Instruccion.nuevaIDel(String.valueOf(tamañoObjetoApuntado));
	}

	private boolean noEsbooleano(ExpTipo val) {
		return val.t() != CatLexica.BOOLEAN;
	}

	private Integer unoSiCierto(Boolean val) {
		return val ? 1 : 0;
	}

	// apila_ind para traerme el dato
	// Exp1(0).cod || Exp1(1).cod || OpComparacion.cod
	private List<Instruccion> codigoOpComparacion(List<Instruccion> exp1cod,
			List<Instruccion> exp1_1cod, List<Instruccion> opccod,
			Boolean exp1EsDesig, Boolean exp1_1EsDesig) {
		List<Instruccion> cod;
		// compruebo si exp1esDesig
		if (exp1EsDesig)
			cod = concat(exp1cod, concat(apila_ind(), exp1_1cod));
		else
			cod = concat(exp1cod, exp1_1cod);
		// compruebo exp11
		if (exp1_1EsDesig)
			cod = concat(cod, apila_ind());
		// concateno finalmente el operador de comparacion
		return concat(cod, opccod);
	}

	private List<Instruccion> codigoOpAditivo(List<Instruccion> exp1cod,
			List<Instruccion> exp2cod, List<Instruccion> opacod,
			Boolean exp1Desig, Boolean exp2Desig) {
		// es el mismo proceso, reutilizamos para ahorrar codigo
		return codigoOpComparacion(exp1cod, exp2cod, opacod, exp1Desig,
				exp2Desig);
	}

	private List<Instruccion> codigoOpMultiplicativo(List<Instruccion> exp2cod,
			List<Instruccion> exp3cod, List<Instruccion> opmcod,
			Boolean exp2Desig, Boolean exp3Desig) {
		return codigoOpComparacion(exp2cod, exp3cod, opmcod, exp2Desig,
				exp3Desig);
	}

	// si es desighay que meterle el apilaind,luego 1 ins mas
	private Integer numeroInstruccionesOpUnario(Boolean designador) {
		return designador ? 1 : 0;
	}

	private List<Instruccion> codigoOpUnario(List<Instruccion> exp3cod,
			List<Instruccion> opucod, Boolean exp3EsDesig) {

		List<Instruccion> cod;
		if (exp3EsDesig)
			cod = concat(exp3cod, concat(apila_ind(), opucod));
		else
			cod = concat(exp3cod, opucod);
		return cod;
	}

	private String desplazamientoDeCampo(ExpTipo campos, String lex) {
		Iterator<ExpTipo> it = campos.campos().iterator();
		while (it.hasNext()) {
			ExpTipo campo = it.next();
			if (campo.id().equals(lex))
				return String.valueOf(campo.desp());

		}
		return null;// no debe entrar aki nunca :O
	}

	private ExpTipo tipoDeSelectorCampo(ExpTipo campos, String lex) {
		Iterator<ExpTipo> it = campos.campos().iterator();
		while (it.hasNext()) {
			ExpTipo campo = it.next();
			if (campo.id().equals(lex))
				return campo.tipo();
		}
		return ExpTipo.nuevaExpTipoError();
	}

	private ExpTipo tipoDeIndireccion(ExpTipo punt) {
		if (punt.t() == CatLexica.PUNTERO)
			return punt.tbase();
		else
			return ExpTipo.nuevaExpTipoError();
	}

	private Integer aEntero(String lex) {
		return Integer.parseInt(lex);
	}

	private boolean reservaMemoriaCorrecta(ExpTipo val) {
		return val.t() == CatLexica.PUNTERO;
	}

	private boolean liberaMemoriaCorrecta(ExpTipo val) {
		return val.t() == CatLexica.PUNTERO;
	}

	// sacado de apuntes
	private Error existeSimbEnUltimoNivel(TS decs1ts, String lex, Integer nivelh) {
		if (existeSimb(decs1ts, lex) && decs1ts.getNivel(lex) == nivelh) {
			return new Error("Ya Existe: " + lex);
		} else
			return noError();
	}

	private Integer numeroInstruccionesIndexacion(Boolean exp0esDesig) {
		if (exp0esDesig)
			return 4;// apila_ind,apila_int(tamBase),mul(),suma()
		else
			return 3;// apila_int(tamBase),mul(),suma()
	}

	private List<Instruccion> codigoIndexacion(ExpTipo mem1tipo,
			Boolean exp0EsDesig, TS ts) {
		List<Instruccion> cod;
		//int tamBase = mem1tipo.tbase().tam();
		int tamBase=ref(ts,mem1tipo).tbase().tam();
		if (exp0EsDesig)
			cod = concat(apila_ind(), apila_int(tamBase));
		else
			cod = single(apila_int(tamBase));
		return concat(cod, concat(mul(), suma()));
	}

	private ExpTipo tipoDeIndexacion(ExpTipo mem1tipo, ExpTipo exp0tipo, TS ts) {
		if (exp0tipo.t() == CatLexica.INT) {
//			if (mem1tipo.t() == CatLexica.REF) {
//				return ts.getExpTipo(mem1tipo.id()).tbase();
			return ref(ts,mem1tipo).tbase();
//			} else
//				return mem1tipo.tbase();
		} else {
			return ExpTipo.nuevaExpTipoError();
		}
	}

	private ExpTipo ref(TS ts, ExpTipo exp){
		if (exp.t() == CatLexica.REF) {
			if(existeSimb(ts, exp.id()))
				return ref(ts,ts.getExpTipo(exp.id()));
		}else
			return exp;
		return exp;
	}
	private int numeroInstruccionesActivacionProgramaPrincipal() {
		return 4 + 1;
		// apila_int(inicioDatosEstaticos),desapilar_dir(1),
		// apila_int(finDatosEstaticos),desapila_dir(0)
		// ir_a(Bloque.dirSalto)
	}

	// se guarda en posicion 0 la cima de la pila y en 1 el primer display
	// dir inicio que representa? pos siguiente en base a tamDatos
	// cp,disp,ret,oldis,inicio, por eso el 1+1+1
	// Segunda Interpretacion
	// Fijamos cp a pos despues de displays, y el display a vacio, xk no se ha
	// activao aun ningun reg de act, el prologo lo hara despues
	private List<Instruccion> codigoActivacionProgramaPrincipal(
			Integer dirInicio, Integer anidamiento, Integer dirSalto) {
		List<Instruccion> codigoDisplay;
		int inicioDatosEstaticos = 0/* anidamiento + 1 + 1 + 1 */;
		codigoDisplay = concat(apila_int(inicioDatosEstaticos), desapila_dir(1));

		List<Instruccion> codigoCP;
		int finDatosEstaticos = anidamiento + 1;

		// 1+ 1 + 1 + anidamiento + dirInicio-1
		codigoCP = concat(apila_int(finDatosEstaticos), desapila_dir(0));

		return concat(codigoDisplay, codigoCP, single(ir_a(dirSalto)));
	}

	private Integer numeroInstruccionesPrologo() {
		return 13;
	}

	private Integer numeroInstruccionesEpilogo() {
		return 13 + 1;// +1 o no si metes el ir_ind en el epilogo
	}

	private List<Instruccion> codigoPrologo(Integer dir, Integer nivelh) {

		List<Instruccion> saveOldDisplay, fijaNewDisplay, incrementaCP;
		// El antiguo display esta en 1(salto cp)+nivelh, eso lo meto en valor
		// generado con la suma de cp+2 (0-cp+[1-dir return|2-oldDisplay])
		saveOldDisplay = concat(apila_dir(0), apila_int(2), suma(),
				apila_dir(1 + nivelh), desapila_ind());

		// El nuevo display se guardara en 1+nivelh, y contendra el valor de
		// inicio de datos del nuevo display (cp+3)
		fijaNewDisplay = concat(apila_dir(0), apila_int(3), suma(),
				desapila_dir(1 + nivelh));// coge cima y mete en dir
		// apila cp, apilo datos locales+2[ret|oldisplay] sumo y save n dir 0
		incrementaCP = concat(apila_dir(0), apila_int(dir + 2), suma(),
				desapila_dir(0));
		return concat(saveOldDisplay, fijaNewDisplay, incrementaCP);
	}

	private List<Instruccion> codigoEpilogo(Integer dir, Integer nivel) {
		List<Instruccion> dirRetorno, fijaCP, recuperaOldDisplay;
		// cojo display, sumo 2 y resto, para situarme en dir ret, y apilo_ind,
		// para traermelo
		dirRetorno = concat(apila_dir(1 + nivel), apila_int(2), resta(),
				apila_ind());
		// cojo display, apilo inicio datos, resto y copio old CP, y fijo ese
		// como new cp
		fijaCP = concat(apila_dir(1 + nivel), apila_int(3), resta(), copia(),
				desapila_dir(0));
		// aprovecho copia (old cp) sumo 2 para situarme en campo old display,
		// apila_ind para traerme lo que contenia y lo guardo como el display
		// actual
		recuperaOldDisplay = concat(apila_int(2), suma(), apila_ind(),
				desapila_dir(1 + nivel));

		return concat(dirRetorno, fijaCP, recuperaOldDisplay, single(ir_ind()));
	}

	// TODO NUEVOS METODOS AUXILIARES
	private Error tipoError(ExpTipo exp) {
		return exp.t() == CatLexica.ERROR ? new Error("Error de tipo")
				: noError();
	}

	private List<Instruccion> codigoAccesoVar(String lex, TS ts) {
		// apilo display,y dir de var para get dir abs
		List<Instruccion> cod;
		int lv = ts.getNivel(lex);
		int dir = dirDe(lex, ts);
		cod = concat(apila_dir(1 + lv), apila_int(dir), suma());
		// y si es pvar añado apila_ind() para get valor
		if (ts.getClase(lex) == CatLexica.PVAR)
			cod = concat(cod, apila_ind());
		return cod;
	}

	private Integer numeroInstruccionesAccesoVar(String lex, TS ts) {
		if (ts.getClase(lex) == CatLexica.PVAR) {
			return 4;
		} else {
			return 3;
		}
	}

	private Instruccion codigoLectura(ExpTipo mem) {
		return Instruccion.nuevaILectura(mem.t());
	}

	private boolean lecturaCorrecta(ExpTipo val) {
		return val.t() == CatLexica.INT || val.t() == CatLexica.BOOLEAN;
	}

	private boolean escrituraCorrecta(ExpTipo val) {
		return val.t() == CatLexica.INT || val.t() == CatLexica.BOOLEAN;
	}

	private Instruccion codigoEscritura(ExpTipo mem) {
		return Instruccion.nuevaIEscritura(mem.t());
	}

	// TODO DUDA
	private List<Integer> direccionSiSaltoIndefinido(String lex, TS ts,
			Integer paramsEtq) {
		List<Integer> l = new LinkedList<Integer>();
		l.add(paramsEtq);
		// si esta indefinido es -1 porque asi lo he inicializado yo
		return dirDe(lex, ts) == -1 ? l : listaIntegerVacia();
	}

	// TODO DUDA
	private List<Instruccion> fijaLlamadasPendientes(
			List<Instruccion> bloqueCod, List<Integer> callPendientes,
			Integer bloqDirIni) {
		List<Instruccion> cod = bloqueCod;
		for (Integer call : callPendientes)
			cod.add(ir_a(call));
		cod.add(ir_a(bloqDirIni));
		return cod;
	}

	// TODO esto falta algo seguro se supone que nivelDe esta la dir de
	// retorno pss
	private List<Instruccion> codigoInicioLlamada(Integer nivelDe,
			Integer parametqRet) {
		List<Instruccion> saveReturn, inicioData;
		saveReturn = concat(apila_dir(0), apila_int(1), suma(),
				apila_int(parametqRet), desapila_ind());
		inicioData = concat(apila_dir(0), apila_int(1), suma());
		return concat(saveReturn, inicioData);
	}

	private int numeroInstruccionesInicioLlamada() {
		return 8;// apila_dir(0), apila_int(1),
					// suma(),apila_int(nivelDe),desapila_ind()
	}

	private Integer numeroInstruccionesFinLlamada() {
		return 1;// desapila
	}

	// ir_a fin de llamada?
	private List<Instruccion> codigoFinLlamada(String idenLex, TS ts, int nInsts) {
		return single(desapila());
	}

	private int numeroInstruccionesCodigoPaso() {
		return 1;
	}

	private List<Instruccion> codigoPaso(String subProgramah, Integer nparams,
			ExpTipo exp0tipo, Boolean exp0EsDesig, TS tsh) {
		List<Instruccion> cod;
		ExpTipo metodo = tsh.getExpTipo(subProgramah);
		ExpTipo param = metodo.params().get(nparams);
		cod = new LinkedList<Instruccion>();
		if (exp0EsDesig) {
			if (param.modo() == CatLexica.VALOR) {
				cod = concat(cod, mueve(1));
			} else {
				cod = concat(cod, desapila_ind());
			}
		} else {
			cod = concat(cod, desapila_ind());
		}
		return cod;
	}

	public static void main(String[] args) {
		GA ga = new GA();

		GA.Programa programa = ga.new ProgR1Debug(ga.new BloqueR1Debug(
				ga.new DecsR2Debug(ga.new DecR1Debug(ga.new TipoR1Debug(),
						new Token(CatLexica.IDEN, "x"))),
				ga.new InstsR2Debug(ga.new InstR1Debug(ga.new IAsigR1Debug(
						ga.new MemR1Debug(new Token(CatLexica.IDEN, "x")),
						ga.new Exp0R2Debug(ga.new Exp1R2Debug(
								ga.new Exp2R2Debug(ga.new Exp3R2Debug(
										ga.new Exp4R3Debug(new Token(
												CatLexica.INT, "20")))))))))));
		try {
			Evaluador evaluador = new Evaluador();
			if (evaluador.evalua(programa.err()).val().hayError()) {
				for (String e : programa.err().val().errores())
					System.out.println(e);
			} else {
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream("out.bin"));
				out.writeObject(evaluador.evalua(programa.cod()).val());
			}
		} catch (Exception e) {
			System.err.println("ERROR:" + e);
			System.exit(1);
		}
	}
}
