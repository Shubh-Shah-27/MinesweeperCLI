package org.example;

import java.util.Scanner;

public class Game {

    private final Board board;
    private final Player player;

    Game(Player player, int row, int col, int mines, int life) {
        this.player = player;
        this.board = new Board(row, col, mines, life);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Player Name: ");
        String name = sc.nextLine();
        Player player = new Player(name);

        Game game = new Game(player, 5, 5, 3, 2);

        System.out.println("Game Created");
        System.out.println("Game is about to start ...");
        game.board.displayBoard();

        while (game.gameOn(game.board)) {
            System.out.println("Enter the position where you want to play: ");
            int x = sc.nextInt();
            int y = sc.nextInt();

            game.board.makeMove(x, y);
            game.board.displayBoard();
        }
        System.out.println();
        if (game.board.getLife() > 0)
            System.out.println(game.player.getPlayerName() + " Wins :)");
        else
            System.out.println("Game Over :(");
    }

    private boolean gameOn(Board board) {
        return board.getLife() != 0 && ((board.getMaxMoves() - board.getBlocksVisited() > board.getMines()));
    }
}