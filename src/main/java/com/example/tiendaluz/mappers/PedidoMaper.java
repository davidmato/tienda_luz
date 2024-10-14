//package com.example.tiendaluz.mappers;
//
//import com.example.tiendaluz.dto.PedidoDTO;
//import com.example.tiendaluz.dto.TipoPagoDTO;
//import com.example.tiendaluz.modelos.Pedido;
//import com.example.tiendaluz.modelos.TipoPago;
//import com.example.tiendaluz.services.TipoPagoServices;
//import lombok.AllArgsConstructor;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//@AllArgsConstructor
//@Mapper(componentModel = "spring")
//public abstract class PedidoMaper {
//
//    TipoPagoServices tipoPagoServices;
//
//    @Mapping(source = "tipoPago", target = "tipoPago", qualifiedByName = "pasarTipoPago")
//    public abstract Pedido toEntity(PedidoDTO dto);
//
//    @Named("pasarTipoPago")
//    public TipoPago getTipoPagoById(TipoPagoDTO tipoPagoDTO) {
//        return tipoPagoServices.getById(tipoPagoDTO.getId());
//    }
//}