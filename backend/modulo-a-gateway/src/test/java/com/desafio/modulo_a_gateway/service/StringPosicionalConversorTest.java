package com.desafio.modulo_a_gateway.service;

import com.desafio.modulo_a_gateway.model.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringPosicionalConversorTest {

    private final StringPosicionalConversor conversor = new StringPosicionalConversor();

    @Test
    void deveLancarExcecaoSeStringNaoTiver40Caracteres() {
        // String curta (39 chars)
        String stringCurta = "PASTEL FRANGO BACON 01 COCA   ";

        // Verifica se a exceção de tamanho é lançada
        assertThrows(IllegalArgumentException.class, () -> {
            conversor.converter(stringCurta);
        });
    }

    @Test
    void deveLancarExcecaoSeQuantidadeNaoForNumerica() {
        // Quantidade (posições 31-32) com letras: '0A'
        String stringInvalida = "HAMBURGUER  CARNE     SALADA    0ACOCA    ";

        // Verifica se a exceção de formato numérico é lançada
        assertThrows(IllegalArgumentException.class, () -> {
            conversor.converter(stringInvalida);
        });
    }

    @Test
    void deveExtrairCamposCorretamenteETrimarEspacos() {
        // Exemplo de 40 chars com preenchimento:
        String linha = "PASTEL    FRANGO    BACON     05SUCO    ";

        Pedido pedido = conversor.converter(linha);

        // Verifica se os espaços foram removidos (trim)
        assertEquals("PASTEL", pedido.getTipoLanche(), "TipoLanche não foi extraído/limpo corretamente.");
        assertEquals(5, pedido.getQuantidade().intValue(), "Quantidade não foi convertida corretamente.");
        assertEquals("FRANGO", pedido.getProteina());
    }
}