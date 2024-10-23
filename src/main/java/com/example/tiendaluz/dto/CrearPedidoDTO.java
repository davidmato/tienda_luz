package com.example.tiendaluz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CrearPedidoDTO {
    private Integer idCliente;
    private List<Integer> idProducto;
    private Integer idTipoPago;
    private LocalDate fecha;
    private Double precio;
}
