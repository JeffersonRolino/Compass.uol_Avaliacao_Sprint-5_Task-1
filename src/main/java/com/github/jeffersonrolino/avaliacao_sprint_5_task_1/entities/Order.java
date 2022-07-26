package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
    @JoinColumn(name = "item_id")
    private List<Item> itens = new ArrayList<>();

    @NotNull
    private Double total;

    public Order(OrderDTO orderDTO) {
        this.id = orderDTO.getId();
        this.cpf = orderDTO.getCpf();
        this.itens = orderDTO.getItens();
        this.total = orderDTO.getTotal();
    }
}
