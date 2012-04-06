//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 2
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica2.recomendador.visual;

import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import jess.JessException;
import oai.practica2.recomendador.Recomendador;

/**
 * JFrame de la aplicacion
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.5 06/04/2012
 */
public class VentanaAI extends JFrame {

	private static final long serialVersionUID = 1L;

	// props
	Recomendador r = null;
	private Thread a;
	private String file = "recomendadorv2.clp";
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
	 * 
	 * @throws JessException
	 */
	public VentanaAI() throws JessException {
		super();
		r = new Recomendador(file);
		initialize();
	}

	/**
	 * Establece la capa visible
	 */
	void estableceLayout(String layoutActual) {
		layout.show(jPBase, layoutActual);
	}

	/**
	 * Resetea probar
	 */
	void limpiaProbar() {
		jPAIdemo.reseteaOuts();
		r.clear();
	}

	/**
	 * Metodo que prepara el panel de demo usando el de configuracion
	 * 
	 * @throws IOException
	 *             si se ha producido algun error de IO
	 */
	void preparaProbar() {
		limpiaProbar();
		// Cargao ejecucion de algoritmo en un thread aparte
		a = new Thread(ejecucionAlgoritmo, "ejecucionAlgoritmo");
		a.start();
	}

	private Runnable ejecucionAlgoritmo = new Runnable() {
		@Override
		public void run() {
			jPAIconfig.completaCurriculum();
			estableceLayout(VentanaAI.AIDEMO);
			r.ejecucion();
			jPAIdemo.inicializaOuts();
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
		this.setTitle("Recomendador desde JAVA");
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
			jPAIconfig = new JPanelAIconfig(this);
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
			jPAIdemo = new JPanelAIdemo(this);
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
