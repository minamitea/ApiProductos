package com.Perfulandia.ApiProductos.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class MarcaDTO extends RepresentationModel<MarcaDTO> {
    private Integer idMarca;
    private String nombreMarca;
}