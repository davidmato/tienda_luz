package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.CatalogoDTO;
import com.example.tiendaluz.dto.ProductoDTO;
import com.example.tiendaluz.dto.TipoTallaDTO;
import com.example.tiendaluz.modelos.Catalogo;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.repositorios.CatalogoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class    CatalogoServices {

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
        try {
            return catalogoRepositorio.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Catalogo no encontrado con el id: " + id));
        } catch (Exception e) {
            // Handle the exception, e.g., log it or rethrow a custom exception
            e.printStackTrace();
            throw e;
        }
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
        List<CatalogoDTO> catalogoDTOS = new ArrayList<>();
        try {
            List<Catalogo> catalogos = catalogoRepositorio.findAll();
            for (Catalogo catalogo : catalogos) {
                CatalogoDTO dto = new CatalogoDTO();
                dto.setPrecio(catalogo.getPrecio());

                Producto producto = catalogo.getProducto();
                if (producto != null) {
                    ProductoDTO productoDTO = new ProductoDTO();
                    productoDTO.setNombre(producto.getNombre());
                    productoDTO.setDescripcion(producto.getDescripcion());
                    productoDTO.setColor(producto.getColor());
                    productoDTO.setUnidades(producto.getUnidades());
                    dto.setProducto(productoDTO);
                }

                TipoTalla tipoTalla = catalogo.getTipoTalla();
                if (tipoTalla != null) {
                    TipoTallaDTO tipoTallaDTO = new TipoTallaDTO();
                    tipoTallaDTO.setNombre(tipoTalla.getNombre());
                    tipoTallaDTO.setProducto(dto.getProducto());
                    dto.setTipoTalla(tipoTallaDTO);
                }

                catalogoDTOS.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catalogoDTOS;
    }
}


