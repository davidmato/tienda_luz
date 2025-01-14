package com.example.tiendaluz.services;

import com.example.tiendaluz.dto.ClienteDTO;
import com.example.tiendaluz.dto.UsuarioDTO;
import com.example.tiendaluz.enumerados.Rol;
import com.example.tiendaluz.exceptions.ClienteReferencedException;
import com.example.tiendaluz.mappers.ClienteMapper;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.modelos.Pedido;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.Usuario;
import com.example.tiendaluz.repositorios.ClienteRepositorio;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
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


    private PasswordEncoder passwordEncoder;


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

    public List<Cliente>FindAll(){
        return clienteRepositorio.findAll();
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
        return clienteRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con el id: " + id));
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




    public Cliente save(ClienteDTO dto){
        Cliente entity = new Cliente();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setDni(dto.getDni());
        entity.setDireccion(dto.getDireccion());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsuarioDTO().getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getUsuarioDTO().getPassword()));
        usuario.setRol(dto.getUsuarioDTO().getRol() != null ? Rol.valueOf(dto.getUsuarioDTO().getRol()) : Rol.CLIENTE); // Set default role if not provided
        entity.setUsuario(usuario);

        return clienteRepositorio.save(entity);
    }


    public List<ClienteDTO> buscarClientePorFiltror(String nombre, String letraDNI) {
        List<Cliente> clientes;
        if (nombre != null && letraDNI != null) {
            clientes = clienteRepositorio.buscarPorLetraDNIYNombre(letraDNI, nombre);
        } else if (nombre == null && letraDNI != null) {
            clientes = clienteRepositorio.buscarPorLetraDNI(letraDNI);
        } else if (nombre != null) {
            clientes = clienteRepositorio.buscarPorNombre(nombre);
        } else {
            clientes = clienteRepositorio.findAll();
        }

        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        for (Cliente cliente : clientes) {
            ClienteDTO dto = new ClienteDTO();
            dto.setNombre(cliente.getNombre());
            dto.setApellido(cliente.getApellido());
            dto.setDni(cliente.getDni());
            dto.setDireccion(cliente.getDireccion());
            dto.setTelefono(cliente.getTelefono());
            dto.setEmail(cliente.getEmail());
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setUsername(cliente.getUsuario().getUsername());
            usuarioDTO.setPassword(cliente.getUsuario().getPassword());
            dto.setUsuarioDTO(usuarioDTO);
            clienteDTOS.add(dto);
        }
        return clienteDTOS;
    }




}
