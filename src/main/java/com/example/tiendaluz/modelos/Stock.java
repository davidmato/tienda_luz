package com.example.tiendaluz.modelos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock", schema = "luz", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode


public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cantidad_disponible", nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "tipo_talla")
    private TipoTalla tipoTalla;

}
