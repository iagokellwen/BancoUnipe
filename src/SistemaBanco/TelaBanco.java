package SistemaBanco;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class TelaBanco {

	protected static final String ContaPF = null;
	private JFrame frame;
	private JTextField tf_nome;
	private JTextField tf_saldo;
	private JTable table;
	private Conta conta;
	private JLabel jl_relogio;

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

	public void data() {

		Thread data = new Thread() {
			public void run() {

				try {
					for (;;) {
						Calendar cal = new GregorianCalendar();
						int dia = cal.get(Calendar.DAY_OF_MONTH);
						int mes = cal.get(Calendar.MONTH);
						int ano = cal.get(Calendar.YEAR);

						int segundo = cal.get(Calendar.SECOND);
						int minuto = cal.get(Calendar.MINUTE);
						int hora = cal.get(Calendar.HOUR);

						jl_relogio.setText("Hora " + hora + ":" + minuto + ":" + segundo + " - Data " + dia + "/" + mes
								+ "/" + ano);

						sleep(1000);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		};

		data.start();

	}

	/**
	 * Create the application.
	 */
	public TelaBanco() {
		initialize();
		data();
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
		JLabel label = new JLabel();
		ImageIcon imagem = new ImageIcon(getClass().getResource("banco.png"));
		label.setIcon(imagem);
		label.setBounds(24, 11, 79, 75);
		frame.getContentPane().add(label);

		JLabel lblNewLabel = new JLabel("Banco Santos");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBackground(new Color(0, 128, 255));
		lblNewLabel.setBounds(145, 11, 79, 55);
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

		conta = null;
		ContaPF pf = new ContaPF();
		ContaPJ pj = new ContaPJ();

		JButton btnCriarConta = new JButton("Criar conta");
		btnCriarConta.setBackground(new Color(255, 255, 128));
		btnCriarConta.setForeground(new Color(0, 128, 0));
		btnCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Tratando o erro caso o usuario não digite dados válidos
				try {

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

					float saldo = Float.parseFloat(tf_saldo.getText());
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

				} catch (Exception e2) {
					if (grupo.isSelected(null)) {
						JOptionPane.showMessageDialog(null, "Escolha um tipo de conta!");
					} else {
						JOptionPane.showMessageDialog(null, "Nome ou saldo inválidos para criar a conta!");
					}

				}

			}
		});
		btnCriarConta.setBounds(10, 395, 112, 23);
		frame.getContentPane().add(btnCriarConta);

//		ContaPF pf = new ContaPF();
//		ContaPJ pj = new ContaPJ();

		JButton btnSacar = new JButton("Sacar");
		btnSacar.setForeground(new Color(255, 255, 255));
		btnSacar.setBackground(new Color(255, 0, 0));
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Tratando o erro caso o usuario não selecione nenhuma linha
				if (table.getSelectedRow() != -1) {
					int linhaSelecionada = table.getSelectedRow(); // pegar a linha selecionada
					String tipo = (String) table.getValueAt(linhaSelecionada, 2);
					String valor = JOptionPane.showInputDialog(null, "Digite o valor do seu saque: ");
					Float valorSaque = Float.parseFloat(valor);

					if (tipo == "Pessoa Juridica") {

						String saldoAtual = (table.getValueAt(linhaSelecionada, 4).toString());
						pj.setSaldo(Float.parseFloat(saldoAtual));
						table.setValueAt(pj.Sacar(valorSaque), linhaSelecionada, 4);

					}

					if (tipo == "Pessoa Física") {

						String saldoAtual = (table.getValueAt(linhaSelecionada, 4).toString());
						pf.setSaldo(Float.parseFloat(saldoAtual));
						table.setValueAt(pf.Sacar(valorSaque), linhaSelecionada, 4);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma conta para sacar!");
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
		btnDepositar.setBackground(new Color(0, 0, 255));
		btnDepositar.setForeground(new Color(255, 255, 255));
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tratando o erro caso o usuario não selecione nenhuma linha

				try {
					if (table.getSelectedRow() != -1) {

						int linhaSelecionada = table.getSelectedRow(); // pegar a linha selecionada
						String tipo = (String) table.getValueAt(linhaSelecionada, 2);

						String valor = JOptionPane.showInputDialog(null, "Digite o valor do seu deposito: ");
						Float valorDeposito = Float.parseFloat(valor);

						if (tipo == "Pessoa Física") {

							String saldoAtual = (table.getValueAt(linhaSelecionada, 4).toString());
							pf.setSaldo(Float.parseFloat(saldoAtual));
							table.setValueAt(pf.depositar(valorDeposito), linhaSelecionada, 4);
						}
						if (tipo == "Pessoa Juridica") {

							String saldoAtual = (table.getValueAt(linhaSelecionada, 4).toString());
							pj.setSaldo(Float.parseFloat(saldoAtual));
							table.setValueAt(pj.depositar(valorDeposito), linhaSelecionada, 4);

						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecione uma conta para depositar!");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Digite um valor de deposito válido!");
				}

			}
		});
		btnDepositar.setBounds(267, 395, 116, 23);
		frame.getContentPane().add(btnDepositar);

		jl_relogio = new JLabel("Data");
		jl_relogio.setBounds(276, 19, 236, 39);
		frame.getContentPane().add(jl_relogio);

	}
}
