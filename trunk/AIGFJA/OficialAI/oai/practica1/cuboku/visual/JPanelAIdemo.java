//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.visual;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import oai.aima.util.AimaUtil;
import oai.practica1.cuboku.Cuboku;
import oai.practica1.cuboku.util.Algoritmo;
import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.SearchAgent;

/**
 * Panel para la vision de ejecucion
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.2 25/12/2011
 */
public class JPanelAIdemo extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final String formatEstadisticas = "%-25s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s";

	/**
	 * Para mostrar mientras se calcula el algoritmo
	 */
	private static final String PROCESANDO = "PROCESANDO ALGORITMO\nESPERE...\nSi tarda mucho tiempo:\n-Ejecute otro algoritmo\n-Cambie el cubo";

	private static final long serialVersionUID = 1L;

	// Props
	private Cuboku cuboku;
	private int i;
	private String out;
	private String props;
	private Algoritmo alg;
	private HeuristicFunction heuristica;
	private SearchAgent agent;
	private PrintWriter pwEstadisticas;
	// Componentes
	private JLabel jLbusqueda = null;
	private JLabel jLheuristica = null;
	private JLabel jLcontroles = null;
	private JLabel jLlogs = null;
	private JButton jBlog = null;
	private JButton jBresults = null;
	private JButton jBnext = null;
	private JButton jBterminar = null;
	private JTextArea jTAout = null;
	private JScrollPane jSPout = null;

	private String sesion;

	/**
	 * This is the default constructor
	 * 
	 */
	public JPanelAIdemo() {
		super();
		i = 0;
		initialize();
	}

	/**
	 * Escribe la cabecera del archivo de estadisticas si no se ha escrito ya
	 */
	private void cabecera() {
		try {
			File a = new File(sesion + ".dat");
			if (!a.exists()) {
				pwEstadisticas = new PrintWriter(a);
				pwEstadisticas.format(formatEstadisticas, "Algoritmo", "Coste",
						"Abiertos", "Expandidos", "MaxNodos", "Completo",
						"Optimo", "Tiempo(r=6)", "Espacio(r=6)");
				pwEstadisticas.println();
				pwEstadisticas.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Establece las propiedades cargadas en el panel de configuracion
	 * 
	 * @param searchAgent
	 *            el search agent
	 * @param alg
	 *            el algoritmo a usar
	 * @param h
	 *            la heuristica a usar
	 * @param cuboku
	 *            el cuboku cargado
	 * @param sesion
	 *            el nombre de la sesion
	 */
	void setPropiedades(SearchAgent searchAgent, Algoritmo alg,
			HeuristicFunction h, Cuboku cuboku, String sesion) {
		this.alg = alg;
		this.heuristica = h;
		this.agent = searchAgent;
		this.cuboku = cuboku;
		this.sesion = sesion;
		i = 0;
	}

	/**
	 * Inicializa los componentes para salida por pantalla
	 */
	void inicializaOuts(boolean h) {
		out = AimaUtil.printActions(agent.getActions(), new Cuboku(cuboku));
		props = AimaUtil.printInstrumentation(agent.getInstrumentation());
		DynamicAction a = (DynamicAction) agent.getActions().get(0);
		String infoExtra = "";
		if (a.getName() == AimaUtil.CUT_OFF)
			infoExtra = "SOLUCION NO ENCONTRADA";
		else if (a.getName() == AimaUtil.NO_OP)
			infoExtra = "YA ES SOLUCION";
		jTAout.setText("Estado Inicial" + AimaUtil.newLine + cuboku + infoExtra);
		if (h)
			jLheuristica.setText("Heuristica: " + heuristica);
		else
			jLheuristica.setText("Heuristica: no usada");
		jLbusqueda.setText("Algoritmo: " + alg);
	}

	/**
	 * Establece la activacion o no de los botones
	 * 
	 * @param enabled
	 *            true o false
	 */
	void setEnabledBotones(boolean enabled) {
		jBnext.setEnabled(enabled);
		jBlog.setEnabled(enabled);
		jBresults.setEnabled(enabled);
		jBterminar.setEnabled(enabled);
	}

	/**
	 * Resetea textos
	 */
	public void reseteaOuts() {
		jLheuristica.setText("Heuristica: ");
		jLbusqueda.setText("Algoritmo: ");
		jTAout.setText(PROCESANDO);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == jBnext) {
			Action a = agent.getActions().get(i);
			cuboku.move(a);
			jTAout.setText(a.toString() + AimaUtil.newLine + cuboku);
			i++;
			if (i == agent.getActions().size()) {
				jBnext.setEnabled(false);
				String props = AimaUtil.printInstrumentation(agent
						.getInstrumentation());
				jTAout.setText(jTAout.getText() + "Estado Final Alcanzado"
						+ AimaUtil.newLine + props);
			}
		} else if (source == jBterminar) {
			jTAout.setText(out + AimaUtil.newLine + props);
		} else if (source == jBlog) {
			PrintWriter pw;
			try {
				pw = new PrintWriter(new FileWriter(sesion + ".log"));
				// new FileWriter(cuboku.getNombre().substring(0,
				// cuboku.getNombre().indexOf("."))
				// + ".log"));
				pw.print(out);
				pw.print(agent.getActions());
				pw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (source == jBresults) {
			// TODO los resultados y tal
			try {
				cabecera();
				pwEstadisticas = new PrintWriter(new FileWriter(
						sesion + ".dat", true));

				String coste, abiertos, expandidos, maxNodos, completo = "-", optimo = "-", tiempo = "-", espacio = "-";
				Properties p = agent.getInstrumentation();
				coste = AimaUtil.getPropertie(p, "Cost");
				abiertos = AimaUtil.getPropertie(p, "queueSize");
				expandidos = AimaUtil.getPropertie(p, "Expanded");
				maxNodos = AimaUtil.getPropertie(p, "maxQueue");
				if (abiertos == null)
					abiertos = "-";
				if (maxNodos == null)
					maxNodos = "-";
				// m->max prof arbol
				// P->min prof sol
				switch (alg) {
				case PROFUNDIDAD:
					completo = "N";
					optimo = "N";
					tiempo = "r^m";
					espacio = "r*m";
					break;
				case ANCHURA:
					completo = "S";
					optimo = "S";
					tiempo = "r^P";
					espacio = "r^P";
					break;
				case PROFUNDIDADLIM:
					completo = "S(L>=P)";
					optimo = "N";
					tiempo = "r^L";
					espacio = "r*L";
					break;
				case UNIFORME:
					completo = "S";
					optimo = "S";
					tiempo = "r^P";
					espacio = "r^P";
					break;
				case VORAZ:
					completo = "N";
					optimo = "N";
					tiempo = "r^m";
					espacio = "r^m";
					break;
				case A:
					completo = "S";
					optimo = "S";
					tiempo = "r^P";
					espacio = "r^P";
					break;
				case ESCALADAMAXPEND:
					completo = "N";
					optimo = "N";
					tiempo = "r^m";
					espacio = "1";
					break;
				}
				pwEstadisticas.format(formatEstadisticas, alg.toString(),
						coste, abiertos, expandidos, maxNodos, completo,
						optimo, tiempo, espacio);
				pwEstadisticas.println();
				pwEstadisticas.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		this.setSize(450, 350);
		this.setPreferredSize(new Dimension(450, 350));
		this.setLayout(null);

		jLlogs = new JLabel();
		jLlogs.setBounds(new Rectangle(225, 160, 170, 20));
		jLlogs.setText("Archivos");
		jLcontroles = new JLabel();
		jLcontroles.setBounds(new Rectangle(225, 70, 170, 20));
		jLcontroles.setText("Controles");
		jLheuristica = new JLabel();
		jLheuristica.setBounds(new Rectangle(225, 40, 225, 20));
		jLheuristica.setText("Heuristica: ");
		jLbusqueda = new JLabel();
		jLbusqueda.setBounds(new Rectangle(225, 10, 225, 20));
		jLbusqueda.setText("Algoritmo: ");

		this.add(jLbusqueda, null);
		this.add(jLheuristica, null);
		this.add(jLcontroles, null);
		this.add(jLlogs, null);
		this.add(getJBnext(), null);
		this.add(getJBterminar(), null);
		this.add(getJBlog(), null);
		this.add(getJBresults(), null);
		this.add(getJSPout(), null);

		setEnabledBotones(false);
	}

	/**
	 * This method initializes jTAout
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTAout() {
		if (jTAout == null) {
			jTAout = new JTextArea(PROCESANDO);
			jTAout.setEditable(false);
		}
		return jTAout;
	}

	/**
	 * This method initializes jSPout
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJSPout() {
		if (jSPout == null) {
			jSPout = new JScrollPane();
			jSPout.setBounds(new Rectangle(10, 10, 210, 300));
			jSPout.setViewportView(getJTAout());
		}
		return jSPout;
	}

	/**
	 * This method initializes jBnext
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJBnext() {
		if (jBnext == null) {
			jBnext = new JButton();
			jBnext.setBounds(new Rectangle(225, 100, 170, 20));
			jBnext.setText("Siguiente Movimiento");
			jBnext.addActionListener(this);
		}
		return jBnext;
	}

	/**
	 * This method initializes jBterminar
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJBterminar() {
		if (jBterminar == null) {
			jBterminar = new JButton();
			jBterminar.setBounds(new Rectangle(225, 130, 170, 20));
			jBterminar.setText("Finalizar");
			jBterminar.addActionListener(this);
		}
		return jBterminar;
	}

	/**
	 * This method initializes jBlog
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJBlog() {
		if (jBlog == null) {
			jBlog = new JButton();
			jBlog.setBounds(new Rectangle(225, 190, 170, 20));
			jBlog.setText("Generar Log");
			jBlog.addActionListener(this);
		}
		return jBlog;
	}

	/**
	 * This method initializes jBresults
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJBresults() {
		if (jBresults == null) {
			jBresults = new JButton();
			jBresults.setBounds(new Rectangle(225, 220, 170, 20));
			jBresults.setText("Añadir a estadisticas");
			jBresults.addActionListener(this);
		}
		return jBresults;
	}
}
