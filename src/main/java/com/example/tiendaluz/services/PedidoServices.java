package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.PedidoDTO;
import com.example.tiendaluz.dto.TipoPagoDTO;
import com.example.tiendaluz.modelos.Pedido;
import com.example.tiendaluz.modelos.TipoPago;
import com.example.tiendaluz.repositorios.PedidoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoServices {
    private PedidoRepositorio pedidoRepositorio;
    private TipoPagoServices tipoPagoServices;

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

    //dto

    /**
     * Buscar por dto
     */


    public List<PedidoDTO> getAllDTO() {
        List<Pedido> pedidos = pedidoRepositorio.findAll();
        List<PedidoDTO> pedidoDTOS = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            PedidoDTO dto = new PedidoDTO();
            dto.setPrecio(pedido.getPrecio());
            dto.setFecha(pedido.getFecha());
            dto.setTipoPago(tipoPagoServices.convertToDTO(pedido.getTipoPago()));
            pedidoDTOS.add(dto);
        }
        return pedidoDTOS;
    }

    /**
     * crear dto
     */
    public PedidoDTO crearDTO(PedidoDTO pedidoDTO) {
        Pedido entity = new Pedido();
        entity.setPrecio(pedidoDTO.getPrecio());
        entity.setFecha(pedidoDTO.getFecha());
        entity.setTipoPago(tipoPagoServices.convertToEntity(pedidoDTO.getTipoPago()));
        Pedido pedidoGuardado = pedidoRepositorio.save(entity);
        return convertToDTO(pedidoGuardado);
    }

    private PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setPrecio(pedido.getPrecio());
        dto.setFecha(pedido.getFecha());
        dto.setTipoPago(tipoPagoServices.convertToDTO(pedido.getTipoPago()));
        return dto;
    }

    /**
     * editar por dto
     */
    public Pedido editar(PedidoDTO pedidoDTO, Integer id) {
        Pedido entity = pedidoRepositorio.getReferenceById(id);
        entity.setPrecio(pedidoDTO.getPrecio());
        entity.setFecha(pedidoDTO.getFecha());
        entity.setTipoPago(tipoPagoServices.convertToEntity(pedidoDTO.getTipoPago()));
        return pedidoRepositorio.save(entity);


    }



    /**
     * convertir a dto
     */
}
