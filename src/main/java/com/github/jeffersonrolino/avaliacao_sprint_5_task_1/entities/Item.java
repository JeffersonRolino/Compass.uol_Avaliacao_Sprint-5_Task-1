package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private String creationDate;

    @Column(name = "data_validade")
    private String expirationDate;

    @NotNull
    @Column(name = "preco")
    private double price;

    @Column(name = "descricao")
    private String description;

//    @ManyToMany(mappedBy = "itens")
//    private List<Order> orders = new ArrayList<>();

//    @ManyToMany()
//    private List<Sale> sales = new ArrayList<>();

    public Item(ItemDTO itemDTO) {
        this.id = itemDTO.getId();
        this.name = itemDTO.getNome();
        this.creationDate = itemDTO.getDataDeCriacao();
        this.expirationDate = itemDTO.getDataDeValidade();
        this.price = itemDTO.getValor();
        this.description = itemDTO.getDescricao();
//        this.sales = itemDTO.getOfertas();
    }
}
