package com.example.tiendaluz.controladores;

import com.example.tiendaluz.dto.PedidoDTO;
import com.example.tiendaluz.dto.TipoPagoDTO;
import com.example.tiendaluz.modelos.Pedido;
import com.example.tiendaluz.services.PedidoServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/pedido")
@AllArgsConstructor
public class    PedidoControlador {

    PedidoServices pedidoServices;


    //dto
    /**
     * buscar por dto
     */
    @GetMapping("/all")
    public List<PedidoDTO> getAllDTO() {
        return pedidoServices.getAllDTO();
    }

    /**
     * crear por dto
     */
    @PostMapping("/crear")
    public Pedido crearDTO(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoServices.crearDTO(pedidoDTO);
    }

}
