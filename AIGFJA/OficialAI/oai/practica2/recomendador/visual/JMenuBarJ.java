//
// Universidad Complutense de Madrid
// Ingeniería Informática
//
// PRACTICA : Practica 1
// ASIGNATURA : Inteligencia Artificial e Ingeniería del Conocimiento
//
package oai.practica2.recomendador.visual;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import oai.excepciones.NoExisteAccionException;

/**
 * MenuBar para las cambiar entre los distintos paneles y opciones
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.4 13/01/2012
 */
public class JMenuBarJ extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private JFrame jOwner = null;
	private JMenu jMenu = null;
	private JMenuItem jMICambiarAlgoritmo = null;
	private JMenuItem jMIProbar = null;
	private JMenuItem jMISalir = null;
	private JMenuItem jMIGenerarCubo = null;

	/**
	 * This method initializes jJMB
	 * 
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
			jMenu.add(getJMGenerarCubo());
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
							v.limpiaProbar();
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
					try {
						v.preparaProbar();
						v.estableceLayout(VentanaAI.AIDEMO);
					} catch (IOException e1) {
						JOptionPane
								.showMessageDialog(
										null,
										"El archivo: "
												+ e1.getMessage().substring(
														0,
														e1.getMessage()
																.indexOf(".")+4)
												+ " no se ha encontrado en el directorio actual y no se ha podido cargar.\nCopie el archivo al directorio actual para poder cargarlo",
										"¡Archivo no encontrado!",
										JOptionPane.ERROR_MESSAGE);
					}

				}
			});
		}
		return jMIProbar;
	}

	/**
	 * This method initializes jMIGenerarCubo
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMGenerarCubo() {
		if (jMIGenerarCubo == null) {
			jMIGenerarCubo = new JMenuItem();
			jMIGenerarCubo.setText("Generar Cubo");
			jMIGenerarCubo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
					ActionEvent.ALT_MASK));
			jMIGenerarCubo
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							VentanaAI v = (VentanaAI) jOwner;
							try {
								v.leeMovs();
							} catch (NoExisteAccionException e1) {
								JOptionPane.showMessageDialog(null,
										"Cubo no generado\nAccion incorrecta: "
												+ e1.getMessage());
							}
						}
					});
		}
		return jMIGenerarCubo;
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
