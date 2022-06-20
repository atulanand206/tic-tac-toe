package com.atul.ttt.controller;


import com.atul.ttt.TestBase;
import com.atul.ttt.entity.Game;
import com.atul.ttt.entity.GameDto;
import com.atul.ttt.entity.MoveRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Requirements:
 *
 * REST APIs: GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS
 *
 *
 * 1. GET: /game/new
 *      - return an empty grid of size 3 x 3.
 *      - create a id.
 *
 * 2. POST: /game/move
 *      - request body: gameId, player, x, y.
 *      - return a board with x, y marked by player.
 *      - return winner when game is won.
 *      - throws exception when called after the game is over.
 *      - throws exception when called on any invalid move.
 *
 *
 * Controller(Presentation Layer)
 * Service(Business Layer)
 * Repository(Persistence Layer) (In-memory, Database)
 * */
public class TTTControllerTest extends TestBase {

    @Autowired
    private WebApplicationContext fAppContext;

    private MockMvc fMockMvc;

    @BeforeAll
    void setUp() {
        fMockMvc = MockMvcBuilders.webAppContextSetup(fAppContext).build();
    }

    @Test
    void test_create_new_game() throws Exception {
        String contentAsString = fMockMvc.perform(newGameRequest())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        GameDto gameDto = new ObjectMapper().readValue(contentAsString, GameDto.class);
        assertEquals("board row size must be 3", 3, gameDto.getBoard().length);
        for (int[] row : gameDto.getBoard()) {
            assertEquals("board col size must be 3", 3, row.length);
            for (int i : row) {
                assertEquals("board cell value must be 0", 0, i);
            }
        }
    }

    @Test
    void test_game_move_throws_exception_when_request_body_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> fMockMvc.perform(gameMoveRequest(null)));
    }

    @Test
    void test_game_move_throws_exception_when_request_dont_contain_game_id() {
        assertThrows(IllegalArgumentException.class,
                () -> fMockMvc.perform(gameMoveRequest(new MoveRequest())));
    }

    @Test
    void test_game_move_does_not_throws_when_game_details_are_set() {
        assertDoesNotThrow(() -> fMockMvc.perform(gameMoveRequest(new MoveRequest(1,1,1,1))));
    }


    private static final String NEW_GAME = "/api/v1/game/new";

    protected static RequestBuilder newGameRequest() {
        return MockMvcRequestBuilders
                .get(NEW_GAME)
                .contentType(APPLICATION_JSON);
    }

    private static final String GAME_MOVE = "/api/v1/game/move";

    protected static RequestBuilder gameMoveRequest(@Nullable MoveRequest moveRequest) throws JsonProcessingException {
        String body = new ObjectMapper().writeValueAsString(moveRequest);
        return MockMvcRequestBuilders
                .post(GAME_MOVE)
                .contentType(APPLICATION_JSON)
                .content(body);
    }
}
