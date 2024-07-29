
public interface IConta {
	
	void sacar(double valor) throws ExpectionBanco;
	
	void depositar(double valor) throws ExpectionBanco;
	
	void transferir(double valor, IConta contaDestino) throws ExpectionBanco;

	void imprimirExtrato();
}
