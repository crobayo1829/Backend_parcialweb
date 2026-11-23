package com.bodegas.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Respuesta de la API: bodega con su lista de productos anidada. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodegaDto {
    private Long id;
    private String nombre;
    private String ubicacion;
    private List<ProductoDto> productos = new ArrayList<>();
}
