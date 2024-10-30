package com.example.tiendaluz.controladores;

import com.example.tiendaluz.dto.UsuarioDTO;
import com.example.tiendaluz.modelos.Usuario;
import com.example.tiendaluz.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    private UsuarioService usuarioService;


    @PostMapping("/registrar")
    public Usuario registrarUsuario(UsuarioDTO dto){
        return usuarioService.guardarUsuario(dto);
    }


}
