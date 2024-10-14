package com.example.tiendaluz.controladores;

import com.example.tiendaluz.dto.TipoTallaDTO;
import com.example.tiendaluz.modelos.TipoTalla;
import com.example.tiendaluz.services.TipoPagoServices;
import com.example.tiendaluz.services.TipoTallaServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tipo_talla")
public class TipoTallaControlador {

    TipoTallaServices tipoTallaServices;

    @GetMapping("/listar")
    public List<TipoTalla> getAllClientes() {
        List<TipoTalla> tipoTallas = tipoTallaServices.getAll();
        return tipoTallas;
    }

    @GetMapping()
    public TipoTalla getById(@RequestParam Integer id) {
        TipoTalla tipoTalla = tipoTallaServices.getById(id);
        return tipoTalla;
    }

    @GetMapping("/get/{id}")
    public TipoTalla getByIdPath(@PathVariable Integer id) {
        TipoTalla tipoTalla = tipoTallaServices.getById(id);
        return tipoTalla;
    }

    @PostMapping
    public TipoTalla guardar(@RequestBody TipoTalla tipoTalla) {
        TipoTalla tipoTallaGuardados = tipoTallaServices.guardar(tipoTalla);
        return tipoTallaGuardados;
    }

    @DeleteMapping
    public String eliminar(@RequestParam Integer id) {
        tipoTallaServices.eliminarPorId(id);
        return "Cliente eliminado";
    }

    //
    ////
    //////DTO
    ////
    //

    /**
     * buscar por dto
     */
    @GetMapping("/all")
    public List<TipoTallaDTO> getAllDTO() {
        return tipoTallaServices.getAllDTO();
    }

    /**
     * crear por dto
     */
    @PostMapping("/crear")
    public TipoTalla crear(@RequestBody TipoTalla tipoTalla) {
        return tipoTallaServices.guardar(tipoTalla);
    }

    /**
     * editar por dto
     */
    @PutMapping("/editar")
    public TipoTalla editar(@RequestBody TipoTalla tipoTalla) {
        return tipoTallaServices.guardar(tipoTalla);
    }

    /**
     * eliminar por dto
     */
    @DeleteMapping("/eliminar")
    public void eliminarDTO(@RequestParam Integer id) {
        tipoTallaServices.eliminarPorId(id);
    }





}
