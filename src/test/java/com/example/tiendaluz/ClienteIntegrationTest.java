package com.example.tiendaluz;

import com.example.tiendaluz.exceptions.ClienteReferencedException;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.modelos.Pedido;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoPago;
import com.example.tiendaluz.repositorios.ClienteRepositorio;
import com.example.tiendaluz.services.ClienteServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClienteIntegrationTest {

    @InjectMocks
    private ClienteServices clienteServices; //REAL

    @Mock
    private ClienteRepositorio clienteRepositorio; //SIMULADO

    /**
     * Test de integración para el metodo findAll de ClienteServices
     */
    @Test
    public void TestFindAllIntegration() {
        //GIVEN
        List<Cliente>clientesPorDefecto = new ArrayList<>();
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan");
        cliente1.setApellido("Perez");
        cliente1.setDni("12344346A");
        cliente1.setDireccion("Calle 123");
        cliente1.setTelefono("1234567");
        cliente1.setEmail("juanperez@safaryes.es");

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Felipe");
        cliente2.setApellido("Rodriguez");
        cliente2.setDni("1234097A");
        cliente2.setDireccion("Calle 823");
        cliente2.setTelefono("1234567");
        cliente2.setEmail("feliperodriguez@safareyes.es");

        clientesPorDefecto.add(cliente1);
        clientesPorDefecto.add(cliente2);

        Mockito.when(clienteRepositorio.findAll()).thenReturn(clientesPorDefecto);


        //WHEN
        List<Cliente>clientes= clienteServices.FindAll();

        //THEN
        assertEquals(2, clientes.size());
        Mockito.verify(clienteRepositorio, Mockito.times(1)).findAll();

    }

    @Test
    public void testBuscarPorIdIntegration() throws Exception {
        //GIVEN
        Mockito.when(clienteRepositorio.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));

        //THEN && WHEN
        assertThrows(Exception.class, () -> clienteServices.getById(3));
        Mockito.verify(clienteRepositorio, Mockito.times(1)).findById(3);
    }



    @Test
    public void eliminarClienteReferenciado() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Cliente Test");
        cliente.setApellido("Apellido Test");
        cliente.setDireccion("Direccion Test");
        cliente.setDni("12345678");
        cliente.setEmail("cliente@test.com");
        cliente.setTelefono("123456789");

        TipoPago tipoPago = new TipoPago();
        tipoPago.setNombre("Tipo de Pago Referenciado");

        Producto producto = new Producto();
        producto.setNombre("Producto Referenciado");
        producto.setDescripcion("Descripcion Referenciada");
        producto.setColor("Color Referenciado");
        producto.setUnidades(10);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTipoPago(tipoPago);
        pedido.setProductos(Set.of(producto));
        pedido.setFecha(LocalDate.parse("2021-01-01"));
        pedido.setPrecio(100.0);
        Mockito.when(clienteRepositorio.findById(1)).thenReturn(Optional.of(cliente));
        Mockito.doThrow(DataIntegrityViolationException.class).when(clienteRepositorio).deleteById(1);

        Exception exception = assertThrows(ClienteReferencedException.class, () -> {
            clienteServices.eliminarCliente(1);
        });

        assertEquals("No se puede eliminar el Cliente: está referenciado por otras entidades", exception.getMessage());
    }

}
