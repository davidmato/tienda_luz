package com.example.tiendaluz.controladores;

import com.example.tiendaluz.dto.CatalogoDTO;
import com.example.tiendaluz.modelos.Catalogo;
import com.example.tiendaluz.services.CatalogoServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogo")
@AllArgsConstructor
public class CatalogoControlador {
    CatalogoServices catalogoServices;

    /**
     * Busca todos los catalogos con todas las cosas incluidas
     */

    @GetMapping("/all")
    public List<CatalogoDTO> getAllDTO() {
        return catalogoServices.getAllDTO();
    }
}