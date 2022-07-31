package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.SaleDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemService itemService;

    @BeforeEach
    void setUp(){
        itemService = new ItemService(itemRepository);
    }

    LocalDateTime creationDate = LocalDateTimeParser.parseAndFormat("26/01/2019 00:01:01");
    LocalDateTime expirationDate = LocalDateTimeParser.parseAndFormat("31/12/2022 23:59:59");



    @Test
    void shoutGetAllItems() {
        //GIVEN
        List<Item> items = items();
        Mockito.when(itemRepository.findAll()).thenReturn(items);

        //WHEN
        List<ItemDTO> itemDTOS = itemService.getAllItems();

        //THEN
        for (ItemDTO itemDTO : itemDTOS) {
            assertEquals(itemDTO.getClass().getSimpleName(), ItemDTO.class.getSimpleName());
        }
    }

    @Test
    void shoulNotGetAllItems(){
        //GIVEN
        Mockito.when(itemService.getAllItems()).thenThrow(RuntimeException.class);

        //THEN
        try{
            itemService.getAllItems();
        } catch (RuntimeException exception){
            assertEquals(exception.getClass().getSimpleName(),
                    RuntimeException.class.getSimpleName());
        }
    }

    @Test
    void getItemById() {
        //GIVEN
        Item item = items().get(0);
        Long id = item.getId();
        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        //WHEN
        ResponseEntity<ItemDTO> responseEntity = itemService.getItemById(id);

        //THEN
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCodeValue(), 200);
        if(Optional.of(item).isPresent()){
            assertEquals(responseEntity.getBody().getClass().getSimpleName(),
                    ItemDTO.class.getSimpleName());
        }
    }

    @Test
    void shouldNotGetItemById() {
        //GIVEN
        Mockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //WHEN
        ResponseEntity<ItemDTO> responseEntity = itemService.getItemById(Mockito.anyLong());

        //THEN
        if(Optional.empty().isEmpty()){
            assertEquals(responseEntity.getStatusCodeValue(), 404);
        }
    }

    @Test
    void shouldRemoveItemById() {
        //GIVEN
        Item item = items().get(0);
        Long id = item.getId();
        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        Optional<Item> optionalItem = itemRepository.findById(id);

        ResponseEntity<ItemDTO> responseEntity = itemService.removeItemById(id);

        if(optionalItem.isPresent()){
            verify(itemRepository).deleteById(id);
            assertEquals(responseEntity.getStatusCodeValue(), 200);
        }
    }

    @Test
    void shouldNotRemoveItemById() {
        //GIVEN
        Mockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Optional<Item> optionalItem = itemRepository.findById(Mockito.anyLong());

        ResponseEntity<ItemDTO> responseEntity = itemService.removeItemById(Mockito.anyLong());

        if(optionalItem.isEmpty()){
            assertEquals(responseEntity.getStatusCodeValue(), 404);
        }
    }

    @Test
    void partialUpdateNewItem() {
    }

    public List<SaleDTO> saleDTOS(){
        List<SaleDTO> saleDTOS = new ArrayList<>();
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

        saleDTOS.add(saleDTO1);
        saleDTOS.add(saleDTO2);

        return saleDTOS;
    }
    public List<Sale> sales(){
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
                "Promoção de Natal",
                creationDate,
                expirationDate,
                20.00,
                "Incrível, todos nosso produtos com R$20,00 reais de desconto!!!"
        );

        sales.add(sale1);
        sales.add(sale2);

        return sales;
    }

    public List<ItemDTO> itemDTOS(){

        List<ItemDTO> itemDTOS = new ArrayList<>();
        List<SaleDTO> salesDTOS = saleDTOS();

        ItemDTO itemDTO1 = new ItemDTO(
                1L,
                "Headset Gamer",
                "22/11/2019 07:06:45",
                "16/07/2022 16:24:58",
                249.90,
                "Headset Gamer HyperX Cloud Stinger",
                salesDTOS
        );

        ItemDTO itemDTO2 = new ItemDTO(
                2L,
                "HD SSD",
                "13/01/2017 06:10:13",
                "22/03/2022 16:24:58",
                179.90,
                "SSD Kingston A400, 240GB, SATA, Leitura 500MB/s, Gravação 350MB/s - SA400S37/240G",
                salesDTOS
        );

        itemDTOS.add(itemDTO1);
        itemDTOS.add(itemDTO2);

        return itemDTOS;
    }

    public List<Item> items(){
        List<Item> items = new ArrayList<>();
        List<Sale> sales = sales();

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
                "HD SSD",
                creationDate,
                expirationDate,
                179.90,
                "SSD Kingston A400, 240GB, SATA, Leitura 500MB/s, Gravação 350MB/s - SA400S37/240G",
                sales
        );

        items.add(item1);
        items.add(item2);

        return items;
    }

}