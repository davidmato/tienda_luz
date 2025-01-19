package com.example.tiendaluz;

import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.repositorios.ProductoRepositorio;
import com.example.tiendaluz.repositorios.TipoTallaRepositorio;
import com.example.tiendaluz.services.ProductoServices;
import com.example.tiendaluz.services.TipoTallaServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class TipoTallaTest {

    @Autowired
    private TipoTallaServices tipoTallaServices;

    @Autowired
    private ProductoServices productoServices;

    @Autowired
    private TipoTallaRepositorio tipoTallaRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    /**
     * TEST 2
     */

    @BeforeEach
    public void inicializarDatos() {
        Producto producto1 = new Producto();
        producto1.setNombre("Camiseta");
        producto1.setColor("Blanco");
        producto1.setDescripcion("Camiseta de algodón");
        producto1.setUnidades(50);
        Producto productoGuardado1 = productoRepositorio.save(producto1);

        Producto producto2 = new Producto();
        producto2.setNombre("Pantalón");
        producto2.setColor("Azul");
        producto2.setDescripcion("Pantalón de mezclilla");
        producto2.setUnidades(30);
        Producto productoGuardado2 = productoRepositorio.save(producto2);

        TipoTalla tipoTalla1 = new TipoTalla();
        tipoTalla1.setNombre("M");
        tipoTalla1.setProducto(productoGuardado1);

        TipoTalla tipoTalla2 = new TipoTalla();
        tipoTalla2.setNombre("L");
        tipoTalla2.setProducto(productoGuardado2);

        tipoTallaRepositorio.save(tipoTalla1);
        tipoTallaRepositorio.save(tipoTalla2);
    }


    /**
     * Test  para el metodo cantidadProducto de TipoTallaServices
     */
    @Test
    public void cantidadDisponibleTipoTalla(){
        // GIVEN

        // WHEN
        String disponibilidad = tipoTallaServices.cantidadProducto(1, "M");

        // THEN
        assertEquals("Hay productos disponibles. Unidades: 50", disponibilidad);
    }

    /**
     * Test para el metodo cantidadProducto de TipoTallaServices
     */

    @Test
    public void cantidadNoDisponibleTipoTalla(){
        // GIVEN

        // WHEN
        String disponibilidad = tipoTallaServices.cantidadProducto(1, "S");

        // THEN
        assertEquals("No hay productos disponibles", disponibilidad);
    }

}
