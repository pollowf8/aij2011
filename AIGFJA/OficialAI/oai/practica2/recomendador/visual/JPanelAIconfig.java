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

import oai.aima.util.AimaUtil;
import oai.practica2.recomendador.Recomendador;

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
	private String[] estudios = { "bachCiencias", "bachLetras", "ESO", "UNI" };
	private String[] empresa = { "pequeña", "media", "grande" };
	private String[] puesto = { "miembro", "directivo", "becario" };

	private VentanaAI owner = null;
	// Componentes
	private JLabel jLnombre = null;
	private JLabel jLapellidos = null;
	private JLabel jLedad = null;
	private JLabel jLEmpresa = null;
	private JLabel jLpuesto = null;
	private JLabel jLduracion = null;
	private JLabel jLeligeInvestigador = null;
	private JLabel jLeligeAcabada = null;
	private JLabel jLeligeEstudios = null;
	private JLabel jLdocente = null;
	private JComboBox jCBestudios = null;
	private JComboBox jCBacabada = null;
	private JComboBox jCBdocente = null;
	private JComboBox jCBinvestigador = null;
	private JComboBox jCBtipoEmp = null;
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
	}

	/**
	 * Para ejecutarse cuando si queda alguna propiedad sin definir
	 */
	public void completaCurriculum() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		int selec;
		try {
			if (source == jCBestudios) {
				selec = jCBestudios.getSelectedIndex();
				owner.r.setEstudios(estudios[selec]);
			} else if (source == jCBacabada) {
				selec = jCBacabada.getSelectedIndex();
				owner.r.setAcabada(sino[selec]);
			} else if (source == jCBdocente) {
				selec = jCBdocente.getSelectedIndex();
				owner.r.setDocente(sino[selec]);
			} else if (source == jCBinvestigador) {
				selec = jCBinvestigador.getSelectedIndex();
				owner.r.setInvestigador(sino[selec]);
			} else if (source == jCBtipoEmp) {
				selec = jCBtipoEmp.getSelectedIndex();
				owner.r.setEmpresa(empresa[selec]);
			} else if (source == jCBpuesto) {
				selec = jCBtipoEmp.getSelectedIndex();
				owner.r.setPuesto(puesto[selec]);
			}
		} catch (JessException j) {
		}
	}

	/**
	 * alizes this
	 * 
	 * @return void
	 */
	private void initialize() {

		this.setSize(250, 270);
		this.setPreferredSize(new Dimension(250, 270));
		this.setLayout(null);

		jLpuesto = new JLabel();
		jLpuesto.setBounds(new Rectangle(10, 220, 88, 15));
		jLpuesto.setText("Puesto");
		jLduracion = new JLabel();
		jLduracion.setBounds(new Rectangle(115, 170, 87, 15));
		jLduracion.setText("Duracion");
		jLEmpresa = new JLabel();
		jLEmpresa.setBounds(new Rectangle(10, 170, 90, 15));
		jLEmpresa.setText("Tipo Empresa");
		jLedad = new JLabel();
		jLedad.setBounds(new Rectangle(183, 10, 50, 15));
		jLedad.setText("Edad:");
		jLapellidos = new JLabel();
		jLapellidos.setBounds(new Rectangle(86, 10, 90, 15));
		jLapellidos.setText("Apellidos:");
		jLnombre = new JLabel();
		jLnombre.setBounds(new Rectangle(10, 10, 70, 15));
		jLnombre.setText("Nombre:");
		jLeligeInvestigador = new JLabel();
		jLeligeInvestigador.setBounds(new Rectangle(115, 115, 90, 15));
		jLeligeInvestigador.setText("Investigador:");
		jLdocente = new JLabel();
		jLdocente.setBounds(new Rectangle(10, 115, 90, 15));
		jLdocente.setText("Docente:");
		jLeligeEstudios = new JLabel();
		jLeligeEstudios.setText("Elige Estudios:");
		jLeligeEstudios.setBounds(new Rectangle(10, 60, 90, 15));
		jLeligeAcabada = new JLabel();
		jLeligeAcabada.setText("Carrera Acabada:");
		jLeligeAcabada.setBounds(new Rectangle(107, 60, 110, 15));

		this.add(jLeligeInvestigador, null);
		this.add(jLeligeAcabada, null);
		this.add(jLeligeEstudios, null);
		this.add(jLdocente, null);
		this.add(jLnombre, null);
		this.add(jLapellidos, null);
		this.add(jLedad, null);
		this.add(jLEmpresa, null);
		this.add(jLduracion, null);
		this.add(jLpuesto, null);
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
			jCBacabada.setBounds(new Rectangle(107, 83, 110, 20));
			jCBacabada.setSelectedIndex(0);
			jCBacabada.addActionListener(this);
		}
		return jCBacabada;
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
			jCBdocente.setSelectedIndex(0);
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
			jCBinvestigador.setSelectedIndex(0);
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
		if (jCBtipoEmp == null) {
			jCBtipoEmp = new JComboBox(empresa);
			jCBtipoEmp.setBounds(new Rectangle(10, 190, 90, 20));
		}
		return jCBtipoEmp;
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
			jTFnombre = new JTextField();
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
			jTFapellidos = new JTextField();
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
			jTFedad = new JTextField();
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
			jTFduracion = new JTextField();
			jTFduracion.setBounds(new Rectangle(115, 190, 90, 20));
		}
		return jTFduracion;
	}

} // @jve:decl-index=0:visual-constraint="13,-10"
