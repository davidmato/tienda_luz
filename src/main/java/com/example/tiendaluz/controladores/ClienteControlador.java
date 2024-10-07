package com.example.tiendaluz.controladores;


import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.services.ClienteServices;
import lombok.AllArgsConstructor;
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

}
