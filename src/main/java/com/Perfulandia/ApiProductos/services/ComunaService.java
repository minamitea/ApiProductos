package com.Perfulandia.ApiProductos.services;

import com.Perfulandia.ApiProductos.dto.ComunaDTO;
import com.Perfulandia.ApiProductos.models.Comuna;
import com.Perfulandia.ApiProductos.repository.ComunaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private ProvinciaService provinciaService;

    public List<ComunaDTO> listarComunas() {
        return comunaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ComunaDTO obtenerComunaPorId(Integer id) {
        return toDTO(findComunaById(id));
    }

    // --- Métodos de ayuda ---
    
    public Comuna findComunaById(Integer id) {
        return comunaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comuna no encontrada con ID: " + id));
    }

    public ComunaDTO toDTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        dto.setProvincia(provinciaService.toDTO(comuna.getProvincia()));
        return dto;
        
    }
}
