package Banco;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private LocalDateTime dataHora;
    private String tipo;
    private double valor;
    private double saldoApos;

    public Transacao(String tipo, double valor, double saldoApos) {
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
        this.saldoApos = saldoApos;
    }

    // Getters
    public LocalDateTime getDataHora() { return dataHora; }
    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public double getSaldoApos() { return saldoApos; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("%s - %s: %.2f (Saldo: %.2f)", dataHora.format(formatter), tipo, valor, saldoApos);
    }
}