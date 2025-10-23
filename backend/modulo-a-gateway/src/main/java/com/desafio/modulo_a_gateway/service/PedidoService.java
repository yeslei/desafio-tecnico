package com.desafio.modulo_a_gateway.service;

import com.desafio.modulo_a_gateway.model.Pedido;

public interface PedidoService {
    Pedido processarStringPosicional(String linhaPosicional);
}