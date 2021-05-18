package com.OSA.Bamboo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ArticleDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;
    @NotNull
    private Double price;

    public ArticleDto(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
