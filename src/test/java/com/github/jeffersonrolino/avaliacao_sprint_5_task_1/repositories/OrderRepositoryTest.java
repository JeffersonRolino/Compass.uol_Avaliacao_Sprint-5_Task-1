package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void itShouldfindOrderByCpf() {
        //Given
        LocalDateTime creationDate = LocalDateTimeParser.parseAndFormat("26/01/2019 00:01:01");
        LocalDateTime expirationDate = LocalDateTimeParser.parseAndFormat("31/12/2022 23:59:59");

            //and

        List<Sale> sales = new ArrayList<>();

        Sale sale1 = new Sale(
                1L,
                "Queima de Estoque",
                creationDate,
                expirationDate,
                10.00,
                "Incrível, todos nosso produtos com R$10,00 reais de desconto!!!"
        );

        sales.add(sale1);

            //and

        List<Item> itens = new ArrayList<>();

        Item item1 = new Item(
                1L,
                "Headset Gamer",
                creationDate,
                expirationDate,
                249.90,
                "Headset Gamer HyperX Cloud Stinger",
                sales
        );

        itens.add(item1);

        String cpf = "168.329.311-82";

        Order order = new Order(
                1L,
                cpf,
                itens,
                6000.00
        );

        orderRepository.save(order);
        //When
        List<Order> orders = orderRepository.findByCpf(cpf);
        //Then
        assertThat(orders).isNotNull();
        assertThat(orders.get(0).getClass().getSimpleName()).isEqualTo(order.getClass().getSimpleName());
        assertThat(orders.get(0).getCpf()).isEqualTo(cpf);
    }
}