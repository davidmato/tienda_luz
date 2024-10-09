package com.example.tiendaluz.controladores;

import com.example.tiendaluz.dto.TipoPagoDTO;
import com.example.tiendaluz.modelos.Producto;
import com.example.tiendaluz.modelos.TipoPago;
import com.example.tiendaluz.services.TipoPagoServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo_pago")
@AllArgsConstructor
public class TipoPagoControlador {
    private TipoPagoServices tipoPagoServices;


    @GetMapping("/listar")
    public List<TipoPago> getAllClientes() {
        List<TipoPago> tipoPagos = tipoPagoServices.getAll();
        return tipoPagos;

    }

    @GetMapping()
    public TipoPago getById(@RequestParam Integer id){
        TipoPago tipoPago = tipoPagoServices.getById(id);
        return tipoPago;
    }

    @GetMapping("/get/{id}")
    public TipoPago getByIdPath(@PathVariable Integer id){
        TipoPago tipoPago = tipoPagoServices.getById(id);
        return tipoPago;
    }


    @PostMapping
    public TipoPago guardar(@RequestBody TipoPago tipoPago){
        TipoPago tipoPagoGuardados = tipoPagoServices.guardar(tipoPago);
        return tipoPagoGuardados;
    }

    @DeleteMapping
    public String eliminar(@RequestParam Integer id){
        tipoPagoServices.eliminarPorID(id);
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
    public List<TipoPagoDTO> getAllDTO() {
        return tipoPagoServices.getAllDTO();
    }

    /**
     * crear por dto
     */
    @PostMapping("/crear")
    public TipoPago crear(@RequestBody TipoPagoDTO tipoPagoDTO) {
        return tipoPagoServices.guardarPorDTO(tipoPagoDTO);
    }

    /**
     * editar por dto
     */
    @PutMapping("/editar")
    public TipoPago editar(@RequestBody TipoPagoDTO tipoPagoDTO, @PathVariable Integer id) {
        return tipoPagoServices.editarPorDTO(tipoPagoDTO,id);
    }

    /**
     * eliminar por dto
     */
    @DeleteMapping("/eliminar")
    public void eliminarPorDTO(@RequestParam Integer id) {
        tipoPagoServices.eliminarPorID(id);
    }





}
