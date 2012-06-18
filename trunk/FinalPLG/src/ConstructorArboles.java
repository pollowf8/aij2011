import java.io.IOException;
import java.util.ArrayList;

public class ConstructorArboles {

	private Token tact;

	private AnalizadorLexico analizadorLexico;

	private GA gramaticaAtributos = new GA();

	String[] c = new String[]{"p1","p2","p3"};
	ArrayList a=new ArrayList<String>();
	public ConstructorArboles(AnalizadorLexico analizadorLexico) {
		this.analizadorLexico = analizadorLexico;
		a.add(c[0]);a.add(c[1]);a.add(c[2]);
	}

	public GA.Programa parse() throws IOException {
		tact = analizadorLexico.sigToken();
		GA.Programa aDePrograma = recPrograma();
		rec(CatLexica.EOF);
		return aDePrograma;
	}

	// Programa ::= Bloque
	// Programa.a = progR1(Bloque.a)
	private GA.Programa recPrograma() throws IOException {
		GA.Bloque aBloque = recBloque();
		return progR1(aBloque);
	}

	// Bloque ::= Declaraciones & Instrucciones
	// Bloque.a = bloqueR1(Declaraciones.a,Instrucciones.a)
	// Bloque ::= Instrucciones
	// Bloque.a = bloqueR2(Instrucciones.a)
	private GA.Bloque recBloque() throws IOException {
		// un bloque empieza por decs o por insts
		// un decs empieza por Tipo,tipo o proc
		// un Tipo empieza por catLexica int,boolean,tabla,registro,puntero,IDEN
		// un insts empieza por IAsig, INew, IDispose, Ilectura, IEscritura,
		// ILlamada, IIF, IDO
		// IAsig por Mem, q es un IDEN
		// Inew por new, IDispose por delete
		// ILectura por leer y IEscritura por escribir
		// IILamada empieza por IDEN
		// IIF por if y IDO por do

		if ((tokenActual(CatLexica.IDEN)) // porq decs tb puede empezar por iden
				|| tokenActual(CatLexica.IF)
				|| tokenActual(CatLexica.DO)
				|| tokenActual(CatLexica.NEW)
				|| tokenActual(CatLexica.DELETE)
				|| tokenActual(CatLexica.LEER)
				|| tokenActual(CatLexica.ESCRIBIR)) {
			GA.Insts aInsts = recInsts();
			return bloqueR2(aInsts);
		} else {
			if (tokenActual(CatLexica.INT) || tokenActual(CatLexica.BOOLEAN)
					|| tokenActual(CatLexica.TABLA)
					|| tokenActual(CatLexica.REG)
					|| tokenActual(CatLexica.PUNTERO)
					|| tokenActual(CatLexica.IDEN)
					|| tokenActual(CatLexica.TIPO)
					|| tokenActual(CatLexica.PROC)) {
				GA.Decs aDecs = recDecs();
				if (tokenActual(CatLexica.AMPERSAND)) {
					rec(CatLexica.AMPERSAND);
					GA.Insts aInsts = recInsts();
					return bloqueR1(aDecs, aInsts);
				}
			}
		}
		errorSintactico();
		return null;
	}

	// Declaraciones ::= Declaracion RDecs
	// Declaraciones.a = RDecs.a
	// RDecs.ah = decsR2(Declaracion.a)
	private GA.Decs recDecs() throws IOException {
		GA.Dec aDec = recDec();
		GA.Decs aRDecs = recRDecs(decsR2(aDec));
		return aRDecs;
	}

	// Rdecs ::= ; Declaracion RDecs
	// RDecs(0).a = RDecs(1).a
	// RDecs(1).ah = decsR1(RDecs(0).ah,Declaracion.a)
	// RDecs ::= lambda
	// RDecs.a=RDecs.ah
	private GA.Decs recRDecs(GA.Decs ahRDecs0) throws IOException {
		if (tokenActual(CatLexica.PUNTOCOMA)) {
			rec(CatLexica.PUNTOCOMA);
			GA.Dec aDec = recDec();
			GA.Decs aRDecs1 = recRDecs(decsR1(ahRDecs0, aDec));
			return aRDecs1;
		} else {
			return ahRDecs0;
		}
	}

	// Declaracion ::= Tipo IDEN
	// Declaracion.a = decR1(Tipo.a,IDEN.lex)
	// Declaracion ::= tipo Tipo IDEN
	// Declaracion.a = decR2(Tipo.a,IDEN.lex)
	// Declaracion ::= proc IDEN ( ParametrosFormales ) { Bloque }
	// Declaracion.a = decR3(IDEN.lex,ParametrosFormales.a,Bloque.a)

	private GA.Dec recDec() throws IOException {
		if (tokenActual(CatLexica.TIPO)) {// segunda ecuacion
			rec(CatLexica.TIPO);
			GA.Tipo aTipo = recTipo();
			Token tIden = tact;
			rec(CatLexica.IDEN);
			return decR2(aTipo, tIden);
		} else {
			if (tokenActual(CatLexica.PROC)) {// tercera ecuacion
				rec(CatLexica.PROC);
				Token tIden = tact;
				rec(CatLexica.IDEN);
				rec(CatLexica.PAP);
				GA.ParamsFormales aParamsFormales = recParamsFormals();
				rec(CatLexica.PCIERRE);
				rec(CatLexica.LLAVEAP);
				GA.Bloque aBloque = recBloque();
				rec(CatLexica.LLAVECIERRE);
				return decR3(tIden, aParamsFormales, aBloque);
			} else if (tokenActual(CatLexica.INT)
					|| tokenActual(CatLexica.BOOLEAN)
					|| tokenActual(CatLexica.TABLA)
					|| tokenActual(CatLexica.REG)
					|| tokenActual(CatLexica.PUNTERO)
					|| tokenActual(CatLexica.IDEN)
					|| tokenActual(CatLexica.TIPO)
					|| tokenActual(CatLexica.PROC)) {// primera ecuacion
				GA.Tipo aTipo = recTipo();
				Token tIden = tact;
				rec(CatLexica.IDEN);
				return decR1(aTipo, tIden);
			} else {
				errorSintactico(CatLexica.IDEN);
				return null;
			}
		}
	}

	// ParametrosFormales ::= ListaParametrosFormales
	// ParametrosFormales.a = parametrosFormalesR1(ListaParametrosFormales.a)
	// ParametrosFormales ::= lambda
	// ParametrosFormales.a = parametrosFormalesR2()
	//
	private GA.ParamsFormales recParamsFormals() throws IOException {
		if (tokenActual(CatLexica.VAR) || tokenActual(CatLexica.INT)
				|| tokenActual(CatLexica.BOOLEAN)
				|| tokenActual(CatLexica.TABLA) || tokenActual(CatLexica.REG)
				|| tokenActual(CatLexica.PUNTERO)
				|| tokenActual(CatLexica.IDEN) || tokenActual(CatLexica.TIPO)
				|| tokenActual(CatLexica.PROC)) {
			GA.ListaParamsFormales aListaParamsFormales = recListaParamsFormales();
			return parametrosFormalesR1(aListaParamsFormales);
		} else {
			// rec(CatLexica.lambda);
			return parametrosFormalesR2();
		}

	}

	// ListaParametrosFormales ::= ParametroFormal RListaParamsForm
	// ListaParametrosFormales.a = RListaParamsForm.a
	// RListaParamsForm.ah = listaParametrosFormalesR2(ParametroFormal.a)

	private GA.ListaParamsFormales recListaParamsFormales() throws IOException {

		GA.ParamFormal aParamFormal = recParamFormal();
		GA.ListaParamsFormales aRListaParamsFormales = recRListaParamsFormales(listaParametrosFormalesR2(aParamFormal));
		return aRListaParamsFormales;

	}

	// RListaParamsForm ::= , ParametroFormal RListaParamsForm
	// RListaParamsForm(0).a = RListaParamsForm(1).a
	// RListaParamsForm(1).ah =
	// listaParametrosFormalesR1(RListaParamsForm(0).ah, ParametroFormal.a)
	// RListaParamsForm ::= lambda
	// RListaParamsForm.a = RListaParamsForm.ah

	private GA.ListaParamsFormales recRListaParamsFormales(
			GA.ListaParamsFormales ahRListaParamsFormales0) throws IOException {
		if (tokenActual(CatLexica.COMA)) {
			rec(CatLexica.COMA);
			GA.ParamFormal aParamFormal = recParamFormal();
			GA.ListaParamsFormales aRListaParamsFormales1 = recRListaParamsFormales(listaParametrosFormalesR1(
					ahRListaParamsFormales0, aParamFormal));
			return aRListaParamsFormales1;
		} else {
			return ahRListaParamsFormales0;
		}
	}

	// ParametroFormal ::= Tipo IDEN
	// ParametroFormal.a = parametroFormalR1(Tipo.a,IDEN.lex)
	// ParametroFormal ::= var Tipo IDEN
	// ParametroFormal.a = parametroFormalR2(Tipo.a,IDEN.lex)
	private GA.ParamFormal recParamFormal() throws IOException {
		if (tokenActual(CatLexica.VAR)) {// segunda ecuacion
			rec(CatLexica.VAR);
			GA.Tipo aTipo = recTipo();
			Token tIden = tact;
			rec(CatLexica.IDEN);
			return parametroFormalR2(aTipo, tIden);
		} else {
			GA.Tipo aTipo = recTipo();
			Token tIden = tact;
			rec(CatLexica.IDEN);
			return parametroFormalR1(aTipo, tIden);
		}
	}

	// Tipo ::= int
	// Tipo.a=tipoR1()
	// Tipo ::= boolean
	// Tipo.a=tipoR2()
	// Tipo ::= tabla [NUM] de Tipo
	// Tipo.a=tipoR3(NUM.lex,Tipo.a)
	// Tipo ::= registro{ Campos }
	// Tipo.a=tipoR4(Campos.a)
	// Tipo ::= puntero Tipo
	// Tipo.a=tipoR5(Tipo.a)
	// Tipo ::= IDEN
	// Tipo.a=tipoR6(IDEN.lex)

	private GA.Tipo recTipo() throws IOException {
		if (tokenActual(CatLexica.INT)) {
			rec(CatLexica.INT);
			return tipoR1();
		} else if (tokenActual(CatLexica.BOOLEAN)) {
			rec(CatLexica.BOOLEAN);
			return tipoR2();
		} else if (tokenActual(CatLexica.TABLA)) {
			rec(CatLexica.TABLA);

			rec(CatLexica.CAP);
			Token aNum = tact;
			rec(CatLexica.NUM);
			rec(CatLexica.CCIERRE);
			rec(CatLexica.DE);
			GA.Tipo aTipo = recTipo();
			return tipoR3(aNum, aTipo);
		} else if (tokenActual(CatLexica.REG)) {
			rec(CatLexica.REG);

			rec(CatLexica.LLAVEAP);
			GA.Campos aCampos = recCampos();
			rec(CatLexica.LLAVECIERRE);

			return tipoR4(aCampos);
		} else if (tokenActual(CatLexica.PUNTERO)) {
			rec(CatLexica.PUNTERO);
			GA.Tipo aTipo = recTipo();
			return tipoR5(aTipo);

		} else if (tokenActual(CatLexica.IDEN)) {
			Token tIden = tact;
			rec(CatLexica.IDEN);
			return tipoR6(tIden);
		} else {
			errorSintactico(CatLexica.INT, CatLexica.BOOLEAN,
					CatLexica.PUNTERO, CatLexica.REG, CatLexica.TABLA,
					CatLexica.IDEN);
			return null;
		}

	}

	// Campos ::= Campo RCampos
	// Campos.a = RCampos.a
	// RCampos.ah = camposR2(Campo.a)

	private GA.Campos recCampos() throws IOException {

		if (tokenActual(CatLexica.INT) || tokenActual(CatLexica.BOOLEAN)
				|| tokenActual(CatLexica.TABLA) || tokenActual(CatLexica.REG)
				|| tokenActual(CatLexica.PUNTERO)
				|| tokenActual(CatLexica.IDEN) || tokenActual(CatLexica.TIPO)
				|| tokenActual(CatLexica.PROC)) {

			GA.Campo aCampo = recCampo();
			GA.Campos aRCampos = recRCampos(camposR2(aCampo));
			return aRCampos;
		}
		errorSintactico();
		return null;
	}

	// RCampos ::= ; Campo RCampos
	// RCampos(0).a = RCampos(1).a
	// RCampos(1).ah = camposR1(RCampos(0).ah,Campo.a)
	// RCampos ::= lambda
	// RCampos.a=RCampos.ah

	private GA.Campos recRCampos(GA.Campos ahRCampos0) throws IOException {
		if (tokenActual(CatLexica.PUNTOCOMA)) {
			rec(CatLexica.PUNTOCOMA);
			GA.Campo aCampo = recCampo();
			GA.Campos aRCampos1 = recRCampos(camposR1(ahRCampos0, aCampo));
			return aRCampos1;
		} else {
			return ahRCampos0;
		}
	}

	// Campo ::= Tipo IDEN
	// Campo.a= campoR1(Tipo.a, IDEN.lex)
	private GA.Campo recCampo() throws IOException {

		if (tokenActual(CatLexica.INT) || tokenActual(CatLexica.BOOLEAN)
				|| tokenActual(CatLexica.TABLA) || tokenActual(CatLexica.REG)
				|| tokenActual(CatLexica.PUNTERO)
				|| tokenActual(CatLexica.IDEN) || tokenActual(CatLexica.TIPO)
				|| tokenActual(CatLexica.PROC)) {

			GA.Tipo aTipo = recTipo();
			Token tIden = tact;
			rec(CatLexica.IDEN);
			return campoR1(aTipo, tIden);
		}
		errorSintactico();
		return null;
	}

	// Instrucciones ::= Instruccion RInsts
	// Instrucciones.a = RInsts.a
	// RInsts.ah = instsR2(Instruccion.a)
	private GA.Insts recInsts() throws IOException {
		GA.Inst aInst = recInst();
		GA.Insts aRInsts = recRInsts(instsR2(aInst));
		return aRInsts;
	}

	// RInsts ::= ; Instruccion RInsts
	// RInsts (0).a = RInsts (1).a
	// RInsts (1).ah = instsR1(RInsts (0).ah, Instruccion.a)
	// RInsts ::= lambda
	// RInsts.a = RInsts.ah

	private GA.Insts recRInsts(GA.Insts ahRInsts0) throws IOException {
		if (tokenActual(CatLexica.PUNTOCOMA)) {
			rec(CatLexica.PUNTOCOMA);
			GA.Inst aInst = recInst();
			GA.Insts aRInsts = recRInsts(instsR1(ahRInsts0, aInst));
			return aRInsts;
		} else {
			return ahRInsts0;
		}

	}

	// Instruccion ::= IAsignacion
	// Instruccion.a = instR1(IAsignacion.a)
	// Instruccion ::= INew
	// Instruccion.a = instR2(INew.a)
	// Instruccion ::= IDispose
	// Instruccion.a = instR3(IDispose.a)
	// Instruccion ::= ILectura
	// Instruccion.a = instR4(ILectura.a)
	// Instruccion ::= IEscritura
	// Instruccion.a = instR5(IEscritura.a)
	// Instruccion ::= ILlamada
	// Instruccion.a = instR6(ILlamada.a)
	// Instruccion ::= IIF
	// Instruccion.a = instR7(IIF.a)
	// Instruccion ::= IDO
	// Instruccion.a = instR8(IDO.a)
	private GA.Inst recInst() throws IOException {

		if (tokenActual(CatLexica.IDEN)) {// mem ->Iden
			//TODO apaño para poder reconocer procedimientos, solo nombre k este en c
			if (a.contains(tact.lex())) {
				GA.Inst aILlamada = recILamada();
				return instR6(aILlamada);
			} else {
				GA.Inst aIAsig = recIAsig();
				return instR1(aIAsig);
			}
		} else if (tokenActual(CatLexica.NEW)) {
			GA.Inst aINew = recINew();
			return instR2(aINew);
		} else if (tokenActual(CatLexica.DELETE)) {
			GA.Inst aIDispose = recIDispose();
			return instR3(aIDispose);
		} else if (tokenActual(CatLexica.LEER)) {
			GA.Inst aILectura = recILectura();
			return instR4(aILectura);
		} else if (tokenActual(CatLexica.ESCRIBIR)) {
			GA.Inst aIEscritura = recIEscritura();
			return instR5(aIEscritura);
		} else if (tokenActual(CatLexica.IDEN)) {
			GA.Inst aILlamada = recILamada();
			return instR6(aILlamada);
		} else if (tokenActual(CatLexica.IF)) {
			GA.Inst aIIF = recIIF();
			return instR7(aIIF);
		} else if (tokenActual(CatLexica.DO)) {
			GA.Inst aIDo = recIDO();
			return instR8(aIDo);
		} else {
			errorSintactico();
			return null;
		}

	}

	// ILlamada ::= IDEN ( ParametrosReales )
	// ILlamada.a = illamadaR1(IDEN.lex, ParametrosReales.a)

	private GA.Inst recILamada() throws IOException {
		if (tokenActual(CatLexica.IDEN)) {
			Token tIdem = tact;
			rec(CatLexica.IDEN);
			rec(CatLexica.PAP);
			GA.ParamsReales aParametrosReales = recParamsReals();
			rec(CatLexica.PCIERRE);
			return illamadaR1(tIdem, aParametrosReales);
		} else {
			// rec(CatLexica.lambda);
			errorSintactico();
			return null;
		}
	}

	// ParametrosReales ::= ListaParametrosReales
	// ParametrosReales.a =parametrosRealesR1(ListaParametrosReales.a)
	// ParametrosReales ::= lambda
	// ParametrosReales.a = parametrosRealesR2()
	private GA.ParamsReales recParamsReals() throws IOException {
		if (tokenActual(CatLexica.MENOS)
				|| tokenActual(CatLexica.NOT)
				|| tokenActual(CatLexica.TRUE) // unario,Exp4 , mem
				|| tokenActual(CatLexica.FALSE) || tokenActual(CatLexica.IDEN)
				|| tokenActual(CatLexica.CAP) || tokenActual(CatLexica.PUNTO)
				|| tokenActual(CatLexica.ACENTO) || tokenActual(CatLexica.NUM)
				|| tokenActual(CatLexica.PAP)) {
			GA.ListaParamsReales aListaParamsReales = recListaParamsReales();
			return parametrosRelaesR1(aListaParamsReales);
		} else {
			// rec(CatLexica.lambda);
			return parametrosRelaesR2();
		}
	}

	// ListaParametrosReales ::= Exp0 RListaParamsReales
	// ListaParametrosReales.a = RListaParamsReales.a
	// RListaParamsReales.ah = listaParametrosRealesR2(Exp0.a)

	private GA.ListaParamsReales recListaParamsReales() throws IOException {
		GA.Exp0 aExp0 = recExp0();
		GA.ListaParamsReales aRListaParamsReales = recRListaParamsReales(listaParametrosRealesR2(aExp0));
		return aRListaParamsReales;

	}

	// RListaParamsReales ::= , Exp0 RListaParamsReales
	// RListaParamsReales (0).a = RListaParamsReales (1).a
	// RListaParamsReales (1).ah = listaParametrosRealesR1(RListaParamsReales
	// (0).ah,Exp0.a)
	// RListaParamsReales ::= lambda
	// RListaParamsReales.a=RListaParamsReales.ah

	private GA.ListaParamsReales recRListaParamsReales(
			GA.ListaParamsReales ahRListaParamsReales0) throws IOException {
		if (tokenActual(CatLexica.COMA)) {
			rec(CatLexica.COMA);
			GA.Exp0 aExp0 = recExp0();
			GA.ListaParamsReales aRListaParamsReales1 = recRListaParamsReales(listaParametrosRealesR1(
					ahRListaParamsReales0, aExp0));
			return aRListaParamsReales1;
		} else {
			return ahRListaParamsReales0;
		}
	}

	// IAsignacion ::= Mem := Exp0
	// IAsignacion.a = iasigR1(Mem.a,Exp0.a)

	private GA.Inst recIAsig() throws IOException {
		if (tokenActual(CatLexica.IDEN) || tokenActual(CatLexica.CAP)
				|| tokenActual(CatLexica.PUNTO)
				|| tokenActual(CatLexica.ACENTO)) {
			GA.Mem aMem = recMem();
			if (tokenActual(CatLexica.IGUAL)) {
				rec(CatLexica.IGUAL);
				GA.Exp0 aExpr0 = recExp0();
				return iasigR1(aMem, aExpr0);
			} else {
				errorSintactico(CatLexica.IGUAL);
				return null;
			}
		} else {
			errorSintactico();
			return null;
		}
	}

	// INew ::= new Mem
	// INew.a=inewR1(Mem.a)
	private GA.Inst recINew() throws IOException {
		if (tokenActual(CatLexica.NEW)) {
			rec(CatLexica.NEW);
			GA.Mem aMem = recMem();
			return inewR1(aMem);
		} else {
			errorSintactico();
			return null;
		}
	}

	// IDispose ::= delete Mem
	// IDispose.a=idisposeR1(Mem.a)
	private GA.Inst recIDispose() throws IOException {
		if (tokenActual(CatLexica.DELETE)) {
			rec(CatLexica.DELETE);
			GA.Mem aMem = recMem();
			return idisposeR1(aMem);
		} else {
			errorSintactico();
			return null;
		}
	}

	// ILectura ::= leer Mem
	// ILectura.a=ilecturaR1(Mem.a)
	private GA.Inst recILectura() throws IOException {
		if (tokenActual(CatLexica.LEER)) {
			rec(CatLexica.LEER);
			GA.Mem aMem = recMem();
			return ilecturaR1(aMem);
		} else {
			errorSintactico();
			return null;
		}
	}

	// IEscritura ::= escribir Exp0
	// IE scritura.a=iescrituraR1(Exp0.a)
	private GA.Inst recIEscritura() throws IOException {
		if (tokenActual(CatLexica.ESCRIBIR)) {
			rec(CatLexica.ESCRIBIR);
			GA.Exp0 aExp0 = recExp0();
			return iescrituraR1(aExp0);
		} else {
			errorSintactico();
			return null;
		}
	}

	// IIF ::= if Casos fi
	// IIF.a = IIFR1(Casos.a)
	private GA.Inst recIIF() throws IOException {
		if (tokenActual(CatLexica.IF)) {
			rec(CatLexica.IF);
			GA.Casos aCasos = recCasos();
			if (tokenActual(CatLexica.FI)) {
				rec(CatLexica.FI);
				return iifR1(aCasos);
			} else {
				errorSintactico(CatLexica.FI);
				return null;
			}
		} else {
			errorSintactico(CatLexica.IF);
			return null;
		}
	}

	// IDO ::= do Casos od
	// IDO.a = IDOR1(Casos.a)
	private GA.Inst recIDO() throws IOException {
		if (tokenActual(CatLexica.DO)) {
			rec(CatLexica.DO);
			GA.Casos aCasos = recCasos();
			if (tokenActual(CatLexica.OD)) {
				rec(CatLexica.OD);
				return idoR1(aCasos);
			} else {
				errorSintactico(CatLexica.OD);
				return null;
			}
		} else {
			errorSintactico(CatLexica.DO);
			return null;
		}
	}

	// Casos ::= Caso RCasos
	// Casos.a = RCasos.a
	// RCasos.ah = CasosR2(Caso.a)
	private GA.Casos recCasos() throws IOException {
		GA.Caso aCaso = recCaso();
		GA.Casos aRCasos = recRCasos(casosR2(aCaso));
		return aRCasos;

	}

	// RCasos ::= [] Caso RCasos
	// RCasos (0).a = RCasos(1).a
	// RCasos (1).ah = CasosR1(RCasos(0).ah, Caso.a)
	// RCasos ::= lambda
	// RCasos.a = RCasos.ah
	private GA.Casos recRCasos(GA.Casos ahRCasos0) throws IOException {
		if (tokenActual(CatLexica.CORCHETES)) {// TODO CAMBIADO
			rec(CatLexica.CORCHETES);
			GA.Caso aCaso = recCaso();
			GA.Casos aRCasos = recRCasos(casosR1(ahRCasos0, aCaso));
			return aRCasos;
		} else {
			return ahRCasos0;
		}

	}

	// Caso ::= case Exp0 -> Instrucciones
	// Caso.a = CasoR1(Exp0.a,Instrucciones.a)
	private GA.Caso recCaso() throws IOException {
		if (tokenActual(CatLexica.CASE)) {
			rec(CatLexica.CASE);
			GA.Exp0 aExpr0 = recExp0();
			if (tokenActual(CatLexica.FLECHA)) {
				rec(CatLexica.FLECHA);
				GA.Insts aInsts = recInsts();
				return casoR1(aExpr0, aInsts);
			} else {
				errorSintactico(CatLexica.FLECHA);
				return null;
			}
		} else {
			errorSintactico(CatLexica.CASE);
			return null;
		}
	}

	// Exp0 ::= Exp1 RExp0
	// Exp0.a = RExp0.a
	// RExp0.ah = Exp1.a
	private GA.Exp0 recExp0() throws IOException {
		GA.Exp1 aExp1 = recExp1();
		GA.Exp0 aRExp0 = recRExp0(aExp1);
		return aRExp0;
	}

	// Rexp0 ::= OpComparacion Exp1
	// RExp0.a = Exp0R1(RExp0.ah,Exp1.a)
	// RExp0 ::= lambda
	// RExp0.a = Exp0R2(RExp0.ah)
	private GA.Exp0 recRExp0(GA.Exp1 ahRExp0) throws IOException {
		if (esOpComparacion()) {
			GA.OpComparacion aOpc = recOpComparacion();
			GA.Exp1 aExp1 = recExp1();
			return exp0R1(ahRExp0, aExp1, aOpc);
		} else {

			return exp0R2(ahRExp0);
		}
	}

	// Exp1 ::= Exp2 RExp1
	// Exp1.a = RExp1.a
	// RExp1.ah = Exp1R2(Exp2.a)
	private GA.Exp1 recExp1() throws IOException {
		GA.Exp2 aExp2 = recExp2();
		GA.Exp1 aRExp1 = recRExp1(exp1R2(aExp2));
		return aRExp1;
	}

	// RExp1 ::= opAditivo Exp2 RExp1
	// RExp1(0).a = RExp1(1).a
	// RExp1(1).ah = Exp1R1(RExp1(0).ah, Exp2.a)
	// RExp1 ::= lambda
	// RExp1.a = RExp1.ah
	private GA.Exp1 recRExp1(GA.Exp1 ahExp1_0) throws IOException {
		if (esOpAditivo()) {
			GA.OpAditivo aOpA = recOpAditivo();
			GA.Exp2 aExp2 = recExp2();
			GA.Exp1 aRExp1 = recRExp1(exp1R1(ahExp1_0, aExp2, aOpA));
			return aRExp1;
		} else {
			return ahExp1_0;
		}
	}

	// Exp2 ::= Exp3 RExp2
	// Exp2.a = RExp2.a
	// RExp2.ah = Exp2R2(Exp3.a)
	private GA.Exp2 recExp2() throws IOException {
		GA.Exp3 aExp3 = recExp3();
		GA.Exp2 aRExp2 = recRExp2(exp2R2(aExp3));
		return aRExp2;
	}

	// RExp2 ::= opMultiplicativo Exp3 RExp2
	// RExp2 (0).a = RExp2 (1).a
	// RExp2 (1).ah = Exp2R1(RExp2(0).ah, Exp3.a)
	// RExp2 ::= lambda
	// RExp2.a = RExp2.ah
	private GA.Exp2 recRExp2(GA.Exp2 ahExp2_0) throws IOException {
		if (esOpMultiplicativo()) {
			GA.OpMultiplicativo aOpM = recOpMultiplicativo();
			GA.Exp3 aExp3 = recExp3();
			GA.Exp2 aRExp2 = recRExp2(exp2R1(ahExp2_0, aExp3, aOpM));
			return aRExp2;
		} else {
			return ahExp2_0;
		}
	}

	// Exp3 ::= OpUnario Exp3
	// Exp3(0).a = exp3R1(Exp3(1).a)
	// Exp3 ::= Exp4
	// Exp3.a = exp4R2(Exp4.a)
	private GA.Exp3 recExp3() throws IOException {
		if (esOpUnario()) {
			GA.OpUnario aDeOpU = recOpUnario();
			GA.Exp3 aExp3 = recExp3();
			return exp3R1(aDeOpU, aExp3);
		} else {
			GA.Exp4 aExp4 = recExp4();
			return exp3R2(aExp4);
		}
	}

	// Exp4 ::= true
	// Exp4.a=exp4R1()
	// Exp4 ::= false
	// Exp4.a=exp4R2()
	// Exp4 ::= NUM
	// Exp4.a = exp4R3(NUM.lex)
	// Exp4 ::= Mem
	// Exp4.a = exp4R4(Mem.a)
	// Exp4 ::= ( Exp0 )
	// Exp4.a = exp4R5(Exp0.a)
	private GA.Exp4 recExp4() throws IOException {
		if (tokenActual(CatLexica.TRUE)) {
			rec(CatLexica.TRUE);
			return exp4R1();
		} else if (tokenActual(CatLexica.FALSE)) {
			rec(CatLexica.FALSE);
			return exp4R2();
		} else if (tokenActual(CatLexica.NUM)) {
			Token tNum = tact;
			rec(CatLexica.NUM);
			return exp4R3(tNum);
		} else if (tokenActual(CatLexica.IDEN)) {
			GA.Mem aMem = recMem();
			return exp4R4(aMem);
		} else if (tokenActual(CatLexica.PAP)) {
			rec(CatLexica.PAP);
			GA.Exp0 aExp0 = recExp0();
			rec(CatLexica.PCIERRE);
			return exp4R5(aExp0);
		} else
			errorSintactico();
		return null;
	}

	// Mem ::= IDEN RMem
	// Mem.a = RMem.a
	// RMem.ah = memR1(IDEN.lex)
	private GA.Mem recMem() throws IOException {

		Token tIdem = tact;
		rec(CatLexica.IDEN);

		GA.Mem aRMem = recRMem(memR1(tIdem));
		return aRMem;
	}

	// RMem ::= [Exp0] RMem
	// RMem(0).a = RMem (1).a
	// RMem (1).ah = memR2(RMem(0).ah,Exp0.a)
	// RMem ::=.IDEN RMem
	// RMem(0).a = RMem (1).a
	// RMem (1).ah = memR3(RMem(0).ah,IDEN.lex)
	// RMem ::= ^ RMem
	// RMem(0).a = RMem (1).a
	// RMem (1).ah = memR4(RMem(0).ah)
	// RMem ::= lambda
	// RMem.a=RMem.ah

	private GA.Mem recRMem(GA.Mem ahRMem0) throws IOException {
		if (tokenActual(CatLexica.CAP)) {
			rec(CatLexica.CAP);
			GA.Exp0 aExp0 = recExp0();
			rec(CatLexica.CCIERRE);
			GA.Mem aRMem = recRMem(memR2(ahRMem0, aExp0));
			return aRMem;
		} else if (tokenActual(CatLexica.PUNTO)) {
			rec(CatLexica.PUNTO);
			Token tIdem = tact;
			rec(CatLexica.IDEN);
			GA.Mem aRMem = recRMem(memR3(ahRMem0, tIdem));
			return aRMem;
		} else if (tokenActual(CatLexica.ACENTO)) {
			rec(CatLexica.ACENTO);
			GA.Mem aRMem = recRMem(memR4(ahRMem0));
			return aRMem;
		} else {
			return ahRMem0;
		}

	}

	private boolean esOpUnario() {
		return tokenActual(CatLexica.MENOS) || tokenActual(CatLexica.NOT);

	}

	private boolean esOpMultiplicativo() {
		return tokenActual(CatLexica.ASTERISCO) || tokenActual(CatLexica.AND)
				|| tokenActual(CatLexica.BARRA);

	}

	private boolean esOpComparacion() {
		return tokenActual(CatLexica.EQ) || tokenActual(CatLexica.NEQ)
				|| tokenActual(CatLexica.GT) || tokenActual(CatLexica.GE)
				|| tokenActual(CatLexica.LT) || tokenActual(CatLexica.LE);

	}

	private boolean esOpAditivo() {
		return tokenActual(CatLexica.MAS) || tokenActual(CatLexica.MENOS)
				|| tokenActual(CatLexica.OR);

	}

	// OpComparacion ::= eq
	// OpComparacion.a = OpComparacionR1()
	// OpComparacion ::= neq
	// OpComparacion.a = OpComparacionR2()
	// OpComparacion ::= gt
	// OpComparacion.a = OpComparacionR3()
	// OpComparacion ::= ge
	// OpComparacion.a = OpComparacionR4()
	// OpComparacion ::= lt
	// OpComparacion.a = OpComparacionR5()
	// OpComparacion ::= le
	// OpComparacion.a = OpComparacionR6()
	private GA.OpComparacion recOpComparacion() throws IOException {
		if (tokenActual(CatLexica.EQ)) {
			rec(CatLexica.EQ);
			return opComparacionR1();
		} else if (tokenActual(CatLexica.NEQ)) {
			rec(CatLexica.NEQ);
			return opComparacionR2();
		} else if (tokenActual(CatLexica.GT)) {
			rec(CatLexica.GT);
			return opComparacionR3();
		} else if (tokenActual(CatLexica.GE)) {
			rec(CatLexica.GE);
			return opComparacionR4();
		} else if (tokenActual(CatLexica.LT)) {
			rec(CatLexica.LT);
			return opComparacionR5();
		} else if (tokenActual(CatLexica.LE)) {
			rec(CatLexica.LE);
			return opComparacionR6();
		}

		else {
			errorSintactico(CatLexica.EQ, CatLexica.NEQ, CatLexica.GT,
					CatLexica.GE, CatLexica.LT, CatLexica.LE);
			return null;
		}

	}

	// OpAditivo ::= +
	// OpAditivo.a = OpAditivoR1()
	// OpAditivo ::= -
	// OpAditivo.a = OpAditivoR2()
	// OpAditivo ::= or
	// OpAditivo.a = OpAditivoR3()
	private GA.OpAditivo recOpAditivo() throws IOException {
		if (tokenActual(CatLexica.MAS)) {
			rec(CatLexica.MAS);
			return opAditivoR1();
		} else if (tokenActual(CatLexica.MENOS)) {
			rec(CatLexica.MENOS);
			return opAditivoR2();
		} else if (tokenActual(CatLexica.OR)) {
			rec(CatLexica.OR);
			return opAditivoR3();
		} else {
			errorSintactico(CatLexica.MAS, CatLexica.MENOS, CatLexica.OR);
			return null;
		}

	}

	// OpMultiplicativo ::= *
	// OpMultiplicativo.a = OpMultiplicativoR1()
	// OpMultiplicativo ::= /
	// OpMultiplicativo.a = OpMultiplicativoR2()
	// OpMultiplicativo ::= and
	// OpMultiplicativo.a = OpMultiplicativoR3()
	private GA.OpMultiplicativo recOpMultiplicativo() throws IOException {
		if (tokenActual(CatLexica.ASTERISCO)) {
			rec(CatLexica.ASTERISCO);
			return opMultiplicativo1();
		} else if (tokenActual(CatLexica.BARRA)) {
			rec(CatLexica.BARRA);
			return opMultiplicativo2();
		} else if (tokenActual(CatLexica.AND)) {
			rec(CatLexica.AND);
			return opMultiplicativo3();
		} else {
			errorSintactico(CatLexica.ASTERISCO, CatLexica.BARRA, CatLexica.AND);
			return null;
		}

	}

	// OpUnario ::= -
	// OpUnario.a = OpUnarioR1()
	// OpUnario ::= not
	// OpUnario.a = OpUnarioR2()
	private GA.OpUnario recOpUnario() throws IOException {
		if (tokenActual(CatLexica.MENOS)) {
			rec(CatLexica.MENOS);
			return opUnario1();
		} else if (tokenActual(CatLexica.NOT)) {
			rec(CatLexica.NOT);
			return opUnario2();
		} else {
			errorSintactico(CatLexica.MENOS, CatLexica.NOT);
			return null;
		}

	}

	private void rec(CatLexica cat) throws IOException {
		if (tokenActual(cat)) {
			tact = sigToken();
		} else {
			errorSintactico(cat);
		}
	}

	private Token sigToken() throws IOException {
		return analizadorLexico.sigToken();
	}

	private boolean tokenActual(CatLexica... cats) {
		boolean encontrada = false;
		int i = 0;
		while (i < cats.length && !encontrada) {
			encontrada = tact.leeCategoria() == cats[i];
			i++;
		}
		return encontrada;
	}

	private void errorSintactico(CatLexica... catsEsperadas) {
		System.err.print("(" + tact.leeFila() + "," + tact.leeCol() + ")"
				+ "ERROR SINTACTICO: Encontrado " + tact
				+ ". Se esperaba alguno de los siguientes elementos: ");
		for (CatLexica catEsperada : catsEsperadas) {
			System.err.print(catEsperada + " ");
		}
		System.err.println();
		System.exit(1);
	}

	// Funciones de conexion con Gramatica de atributos
	private GA.Programa progR1(GA.Bloque bloque) {
		if (Config.DEBUG)
			return gramaticaAtributos.new ProgR1Debug(bloque);
		else
			return gramaticaAtributos.new ProgR1(bloque);
	}

	private GA.Bloque bloqueR1(GA.Decs decs, GA.Insts insts) {
		if (Config.DEBUG)
			return gramaticaAtributos.new BloqueR1Debug(decs, insts);
		else
			return gramaticaAtributos.new BloqueR1(decs, insts);
	}

	private GA.Bloque bloqueR2(GA.Insts insts) {
		if (Config.DEBUG)
			return gramaticaAtributos.new BloqueR2Debug(insts);
		else
			return gramaticaAtributos.new BloqueR2(insts);
	}

	private GA.Decs decsR1(GA.Decs ahDeRDecs_0, GA.Dec aDec) {
		if (Config.DEBUG)
			return gramaticaAtributos.new DecsR1Debug(ahDeRDecs_0, aDec);
		else
			return gramaticaAtributos.new DecsR1(ahDeRDecs_0, aDec);
	}

	private GA.Decs decsR2(GA.Dec aDec) {
		if (Config.DEBUG)
			return gramaticaAtributos.new DecsR2Debug(aDec);
		else
			return gramaticaAtributos.new DecsR2(aDec);
	}

	private GA.Dec decR1(GA.Tipo tipo, Token iden) {
		if (Config.DEBUG)
			return gramaticaAtributos.new DecR1Debug(tipo, iden);
		else
			return gramaticaAtributos.new DecR1(tipo, iden);
	}

	private GA.Dec decR2(GA.Tipo tipo, Token iden) {
		if (Config.DEBUG)
			return gramaticaAtributos.new DecR2Debug(tipo, iden);
		else
			return gramaticaAtributos.new DecR2(tipo, iden);
	}

	private GA.Dec decR3(Token iden, GA.ParamsFormales ParamsFormales,
			GA.Bloque Bloque) {
		if (Config.DEBUG)
			return gramaticaAtributos.new DecR3Debug(iden, ParamsFormales,
					Bloque);
		else
			return gramaticaAtributos.new DecR3(iden, ParamsFormales, Bloque);
	}

	private GA.ParamsFormalesR1 parametrosFormalesR1(
			GA.ListaParamsFormales aParamsFormales) {
		if (!Config.DEBUG)
			return gramaticaAtributos.new ParamsFormalesR1(aParamsFormales);
		else
			return gramaticaAtributos.new ParamsFormalesR1Debug(aParamsFormales);
	}

	private GA.ParamsFormalesR2 parametrosFormalesR2() {
		if (!Config.DEBUG)
			return gramaticaAtributos.new ParamsFormalesR2();
		else
			return gramaticaAtributos.new ParamsFormalesR2Debug();
	}

	private GA.ListaParamsFormalesR2 listaParametrosFormalesR2(
			GA.ParamFormal aParamFormal) {
		if (!Config.DEBUG)
			return gramaticaAtributos.new ListaParamsFormalesR2(aParamFormal);
		else
			return gramaticaAtributos.new ListaParamsFormalesR2Debug(
					aParamFormal);
	}

	private GA.ListaParamsFormalesR1 listaParametrosFormalesR1(
			GA.ListaParamsFormales ahRListaParamsFormales0,
			GA.ParamFormal aParamFormal) {
		if (!Config.DEBUG)
			return gramaticaAtributos.new ListaParamsFormalesR1(
					ahRListaParamsFormales0, aParamFormal);
		else
			return gramaticaAtributos.new ListaParamsFormalesR1Debug(
					ahRListaParamsFormales0, aParamFormal);
	}

	private GA.ParamFormalR2 parametroFormalR2(GA.Tipo aTipo, Token tIden) {
		if (!Config.DEBUG)
			return gramaticaAtributos.new ParamFormalR2(aTipo, tIden);
		else
			return gramaticaAtributos.new ParamFormalR2Debug(aTipo, tIden);
	}

	private GA.ParamFormalR1 parametroFormalR1(GA.Tipo aTipo, Token tIden) {
		if (!Config.DEBUG)
			return gramaticaAtributos.new ParamFormalR1(aTipo, tIden);
		else
			return gramaticaAtributos.new ParamFormalR1Debug(aTipo, tIden);
	}

	private GA.TipoR1 tipoR1() {
		if (Config.DEBUG)
			return gramaticaAtributos.new TipoR1Debug();
		else
			return gramaticaAtributos.new TipoR1();
	}

	private GA.TipoR2 tipoR2() {
		if (Config.DEBUG)
			return gramaticaAtributos.new TipoR2Debug();
		else
			return gramaticaAtributos.new TipoR2();
	}

	private GA.TipoR3 tipoR3(Token aNum, GA.Tipo aTipo) {
		if (Config.DEBUG)
			return gramaticaAtributos.new TipoR3Debug(aNum, aTipo);
		else
			return gramaticaAtributos.new TipoR3(aNum, aTipo);
	}

	private GA.TipoR4 tipoR4(GA.Campos aCampos) {
		if (Config.DEBUG)
			return gramaticaAtributos.new TipoR4Debug(aCampos);
		else
			return gramaticaAtributos.new TipoR4(aCampos);
	}

	private GA.TipoR5 tipoR5(GA.Tipo aTipo) {
		if (Config.DEBUG)
			return gramaticaAtributos.new TipoR5Debug(aTipo);
		else
			return gramaticaAtributos.new TipoR5(aTipo);
	}

	private GA.TipoR6 tipoR6(Token tIden) {
		if (Config.DEBUG)
			return gramaticaAtributos.new TipoR6Debug(tIden);
		else
			return gramaticaAtributos.new TipoR6(tIden);
	}

	private GA.CamposR1 camposR1(GA.Campos ahRCampos0, GA.Campo aCampo) {
		if (Config.DEBUG)
			return gramaticaAtributos.new CamposR1Debug(ahRCampos0, aCampo);
		else
			return gramaticaAtributos.new CamposR1(ahRCampos0, aCampo);
	}

	private GA.CamposR2 camposR2(GA.Campo aCamp) {
		if (Config.DEBUG)
			return gramaticaAtributos.new CamposR2Debug(aCamp);
		else
			return gramaticaAtributos.new CamposR2(aCamp);
	}

	private GA.CampoR1 campoR1(GA.Tipo aTipo, Token tIden) {
		if (Config.DEBUG)
			return gramaticaAtributos.new CampoR1Debug(aTipo, tIden);
		else
			return gramaticaAtributos.new CampoR1(aTipo, tIden);
	}

	private GA.ParamsRealesR1 parametrosRelaesR1(
			GA.ListaParamsReales aListaParamsReales) {
		if (Config.DEBUG)
			return gramaticaAtributos.new ParamsRealesR1Debug(
					aListaParamsReales);
		else
			return gramaticaAtributos.new ParamsRealesR1(aListaParamsReales);
	}

	private GA.ParamsRealesR2 parametrosRelaesR2() {
		if (Config.DEBUG)
			return gramaticaAtributos.new ParamsRealesR2Debug();
		else
			return gramaticaAtributos.new ParamsRealesR2();
	}

	private GA.ListaParamsRealesR2 listaParametrosRealesR2(GA.Exp0 aExp0) {
		if (Config.DEBUG)
			return gramaticaAtributos.new ListaParamsRealesR2Debug(aExp0);
		else
			return gramaticaAtributos.new ListaParamsRealesR2(aExp0);
	}

	private GA.ListaParamsRealesR1 listaParametrosRealesR1(
			GA.ListaParamsReales ahRListaParamsReales0, GA.Exp0 aExp0) {
		if (Config.DEBUG)
			return gramaticaAtributos.new ListaParamsRealesR1Debug(
					ahRListaParamsReales0, aExp0);
		else
			return gramaticaAtributos.new ListaParamsRealesR1(
					ahRListaParamsReales0, aExp0);
	}

	private GA.MemR1 memR1(Token tIdem) {
		if (Config.DEBUG)
			return gramaticaAtributos.new MemR1Debug(tIdem);
		else
			return gramaticaAtributos.new MemR1(tIdem);
	}

	private GA.MemR4 memR2(GA.Mem ahRMem0, GA.Exp0 aExp0) {
		if (Config.DEBUG)
			return gramaticaAtributos.new MemR4Debug(ahRMem0, aExp0);
		else
			return gramaticaAtributos.new MemR4(ahRMem0, aExp0);
	}

	private GA.MemR2 memR3(GA.Mem ahRMem0, Token tIdem) {
		if (Config.DEBUG)
			return gramaticaAtributos.new MemR2Debug(ahRMem0, tIdem);
		else
			return gramaticaAtributos.new MemR2(ahRMem0, tIdem);
	}

	private GA.MemR3 memR4(GA.Mem ahRMem0) {
		if (Config.DEBUG)
			return gramaticaAtributos.new MemR3Debug(ahRMem0);
		else
			return gramaticaAtributos.new MemR3(ahRMem0);
	}

	/*
	 * private GA.Tipo tipoR2(Token tDeBool) { if (Config.DEBUG) return
	 * gramaticaAtributos.new TipoR2Debug(tDeBool); else return
	 * gramaticaAtributos.new TipoR2(tDeBool); }
	 */

	// ---------------------------------Cuidado las intruciones del constructor
	// del arbol tienen distinto numero que las de la gramatica
	private GA.InstR1 instR1(GA.Inst IAsig) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstR1Debug(IAsig);
		else
			return gramaticaAtributos.new InstR1(IAsig);
	}

	private GA.Inst instR2(GA.Inst INew) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstR4Debug(INew);
		else
			return gramaticaAtributos.new InstR4(INew);
	}

	private GA.Inst instR3(GA.Inst IDispose) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstR6Debug(IDispose);
		else
			return gramaticaAtributos.new InstR6(IDispose);
	}

	private GA.Inst instR4(GA.Inst ILectura) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstR7Debug(ILectura);
		else
			return gramaticaAtributos.new InstR7(ILectura);
	}

	private GA.Inst instR5(GA.Inst IEscritura) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstR8Debug(IEscritura);
		else
			return gramaticaAtributos.new InstR8(IEscritura);
	}

	private GA.Inst instR6(GA.Inst ILlamada) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstR9Debug(ILlamada);
		else
			return gramaticaAtributos.new InstR9(ILlamada);
	}

	private GA.Inst instR7(GA.Inst IIF) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstR2Debug(IIF);
		else
			return gramaticaAtributos.new InstR2(IIF);
	}

	private GA.Inst instR8(GA.Inst IDo) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstR3Debug(IDo);
		else
			return gramaticaAtributos.new InstR3(IDo);
	}

	private GA.Insts instsR1(GA.Insts ahDeRInsts0, GA.Inst aInst) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstsR1Debug(ahDeRInsts0, aInst);
		else
			return gramaticaAtributos.new InstsR1(ahDeRInsts0, aInst);
	}

	private GA.Insts instsR2(GA.Inst inst) {
		if (Config.DEBUG)
			return gramaticaAtributos.new InstsR2Debug(inst);
		else
			return gramaticaAtributos.new InstsR2(inst);
	}

	private GA.Inst iasigR1(GA.Mem mem, GA.Exp0 exp0) {
		if (Config.DEBUG)
			return gramaticaAtributos.new IAsigR1Debug(mem, exp0);
		else
			return gramaticaAtributos.new IAsigR1(mem, exp0);
	}

	private GA.Inst iifR1(GA.Casos casos) {
		if (Config.DEBUG)
			return gramaticaAtributos.new IIFR1Debug(casos);
		else
			return gramaticaAtributos.new IIFR1(casos);
	}

	private GA.Inst idoR1(GA.Casos casos) {
		if (Config.DEBUG)
			return gramaticaAtributos.new IDOR1Debug(casos);
		else
			return gramaticaAtributos.new IDOR1(casos);
	}

	private GA.INewR1 inewR1(GA.Mem mem) {
		if (Config.DEBUG)
			return gramaticaAtributos.new INewR1Debug(mem);
		else
			return gramaticaAtributos.new INewR1Debug(mem);
	}

	private GA.IDisposeR1 idisposeR1(GA.Mem mem) {
		if (Config.DEBUG)
			return gramaticaAtributos.new IDisposeR1Debug(mem);
		else
			return gramaticaAtributos.new IDisposeR1(mem);
	}

	private GA.ILecturaR1 ilecturaR1(GA.Mem mem) {
		if (Config.DEBUG)
			return gramaticaAtributos.new ILecturaR1Debug(mem);
		else
			return gramaticaAtributos.new ILecturaR1(mem);
	}

	private GA.IEscrituraR1 iescrituraR1(GA.Exp0 exp0) {
		if (Config.DEBUG)
			return gramaticaAtributos.new IEscrituraR1Debug(exp0);
		else
			return gramaticaAtributos.new IEscrituraR1(exp0);
	}

	private GA.ILlamadaR1 illamadaR1(Token tIdem,
			GA.ParamsReales aParametrosReales) {
		if (Config.DEBUG)
			return gramaticaAtributos.new ILlamadaR1Debug(tIdem,
					aParametrosReales);
		else
			return gramaticaAtributos.new ILlamadaR1(tIdem, aParametrosReales);
	}

	private GA.Caso casoR1(GA.Exp0 exp0, GA.Insts insts) {
		if (Config.DEBUG)
			return gramaticaAtributos.new CasoR1Debug(exp0, insts);
		else
			return gramaticaAtributos.new CasoR1(exp0, insts);
	}

	private GA.Casos casosR2(GA.Caso aCaso) {
		if (Config.DEBUG)
			return gramaticaAtributos.new CasosR2Debug(aCaso);
		else
			return gramaticaAtributos.new CasosR2(aCaso);
	}

	private GA.Casos casosR1(GA.Casos casos, GA.Caso caso) {
		if (Config.DEBUG)
			return gramaticaAtributos.new CasosR1Debug(casos, caso);
		else
			return gramaticaAtributos.new CasosR1(casos, caso);
	}

	private GA.Exp0 exp0R1(GA.Exp1 exp1, GA.Exp1 exp1_1, GA.OpComparacion opcomp) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp0R1Debug(exp1, exp1_1, opcomp);
		else
			return gramaticaAtributos.new Exp0R1(exp1, exp1_1, opcomp);
	}

	private GA.Exp0 exp0R2(GA.Exp1 exp1) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp0R2Debug(exp1);
		else
			return gramaticaAtributos.new Exp0R2(exp1);
	}

	private GA.Exp1 exp1R2(GA.Exp2 exp2) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp1R2Debug(exp2);
		else
			return gramaticaAtributos.new Exp1R2(exp2);
	}

	private GA.Exp1 exp1R1(GA.Exp1 exp1, GA.Exp2 exp2, GA.OpAditivo opaditivo) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp1R1Debug(exp1, exp2, opaditivo);
		else
			return gramaticaAtributos.new Exp1R1(exp1, exp2, opaditivo);
	}

	private GA.Exp2 exp2R2(GA.Exp3 exp3) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp2R2Debug(exp3);
		else
			return gramaticaAtributos.new Exp2R2(exp3);
	}

	private GA.Exp2 exp2R1(GA.Exp2 exp2, GA.Exp3 exp3, GA.OpMultiplicativo opmul) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp2R1Debug(exp2, exp3, opmul);
		else
			return gramaticaAtributos.new Exp2R1(exp2, exp3, opmul);

	}

	private GA.Exp3 exp3R1(GA.OpUnario opunario, GA.Exp3 exp3) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp3R1Debug(exp3, opunario);
		else
			return gramaticaAtributos.new Exp3R1(exp3, opunario);
	}

	private GA.Exp3 exp3R2(GA.Exp4 exp4) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp3R2Debug(exp4);
		else
			return gramaticaAtributos.new Exp3R2(exp4);
	}

	private GA.Exp4 exp4R1() {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp4R1Debug();
		else
			return gramaticaAtributos.new Exp4R1();
	}

	private GA.Exp4 exp4R2() {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp4R2Debug();
		else
			return gramaticaAtributos.new Exp4R2();
	}

	private GA.Exp4 exp4R3(Token num) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp4R3Debug(num);
		else
			return gramaticaAtributos.new Exp4R3(num);
	}

	private GA.Exp4 exp4R4(GA.Mem mem) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp4R4Debug(mem);
		else
			return gramaticaAtributos.new Exp4R4(mem);
	}

	private GA.Exp4 exp4R5(GA.Exp0 exp0) {
		if (Config.DEBUG)
			return gramaticaAtributos.new Exp4R5Debug(exp0);
		else
			return gramaticaAtributos.new Exp4R5(exp0);
	}

	private GA.OpComparacion opComparacionR1() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpComparacionR1Debug();
		else
			return gramaticaAtributos.new OpComparacionR1();
	}

	private GA.OpComparacion opComparacionR2() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpComparacionR2Debug();
		else
			return gramaticaAtributos.new OpComparacionR2();
	}

	private GA.OpComparacion opComparacionR3() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpComparacionR3Debug();
		else
			return gramaticaAtributos.new OpComparacionR3();
	}

	private GA.OpComparacion opComparacionR4() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpComparacionR4Debug();
		else
			return gramaticaAtributos.new OpComparacionR4();
	}

	private GA.OpComparacion opComparacionR5() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpComparacionR5Debug();
		else
			return gramaticaAtributos.new OpComparacionR5();
	}

	private GA.OpComparacion opComparacionR6() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpComparacionR6Debug();
		else
			return gramaticaAtributos.new OpComparacionR6();
	}

	private GA.OpAditivo opAditivoR1() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpAditivoR1Debug();
		else
			return gramaticaAtributos.new OpAditivoR1();
	}

	private GA.OpAditivo opAditivoR2() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpAditivoR2Debug();
		else
			return gramaticaAtributos.new OpAditivoR2();
	}

	private GA.OpAditivo opAditivoR3() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpAditivoR3Debug();
		else
			return gramaticaAtributos.new OpAditivoR3();
	}

	private GA.OpMultiplicativo opMultiplicativo1() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpMultiplicativoR1Debug();
		else
			return gramaticaAtributos.new OpMultiplicativoR1();
	}

	private GA.OpMultiplicativo opMultiplicativo2() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpMultiplicativoR2Debug();
		else
			return gramaticaAtributos.new OpMultiplicativoR2();
	}

	private GA.OpMultiplicativo opMultiplicativo3() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpMultiplicativoR3Debug();
		else
			return gramaticaAtributos.new OpMultiplicativoR3();
	}

	private GA.OpUnario opUnario1() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpUnarioR1Debug();
		else
			return gramaticaAtributos.new OpUnarioR1();

	}

	private GA.OpUnario opUnario2() {
		if (Config.DEBUG)
			return gramaticaAtributos.new OpUnarioR2Debug();
		else
			return gramaticaAtributos.new OpUnarioR2();
	}
}