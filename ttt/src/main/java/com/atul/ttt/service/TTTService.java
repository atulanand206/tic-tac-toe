package com.atul.ttt.service;

import com.atul.ttt.entity.Game;
import com.atul.ttt.entity.GameDto;
import com.atul.ttt.entity.MoveRequest;
import com.atul.ttt.repository.Repo;
import com.atul.ttt.utils.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TTTService {

    @Autowired
    Repo repo;

    public GameDto newGame() {
        Game game = repo.newGame();
        return GameDto.fromGame(game);
    }

    public GameDto move(MoveRequest moveRequest) {
        Preconditions.validateGreater(moveRequest.getGameId(), 0);
        Preconditions.validateGreater(moveRequest.getPlayer(), 1);
        Preconditions.validateSmaller(moveRequest.getPlayer(), 2);
        Preconditions.validateGreater(moveRequest.getX(), 1);
        Preconditions.validateSmaller(moveRequest.getX(), 3);
        Preconditions.validateGreater(moveRequest.getY(), 1);
        Preconditions.validateSmaller(moveRequest.getY(), 3);

        return null;
    }
}
