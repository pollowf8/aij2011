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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import oai.aima.util.AimaUtil;
import oai.practica1.cuboku.Cuboku;
import oai.practica1.cuboku.util.Algoritmo;
import aima.core.agent.Action;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.SearchAgent;

/**
 * Panel para la vision de ejecucion
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.1 06/12/2011
 */
public class JPanelAIdemo extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Props
	private Cuboku cuboku;
	private int i;
	private String out;
	private String props;
	private Algoritmo alg;
	private HeuristicFunction h;
	private SearchAgent agent;
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
	 */
	void setPropiedades(SearchAgent searchAgent, Algoritmo alg,
			HeuristicFunction h, Cuboku cuboku) {
		this.alg = alg;
		this.h = h;
		this.agent = searchAgent;
		this.cuboku = cuboku;
		i = 0;
	}

	/**
	 * Inicializa los componentes para salida por pantalla
	 */
	void inicializaOuts(boolean h) {
		out = AimaUtil.printActions(agent.getActions(), new Cuboku(cuboku));
		props = AimaUtil.printInstrumentation(agent.getInstrumentation());
		jTAout.setText("Estado Inicial" + AimaUtil.newLine + cuboku);
		if (h)
			jLheuristica.setText("Heuristica: " + h);
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
		jTAout.setText("PROCESANDO ALGORITMO, ESPERE...");
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
				// le kitamos la extension
				pw = new PrintWriter(
						new FileWriter(cuboku.getNombre().substring(0,
								cuboku.getNombre().indexOf("."))
								+ ".log"));
				pw.print(out);
				pw.print(agent.getActions());
				pw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (source == jBresults) {
			// TODO los resultados y tal
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
		jLheuristica.setBounds(new Rectangle(225, 40, 190, 20));
		jLheuristica.setText("Heuristica: ");
		jLbusqueda = new JLabel();
		jLbusqueda.setBounds(new Rectangle(225, 10, 190, 20));
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
			jTAout = new JTextArea("PROCESANDO ALGORITMO, ESPERE...");
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
			jBresults.setText("Generar Estadisticas");
			jBresults.addActionListener(this);
		}
		return jBresults;
	}
}
