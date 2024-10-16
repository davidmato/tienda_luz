package com.example.tiendaluz.controladores;

import com.example.tiendaluz.dto.StockDTO;
import com.example.tiendaluz.modelos.Stock;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.repositorios.StockRepositorio;
import com.example.tiendaluz.services.StockServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/stock")
@RestController
public class StockControlador {
    StockServices stockServices;
    StockRepositorio stockRepositorio;
    /**
     * modificar stock
     */
    // Add this method to your StockControlador class

    @PutMapping("/modificar/{id}")
    public void modificarStock(@RequestBody StockDTO dto, @PathVariable Integer id) {
        stockServices.modificarStock(dto, id);
    }
    /**
     * Buscar todas las stock
     */
    // Add this method to your StockControlador class
    @GetMapping("/all")
    public List<Stock> getAll() {
        return stockServices.getAll();
    }

}
