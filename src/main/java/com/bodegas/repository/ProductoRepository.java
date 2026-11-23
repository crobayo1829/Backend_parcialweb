package com.bodegas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bodegas.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByBodegaId(Long bodegaId);
}
