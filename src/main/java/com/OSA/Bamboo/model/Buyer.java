package com.OSA.Bamboo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
public class Buyer extends User {

    @NotBlank(message = "Address is mandatory")
    @Column(length = 64)
    private String address;
}
