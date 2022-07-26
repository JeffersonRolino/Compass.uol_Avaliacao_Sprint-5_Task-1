package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories.ItemRepository;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    public boolean saveNewOrder(OrderDTO orderDTO){
        try {
            Order order = new Order(orderDTO);
//            @NotNull @NotEmpty List<ItemDTO> itensToSave = orderDTO.getItens();
//            for(ItemDTO itemDTO : itensToSave){
//                System.out.println(itemDTO.getId());
//                System.out.println(itemDTO.getNome());
//                System.out.println(itemDTO.getDataDeCriacao());
//                System.out.println(itemDTO.getDataDeValidade());
//                System.out.println(itemDTO.getValor());
//                System.out.println(itemDTO.getDescricao());
//            }
            orderRepository.save(order);
            return true;
        } catch (RuntimeException exception){
            exception.printStackTrace();
            return false;
        }
    }

}
