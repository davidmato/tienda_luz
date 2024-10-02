package com.example.tiendaluz.services;


import com.example.tiendaluz.modelos.DetallesVenta;
import com.example.tiendaluz.repositorios.DetallesVentaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DetallesVentaServices {
    private DetallesVentaRepositorio detallesVentaRepositorio;

    /**
     * Buscar todas las detallesVenta
     */
    public List<DetallesVenta> getAll() {
        return detallesVentaRepositorio.findAll();
    }

    /**
     * Buscar detallesVenta por id
     */
    public DetallesVenta getById(Integer id) {
        return detallesVentaRepositorio.findById(id).orElse(null);
    }

    /**
     * crea una detallesVenta nueva o modifica una existente
     */
    public DetallesVenta guardar(DetallesVenta detallesVenta) {
        return detallesVentaRepositorio.save(detallesVenta);
    }

    /**
     * eliminar una detallesVenta por id
     */
    public void eliminarPorID(Integer id) {
        detallesVentaRepositorio.deleteById(id);
    }

    /**
     * eliminar un detallesVenta
     */
    public void eliminar(DetallesVenta detallesVenta) {
        detallesVentaRepositorio.delete(detallesVenta);
    }
}
