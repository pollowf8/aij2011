//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 2
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica2.recomendador.visual;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Panel para la vision de ejecucion
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.4 06/04/2012
 */
public class JPanelAIdemo extends JPanel {

	private static final long serialVersionUID = 1L;

	// props
	private VentanaAI owner = null;
	// Componentes
	private JLabel jLinfo = null;
	private JTextArea jTAout = null;
	private JScrollPane jSPout = null;

	/**
	 * This is the default constructor
	 * 
	 */
	public JPanelAIdemo(JFrame owner) {
		super();
		this.owner = (VentanaAI) owner;
		initialize();
	}

	/**
	 * Inicializa los componentes para salida por pantalla
	 */
	void inicializaOuts() {
		String result = owner.r.getResult();
		if (result == "")
			jTAout.setText("No se obtuvieron recomendaciones");
		else
			jTAout.setText(result);
	}

	/**
	 * Resetea textos
	 */
	public void reseteaOuts() {
		jTAout.setText("PROCESANDO...");
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

		jLinfo = new JLabel();
		jLinfo.setBounds(new Rectangle(10, 10, 70, 20));
		jLinfo.setText("Resultados:");
		this.add(jLinfo, null);
		this.add(getJSPout(), null);
	}

	/**
	 * This method initializes jTAout
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTAout() {
		if (jTAout == null) {
			jTAout = new JTextArea("PROCESANDO...");
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
			jSPout.setBounds(new Rectangle(10, 40, 430, 300));
			jSPout.setViewportView(getJTAout());
		}
		return jSPout;
	}
}
