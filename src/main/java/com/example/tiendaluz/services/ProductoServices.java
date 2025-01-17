package com.example.tiendaluz.services;


import com.example.tiendaluz.dto.ProductoDTO;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.repositorios.ProductoRepositorio;
import com.example.tiendaluz.repositorios.TipoTallaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductoServices {
    private ProductoRepositorio productoRepositorio;

    private TipoTallaRepositorio tipoTallaRepositorio;


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

    //DTO

    /**
     * crear un producto
     */

    public Producto crearProducto(ProductoDTO dto){
        Producto entity = new Producto();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setUnidades(dto.getUnidades());
        entity.setColor(dto.getColor());
        return productoRepositorio.save(entity);
    }

    /**
     * editar un producto
     */

    public Producto editarProducto(ProductoDTO dto,Integer id){
        Producto entity = productoRepositorio.getReferenceById(id);
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setUnidades(dto.getUnidades());
        entity.setColor(dto.getColor());
        return productoRepositorio.save(entity);
    }

    /**
     * buscar por dto
     * @return
     */

    public List<ProductoDTO>getAllDTO() {
        List<Producto> productos = productoRepositorio.findAll();
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for (Producto producto : productos) {
            ProductoDTO dto = new ProductoDTO();
            dto.setNombre(producto.getNombre());
            dto.setDescripcion(producto.getDescripcion());
            dto.setUnidades(producto.getUnidades());
            dto.setColor(producto.getColor());
            productosDTO.add(dto);
        }
        return productosDTO;
    }



}
