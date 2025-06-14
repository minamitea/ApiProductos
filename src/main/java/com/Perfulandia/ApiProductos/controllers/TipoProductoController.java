package com.Perfulandia.ApiProductos.controllers;

import com.Perfulandia.ApiProductos.dto.TipoProductoDTO;
import com.Perfulandia.ApiProductos.services.TipoProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-producto")
public class TipoProductoController {

    @Autowired
    private TipoProductoService tipoProductoService;

    @GetMapping
    public ResponseEntity<List<TipoProductoDTO>> listar() {
        return ResponseEntity.ok(tipoProductoService.listarTiposProducto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProductoDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(tipoProductoService.obtenerTipoProductoPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoProductoDTO> crear(@RequestBody TipoProductoDTO dto) {
        return new ResponseEntity<>(tipoProductoService.guardarTipoProducto(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoProductoDTO> actualizar(@PathVariable Integer id, @RequestBody TipoProductoDTO dto) {
        try {
            return ResponseEntity.ok(tipoProductoService.actualizarTipoProducto(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            tipoProductoService.eliminarTipoProducto(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}