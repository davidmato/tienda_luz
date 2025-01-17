package com.example.tiendaluz;

import com.example.tiendaluz.dto.StockDTO;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.Stock;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.repositorios.StockRepositorio;
import com.example.tiendaluz.services.StockServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class StockTest {

    @Autowired
    private StockRepositorio stockRepositorio;

    @Autowired
    private StockServices stockServices;

    @BeforeEach
    public void inicializarDatos() {
        Producto producto = new Producto();
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripcion 1");
        producto.setUnidades(10);
        producto.setColor("Color 1");

        TipoTalla tipoTalla = new TipoTalla();
        tipoTalla.setNombre("Talla 1");

        Stock stock = new Stock();
        stock.setId(1);
        stock.setCantidad(5);
        stock.setProducto(producto);
        stock.setTipoTalla(tipoTalla);

        stockRepositorio.save(stock);
    }

    /**
     * Test para modificar el stock de un producto
     */

    @Test
    public void testModificarStockConDatosValidos() {

        //GIVEN
        StockDTO dto = new StockDTO();
        dto.setCantidad(10);

        //WHEN
        Stock result = stockServices.modificarStock(dto, 1);

        //THEN
        assertNotNull(result);
        assertEquals(10, result.getCantidad());
    }

    /**
     * Test para modificar el stock de un producto con cantidad negativa
     */
    @Test
    public void testModificarStockConCantidadNegativa() {

        //GIVEN
        StockDTO dto = new StockDTO();
        dto.setCantidad(-5);

        //WHEN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stockServices.modificarStock(dto, 1);
        });

        //THEN
        assertEquals("El stock no puede ser negativo", exception.getMessage());
    }

}
