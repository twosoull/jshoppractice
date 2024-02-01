package com.jshoppractice.jshoppractice.domain;

import jakarta.persistence.*;

@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private Integer price;
    private Integer StockQuantity;

    @Enumerated(EnumType.STRING)
    private Dtype dtype;
    private String artist;
    private String etc;
    private String author;
    private String isbn;
    private String director;
    private String act;


}
