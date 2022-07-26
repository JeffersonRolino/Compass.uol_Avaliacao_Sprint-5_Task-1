package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_pedidos")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long id;

    private String cpf;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> itens;

    private Double total;
}
