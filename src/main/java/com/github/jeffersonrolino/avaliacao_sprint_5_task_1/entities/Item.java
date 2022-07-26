package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_criacao")
    private LocalDateTime creationDate;

    @Column(name = "data_validade")
    private LocalDateTime expirationDate;

    @Column(name = "preco")
    private double price;

    @Column(name = "descricao")
    private String description;

    @Column(name = "ofertas")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Sale> sales;

}
