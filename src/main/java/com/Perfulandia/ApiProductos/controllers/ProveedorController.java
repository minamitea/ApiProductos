package com.Perfulandia.ApiProductos.controllers;

import com.Perfulandia.ApiProductos.dto.ProveedorDTO;
import com.Perfulandia.ApiProductos.services.ProveedorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> listar() {
        return ResponseEntity.ok(proveedorService.listarProveedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(proveedorService.obtenerProveedorPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> crear(@RequestBody ProveedorDTO dto) {
        return new ResponseEntity<>(proveedorService.guardarProveedor(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizar(@PathVariable Integer id, @RequestBody ProveedorDTO dto) {
        try {
            return ResponseEntity.ok(proveedorService.actualizarProveedor(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        try {
            proveedorService.eliminarProveedor(id);
            return ResponseEntity.noContent().build(); // HTTP 204 No Content si fue exitoso
        
        } catch (EntityNotFoundException e) {
            // Este error es cuando el proveedor ni siquiera existe
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found

        } catch (DataIntegrityViolationException e) {
            // ¡Aquí atrapamos el nuevo error!
            // Este error ocurre cuando el proveedor existe pero no se puede borrar.
            // Devolvemos un 409 Conflict, que es el código correcto para esta situación.
            String mensaje = "No se puede eliminar el proveedor porque tiene productos asociados.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", mensaje));
        }
    }
}