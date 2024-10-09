package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.ProductoDTO;
import com.example.tiendaluz.dto.TipoPagoDTO;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoPago;
import com.example.tiendaluz.repositorios.PedidoRepositorio;
import com.example.tiendaluz.repositorios.TipoPagoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TipoPagoServices {
    private TipoPagoRepositorio tipoPagoRepositorio;
    private PedidoRepositorio pedidoRepositorio;

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
    @Transactional
    public void eliminarPorID(Integer id) {

        TipoPago tipoPago = tipoPagoRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("TipoPago not found"));
        if (!pedidoRepositorio.findByTipoPago(tipoPago).isEmpty()) {
            throw new IllegalStateException("Cannot delete TipoPago as it is referenced by Pedido");
        }
        tipoPagoRepositorio.delete(tipoPago);
    }


    /**
     * Buscar por dto
     */
    public List<TipoPagoDTO>getAllDTO() {
        List<TipoPago> tipoPagos = tipoPagoRepositorio.findAll();
        List<TipoPagoDTO> tipoPagoDTOS = new ArrayList<>();
        for (TipoPago tipoPago : tipoPagos) {
            TipoPagoDTO dto = new TipoPagoDTO();
            dto.setNombre(tipoPago.getNombre());
            tipoPagoDTOS.add(dto);
        }
        return tipoPagoDTOS;
    }

    /**
     * crear por dto
     */
    public TipoPago guardarPorDTO(TipoPagoDTO tipoPagoDTO) {
        TipoPago entity = new TipoPago();
        entity.setNombre(tipoPagoDTO.getNombre());
        return tipoPagoRepositorio.save(entity);
    }
    /**
     * editar por dto
     */
    public TipoPago editarPorDTO(TipoPagoDTO dto, Integer id) {
        TipoPago entity = tipoPagoRepositorio.getReferenceById(id);
        entity.setNombre(dto.getNombre());
        return tipoPagoRepositorio.save(entity);
    }


    /**
     * convertir a dto
     */
    public TipoPagoDTO convertToDTO(TipoPago tipoPago) {
        TipoPagoDTO dto = new TipoPagoDTO();
        dto.setNombre(tipoPago.getNombre());
        return dto;
    }

    /**
     * convertir a entity
     */

    public TipoPago convertToEntity(TipoPagoDTO dto) {
        TipoPago tipoPago = new TipoPago();
        tipoPago.setNombre(dto.getNombre());
        return tipoPago;
    }



}
