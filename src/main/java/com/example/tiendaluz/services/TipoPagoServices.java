package com.example.tiendaluz.services;

import com.example.tiendaluz.modelos.TipoPago;
import com.example.tiendaluz.repositorios.TipoPagoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoPagoServices {
    private TipoPagoRepositorio tipoPagoRepositorio;

    /**
     * Buscar todas las tipoPago
     */
    public List<TipoPago> getAll() {
        return tipoPagoRepositorio.findAll();
    }

    /**
     * Buscar tipoPago por id
     */
    public TipoPago getById(Integer id) {
        return tipoPagoRepositorio.findById(id).orElse(null);
    }

    /**
     * crea una tipoPago nueva o modifica una existente
     */
    public TipoPago guardar(TipoPago tipoPago) {
        return tipoPagoRepositorio.save(tipoPago);
    }

    /**
     * eliminar una tipoPago por id
     */
    public void eliminarPorID(Integer id) {
        tipoPagoRepositorio.deleteById(id);
    }


}
