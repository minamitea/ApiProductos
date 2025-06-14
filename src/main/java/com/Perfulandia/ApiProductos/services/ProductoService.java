package com.Perfulandia.ApiProductos.services;

import com.Perfulandia.ApiProductos.dto.ProductoDTO;
import com.Perfulandia.ApiProductos.models.Marca;
import com.Perfulandia.ApiProductos.models.Producto;
import com.Perfulandia.ApiProductos.models.Proveedor;
import com.Perfulandia.ApiProductos.models.TipoProducto;
import com.Perfulandia.ApiProductos.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired private ProductoRepository productoRepository;
    @Autowired private MarcaService marcaService;
    @Autowired private TipoProductoService tipoProductoService;
    @Autowired private ProveedorService proveedorService;

    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProductoDTO obtenerProductoPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        return toDTO(producto);
    }

    public ProductoDTO guardarProducto(ProductoDTO dto) {
        Marca marca = marcaService.findMarcaById(dto.getMarca().getIdMarca());
        TipoProducto tipoProducto = tipoProductoService.findTipoProductoById(dto.getTipoProducto().getIdTipoProducto());
        Proveedor proveedor = proveedorService.findProveedorById(dto.getProveedor().getIdProveedor());

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCosto(dto.getCosto());
        producto.setMarca(marca);
        producto.setTipoProducto(tipoProducto);
        producto.setProveedor(proveedor);

        return toDTO(productoRepository.save(producto));
    }

    public ProductoDTO actualizarProducto(Integer id, ProductoDTO dto) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        
        Marca marca = marcaService.findMarcaById(dto.getMarca().getIdMarca());
        TipoProducto tipoProducto = tipoProductoService.findTipoProductoById(dto.getTipoProducto().getIdTipoProducto());
        Proveedor proveedor = proveedorService.findProveedorById(dto.getProveedor().getIdProveedor());

        productoExistente.setNombre(dto.getNombre());
        productoExistente.setDescripcion(dto.getDescripcion());
        productoExistente.setPrecio(dto.getPrecio());
        productoExistente.setCosto(dto.getCosto());
        productoExistente.setMarca(marca);
        productoExistente.setTipoProducto(tipoProducto);
        productoExistente.setProveedor(proveedor);

        return toDTO(productoRepository.save(productoExistente));
    }

    public void eliminarProducto(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
    
    // --- Método de ayuda ---
    
    public ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setCosto(producto.getCosto());
        dto.setMarca(marcaService.toDTO(producto.getMarca()));
        dto.setTipoProducto(tipoProductoService.toDTO(producto.getTipoProducto()));
        dto.setProveedor(proveedorService.toDTO(producto.getProveedor()));
        return dto;
    }
}