package com.example.tiendaluz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private String telefono;
    private String email;

}
