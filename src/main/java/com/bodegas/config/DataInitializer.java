package com.bodegas.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bodegas.entity.Bodega;
import com.bodegas.entity.Producto;
import com.bodegas.repository.BodegaRepository;
import com.bodegas.repository.ProductoRepository;

/**
 * Carga datos de prueba al iniciar la aplicación, usando únicamente los repositorios.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final BodegaRepository bodegaRepository;
    private final ProductoRepository productoRepository;

    public DataInitializer(BodegaRepository bodegaRepository, ProductoRepository productoRepository) {
        this.bodegaRepository = bodegaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {
        if (bodegaRepository.count() > 0) {
            return;
        }

        Bodega norte = bodegaRepository.save(nuevaBodega("Bodega Norte", "Bogotá - Zona Norte"));
        Bodega sur = bodegaRepository.save(nuevaBodega("Bodega Sur", "Bogotá - Zona Sur"));

        productoRepository.saveAll(List.of(
                nuevoProducto("Computador portátil", "Portátil 15 pulgadas, 16 GB RAM, SSD 512 GB",
                        "2500000", 15, norte),
                nuevoProducto("Monitor", "Monitor LED 24 pulgadas Full HD",
                        "750000", 20, norte),
                nuevoProducto("Impresora", "Impresora multifuncional láser",
                        "1200000", 8, sur)));
    }

    private Bodega nuevaBodega(String nombre, String ubicacion) {
        Bodega bodega = new Bodega();
        bodega.setNombre(nombre);
        bodega.setUbicacion(ubicacion);
        return bodega;
    }

    private Producto nuevoProducto(String nombre, String descripcion, String precio,
                                   int cantidad, Bodega bodega) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(new BigDecimal(precio));
        producto.setCantidad(cantidad);
        producto.setBodega(bodega);
        return producto;
    }
}
