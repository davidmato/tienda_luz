package com.example.tiendaluz.controladores;

import com.example.tiendaluz.dto.DetallesVentaDTO;
import com.example.tiendaluz.dto.PedidoDTO;
import com.example.tiendaluz.modelos.DetallesVenta;
import com.example.tiendaluz.services.DetallesVentaServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallesventa")
@AllArgsConstructor
public class DetallesVentaControlador {

    DetallesVentaServices detallesVentaServices;

    @GetMapping("/all")
    public List<DetallesVenta> getAll() {
        return detallesVentaServices.getAll();
    }

    @GetMapping("/pedidosbycliente/{idCliente}")
    public List<DetallesVentaDTO> getAllDTOByIdCliente(@PathVariable Integer idCliente) {
        return detallesVentaServices.getAllDTOByIdCliente(idCliente);
    }


}

