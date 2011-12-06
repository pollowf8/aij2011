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
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oai.aima.util.AimaUtil;
import aima.core.search.framework.SearchAgent;

/**
 * Panel para la eleccion de parametros para la ejecucion
 * 
 * @author Jose Angel Garcia Fernandez
 * @date 1.1 06/12/2011
 */
public class JPanelAIconfig extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	// props
	private String[] algoritmos = { "1º en Profundidad", "1º en Anchura",
			"Profundidad limitada", "Coste Uniforme", "Voraz", "A*",
			"Escalada MaxPendiente" };
	private String[] files = { "cuboIsi.txt", "hola.txt" };
	private String[] heuristicas = { "h1", "h2", "h3" };
	private String[] profs = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10" };
	SearchAgent agent;
	Properties propiedades;
	// Componentes
	private JLabel jLeligeFile = null;
	private JLabel jLeligeAlg = null;
	private JLabel jLheuristica = null;
	private JComboBox jCBfile = null;
	private JComboBox jCBalgoritmo = null;
	private JComboBox jCBheuristica = null;
	private JComboBox jCBprof = null;

	/**
	 * This is the default constructor
	 */
	public JPanelAIconfig() {
		super();
		propiedades = new Properties();
		// rellena propiedades con valores por defecto para evitar vacios
		propiedades.put(AimaUtil.keyFile, files[0]);
		propiedades.put(AimaUtil.keyAlgoritmo, "1");
		propiedades.put(AimaUtil.keyH, "0");
		propiedades.put(AimaUtil.keyProf, "6");
		initialize();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		int selec;
		if (source == jCBfile) {
			selec = jCBfile.getSelectedIndex();
			propiedades.put(AimaUtil.keyFile, files[selec]);
		} else if (source == jCBalgoritmo) {
			selec = jCBalgoritmo.getSelectedIndex();
			propiedades.put(AimaUtil.keyAlgoritmo, String.valueOf(selec));
			if (selec == 2) {
				jCBprof.setVisible(true);
				jCBheuristica.setVisible(false);
				jLheuristica.setVisible(false);
			} else if (selec > 3) {
				jCBheuristica.setVisible(true);
				jLheuristica.setVisible(true);
				jCBprof.setVisible(false);
			} else {
				jCBprof.setVisible(false);
				jCBheuristica.setVisible(false);
				jLheuristica.setVisible(false);
			}
		} else if (source == jCBheuristica) {
			selec = jCBheuristica.getSelectedIndex();
			propiedades.put(AimaUtil.keyH, String.valueOf(selec));
		} else if (source == jCBprof) {
			selec = jCBprof.getSelectedIndex();
			propiedades.put(AimaUtil.keyProf, String.valueOf(selec + 1));
		}

	}

	/**
	 * alizes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(360, 90);
		this.setPreferredSize(new Dimension(360, 90));
		this.setLayout(null);

		jLheuristica = new JLabel();
		jLheuristica.setBounds(new Rectangle(260, 10, 90, 15));
		jLheuristica.setText("Elige heuristica:");
		jLheuristica.setVisible(false);
		jLeligeAlg = new JLabel();
		jLeligeAlg.setText("Elige Archivo:");
		jLeligeAlg.setBounds(new Rectangle(10, 10, 90, 15));
		jLeligeFile = new JLabel();
		jLeligeFile.setText("Elige Algoritmo:");
		jLeligeFile.setBounds(new Rectangle(110, 10, 140, 15));

		this.add(jLeligeFile, null);
		this.add(jLeligeAlg, null);
		this.add(jLheuristica, null);
		this.add(getJCBfile(), null);
		this.add(getJCBalgoritmo(), null);
		this.add(getJCBheuristica(), null);
		this.add(getJCBprof(), null);
	}

	/**
	 * This method initializes jCBfile
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBfile() {
		if (jCBfile == null) {
			jCBfile = new JComboBox(files);
			jCBfile.setBounds(new Rectangle(10, 35, 90, 20));
			jCBfile.setSelectedIndex(0);
			jCBfile.addActionListener(this);
		}
		return jCBfile;
	}

	/**
	 * This method initializes jCBalgoritmo
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBalgoritmo() {
		if (jCBalgoritmo == null) {
			jCBalgoritmo = new JComboBox(algoritmos);
			jCBalgoritmo.setBounds(new Rectangle(110, 35, 140, 20));
			jCBalgoritmo.setSelectedIndex(0);
			jCBalgoritmo.addActionListener(this);
		}
		return jCBalgoritmo;
	}

	/**
	 * This method initializes jCBheuristica
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBheuristica() {
		if (jCBheuristica == null) {
			jCBheuristica = new JComboBox(heuristicas);
			jCBheuristica.setBounds(new Rectangle(260, 35, 90, 20));
			jCBheuristica.setSelectedIndex(0);
			jCBheuristica.addActionListener(this);
			jCBheuristica.setVisible(false);
		}
		return jCBheuristica;
	}

	/**
	 * This method initializes jCBprof
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBprof() {
		if (jCBprof == null) {
			jCBprof = new JComboBox(profs);
			jCBprof.setBounds(new Rectangle(110, 60, 90, 20));
			jCBprof.setSelectedIndex(5);
			jCBprof.addActionListener(this);
			jCBprof.setVisible(false);
		}
		return jCBprof;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
