package com.example.tiendaluz;

import com.example.tiendaluz.dto.DetallesVentaDTO;
import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.*;
import com.example.tiendaluz.services.DetallesVentaServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DetallesVentaIntegrationTest {

    @Mock
    private DetallesVentaRepositorio detallesVentaRepositorio;

    @Mock
    private PedidoRepositorio pedidoRepositorio;

    @InjectMocks
    private DetallesVentaServices detallesVentaServices;


    /**
     * TEST 3 INTEGRACION
     */

    /**
     * Test de integración para el metodo getAllDTOByIdCliente de DetallesVentaServices
     */

    @Test
    void testTotalPedidosCuandoClienteExiste() {

        //GIVEN
        Integer idCliente = 1;
        List<DetallesVenta> detallesVentas = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        DetallesVenta detallesVenta = new DetallesVenta();
        Pedido pedido = new Pedido();
        Producto producto = new Producto();
        TipoPago tipoPago = new TipoPago();
        Cliente cliente = new Cliente();

        detallesVenta.setCantidad(2);
        detallesVenta.setPrecioUnitario(100.0);
        detallesVenta.setProducto(producto);
        detallesVentas.add(detallesVenta);

        pedido.setPrecio(200.0);
        pedido.setFecha(LocalDate.parse("2021-10-10"));
        pedido.setTipoPago(tipoPago);
        pedido.setCliente(cliente);
        pedidos.add(pedido);

        when(detallesVentaRepositorio.findByPedido_Cliente_Id(idCliente)).thenReturn(detallesVentas);
        when(pedidoRepositorio.findAllByClienteId(idCliente)).thenReturn(pedidos);

        //WHEN

        List<DetallesVentaDTO> result = detallesVentaServices.getAllDTOByIdCliente(idCliente);


        //THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getCantidad());
        assertEquals(100.0, result.get(0).getPrecioUnitario());
    }

}
