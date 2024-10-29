package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
}
