package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {

    @Test
    void shouldNotReturnNullItemDTO(){
        ItemDTO itemDTO1 = new ItemDTO();
        assertNotNull(itemDTO1);
    }

    @Test
    void shouldGetId() {
        Long id = itemDTO.getId();

        assertEquals(id.getClass().getSimpleName(), Long.class.getSimpleName());
        assertEquals(id, 1L);
    }

    @Test
    void shouldGetNome() {
        String nome = itemDTO.getNome();

        assertEquals(nome.getClass().getSimpleName(), String.class.getSimpleName());
        assertEquals(nome, "Headset Gamer");
    }

    @Test
    void shouldGetDataDeCriacao() {
        String dataCriacao = itemDTO.getDataDeCriacao();

        assertEquals(dataCriacao.getClass().getSimpleName(), String.class.getSimpleName());
        assertEquals(dataCriacao, "22/11/2019 07:06:45");
    }

    @Test
    void shouldGetDataDeValidade() {
        String dataValidade = itemDTO.getDataDeValidade();

        assertEquals(dataValidade.getClass().getSimpleName(), String.class.getSimpleName());
        assertEquals(dataValidade, "16/07/2022 16:24:58");
    }

    @Test
    void getValor() {
        double valor = itemDTO.getValor();

        assertEquals(valor, 249.90);
    }

    @Test
    void getDescricao() {
        String descricao = itemDTO.getDescricao();

        assertEquals(descricao.getClass().getSimpleName(), String.class.getSimpleName());
        assertEquals(descricao, "Headset Gamer HyperX Cloud Stinger");
    }

    @Test
    void getOfertas() {
        List<SaleDTO> saleDTOS = itemDTO.getOfertas();

        for(SaleDTO saleDTO : saleDTOS){
            assertEquals(saleDTO.getClass().getSimpleName(), SaleDTO.class.getSimpleName());
        }
    }

    @Test
    void setId() {
        Long id = 2L;

        itemDTO.setId(2L);

        assertEquals(itemDTO.getId(), id);
    }

    @Test
    void setNome() {
        String novoNome = "novoNome";

        itemDTO.setNome(novoNome);

        assertEquals(itemDTO.getNome(), novoNome);
    }

    @Test
    void setDataDeCriacao() {
        String novaData = "01/01/2000 05:06:43";

        itemDTO.setDataDeCriacao(novaData);

        assertEquals(itemDTO.getDataDeCriacao(), novaData);
    }

    @Test
    void setDataDeValidade() {
        String novaData = "01/01/2023 05:06:43";

        itemDTO.setDataDeValidade(novaData);

        assertEquals(itemDTO.getDataDeValidade(), novaData);
    }

    @Test
    void setValor() {
        double novoValor = 123.12;

        itemDTO.setValor(novoValor);

        assertEquals(itemDTO.getValor(), novoValor);
    }

    @Test
    void setDescricao() {
        String novaDescricao = "Nova Descrição";

        itemDTO.setDescricao(novaDescricao);

        assertEquals(itemDTO.getDescricao(), novaDescricao);
    }

    @Test
    void setOfertas() {
        List<SaleDTO> saleDTOS = saleDTOS();

        itemDTO.setOfertas(saleDTOS);

        for (SaleDTO saleDTO : saleDTOS){
            assertEquals(saleDTO.getClass().getSimpleName(), SaleDTO.class.getSimpleName());
        }
    }

    @Test
    void shouldConstrucItemDTOfromItem(){

        ItemDTO itemDTO1 = new ItemDTO(item);

        assertEquals(itemDTO1.getClass().getSimpleName(), ItemDTO.class.getSimpleName());
    }


    //Métodos Utilitários
    LocalDateTime creationDate = LocalDateTimeParser.parseAndFormat("26/01/2019 00:01:01");
    LocalDateTime expirationDate = LocalDateTimeParser.parseAndFormat("31/12/2022 23:59:59");
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

    List<SaleDTO> salesDTOS = saleDTOS();

    SaleDTO saleDTO = new SaleDTO(
            "Queima de Estoque",
            "26/12/2022 00:01:01",
            "31/12/2022 23:59:59",
            10.00,
            "Incrível, todos nosso produtos com R$10,00 reais de desconto!!!"
    );

    ItemDTO itemDTO = new ItemDTO(
            1L,
            "Headset Gamer",
            "22/11/2019 07:06:45",
            "16/07/2022 16:24:58",
            249.90,
            "Headset Gamer HyperX Cloud Stinger",
            salesDTOS
    );

    List<Sale> sales = sales();

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

    Item item = new Item(
            1L,
            "Headset Gamer",
            creationDate,
            expirationDate,
            249.90,
            "Headset Gamer HyperX Cloud Stinger",
            sales
    );




}