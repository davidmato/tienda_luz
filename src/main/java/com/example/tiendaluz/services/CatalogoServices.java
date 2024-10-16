package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.CatalogoDTO;
import com.example.tiendaluz.dto.ProductoDTO;
import com.example.tiendaluz.dto.TipoTallaDTO;
import com.example.tiendaluz.modelos.Catalogo;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.repositorios.CatalogoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    //dto


    /**
     * Metodo que muestra el catalogo de todos los productos ecistentes , con modelos y tallas disponibles
     *
     *
     */


    public List<CatalogoDTO> getAllDTO() {
        List<Catalogo> catalogos = catalogoRepositorio.findAll();
        List<CatalogoDTO>catalogoDTOS = new ArrayList<>();
        for (Catalogo catalogo : catalogos) {
            CatalogoDTO dto = new CatalogoDTO();
            dto.setPrecio(catalogo.getPrecio());

            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setNombre(catalogo.getProducto().getNombre());
            productoDTO.setDescripcion(catalogo.getProducto().getDescripcion());
            productoDTO.setColor(catalogo.getProducto().getColor());
            productoDTO.setUnidades(catalogo.getProducto().getUnidades());

            TipoTallaDTO tipoTallaDTO = new TipoTallaDTO();
            tipoTallaDTO.setNombre(catalogo.getTipoTalla().getNombre());
            tipoTallaDTO.setProducto(productoDTO);

            dto.setProducto(productoDTO);
            dto.setTipoTalla(tipoTallaDTO);

            catalogoDTOS.add(dto);
        }
        return catalogoDTOS;
    }


}


