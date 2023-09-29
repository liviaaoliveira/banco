package com.liviaaoliveira.banco.modelo;

import java.math.BigDecimal;

public class ContaEspecial extends Conta{

    private BigDecimal valorLimite;

    public ContaEspecial(Pessoa titular, int angencia, int numero, BigDecimal valorLimite) {
        super(titular, angencia, numero);
        this.valorLimite = valorLimite;
    }
    @Override
    public void debitarTarifaMensal() {
        sacar(new BigDecimal("20"));
    }

    @Override
    public BigDecimal getSaldoDisponivel() {
    return getSaldo().add(getValorLimite());
    }

    public BigDecimal getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(BigDecimal valorLimite) {
        this.valorLimite = valorLimite;
    }
}
