package com.example.tiendaluz.repositorios;


import com.example.tiendaluz.modelos.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoRepositorio extends JpaRepository<TipoPago, Integer> {

}
