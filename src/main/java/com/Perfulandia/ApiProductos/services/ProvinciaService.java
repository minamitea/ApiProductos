package com.Perfulandia.ApiProductos.services;

import com.Perfulandia.ApiProductos.dto.ProvinciaDTO;
import com.Perfulandia.ApiProductos.models.Provincia;
import com.Perfulandia.ApiProductos.repository.ProvinciaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public List<ProvinciaDTO> listarProvincias() {
        return provinciaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProvinciaDTO obtenerProvinciaPorId(Integer id) {
        return toDTO(findProvinciaById(id));
    }
    
    // --- Métodos de ayuda ---

    public Provincia findProvinciaById(Integer id) {
        return provinciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provincia no encontrada con ID: " + id));
    }
    
    public ProvinciaDTO toDTO(Provincia provincia) {
        ProvinciaDTO dto = new ProvinciaDTO();
        dto.setIdProvincia(provincia.getIdProvincia());
        dto.setNombreProvincia(provincia.getNombreProvincia());
        return dto;
    }
}