package com.example.tiendaluz.controladores;


import com.example.tiendaluz.dto.ClienteDTO;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.services.ClienteServices;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor //cuando se pone no hace falta el autowired
public class ClienteControlador {

    private ClienteServices clienteServices;

    @GetMapping("/listar")
    public List<Cliente> getAllClientes() {
        List<Cliente> cliente = clienteServices.getAll();
        return cliente;

    }

    @GetMapping()
    public Cliente getById(@RequestParam Integer id){
        Cliente cliente = clienteServices.getById(id);
        return cliente;
    }

    @GetMapping("/get/{id}")
    public Cliente getByIdPath(@PathVariable Integer id){
        Cliente cliente = clienteServices.getById(id);
        return cliente;
    }


    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente){
        Cliente clienteGuaradado = clienteServices.guardar(cliente);
        return clienteGuaradado;
    }

    @DeleteMapping
    public String eliminar(@RequestParam Integer id){
        clienteServices.eliminarPorID(id);
        return "Cliente eliminado";
    }
    //
    ////
    //////DTO
    ////
    //

    /**
     * buscar por dto
     * @return
     */
    @GetMapping("/all")
    public List<ClienteDTO>getAllClientesDTO(){
        List<ClienteDTO>clientes= clienteServices.getAllDTO();
        return clientes;
    }


    /**
     * crear por dto
     * @param clienteDTO
     * @return
     */

    @PostMapping("/crear")
    public Cliente crearCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteServices.crearCliente(clienteDTO);

    }

    /**
     * editar por dto
     * @param clienteDTO
     * @param id
     * @return
     */

    @PutMapping("/editar/{id}")
    public Cliente editarCliente(@RequestBody ClienteDTO clienteDTO,
                                 @PathVariable Integer id){

        return clienteServices.editarCliente(clienteDTO, id);

    }

    /**
     * eliminar por id
     * @param id
     * @return
     */
    @DeleteMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id){
        return clienteServices.eliminarCliente(id);
    }

}
