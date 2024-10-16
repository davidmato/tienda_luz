package com.example.tiendaluz.services;


import com.example.tiendaluz.dto.*;
import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DetallesVentaServices {
    private DetallesVentaRepositorio detallesVentaRepositorio;
    private PedidoRepositorio pedidoRepositorio;
    private TipoPagoRepositorio tipoPagoRepositorio;
    private ProductoRepositorio productoRepositorio;
    private ClienteRepositorio clienteRepositorio;

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


    /**
     *Metodo para obtener todos los detalles de venta de un cliente
     */
    public List<DetallesVentaDTO> getAllDTOByIdCliente(Integer idCliente) {
        List<DetallesVenta> detallesVentas = detallesVentaRepositorio.findByPedido_Cliente_Id(idCliente);
        List<DetallesVentaDTO> detallesVentaDTOS = new ArrayList<>();
        List<Pedido> pedidos = pedidoRepositorio.findAllByClienteId(idCliente);
        for (Pedido pedido : pedidos) {
            for (DetallesVenta detallesVenta : detallesVentas) {
                DetallesVentaDTO dto = new DetallesVentaDTO();
                dto.setCantidad(detallesVenta.getCantidad());
                dto.setPrecioUnitario(detallesVenta.getPrecioUnitario());

                Producto producto = detallesVenta.getProducto();
                ProductoDTO productoDTO = new ProductoDTO();
                productoDTO.setNombre(producto.getNombre());
                productoDTO.setDescripcion(producto.getDescripcion());
                productoDTO.setUnidades(producto.getUnidades());
                productoDTO.setColor(producto.getColor());

                PedidoDTO pedidoDTO = new PedidoDTO();
                pedidoDTO.setPrecio(pedido.getPrecio());
                pedidoDTO.setFecha(pedido.getFecha());

                TipoPago tipoPago = pedido.getTipoPago();
                TipoPagoDTO tipoPagoDTO = new TipoPagoDTO();
                tipoPagoDTO.setNombre(tipoPago.getNombre());

                Cliente cliente = pedido.getCliente();
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setNombre(cliente.getNombre());
                clienteDTO.setApellido(cliente.getApellido());
                clienteDTO.setDni(cliente.getDni());
                clienteDTO.setDireccion(cliente.getDireccion());
                clienteDTO.setTelefono(cliente.getTelefono());
                clienteDTO.setEmail(cliente.getEmail());

                pedidoDTO.setIdtipoPago(tipoPagoDTO);
                pedidoDTO.setIdcliente(clienteDTO);

                dto.setProducto(productoDTO);
                dto.setPedido(pedidoDTO);

                detallesVentaDTOS.add(dto);



            }
        }
        return detallesVentaDTOS;
    }



}
