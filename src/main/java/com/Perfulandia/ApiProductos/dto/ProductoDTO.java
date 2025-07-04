package com.Perfulandia.ApiProductos.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ProductoDTO extends RepresentationModel<ProductoDTO> {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Integer costo;
    private MarcaDTO marca;
    private TipoProductoDTO tipoProducto;
    private ProveedorDTO proveedor;
}