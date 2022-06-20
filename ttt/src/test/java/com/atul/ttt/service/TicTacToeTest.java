package com.atul.ttt.service;

import com.atul.ttt.entity.Game;
import com.atul.ttt.exception.InvalidMoveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


/**
 *
 * Requirements:
 *
 * 1. TicTacToe can be represented as an int[] of size 9.
 * 2. There are 2 players playing turn by turn.
 * 3. If a player marks 3 straight cells, they win.
 *
 *  0 1 2
 *  3 4 5
 *  6 7 8
 *
 *
 *     1  2 3
 * 1 1,1
 * 2
 * 3
 *
 * */
public class TicTacToeTest {

    @Test
    void test_ttt() {
        Game game = new Game();
        assertEquals("board size must be 3", 3, game.getBoard().length);
    }

    @Test
    void test_mark_cell_on_player_move() {
        Game game = new Game();
        game.move(1, 2, 3);
        assertEquals("cell (2,3) by player 1 must be marked",
                1, game.getCell(2, 3));
        game.move(2, 1, 2);
        assertEquals("cell (1,2) by player 2 must be marked",
                2, game.getCell(1, 2));
    }

    @Test
    void test_mark_on_already_marked_cell_throws_exception() {
        Game game = new Game();
        game.move(1, 2, 2);
        assertEquals("cell marked",1, game.getCell(2, 2));
        assertThrows(InvalidMoveException.class, () -> game.move(2, 2, 2));
        assertThrows(InvalidMoveException.class, () -> game.move(1, 2, 2));
    }

    @Test
    void test_same_player_cant_mark_consecutively() {
        Game game = new Game();
        game.move(1, 2, 2);
        assertThrows(InvalidMoveException.class, () -> game.move(1, 2, 3));
    }

    @Test
    void test_range_of_input_values() {
        Game game = new Game();
        assertThrows(InvalidMoveException.class, () -> game.move(1, 0, 0));
        assertThrows(InvalidMoveException.class, () -> game.move(1, 2, 4));
    }

    @Test
    void test_gameplay() {
        Game game = new Game();
        assertFalse(game.move(1, 1, 1));
        assertFalse(game.move(2, 2, 2));
        assertFalse(game.move(1, 3, 1));
        assertFalse(game.move(2, 1, 3));
        assertTrue(game.move(1, 2, 1));
    }

    @Test
    void test_gameplay_stops_after_win() {
        Game game = new Game();
        assertFalse(game.move(1, 1, 1));
        assertFalse(game.move(2, 2, 2));
        assertFalse(game.move(1, 3, 1));
        assertFalse(game.move(2, 1, 3));
        assertTrue(game.move(1, 2, 1));
        assertThrows(InvalidMoveException.class, () -> game.move(2, 3, 3));
    }
}
