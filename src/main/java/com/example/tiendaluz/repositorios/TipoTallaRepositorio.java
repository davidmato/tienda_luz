package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.TipoTalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoTallaRepositorio extends JpaRepository<TipoTalla, Integer> {

    List<TipoTalla> findAllByNombreEquals(String nombre);

    @Query("SELECT t FROM TipoTalla t WHERE t.nombre = :nombre AND t.producto.id = :productoId")
    TipoTalla findByNombreAndProductoId(@Param("nombre") String nombre, @Param("productoId") Integer productoId);

}