package com.example.tiendaluz;

import com.example.tiendaluz.dto.CrearPedidoDTO;
import com.example.tiendaluz.enumerados.Rol;
import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.*;
import com.example.tiendaluz.services.PedidoServices;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class PedidoTest {

    @Autowired
    private PedidoServices pedidoServices;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private TipoPagoRepositorio tipoPagoRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @BeforeEach
    public void inicializarDatos() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setRol(Rol.CLIENTE);
        usuario.setUsername("usuario1");
        usuario.setPassword("password1");
        usuarioRepositorio.save(usuario);

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Cliente 1");
        cliente.setApellido("Apellido 1");
        cliente.setDireccion("Direccion 1");
        cliente.setDni("12345678");
        cliente.setEmail("cliente1@example.com");
        cliente.setTelefono("123456789");
        cliente.setUsuario(usuario);
        clienteRepositorio.save(cliente);

        TipoPago tipoPago = new TipoPago();
        tipoPago.setId(1);
        tipoPago.setNombre("Tipo Pago 1");
        tipoPagoRepositorio.save(tipoPago);

        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripcion 1");
        producto.setColor("Color 1");
        producto.setUnidades(10);
        productoRepositorio.save(producto);
    }

    @Test
    public void testCrearPedidoConDatosValidos() {
        CrearPedidoDTO pedidoDTO = new CrearPedidoDTO();
        pedidoDTO.setPrecio(200.0);
        pedidoDTO.setFecha(LocalDate.now().plusDays(1));
        pedidoDTO.setIdCliente(1);
        pedidoDTO.setIdTipoPago(1);
        List<Integer> idProductos = new ArrayList<>();
        idProductos.add(1);
        pedidoDTO.setIdProducto(idProductos);

        Pedido pedido = pedidoServices.crearPedido(pedidoDTO);

        assertNotNull(pedido);
        assertEquals(200.0, pedido.getPrecio());
        assertEquals(LocalDate.now().plusDays(1), pedido.getFecha());
        assertEquals(1, pedido.getCliente().getId());
        assertEquals(1, pedido.getTipoPago().getId());
        assertEquals(1, pedido.getProductos().size());
    }

    @Test
    public void testCrearPedidoConPrecioNegativo() {
        CrearPedidoDTO pedidoDTO = new CrearPedidoDTO();
        pedidoDTO.setPrecio(-200.0);
        pedidoDTO.setFecha(LocalDate.now().plusDays(1));
        pedidoDTO.setIdCliente(1);
        pedidoDTO.setIdTipoPago(1);
        List<Integer> idProductos = new ArrayList<>();
        idProductos.add(1);
        pedidoDTO.setIdProducto(idProductos);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pedidoServices.crearPedido(pedidoDTO);
        });

        assertEquals("El precio no puede ser negativo o igual 0", exception.getMessage());
    }

    @Test
    public void testCrearPedidoConFechaPasada() {
        CrearPedidoDTO pedidoDTO = new CrearPedidoDTO();
        pedidoDTO.setPrecio(200.0);
        pedidoDTO.setFecha(LocalDate.now().minusDays(1));
        pedidoDTO.setIdCliente(1);
        pedidoDTO.setIdTipoPago(1);
        List<Integer> idProductos = new ArrayList<>();
        idProductos.add(1);
        pedidoDTO.setIdProducto(idProductos);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pedidoServices.crearPedido(pedidoDTO);
        });

        assertEquals("La fecha no puede ser anterior a la actual", exception.getMessage());
    }

}
