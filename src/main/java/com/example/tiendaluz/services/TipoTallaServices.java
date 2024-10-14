package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.ProductoDTO;
import com.example.tiendaluz.dto.TipoTallaDTO;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.repositorios.CatalogoRepositorio;
import com.example.tiendaluz.repositorios.TipoTallaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
@AllArgsConstructor
public class TipoTallaServices {
    private TipoTallaRepositorio tipoTallaRepositorio;
    private CatalogoRepositorio catalogoRepositorio;

    /**
     * Busca tipoTalla por nombre
     * param nombre
     */
    public List<TipoTalla> buscarPorNombre(String nombre) {
        List<TipoTalla> tipoTallas = tipoTallaRepositorio.findAllByNombreEquals(nombre);
        return tipoTallas;
    }

    /**
     * Buscar todos los tipoTalla
     */
    public List<TipoTalla> getAll() {
        return tipoTallaRepositorio.findAll();
    }

    /**
     * buscar tipoTalla por id
     */

    public TipoTalla getById(Integer id){
        return tipoTallaRepositorio.findById(id).orElse(null);
    }

    /**
     * crea un tipoTalla nuevo o modifica uno ya existente
     */
    public TipoTalla guardar(TipoTalla tipoTalla){
        return tipoTallaRepositorio.save(tipoTalla);
    }

    /**
     * eliminar tipoTalla por id
     */
    @Transactional
    public void eliminarPorId(Integer id) {
        tipoTallaRepositorio.deleteById(id);

    }

    //dto

    /**
     * buscar por dto
     */
    public List<TipoTallaDTO> getAllDTO() {
        List<TipoTalla> tipoTallas = tipoTallaRepositorio.findAll();
        List<TipoTallaDTO> tipoTallaDTOS = new ArrayList<>();

        for (TipoTalla tipoTalla : tipoTallas) {
            TipoTallaDTO dto = new TipoTallaDTO();
            dto.setNombre(tipoTalla.getNombre());
            Producto producto = tipoTalla.getProducto();
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setNombre(producto.getNombre());
            dto.setIdProducto(producto.getId());
            tipoTallaDTOS.add(dto);
        }
        return tipoTallaDTOS;
    }


}
