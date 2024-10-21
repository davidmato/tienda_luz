package com.example.tiendaluz.controladores;

import com.example.tiendaluz.dto.ClienteDTO;
import com.example.tiendaluz.dto.CrearPedidoDTO;
import com.example.tiendaluz.dto.PedidoDTO;
import com.example.tiendaluz.modelos.DetallesVenta;
import com.example.tiendaluz.modelos.Pedido;
import com.example.tiendaluz.services.PedidoServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/pedido")
@AllArgsConstructor
public class PedidoControlador {

    PedidoServices pedidoServices;


    //dto
    /**
     * buscar por dto
     */
    @GetMapping("/all")
    public List<Pedido> getAll() {
        return pedidoServices.getAll();
    }
//
//    /**
//     * crear por dto
//     */
//    @PostMapping("/crear")
//    public PedidoDTO crearDTO(@RequestBody PedidoDTO pedidoDTO) {
//        return pedidoServices.crearDTO(pedidoDTO);
//    }
//
//    /**
//     * editar por dto
//     */
//    @PutMapping("/editar/{id}")
//    public PedidoDTO editarDTO(@RequestBody PedidoDTO pedidoDTO,
//                               @PathVariable Integer id) {
//        return pedidoServices.editar(pedidoDTO,id);
//    }

    @PostMapping("/crear")
    public Pedido crearDetallesVenta(@RequestBody CrearPedidoDTO pedidoDTO) {
        return pedidoServices.crearPedido(pedidoDTO);

    }



}
