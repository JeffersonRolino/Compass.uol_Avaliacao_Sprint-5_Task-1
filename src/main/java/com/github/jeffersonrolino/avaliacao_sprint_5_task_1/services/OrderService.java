package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public OrderService(){}

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    public boolean saveNewOrder( OrderDTO orderDTO){
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
            List< Order> orders = orderRepository.findAll();
            List< OrderDTO> orderDTOS = new ArrayList<>();
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

    public boolean partialUpdateNewOrder(OrderDTO orderDTO, Long id) throws RuntimeException{
        Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);

        order.setCpf(orderDTO.getCpf());
        order.setItens(orderDTO.convertToItens(orderDTO.getItens()));
        order.setTotal(orderDTO.getTotal());

        orderRepository.save(order);
        return true;
    }

    public List<OrderDTO> getAllOrdersByPriceValue(String order) {
        if(order.equalsIgnoreCase("ASC")){
            return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "total")).stream().map(OrderDTO::new).collect(Collectors.toList());
        }
        if(order.equalsIgnoreCase("DESC"))
        {
            return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "total")).stream().map(OrderDTO::new).collect(Collectors.toList());
        }
        return getAllOrders();
    }

    public List<OrderDTO> getAllOrdersByCpf(String cpf) {
        return orderRepository.findByCpf(cpf).stream().map(OrderDTO::new).collect(Collectors.toList());
    }
}
