package com.example.tiendaluz.repositorios;


import com.example.tiendaluz.modelos.DetallesVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallesVentaRepositorio extends JpaRepository<DetallesVenta, Integer> {

}
