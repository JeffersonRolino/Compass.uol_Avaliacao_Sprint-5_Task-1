package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.controller;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(("/api/pedido"))
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createNewOrder(@RequestBody @Valid OrderDTO orderDTO){
        boolean isSave = orderService.saveNewOrder(orderDTO);

        if(isSave){
            return ResponseEntity.status(HttpStatus.CREATED).body("Pedido criado com sucesso!");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pedido não foi processado!");
        }
    }
}
