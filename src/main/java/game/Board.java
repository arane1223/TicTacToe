package game;

public class Board {
    private final String[][] grid;
    private int moves;

    public Board(int size) {
        grid = new String[size][size];
        moves = 0;
    }

    public boolean placeMark(int row, int col, String mark) {
        if (grid[row][col] != null) return false;
        grid[row][col] = mark;
        moves++;
        return true;
    }

    public String getCell(int row, int col) {
        return grid[row][col];
    }

    public boolean isFull() {
        return moves == grid.length * grid.length;
    }

    public int getSize() {
        return grid.length;
    }

    public void reset() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = null;
            }
        }
        moves = 0;
    }
}
