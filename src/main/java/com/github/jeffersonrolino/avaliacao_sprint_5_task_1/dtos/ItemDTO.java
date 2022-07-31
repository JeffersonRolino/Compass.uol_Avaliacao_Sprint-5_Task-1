package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.mappers.Mapper;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
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
public class ItemDTO {
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    private String dataDeCriacao;

    @NotNull
    private String dataDeValidade;

    @PositiveOrZero
    private double valor;

    @NotNull @NotEmpty
    private String descricao;

    @NotNull
    private List<@Valid SaleDTO> ofertas = new ArrayList<>();

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.nome = item.getName();
        this.dataDeCriacao = LocalDateTimeParser.toString(item.getCreationDate());
        this.dataDeValidade = LocalDateTimeParser.toString(item.getCreationDate());
        this.valor = item.getPrice();
        this.descricao = item.getDescription();
        this.ofertas = Mapper.salesToSalesDTO(item.getSales());
    }
}
