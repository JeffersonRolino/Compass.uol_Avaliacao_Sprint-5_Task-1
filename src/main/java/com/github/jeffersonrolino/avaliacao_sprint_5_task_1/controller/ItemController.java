package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.controller;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.dtos.ItemDTO;
import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/item"))
public class ItemController {

    @Autowired
    private ItemService ItemService;

    @GetMapping
    public List<ItemDTO> returnAllItems(){
        return ItemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO>  returnItemById(@PathVariable() Long id){
        return ItemService.getItemById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDTO> deleteItemById(@PathVariable Long id){
        return ItemService.removeItemById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateItemById(
            @RequestBody ItemDTO ItemDTO, @PathVariable("id") Long id)
    {
        boolean isSave = ItemService.partialUpdateNewItem(ItemDTO, id);

        if(isSave){
            return ResponseEntity.status(HttpStatus.OK).body("Pedido atualizado com sucesso!");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Desculpe, pedido não foi atualizado!");
        }
    }

}
