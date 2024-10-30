package com.example.tiendaluz.controladores;


import com.example.tiendaluz.dto.ClienteDTO;
import com.example.tiendaluz.dto.UsuarioDTO;
import com.example.tiendaluz.modelos.Cliente;
import com.example.tiendaluz.modelos.Token;
import com.example.tiendaluz.modelos.Usuario;
import com.example.tiendaluz.dto.AuthDTO;
import com.example.tiendaluz.security.JWTService;
import com.example.tiendaluz.services.ClienteServices;
import com.example.tiendaluz.services.TokenServices;
import com.example.tiendaluz.services.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private TokenServices tokenService;

    @Autowired
    private ClienteServices clienteService;


    @PostMapping("/login")
    public AuthDTO login(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario;
        try {
            usuario = (Usuario) usuarioService.loadUserByUsername(usuarioDTO.getUsername());
        } catch (UsernameNotFoundException e) {
            usuario = null;
        }

        String apiKey = null;
        String mensaje;

        if (usuario == null) {
            mensaje = "Usuario No encontrado";
        } else if (!usuarioService.validarPassword(usuario, usuarioDTO.getPassword())) {
            mensaje = "Contraseña no válida";
        } else {
            mensaje = "Usuario Logueado";

            // Usuario sin token
            if (usuario.getToken() == null) {
                apiKey = jwtService.generateToken(usuario);
                Token token = new Token();
                token.setUsuario(usuario);
                token.setToken(apiKey);
                token.setFechaExpiracion(LocalDateTime.now().plusDays(1));
                tokenService.save(token);

                // Usuario con token caducado
            } else if (usuario.getToken().getFechaExpiracion().isBefore(LocalDateTime.now())) {
                Token token = usuario.getToken();
                apiKey = jwtService.generateToken(usuario);
                token.setToken(apiKey);
                token.setFechaExpiracion(LocalDateTime.now().plusDays(1));
                tokenService.save(token);

                // Usuario con token válido
            } else {
                apiKey = usuario.getToken().getToken();
            }
        }

        return AuthDTO
                .builder()
                .token(apiKey)
                .info(mensaje)
                .build();
    }

    @PostMapping("/register")
    public AuthDTO register(@RequestBody ClienteDTO clienteDTO) {
        Cliente clienteNuevo = clienteService.save(clienteDTO);
        String tokenString = jwtService.generateToken(clienteNuevo.getUsuario());

        Token token = new Token();
        token.setUsuario(clienteNuevo.getUsuario());
        token.setToken(tokenString);
        token.setFechaExpiracion(LocalDateTime.now().plusDays(1));
        tokenService.save(token);

        return AuthDTO
                .builder()
                .token(tokenString)
                .info("Usuario creado correctamente")
                .build();
    }


}
