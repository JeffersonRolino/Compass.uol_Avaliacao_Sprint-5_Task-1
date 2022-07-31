package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.SaleDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository);
    }

    @Test
    void shouldSaveNewOrder() {
        List<OrderDTO> orderDTOS = orderDTOS();
        OrderDTO orderDTO = orderDTOS.get(0);

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

    @Test
    void shouldGetAllOrders() {
        List<Order> orders = orders();
        Mockito.when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDTO> orderDTOS = orderService.getAllOrders();

        for (OrderDTO orderDTO : orderDTOS){
            assertThat(orderDTO).isNotNull();
            assertThat(orderDTO.getClass()).isEqualTo(OrderDTO.class);
        }
    }


    @Test
    void shouldNotGetAllOrders() {
        Mockito.when(orderService.getAllOrders()).thenThrow(RuntimeException.class);

        try{
            List<OrderDTO> orderDTOS = orderService.getAllOrders();
        } catch (Exception e){
            assertThat(e).isEqualTo(RuntimeException.class);
            assertThat(orderDTOS()).isNull();
        }
    }



    @Test
    void getOrderById() {
        //GIVEN
        List<Order> orders = orders();
        Order order = orders.get(0);

        //WHEN
        ResponseEntity<OrderDTO> responseEntity = new ResponseEntity<>(new OrderDTO(), HttpStatus.OK);

        ResponseEntity<OrderDTO> notFoundResponseEntity = new ResponseEntity<>(new OrderDTO(), HttpStatus.NOT_FOUND);

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> optionalOrder = orderRepository.findById(1L);

        //THEN
        Assertions.assertEquals(orderRepository.findById(1L), optionalOrder);
        Assertions.assertEquals(optionalOrder.get().getId(), 1L);

        if (optionalOrder.isPresent()) {
            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        }


//        Assertions.assertEquals(optionalOrder.get().getId(), order.getId());
//
//        Assertions.assertEquals(optionalOrder.getClass().getSimpleName(), Optional.of(order).getClass().getSimpleName());
//
//        Optional<Order> optionalOrder2 = orderRepository.findById(id);
//        Assertions.assertEquals(optionalOrder2.get().getId(), optionalOrder.get().getId());
//        Assertions.assertEquals(optionalOrder2.get().getClass().getSimpleName(),
//                optionalOrder.get().getClass().getSimpleName());


    }

    @Test
    void removeOrderById() {

    }

    @Test
    void partialUpdateNewOrder() {
    }

    @Test
    void getAllOrdersByPriceValue() {
    }

    @Test
    void getAllOrdersByCpf() {
    }


    //Métodos Utilitários
    public List<OrderDTO> orderDTOS() {
        LocalDateTime creationDate = LocalDateTimeParser.parseAndFormat("26/01/2019 00:01:01");
        LocalDateTime expirationDate = LocalDateTimeParser.parseAndFormat("31/12/2022 23:59:59");

        List<OrderDTO> orderDTOS = new ArrayList<>();
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

        OrderDTO orderDTO1 = new OrderDTO(1L, "168.329.311-82", itens, 429.80);
        OrderDTO orderDTO2 = new OrderDTO(2L, "325.606.417-55", itens, 452.80);
        OrderDTO orderDTO3 = new OrderDTO(3L, "277.118.703-53", itens, 429.80);

        orderDTOS.add(orderDTO1);
        orderDTOS.add(orderDTO2);
        orderDTOS.add(orderDTO3);

        return orderDTOS;
    }

    public List<Order> orders() {
        LocalDateTime creationDate = LocalDateTimeParser.parseAndFormat("17/03/2015 00:01:01");
        LocalDateTime expirationDate = LocalDateTimeParser.parseAndFormat("07/07/2019 23:59:59");

        List<Order> orders = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        List<Sale> sales = new ArrayList<>();

        Sale sale1 = new Sale(
                1L,
                "Queima de Estoque",
                creationDate,
                expirationDate,
                10.00,
                "Incrível, todos nosso produtos com R$10,00 reais de desconto!!!"
        );

        Sale sale2 = new Sale(
                2L,
                "Liquidação de Natal",
                creationDate,
                expirationDate,
                10.00,
                "Feliz Natal!!!"
        );

        Sale sale3 = new Sale(
                3L,
                "Liquidação de Páscoa",
                creationDate,
                expirationDate,
                10.00,
                "Feliz Páscoa!!!"
        );

        sales.add(sale1);
        sales.add(sale2);
        sales.add(sale3);

        Item item1 = new Item(
                1L,
                "Headset Gamer",
                creationDate,
                expirationDate,
                249.90,
                "Headset Gamer HyperX Cloud Stinger",
                sales
        );

        Item item2 = new Item(
                2L,
                "Smartphone Samsung Galaxy S42",
                creationDate,
                expirationDate,
                249.90,
                "Este celular é de outra galáxia!!!",
                sales
        );

        items.add(item1);
        items.add(item2);


        String cpf1 = "168.329.311-82";
        String cpf2 = "867.935.654-94";
        String cpf3 = "252.526.234-49";
        String cpf4 = "652.111.189-28";

        Order order1 = new Order(1L, cpf1, items, 6000.00);
        Order order2 = new Order(2L, cpf2, items, 485.55);
        Order order3 = new Order(3L, cpf3, items, 135.45);
        Order order4 = new Order(4L, cpf4, items, 800.45);

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        return orders;
    }

}