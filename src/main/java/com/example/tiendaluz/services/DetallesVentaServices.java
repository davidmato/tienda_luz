package com.example.tiendaluz.services;


import com.example.tiendaluz.dto.*;
import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.DetallesVentaRepositorio;
import com.example.tiendaluz.repositorios.PedidoRepositorio;
import com.example.tiendaluz.repositorios.TipoPagoRepositorio;
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

                pedidoDTO.setTipoPago(tipoPagoDTO);
                pedidoDTO.setCliente(clienteDTO);

                dto.setProducto(productoDTO);
                dto.setPedido(pedidoDTO);

                detallesVentaDTOS.add(dto);
            }
        }
        return detallesVentaDTOS;
    }


    /**
     *Metodo para crear un pedido para un cliente, con productos y tipo de pago
     */

    public DetallesVentaDTO crearDTO(DetallesVentaDTO dto) {
        DetallesVenta entity = new DetallesVenta();
        entity.setCantidad(dto.getCantidad());
        entity.setPrecioUnitario(dto.getPrecioUnitario());

        // Map ProductoDTO to Producto entity
        Producto producto = new Producto();
        producto.setNombre(dto.getProducto().getNombre());
        producto.setDescripcion(dto.getProducto().getDescripcion());
        producto.setUnidades(dto.getProducto().getUnidades());
        producto.setColor(dto.getProducto().getColor());
        entity.setProducto(producto);

        // Map PedidoDTO to Pedido entity
        Pedido pedido = new Pedido();
        pedido.setPrecio(dto.getPedido().getPrecio());
        pedido.setFecha(dto.getPedido().getFecha());

        // Map ClienteDTO to Cliente entity
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getPedido().getCliente().getNombre());
        cliente.setApellido(dto.getPedido().getCliente().getApellido());
        cliente.setDni(dto.getPedido().getCliente().getDni());
        cliente.setDireccion(dto.getPedido().getCliente().getDireccion());
        cliente.setTelefono(dto.getPedido().getCliente().getTelefono());
        cliente.setEmail(dto.getPedido().getCliente().getEmail());
        pedido.setCliente(cliente);

        // Map TipoPagoDTO to TipoPago entity
        TipoPago tipoPago = new TipoPago();
        tipoPago.setNombre(dto.getPedido().getTipoPago().getNombre());
        pedido.setTipoPago(tipoPago);

        entity.setPedido(pedido);

        if (dto.getProducto() == null || dto.getPedido() == null) {
            throw new IllegalArgumentException("Producto id and Pedido id must not be null");
        }
        DetallesVenta detallesVenta = detallesVentaRepositorio.save(entity);

        return dto;
    }
}
