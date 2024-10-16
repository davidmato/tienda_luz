package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.ProductoDTO;
import com.example.tiendaluz.dto.TipoTallaDTO;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.repositorios.CatalogoRepositorio;
import com.example.tiendaluz.repositorios.TipoTallaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
@AllArgsConstructor
public class TipoTallaServices {
    private TipoTallaRepositorio tipoTallaRepositorio;
    private CatalogoRepositorio catalogoRepositorio;
    private ProductoServices productoServices;

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
            dto.setProducto(productoDTO);
            tipoTallaDTOS.add(dto);
        }
        return tipoTallaDTOS;
    }

    /**
     * Cantidad de dicho producto que hay de la talla indicada . Si no hayt ninguno muestra un mensaje  de "no hay productos disponibles"
     * @return idProducto, talla
     */
    public Map<String, Object> cantidadProducto(Integer idProducto, String talla) {
        Producto producto = productoServices.getById(idProducto);
        Map<String, Object> response = new HashMap<>();
        if (producto == null) {
            response.put("message", "No hay productos disponibles");
            return response;
        }
        TipoTalla tipoTalla = buscarPorNombre(talla).stream()
                .filter(t -> t.getProducto().getId().equals(producto.getId()))
                .findFirst()
                .orElse(null);
        if (tipoTalla == null) {
            response.put("message", "No hay productos disponibles");
            return response;
        }
        response.put("Alerta:", "Hay productos disponibles");
        response.put("Unidades", producto.getUnidades());
        return response;
    }


}
