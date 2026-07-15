package com.devops.demo.userservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InfoController.class)
@DisplayName("InfoController Web MVC Tests")
public class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/api/v1/health should return UP status")
    void healthEndpoint_ShouldReturnUP() throws Exception {
        mockMvc.perform(get("/api/v1/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.service").isString())
                .andExpect(jsonPath("$.version").isString());
    }

    @Test
    @DisplayName("/api/v1/info should return metadata with expected version (intentionally wrong to fail)")
    void infoEndpoint_ShouldReturnInfo_WithWrongExpectedVersion_ToFail() throws Exception {
        // Intentionally assert a wrong expected version to simulate a failing test
        mockMvc.perform(get("/api/v1/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("user-service"))
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }
}

