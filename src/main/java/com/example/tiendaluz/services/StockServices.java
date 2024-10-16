package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.ProductoDTO;
import com.example.tiendaluz.dto.StockDTO;
import com.example.tiendaluz.dto.TipoPagoDTO;
import com.example.tiendaluz.dto.TipoTallaDTO;
import com.example.tiendaluz.modelos.Stock;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.repositorios.StockRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class StockServices {
    private StockRepositorio stockRepositorio;

    /**
     * Buscar todas las stock
     */
    public List<Stock> getAll() {
        return stockRepositorio.findAll();
    }

    /**
     * Buscar stock por id
     */
    public Stock getById(Integer id) {
        return stockRepositorio.findById(id).orElse(null);
    }

    /**
     * crea una stock nueva o modifica una existente
     */
    public Stock guardar(Stock stock) {
        return stockRepositorio.save(stock);
    }

    /**
     * eliminar una stock por id
     */
    public void eliminarPorID(Integer id) {
        stockRepositorio.deleteById(id);
    }








    /**
     * A traves de  un JSON con los datos del producto y la cantidad a modificar junto con la talla, modifica el stock del producto para esa talla
     */
    @Transactional
    public Stock modificarStock(StockDTO dto,Integer id){
        Stock entity = stockRepositorio.getReferenceById(id);

        entity.setCantidad(dto.getCantidad());

        ProductoDTO productoDTO =  new ProductoDTO();
        productoDTO.setUnidades(dto.getProducto().getUnidades());
        productoDTO.setColor(dto.getProducto().getColor());
        productoDTO.setDescripcion(dto.getProducto().getDescripcion());
        productoDTO.setNombre(dto.getProducto().getNombre());

        TipoTallaDTO tipoTallaDTO = new TipoTallaDTO();
        tipoTallaDTO.setNombre(dto.getTipoTalla().getNombre());

        dto.setProducto(productoDTO);
        dto.setTipoTalla(tipoTallaDTO);

        return stockRepositorio.save(entity);
    }
}
