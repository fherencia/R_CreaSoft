package com.placamas.seguridad;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.placamas.beans.LocalBean;
import com.placamas.beans.MarcasBean;
import com.placamas.beans.UsuarioBean;
import com.placamas.beans.UsuarioRight;
import com.placamas.componentes.JComboBoxBD;
import com.placamas.controlador.LocalesControlador;
import com.placamas.controlador.MarcasControlador;
import com.placamas.controlador.UsuarioControlador;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JCheckBox;

public class FrmControlUsuarios extends JInternalFrame implements ActionListener, MouseListener {
	
	UsuarioControlador obj=new UsuarioControlador();
	public JTable table;
	public JTable tableul;
	public JTable tablelocal;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnListar;
	public JPanel control;
	DefaultTableModel modelo=new DefaultTableModel();
	private JToolBar toolBar;
	ResourceBundle rb = ResourceBundle.getBundle("database_sql");
	private JComboBox cboUsuario_1;

	//private ControladorCategoria c = new ControladorCategoria();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmControlUsuarios frame = new FrmControlUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmControlUsuarios() {
		
		control = new JPanel();
		/*
		setBounds(100, 100, 1008, 673);
		control.setLayout(null);
		setClosable(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setIconifiable(true);
		setTitle("Control de Usuarios");*/
		control.setLayout(null);
		
		//cboUsuario_1 = new JComboBox();
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmControlUsuarios.class.getResource("/gui/img/banners/pruebaBanner2.jpg")));
		label.setBounds(20, 32, 1082, 157);
		control.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 245, 300, 350);
		control.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBounds(328, 292, 318, 138);
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo Usuario", "Nombre de Usuario"
			}
		));
		
		toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 358, 23);
		control.add(toolBar);
		
		JButton btnConsultar = new JButton("Consultar");
		toolBar.add(btnConsultar);
		
		btnListar = new JButton("Listar");
		toolBar.add(btnListar);
		btnListar.addActionListener(this);
		
		btnAgregar = new JButton("Agregar");
		toolBar.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		toolBar.add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		toolBar.add(btnActualizar);
		
		JButton btnVerOpciones = new JButton("Ver Opciones");
		btnVerOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmOpciones frm = new FrmOpciones();
				frm.setVisible(true);
			}
		});
		toolBar.add(btnVerOpciones);
		
		JLabel lblSeleccioneUnUsuario = new JLabel("Seleccione un Usuario:");
		lblSeleccioneUnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccioneUnUsuario.setBounds(13, 945, 137, 17);
		control.add(lblSeleccioneUnUsuario);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(155, 952, 0, 2);
		control.add(separator);
		
		
		modelo.addColumn("Cod_Usuario");
		modelo.addColumn("Local");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(631, 244, 300, 150);
		control.add(scrollPane_1);
		
		tableul = new JTable();
		scrollPane_1.setViewportView(tableul);
		tableul.addKeyListener(new KeyAdapter() {
			@Override
			//DISE�O CLIC DERECHO EN EL SCROL / EVENT /KEY/ KEYRELEASED
			public void keyReleased(KeyEvent arg0) {
				//Mostrar();	
			}
		});
		tableul.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//Mostrar();
			}
		});
		tableul.setModel(modelo);
		
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(631, 445, 300, 150);
		control.add(scrollPane_2);
		
		tablelocal = new JTable();
		tablelocal.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo Local", "Nombre de Local"
			}
		));
		scrollPane_2.setViewportView(tablelocal);
		
		JLabel lblLocalXUsuarios = new JLabel("Usuarios x Local");
		lblLocalXUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocalXUsuarios.setBounds(631, 219, 125, 14);
		control.add(lblLocalXUsuarios);
		
		JLabel lblLocales = new JLabel("Locales");
		lblLocales.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocales.setBounds(631, 420, 97, 14);
		control.add(lblLocales);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(68, 306, 48, 19);
		control.add(lblUsuario);
		cboUsuario_1 = new JComboBoxBD(rb.getString("SQL_COMBO_USUARIO"));
		cboUsuario_1.setBounds(131, 307, 171, 20);
		control.add(cboUsuario_1);
		
		JLabel label_1 = new JLabel("Lista de Privilegios");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(383, 215, 149, 23);
		control.add(label_1);
		
		JCheckBox checkBox_1 = new JCheckBox("Anular LP");
		checkBox_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_1.setBounds(383, 260, 228, 23);
		control.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Desbloquear LP");
		checkBox_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_2.setBounds(383, 280, 228, 23);
		control.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("Aprobar/Desaprobar Produccion");
		checkBox_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_3.setBounds(383, 301, 228, 23);
		control.add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("Incluir Lista de Digitadores");
		checkBox_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_4.setBounds(383, 321, 228, 23);
		control.add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("Incluir Lista de Optimizadores");
		checkBox_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_5.setBounds(383, 341, 228, 23);
		control.add(checkBox_5);
		
		JCheckBox checkBox_6 = new JCheckBox("Editar Digitador");
		checkBox_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_6.setBounds(383, 362, 228, 23);
		control.add(checkBox_6);
		
		JCheckBox checkBox_7 = new JCheckBox("Editar Optimizador");
		checkBox_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_7.setBounds(383, 382, 228, 23);
		control.add(checkBox_7);
		
		JCheckBox checkBox_8 = new JCheckBox("Editar Vendedor");
		checkBox_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_8.setBounds(383, 402, 228, 23);
		control.add(checkBox_8);
		
		JCheckBox checkBox_9 = new JCheckBox("Editar Tiempo de Entrega");
		checkBox_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_9.setBounds(383, 422, 228, 23);
		control.add(checkBox_9);
		
		JCheckBox checkBox_10 = new JCheckBox("Editar Documentos de Venta");
		checkBox_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_10.setBounds(383, 442, 228, 23);
		control.add(checkBox_10);
		
		JCheckBox checkBox_11 = new JCheckBox("Exportar hacia Servidor FTP");
		checkBox_11.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_11.setBounds(383, 462, 228, 23);
		control.add(checkBox_11);
		
		JCheckBox checkBox_12 = new JCheckBox("Importar desde Servidor FTP");
		checkBox_12.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_12.setBounds(383, 482, 228, 23);
		control.add(checkBox_12);
		
		JCheckBox checkBox_13 = new JCheckBox("Administrar Usuarios");
		checkBox_13.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_13.setBounds(383, 502, 228, 23);
		control.add(checkBox_13);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(383, 245, 209, 2);
		control.add(separator_1);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsuarios.setBounds(51, 210, 121, 23);
		control.add(lblUsuarios);
		
		JButton btnA = new JButton("");
		btnA.setContentAreaFilled(false);
		btnA.setIcon(new ImageIcon(FrmControlUsuarios.class.getResource("/iconos/Up.gif")));
		btnA.setBounds(754, 404, 33, 30);
		control.add(btnA);
		
		JButton btnB = new JButton("");
		btnB.setContentAreaFilled(false);
		btnB.setIcon(new ImageIcon(FrmControlUsuarios.class.getResource("/iconos/Down.gif")));
		btnB.setBounds(800, 405, 33, 28);
		control.add(btnB);
		btnActualizar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnAgregar.addActionListener(this);
		
		listaLocales();
		listarUsuarioLocales();
		listaData();

	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnListar) {
			do_btnListar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			do_btnActualizar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(arg0);
		}
	}
	protected void do_btnAgregar_actionPerformed(ActionEvent arg0) {
		//String des = textField.getText().trim();
		
	/*//	CategoriaBean bean = new CategoriaBean();
		bean.setDescripcion(des);
		
		c.inserta(bean);
		
		listaData();*/
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent arg0) {
		/*if( idCategoria!= -1){
			c.elimina(idCategoria);
			textField.setText("");
			listaData();
		}*/
	}
	protected void do_btnActualizar_actionPerformed(ActionEvent arg0) {
		/*if( idCategoria!= -1){
			String des = textField.getText().trim();
			
			CategoriaBean bean = new CategoriaBean();
			bean.setIdCategoria(idCategoria);
			bean.setDescripcion(des);
			
			c.actualiza(bean);
			
			listaData();
		}*/
	}
	protected void do_btnListar_actionPerformed(ActionEvent arg0) {
		listaData();
		listarUsuarioLocales();
		listaLocales();
		
	}
	
	private void listarUsuarioLocales() {
		
		modelo.setRowCount(0);
		ArrayList<UsuarioRight> info=obj.listarUsuarioLocal();
		for(UsuarioRight x:info){
			Object fila[]={x.getIdUser(),x.getLocal()};
			modelo.addRow(fila);		
		}
	}

	private void listaLocales() {
		
		//la data de la abse de datos
				LocalesControlador lc = new LocalesControlador();
				ArrayList<LocalBean> lista = lc.listarLocal();
				
				//el model de la tabla(GUI)
				DefaultTableModel model = (DefaultTableModel) tablelocal.getModel();
				
				//limpia la tabla
				model.setRowCount(0);
				
				//paso la data de la lista al model
				for (LocalBean bean : lista) {
					model.addRow(new Object[]{bean.getIdLocal(),
												bean.getLoc_Nomb()});			
				}

	}

	public void listaData(){
		
	//la data de la abse de datos
		//UsuarioControlador uc = new UsuarioControlador();
		ArrayList<UsuarioBean> lista = obj.listarUsuario();
		
		//el model de la tabla(GUI)
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		//limpia la tabla
		model.setRowCount(0);
		
		//paso la data de la lista al model
		for (UsuarioBean bean : lista) {
			model.addRow(new Object[]{bean.getIdUser(),
										bean.getUser_Nomb()});			
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
		

		
	}
	
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
}
