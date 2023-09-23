package SistemaBanco;

import javax.swing.JOptionPane;

public class ContaPJ extends Conta {

	public float depositar(float deposito) {
		JOptionPane.showMessageDialog(null, "Deposito realizado!");

		return (getSaldo() + (deposito * 1.015f));

	}

	public Float Sacar(float saque) {
		if (getSaldo() > saque) {
			JOptionPane.showMessageDialog(null, "Saque realizado!");
			return getSaldo() - saque;			
			
		} else {
			JOptionPane.showMessageDialog(null, "Saldo insuficiente!\nSaldo disponível para saque é: "+ getSaldo());
		}

		return getSaldo();
	}

}
