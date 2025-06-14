package com.Perfulandia.ApiProductos.services;

import com.Perfulandia.ApiProductos.dto.TipoProductoDTO;
import com.Perfulandia.ApiProductos.models.TipoProducto;
import com.Perfulandia.ApiProductos.repository.TipoProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoProductoService {

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    public List<TipoProductoDTO> listarTiposProducto() {
        return tipoProductoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }



    public TipoProductoDTO obtenerTipoProductoPorId(Integer id) {
        TipoProducto tipo = findTipoProductoById(id);
        return toDTO(tipo);
    }

    public TipoProductoDTO guardarTipoProducto(TipoProductoDTO dto) {
        TipoProducto tipo = new TipoProducto();
        tipo.setNombreTipoproducto(dto.getNombreTipoproducto());
        return toDTO(tipoProductoRepository.save(tipo));
    }

    public TipoProductoDTO actualizarTipoProducto(Integer id, TipoProductoDTO dto) {
        TipoProducto tipoExistente = findTipoProductoById(id);
        tipoExistente.setNombreTipoproducto(dto.getNombreTipoproducto());
        return toDTO(tipoProductoRepository.save(tipoExistente));
    }

    public void eliminarTipoProducto(Integer id) {
        if (!tipoProductoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Tipo de producto no encontrado con ID: " + id);
        }
        tipoProductoRepository.deleteById(id);
    }
    
    // --- Métodos de ayuda ---

    public TipoProductoDTO toDTO(TipoProducto tipo) {
        TipoProductoDTO dto = new TipoProductoDTO();
        dto.setIdTipoProducto(tipo.getIdTipoProducto());
        dto.setNombreTipoproducto(tipo.getNombreTipoproducto());
        return dto;
    }

    public TipoProducto findTipoProductoById(Integer id) {
        return tipoProductoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de producto no encontrado con ID: " + id));
    }
}