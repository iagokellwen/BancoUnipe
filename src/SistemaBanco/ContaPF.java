package SistemaBanco;

import javax.swing.JOptionPane;

public class ContaPF extends Conta {

	public Float depositar(float deposito) {

		return getSaldo() + deposito;
	}

	public Float Sacar(float saque) {
		
		if (getSaldo() > (saque + (saque * 0.0125f))) {
			return getSaldo() - saque -(saque * 0.0125f);
		}else {
			float saldoDisponivel;
			saldoDisponivel = saque -(saque * 0.0125f);
			JOptionPane.showMessageDialog(null, "Saldo insuficiente!\nSaldo disponível para saque é: " + saldoDisponivel);
		}

		return getSaldo();
	}

}
