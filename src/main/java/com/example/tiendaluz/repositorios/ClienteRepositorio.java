package com.example.tiendaluz.repositorios;

import com.example.tiendaluz.dto.PedidoDTO;
import com.example.tiendaluz.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {


    //JPA Interface select * from cliente where nombre = "";
    List<Cliente> findAllByNombreEquals(String nombre);


}
