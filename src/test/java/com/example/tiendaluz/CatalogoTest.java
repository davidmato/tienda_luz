package com.example.tiendaluz;

import com.example.tiendaluz.modelos.Catalogo;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.services.CatalogoServices;
import com.example.tiendaluz.services.ProductoServices;
import com.example.tiendaluz.services.TipoTallaServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CatalogoTest {
    @Autowired
    CatalogoServices catalogoServices;

    @Autowired
    ProductoServices productoServices;

    @Autowired
    TipoTallaServices tipoTallaServices;

    @Test
    @Transactional
    @Commit
    void testCrearCatalogo(){

        Producto producto = new Producto();
        producto.setNombre("Camiseta");
        producto.setDescripcion("Camiseta de algodon");
        producto.setColor("Blanco");
        producto.setUnidades(6);
        Producto productoGuardado = productoServices.guardar(producto);

        TipoTalla tipoTalla = new TipoTalla();
        tipoTalla.setNombre("M");
        tipoTalla.setProducto(productoGuardado);
        TipoTalla tipoTallaGuardado = tipoTallaServices.guardar(tipoTalla);


        Catalogo catalogo = new Catalogo();
        catalogo.setPrecio(35.5);
        catalogo.setProducto(productoGuardado);
        catalogo.setTipoTalla(tipoTallaGuardado);
        Catalogo catalogoGuardado = catalogoServices.guardar(catalogo);
        System.out.println(catalogoGuardado.toString());

    }
    @Test
    void testEditarCatalogo(){
        Catalogo catalogo=
    }
}
