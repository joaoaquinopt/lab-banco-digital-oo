package Banco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements IConta {

	private List<Transacao> historicoTransacoes = new ArrayList<>();
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;
	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public void sacar(double valor) throws ExpectionBanco {
		if (temSaldoSuficiente(valor)) {
			saldo -= valor;
			historicoTransacoes.add(new Transacao("Saque", valor, saldo));
		} else {
			throw new ExpectionBanco("Saldo insuficiente para saque.");
		}
	}

	@Override
	public void depositar(double valor) throws ExpectionBanco {
		if (valor <= 0) {
			throw new ExpectionBanco("Valor de depósito inválido. O valor deve ser positivo.");
		} else {
			saldo += valor;
			historicoTransacoes.add(new Transacao("Depósito", valor, saldo));
		}
	}

	@Override
	public void transferir(double valor, IConta contaDestino) throws ExpectionBanco {
		if (temSaldoSuficiente(valor)) {
			sacar(valor);
			contaDestino.depositar(valor);
			historicoTransacoes.add(new Transacao("Transferência", valor, saldo));
		} else {
			throw new ExpectionBanco("Saldo insuficiente para transferência.");
		}
	}

	public void exibirExtrato() {
		System.out.println("\nExtrato da conta:");
		for (Transacao transacao : historicoTransacoes) {
			System.out.println(transacao);
		}
		System.out.println("Saldo atual: " + saldo + "\n");
	}

	public List<Transacao> getHistoricoTransacoes() {
		return Collections.unmodifiableList(historicoTransacoes);
	}

	public boolean temSaldoSuficiente(double valor) {
		return saldo >= valor;
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome() + " " + this.cliente.getSobrenome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f",	this.saldo));
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Banco.Conta ===");
		imprimirInfosComuns();
	}
}
