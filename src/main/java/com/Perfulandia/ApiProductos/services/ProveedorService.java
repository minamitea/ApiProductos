package com.Perfulandia.ApiProductos.services;

import com.Perfulandia.ApiProductos.dto.ProveedorDTO;
import com.Perfulandia.ApiProductos.models.Comuna;
import com.Perfulandia.ApiProductos.models.Proveedor;
import com.Perfulandia.ApiProductos.repository.ProveedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ComunaService comunaService;

    public List<ProveedorDTO> listarProveedores() {
        return proveedorRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProveedorDTO obtenerProveedorPorId(Integer id) {
        return toDTO(findProveedorById(id));
    }

    public ProveedorDTO guardarProveedor(ProveedorDTO dto) {
        Comuna comuna = comunaService.findComunaById(dto.getComuna().getIdComuna());

        Proveedor proveedor = new Proveedor();
        proveedor.setNombreProveedor(dto.getNombreProveedor());
        proveedor.setRazonSocial(dto.getRazonSocial());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setEmail(dto.getEmail());
        proveedor.setComuna(comuna);

        return toDTO(proveedorRepository.save(proveedor));
    }

    public ProveedorDTO actualizarProveedor(Integer id, ProveedorDTO dto) {
        Proveedor proveedorExistente = findProveedorById(id);
        Comuna comuna = comunaService.findComunaById(dto.getComuna().getIdComuna());
        
        proveedorExistente.setNombreProveedor(dto.getNombreProveedor());
        proveedorExistente.setRazonSocial(dto.getRazonSocial());
        proveedorExistente.setTelefono(dto.getTelefono());
        proveedorExistente.setEmail(dto.getEmail());
        proveedorExistente.setComuna(comuna);

        return toDTO(proveedorRepository.save(proveedorExistente));
    }

    public void eliminarProveedor(Integer id) {
        if (!proveedorRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Proveedor no encontrado con ID: " + id);
        }
        proveedorRepository.deleteById(id);
    }
    
    // --- Métodos de ayuda ---

    public Proveedor findProveedorById(Integer id) {
        return proveedorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
    }

    public ProveedorDTO toDTO(Proveedor prov) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setIdProveedor(prov.getIdProveedor());
        dto.setNombreProveedor(prov.getNombreProveedor());
        dto.setRazonSocial(prov.getRazonSocial());
        dto.setTelefono(prov.getTelefono());
        dto.setEmail(prov.getEmail());
        dto.setComuna(comunaService.toDTO(prov.getComuna()));
        return dto;
    }
}