package com.example.tiendaluz.dto;

import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoTalla;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CatalogoDTO {
    private Double precio;
    private ProductoDTO producto;
    private TipoTallaDTO tipoTalla;

}