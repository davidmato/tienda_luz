package com.example.tiendaluz.modelos;

import com.example.tiendaluz.enumerados.Rol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario", schema = "luz", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="rol")
    @Enumerated(EnumType.ORDINAL)
    private Rol rol;


}
