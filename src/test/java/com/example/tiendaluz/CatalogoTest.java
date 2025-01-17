package com.example.tiendaluz;

import com.example.tiendaluz.dto.CatalogoDTO;
import com.example.tiendaluz.modelos.Catalogo;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.repositorios.CatalogoRepositorio;
import com.example.tiendaluz.services.CatalogoServices;
import com.example.tiendaluz.services.ProductoServices;
import com.example.tiendaluz.services.TipoTallaServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class CatalogoTest {

    @Autowired
    private CatalogoServices catalogoServices;


    @Autowired
    private CatalogoRepositorio catalogoRepositorio;

    @Autowired
    private ProductoServices productoServices;

    @Autowired
    private TipoTallaServices tipoTallaServices;

    /**
     * TEST 1
     */

    @BeforeEach
    public void inicializarDatos() {
        Producto producto1 = new Producto();
        producto1.setNombre("Camiseta");
        producto1.setDescripcion("Camiseta de algodón");
        producto1.setColor("Blanco");
        producto1.setUnidades(50);
        Producto productoGuardado1 = productoServices.guardar(producto1);

        Producto producto2 = new Producto();
        producto2.setNombre("Pantalón");
        producto2.setDescripcion("Pantalón de mezclilla");
        producto2.setColor("Azul");
        producto2.setUnidades(30);
        Producto productoGuardado2 = productoServices.guardar(producto2);

        TipoTalla tipoTalla1 = new TipoTalla();
        tipoTalla1.setNombre("M");
        tipoTalla1.setProducto(productoGuardado1);
        TipoTalla tipoTallaGuardado1 = tipoTallaServices.guardar(tipoTalla1);

        TipoTalla tipoTalla2 = new TipoTalla();
        tipoTalla2.setNombre("L");
        tipoTalla2.setProducto(productoGuardado2);
        TipoTalla tipoTallaGuardado2 = tipoTallaServices.guardar(tipoTalla2);

        Catalogo catalogo1 = new Catalogo();
        catalogo1.setPrecio(25.0);
        catalogo1.setProducto(productoGuardado1);
        catalogo1.setTipoTalla(tipoTallaGuardado1);
        catalogoRepositorio.save(catalogo1);

        Catalogo catalogo2 = new Catalogo();
        catalogo2.setPrecio(40.0);
        catalogo2.setProducto(productoGuardado2);
        catalogo2.setTipoTalla(tipoTallaGuardado2);
        catalogoRepositorio.save(catalogo2);

    }


    /**
     * Test del primer ejercicio: obtener todos los catalogos con sus productos y tallas
     */

    @Test
    public void testFindAll() {
        //GIVEN

        //WHEN
        List<CatalogoDTO> catalogo = catalogoServices.getAllDTO();


        //THEN

        assertEquals(2, catalogo.size());
    }


    /**
     * Test negativo del primer ejercicio: obtener todos los catalogos con sus productos y tallas
     *
     */
    @Test
    public void testFindAllNegativo() {
        //GIVEN
        catalogoRepositorio.deleteAll();

        //WHEN
        List<CatalogoDTO> catalogo = catalogoServices.getAllDTO();

        //THEN
        assertTrue(catalogo.isEmpty());
    }


}
