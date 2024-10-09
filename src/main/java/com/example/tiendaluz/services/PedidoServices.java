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

    public Pedido crearDTO(PedidoDTO dto) {
        Pedido entity = new Pedido();
        entity.setPrecio(dto.getPrecio());
        entity.setFecha(dto.getFecha());

        TipoPago tipoPago = tipoPagoServices.getById(dto.getTipoPago().getId());
        entity.setTipoPago(tipoPago);

        return pedidoRepositorio.save(entity);
    }

    public List<PedidoDTO> getAllDTO() {
        List<Pedido> pedidos = pedidoRepositorio.findAll();
        List<PedidoDTO> pedidoDTOS = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            PedidoDTO dto = new PedidoDTO();
            dto.setPrecio(pedido.getPrecio());
            dto.setFecha(pedido.getFecha());

            TipoPagoDTO tipoPagoDTO = new TipoPagoDTO();
            tipoPagoDTO.setNombre(pedido.getTipoPago().getNombre());
            dto.setTipoPago(tipoPagoDTO);

            pedidoDTOS.add(dto);
        }
        return pedidoDTOS;
    }

//    private PedidoDTO convertToDTO(Pedido pedido) {
//        PedidoDTO dto = new PedidoDTO();
//        dto.setPrecio(pedido.getPrecio());
//        dto.setFecha(pedido.getFecha());
//        dto.setTipoPago(tipoPagoServices.convertToDTO(pedido.getTipoPago()));
//        return dto;
//    }

    /**
     * editar por dto
     */
//    public Pedido editar(PedidoDTO pedidoDTO, Integer id) {
//        Pedido entity = pedidoRepositorio.getReferenceById(id);
//        entity.setPrecio(pedidoDTO.getPrecio());
//        entity.setFecha(pedidoDTO.getFecha());
//        entity.setTipoPago(tipoPagoServices.convertToEntity(pedidoDTO.getTipoPago()));
//        return pedidoRepositorio.save(entity);
//
//
//    }



    /**
     * convertir a dto
     */
}
