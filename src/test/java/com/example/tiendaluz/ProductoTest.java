package com.example.tiendaluz;


import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.repositorios.ProductoRepositorio;
import com.example.tiendaluz.services.ProductoServices;
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
public class ProductoTest {
    @Autowired

    private ProductoServices productoServices;

    @Autowired
    private ProductoRepositorio productoRepositorio;


    @BeforeEach
    public void inicializarDatos() {
        Producto producto1 = new Producto();
        producto1.setNombre("camiseta");
        producto1.setColor("azul");
        producto1.setDescripcion("hecha de algodon");
        producto1.setUnidades(98);

        Producto producto2 = new Producto();
        producto2.setNombre("pantalon");
        producto2.setColor("Naranja");
        producto2.setDescripcion("hecha de algodon");
        producto2.setUnidades(98);


        productoRepositorio.save(producto1);

        productoRepositorio.save(producto2);

    }

    @Test
    public void TestFindAll() {
        //GIVEN

        //WHEN
        List<Producto> productos = productoServices.getAll();

        //THEN
        assertEquals(2, productos.size());
    }

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
    @Transactional
    @Commit
    void testEliminar(){
        productoServices.eliminarPorId(3);
    }

    @Test
    void testBuscarProductos(){
        List<Producto> productos = productoServices.getAll();
        for (Producto p : productos){
            System.out.println(p.getNombre());
        }
    }
}
