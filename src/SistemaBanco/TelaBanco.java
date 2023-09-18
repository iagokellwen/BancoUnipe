package SistemaBanco;

import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaBanco {

	private JFrame frame;
	private JTextField tf_nome;
	private JTextField tf_saldo;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBanco window = new TelaBanco();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaBanco() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 538, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ButtonGroup grupo = new ButtonGroup();

		JLabel lblNewLabel = new JLabel("Banco Saf");
		lblNewLabel.setBounds(224, 11, 93, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome: ");
		lblNewLabel_1.setBounds(10, 109, 77, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Saldo Atual: ");
		lblNewLabel_1_1.setBounds(10, 137, 77, 14);
		frame.getContentPane().add(lblNewLabel_1_1);

		tf_nome = new JTextField();
		tf_nome.setBounds(114, 106, 377, 20);
		frame.getContentPane().add(tf_nome);
		tf_nome.setColumns(10);

		tf_saldo = new JTextField();
		tf_saldo.setColumns(10);
		tf_saldo.setBounds(114, 134, 77, 20);
		frame.getContentPane().add(tf_saldo);

		JLabel lblNewLabel_1_1_1 = new JLabel("Tipo Conta:");
		lblNewLabel_1_1_1.setBounds(224, 137, 77, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JRadioButton rb_PF = new JRadioButton("PF");
		rb_PF.setBounds(307, 133, 55, 23);
		frame.getContentPane().add(rb_PF);

		JRadioButton rb_PJ = new JRadioButton("PJ");
		rb_PJ.setBounds(380, 133, 55, 23);
		frame.getContentPane().add(rb_PJ);

		grupo.add(rb_PJ);
		grupo.add(rb_PF);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		scrollPane.setBounds(10, 189, 495, 155);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Ag\u00EAncia", "Conta", "Tipo Conta", "Nome", "Saldo" }));

		Conta conta = null;

		JButton btnCriarConta = new JButton("Criar conta");
		btnCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Conta conta = null;

				Random r = new Random();
				String tipoConta = "";

				// verificando se o RadioButton selecionado vai ser PF ou PJ
				if (rb_PF.isSelected()) {
					conta = new ContaPF();

					tipoConta = "Pessoa Física";

				}
				if (rb_PJ.isSelected()) {
					conta = new ContaPJ();

					tipoConta = "Pessoa Juridica";

				}
				conta.setTipoConta(tipoConta);

				// pegando um numero aleatório para agencia
				int agencia = r.nextInt(1000);
				conta.setAgencia(agencia);

				// pegando um numero aleatório para conta
				int numConta = r.nextInt(9000000);
				conta.setConta(numConta);

				String nome = tf_nome.getText();
				conta.setNome(nome);

				double saldo = Double.parseDouble(tf_saldo.getText());
				conta.setSaldo(saldo);

				DefaultTableModel tab = (DefaultTableModel) table.getModel();
				Object[] dados = { String.valueOf(conta.getAgencia()), String.valueOf(conta.getConta()),
						conta.getTipoConta(), conta.getNome(), String.valueOf(conta.getSaldo()) };
				tab.addRow(dados);

				// Limpando os dados e focando no tfNome para adcionar uma nova conta
				tf_nome.setText("");
				tf_saldo.setText("");
				grupo.clearSelection();
				tf_nome.requestFocus();

			}
		});
		btnCriarConta.setBounds(10, 395, 112, 23);
		frame.getContentPane().add(btnCriarConta);

		JButton btnSacar = new JButton("Sacar");
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = table.getSelectedRow(); // pegar a linha selecionada
				String tipo = (String) table.getValueAt(linhaSelecionada, 2);
				double valorSaque = 10;

				if (tipo == "Pessoa Juridica") {

					// table.setValueAt(pj.depositar(valorSaque), linhaSelecionada, 4);
				}

				if (tipo == "Pessoa Física") {
					// table.setValueAt(conta.depositar(valorSaque), linhaSelecionada, 4);
				}

			}
		});
		btnSacar.setBounds(145, 395, 112, 23);
		frame.getContentPane().add(btnSacar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Tratando o erro caso o usuario não selecione nenhuma linha
				if (table.getSelectedRow() != -1) {
					DefaultTableModel tab = (DefaultTableModel) table.getModel();
					tab.removeRow(table.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma conta para excluir!");
				}
			}
		});
		btnExcluir.setBounds(393, 395, 112, 23);
		frame.getContentPane().add(btnExcluir);

		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = table.getSelectedRow(); // pegar a linha selecionada
				String tipo = (String) table.getValueAt(linhaSelecionada, 2);

				double valorDeposito = 10;

				if (tipo == "Pessoa Juridica") {
					Conta pj = new ContaPJ();
					table.setValueAt(conta.depositar(valorDeposito), linhaSelecionada, 4);
				}

				if (tipo == "Pessoa Física") {
					Conta pf = new ContaPF();
					table.setValueAt(pf.depositar(valorDeposito), linhaSelecionada, 4);
				}

			}
		});
		btnDepositar.setBounds(267, 395, 116, 23);
		frame.getContentPane().add(btnDepositar);
	}
}
