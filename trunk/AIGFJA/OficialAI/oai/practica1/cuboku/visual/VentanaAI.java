//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.visual;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import oai.aima.util.AimaUtil;
import oai.practica1.cuboku.Cuboku;
import oai.practica1.cuboku.heuristicas.H1HeuristicFunction;
import oai.practica1.cuboku.heuristicas.H2HeuristicFunction;
import oai.practica1.cuboku.heuristicas.H3HeuristicFunction;
import oai.practica1.cuboku.util.Algoritmo;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.SearchAgent;

/**
 * JFrame de la aplicacion
 * 
 * @author Jose Angel Garcia Fernandez
 * @date 1.2 06/12/2011
 */
public class VentanaAI extends JFrame {

	private static final long serialVersionUID = 1L;

	// props
	private Cuboku cuboku = null;
	private SearchAgent searchAgent;
	private HeuristicFunction h = null;
	private Algoritmo alg;
	int algoritmo = 0, heuristica = 0, prof = 0;
	// componentes
	private JPanel jPBase = null;
	private JPanelAIconfig jPAIconfig = null;
	private JPanelAIdemo jPAIdemo = null;
	private JMenuBarJ jMBJ = null;
	private CardLayout layout = null;

	// capas
	final static String AICONFIG = "AI configuracion";
	final static String AIDEMO = "AI Demo";

	/**
	 * This is the default constructor
	 */
	public VentanaAI() {
		super();
		initialize();
	}

	/**
	 * Establece la capa visible
	 */
	void estableceLayout(String layoutActual) {
		layout.show(jPBase, layoutActual);
	}

	/**
	 * Metodo que prepara el panel de demo usando el de configuracion
	 */
	public void preparaProbar() {
	
		// obtengo propiedades
		String pathString = jPAIconfig.propiedades
				.getProperty(AimaUtil.keyFile);
		String algoString = jPAIconfig.propiedades
				.getProperty(AimaUtil.keyAlgoritmo);
		String heurString = jPAIconfig.propiedades.getProperty(AimaUtil.keyH);
		String profString = jPAIconfig.propiedades
				.getProperty(AimaUtil.keyProf);
	
		if (algoString != null)
			algoritmo = Integer.parseInt(algoString);
		if (heurString != null)
			heuristica = Integer.parseInt(heurString);
		if (profString != null)
			prof = Integer.parseInt(profString);
	
		cuboku = new Cuboku(pathString);
	
		// Elijo Heuristica
		switch (heuristica) {
		case 0:
			h = new H1HeuristicFunction();
			break;
		case 1:
			h = new H2HeuristicFunction();
			break;
		case 2:
			h = new H3HeuristicFunction();
			break;
		}
		// Cargao ejecucion de algoritmo en un thread aparte
		Thread a = new Thread(ejecucionAlgoritmo, "ejecucionAlgoritmo");
		a.start();
	}

	private Runnable ejecucionAlgoritmo = new Runnable() {
		@Override
		public void run() {
			switch (alg = (Algoritmo.getEnum(algoritmo))) {
			case PROFUNDIDAD:
				searchAgent = AimaUtil.DFSDemo(cuboku);
				break;
			case ANCHURA:
				searchAgent = AimaUtil.BFSDemo(cuboku);
				break;
			case PROFUNDIDADLIM:
				searchAgent = AimaUtil.DLSDemo(cuboku, prof);
				break;
			case UNIFORME:
				searchAgent = AimaUtil.UCDemo(cuboku);
				break;
			case VORAZ:
				searchAgent = AimaUtil.GBFSDemo(cuboku, h);
				break;
			case A:
				searchAgent = AimaUtil.AStarDemo(cuboku, h);
				break;
			case ESCALADAMAXPEND:
				searchAgent = AimaUtil.EMPDemo(cuboku, h);
				break;
			}
			jPAIdemo.setPropiedades(searchAgent, alg, h, cuboku);
			jPAIdemo.inicializaOuts();
			jPAIdemo.setEnabledBotones(true);
		}
	};

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setJMenuBar(getJMBJ());
		this.setTitle("Cuboku Prueba Algoritmos");
		this.setResizable(false);
		this.pack();
	}

	/**
	 * This method initializes jPBase
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jPBase == null) {
			layout = new CardLayout();
			jPBase = new JPanel(layout);
			jPBase.add(getJPAIconfig(), AICONFIG);
			jPBase.add(getJPAIdemo(), AIDEMO);
		}
		return jPBase;
	}

	/**
	 * This method initializes jPAIconfig
	 * 
	 * @return JPanelAIconfig
	 */
	private JPanelAIconfig getJPAIconfig() {
		if (jPAIconfig == null) {
			jPAIconfig = new JPanelAIconfig();
		}
		return jPAIconfig;
	}

	/**
	 * This method initializes getJPAIdemo
	 * 
	 * @return JPanelAIdemo
	 */
	private JPanelAIdemo getJPAIdemo() {
		if (jPAIdemo == null) {
			jPAIdemo = new JPanelAIdemo();
		}
		return jPAIdemo;
	}

	/**
	 * This method initializes jTFrutaArchivo
	 * 
	 * @return javax.swing.JTextField
	 */
	private JMenuBarJ getJMBJ() {
		if (jMBJ == null) {
			jMBJ = new JMenuBarJ(this);
		}
		return jMBJ;
	}
}
