package com.atul.ttt.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

public class GameDto {

    @JsonProperty("id")
    int id;

    @JsonProperty("board")
    int[][] board;

    GameDto() {

    }

    GameDto(int id, int[][] board) {
        this.id = id;
        this.board = board;
    }

    public static GameDto fromGame(Game game) {
        return new GameDto(game.gameId(), game.getBoard());
    }

    public int[][] getBoard() {
        return board;
    }
}
