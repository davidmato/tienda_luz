package com.example.tiendaluz.services;

import com.example.tiendaluz.modelos.Catalogo;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.repositorios.CatalogoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatalogoServices {

    CatalogoRepositorio catalogoRepositorio;
    /**
     * Busca catalogo por precio
     * param nombre
     */
    public List<Catalogo> buscarClientePorPrecio(Integer precio) {
        return catalogoRepositorio.findAllByPrecioEquals(precio);
    }

    /**
     * Buscar todas las catalogos
     */
    public List<Catalogo> getAll() {
            return catalogoRepositorio.findAll();
    }
    /**
     * Buscar una catalogo por id
     */
    public Catalogo getById(Integer id) {
        return catalogoRepositorio.findById(id).orElse(null);
    }


    /**
     * crea una catalogos nueva o modifica una existente
     */

    public Catalogo guardar (Catalogo catalogo){
        return catalogoRepositorio.save(catalogo);
    }
    /**
     * eliminar una catalogo por id
     */

    public void eliminarPorID (Integer id){
        catalogoRepositorio.deleteById(id);
    }
    /**
     * eliminar una catalogo
     */
    public void eliminar (Catalogo catalogo){
        catalogoRepositorio.delete(catalogo);
    }


}


