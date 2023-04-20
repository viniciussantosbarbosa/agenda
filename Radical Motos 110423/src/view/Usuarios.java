package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private JTextField txtPerfil;
	private JButton btnCreate;
	private JButton btnBuscar;
	private JButton btnEditar;
	private JButton btnExcluir;
	

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
		getContentPane().setEnabled(false);
		setTitle("Usuários");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 632, 362);
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
		txtNome.setBounds(81, 78, 339, 20);
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
		btnCreate.setBounds(36, 226, 48, 48);
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
				editarUsuario();
			}
		});
		btnEditar.setBounds(119, 226, 48, 48);
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
		btnExcluir.setBounds(179, 226, 48, 48);
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
		btnLimpar.setBounds(369, 226, 48, 48);
		getContentPane().add(btnLimpar);
		
		getRootPane().setDefaultButton(btnBuscar);
		
		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(444, 166, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtPerfil = new JTextField();
		txtPerfil.setBounds(490, 163, 116, 20);
		getContentPane().add(txtPerfil);
		txtPerfil.setColumns(10);

	}// fim do construtor
	

	/**
	 * Método para adicionar um novo usuário
	 */
	private void adicionarUsuario() {
		
		String capturaSenha = new String(txtSenha.getPassword());
		
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0 ) {
			JOptionPane.showMessageDialog(null, "Digite a senha");
			txtSenha.requestFocus();
		} else if (txtPerfil.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o perfil");
			txtPerfil.requestFocus();
		} else {
		
		// System.out.println("teste do botão adicionar");
		String create = "insert into usuarios(nome,login,senha,perfil) values(?,?,?,?)";		
		try {
			
			con = dao.conectar();
			pst = con.prepareStatement(create);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtLogin.getText());
			pst.setString(3, capturaSenha);
			pst.setString(4, txtPerfil.getText());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		}
	}//fim do método novo usuário
	
	
	/**
	 * Método para buscar novo usuario
	 */
	private void buscarUsuario() {
		//System.out.println("teste do botão buscar");
		String read = "select * from usuarios where login = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtLogin.getText());
			rs = pst.executeQuery();
			if(rs.next()) {
				txtID.setText(rs.getString(1));
				txtNome.setText(rs.getString(2));
				txtLogin.setText(rs.getString(3));
				txtSenha.setText(rs.getString(4));
				txtPerfil.setText(rs.getString(5));
				
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
		
		
	}//fim do método buscar usuário
	
	/**
	 * método para editar os dados do usuário
	 */
	private void editarUsuario() {
		//System.out.println("teste editar");
		String capturaSenha = new String(txtSenha.getPassword());
		
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0 ) {
			JOptionPane.showMessageDialog(null, "Digite a senha");
			txtSenha.requestFocus();
		} else if (txtPerfil.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o perfil");
			txtPerfil.requestFocus();
		} else {
		
		String update = "update usuarios set nome=?,login=?,senha=?,perfil=? where iduser=? ";		
		try {
			con = dao.conectar();
			
			pst = con.prepareStatement(update);
			
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtLogin.getText());
			pst.setString(3, capturaSenha);
			pst.setString(4, txtPerfil.getText()); 	
			pst.setString(5, txtID.getText());
			
			
			
			
			
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dados do usuário editado com sucesso");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		}
		
	}//fim do método editar usuario
	
	/**
	 * Método para exclusão do usuario
	 */
	private void excluirUsuario() {
		//System.out.println("teste excluir");
		
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
		txtPerfil.setText(null);
		
		btnCreate.setEnabled(false);
		btnEditar.setEnabled(false);
		btnBuscar.setEnabled(true);
		btnExcluir.setEnabled(false);
		
	}
}// fim do código
