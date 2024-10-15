package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.modelos.Pedido;
import com.example.tiendaluz.modelos.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByTipoPago(TipoPago tipoPago);

    List<Pedido> findAllByClienteId(Integer idCliente);


}
