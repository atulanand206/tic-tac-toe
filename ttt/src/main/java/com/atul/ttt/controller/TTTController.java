package com.atul.ttt.controller;

import com.atul.ttt.entity.GameDto;
import com.atul.ttt.entity.MoveRequest;
import com.atul.ttt.service.TTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/game")
public class TTTController {

    @Autowired
    private TTTService tttService;

    @GetMapping("/new")
    public GameDto newGame() {
        return tttService.newGame();
    }

    @PostMapping("/move")
    public GameDto move(@RequestBody MoveRequest moveRequest) {
        return tttService.move(moveRequest);
    }
}
