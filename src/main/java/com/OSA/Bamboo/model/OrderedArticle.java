package com.OSA.Bamboo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderedArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 10)
    private Long id;

    @NotBlank(message = "HourlyRate is mandatory")
    private LocalDate hourlyRate;

    @NotBlank(message = "Delivered is mandatory")
    private boolean delivered;

    @NotBlank(message = "Grade is mandatory")
    private int grade;

    @NotBlank(message = "Comment is mandatory")
    private String comment;

    @NotBlank(message = "Anonymous comment is mandatory")
    private boolean anonymousComment;

    @NotBlank(message = "Archived comment is mandatory")
    private boolean archivedComment;
}

