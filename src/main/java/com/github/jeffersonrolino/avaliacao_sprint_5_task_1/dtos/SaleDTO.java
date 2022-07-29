package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class SaleDTO {
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    private String dataDeCriacao;

    @NotNull
    private String dataDeValidade;

    @NotNull @Positive
    private double desconto;

    @NotNull @NotEmpty
    private String descricao;

    public SaleDTO(String nome, String dataDeCriacao, String dataDeValidade, double desconto, String descricao) {
        this.nome = nome;
        this.dataDeCriacao = dataDeCriacao;
        this.dataDeValidade = dataDeValidade;
        this.desconto = desconto;
        this.descricao = descricao;
    }

    public SaleDTO(Sale sale) {
        this.id = sale.getId();
        this.nome = sale.getName();
        this.dataDeCriacao = LocalDateTimeParser.toString(sale.getCreationDate());
        this.dataDeValidade = LocalDateTimeParser.toString(sale.getExpirationDate());
        this.desconto = sale.getDiscount();
        this.descricao = sale.getDescription();
    }
}
