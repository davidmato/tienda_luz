package com.example.tiendaluz;

import com.example.tiendaluz.dto.StockDTO;
import com.example.tiendaluz.modelos.Stock;
import com.example.tiendaluz.repositorios.ProductoRepositorio;
import com.example.tiendaluz.repositorios.StockRepositorio;
import com.example.tiendaluz.repositorios.TipoTallaRepositorio;
import com.example.tiendaluz.services.StockServices;
import com.example.tiendaluz.services.TipoTallaServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class StockIntegrationTest {

    @InjectMocks
    private StockServices stockServices;

    @Mock
    private StockRepositorio stockRepositorio;


    /**
     * Test para modificar stock con id invalido
     */
    @Test
    public void modificarStockConIdInvalido() {
        //GIVEN
        StockDTO dto = new StockDTO();
        dto.setCantidad(10);

        when(stockRepositorio.findById(1)).thenReturn(Optional.empty());


        //WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stockServices.modificarStock(dto, 1);
        });

        //THEN
        assertEquals("ID invalido", exception.getMessage());
        verify(stockRepositorio, never()).save(any(Stock.class));
    }

}
