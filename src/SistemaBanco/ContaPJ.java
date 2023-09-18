package SistemaBanco;

public class ContaPJ extends Conta {

	@Override
	public double depositar(double deposito) {
		
		
		setSaldo(getSaldo() + (deposito*1.5));
		
		return getSaldo();

	}

}
