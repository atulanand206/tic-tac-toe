package com.atul.ttt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveRequest {

    @JsonProperty("game_id")
    private int gameId;

    @JsonProperty("player")
    private int player;

    @JsonProperty("x")
    private int x;

    @JsonProperty("y")
    private int y;

    public MoveRequest() {

    }

    public MoveRequest(int gameId, int player, int x, int y) {
        this.gameId = gameId;
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public int getGameId() {
        return gameId;
    }

    public int getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
