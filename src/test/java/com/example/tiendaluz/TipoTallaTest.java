package com.example.tiendaluz;

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
public class TipoTallaTest {

    @Autowired
    private TipoTallaServices tipoTallaServices;

    @Autowired
    private ProductoServices productoServices;



    @Test
    @Transactional
    @Commit
    void testCrearTipoTalla(){
        Producto producto = new Producto();
        producto.setNombre("camiseta");
        producto.setColor("azul");
        producto.setDescripcion("hecha de algodon");
        producto.setUnidades(110);
        Producto productoGuardado = productoServices.guardar(producto);

        TipoTalla tipoTalla = new TipoTalla();
        tipoTalla.setNombre("XXL");
        tipoTalla.setProducto(productoGuardado);
        TipoTalla tipoTallaGuardado = tipoTallaServices.guardar(tipoTalla);
        System.out.println(tipoTallaGuardado.toString());
    }
    @Test
    void testEditarTipoTalla(){
        TipoTalla tipoTalla = tipoTallaServices.buscarPorId(6);
        tipoTalla.setNombre("L");
        TipoTalla tipoTallaGuardado = tipoTallaServices.guardar(tipoTalla);
        System.out.println(tipoTallaGuardado.toString());

    }
    @Test
    @Transactional
    void testEliminar(){
        tipoTallaServices.eliminarPorId(1);
    }

    @Test
    void testBuscarTipoTallas(){
        System.out.println("TipoTallas");
        for (TipoTalla t : tipoTallaServices.getAll()){
            System.out.println(t.getNombre());
        }
    }
}
