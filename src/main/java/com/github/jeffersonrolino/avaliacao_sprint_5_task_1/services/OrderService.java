package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            List<Order> orders = orderRepository.findAll();
            List<OrderDTO> orderDTOS = new ArrayList<>();
            for(Order order : orders){
                orderDTOS.add(new OrderDTO(order));
            }
            return orderDTOS;
        } catch (RuntimeException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public ResponseEntity<OrderDTO> getOrderById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            return ResponseEntity.ok(new OrderDTO(order.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<OrderDTO> removeOrderById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

//    public boolean partialUpdateNewOrder(OrderDTO orderDTO, Long id){
//
//        try {
//            Optional<Order> targetOrder = orderRepository.findById(id);
//            if(targetOrder.isPresent()){
//                Order order = update(id, orderDTO, orderRepository);
//                orderRepository.save(order);
//                return true;
//            }
//            return false;
//        } catch (RuntimeException exception){
//            exception.printStackTrace();
//            return false;
//        }
//    }

    public boolean partialUpdateNewOrder(OrderDTO orderDTO, Long id) throws RuntimeException{
        Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);

        order.setCpf(orderDTO.getCpf());
        order.setItens(orderDTO.convertToItens(orderDTO.getItens()));
        order.setTotal(orderDTO.getTotal());

        orderRepository.save(order);
        return true;
    }


//    public Order update(Long id, OrderDTO orderDTO, OrderRepository orderRepository){
//        Order order = orderRepository.getReferenceById(id);
//
//            order.setCpf(orderDTO.getCpf());
//            order.setItens(orderDTO.convertToItens(orderDTO.getItens()));
//            order.setTotal(orderDTO.getTotal());
//
//        return order;
//    }









}
