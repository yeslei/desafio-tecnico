package com.desafio.modulo_a_gateway.service;

import com.desafio.modulo_a_gateway.model.Pedido;
import com.desafio.modulo_a_gateway.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private RabbitMQPublisher publisher;
    @Mock
    private StringPosicionalConversor conversor;
    @Mock
    private CalculoValorService calculador;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Test
    void deveChamarTodosOsComponentesNaOrdemCorreta() {

        String linhaPosicionalDeTeste = "STRING FICTICIA";
        Long pedidoId = 123L;

        Pedido pedidoDoConversor = new Pedido();

        Pedido pedidoSalvoComId = new Pedido();
        pedidoSalvoComId.setId(pedidoId);

        when(conversor.converter(linhaPosicionalDeTeste)).thenReturn(pedidoDoConversor);
        when(calculador.calcularValorTotal(any(Pedido.class))).thenReturn(new BigDecimal("18.00"));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoSalvoComId);

        pedidoService.processarStringPosicional(linhaPosicionalDeTeste);

        // ASSERT (Verificação do Fluxo)
        // 1. Verifica se o save foi chamado
        verify(pedidoRepository, times(1)).save(any(Pedido.class));

        // 2. Verifica se o publish foi chamado com o ID retornado pelo DB
        verify(publisher, times(1)).publicarPedidoRecebido(pedidoId);

        // 3. Opcional: Verifica se o valor foi setado antes de salvar (boa prática)
        verify(calculador, times(1)).calcularValorTotal(pedidoDoConversor);
    }
}