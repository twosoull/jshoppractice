package com.jshoppractice.jshoppractice.controller;

import com.jshoppractice.jshoppractice.domain.Item;
import com.jshoppractice.jshoppractice.form.ItemForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    public final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        log.info("items new");
        model.addAttribute("form",new ItemForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(ItemForm itemform, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/";
        }

        Item item = new Item();
        item.setName(itemform.getName());
        item.setAuthor(itemform.getAuthor());
        item.setIsbn(itemform.getIsbn());
        item.setPrice(itemform.getPrice());
        item.setStockQuantity(itemform.getStockQuantity());

        itemService.saveItem(item);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();

        model.addAttribute("items",items);
        return "items/itemList";
    }
}
