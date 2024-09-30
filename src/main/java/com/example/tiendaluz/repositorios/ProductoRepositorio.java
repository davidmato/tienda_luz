package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
    List<Producto> findAllByNombreEquals(String nombre);
}
