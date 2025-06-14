package com.Perfulandia.ApiProductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Perfulandia.ApiProductos.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}