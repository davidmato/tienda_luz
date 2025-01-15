package com.example.tiendaluz;

import com.example.tiendaluz.dto.CrearPedidoDTO;
import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.*;
import com.example.tiendaluz.services.PedidoServices;
import com.example.tiendaluz.services.ProductoServices;
import com.example.tiendaluz.services.TipoTallaServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoIntegrationTest {

    @Mock
    private ClienteRepositorio clienteRepositorio;
    @Mock
    private TipoPagoRepositorio tipoPagoRepositorio;
    @Mock
    private ProductoRepositorio productoRepositorio;
    @Mock
    private PedidoRepositorio pedidoRepositorio;

    @InjectMocks
    private PedidoServices pedidoServices;




    @Test
    void crearPedidoConFechaAnterior() {
        //GIVEN
        CrearPedidoDTO pedidoDTO = new CrearPedidoDTO();
        pedidoDTO.setPrecio(100.0);
        pedidoDTO.setFecha(LocalDate.now().minusDays(1));
        pedidoDTO.setIdCliente(1);
        pedidoDTO.setIdTipoPago(1);
        pedidoDTO.setIdProducto(Arrays.asList(1, 2));

        //WHEN && THEN
        assertThrows(IllegalArgumentException.class, () -> {
            pedidoServices.crearPedido(pedidoDTO);
        });
    }

}
