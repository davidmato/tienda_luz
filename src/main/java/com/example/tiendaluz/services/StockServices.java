package com.example.tiendaluz.services;

import com.example.tiendaluz.modelos.Stock;
import com.example.tiendaluz.repositorios.StockRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
