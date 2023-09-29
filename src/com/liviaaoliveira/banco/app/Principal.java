package com.liviaaoliveira.banco.app;

import com.liviaaoliveira.banco.modelo.*;
import com.liviaaoliveira.banco.modelo.atm.CaixaEletronico;
import com.liviaaoliveira.banco.modelo.excecao.SaldoInsuficienteException;
import com.liviaaoliveira.banco.modelo.pagamento.Boleto;
import com.liviaaoliveira.banco.modelo.pagamento.DocumentoPagavel;
import com.liviaaoliveira.banco.modelo.pagamento.Holerite;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Principal {

    public static void main(String[] args) {

        Pessoa titular1 = new Pessoa();
        titular1.setNome("Livia de Oliveira");
        titular1.setDocumento("1234567890");
        titular1.setRendimentoAnual(new BigDecimal("15000.0"));
        titular1.setTipo(TipoPessoa.JURIDICA);

        titular1.setDataUltimaAtualizacao(LocalDateTime.parse("2023-12-12T12:20:00"));
        System.out.println(titular1.getDataUltimaAtualizacao());

        Pessoa titular2 = new Pessoa();
        titular2.setNome("Giovanna Souza");
        titular2.setDocumento("0987654321");

        CaixaEletronico caixaEletronico = new CaixaEletronico();

        ContaInvestimento minhaConta = new ContaInvestimento(titular1, 123, 967);
        ContaEspecial suaConta = new ContaEspecial(titular2, 222, 333, new BigDecimal("1000"));

        try {
            minhaConta.depositar(new BigDecimal("30000"));
            minhaConta.sacar(new BigDecimal("1000"));
            minhaConta.creditarRendimentos(new BigDecimal("0.8"));
            minhaConta.debitarTarifaMensal();

            suaConta.depositar(new BigDecimal("15000"));
            suaConta.sacar(new BigDecimal("15500"));
            suaConta.debitarTarifaMensal();

            Boleto boletoEscola = new Boleto(titular2, new BigDecimal("35000"));
            Holerite salarioFuncionario = new Holerite(titular2, new BigDecimal("100"), 160);

            caixaEletronico.pagar(boletoEscola, minhaConta);
            caixaEletronico.pagar(salarioFuncionario, minhaConta);

            caixaEletronico.estornarPagamento(boletoEscola, minhaConta);
            caixaEletronico.estornarPagamento(salarioFuncionario, minhaConta);

            boletoEscola.imprimirRecibo();
            salarioFuncionario.imprimirRecibo();
        } catch (SaldoInsuficienteException e){
            System.out.println("Erro ao executar operacao na conta: " + e.getMessage());
        }

        //System.out.println("Boleto pago: " + boletoEscola.estaPago());
        //System.out.println("Salario pago: " + salarioFuncionario.estaPago());

        caixaEletronico.ImprimirSaldo(minhaConta);
        System.out.println();
        caixaEletronico.ImprimirSaldo(suaConta);




    }
}
