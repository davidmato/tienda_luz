package com.example.tiendaluz;


import com.example.tiendaluz.dto.UsuarioDTO;
import com.example.tiendaluz.enumerados.Rol;
import com.example.tiendaluz.modelos.Usuario;
import com.example.tiendaluz.repositorios.UsuarioRepositorio;
import com.example.tiendaluz.services.UsuarioService;
import org.hibernate.annotations.Array;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class UsuarioTest {

    @Autowired
    private UsuarioService usuarioServices;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @BeforeEach
    public void inicializarDatos() {
        Usuario usuario1 = new Usuario();
        usuario1.setUsername("usuario1");
        usuario1.setPassword("password1");
        usuario1.setRol(Rol.CLIENTE);

        Usuario usuario2 = new Usuario();
        usuario2.setUsername("usuario2");
        usuario2.setPassword("password2");
        usuario2.setRol(Rol.CLIENTE);

        usuarioRepositorio.save(usuario1);
        usuarioRepositorio.save(usuario2);
    }


    @Test
    public void TestFindAll() {
        //GIVEN

        //WHEN
        List<Usuario> usuarios = usuarioServices.findAll();

        //THEN
//        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
    }

    @Test
    public void TestGuardarUsuario() {
        //GIVEN
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsername("usuario1");
        usuarioDTO.setPassword("password1");
        usuarioDTO.setRol("CLIENTE");

        //WHEN
        Usuario usuario = usuarioServices.guardarUsuario(usuarioDTO);

        //THEN
        assertNotNull(usuario);
//        assertEquals("usuario1", usuario.getUsername());
    }
}
