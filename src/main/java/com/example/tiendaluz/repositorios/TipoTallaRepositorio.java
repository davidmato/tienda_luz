package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.TipoTalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTallaRepositorio extends JpaRepository<TipoTalla, Integer> {
}
