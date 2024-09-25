package com.example.tiendaluz;

import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TiendaLuzApplicationTests {
    @Autowired
    private ClienteRepositorio clienteRepositorio;


    @Test
    void contextLoads() {

        List<Cliente> clientes = clienteRepositorio.findAll();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

    }

}
