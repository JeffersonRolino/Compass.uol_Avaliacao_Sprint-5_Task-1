package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class SaleDTO {
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    private LocalDateTime dataDeCriacao;

    @NotNull
    private LocalDateTime dataDeValidade;

    @NotNull
    private double desconto;

    @NotNull @NotEmpty
    private String descricao;
}
