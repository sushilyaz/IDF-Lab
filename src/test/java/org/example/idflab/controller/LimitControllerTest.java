package org.example.idflab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.idflab.dto.AllLimitDTO;
import org.example.idflab.dto.NewLimitDto;
import org.example.idflab.service.LimitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LimitControllerTest {
    private MockMvc mockMvc;

    @Mock
    private LimitService limitService;

    @InjectMocks
    private LimitController limitController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(limitController).build();
    }

    @Test
    @DisplayName("CreateNewLimitController status test and parse")
    void testCreateNewLimit() throws Exception {
        NewLimitDto dto = new NewLimitDto();

        mockMvc.perform(post("/api/limit-action/create-new-limit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GetAllLimitController status test and parse")
    void testGetAllLimit() throws Exception {
        List<AllLimitDTO> mockedList = Collections.singletonList(new AllLimitDTO());

        when(limitService.getCurrentLimit()).thenReturn(mockedList);

        mockMvc.perform(get("/api/limit-action/get-all-current-limits"))
                .andExpect(status().isCreated());
    }
}
