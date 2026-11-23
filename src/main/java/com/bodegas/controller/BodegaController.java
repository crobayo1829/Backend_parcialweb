package com.bodegas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodegas.dto.BodegaDto;
import com.bodegas.dto.BodegaRequestDto;
import com.bodegas.service.BodegaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    private final BodegaService service;

    public BodegaController(BodegaService service) {
        this.service = service;
    }

    /** POST /bodegas - registrar bodega. */
    @PostMapping
    public ResponseEntity<BodegaDto> create(@Valid @RequestBody BodegaRequestDto dto) {
        BodegaDto created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /** GET /bodegas - consultar bodegas con sus productos asociados. */
    @GetMapping
    public ResponseEntity<List<BodegaDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
