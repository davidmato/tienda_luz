package com.example.tiendaluz.controladores;


import com.example.tiendaluz.dto.ProductoDTO;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.services.ProductoServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoControlador {
    private ProductoServices productoServices;

    @GetMapping("/listar")
    public List<Producto> getAllClientes() {
        List<Producto> productos = productoServices.getAll();
        return productos;

    }

    @GetMapping()
    public Producto getById(@RequestParam Integer id){
        Producto producto = productoServices.getById(id);
        return producto;
    }

    @GetMapping("/get/{id}")
    public Producto getByIdPath(@PathVariable Integer id){
        Producto producto = productoServices.getById(id);
        return producto;
    }


    @PostMapping
    public Producto guardar(@RequestBody Producto producto){
        Producto productoGuaradado = productoServices.guardar(producto);
        return productoGuaradado;
    }

    @DeleteMapping
    public String eliminar(@RequestParam Integer id){
        productoServices.eliminarPorId(id);
        return "Cliente eliminado";
    }

    //
    ////
    //////DTO
    ////
    //

    /**
     * buscar por dto
     * @return
     */
    @GetMapping("/all")
    public List<ProductoDTO>getAllProductosDTO(){
        List<ProductoDTO>productos= productoServices.getAllDTO();
        return productos;
    }

    /**
     * crear en dto
     * @param productoDTO
     * @return
     */

    @PostMapping("/crear")
    public Producto crearProducto(@RequestBody ProductoDTO productoDTO){
        return productoServices.crearProducto(productoDTO);
    }

    /**
     * editar
     * @param productoDTO
     * @return
     */
    @PutMapping("/editar/{id}")
    public Producto editarProducto(@RequestBody ProductoDTO productoDTO, @PathVariable Integer id){
        return productoServices.editarProducto(productoDTO,id);
    }

    /**
     * eliminar por dto
     * @return
     */
    @DeleteMapping("/eliminar/{id}")
    public String eliminarPorDTO(@PathVariable Integer id){
        productoServices.eliminarPorId(id);
        return "Producto eliminado";
    }




}
