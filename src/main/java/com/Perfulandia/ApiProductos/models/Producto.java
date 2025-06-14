package com.Perfulandia.ApiProductos.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PRODUCTO")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    private Integer costo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MARCA_id_marca", nullable = false)
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TIPO_PRODUCTO_id_tipo_producto", nullable = false)
    private TipoProducto tipoProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVEEDOR_id_proveedor", nullable = false)
    private Proveedor proveedor;
}