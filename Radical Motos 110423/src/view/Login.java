package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import util.Validador;
import java.awt.Cursor;

public class Login extends JFrame {
	// instanciar objetos (JDBC)
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	private JTextField txtLogin;
	private JLabel lblNewLabel_1;
	private JPasswordField txtSenha;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo128.png")));
		setTitle("Radical Motos - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// a linha abaixo centraliza o JFrame / Jdialog
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLogin = new JButton("Acessar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnLogin.setBounds(230, 136, 89, 23);
		contentPane.add(btnLogin);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(10, 129, 48, 48);
		contentPane.add(lblStatus);

		txtLogin = new JTextField();
		txtLogin.setBounds(52, 26, 144, 17);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		txtLogin.setColumns(10);
		// uso do Validador para limitar máximo de caractéres
		txtLogin.setDocument(new Validador(20));

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(10, 28, 46, 14);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(10, 56, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(52, 54, 144, 17);
		contentPane.add(txtSenha);
		txtSenha.setDocument(new Validador(250));
		
        //vincular a tecla [enter] ao botão acessar
		getRootPane().setDefaultButton(btnLogin);
		
		btnNewButton = new JButton("");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/img/carro.png")));
		btnNewButton.setBounds(123, 34, 239, 143);
		contentPane.add(btnNewButton);
	}// fim do construtor

	/**
	 * Método para verificar o status de conexão
	 */
	private void status() {
		// System.out.println("teste janela ativada");
		try {
			// abrir a conexão com o banco
			con = dao.conectar();
			if (con == null) {
				// mudar o ícone da jlabel
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
			} else {
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dbon.png")));
			}
			// fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do método status

	/**
	 * Método para autenticar um usuário
	 */

	private void logar() {
		
		//variável usada para captura segura de senhas
		String capturaSenha = new String(txtSenha.getPassword());
		
		// System.out.println("teste do botão logar");

		// validação de campos obrigatórios
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0 ) {
			JOptionPane.showMessageDialog(null, "Digite a senha");
			txtSenha.requestFocus();
		} else {
			// lógica principal
			// tratamento de exceções
			String read = "select * from usuarios where login=? and senha=md5(?)";
			try {
				// abrir conexão
				con = dao.conectar();
				// preparar a exucução da query( CRUD Read)
				pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				// executar a query e obter o resultado (consulta do banco)

				rs = pst.executeQuery();
				//se existir login e senha correspondente
				if (rs.next()) {
					
					//capturar perfil do usuário
					//System.out.println(rs.getString(5)); apoio a lógica
					String perfil = rs. getString(5);
					//tratamento do perfil
					if (perfil.equals("admin")){
						//System.out.println("admin logado");
						
						// ativar a janela principal
						Principal principal = new Principal();
						//liberar botões
						principal.btnRelatorio.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						principal.setVisible(true);
						//ALTERAR COR RODAPÉ
						principal.panelRodape.setBackground(Color.BLUE);
						// a linha abaixo muda o texto pra jlabel lblStatus da tela principal
						//pelo nome do usuário (campo 2 da tabela)
						principal.lblUsuario.setText(rs.getString(2));
						
						// fechar esta janela
						this.dispose();
					}else {
						//System.out.println("user logado");
						
						// ativar a janela principal
						Principal principal = new Principal();
						principal.setVisible(true);
						// a linha abaixo muda o texto pra jlabel lblStatus da tela principal
						//pelo nome do usuário (campo 2 da tabela)
						principal.lblUsuario.setText(rs.getString(2));
						
						// fechar esta janela
						this.dispose();
					}
				
				} else {
					JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos(s)");
				}
				// fechar conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}// fim do código
