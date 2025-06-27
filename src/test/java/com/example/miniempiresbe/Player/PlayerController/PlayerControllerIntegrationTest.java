package com.example.miniempiresbe.Player.PlayerController;

import com.example.miniempiresbe.Player.PlayerModel.Player;
import com.example.miniempiresbe.Player.PlayerRepository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase  // auto‐switches to H2
@ActiveProfiles("test")
class PlayerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PlayerRepository repo;

    @BeforeEach
    void cleanup() {
        repo.deleteAll();
    }

    @Test
    void createPlayer_shouldReturn201() throws Exception {
        Player p = new Player();
        p.setName("TestUser");
        p.setEmail("test@ex.com");

        mockMvc.perform(post("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(p)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getAllPlayers_shouldReturn200AndJsonArray() throws Exception {
        mockMvc.perform(get("/api/players")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getPlayerById_shouldReturnPlayer() throws Exception {
        // Create player
        Player p = new Player();
        p.setName("Vu");
        p.setEmail("vu@example.com");

        mockMvc.perform(post("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(p)))
                .andExpect(status().isCreated());

        // Now fetch by ID
        mockMvc.perform(get("/api/players/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("vu@example.com"));
    }


    @Test
    void updatePlayer_shouldReturnUpdatedFields() throws Exception {
        // Seed a player first
        Player original = new Player();
        original.setName("Vu");
        original.setEmail("vu@ex.com");

        mockMvc.perform(post("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(original)))
                .andExpect(status().isCreated());

        // Prepare an update payload
        String updatedJson = """
        {
          "name": "Updated Vu",
          "level": 42
        }
        """;

        // Perform PUT
        mockMvc.perform(put("/api/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Vu"))
                .andExpect(jsonPath("$.level").value(42));
    }

    @Test
    void deletePlayer_shouldReturn204() throws Exception {
        // Step 1: Create a player
        Player p = new Player();
        p.setName("DeleteMe");
        p.setEmail("delete@ex.com");

        mockMvc.perform(post("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(p)))
                .andExpect(status().isCreated());

        // Step 2: Delete the player
        mockMvc.perform(delete("/api/players/1"))
                .andExpect(status().isNoContent());

        // Step 3: Confirm it's gone
        mockMvc.perform(get("/api/players/1"))
                .andExpect(status().isNotFound());
    }
}