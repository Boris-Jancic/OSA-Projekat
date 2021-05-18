package com.OSA.Bamboo.dto;

import com.OSA.Bamboo.model.enums.UserRole;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserDto {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "LastName is mandatory")
    private String lastName;

    private UserRole role;

    private boolean blocked;
}
