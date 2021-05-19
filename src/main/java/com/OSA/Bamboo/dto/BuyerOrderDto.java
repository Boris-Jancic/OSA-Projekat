package com.OSA.Bamboo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyerOrderDto {

    private boolean delivered;
    private int grade;
    private String comment;
    private String username;
    private boolean anonymousComment;
    private boolean archivedComment;
}
