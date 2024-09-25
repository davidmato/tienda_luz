package com.example.tiendaluz.repositorios;


import com.example.tiendaluz.modelos.DetallesVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesVentaRepositorio extends JpaRepository<DetallesVenta, Integer> {
}
