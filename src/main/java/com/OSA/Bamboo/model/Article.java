package com.OSA.Bamboo.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 10)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String imageName;

    private Long sellerId;

    public Article(String name, String description, double price, String imageName, Long sellerId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
        this.sellerId = sellerId;
    }
}
