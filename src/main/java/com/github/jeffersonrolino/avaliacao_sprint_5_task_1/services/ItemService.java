package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Item;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers.LocalDateTimeParser;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository ItemRepository;


    public ItemService(){}

    public ItemService(ItemRepository ItemRepository){
        this.ItemRepository = ItemRepository;
    }


    public List<ItemDTO> getAllItems(){
        try{
            List<Item> Items = ItemRepository.findAll();
            List<ItemDTO> ItemDTOS = new ArrayList<>();
            for(Item Item : Items){
                ItemDTOS.add(new ItemDTO(Item));
            }
            return ItemDTOS;
        } catch (RuntimeException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public ResponseEntity<ItemDTO> getItemById(Long id){
        Optional<Item> Item = ItemRepository.findById(id);
        if(Item.isPresent()){
            return ResponseEntity.ok(new ItemDTO(Item.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ItemDTO> removeItemById(Long id){
        Optional<Item> Item = ItemRepository.findById(id);
        if(Item.isPresent()){
            ItemRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public boolean partialUpdateNewItem(ItemDTO ItemDTO, Long id) throws RuntimeException{
        Item Item = ItemRepository.findById(id).orElseThrow(RuntimeException::new);

        if(ItemDTO.getNome() != null){
            Item.setName(ItemDTO.getNome());
        }
        if(ItemDTO.getDataDeCriacao() != null){
            Item.setCreationDate(LocalDateTimeParser.parseAndFormat(ItemDTO.getDataDeCriacao()));
        }
        if(ItemDTO.getDataDeValidade() != null){
            Item.setExpirationDate(LocalDateTimeParser.parseAndFormat(ItemDTO.getDataDeValidade()));
        }
        if(ItemDTO.getValor() > 0){
            Item.setPrice(ItemDTO.getValor());
        }
        if(ItemDTO.getDescricao() != null){
            Item.setDescription(ItemDTO.getDescricao());
        }

        ItemRepository.save(Item);
        return true;
    }
}
