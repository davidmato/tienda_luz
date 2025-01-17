package com.example.tiendaluz;


import com.example.tiendaluz.dto.DetallesVentaDTO;
import com.example.tiendaluz.enumerados.Rol;
import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.*;
import com.example.tiendaluz.services.DetallesVentaServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class DetallesVentaTest {

    @Autowired
    private DetallesVentaServices detallesVentaServices;

    @Autowired
    private DetallesVentaRepositorio detallesVentaRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private TipoPagoRepositorio tipoPagoRepositorio;
    @Autowired
    private ProductoRepositorio productoRepositorio;

    @BeforeEach
    public void inicializarDatos() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Cliente 1");
        cliente.setApellido("Apellido 1");
        cliente.setDireccion("Direccion 1");
        cliente.setEmail("asdasd@gmail.com");
        cliente.setDni("12345678");
        cliente.setTelefono("123456789");
        clienteRepositorio.save(cliente);
        TipoPago tipoPago = new TipoPago();
        tipoPago.setNombre("Tipo Pago 1");
        tipoPagoRepositorio.save(tipoPago);
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setPrecio(200.0);
        pedido.setFecha(LocalDate.parse("2021-10-10"));
        pedido.setPrecio(200.0);
        pedido.setTipoPago(tipoPago);
        DetallesVenta detallesVenta = new DetallesVenta();
        detallesVenta.setCantidad(2);
        detallesVenta.setPrecioUnitario(100.0);
        detallesVenta.setPedido(pedido);
        Producto producto = new Producto();
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripcion 1");
        producto.setUnidades(10);
        producto.setColor("Color 1");
        productoRepositorio.save(producto);
        detallesVenta.setProducto(producto);

        detallesVentaRepositorio.save(detallesVenta);
        pedidoRepositorio.save(pedido);
    }

    /**
     * Test para obtener todos los detalles de venta de un cliente
     */
    @Test
    public void testTotalPedidosCuandoClienteExiste() {
        // GIVEN

        // WHEN
        List<DetallesVentaDTO> result = detallesVentaServices.getAllDTOByIdCliente(1);

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getCantidad());
        assertEquals(100.0, result.get(0).getPrecioUnitario());
    }


    /**
     * Test para obtener todos los detalles de venta de un cliente cuando el cliente no existe
     */
    @Test
    public void getAllDTOByIdClienteCuandoClienteNoExiste() {
        // GIVEN

        // WHEN
        List<DetallesVentaDTO> result = detallesVentaServices.getAllDTOByIdCliente(753);

        // THEN
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
