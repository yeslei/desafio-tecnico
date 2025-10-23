package com.desafio.modulo_a_gateway.service;

import com.desafio.modulo_a_gateway.model.Pedido;
import com.desafio.modulo_a_gateway.model.Status;
import com.desafio.modulo_a_gateway.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final StringPosicionalConversor conversor;
    private final CalculoValorService calculador;
    private final RabbitMQPublisher publisher;

    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            StringPosicionalConversor conversor,
            CalculoValorService calculador,
            RabbitMQPublisher publisher
    ) {
        this.pedidoRepository = pedidoRepository;
        this.conversor = conversor;
        this.calculador = calculador;
        this.publisher = publisher;
    }

    @Override
    public Pedido processarStringPosicional(String linhaPosicional) {

        Pedido pedido = conversor.converter(linhaPosicional);
        pedido.setValor(calculador.calcularValorTotal(pedido));
        pedido.setStatus(Status.RECEBIDO);

        Pedido salvo = pedidoRepository.save(pedido);

        publisher.publicarPedidoRecebido(salvo.getId());

        return salvo;
    }
}