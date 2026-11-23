package com.bodegas;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BodegaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBodegasIncluyeProductosAsociados() throws Exception {
        mockMvc.perform(get("/bodegas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].nombre", hasItem("Bodega Norte")))
                .andExpect(jsonPath("$[?(@.nombre == 'Bodega Norte')].productos[*].nombre",
                        hasItem("Monitor")))
                .andExpect(jsonPath("$[?(@.nombre == 'Bodega Sur')].productos[*].nombre",
                        hasItem("Impresora")));
    }

    @Test
    void postBodegaRegistraYDevuelve201() throws Exception {
        mockMvc.perform(post("/bodegas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Bodega Este\",\"ubicacion\":\"Medellín\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nombre").value("Bodega Este"))
                .andExpect(jsonPath("$.productos").isEmpty());
    }

    @Test
    void postBodegaConNombreVacioDevuelve400() throws Exception {
        mockMvc.perform(post("/bodegas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"  \",\"ubicacion\":\"Cali\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }
}
