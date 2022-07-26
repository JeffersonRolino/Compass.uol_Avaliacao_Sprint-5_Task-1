package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.mappers.Mapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;

    @NotNull
    private String cpf;

    @NotNull @NotEmpty
    private List<@Valid ItemDTO> itens = new ArrayList<>();

    @PositiveOrZero
    private Double total;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.cpf = order.getCpf();
        this.itens = Mapper.itensToItensDTO(order.getItens());
        this.total = order.getTotal();
    }


}
