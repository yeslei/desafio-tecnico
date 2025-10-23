package com.desafio.modulo_a_gateway.service;

import com.desafio.modulo_a_gateway.model.Pedido;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculoValorService {

    private static final BigDecimal PRECO_HAMBURGUER = new BigDecimal("20.00");
    private static final BigDecimal PRECO_PASTEL = new BigDecimal("15.00");
    private static final BigDecimal PRECO_OUTROS = new BigDecimal("12.00");
    private static final BigDecimal DESCONTO_PERCENTUAL = new BigDecimal("0.10"); // 10%

    public BigDecimal calcularValorTotal(Pedido pedido) {

        BigDecimal precoBase;

        String tipoLanche = pedido.getTipoLanche().toUpperCase();

        if (tipoLanche.contains("HAMBURGUER")) {
            precoBase = PRECO_HAMBURGUER;
        } else if (tipoLanche.contains("PASTEL")) {
            precoBase = PRECO_PASTEL;
        } else {
            precoBase = PRECO_OUTROS;
        }

        BigDecimal valorBruto = precoBase.multiply(new BigDecimal(pedido.getQuantidade()));

        BigDecimal desconto = BigDecimal.ZERO;

        boolean aplicaDesconto =
                tipoLanche.contains("HAMBURGUER") &&
                        pedido.getProteina().toUpperCase().contains("CARNE") &&
                        pedido.getAcompanhamento().toUpperCase().contains("SALADA");

        if (aplicaDesconto) {
            desconto = valorBruto.multiply(DESCONTO_PERCENTUAL);
        }

        BigDecimal valorTotal = valorBruto.subtract(desconto)
                .setScale(2, RoundingMode.HALF_UP); // Arredonda para 2 casas

        return valorTotal;
    }
}