package com.example.tiendaluz.services;


import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.repositorios.ProductoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServices {
    private ProductoRepositorio productoRepositorio;

    /**
     * Busca productos por nombre
     * param nombre
     */
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productos = productoRepositorio.findAllByNombreEquals(nombre);
        return productos;
    }

    /**
     * Buscar todos los productos
     */
    public List<Producto> getAll() {
        return productoRepositorio.findAll();
    }

    /**
     * buscar producto por id
     */

    public Producto getById(Integer id){
        return productoRepositorio.findById(id).orElse(null);
    }

    /**
     * crea un producto nuevo o modifica uno ya existente
     */
    public Producto guardar(Producto producto){
        return productoRepositorio.save(producto);
    }


    /**
     * eliminar producto por id
     */

    public void eliminarPorId(Integer id){
        productoRepositorio.deleteById(id);
    }

    /**
     * eliminar producto
     * @param producto
     */

    public void eliminar(Producto producto){
        productoRepositorio.delete(producto);
    }




}
