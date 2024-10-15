package com.example.tiendaluz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetallesVentaDTO {
    private Integer cantidad;
    private Double precioUnitario;
    private ProductoDTO producto;
    private PedidoDTO pedido;
}
