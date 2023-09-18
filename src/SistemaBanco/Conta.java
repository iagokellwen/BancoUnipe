package SistemaBanco;

public class Conta {

	private int agencia;
	private int conta;
	private String tipoConta;
	private String nome;
	private double saldo;
	private double deposito;

	public double getDeposito() {
		return deposito;
	}

	public void setDeposito(double deposito) {
		this.deposito = deposito;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double depositar(double deposito) {

		setSaldo(getSaldo() + (deposito));

		return getSaldo();

	}

//public double sacar(double saque) {
//	
//	setSaldo(getSaldo() - saque);
//	getSaldo();
//}

}
