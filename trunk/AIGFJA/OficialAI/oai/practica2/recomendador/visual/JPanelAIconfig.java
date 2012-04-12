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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jess.JessException;

/**
 * Panel para la eleccion de parametros para la ejecucion
 * 
 * @author Jose Angel Garcia Fernandez
 * @version 1.2 06/04/2012
 */
public class JPanelAIconfig extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	// props
	private String[] sino = { "SI", "NO" };
	private String[] tipo = { "tecnico", "letras" };
	private String[] estudios = { "bachCiencias", "bachLetras", "ESO", "UNI" };
	private String[] empresa = { "peque", "media", "grande" };
	private String[] puesto = { "miembro", "directivo", "becario" };

	private VentanaAI owner = null;
	// Componentes
	private JLabel jLnombre = null;
	private JLabel jLapellidos = null;
	private JLabel jLedad = null;
	private JLabel jLempresa = null;
	private JLabel jLpuesto = null;
	private JLabel jLduracion = null;
	private JLabel jLinvestigador = null;
	private JLabel jLacabada = null;
	private JLabel jLtipo = null;
	private JLabel jLeligeEstudios = null;
	private JLabel jLdocente = null;
	private JComboBox jCBestudios = null;
	private JComboBox jCBacabada = null;
	private JComboBox jCBtipo = null;
	private JComboBox jCBdocente = null;
	private JComboBox jCBinvestigador = null;
	private JComboBox jCBempresa = null;
	private JComboBox jCBpuesto = null;
	private JTextField jTFnombre = null;
	private JTextField jTFapellidos = null;
	private JTextField jTFedad = null;
	private JTextField jTFduracion = null;

	/**
	 * This is the default constructor
	 */
	public JPanelAIconfig(JFrame owner) {
		super();
		this.owner = (VentanaAI) owner;
		initialize();
		setStart();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		int selec;
		try {
			if (source == jCBestudios) {
				selec = jCBestudios.getSelectedIndex();
				owner.r.setEstudios(estudios[selec]);
				if (selec == 3) {
					setUNI(true);
				} else {
					setStart();
				}
			} else if (source == jCBacabada) {
				selec = jCBacabada.getSelectedIndex();
				owner.r.setAcabada(sino[selec]);
				if (selec == 1)
					setAcabada(false, 1);
				else
					setAcabada(true, 0);
			} else if (source == jCBtipo) {
				selec = jCBtipo.getSelectedIndex();
				owner.r.setTipo(tipo[selec]);
			} else if (source == jCBdocente) {
				selec = jCBdocente.getSelectedIndex();
				owner.r.setDocente(sino[selec]);
			} else if (source == jCBinvestigador) {
				selec = jCBinvestigador.getSelectedIndex();
				owner.r.setInvestigador(sino[selec]);
			} else if (source == jCBempresa) {
				selec = jCBempresa.getSelectedIndex();
				owner.r.setEmpresa(empresa[selec]);
			} else if (source == jCBpuesto) {
				selec = jCBpuesto.getSelectedIndex();
				owner.r.setPuesto(puesto[selec]);
			}
		} catch (JessException j) {
			j.printStackTrace();
		}
	}

	/**
	 * Pone a inicio
	 */
	void setStart() {
		jCBestudios.setSelectedIndex(0);
		setUNI(false);
		setAcabada(false, 1);
	}

	/**
	 * Para ejecutarse si es necesario rellenar alguna propiedad sin definir
	 */
	void completaCurriculum() {
		try {
			if (jTFduracion.getText().compareTo("") != 0)
				owner.r.setDuracion(Integer.parseInt(jTFduracion.getText()));
			if (jTFedad.getText().compareTo("") != 0)
				owner.r.setEdad(Integer.parseInt(jTFedad.getText()));
			if (jTFnombre.getText().compareTo("") != 0)
				owner.r.setNombre(jTFnombre.getText());
			if (jTFapellidos.getText().compareTo("") != 0)
				owner.r.setApellidos(jTFapellidos.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Activa o desactiva la parte de titulado
	 * 
	 * @param b
	 *            si o no
	 */
	private void setUNI(boolean b) {
		jLacabada.setVisible(b);
		jCBacabada.setVisible(b);
		jLtipo.setVisible(b);
		jCBtipo.setVisible(b);
	}

	/**
	 * Activa o desactiva la parte de acabada
	 * 
	 * @param b
	 *            si o no
	 */
	private void setAcabada(boolean b, int i) {
		jCBacabada.setSelectedIndex(i);
		jCBempresa.setSelectedIndex(i);
		jCBpuesto.setSelectedIndex(i);
		jCBdocente.setSelectedIndex(i);
		jCBinvestigador.setSelectedIndex(i);

		jLempresa.setVisible(b);
		jLpuesto.setVisible(b);
		jLduracion.setVisible(b);
		jLinvestigador.setVisible(b);
		jLdocente.setVisible(b);

		jCBempresa.setVisible(b);
		jCBpuesto.setVisible(b);
		jTFduracion.setVisible(b);
		jCBinvestigador.setVisible(b);
		jCBdocente.setVisible(b);
	}

	/**
	 * Realizes this
	 * 
	 * @return void
	 */
	private void initialize() {

		this.setSize(250, 300);
		this.setPreferredSize(new Dimension(250, 300));
		this.setLayout(null);

		jLpuesto = new JLabel();
		jLpuesto.setBounds(new Rectangle(10, 220, 88, 15));
		jLpuesto.setText("Puesto");
		jLduracion = new JLabel();
		jLduracion.setBounds(new Rectangle(115, 170, 87, 15));
		jLduracion.setText("Duracion");
		jLempresa = new JLabel();
		jLempresa.setBounds(new Rectangle(10, 170, 90, 15));
		jLempresa.setText("Tipo Empresa");
		jLedad = new JLabel();
		jLedad.setBounds(new Rectangle(183, 10, 50, 15));
		jLedad.setText("Edad:");
		jLapellidos = new JLabel();
		jLapellidos.setBounds(new Rectangle(86, 10, 90, 15));
		jLapellidos.setText("Apellidos:");
		jLnombre = new JLabel();
		jLnombre.setBounds(new Rectangle(10, 10, 70, 15));
		jLnombre.setText("Nombre:");
		jLinvestigador = new JLabel();
		jLinvestigador.setBounds(new Rectangle(115, 115, 90, 15));
		jLinvestigador.setText("Investigador:");
		jLdocente = new JLabel();
		jLdocente.setBounds(new Rectangle(10, 115, 90, 15));
		jLdocente.setText("Docente:");
		jLeligeEstudios = new JLabel();
		jLeligeEstudios.setText("Elige Estudios:");
		jLeligeEstudios.setBounds(new Rectangle(10, 60, 90, 15));
		jLacabada = new JLabel();
		jLacabada.setText("¿Acabada?");
		jLacabada.setBounds(new Rectangle(107, 60, 70, 15));
		jLtipo = new JLabel();
		jLtipo.setBounds(new Rectangle(180, 60, 55, 15));
		jLtipo.setText("Tipo");

		this.add(jLinvestigador, null);
		this.add(jLacabada, null);
		this.add(jLeligeEstudios, null);
		this.add(jLdocente, null);
		this.add(jLnombre, null);
		this.add(jLapellidos, null);
		this.add(jLedad, null);
		this.add(jLempresa, null);
		this.add(jLduracion, null);
		this.add(jLpuesto, null);
		this.add(jLtipo, null);
		this.add(getJCBtipo(), null);
		this.add(getJCBestudios(), null);
		this.add(getJCBacabada(), null);
		this.add(getJCBdocente(), null);
		this.add(getJCBinvestigador(), null);
		this.add(getJTFnombre(), null);
		this.add(getJTFapellidos(), null);
		this.add(getJTFedad(), null);
		this.add(getJCBtipoEmp(), null);
		this.add(getJTFduracion(), null);
		this.add(getJCBpuesto(), null);
	}

	/**
	 * This method initializes jCBestudios
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBestudios() {
		if (jCBestudios == null) {
			jCBestudios = new JComboBox(estudios);
			jCBestudios.setBounds(new Rectangle(9, 83, 90, 20));
			jCBestudios.setSelectedIndex(0);
			jCBestudios.addActionListener(this);
		}
		return jCBestudios;
	}

	/**
	 * This method initializes jCBacabada
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBacabada() {
		if (jCBacabada == null) {
			jCBacabada = new JComboBox(sino);
			jCBacabada.setBounds(new Rectangle(107, 83, 55, 20));
			jCBacabada.setSelectedIndex(1);
			jCBacabada.addActionListener(this);
		}
		return jCBacabada;
	}

	/**
	 * This method initializes jCBtipo
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBtipo() {
		if (jCBtipo == null) {
			jCBtipo = new JComboBox(tipo);
			jCBtipo.setBounds(new Rectangle(175, 83, 70, 20));
			jCBtipo.setSelectedIndex(0);
			jCBtipo.addActionListener(this);
		}
		return jCBtipo;
	}

	/**
	 * This method initializes jCBdocente
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBdocente() {
		if (jCBdocente == null) {
			jCBdocente = new JComboBox(sino);
			jCBdocente.setBounds(new Rectangle(10, 140, 90, 20));
			jCBdocente.setSelectedIndex(1);
			jCBdocente.addActionListener(this);
		}
		return jCBdocente;
	}

	/**
	 * This method initializes jCBinvestigador
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBinvestigador() {
		if (jCBinvestigador == null) {
			jCBinvestigador = new JComboBox(sino);
			jCBinvestigador.setBounds(new Rectangle(114, 140, 90, 20));
			jCBinvestigador.setSelectedIndex(1);
			jCBinvestigador.addActionListener(this);
		}
		return jCBinvestigador;
	}

	/**
	 * This method initializes jCBtipoEmp
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBtipoEmp() {
		if (jCBempresa == null) {
			jCBempresa = new JComboBox(empresa);
			jCBempresa.setBounds(new Rectangle(10, 190, 90, 20));
			jCBempresa.setSelectedIndex(0);
			jCBempresa.addActionListener(this);
		}
		return jCBempresa;
	}

	/**
	 * This method initializes jCBpuesto
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJCBpuesto() {
		if (jCBpuesto == null) {
			jCBpuesto = new JComboBox(puesto);
			jCBpuesto.setBounds(new Rectangle(10, 240, 90, 20));
			jCBpuesto.setSelectedIndex(0);
			jCBpuesto.addActionListener(this);
		}
		return jCBpuesto;
	}

	/**
	 * This method initializes jTFnombre
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTFnombre() {
		if (jTFnombre == null) {
			jTFnombre = new JTextField("defaultNom");
			jTFnombre.setBounds(new Rectangle(10, 30, 70, 20));
		}
		return jTFnombre;
	}

	/**
	 * This method initializes jTFapellidos
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTFapellidos() {
		if (jTFapellidos == null) {
			jTFapellidos = new JTextField("defaultApe");
			jTFapellidos.setBounds(new Rectangle(86, 30, 90, 20));
		}
		return jTFapellidos;
	}

	/**
	 * This method initializes jTFedad
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTFedad() {
		if (jTFedad == null) {
			jTFedad = new JTextField("17");
			jTFedad.setBounds(new Rectangle(183, 30, 50, 20));
		}
		return jTFedad;
	}

	/**
	 * This method initializes jTFduracion
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTFduracion() {
		if (jTFduracion == null) {
			jTFduracion = new JTextField("6");
			jTFduracion.setBounds(new Rectangle(115, 190, 90, 20));
		}
		return jTFduracion;
	}

} // @jve:decl-index=0:visual-constraint="13,-10"
