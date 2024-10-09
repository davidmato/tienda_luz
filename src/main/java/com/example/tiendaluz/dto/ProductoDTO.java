package com.example.tiendaluz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private Integer unidades;
    private String color;
}
