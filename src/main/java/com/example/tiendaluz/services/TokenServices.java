package com.example.tiendaluz.services;
import com.example.tiendaluz.modelos.Token;
import com.example.tiendaluz.modelos.Usuario;
import com.example.tiendaluz.repositorios.TokenRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServices {

    private final TokenRepositorio tokenRepositorio;


    public Token getByUsuario(Usuario usuario){
        return tokenRepositorio.findTopByUsuario(usuario);
    }

    public Token save(Token token){
        return tokenRepositorio.save(token);
    }

}