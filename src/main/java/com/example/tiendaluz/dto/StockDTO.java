package com.example.tiendaluz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockDTO {
    private Integer cantidad;
    private ProductoDTO producto;
    private TipoTallaDTO tipoTalla;
}
