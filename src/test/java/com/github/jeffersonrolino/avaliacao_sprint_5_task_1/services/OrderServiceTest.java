package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.SaleDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp(){
        orderService = new OrderService(orderRepository);
    }

    @Test
    void saveNewOrder() {
        //given
        List<ItemDTO> itens = new ArrayList<>();
        List<SaleDTO> sales = new ArrayList<>();


        SaleDTO saleDTO1 = new SaleDTO(
                "Queima de Estoque",
                "26/12/2022 00:01:01",
                "31/12/2022 23:59:59",
                10.00,
                "Incrível, todos nosso produtos com R$10,00 reais de desconto!!!"
        );

        SaleDTO saleDTO2 = new SaleDTO(
                "Promoção de Natal",
                "20/12/2022 00:01:01",
                "31/12/2022 23:59:59",
                20.00,
                "Incrível, todos nosso produtos com R$20,00 reais de desconto!!!"
        );

        sales.add(saleDTO1);
        sales.add(saleDTO2);

        ItemDTO itemDTO1 = new ItemDTO(
                1L,
                "Headset Gamer",
                "22/11/2019 07:06:45",
                "16/07/2022 16:24:58",
                249.90,
                "Headset Gamer HyperX Cloud Stinger",
                sales
        );

        ItemDTO itemDTO2 = new ItemDTO(
                2L,
                "HD SSD",
                "13/01/2017 06:10:13",
                "22/03/2022 16:24:58",
                179.90,
                "SSD Kingston A400, 240GB, SATA, Leitura 500MB/s, Gravação 350MB/s - SA400S37/240G",
                sales
        );

        itens.add(itemDTO1);
        itens.add(itemDTO2);

        OrderDTO orderDTO = new OrderDTO(1L, "168.329.311-82", itens,429.80);

        boolean isSaved = orderService.saveNewOrder(orderDTO);

        //when
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

        verify(orderRepository).save(orderArgumentCaptor.capture());

        Order capturedOrder = orderArgumentCaptor.getValue();

        String capturedClass = capturedOrder.getClass().getSimpleName();

        //then
        assertThat(capturedOrder).isNotNull();
        assertThat(capturedClass).isEqualTo(Order.class.getSimpleName());
        assertThat(isSaved).isEqualTo(true);
    }
}