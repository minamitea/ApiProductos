package com.Perfulandia.ApiProductos.dto;

import lombok.Data;

@Data
public class ProveedorDTO {
    private Integer idProveedor;
    private String nombreProveedor;
    private String razonSocial;
    private Integer telefono;
    private String email;
    private ComunaDTO comuna;
}