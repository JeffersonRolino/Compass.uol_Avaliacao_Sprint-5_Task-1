package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "preco")
    private double price;

    @Column(name = "descricao")
    private String description;

    @Column(name = "pedidos")
    @ManyToMany(mappedBy = "itens", cascade = CascadeType.ALL)
    private List<Order> orders;

    @Column(name = "ofertas")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Sale> sales;

    public Item(ItemDTO itemDTO) {
        this.id = itemDTO.getId();
        this.name = itemDTO.getNome();
        this.creationDate = itemDTO.getDataDeCriacao();
        this.expirationDate = itemDTO.getDataDeValidade();
        this.price = itemDTO.getValor();
        this.description = itemDTO.getDescricao();
        this.sales = itemDTO.getOfertas();
    }
}
