//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica1.cuboku.visual;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * MenuBar para las cambiar entre los distintos paneles
 * 
 * @author Jose Angel Garcia Fernandez
 * @date 1.2 06/12/2011
 */
public class JMenuBarJ extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private JFrame jOwner = null;
	private JMenu jMenu = null;
	private JMenuItem jMICambiarAlgoritmo = null;
	private JMenuItem jMIProbar = null;
	private JMenuItem jMISalir = null;

	/**
	 * This method initializes jJMB
	 * 
	 * @return javax.swing.JMenuBar
	 */
	public JMenuBarJ(JFrame frame) {
		jOwner = frame;
		add(getJMenu());
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setMnemonic(KeyEvent.VK_O);
			jMenu.setText("Opciones");
			jMenu.add(getJMCambiarOpciones());
			jMenu.add(getJMProbar());
			jMenu.add(getJMISalir());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMICambiarAlgoritmo
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMCambiarOpciones() {
		if (jMICambiarAlgoritmo == null) {
			jMICambiarAlgoritmo = new JMenuItem();
			jMICambiarAlgoritmo.setText("Cambiar Propiedades");
			jMICambiarAlgoritmo.setAccelerator(KeyStroke.getKeyStroke(
					KeyEvent.VK_C, ActionEvent.ALT_MASK));
			jMICambiarAlgoritmo
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							VentanaAI v = (VentanaAI) jOwner;
							v.estableceLayout(VentanaAI.AICONFIG);
						}
					});
		}
		return jMICambiarAlgoritmo;
	}

	/**
	 * This method initializes jMIProbar
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMProbar() {
		if (jMIProbar == null) {
			jMIProbar = new JMenuItem();
			jMIProbar.setText("Probar ejecucion");
			jMIProbar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
					ActionEvent.ALT_MASK));
			jMIProbar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					VentanaAI v = (VentanaAI) jOwner;
					v.estableceLayout(VentanaAI.AIDEMO);
					v.preparaProbar();
				}
			});
		}
		return jMIProbar;
	}

	/**
	 * This method initializes jMICerrar
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMISalir() {
		if (jMISalir == null) {
			jMISalir = new JMenuItem();
			jMISalir.setText("Salir");
			jMISalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					ActionEvent.ALT_MASK));
			jMISalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return jMISalir;
	}

}
