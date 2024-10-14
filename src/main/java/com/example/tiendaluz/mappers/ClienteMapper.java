package com.example.tiendaluz.mappers;

import com.example.tiendaluz.dto.ClienteDTO;
import com.example.tiendaluz.modelos.Cliente;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteMapper {
    /**
     * Convierte un Cliente a ClienteDTO
     * @param dto
     * @return
     */
    Cliente toEntity(ClienteDTO dto);

    /**
     * Convierte un ClienteDTO a Cliente
     * @param entity
     * @return
     */
    ClienteDTO toDTO(Cliente entity);

}
