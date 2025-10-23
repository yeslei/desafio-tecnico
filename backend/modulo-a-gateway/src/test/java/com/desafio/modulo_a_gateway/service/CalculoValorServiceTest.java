package com.desafio.modulo_a_gateway.service;

import com.desafio.modulo_a_gateway.model.Pedido;
import com.desafio.modulo_a_gateway.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculoValorServiceTest {

    private final CalculoValorService calculoService = new CalculoValorService();
    private Pedido pedidoBase;

    @BeforeEach
    void setup() {
        pedidoBase = new Pedido();
        pedidoBase.setBebida("COCA");
        pedidoBase.setStatus(Status.RECEBIDO);
    }

    @Test
    void deveAplicarDescontoDe10PorcentoParaHamburguerCompleto() {
        // Cenario: HAMBURGUER + CARNE + SALADA, 1 unid
        pedidoBase.setTipoLanche("HAMBURGUER");
        pedidoBase.setProteina("CARNE");
        pedidoBase.setAcompanhamento("SALADA");
        pedidoBase.setQuantidade(1);

        // Esperado: R$ 20,00 - 10% = R$ 18,00
        BigDecimal valorEsperado = new BigDecimal("18.00");

        BigDecimal valorAtual = calculoService.calcularValorTotal(pedidoBase);

        assertEquals(valorEsperado, valorAtual, "O desconto de 10% falhou.");
    }

    @Test
    void deveCalcularValorCorretamenteParaPastelSemDescontoEm2Unidades() {
        // Cenario: PASTEL, 2 unid
        pedidoBase.setTipoLanche("PASTEL");
        pedidoBase.setQuantidade(2);

        // Esperado: R$ 15,00 * 2 = R$ 30,00
        BigDecimal valorEsperado = new BigDecimal("30.00");

        BigDecimal valorAtual = calculoService.calcularValorTotal(pedidoBase);

        assertEquals(valorEsperado, valorAtual, "O cálculo do Pastel falhou.");
    }

    @Test
    void deveUsarPrecoBaseDe12ReaisParaOutrosLanches() {
        // Cenario: Outros lanches, 5 unid
        pedidoBase.setTipoLanche("PIZZA");
        pedidoBase.setQuantidade(5);

        // Esperado: R$ 12,00 * 5 = R$ 60,00
        BigDecimal valorEsperado = new BigDecimal("60.00");

        BigDecimal valorAtual = calculoService.calcularValorTotal(pedidoBase);

        assertEquals(valorEsperado, valorAtual);
    }
}