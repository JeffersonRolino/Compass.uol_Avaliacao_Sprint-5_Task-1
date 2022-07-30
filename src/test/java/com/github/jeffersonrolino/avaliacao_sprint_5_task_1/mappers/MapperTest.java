package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.mappers;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.OrderDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.SaleDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Order;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {
    LocalDateTime creationDate = LocalDateTimeParser.parseAndFormat("26/01/2019 00:01:01");
    LocalDateTime expirationDate = LocalDateTimeParser.parseAndFormat("31/12/2022 23:59:59");

    @Test
    void itemDTOsToItens() {
        List<ItemDTO> itemDTOS = itemDTOS();
        List<Item> items = Mapper.itemDTOsToItens(itemDTOS);
        for (ItemDTO itemDTO : itemDTOS){
            items.add(new Item(itemDTO));
        }

        for(Item item : items){
            int index = 0;
            Assertions.assertEquals(item.getClass().getSimpleName(), Item.class.getSimpleName());
            Assertions.assertEquals(items.get(index).getId(), itemDTOS.get(index).getId());
            Assertions.assertEquals(items.get(index).getName(), itemDTOS.get(index).getNome());
            Assertions.assertEquals(items.get(index).getDescription(), itemDTOS.get(index).getDescricao());

            index++;
        }

    }

    @Test
    void saleDTOsToSales() {
        List<SaleDTO> saleDTOS = saleDTOS();
        List<Sale> sales = Mapper.saleDTOsToSales(saleDTOS);

        for(SaleDTO saleDTO : saleDTOS){
            sales.add(new Sale(saleDTO));
        }

        for(Sale sale : sales){
            int index = 0;
            Assertions.assertEquals(sale.getClass().getSimpleName(), Sale.class.getSimpleName());
            Assertions.assertEquals(sales.get(index).getId(), saleDTOS.get(index).getId());
        }
    }

    @Test
    void salesToSalesDTO() {
        List<Sale> sales = sales();
        List<SaleDTO> saleDTOS = Mapper.salesToSalesDTO(sales);

        for(Sale sale : sales){
            saleDTOS.add(new SaleDTO(sale));
        }

        for(SaleDTO saleDTO : saleDTOS){
            int index = 0;
            Assertions.assertEquals(saleDTO.getClass().getSimpleName(), SaleDTO.class.getSimpleName());
            Assertions.assertEquals(saleDTOS.get(index).getId(), sales.get(index).getId());
        }
    }

    @Test
    void itensToItensDTO() {
        List<Item> items = items();
        List<ItemDTO> itemDTOS = Mapper.itensToItensDTO(items);

        for(Item item : items){
            itemDTOS.add(new ItemDTO(item));
        }

        for(ItemDTO itemDTO : itemDTOS){
            int index = 0;
            Assertions.assertEquals(itemDTO.getClass().getSimpleName(), ItemDTO.class.getSimpleName());
            Assertions.assertEquals(itemDTOS.get(index).getId(), items.get(index).getId());
        }
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