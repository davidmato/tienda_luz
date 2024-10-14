package com.example.tiendaluz.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoTallaDTO {
    private String nombre;
    private ProductoDTO producto;
}
