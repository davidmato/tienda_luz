package com.example.tiendaluz;


import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.services.ProductoServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootTest
public class ProductoTest {
    @Autowired

    private ProductoServices productoServices;

    @Test
    void testCrearProducto(){
        Producto producto = new Producto();
        producto.setNombre("camiseta");
        producto.setColor("azul");
        producto.setDescripcion("hecha de algodon");
        producto.setUnidades(98);
        Producto productoGuardado = productoServices.guardar(producto);
        System.out.println(productoGuardado.toString());
    }
    @Test
    void testEditarProducto(){
        Producto producto = productoServices.getById(6);
        producto.setNombre("camiseta");
        producto.setColor("azul");
        producto.setDescripcion("hecha de algodon");
        producto.setUnidades(9);
        Producto productoGuardado = productoServices.guardar(producto);
        System.out.println(productoGuardado.toString());

    }
    @Test
    void testEliminar(){
        productoServices.eliminarPorId(6);
    }

    @Test
    void testBuscarProductos(){
        List<Producto> productos = productoServices.getAll();
        for (Producto p : productos){
            System.out.println(p.getNombre());
        }
    }
}
