package com.example.tiendaluz.services;

import com.example.tiendaluz.modelos.LineaPedido;
import com.example.tiendaluz.repositorios.LineaPedidoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LineaPedidoServices {
    private LineaPedidoRepositorio lineaPedidoRepositorio;

    /**
     * Buscar todas las lineaPedido
     */
    public List<LineaPedido> getAll() {
        return lineaPedidoRepositorio.findAll();
    }

    /**
     * Buscar lineaPedido por id
     */
    public LineaPedido getById(Integer id) {
        return lineaPedidoRepositorio.findById(id).orElse(null);
    }

    /**
     * crea una lineaPedido nueva o modifica una existente
     */
    public LineaPedido guardar(LineaPedido lineaPedido) {
        return lineaPedidoRepositorio.save(lineaPedido);
    }

    /**
     * eliminar una lineaPedido por id
     */
    public void eliminarPorID(Integer id) {
        lineaPedidoRepositorio.deleteById(id);
    }

    /**
     * eliminar un lineaPedido
     */
    public void eliminar(LineaPedido lineaPedido) {
        lineaPedidoRepositorio.delete(lineaPedido);
    }
}
