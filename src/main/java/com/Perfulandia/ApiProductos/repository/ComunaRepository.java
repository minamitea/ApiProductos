package com.Perfulandia.ApiProductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Perfulandia.ApiProductos.models.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer>{

}
