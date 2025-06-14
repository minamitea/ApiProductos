package com.Perfulandia.ApiProductos.dto;

import lombok.Data;

@Data
public class ComunaDTO {
    private Integer idComuna;
    private String nombreComuna;
    private ProvinciaDTO provincia;
}
