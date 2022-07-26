package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {
    private Long id;

    @NotNull @NotEmpty
    private String name;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private LocalDateTime expirationDate;

    @NotNull
    private double price;

    @NotNull @NotEmpty
    private String description;

    private List<Sale> sales;
}
