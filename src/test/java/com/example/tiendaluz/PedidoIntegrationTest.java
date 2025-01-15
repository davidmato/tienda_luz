package com.example.tiendaluz;

import com.example.tiendaluz.repositorios.PedidoRepositorio;
import com.example.tiendaluz.services.PedidoServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PedidoIntegrationTest {

    @InjectMocks
    private PedidoServices pedidoServices; //REAL

    @Mock
    private PedidoRepositorio pedidoRepositorio; //SIMULADO

    @Test
    public void testCrearPedido(){
        //GIVEN
    }


}
