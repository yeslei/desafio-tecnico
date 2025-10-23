package com.desafio.modulo_a_gateway.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class RabbitMQPublisher {

    private static final String FILA_PEDIDOS_RECEBIDOS = "pedidos.recebidos";

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarPedidoRecebido(Long pedidoId) {

        Map<String, Long> mensagem = Map.of("pedidold", pedidoId);

        rabbitTemplate.convertAndSend(FILA_PEDIDOS_RECEBIDOS, mensagem);

        System.out.println("Pedido ID: " + pedidoId + " publicado com sucesso na fila: " + FILA_PEDIDOS_RECEBIDOS);
    }
}