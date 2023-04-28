package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.DAO;

public class Usuarios extends JDialog {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JButton btnCreate;
	private JButton btnBuscar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JComboBox<?> cboPerfil;
	private JCheckBox chkSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setEnabled(false);
		setTitle("Usuários");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 632, 408);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(36, 38, 46, 14);
		getContentPane().add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(81, 35, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(36, 81, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(36, 123, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(36, 166, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtNome = new JTextField();
		txtNome.setBounds(81, 78, 292, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		txtLogin = new JTextField();
		txtLogin.setBounds(81, 120, 162, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		btnCreate = new JButton("");
		btnCreate.setContentAreaFilled(false);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setBorderPainted(false);
		btnCreate.setToolTipText("Adicionar");
		btnCreate.setBorder(null);
		btnCreate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/adicionar.png")));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnCreate.setBounds(81, 254, 48, 48);
		getContentPane().add(btnCreate);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(81, 163, 339, 20);
		getContentPane().add(txtSenha);

		btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarUsuario();
			}
		});
		btnBuscar.setBounds(261, 109, 48, 48);
		getContentPane().add(btnBuscar);

		btnEditar = new JButton("");
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/editar.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ler o status do checkbox, escolhendo o método de acordo com a seleção da
				// caixa.
				if (chkSenha.isSelected()) {
					editarUsuarioSenha();
				} else {
					editarUsuario();
				}
			}
			
		});
		btnEditar.setBounds(139, 254, 48, 48);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/excluir contato.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setBounds(197, 254, 48, 48);
		getContentPane().add(btnExcluir);

		JButton btnLimpar = new JButton("");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorderPainted(false);
		btnLimpar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/limpar (2).png")));
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
			}
		});
		btnLimpar.setBounds(479, 232, 48, 48);
		getContentPane().add(btnLimpar);

		getRootPane().setDefaultButton(btnBuscar);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(444, 166, 46, 14);
		getContentPane().add(lblNewLabel_4);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setBounds(478, 162, 107, 22);
		getContentPane().add(cboPerfil);

		chkSenha = new JCheckBox("Alterar a senha");
		chkSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSenha.setEditable(true);
				txtSenha.setText(null);
				txtSenha.requestFocus();
				txtSenha.setBackground(Color.yellow);
			}
		});
		chkSenha.setVisible(false);
		chkSenha.setBounds(81, 211, 131, 23);
		getContentPane().add(chkSenha);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Usuarios.class.getResource("/img/logo-2.jpg")));
		btnNewButton.setBounds(374, 11, 232, 142);
		getContentPane().add(btnNewButton);

	}// fim do construtor

	/**
	 * Método para adicionar um novo usuário
	 */
	private void adicionarUsuario() {

		// validação do combobox
		// if(cboPerfil.getSelectedItem().equal("))
		// System.out.println

		String capturaSenha = new String(txtSenha.getPassword());

		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Digite a senha");
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite o perfil");
			cboPerfil.requestFocus();
		} else {

			// System.out.println("teste do botão adicionar");
			String create = "insert into usuarios(nome,login,senha,perfil) values(?,?,md5(?),?)";
			try {

				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
				con.close();
				// tratamento de exceção em caso de duplicação de login
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Usuário não adicionado.\nEste login já está sendo utilizado");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}// fim do método novo usuário

	/**
	 * Método para buscar novo usuario
	 */
	private void buscarUsuario() {
		// System.out.println("teste do botão buscar");
		String read = "select * from usuarios where login = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtLogin.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				txtNome.setText(rs.getString(2));
				txtLogin.setText(rs.getString(3));
				txtSenha.setText(rs.getString(4));
				cboPerfil.setSelectedItem(rs.getString(5));
				// mostrar a caixa o checkbox (troca de senha)
				chkSenha.setVisible(true);
				// desabilitar a caixa de senha
				txtSenha.setEditable(false);

				btnCreate.setEnabled(false);

				btnBuscar.setEnabled(false);

				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);

			} else {
				JOptionPane.showMessageDialog(null, "Usuario não cadastrado");

				btnCreate.setEnabled(true);

				btnBuscar.setEnabled(false);

			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}// fim do método buscar usuário

	/**
	 * método para editar os dados do usuário e senha
	 */
	private void editarUsuarioSenha() {
		// System.out.println("teste editar");
		String capturaSenha = new String(txtSenha.getPassword());

		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Digite a senha");
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite o perfil");
			cboPerfil.requestFocus();
		} else {

			String update = "update usuarios set nome=?,login=?,senha=md5(?),perfil=? where iduser=? ";
			try {
				con = dao.conectar();

				pst = con.prepareStatement(update);

				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtID.getText());

				pst.executeUpdate();
				// JOptionPane.showMessageDialog(null, "Dados do usuário editado com sucesso");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}// fim do método editar usuarioSenha

	/**
	 * método para editar os dados do usuário e senha
	 */
	private void editarUsuario() {
		// System.out.println("teste editar");

		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o login");
			txtLogin.requestFocus();
			txtSenha.requestFocus();

		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite o perfil");
			cboPerfil.requestFocus();
		} else {

			String update = "update usuarios set nome=?,login=?,perfil=? where iduser=? ";
			try {
				con = dao.conectar();

				pst = con.prepareStatement(update);

				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtID.getText());

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do usuário editado com sucesso");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}// fim do método editar usuario

	/**
	 * Método para exclusão do usuario
	 */
	private void excluirUsuario() {
		// System.out.println("teste excluir");

		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where iduser=?";
			try {

				con = dao.conectar();

				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();

				Limpar();
				JOptionPane.showMessageDialog(null, "Usuário excluído");

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void Limpar() {
		txtID.setText(null);
		txtNome.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		cboPerfil.setSelectedItem("");

		btnCreate.setEnabled(false);
		btnEditar.setEnabled(false);
		btnBuscar.setEnabled(true);
		btnExcluir.setEnabled(false);
		chkSenha.setVisible(false);
		txtSenha.setBackground(Color.white);

	}
}// fim do código
