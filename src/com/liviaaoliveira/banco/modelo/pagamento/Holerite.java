package com.liviaaoliveira.banco.modelo.pagamento;

import com.liviaaoliveira.banco.modelo.Pessoa;

import java.math.BigDecimal;

public class Holerite implements DocumentoPagavel, DocumentoEstornavel {

    private Pessoa funcionario;
    private BigDecimal valorHora;
    private int quantidadeDeHoras;
    private boolean pago;

    public Holerite(Pessoa funcionario, BigDecimal valorHora, int quantidadeDeHoras) {
        this.funcionario = funcionario;
        this.valorHora = valorHora;
        this.quantidadeDeHoras = quantidadeDeHoras;
    }

    @Override
    public BigDecimal getValorTotal() {
        return valorHora.multiply( new BigDecimal(quantidadeDeHoras));
    }

    @Override
    public boolean estaPago() {
        return pago;
    }

    @Override
    public void estornarPagamento() {
        pago = false;
    }

    @Override
    public void quitarPagamento() {
    pago = true;
    }
}
