package com.spassu.livro.controller;

import com.spassu.livro.dto.AutorDTO;
import com.spassu.livro.service.AutorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class AutorControllerTest {

    @Mock
    private AutorService autorService;

    @InjectMocks
    private AutorController autorController;

    private MockMvc mockMvc;

    @Test
    public void listarTodos() throws Exception {
        AutorDTO autorDTO = new AutorDTO(1L,"nome");
        when(autorService.listarTodos()).thenReturn(List.of(autorDTO));

        mockMvc = MockMvcBuilders.standaloneSetup(autorController).build();

        mockMvc.perform(get("/autores")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists()); // Verifica se o primeiro elemento existe
    }

    @Test
    public void buscarPorId() throws Exception {
        Long id = 1L;
        AutorDTO autorDTO = new AutorDTO(1L, "teste");
        when(autorService.buscarPorId(id)).thenReturn(autorDTO);

        mockMvc = MockMvcBuilders.standaloneSetup(autorController).build();

        mockMvc.perform(get("/autores/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void criar() throws Exception {
        AutorDTO autorDTO = new AutorDTO(null,"Nome do Autor");
        when(autorService.criar(any(AutorDTO.class))).thenReturn(autorDTO);

        mockMvc = MockMvcBuilders.standaloneSetup(autorController).build();

        mockMvc.perform(post("/autores")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Nome do Autor\"}")) // JSON válido
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void atualizar() throws Exception {
        Long id = 1L;
        AutorDTO autorDTO = new AutorDTO(1L, "Novo Nome");
        when(autorService.atualizar(eq(id), any(AutorDTO.class))).thenReturn(autorDTO);

        mockMvc = MockMvcBuilders.standaloneSetup(autorController).build();
        mockMvc.perform(put("/autores/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Novo Nome\"}")) // JSON válido
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void excluir() throws Exception {
        Long id = 1L;

        mockMvc = MockMvcBuilders.standaloneSetup(autorController).build();

        mockMvc.perform(delete("/autores/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(autorService, times(1)).excluir(id);
    }

}