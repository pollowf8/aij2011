//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.visual;

import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import oai.practica1.cuboku.Cuboku;

/**
 * Clase visual que representa el dialogo para mostra rel cubo aplanado
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.0 13.01.2012
 */
public class JDialogCubo extends JDialog {

	private static final int _SPACE = 10;
	private static final int _SIZE = 65;
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jBclose = null;
	private Cuboku cuboku;

	private JTextArea jTAcara1;
	private JTextArea jTAcara2;
	private JTextArea jTAcara3;
	private JTextArea jTAcara4;
	private JTextArea jTAcara5;
	private JTextArea jTAcara6;

	/**
	 * @param owner
	 */
	public JDialogCubo(Frame owner, Cuboku c) {
		super(owner);
		this.cuboku = c;
		initialize();
	}

	public void setCuboku(Cuboku c) {
		this.cuboku = c;
		jTAcara1.setText("Cara 1\n"+cuboku.getCube()[0].toStringSimple());
		jTAcara2.setText("Cara 2\n"+cuboku.getCube()[1].toStringSimple());
		jTAcara3.setText("Cara 3\n"+cuboku.getCube()[2].toStringSimple());
		jTAcara4.setText("Cara 4\n"+cuboku.getCube()[3].toStringSimple());
		jTAcara5.setText("Cara 5\n"+cuboku.getCube()[4].toStringSimple());
		jTAcara6.setText("Cara 6\n"+cuboku.getCube()[5].toStringSimple());
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setTitle("Cuboku Aplanado");
		this.setSize(310, 250);//
		this.setContentPane(getJContentPane());
		getJBclose().requestFocus();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJBclose(), null);
			jContentPane.add(getJTAcara1());
			jContentPane.add(getJTAcara2());
			jContentPane.add(getJTAcara3());
			jContentPane.add(getJTAcara4());
			jContentPane.add(getJTAcara5());
			jContentPane.add(getJTAcara6());
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTAcara1
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTAcara1() {
		if (jTAcara1 == null) {
			jTAcara1 = new JTextArea(cuboku.getCube()[0].toString());
			jTAcara1.setEditable(false);
			jTAcara1.setBounds(new Rectangle(_SPACE + _SIZE, _SPACE + _SIZE, _SIZE, _SIZE));
		}
		return jTAcara1;
	}

	/**
	 * This method initializes jTAcara2
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTAcara2() {
		if (jTAcara2 == null) {
			jTAcara2 = new JTextArea(cuboku.getCube()[1].toString());
			jTAcara2.setEditable(false);
			jTAcara2.setBounds(new Rectangle(_SPACE + _SIZE + _SIZE, _SPACE + _SIZE, _SIZE, _SIZE));
		}
		return jTAcara2;
	}

	/**
	 * This method initializes jTAcara3
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTAcara3() {
		if (jTAcara3 == null) {
			jTAcara3 = new JTextArea(cuboku.getCube()[2].toString());
			jTAcara3.setEditable(false);
			jTAcara3.setBounds(new Rectangle(_SPACE + _SIZE + _SIZE + _SIZE, _SPACE + _SIZE, _SIZE, _SIZE));
		}
		return jTAcara3;
	}

	/**
	 * This method initializes jTAcara4
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTAcara4() {
		if (jTAcara4 == null) {
			jTAcara4 = new JTextArea(cuboku.getCube()[3].toString());
			jTAcara4.setEditable(false);
			jTAcara4.setBounds(new Rectangle(_SPACE, _SPACE + _SIZE, _SIZE, _SIZE));
		}
		return jTAcara4;
	}

	/**
	 * This method initializes jTAcara5
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTAcara5() {
		if (jTAcara5 == null) {
			jTAcara5 = new JTextArea(cuboku.getCube()[4].toString());
			jTAcara5.setEditable(false);
			jTAcara5.setBounds(new Rectangle(_SPACE + _SIZE, _SPACE, _SIZE, _SIZE));
		}
		return jTAcara5;
	}

	/**
	 * This method initializes jTAcara6
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTAcara6() {
		if (jTAcara6 == null) {
			jTAcara6 = new JTextArea(cuboku.getCube()[5].toString());
			jTAcara6.setEditable(false);
			jTAcara6.setBounds(new Rectangle(_SPACE + _SIZE, _SPACE + _SIZE + _SIZE, _SIZE, _SIZE));
		}
		return jTAcara6;
	}

	/**
	 * This method initializes jBclose
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJBclose() {
		if (jBclose == null) {
			jBclose = new JButton();
			jBclose.setBounds(new Rectangle(190, 170, 75, 20));
			jBclose.setText("Cerrar");
			jBclose.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
			jBclose.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						dispose();
				}
			});
		}
		return jBclose;
	}

}
