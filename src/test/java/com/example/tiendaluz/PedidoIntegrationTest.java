package com.example.tiendaluz;

import com.example.tiendaluz.dto.CrearPedidoDTO;
import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.*;
import com.example.tiendaluz.services.PedidoServices;
import com.example.tiendaluz.services.ProductoServices;
import com.example.tiendaluz.services.TipoTallaServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoIntegrationTest {

    @Mock
    private ClienteRepositorio clienteRepositorio;
    @Mock
    private TipoPagoRepositorio tipoPagoRepositorio;
    @Mock
    private ProductoRepositorio productoRepositorio;
    @Mock
    private PedidoRepositorio pedidoRepositorio;

    @InjectMocks
    private PedidoServices pedidoServices;



    @Test
    void crearPedidoConDatosValidos() {
        CrearPedidoDTO pedidoDTO = new CrearPedidoDTO();
        pedidoDTO.setPrecio(100.0);
        pedidoDTO.setFecha(LocalDate.now().plusDays(1));
        pedidoDTO.setIdCliente(1);
        pedidoDTO.setIdTipoPago(1);
        pedidoDTO.setIdProducto(Arrays.asList(1, 2));

        Cliente cliente = new Cliente();
        cliente.setId(1);
        TipoPago tipoPago = new TipoPago();
        tipoPago.setId(1);
        Producto producto1 = new Producto();
        producto1.setId(1);
        Producto producto2 = new Producto();
        producto2.setId(2);

        when(clienteRepositorio.getById(1)).thenReturn(cliente);
        when(tipoPagoRepositorio.getById(1)).thenReturn(tipoPago);
        when(productoRepositorio.getReferenceById(1)).thenReturn(producto1);
        when(productoRepositorio.getReferenceById(2)).thenReturn(producto2);
        when(pedidoRepositorio.save(any(Pedido.class))).thenReturn(new Pedido());

        Pedido result = pedidoServices.crearPedido(pedidoDTO);

        assertNotNull(result);
        assertEquals(100.0, result.getPrecio());
        assertEquals(LocalDate.now().plusDays(1), result.getFecha());
        assertEquals(cliente, result.getCliente());
        assertEquals(tipoPago, result.getTipoPago());
        assertTrue(result.getProductos().contains(producto1));
        assertTrue(result.getProductos().contains(producto2));
    }

//    @Test
//    void crearPedidoConPrecioNegativo() {
//        CrearPedidoDTO pedidoDTO = new CrearPedidoDTO();
//        pedidoDTO.setPrecio(-100.0);
//        pedidoDTO.setFecha(LocalDate.now().plusDays(1));
//        pedidoDTO.setIdCliente(1);
//        pedidoDTO.setIdTipoPago(1);
//        pedidoDTO.setIdProducto(Arrays.asList(1, 2));
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            pedidoServices.crearPedido(pedidoDTO);
//        });
//    }
//
//    @Test
//    void crearPedidoConFechaAnterior() {
//        CrearPedidoDTO pedidoDTO = new CrearPedidoDTO();
//        pedidoDTO.setPrecio(100.0);
//        pedidoDTO.setFecha(LocalDate.now().minusDays(1));
//        pedidoDTO.setIdCliente(1);
//        pedidoDTO.setIdTipoPago(1);
//        pedidoDTO.setIdProducto(Arrays.asList(1, 2));
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            pedidoServices.crearPedido(pedidoDTO);
//        });
//    }

}
