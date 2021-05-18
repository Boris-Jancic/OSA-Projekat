package com.OSA.Bamboo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class   Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Discount percent is required")
    private int discountPercent;

    @NotBlank(message = "From date is required")
    private LocalDate fromDate;

    @NotBlank(message = "Till date is required")
    private LocalDate tillDate;

    @NotBlank(message = "Discount reason is required")
    private String description;
}
