package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(){}

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    public boolean saveNewOrder(OrderDTO orderDTO){
        try {
            Order order = new Order(orderDTO);
            orderRepository.save(order);
            return true;
        } catch (RuntimeException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public List<OrderDTO> getAllOrders(){
        try{
            return orderRepository.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
        } catch (RuntimeException exception){
            exception.printStackTrace();
            return null;
        }
    }
}
