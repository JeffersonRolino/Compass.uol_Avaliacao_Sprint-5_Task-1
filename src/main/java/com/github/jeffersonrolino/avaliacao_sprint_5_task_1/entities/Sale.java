package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.SaleDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.validations.ValidDates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_ofertas")
@ValidDates
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oferta_id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_criacao")
    private LocalDateTime creationDate;

    @Column(name = "data_validade")
    private LocalDateTime expirationDate;

    @PositiveOrZero
    @Column(name = "desconto")
    private double discount;

    @Column(name = "descricao")
    private String description;

    public Sale(SaleDTO saleDTO) {
        this.id = saleDTO.getId();
        this.name = saleDTO.getNome();
        this.creationDate = LocalDateTimeParser.parseAndFormat(saleDTO.getDataDeCriacao());
        this.expirationDate = LocalDateTimeParser.parseAndFormat(saleDTO.getDataDeValidade());
        this.discount = saleDTO.getDesconto();
        this.description = saleDTO.getDescricao();
    }
}
