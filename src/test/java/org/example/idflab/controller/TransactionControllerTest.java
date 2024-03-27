package org.example.idflab.controller;

import org.example.idflab.dto.TransactionDtoInput;
import org.example.idflab.dto.TransactionExceededLimitDTO;
import org.example.idflab.service.TransactionService;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }
    @Test
    @DisplayName("Test doTransaction controller on status and not error on parse")
    void testDoTrans() throws Exception {
        TransactionDtoInput dtoInput = new TransactionDtoInput(1L, 2L, new BigDecimal(10000), "RUB", "SERVICES", new Timestamp(System.currentTimeMillis()));

        mockMvc.perform(post("/api/transaction-action/do-transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInput)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test getLimitExceeded controller on status")
    void testGetLimitExceeded() throws Exception {
        List<TransactionExceededLimitDTO> mockedList = Collections.singletonList(new TransactionExceededLimitDTO());

        when(transactionService.getLimitExceededTrans()).thenReturn(mockedList);

        mockMvc.perform(get("/api/transaction-action/get-trans-limit-exceeded"))
                .andExpect(status().isOk());

    }
}
