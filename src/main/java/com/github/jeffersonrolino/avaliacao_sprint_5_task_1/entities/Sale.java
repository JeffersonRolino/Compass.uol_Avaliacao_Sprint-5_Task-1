package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.SaleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_ofertas")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oferta_id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_criacao")
    private String creationDate;

    @Column(name = "data_validade")
    private String expirationDate;

    @PositiveOrZero
    @Column(name = "desconto")
    private double discount;

    @Column(name = "descricao")
    private String description;

    public Sale(SaleDTO saleDTO) {
        this.id = saleDTO.getId();
        this.name = saleDTO.getNome();
        this.creationDate = saleDTO.getDataDeCriacao();
        this.expirationDate = saleDTO.getDataDeValidade();
        this.discount = saleDTO.getDesconto();
        this.description = saleDTO.getDescricao();
    }
}
