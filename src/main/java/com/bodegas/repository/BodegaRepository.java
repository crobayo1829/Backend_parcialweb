package com.bodegas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bodegas.entity.Bodega;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    /**
     * Trae las bodegas junto con sus productos en una sola consulta (JOIN FETCH),
     * evitando el problema N+1 al construir la respuesta de GET /bodegas.
     */
    @Query("SELECT DISTINCT b FROM Bodega b LEFT JOIN FETCH b.productos ORDER BY b.id")
    List<Bodega> findAllConProductos();
}
