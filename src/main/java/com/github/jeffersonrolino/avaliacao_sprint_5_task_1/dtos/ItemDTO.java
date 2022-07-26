package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {
    private Long id;

    @NotNull @NotEmpty
    private String nome;

    @NotNull
    private LocalDateTime dataDeCriacao;

    @NotNull
    private LocalDateTime dataDeValidade;

    @PositiveOrZero
    private double valor;

    @NotNull @NotEmpty
    private String descricao;

    private List<Sale> ofertas;
}
