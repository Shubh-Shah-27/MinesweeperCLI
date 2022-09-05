package org.example;

import java.util.Arrays;

public class Board {
    private byte board [][];
    private byte visited[][];

    Board(int size, int mines)
    {
        board = new byte[size][size];
        visited = new byte[size][size];

        placeMines(mines);
        generateNumbers();
    }

    Board(int row, int column, int mines)
    {
        board = new byte[row][column];
        visited = new byte[row][column];

        placeMines(mines);
        generateNumbers();
    }

//    public byte[][] getBoard() {
//        return board;
//    }

    public void displayBoard()
    {
        for(int i=0;i<board.length;i++)
        {
            for (int j = 0; j < board[i].length; j++) {
                if(visited[i][j] == 0)
                    System.out.print(". ");
                else if(visited[i][j] == 1)
                    System.out.println("F ");
                else
                    System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private void placeMines(int mines)
    {
        int mineUpperLimit = (int) (0.3*board.length);
        int mineLowerLimit = (int) (0.05* board.length);
        if(mines > mineUpperLimit)
            mines = mineUpperLimit;
        else if(mines < mineLowerLimit)
            mines = mineLowerLimit;

        for(int i=1;i<=mines;i++)
        {
            int num = (int)(Math.random()*100);
            board[num/10][num%10] = -1; // Mine placed
        }
    }

    private void generateNumbers()
    {
        for(int i=0;i<board.length;i++)
        {
            for (int j = 0; j < board[i].length; j++) {

                if(board[i][j] == -1)
                    continue;

                int count = 0;

                if(i-1 >=0) {
                    if(board[i-1][j] == -1) {
                        count++;
                    }

                    if(j-1>=0 && board[i-1][j-1] == -1)
                        count++;

                    if(j+1 < board[i].length && board[i-1][j+1] == -1)
                        count++;
                }

                if(i+1 < board.length) {
                    if (board[i + 1][j] == -1)
                        count++;

                    if (j - 1 >= 0 && board[i + 1][j - 1] == -1)
                        count++;

                    if (j + 1 < board[i].length && board[i + 1][j + 1] == -1)
                        count++;
                }

                if(j-1 >= 0 && board[i][j-1] == -1)
                    count++;

                if(j+1 < board[i].length && board[i][j+1] == -1)
                    count++;

                board[i][j] = (byte) count;
            }
        }
    }
}
