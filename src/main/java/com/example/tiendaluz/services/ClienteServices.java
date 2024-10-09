package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.ClienteDTO;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.repositorios.ClienteRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServices {
    private ClienteRepositorio clienteRepositorio;


    /*

     */


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

    //
    ////
    //////DTO
    ////
    //

    /**
     * Crear un cliente
     */
    public  Cliente crearCliente (ClienteDTO dto){
        Cliente entity = new Cliente();
        entity.setEmail(dto.getEmail());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setDni(dto.getDni());
        entity.setDireccion(dto.getDireccion());
        entity.setTelefono(dto.getTelefono());
        return clienteRepositorio.save(entity);

    }

    /**
     * Editar un cliente
     */

    public  Cliente editarCliente (ClienteDTO dto, Integer id) {
        Cliente entity = clienteRepositorio.getReferenceById(id);

        entity.setEmail(dto.getEmail());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setDni(dto.getDni());
        entity.setDireccion(dto.getDireccion());
        entity.setTelefono(dto.getTelefono());
        return clienteRepositorio.save(entity);
    }

    /**
     * mostrar
     */
    public List<ClienteDTO>getAllDTO    (){
        List<Cliente> clientes= clienteRepositorio.findAll();
        List<ClienteDTO> clienteDTOS= new ArrayList<>();

        for (Cliente c:clientes){
            ClienteDTO dto= new ClienteDTO();
            dto.setNombre(c.getNombre());
            dto.setApellido(c.getApellido());
            dto.setDni(c.getDni());
            dto.setTelefono(c.getTelefono());
            dto.setDireccion(c.getDireccion());
            dto.setEmail(c.getEmail());
            clienteDTOS.add(dto);
        }
        return clienteDTOS;

    }

}
