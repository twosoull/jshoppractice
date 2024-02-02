package com.jshoppractice.jshoppractice.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Category {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM", joinColumns = @JoinColumn(name = "category_id"),
                inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();
}
