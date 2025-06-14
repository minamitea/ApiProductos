package com.Perfulandia.ApiProductos.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Integer costo;
    private MarcaDTO marca;
    private TipoProductoDTO tipoProducto;
    private ProveedorDTO proveedor;
}