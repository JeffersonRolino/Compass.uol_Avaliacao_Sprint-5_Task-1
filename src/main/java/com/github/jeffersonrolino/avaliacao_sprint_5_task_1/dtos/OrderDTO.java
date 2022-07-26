package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long id;

    @NotNull
    private String cpf;

    @NotNull @NotEmpty
    private List<Item> itens;

    @NotNull @NotEmpty
    private Double total;


    public OrderDTO(String cpf, List<Item> itens, Double total) {
        this.cpf = cpf;
        this.itens = itens;
        this.total = total;
    }
}
