package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

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
    private LocalDateTime creationDate;

    @Column(name = "data_validade")
    private LocalDateTime expirationDate;

    @PositiveOrZero
    @Column(name = "desconto")
    private double discount;

    @Column(name = "descricao")
    private String description;
}