package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.DAO;

public class Clientes extends JDialog {
	
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
	private JTextField txtTelefone;
	private JButton btnCreate;
	private JButton btnBuscar;
	private JButton btnEditar;
	private JButton btnExcluir;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEndereco;
	private JLabel lblNewLabel_4;
	private JTextField txtPlaca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	public Clientes() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setEnabled(false);
		setTitle("Clientes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 632, 408);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID cli");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(52, 38, 46, 14);
		getContentPane().add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(111, 36, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(58, 97, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Endereço:");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(42, 151, 62, 14);
		getContentPane().add(lblNewLabel_3);

		txtNome = new JTextField();
		txtNome.setBounds(111, 95, 292, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(111, 124, 292, 20);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);

		btnCreate = new JButton("");
		btnCreate.setContentAreaFilled(false);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setBorderPainted(false);
		btnCreate.setToolTipText("Adicionar");
		btnCreate.setBorder(null);
		btnCreate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/adicionar.png")));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnCreate.setBounds(81, 241, 48, 48);
		getContentPane().add(btnCreate);

		btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCliente();
			}
		});
		btnBuscar.setBounds(414, 88, 48, 48);
		getContentPane().add(btnBuscar);

		btnEditar = new JButton("");
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/editar.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
				
			}
			
		});
		btnEditar.setBounds(139, 241, 48, 48);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/excluir contato.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setBounds(197, 241, 48, 48);
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
		btnLimpar.setBounds(314, 241, 48, 48);
		getContentPane().add(btnLimpar);

		getRootPane().setDefaultButton(btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(42, 126, 56, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(111, 149, 292, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Placa Veiculo:");
		lblNewLabel_4.setBackground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(25, 180, 91, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(111, 174, 292, 20);
		getContentPane().add(txtPlaca);
		txtPlaca.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(Clientes.class.getResource("/img/carro-2.png")));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(380, 203, 249, 199);
		getContentPane().add(btnNewButton);
		
		

	}// fim do construtor
	
	private void adicionarCliente() {

		// validação do combobox
		// if(cboPerfil.getSelectedItem().equal("))
		// System.out.println


		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome do cliente");
			txtNome.requestFocus();
		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o telefone do cliente");
			txtTelefone.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o endereço do cliente");
			txtEndereco.requestFocus();
		} else if (txtPlaca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a placa do veiculo");
			txtPlaca.requestFocus();
		}else {
			
			// System.out.println("teste do botão adicionar");
			String create = "insert into clientes(nome,telefone,endereco,placaveiculo) values(?,?,?,?)";
			try {

				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtTelefone.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtPlaca.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
				con.close();
				
			}
			
				// tratamento de exceção em caso de duplicação de login	
	}// fim do método novo cliente

/**
 * Método para buscar novo usuario
 */
private void buscarCliente() {
	// System.out.println("teste do botão buscar");
	String read = "select * from clientes where nome = ?";
	try {
		con = dao.conectar();
		pst = con.prepareStatement(read);
		pst.setString(1, txtNome.getText());
		rs = pst.executeQuery();
		if (rs.next()) {
			txtID.setText(rs.getString(1));
			txtNome.setText(rs.getString(2));
			txtTelefone.setText(rs.getString(3));
			txtEndereco.setText(rs.getString(4));
			txtPlaca.setText(rs.getString(5));
			// mostrar a caixa o checkbox (troca de senha)
			
			// desabilitar a caixa de senha
			

			btnCreate.setEnabled(false);

			btnBuscar.setEnabled(false);

			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);

		} else {
			JOptionPane.showMessageDialog(null, "Cliente não cadastrado");

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
private void editarCliente() {
	// System.out.println("teste editar");

	if (txtNome.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Informe o nome do cliente");
		txtNome.requestFocus();
	}  else {

		String update = "update clientes set nome=?,telefone=?,endereco=?,placaveiculo=? where idcli=? ";
		try {
			con = dao.conectar();

			pst = con.prepareStatement(update);

			pst.setString(1, txtNome.getText());
			pst.setString(2, txtTelefone.getText());
			pst.setString(3, txtEndereco.getText());
			pst.setString(4, txtPlaca.getText());
			pst.setString(5, txtID.getText());

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dados do Cliente editados com sucesso");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}// fim do método editar usuario

/**
 * Método para exclusão do usuario
 */
private void excluirCliente() {
	// System.out.println("teste excluir");

	int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Cliente?", "ATENÇÃO!",
			JOptionPane.YES_NO_OPTION);
	if (confirma == JOptionPane.YES_OPTION) {
		String delete = "delete from clientes where idcli=?";
		try {

			con = dao.conectar();

			pst = con.prepareStatement(delete);
			pst.setString(1, txtID.getText());
			pst.executeUpdate();

			Limpar();
			JOptionPane.showMessageDialog(null, "Cliente excluído");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

private void Limpar() {
	txtID.setText(null);
	txtNome.setText(null);
	txtTelefone.setText(null);
	txtEndereco.setText(null);
	txtPlaca.setText(null);
	

	btnCreate.setEnabled(false);
	btnEditar.setEnabled(false);
	btnBuscar.setEnabled(true);
	btnExcluir.setEnabled(false);


}
}// fim do código
