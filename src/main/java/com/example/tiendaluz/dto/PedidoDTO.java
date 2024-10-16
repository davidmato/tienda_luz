package com.example.tiendaluz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {
    private Double precio;
    private LocalDate fecha;
    private TipoPagoDTO idtipoPago;
    private ClienteDTO idcliente;


}
