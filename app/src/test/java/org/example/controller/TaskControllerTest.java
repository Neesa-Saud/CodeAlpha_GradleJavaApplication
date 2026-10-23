
package org.example.controller;

import org.example.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddAndListTasks() throws Exception {
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Learn Gradle\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Learn Gradle"))
                .andExpect(jsonPath("$.completed").value(false));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].title", hasItem("Learn Gradle")));
    }

    @Test
    void shouldRejectBlankTaskTitle() throws Exception {
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"   \"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotFoundForMissingTask() throws Exception {
        mockMvc.perform(patch("/api/tasks/999999/complete"))
                .andExpect(status().isNotFound());
    }
}
