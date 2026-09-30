package com.balazs.system_health_monitor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SystemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getExistingSystemShouldReturn200() throws Exception {

        mockMvc.perform(get("/api/systems/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("sensor-service"))
                .andExpect(jsonPath("$.status").value("ONLINE"));
    }

    @Test
    void getUnknownSystemShouldReturn404() throws Exception {

        mockMvc.perform(get("/api/systems/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addSystemShouldReturnNewSystem() throws Exception {

       String json = """
               {
                 "id": 4,
                 "name": "radar-service",
                 "status": "ONLINE"
               }
               """;

       mockMvc.perform(post("/api/systems")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(4))
               .andExpect(jsonPath("$.name").value("radar-service"))
               .andExpect(jsonPath("$.status").value("ONLINE"));
    }
    @Test
    void updateStatusShouldReturnUpdatedSystem() throws Exception {

        String json = """
                {
                "status": "MAINTENANCE"
                }
                """;

        mockMvc.perform(put("/api/systems/2/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("sensor-service"))
                .andExpect(jsonPath("$.status").value("MAINTENANCE"));
    }
    @Test
    void updateUnknownSystemShouldReturn404() throws Exception {

        String json = """
                {
                "status": "ONLINE"
                }
                """;

        mockMvc.perform(put("/api/systems/99/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound());
    } 
    
    @Test
    void invalidStatusShouldReturn400() throws Exception {

        String json = """
                {
                "status": "BANANA"
                }
                """;

        mockMvc.perform(put("/api/systems/2/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}