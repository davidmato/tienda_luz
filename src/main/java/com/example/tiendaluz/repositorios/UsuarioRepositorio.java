package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findTopByUsername(String username);

}
