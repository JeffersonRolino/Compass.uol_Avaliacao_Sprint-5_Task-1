package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.mappers.Mapper;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull
    @Column(name = "preco")
    private double price;

    @Column(name = "descricao")
    private String description;

    @ManyToMany(targetEntity = Sale.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", nullable = false)
    private List< Sale> sales = new ArrayList<>();

    public Item(ItemDTO itemDTO) {
        this.id = itemDTO.getId();
        this.name = itemDTO.getNome();
        this.creationDate = LocalDateTimeParser.parseAndFormat(itemDTO.getDataDeCriacao());
        this.expirationDate = LocalDateTimeParser.parseAndFormat(itemDTO.getDataDeValidade());
        this.price = itemDTO.getValor();
        this.description = itemDTO.getDescricao();
        this.sales = Mapper.saleDTOsToSales(itemDTO.getOfertas());

    }

}
