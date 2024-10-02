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
    @Transactional
    @Commit
    void testEditarCatalogo(){
        Catalogo catalogo = catalogoServices.getById(1);
        catalogo.setPrecio(40.5);
        catalogo.setProducto(productoServices.getById(1));
        catalogo.setTipoTalla(tipoTallaServices.getById(1));
        Catalogo catalogoGuardado = catalogoServices.guardar(catalogo);
        System.out.println(catalogoGuardado.toString());

    }
    @Test
    @Transactional
    void testEliminarCatalogo(){
        catalogoServices.eliminarPorID(2);
    }

    @Test
    void testBuscarCatalogosPorPrecio(){
        System.out.println("Catalogos");
        for (Catalogo c : catalogoServices.getAll()){
            System.out.println(c.getPrecio());
        }
    }
    @Test
    void eliminarCatalogo(){
    }
}
