package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.mappers;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.SaleDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static List<Item> itemDTOsToItens(List<@Valid ItemDTO> itemDTOS){
        List<@Valid Item> itens = new ArrayList<>();
        for (ItemDTO itemDTO : itemDTOS){
            itens.add(new Item(itemDTO));
        }
        return itens;
    }

    public static List< Sale> saleDTOsToSales(List<@Valid SaleDTO> salesDTOS){
        List<@Valid Sale> sales = new ArrayList<>();
        for (SaleDTO saleDTOS : salesDTOS){
            sales.add(new Sale(saleDTOS));
        }
        return sales;
    }

    public static List<SaleDTO> salesToSalesDTO(List<@Valid Sale> sales){
        List<@Valid SaleDTO> saleDTOS = new ArrayList<>();
        for(Sale sale : sales){
            SaleDTO saleDTO = new SaleDTO(sale);
            saleDTOS.add(saleDTO);
        }
        return saleDTOS;
    }

    public static List<@Valid ItemDTO> itensToItensDTO(List<@Valid Item> itens){
        List<@Valid ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : itens){
            itemDTOS.add(new ItemDTO(item));
        }
        return itemDTOS;
    }

}
