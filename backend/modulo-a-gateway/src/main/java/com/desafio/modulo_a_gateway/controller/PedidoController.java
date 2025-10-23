package com.desafio.modulo_a_gateway.controller;

import com.desafio.modulo_a_gateway.model.Pedido;
import com.desafio.modulo_a_gateway.service.PedidoService; // Garante a injeção do seu serviço
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping(value = "/posicional", consumes = "text/plain", produces = "application/json")
    public ResponseEntity<Pedido> receberPedidoPosicional(@RequestBody String linhaPosicional) {

        Pedido pedidoSalvo = pedidoService.processarStringPosicional(linhaPosicional);

        return new ResponseEntity<>(pedidoSalvo, HttpStatus.CREATED);
    }

}