package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.TipoTalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoTallaRepositorio extends JpaRepository<TipoTalla, Integer> {

    List<TipoTalla> findAllByNombreEquals(String nombre);

    List<TipoTalla> findByNombreAndProductoId(String nombre, Integer idProducto);}
