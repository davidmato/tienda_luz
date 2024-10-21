package com.example.tiendaluz.controladores;


import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.services.ProductoServices;
import com.example.tiendaluz.services.TipoTallaServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("/disponibilidad")
@RestController
public class DisponibilidadControlador {
    TipoTallaServices productoServices;



    @GetMapping("/{idProducto}/{talla}")
    public String getProductoCantidad(@PathVariable Integer idProducto, @PathVariable String talla) {
        return productoServices.cantidadProducto(idProducto, talla);
    }
}
