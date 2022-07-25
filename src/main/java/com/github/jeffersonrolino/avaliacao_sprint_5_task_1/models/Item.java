package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_criacao")
    private LocalDateTime creationDate;

    @Column(name = "data_validade")
    private LocalDateTime expirationDate;

    @Column(name = "valor")
    private double price;

    @Column(name = "descricao")
    private String description;

    @OneToMany(mappedBy = "item")
    private List<Sale> sales;

}
