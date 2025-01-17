package com.example.tiendaluz.modelos;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_talla", schema = "luz")//, catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class TipoTalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Producto producto;


    //importante poner el JsonBackReference para evitar el loop infinito
    @JsonBackReference
    @OneToMany(mappedBy = "tipoTalla", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stocks = new ArrayList<>();
    @JsonBackReference
    @OneToMany(mappedBy = "tipoTalla", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Catalogo> catalogos = new ArrayList<>();

//    @OneToMany(mappedBy = "tipoTalla", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<LineaPedido> lineaPedidos = new ArrayList<>();



}
