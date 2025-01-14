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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TipoTallaRepositorio tipoTallaRepositorio;

    @Autowired
    private TipoPagoRepositorio tipoPagoRepositorio;
    @Autowired
    private ProductoRepositorio productoRepositorio;

    @BeforeEach
    public void inicializarDatos() {
        Usuario usuario = new Usuario();
        usuario.setRol(Rol.CLIENTE);
        usuario.setUsername("usuario1");
        usuario.setPassword("password1");

        usuarioRepositorio.save(usuario);

        Cliente cliente = new Cliente();
        cliente.setNombre("Cliente 1");
        cliente.setApellido("Apellido 1");
        cliente.setDireccion("Direccion 1");
        cliente.setDni("12345678");
        cliente.setEmail("cliente1@example.com");
        cliente.setTelefono("123456789");
        cliente.setUsuario(usuario);

        clienteRepositorio.save(cliente);

        TipoPago tipoPago = new TipoPago();
        tipoPago.setNombre("Tipo Pago 1");

        tipoPagoRepositorio.save(tipoPago);

        Set<Producto> productos = new HashSet<>();

        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");
        producto1.setColor("Color 1");
        producto1.setDescripcion("Descripcion 1");
        producto1.setUnidades(10);

        Producto producto = new Producto();
        producto.setNombre("Producto 2");
        producto.setColor("Color 2");
        producto.setDescripcion("Descripcion 2");
        producto.setUnidades(15);

        productos.add(producto1);
        productos.add(producto);

        productoRepositorio.save(producto);
        productoRepositorio.save(producto1);

//        TipoTalla tipoTalla = new TipoTalla();
//        tipoTalla.setNombre("Talla 1");
//        tipoTalla.setProducto(producto1);
//
//        tipoTallaRepositorio.save(tipoTalla);

        Pedido pedido = new Pedido();
        pedido.setPrecio(200.0);
        pedido.setFecha(LocalDate.parse("2021-10-10"));
        pedido.setTipoPago(tipoPago);
        pedido.setCliente(cliente);
        pedido.setProductos(productos);

        pedidoRepositorio.save(pedido);

        DetallesVenta detallesVenta = new DetallesVenta();
        detallesVenta.setId(1);
        detallesVenta.setCantidad(2);
        detallesVenta.setPrecioUnitario(100.0);
        detallesVenta.setProducto(producto);
        detallesVenta.setPedido(pedido);

        detallesVentaRepositorio.save(detallesVenta);
    }

    @Test
    public void testTotalPedidosCuandoClienteExiste() {
        // GIVEN

        // WHEN
        List<DetallesVentaDTO> result = detallesVentaServices.getAllDTOByIdCliente(1);

        // THEN
        assertNotNull(result);
    }

    @Test
    public  void find(){
        DetallesVenta detallesVenta = detallesVentaServices.getById(1);

        assertEquals(2, detallesVenta.getCantidad());
    }

    @Test
    public void findAll(){
        List<DetallesVenta> detallesVentas = detallesVentaServices.getAll();
        assertEquals(1, detallesVentas.size());
    }

}
