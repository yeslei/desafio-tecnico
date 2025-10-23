package com.desafio.modulo_a_gateway.service;

import com.desafio.modulo_a_gateway.model.Pedido;
import com.desafio.modulo_a_gateway.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido processarStringPosicional(String linhaPosicional) {
        return null;
    }
}