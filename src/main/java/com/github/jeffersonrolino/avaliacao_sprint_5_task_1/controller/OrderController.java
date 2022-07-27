package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.controller;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public List<OrderDTO> returnAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO>  returnOrderById(@PathVariable() Long id){
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDTO> deleteOrderById(@PathVariable Long id){
        return orderService.removeOrderById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateOrderById(
            @RequestBody OrderDTO orderDTO, @PathVariable("id") Long id)
    {
        boolean isSave = orderService.partialUpdateNewOrder(orderDTO, id);

        if(isSave){
            return ResponseEntity.status(HttpStatus.OK).body("Pedido atualizado com sucesso!");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Desculpe, pedido não foi atualizado!");
        }
    }

}
