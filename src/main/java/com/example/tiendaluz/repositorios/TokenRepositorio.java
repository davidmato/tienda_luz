package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.Token;
import com.example.tiendaluz.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepositorio extends JpaRepository<Token,Integer> {
    Token findTopByUsuario(Usuario usuario);

}
