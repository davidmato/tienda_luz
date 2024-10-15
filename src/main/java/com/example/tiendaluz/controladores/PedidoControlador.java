package com.example.tiendaluz.controladores;

import com.example.tiendaluz.dto.ClienteDTO;
import com.example.tiendaluz.dto.PedidoDTO;
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
//    /**
//     * buscar por dto
//     */
//    @GetMapping("/all")
//    public List<PedidoDTO> getAllDTO() {
//        return pedidoServices.getAllDTO();
//    }
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





}
