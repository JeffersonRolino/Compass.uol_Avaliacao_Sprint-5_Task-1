package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.Valid;
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
    private Long id;

    @CPF(message = "Formato do CPF inválido...")
    @NotNull
    private String cpf;


    @OneToMany(targetEntity = Item.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private List< Item> itens = new ArrayList<>();

    @NotNull
    private Double total;

    public Order( OrderDTO orderDTO) {
        this.id = orderDTO.getId();
        this.cpf = orderDTO.getCpf();
        this.itens = orderDTO.convertToItens(orderDTO.getItens());
        this.total = orderDTO.getTotal();
    }

    public List<@Valid ItemDTO> itensToItensDTO(List<@Valid Item> itens){
        List<@Valid ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : itens){
            itemDTOS.add(new ItemDTO(item));
        }
        return itemDTOS;
    }
}
