package com.example.tiendaluz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CrearPedidoDTO {
    private Integer idCliente;
    private Integer idProducto;
    private Integer idTipoPago;
    private LocalDate fecha;
    private Double precioUnitario;
}
