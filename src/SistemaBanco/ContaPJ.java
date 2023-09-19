package SistemaBanco;

public class ContaPJ extends Conta {
	
	public double depositar(double deposito) {		
		
		return getSaldo()+(deposito*1.5);

	}

}
