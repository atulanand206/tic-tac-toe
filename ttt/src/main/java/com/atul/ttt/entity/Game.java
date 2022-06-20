package com.atul.ttt.entity;

import com.atul.ttt.exception.InvalidMoveException;

public class Game {

    private static int id = 0;

    int gameId;
    int[][] board = new int[3][3];
    int playerTurn = 1;
    boolean finished = false;

    public Game() {
        this.gameId = id;
        id++;
    }

    public int gameId() {
        return gameId;
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean move(int player, int x, int y) {
        if (finished) {
            throw new InvalidMoveException("game is already over");
        }
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new InvalidMoveException("cell out of bounds");
        }
        if (player != playerTurn) {
            throw new InvalidMoveException("it's not your turn");
        }
        if (board[x - 1][y - 1] != 0) {
            throw new InvalidMoveException("cell is already marked");
        }
        board[x - 1][y - 1] = player;
        playerTurn = (playerTurn == 1) ? 2 : 1;
        print();
        return finished = isWinner(player);
    }

    private void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d ", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    int[][][] triplets = new int[][][] {
            {{1,1},{1,2},{1,3}},
            {{2,1},{2,2},{2,3}},
            {{3,1},{3,2},{3,3}},
            {{1,1},{2,1},{3,1}},
            {{1,2},{2,2},{3,2}},
            {{1,3},{2,3},{3,3}},
            {{1,1},{2,2},{3,3}},
            {{1,3},{2,2},{3,1}}
    };

    private boolean isWinner(int player) {
        for (int[][] trip : triplets) {
            boolean win = true;
            for (int[] cell : trip) {
                if (board[cell[0] - 1][cell[1] - 1] != player) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    public int getCell(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new InvalidMoveException("cell out of bounds");
        }
        return board[x - 1][y - 1];
    }
}
