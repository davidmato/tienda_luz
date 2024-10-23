package com.example.tiendaluz.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "linea_pedido", schema = "luz", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_compra", nullable = false)
    private Double precioCompra;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;


    @ManyToOne
    @JoinColumn(name = "id_tipo_talla")
    private TipoTalla tipoTalla;
}
