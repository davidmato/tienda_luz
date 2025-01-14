package com.example.tiendaluz;

import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.repositorios.ClienteRepositorio;
import com.example.tiendaluz.services.ClienteServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClienteIntegrationTest {

    @InjectMocks
    private ClienteServices clienteServices; //REAL

    @Mock
    private ClienteRepositorio clienteRepositorio; //SIMULADO

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
}
