package com.example.tiendaluz;

import com.example.tiendaluz.modelos.*;
import com.example.tiendaluz.repositorios.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TiendaLuzApplicationTests {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private DetallesVentaRepositorio detallesVentaRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private TipoPagoRepositorio tipoPagoRepositorio;



    @Test
    void contextLoads() {

        List<Cliente> clientes = clienteRepositorio.findAll();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        List<DetallesVenta> detallesVentas = detallesVentaRepositorio.findAll();
        for (DetallesVenta detallesVenta : detallesVentas) {
            System.out.println(detallesVenta);
        }

        List<Pedido> pedidos = pedidoRepositorio.findAll();
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }

        List<Producto> productos = productoRepositorio.findAll();
        for (Producto producto : productos) {
            System.out.println(producto);
        }
//
        List<TipoPago> tipoPagos = tipoPagoRepositorio.findAll();
        for (TipoPago tipoPago : tipoPagos) {
            System.out.println(tipoPago);
        }

    }

}
