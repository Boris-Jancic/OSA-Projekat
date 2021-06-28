package com.OSA.Bamboo.dto;

import com.OSA.Bamboo.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    private Long id;
    @NotBlank(message = "LastName is mandatory")
    private String username;
    @NotBlank(message = "LastName is mandatory")
    private String password;
    @NotBlank(message = "LastName is mandatory")
    private String name;
    @NotBlank(message = "LastName is mandatory")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean blocked;
}
