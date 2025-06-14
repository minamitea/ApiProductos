package com.Perfulandia.ApiProductos.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PROVEEDOR")  
@Data
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "nombre_proveedor", nullable = false, length = 50)
    private String nombreProveedor;

    @Column(name = "razon_social", nullable = false, length = 150)
    private String razonSocial;

    @Column(nullable = false)
    private Integer telefono;

    @Column(nullable = false, length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMUNA_id_comuna", nullable = false)
    private Comuna comuna;
}