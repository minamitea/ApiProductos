package com.Perfulandia.ApiProductos.services;

import com.Perfulandia.ApiProductos.dto.MarcaDTO;
import com.Perfulandia.ApiProductos.models.Marca;
import com.Perfulandia.ApiProductos.repository.MarcaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<MarcaDTO> listarMarcas() {
        return marcaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MarcaDTO obtenerMarcaPorId(Integer id) {
        Marca marca = findMarcaById(id);
        return toDTO(marca);
    }

    public MarcaDTO guardarMarca(MarcaDTO dto) {
        Marca marca = new Marca();
        marca.setNombreMarca(dto.getNombreMarca());
        return toDTO(marcaRepository.save(marca));
    }

    public MarcaDTO actualizarMarca(Integer id, MarcaDTO dto) {
        Marca marcaExistente = findMarcaById(id);
        marcaExistente.setNombreMarca(dto.getNombreMarca());
        return toDTO(marcaRepository.save(marcaExistente));
    }

    public void eliminarMarca(Integer id) {
        if (!marcaRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Marca no encontrada con ID: " + id);
        }
        marcaRepository.deleteById(id);
    }
    
    // --- Métodos de ayuda ---
    
    // Lo hacemos público para que otros servicios puedan usarlo.
    public MarcaDTO toDTO(Marca marca) {
        MarcaDTO dto = new MarcaDTO();
        dto.setIdMarca(marca.getIdMarca());
        dto.setNombreMarca(marca.getNombreMarca());
        return dto;
    }
    
    // Lo hacemos público para que otros servicios puedan usarlo.
    public Marca findMarcaById(Integer id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Marca no encontrada con ID: " + id));
    }
}