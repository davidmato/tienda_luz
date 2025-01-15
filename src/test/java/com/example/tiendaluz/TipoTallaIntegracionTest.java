package com.example.tiendaluz;


import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.repositorios.TipoTallaRepositorio;
import com.example.tiendaluz.services.ProductoServices;
import com.example.tiendaluz.services.TipoTallaServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TipoTallaIntegracionTest {

    @InjectMocks
    private TipoTallaServices tipoTallaServices;

    @Mock
    private TipoTallaRepositorio tipoTallaRepositorio;

    @Mock
    private ProductoServices productoServices;


    @Test
    void cantidadProductoCuandoProductoNoExiste() {
        when(productoServices.getById(1)).thenReturn(null);

        String result = tipoTallaServices.cantidadProducto(1, "M");

        assertEquals("No hay productos disponibles", result);
    }


}
