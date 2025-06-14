package com.Perfulandia.ApiProductos.controllers;

import com.Perfulandia.ApiProductos.dto.MarcaDTO;
import com.Perfulandia.ApiProductos.services.MarcaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> listar() {
        return ResponseEntity.ok(marcaService.listarMarcas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(marcaService.obtenerMarcaPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> crear(@RequestBody MarcaDTO dto) {
        return new ResponseEntity<>(marcaService.guardarMarca(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> actualizar(@PathVariable Integer id, @RequestBody MarcaDTO dto) {
        try {
            return ResponseEntity.ok(marcaService.actualizarMarca(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            marcaService.eliminarMarca(id);
            return ResponseEntity.noContent().build(); // HTTP 204
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}