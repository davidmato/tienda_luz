package com.example.tiendaluz;

import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.services.ProductoServices;
import com.example.tiendaluz.services.TipoTallaServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TipoTallaTest {

    @Autowired
    private TipoTallaServices tipoTallaServices;

    @Autowired
    private ProductoServices productoServices;



    @Test
    void testCrearTipoTalla(){
        Producto producto = new Producto();
        producto.setNombre("camiseta");
        producto.setColor("azul");
        producto.setDescripcion("hecha de algodon");
        producto.setUnidades(5);
        Producto productoGuardado = productoServices.guardar(producto);

        TipoTalla tipoTalla = new TipoTalla();
        tipoTalla.setNombre("S");
        tipoTalla.setProducto(productoGuardado);
        TipoTalla tipoTallaGuardado = tipoTallaServices.guardar(tipoTalla);
        System.out.println(tipoTallaGuardado.toString());
    }
    @Test
    void testEditarTipoTalla(){
        TipoTalla tipoTalla = tipoTallaServices.buscarPorId(1);
        tipoTalla.setNombre("M");
        TipoTalla tipoTallaGuardado = tipoTallaServices.guardar(tipoTalla);
        System.out.println(tipoTallaGuardado.toString());

    }
    @Test
    void testEliminar(){
        tipoTallaServices.eliminarPorId(4);
    }

    @Test
    void testBuscarTipoTallas(){
        System.out.println("TipoTallas");
        for (TipoTalla t : tipoTallaServices.getAll()){
            System.out.println(t.getNombre());
        }
    }
}
