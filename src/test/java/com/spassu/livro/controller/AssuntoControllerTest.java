package com.spassu.livro.controller;

import com.spassu.livro.controller.AssuntoController;
import com.spassu.livro.dto.AssuntoDTO;
import com.spassu.livro.service.AssuntoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AssuntoControllerTest {

    @Mock
    private AssuntoService assuntoService;

    @InjectMocks
    private AssuntoController assuntoController;

    private MockMvc mockMvc;

    @Test
    public void listarTodos() throws Exception {
        AssuntoDTO assuntoDTO = new AssuntoDTO(1L, "Assunto");
        when(assuntoService.listarTodos()).thenReturn(List.of(assuntoDTO));

        mockMvc = MockMvcBuilders.standaloneSetup(assuntoController).build();

        mockMvc.perform(get("/assuntos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    public void buscarPorId() throws Exception {
        Long id = 1L;
        AssuntoDTO assuntoDTO = new AssuntoDTO(id, "Assunto");
        when(assuntoService.buscarPorId(id)).thenReturn(assuntoDTO);

        mockMvc = MockMvcBuilders.standaloneSetup(assuntoController).build();

        mockMvc.perform(get("/assuntos/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void criar() throws Exception {
        AssuntoDTO assuntoDTO = new AssuntoDTO(1L, "Descrição do Assunto");
        when(assuntoService.criar(any(AssuntoDTO.class))).thenReturn(assuntoDTO);

        mockMvc = MockMvcBuilders.standaloneSetup(assuntoController).build();

        mockMvc.perform(post("/assuntos")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Descrição do Assunto\"}")) // JSON válido
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void atualizar() throws Exception {
        Long id = 1L;
        AssuntoDTO assuntoDTO = new AssuntoDTO(id, "Nova Descrição");
        when(assuntoService.atualizar(eq(id), any(AssuntoDTO.class))).thenReturn(assuntoDTO);

        mockMvc = MockMvcBuilders.standaloneSetup(assuntoController).build();

        mockMvc.perform(put("/assuntos/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Nova Descrição\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void excluir() throws Exception {
        Long id = 1L;

        mockMvc = MockMvcBuilders.standaloneSetup(assuntoController).build();

        mockMvc.perform(delete("/assuntos/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(assuntoService, times(1)).excluir(id);
    }

}