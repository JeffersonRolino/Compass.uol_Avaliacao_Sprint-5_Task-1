package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private double desconto;

    @NotNull @NotEmpty
    private String descricao;
}
