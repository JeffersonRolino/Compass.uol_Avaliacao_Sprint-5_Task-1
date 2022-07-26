package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {
    private Long id;

    @NotNull @NotEmpty
    private String nome;

    @NotNull
    private String dataDeCriacao;

    @NotNull
    private String dataDeValidade;

    @PositiveOrZero
    private double valor;

    @NotNull @NotEmpty
    private String descricao;

//    private List<Sale> ofertas = new ArrayList<>();
}
