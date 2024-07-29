import java.util.List;

public class Main {

	public static void main(String[] args) {
		Cliente joao = new Cliente();
		joao.setNome("Joao");
		joao.setSobrenome("Soares");

		ContaCorrente cc = new ContaCorrente(joao);
		ContaPoupanca poupanca = new ContaPoupanca(joao);

		List<Transacao> historicoCC = cc.getHistoricoTransacoes();

		try {
			cc.depositar(1000);
			cc.exibirExtrato();
			cc.getHistoricoTransacoes();
		} catch (ExpectionBanco e) {
			System.out.println("Erro ao depositar: " + e.getMessage());
		}

		try {
			cc.sacar(400.0);
			cc.exibirExtrato();
			cc.getHistoricoTransacoes();
		} catch (ExpectionBanco e) {
			System.out.println("Erro ao sacar: " + e.getMessage());
		}

		try {
			cc.transferir(500.0, poupanca);
			cc.exibirExtrato();
			cc.getHistoricoTransacoes();
		} catch (ExpectionBanco e) {
			System.out.println("Erro ao transferir: " + e.getMessage());
		}

		cc.imprimirExtrato();
		poupanca.imprimirExtrato();

	}

}
