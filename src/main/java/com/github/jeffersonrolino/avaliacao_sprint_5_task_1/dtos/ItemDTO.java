package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
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

    @NotNull
    private List<SaleDTO> ofertas = new ArrayList<>();


    public List<Sale> convertToSales(List<SaleDTO> salesDTOS){
        List<Sale> sales = new ArrayList<>();
        for (SaleDTO saleDTOS : salesDTOS){
            sales.add(new Sale(saleDTOS));
        }
        return sales;
    }


}
