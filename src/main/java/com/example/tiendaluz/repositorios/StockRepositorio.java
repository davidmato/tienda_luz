package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.TipoTalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tiendaluz.modelos.Stock;

import java.util.List;

@Repository
public interface StockRepositorio extends JpaRepository<Stock, Integer> {
    Stock findByProductoIdAndTipoTalla(Integer idProducto, TipoTalla tipoTalla);
}