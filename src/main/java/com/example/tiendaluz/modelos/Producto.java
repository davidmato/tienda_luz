package com.example.tiendaluz.modelos;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto", schema = "luz", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "unidades", nullable = false)
    private Integer unidades;

    @Column(name = "color", nullable = false)
    private String color;


//    @OneToMany(targetEntity = TipoTalla.class, mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<TipoTalla> tipoTalla;



}