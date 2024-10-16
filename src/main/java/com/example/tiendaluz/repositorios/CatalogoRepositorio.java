package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CatalogoRepositorio extends JpaRepository<Catalogo, Integer> {
    List<Catalogo> findAllByPrecioEquals(Integer precio);



}
