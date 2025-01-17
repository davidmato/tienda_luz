package com.example.tiendaluz.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;import com.fasterxml.jackson.annotation.JsonManagedReference;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pedido", schema = "luz")//, catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_tipo_pago")
    @JsonManagedReference
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;


//    @JsonBackReference
//    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<DetallesVenta> detallesVenta = new ArrayList<>();


//    @JsonBackReference
//    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<LineaPedido> lineaPedido = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "id_producto")
//    private Producto producto;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Producto.class)
    @JoinTable(name = "pedido_producto", schema = "luz",catalog = "postgres",
            joinColumns = {@JoinColumn(name = "id_pedido", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_producto",nullable = false)})
    private Set<Producto> productos = new HashSet<>(0);


}
