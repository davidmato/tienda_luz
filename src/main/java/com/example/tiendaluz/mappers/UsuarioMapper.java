package com.example.tiendaluz.mappers;


import com.example.tiendaluz.dto.UsuarioDTO;
import com.example.tiendaluz.modelos.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDTO(Usuario entity);

    List<Usuario> toEntity(List<UsuarioDTO> dtos);

    List<UsuarioDTO> toDTO(List<Usuario> entities);
}
