package com.jshoppractice.jshoppractice.controller;

import com.jshoppractice.jshoppractice.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}
