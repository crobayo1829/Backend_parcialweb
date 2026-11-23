package com.bodegas.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bodegas.dto.BodegaDto;
import com.bodegas.dto.BodegaRequestDto;
import com.bodegas.entity.Bodega;
import com.bodegas.repository.BodegaRepository;

@Service
public class BodegaService {

    private final BodegaRepository bodegaRepository;
    private final ModelMapper modelMapper;

    public BodegaService(BodegaRepository bodegaRepository, ModelMapper modelMapper) {
        this.bodegaRepository = bodegaRepository;
        this.modelMapper = modelMapper;
    }

    /** Registra una bodega nueva (sin productos). */
    @Transactional
    public BodegaDto create(BodegaRequestDto dto) {
        dto.setNombre(dto.getNombre().trim());
        dto.setUbicacion(dto.getUbicacion().trim());

        Bodega bodega = modelMapper.map(dto, Bodega.class);
        Bodega saved = bodegaRepository.save(bodega);
        return modelMapper.map(saved, BodegaDto.class);
    }

    /**
     * Consulta todas las bodegas con sus productos.
     * Es transaccional (solo lectura) para poder recorrer la colección de productos
     * mientras se convierte a DTO.
     */
    @Transactional(readOnly = true)
    public List<BodegaDto> getAll() {
        return bodegaRepository.findAllConProductos()
                .stream()
                .map(bodega -> modelMapper.map(bodega, BodegaDto.class))
                .toList();
    }
}
