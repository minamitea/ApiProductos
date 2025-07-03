package com.Perfulandia.ApiProductos.controllers;

import com.Perfulandia.ApiProductos.dto.ProductoDTO;
import com.Perfulandia.ApiProductos.services.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productoService.obtenerProductoPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO dto) {
        return new ResponseEntity<>(productoService.guardarProducto(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Integer id, @RequestBody ProductoDTO dto) {
        try {
            return ResponseEntity.ok(productoService.actualizarProducto(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene un producto por su ID y le añade enlaces HATEOAS.
     */
    @GetMapping("/hateoas/{id}")
    public ProductoDTO obtenerHATEOAS(@PathVariable Integer id) {
        ProductoDTO dto = productoService.obtenerProductoPorId(id);
        String gatewayUrl = "http://localhost:8888/api/proxy/productos";

        dto.add(Link.of(gatewayUrl + "/hateoas/" + id).withSelfRel());
        dto.add(Link.of(gatewayUrl).withRel("todos-los-productos"));
        dto.add(Link.of(gatewayUrl + "/" + id).withRel("eliminar").withType("DELETE"));
        dto.add(Link.of(gatewayUrl + "/" + id).withRel("actualizar").withType("PUT"));

        return dto;
    }

    /**
     * Obtiene todos los productos y añade enlaces HATEOAS a cada uno.
     */
    @GetMapping("/hateoas")
    public List<ProductoDTO> listarHATEOAS() {
        List<ProductoDTO> productos = productoService.listarProductos();
        String gatewayUrl = "http://localhost:8888/api/proxy/productos";

        for (ProductoDTO dto : productos) {
            dto.add(Link.of(gatewayUrl + "/hateoas/" + dto.getIdProducto()).withSelfRel());
            dto.add(Link.of(gatewayUrl).withRel("crear-nuevo-producto").withType("POST"));
            dto.add(Link.of(gatewayUrl).withRel("editar-producto").withType("PUT"));
            dto.add(Link.of(gatewayUrl).withRel("eliminar-producto").withType("DELETE"));
        }
        return productos;
    }
}