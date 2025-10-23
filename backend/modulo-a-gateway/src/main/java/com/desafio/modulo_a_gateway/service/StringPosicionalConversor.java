package com.desafio.modulo_a_gateway.service;

import com.desafio.modulo_a_gateway.model.Pedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class StringPosicionalConversor {

    public Pedido converter(String linhaPosicional) {

        if (linhaPosicional == null || linhaPosicional.length() != 40) {
            throw new IllegalArgumentException("A linha posicional deve ter exatamente 40 caracteres. Comprimento atual: " + (linhaPosicional != null ? linhaPosicional.length() : 0));
        }

        // Posição 1-10 (substring 0 a 10)
        String tipoLanche = linhaPosicional.substring(0, 10).trim();

        // Posição 11-20 (substring 10 a 20)
        String proteina = linhaPosicional.substring(10, 20).trim();

        // Posição 21-30 (substring 20 a 30)
        String acompanhamento = linhaPosicional.substring(20, 30).trim();

        // Posição 33-40 (substring 32 a 40)
        String bebida = linhaPosicional.substring(32, 40).trim();

        // Posição 31-32 (substring 30 a 32)
        String quantidadeBruta = linhaPosicional.substring(30, 32);

        int quantidade;

        try {
            quantidade = Integer.parseInt(quantidadeBruta);

            if (quantidade < 1 || quantidade > 99) {
                throw new IllegalArgumentException("Quantidade deve ser um valor numérico entre 01 e 99.");
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Quantidade (posições 31-32) deve conter apenas dígitos numéricos (01-99).");
        }

        Pedido pedido = new Pedido();
        pedido.setTipoLanche(tipoLanche);
        pedido.setProteina(proteina);
        pedido.setAcompanhamento(acompanhamento);
        pedido.setQuantidade(quantidade);
        pedido.setBebida(bebida);

        return pedido;
    }
}