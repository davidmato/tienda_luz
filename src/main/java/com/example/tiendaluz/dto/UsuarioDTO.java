package com.example.tiendaluz.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String rol = "CLIENTE"; // Default role


}
