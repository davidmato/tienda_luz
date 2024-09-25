package com.example.tiendaluz.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_pago", schema = "luz", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre_pago", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
