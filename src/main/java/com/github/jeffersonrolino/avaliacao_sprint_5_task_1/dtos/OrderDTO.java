package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long id;

    @NotNull
    private String cpf;

    @NotNull @NotEmpty
    private List<ItemDTO> itens = new ArrayList<>();

    @PositiveOrZero
    private Double total;

    public OrderDTO(){}

    public OrderDTO(String cpf, List<ItemDTO> itens, Double total) {
        this.cpf = cpf;
        this.itens = itens;
        this.total = total;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.cpf = order.getCpf();
        this.itens = order.itensToItensDTO(order.getItens());
        this.total = order.getTotal();
    }

    public List<Item> convertToItens(List<ItemDTO> itemDTOS){
        List<Item> itens = new ArrayList<>();
        for (ItemDTO itemDTO : itemDTOS){
            itens.add(new Item(itemDTO));
        }
        return itens;
    }


}
