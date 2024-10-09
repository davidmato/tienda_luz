package com.example.tiendaluz.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "catalogo", schema = "luz", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "precio", nullable = false)
    private Double precio;


    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_tipo_talla")
    private TipoTalla tipoTalla;







}
