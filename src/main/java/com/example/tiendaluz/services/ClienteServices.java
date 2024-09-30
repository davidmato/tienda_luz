package com.example.tiendaluz.services;

import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.repositorios.ClienteRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServices {
    private ClienteRepositorio clienteRepositorio;

    /**
     * Busca cliente por nombre
     * param nombre
     */


    public List<Cliente> buscarPorNombre(String nombre) {
        List<Cliente> clientes = clienteRepositorio.findAllByNombreEquals(nombre);
        return clientes;
    }

    /**
     * Buscar todas las cliente
     */
    public List<Cliente> getAll() {
        return clienteRepositorio.findAll();

    }

    /**
     * Buscar cliente por id
     */
    public Cliente getById(Integer id) {
        return clienteRepositorio.findById(id).orElse(null);
    }


    /**
     * crea una cliente nueva o modifica una existente
     */

    public Cliente guardar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }


    /**
     * eliminar una cliente por id
     */

    public void eliminarPorID(Integer id) {
        clienteRepositorio.deleteById(id);
    }

    /**
     * eliminar una cliente
     */

    public void eliminar(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }
}
