import java.io.IOException;

public class ConstructorArbolesTiny {

	public static final boolean DEBUG = false;
	private Token tact;

	private AnalizadorLexico analizadorLexico;

	private GA gramaticaAtributos = new GA();

	public ConstructorArbolesTiny(AnalizadorLexico analizadorLexico) {
		this.analizadorLexico = analizadorLexico;
	}

	public GA.Programa parse() throws IOException {
		tact = analizadorLexico.sigToken();
		GA.Programa aDePrograma = recPrograma();
		rec(CatLexica.EOF);
		return aDePrograma;
	}

	// Programa ::= Declaraciones & Instrucciones
	// Programa.a = progR1(Declaraciones.a,Instrucciones.a)
	// Programa ::= Instrucciones
	// Programa.a = progR2(Instrucciones.a)
	private GA.Programa recPrograma() throws IOException {
		if (tokenActual(CatLexica.INT) || tokenActual(CatLexica.BOOLEAN)) {
			GA.Decs aDecs = recDecs();
			if (tokenActual(CatLexica.AMPERSAND)) {
				rec(CatLexica.AMPERSAND);
				GA.Insts aInsts = recInsts();
				return progR1(aDecs, aInsts);
			}
		} else if (tokenActual(CatLexica.IDEN) || tokenActual(CatLexica.IF)
				|| tokenActual(CatLexica.DO)) {
			GA.Insts aInsts = recInsts();
			return progR2(aInsts);
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
	private GA.Dec recDec() throws IOException {
		GA.Tipo aTipo = recTipo();
		if (tokenActual(CatLexica.IDEN)) {
			Token tIden = tact;
			rec(CatLexica.IDEN);
			return decR1(aTipo, tIden);
		} else {
			errorSintactico(CatLexica.IDEN);
			return null;
		}
	}

	// Tipo ::= int
	// Tipo.a=tipoR1()
	// Tipo ::= boolean
	// Tipo.a=tipoR2()
	private GA.Tipo recTipo() throws IOException {
		if (tokenActual(CatLexica.INT)) {
			Token tInt = tact;
			rec(CatLexica.INT);
			return tipoR1(tInt);
		} else if (tokenActual(CatLexica.BOOLEAN)) {
			Token tBool = tact;
			rec(CatLexica.BOOLEAN);
			return tipoR2(tBool);
		} else {
			errorSintactico(CatLexica.INT, CatLexica.BOOLEAN);
			return null;
		}

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
	// Instruccion ::= IIF
	// Instruccion.a = instR2(IIF.a)
	// Instruccion ::= IDO
	// Instruccion.a = instR3(IDO.a)
	private GA.Inst recInst() throws IOException {
		if (tokenActual(CatLexica.IDEN)) {
			return recIAsig();
		} else if (tokenActual(CatLexica.IF)) {
			return recIIF();
		} else if (tokenActual(CatLexica.DO)) {
			return recIDO();
		} else {
			errorSintactico();
			return null;
		}

	}

	// IAsignacion ::= IDEN := Exp0
	// IAsignacion.a = iasigR1(IDEN.lex,Exp0.a)
	private GA.Inst recIAsig() throws IOException {
		if (tokenActual(CatLexica.IDEN)) {
			Token tIden = tact;
			rec(CatLexica.IDEN);
			if (tokenActual(CatLexica.IGUAL)) {
				rec(CatLexica.IGUAL);
				GA.Exp0 aExpr0 = recExp0();
				return iasigR1(tIden, aExpr0);
			} else {
				errorSintactico(CatLexica.IGUAL);
				return null;
			}
		} else {
			errorSintactico(CatLexica.IDEN);
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
		if (tokenActual(CatLexica.CAP)) {
			rec(CatLexica.CAP);
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
	// Exp4 ::= IDEN
	// Exp4.a = exp4R4(IDEN.lex)
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
			Token tIden = tact;
			rec(CatLexica.IDEN);
			return exp4R4(tIden);
		} else if (tokenActual(CatLexica.PAP)) {
			rec(CatLexica.PAP);
			GA.Exp0 aExp0 = recExp0();
			rec(CatLexica.PCIERRE);
			return exp4R5(aExp0);
		} else
			errorSintactico();
		return null;
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
	private GA.Programa progR1(GA.Decs decs, GA.Insts insts) {
		if (DEBUG)
			return gramaticaAtributos.new ProgR1Debug(decs, insts);
		else
			return gramaticaAtributos.new ProgR1(decs, insts);
	}

	private GA.Programa progR2(GA.Insts insts) {
		if (DEBUG)
			return gramaticaAtributos.new ProgR2Debug(insts);
		else
			return gramaticaAtributos.new ProgR2(insts);
	}

	private GA.Decs decsR2(GA.Dec aDec) {
		if (DEBUG)
			return gramaticaAtributos.new DecsR2Debug(aDec);
		else
			return gramaticaAtributos.new DecsR2(aDec);
	}

	private GA.Dec decR1(GA.Tipo tipo, Token iden) {
		if (DEBUG)
			return gramaticaAtributos.new DecR1Debug(tipo, iden);
		else
			return gramaticaAtributos.new DecR1(tipo, iden);
	}

	private GA.Decs decsR1(GA.Decs ahDeRDecs_0, GA.Dec aDec) {
		if (DEBUG)
			return gramaticaAtributos.new DecsR1Debug(ahDeRDecs_0, aDec);
		else
			return gramaticaAtributos.new DecsR1(ahDeRDecs_0, aDec);
	}

	private GA.Tipo tipoR1(Token tDeInt) {
		if (DEBUG)
			return gramaticaAtributos.new TipoR1Debug(tDeInt);
		else
			return gramaticaAtributos.new TipoR1(tDeInt);
	}

	private GA.Tipo tipoR2(Token tDeBool) {
		if (DEBUG)
			return gramaticaAtributos.new TipoR2Debug(tDeBool);
		else
			return gramaticaAtributos.new TipoR2(tDeBool);
	}

	private GA.Insts instsR2(GA.Inst inst) {
		if (DEBUG)
			return gramaticaAtributos.new InstsR2Debug(inst);
		else
			return gramaticaAtributos.new InstsR2(inst);
	}

	private GA.Insts instsR1(GA.Insts ahDeRInsts0, GA.Inst aInst) {
		if (DEBUG)
			return gramaticaAtributos.new InstsR1Debug(ahDeRInsts0, aInst);
		else
			return gramaticaAtributos.new InstsR1(ahDeRInsts0, aInst);
	}

	private GA.Inst iasigR1(Token iden, GA.Exp0 exp0) {
		if (DEBUG)
			return gramaticaAtributos.new IAsigR1Debug(iden, exp0);
		else
			return gramaticaAtributos.new IAsigR1(iden, exp0);
	}

	private GA.Inst iifR1(GA.Casos casos) {
		if (DEBUG)
			return gramaticaAtributos.new IIFR1Debug(casos);
		else
			return gramaticaAtributos.new IIFR1(casos);
	}

	private GA.Inst idoR1(GA.Casos casos) {
		if (DEBUG)
			return gramaticaAtributos.new IDOR1Debug(casos);
		else
			return gramaticaAtributos.new IDOR1(casos);
	}

	private GA.Caso casoR1(GA.Exp0 exp0, GA.Insts insts) {
		if (DEBUG)
			return gramaticaAtributos.new CasoR1Debug(exp0, insts);
		else
			return gramaticaAtributos.new CasoR1(exp0, insts);
	}

	private GA.Casos casosR2(GA.Caso aCaso) {
		if (DEBUG)
			return gramaticaAtributos.new CasosR2Debug(aCaso);
		else
			return gramaticaAtributos.new CasosR2(aCaso);
	}

	private GA.Casos casosR1(GA.Casos casos, GA.Caso caso) {
		if (DEBUG)
			return gramaticaAtributos.new CasosR1Debug(casos, caso);
		else
			return gramaticaAtributos.new CasosR1(casos, caso);
	}

	private GA.Exp0 exp0R1(GA.Exp1 exp1, GA.Exp1 exp1_1, GA.OpComparacion opcomp) {
		if (DEBUG)
			return gramaticaAtributos.new Exp0R1Debug(exp1, exp1_1, opcomp);
		else
			return gramaticaAtributos.new Exp0R1(exp1, exp1_1, opcomp);
	}

	private GA.Exp0 exp0R2(GA.Exp1 exp1) {
		if (DEBUG)
			return gramaticaAtributos.new Exp0R2Debug(exp1);
		else
			return gramaticaAtributos.new Exp0R2(exp1);
	}

	private GA.Exp1 exp1R2(GA.Exp2 exp2) {
		if (DEBUG)
			return gramaticaAtributos.new Exp1R2Debug(exp2);
		else
			return gramaticaAtributos.new Exp1R2(exp2);
	}

	private GA.Exp1 exp1R1(GA.Exp1 exp1, GA.Exp2 exp2, GA.OpAditivo opaditivo) {
		if (DEBUG)
			return gramaticaAtributos.new Exp1R1Debug(exp1, exp2, opaditivo);
		else
			return gramaticaAtributos.new Exp1R1(exp1, exp2, opaditivo);
	}

	private GA.Exp2 exp2R2(GA.Exp3 exp3) {
		if (DEBUG)
			return gramaticaAtributos.new Exp2R2Debug(exp3);
		else
			return gramaticaAtributos.new Exp2R2(exp3);
	}

	private GA.Exp2 exp2R1(GA.Exp2 exp2, GA.Exp3 exp3, GA.OpMultiplicativo opmul) {
		if (DEBUG)
			return gramaticaAtributos.new Exp2R1Debug(exp2, exp3, opmul);
		else
			return gramaticaAtributos.new Exp2R1(exp2, exp3, opmul);

	}

	private GA.Exp3 exp3R1(GA.OpUnario opunario, GA.Exp3 exp3) {
		if (DEBUG)
			return gramaticaAtributos.new Exp3R1Debug(exp3, opunario);
		else
			return gramaticaAtributos.new Exp3R1(exp3, opunario);
	}

	private GA.Exp3 exp3R2(GA.Exp4 exp4) {
		if (DEBUG)
			return gramaticaAtributos.new Exp3R2Debug(exp4);
		else
			return gramaticaAtributos.new Exp3R2(exp4);
	}

	private GA.Exp4 exp4R1() {
		if (DEBUG)
			return gramaticaAtributos.new Exp4R1Debug();
		else
			return gramaticaAtributos.new Exp4R1();
	}

	private GA.Exp4 exp4R2() {
		if (DEBUG)
			return gramaticaAtributos.new Exp4R2Debug();
		else
			return gramaticaAtributos.new Exp4R2();
	}

	private GA.Exp4 exp4R3(Token num) {
		if (DEBUG)
			return gramaticaAtributos.new Exp4R3Debug(num);
		else
			return gramaticaAtributos.new Exp4R3(num);
	}

	private GA.Exp4 exp4R4(Token iden) {
		if (DEBUG)
			return gramaticaAtributos.new Exp4R4Debug(iden);
		else
			return gramaticaAtributos.new Exp4R4(iden);
	}

	private GA.Exp4 exp4R5(GA.Exp0 exp0) {
		if (DEBUG)
			return gramaticaAtributos.new Exp4R5Debug(exp0);
		else
			return gramaticaAtributos.new Exp4R5(exp0);
	}

	private GA.OpComparacion opComparacionR1() {
		if (DEBUG)
			return gramaticaAtributos.new OpComparacionR1Debug();
		else
			return gramaticaAtributos.new OpComparacionR1();
	}

	private GA.OpComparacion opComparacionR2() {
		if (DEBUG)
			return gramaticaAtributos.new OpComparacionR2Debug();
		else
			return gramaticaAtributos.new OpComparacionR2();
	}

	private GA.OpComparacion opComparacionR3() {
		if (DEBUG)
			return gramaticaAtributos.new OpComparacionR3Debug();
		else
			return gramaticaAtributos.new OpComparacionR3();
	}

	private GA.OpComparacion opComparacionR4() {
		if (DEBUG)
			return gramaticaAtributos.new OpComparacionR4Debug();
		else
			return gramaticaAtributos.new OpComparacionR4();
	}

	private GA.OpComparacion opComparacionR5() {
		if (DEBUG)
			return gramaticaAtributos.new OpComparacionR5Debug();
		else
			return gramaticaAtributos.new OpComparacionR5();
	}

	private GA.OpComparacion opComparacionR6() {
		if (DEBUG)
			return gramaticaAtributos.new OpComparacionR6Debug();
		else
			return gramaticaAtributos.new OpComparacionR6();
	}

	private GA.OpAditivo opAditivoR1() {
		if (DEBUG)
			return gramaticaAtributos.new OpAditivoR1Debug();
		else
			return gramaticaAtributos.new OpAditivoR1();
	}

	private GA.OpAditivo opAditivoR2() {
		if (DEBUG)
			return gramaticaAtributos.new OpAditivoR2Debug();
		else
			return gramaticaAtributos.new OpAditivoR2();
	}

	private GA.OpAditivo opAditivoR3() {
		if (DEBUG)
			return gramaticaAtributos.new OpAditivoR3Debug();
		else
			return gramaticaAtributos.new OpAditivoR3();
	}

	private GA.OpMultiplicativo opMultiplicativo1() {
		if (DEBUG)
			return gramaticaAtributos.new OpMultiplicativoR1Debug();
		else
			return gramaticaAtributos.new OpMultiplicativoR1();
	}

	private GA.OpMultiplicativo opMultiplicativo2() {
		if (DEBUG)
			return gramaticaAtributos.new OpMultiplicativoR2Debug();
		else
			return gramaticaAtributos.new OpMultiplicativoR2();
	}

	private GA.OpMultiplicativo opMultiplicativo3() {
		if (DEBUG)
			return gramaticaAtributos.new OpMultiplicativoR3Debug();
		else
			return gramaticaAtributos.new OpMultiplicativoR3();
	}

	private GA.OpUnario opUnario1() {
		if (DEBUG)
			return gramaticaAtributos.new OpUnarioR1Debug();
		else
			return gramaticaAtributos.new OpUnarioR1();
	}

	private GA.OpUnario opUnario2() {
		if (DEBUG)
			return gramaticaAtributos.new OpUnarioR2Debug();
		else
			return gramaticaAtributos.new OpUnarioR2();
	}
}