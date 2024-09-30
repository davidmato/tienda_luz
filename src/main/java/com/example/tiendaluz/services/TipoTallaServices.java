package com.example.tiendaluz.services;

import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.repositorios.TipoTallaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoTallaServices {
    private TipoTallaRepositorio tipoTallaRepositorio;

    /**
     * Busca productos por nombre
     * param nombre
     */
    public List<TipoTalla> buscarPorNombre(String nombre) {
        List<TipoTalla> tipoTallas = tipoTallaRepositorio.findAllByNombreEquals(nombre);
        return tipoTallas;
    }

    /**
     * Buscar todos los productos
     */
    public List<TipoTalla> getAll() {
        return tipoTallaRepositorio.findAll();
    }

    /**
     * buscar producto por id
     */

    public TipoTalla buscarPorId(Integer id){
        return tipoTallaRepositorio.findById(id).orElse(null);
    }

    /**
     * crea un producto nuevo o modifica uno ya existente
     */
    public TipoTalla guardar(TipoTalla tipoTalla){
        return tipoTallaRepositorio.save(tipoTalla);
    }

    /**
     * eliminar producto por id
     */

    public void eliminarPorId(Integer id) {
        tipoTallaRepositorio.deleteById(id);
    }

}
