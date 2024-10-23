package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.*;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.modelos.Pedido;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoPago;
import com.example.tiendaluz.repositorios.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor

public class PedidoServices {
    private PedidoRepositorio pedidoRepositorio;
    private TipoPagoServices tipoPagoServices;
    private ClienteRepositorio clienteRepositorio;
    private TipoPagoRepositorio tipoPagoRepositorio;
    private ClienteServices clienteServices;
    private ProductoRepositorio productoRepositorio;

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

//    public PedidoDTO crearDTO(PedidoDTO dto) {
//        Pedido entity = new Pedido();
//        entity.setPrecio(dto.getPrecio());
//        entity.setFecha(dto.getFecha());
//        entity.setTipoPago(tipoPagoServices.getById(dto.getIdTipoPago()));
//
//        if (dto.getIdTipoPago() == null || dto.getIdTipoPago() == null) {
//            throw new IllegalArgumentException("TipoPago id must not be null");
//        }
//        Pedido pedido = pedidoRepositorio.save(entity);
//
//        return dto;
//    }
//
//    public List<PedidoDTO> getAllDTO() {
//        List<Pedido> pedidos = pedidoRepositorio.findAll();
//        List<PedidoDTO> pedidoDTOS = new ArrayList<>();
//        for (Pedido pedido : pedidos) {
//            PedidoDTO dto = new PedidoDTO();
//            dto.setPrecio(pedido.getPrecio());
//            dto.setFecha(pedido.getFecha());
//            TipoPago tipoPagos = pedido.getTipoPago();
//            TipoPagoDTO tipoPagoDTO = new TipoPagoDTO();
//            tipoPagoDTO.setNombre(tipoPagos.getNombre());
//            dto.setIdTipoPago(tipoPagos.getId());
//
//            pedidoDTOS.add(dto);
//        }
//        return pedidoDTOS;
//    }
//
//
//
//    /**
//     * editar por dto
//     */
//    public PedidoDTO editar(PedidoDTO pedidoDTO, Integer id) {
//        Pedido entity = pedidoRepositorio.getReferenceById(id);
//
//        entity.setPrecio(pedidoDTO.getPrecio());
//        entity.setFecha(pedidoDTO.getFecha());
//        entity.setTipoPago(tipoPagoServices.getById(pedidoDTO.getIdTipoPago()));
//        Pedido pedido= pedidoRepositorio.save(entity);
//        return pedidoDTO;
//
//    }


    /**
     * Metodo para crear un pedido para un cliente, con productos, cantidades,etc
     */

    public Pedido crearPedido(CrearPedidoDTO pedidoDTO) {
        Pedido entity = new Pedido();
        entity.setPrecio(pedidoDTO.getPrecio());
        entity.setFecha(pedidoDTO.getFecha());

        Cliente cliente = clienteRepositorio.getById(pedidoDTO.getIdCliente());
        entity.setCliente(cliente);
        TipoPago tipoPago = tipoPagoRepositorio.getById(pedidoDTO.getIdTipoPago());
        entity.setTipoPago(tipoPago);

        Set<Producto> productos = new HashSet<>();
        for (Integer idProducto : pedidoDTO.getIdProducto()) {
            Producto producto = productoRepositorio.getReferenceById(idProducto);
            productos.add(producto);

        }
        entity.setProductos(productos);

        Pedido pedido = pedidoRepositorio.save(entity);

        return pedido;
    }


}