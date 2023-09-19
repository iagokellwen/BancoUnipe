package SistemaBanco;

public class ContaPF extends Conta {

	public double depositar(double deposito) {

		return getSaldo()+deposito;
	}

}
