package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long id;

    @NotNull
    private String cpf;

    @NotNull @NotEmpty
    private List<ItemDTO> itens = new ArrayList<>();

    @PositiveOrZero
    private Double total;


    public OrderDTO(String cpf, List<ItemDTO> itens, Double total) {
        this.cpf = cpf;
        this.itens = itens;
        this.total = total;
    }

    public List<Item> convertItens(List<ItemDTO> itemDTOS){
        List<Item> itens = new ArrayList<>();
        for (ItemDTO itemDTO : itemDTOS){
            itens.add(new Item(itemDTO));
        }
        return itens;
    }
}
