package SistemaBanco;

public class ContaPF extends Conta {

	@Override
	public double depositar(double deposito) {
		setSaldo(getSaldo() + deposito);

		return getSaldo();
	}

}
