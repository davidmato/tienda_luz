package com.example.tiendaluz.services;

import com.example.tiendaluz.modelos.Pedido;
import com.example.tiendaluz.repositorios.PedidoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoServices {
    private PedidoRepositorio pedidoRepositorio;

    /**
     * Buscar todas las pedido
     */
    public List<Pedido> getAll() {
        return pedidoRepositorio.findAll();
    }

    /**
     * Buscar pedido por id
     */
    public Pedido getById(Integer id) {
        return pedidoRepositorio.findById(id).orElse(null);
    }

    /**
     * crea una pedido nueva o modifica una existente
     */
    public Pedido guardar(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    /**
     * eliminar una pedido por id
     */
    public void eliminarPorID(Integer id) {
        pedidoRepositorio.deleteById(id);
    }

}
