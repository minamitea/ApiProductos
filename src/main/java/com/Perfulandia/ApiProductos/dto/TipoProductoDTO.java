package com.Perfulandia.ApiProductos.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class TipoProductoDTO extends RepresentationModel<TipoProductoDTO> {
    private Integer idTipoProducto;
    private String nombreTipoproducto;
}
