package com.example.tiendaluz.controladores;


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
        Producto cliente = productoServices.getById(id);
        return cliente;
    }

    @GetMapping("/get/{id}")
    public Producto getByIdPath(@PathVariable Integer id){
        Producto cliente = productoServices.getById(id);
        return cliente;
    }


    @PostMapping
    public Producto guardar(@RequestBody Producto cliente){
        Producto clienteGuaradado = productoServices.guardar(cliente);
        return clienteGuaradado;
    }

    @DeleteMapping
    public String eliminar(@RequestParam Integer id){
        productoServices.eliminarPorId(id);
        return "Cliente eliminado";
    }

}
