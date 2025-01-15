package com.example.tiendaluz;

import com.example.tiendaluz.enumerados.Rol;
import com.example.tiendaluz.exceptions.ClienteReferencedException;
import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.*;
import com.example.tiendaluz.services.ClienteServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClienteTest {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteServices clienteServices;

    @Autowired
    private TipoPagoRepositorio tipoPagoRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @BeforeEach
    public void inicializarDatos() {



        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Cliente 1");
        cliente1.setApellido("Apellido 1");
        cliente1.setDireccion("Direccion 1");
        cliente1.setDni("12345678");
        cliente1.setEmail("cliente1@example.com");
        cliente1.setTelefono("123456789");
        clienteRepositorio.save(cliente1);


        Cliente cliente = new Cliente();
        cliente.setNombre("Cliente 2");
        cliente.setApellido("Apellido 2");
        cliente.setDireccion("Direccion 2");
        cliente.setDni("12345678");
        cliente.setEmail("cliente1@example.com");
        cliente.setTelefono("123456789");
        clienteRepositorio.save(cliente);

        TipoPago tipoPago = new TipoPago();
        tipoPago.setNombre("Tipo Pago 1");
        tipoPagoRepositorio.save(tipoPago);

        Producto producto = new Producto();
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripcion 1");
        producto.setColor("Color 1");
        producto.setUnidades(10);
        productoRepositorio.save(producto);

        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");
        producto1.setDescripcion("Descripcion 1");
        producto1.setColor("Color 1");
        producto1.setUnidades(10);
        productoRepositorio.save(producto1);

    }

    /**
     * Test para eliminar un cliente existente
     */
    @Test
    public void testEliminarClienteExistente() {
        Integer id = 1;

        String mensaje = clienteServices.eliminarCliente(id);

        assertEquals("Cliente eliminado correctamente", mensaje);
        assertFalse(clienteRepositorio.findById(id).isPresent());
    }

    /**
     * Test para eliminar un cliente no existente
     */
    @Test
    public void testEliminarClienteNoExistente() {
        Integer id = 999;

        String mensaje = clienteServices.eliminarCliente(id);

        assertEquals("El cliente con el id indicado no existe", mensaje);
    }



}
