package com.Perfulandia.ApiProductos.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ProveedorDTO extends RepresentationModel<ProveedorDTO> {
    private Integer idProveedor;
    private String nombreProveedor;
    private String razonSocial;
    private Integer telefono;
    private String email;
    private ComunaDTO comuna;
}