package com.Perfulandia.ApiProductos.repository;

import com.Perfulandia.ApiProductos.models.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
}