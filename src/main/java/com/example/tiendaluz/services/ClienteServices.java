package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.ClienteDTO;
import com.example.tiendaluz.exceptions.ClienteReferencedException;
import com.example.tiendaluz.mappers.ClienteMapper;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.modelos.Pedido;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.repositorios.ClienteRepositorio;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServices {

    private ClienteMapper clienteMapper;
    private ClienteRepositorio clienteRepositorio;
    private static final Logger logger = LoggerFactory.getLogger(ClienteServices.class);




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
            clienteDTOS.add(clienteMapper.toDTO(c));
        }
        return clienteDTOS;

    }

    /**
     * Mensaje indicando si se ha podidio eliminar o no el cliente
     */

    public String eliminarCliente(Integer id) {
        String mensaje;
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);

        if (cliente == null) {
            return "El cliente con el id indicado no existe";
        }
        try {
            clienteRepositorio.deleteById(id);
            mensaje = "Cliente eliminado correctamente";
        } catch (DataIntegrityViolationException e) {
            logger.error("Error al eliminar el cliente con id: {}", id, e);
            throw new ClienteReferencedException("No se puede eliminar el Cliente: está referenciado por otras entidades");
        }

        return mensaje;
    }


    public void eliminar(Cliente cliente){
        clienteRepositorio.delete(cliente);
    }


}
