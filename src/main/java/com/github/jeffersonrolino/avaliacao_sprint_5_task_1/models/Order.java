package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_pedido")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @OneToMany(mappedBy = "tb_pedido")
    private List<Item> itens;

    private Double total;
}
