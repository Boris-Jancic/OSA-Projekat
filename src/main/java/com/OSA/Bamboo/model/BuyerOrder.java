package com.OSA.Bamboo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class BuyerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "buyer_order_id", unique = true, nullable = false)
    private Long id;

    private LocalDate hourlyRate;

    private boolean delivered;

    private int grade;

    private String comment;

    private boolean anonymousComment;

    private boolean archivedComment;

    private String user;
}