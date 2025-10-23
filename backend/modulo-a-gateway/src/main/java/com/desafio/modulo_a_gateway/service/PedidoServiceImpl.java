package com.desafio.modulo_a_gateway.service;

import com.desafio.modulo_a_gateway.model.Pedido;
import com.desafio.modulo_a_gateway.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final StringPosicionalConversor conversor;

    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            StringPosicionalConversor conversor
    ) {
        this.pedidoRepository = pedidoRepository;
        this.conversor = conversor;
    }

    @Override
    public Pedido processarStringPosicional(String linhaPosicional) {

        Pedido pedido = conversor.converter(linhaPosicional);

        // falta fazer o CalculoValorService para definir o valor total
        // BigDecimal valorTotal = calculador.calcular(pedido);
        // pedido.setValor(valorTotal);

        Pedido salvo = pedidoRepository.save(pedido);

        //Falta fazer o envio pra fila

        return salvo;
    }
}