package com.example.tiendaluz;

import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.services.ClienteServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteServices clienteServices;



    @Test
    void  testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setDni("1234567A");
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("1234567");
        cliente.setEmail("juanperez@safaryes.es");
        Cliente clienteGuardado = clienteServices.guardar(cliente);
        System.out.println(clienteGuardado.toString());
    }

    @Test
    void testEditarCliente(){
        Cliente cliente = clienteServices.getById(6);
        cliente.setNombre("Felipe");
        cliente.setApellido("Perez");
        cliente.setDni("1234097A");
        cliente.setDireccion("Calle 823");
        cliente.setTelefono("1234567");
        cliente.setEmail("juanperez@safaryes.es");
        Cliente clienteGuardado = clienteServices.guardar(cliente);
        System.out.println(clienteGuardado.toString());

    }

    @Test
    void testEliminar(){
        clienteServices.eliminarPorID(6);
    }

    @Test
    void testBuscarClientes(){
        List<Cliente> clientes = clienteServices.getAll();
        for (Cliente c : clientes){
            System.out.println(c.getNombre());
        }
    }



}
