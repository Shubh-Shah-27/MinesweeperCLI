package org.example;

public class Board {
    private final byte[][] board;
    private final byte[][] visited;
    private int life;
    private int blocksVisited;
    private final int mines;
    private final int maxMoves;

    Board(int row, int column, int mines, int life) {
        board = new byte[row][column];
        visited = new byte[row][column];
        blocksVisited = 0;
        maxMoves = row * column;

        int lifeUpperLimit = (int) (0.05 * row * column);
        int lifeLowerLimit = 1;
        if (life > lifeUpperLimit)
            this.life = lifeUpperLimit;
        else
            this.life = Math.max(life, lifeLowerLimit);

        int mineUpperLimit = (int) (0.3 * row * column);
        int mineLowerLimit = (int) (0.1 * row * column);
        if (mines > mineUpperLimit)
            this.mines = mineUpperLimit;
        else
            this.mines = Math.max(mines, mineLowerLimit);

        placeMines(this.mines);
        generateNumbers();
    }

    private void placeMines(int mines) {
        for (int i = 1; i <= mines; i++) {
            int num = (int) (Math.random() * board.length * board[0].length);
            board[num / board.length][num % board[0].length] = -1; // Mine placed
        }
    }

    private void generateNumbers() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == -1)
                    continue;

                int count = 0;

                if (i - 1 >= 0) {
                    if (board[i - 1][j] == -1) {
                        count++;
                    }

                    if (j - 1 >= 0 && board[i - 1][j - 1] == -1)
                        count++;

                    if (j + 1 < board[i].length && board[i - 1][j + 1] == -1)
                        count++;
                }

                if (i + 1 < board.length) {
                    if (board[i + 1][j] == -1)
                        count++;

                    if (j - 1 >= 0 && board[i + 1][j - 1] == -1)
                        count++;

                    if (j + 1 < board[i].length && board[i + 1][j + 1] == -1)
                        count++;
                }

                if (j - 1 >= 0 && board[i][j - 1] == -1)
                    count++;

                if (j + 1 < board[i].length && board[i][j + 1] == -1)
                    count++;

                board[i][j] = (byte) count;
            }
        }
    }

    public int getLife() {
        return life;
    }

    private void loseLife() {
        life -= 1;
    }

    public int getBlocksVisited() {
        return blocksVisited;
    }

    public int getMines() {
        return mines;
    }

    public int getMaxMoves() {
        return maxMoves;
    }

    public void makeMove(int x, int y, boolean flagged) {
        x--;
        y--;

        if (x < 0 || y < 0 || x >= board.length || y >= board[x].length)
            System.out.println("Input Out of Range");
        else if (visited[x][y] == 1)
            System.out.println("Already Visited");
        else {
            if (flagged) {
                visited[x][y] = 2;
                if (board[x][y] == -1)
                    blocksVisited++;
            } else if (board[x][y] == -1) {
                System.out.println("You hit a mine");
                loseLife();
                System.out.println("Lifes Left: " + life);
            } else
                updateBoard(x, y);
        }
    }

    private void updateBoard(int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[x].length)
            return;

        if (visited[x][y] != 0 || board[x][y] == -1)
            return;

        visited[x][y] = 1;
        blocksVisited++;
        if (board[x][y] == 0) {
            updateBoard(x - 1, y);
            updateBoard(x + 1, y);
            updateBoard(x, y - 1);
            updateBoard(x, y + 1);
            updateBoard(x - 1, y - 1);
            updateBoard(x + 1, y - 1);
            updateBoard(x - 1, y + 1);
            updateBoard(x + 1, y + 1);
        }
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (visited[i][j] == 0)
                    System.out.print(". ");
                else if (visited[i][j] == 2)
                    System.out.print("F ");
                else
                    System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


}
