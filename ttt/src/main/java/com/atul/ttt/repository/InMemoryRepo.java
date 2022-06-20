package com.atul.ttt.repository;

import com.atul.ttt.entity.Game;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepo implements Repo {

    Map<Integer, Game> games = new HashMap<>();

    @Override
    public Game newGame() {
        Game game = new Game();
        games.put(game.gameId(), game);
        return game;
    }
}
