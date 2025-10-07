package game;

public class Game {
    private final Board board;
    private Player currentPlayer;
    private boolean gameOver;

    public Game(int size) {
        this.board = new Board(size);
        this.currentPlayer = Player.X;
        this.gameOver = false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Board getBoard() {
        return board;
    }

    public String makeMove(int row, int col) {
        if (gameOver || !board.placeMark(row, col, currentPlayer.getSymbol())) return null;

        if (checkWinner()) {
            gameOver = true;
            return currentPlayer.getSymbol() + " победил!";
        }

        if (board.isFull()) {
            gameOver = true;
            return "Ничья!";
        }

        currentPlayer = currentPlayer.opposite();
        return "Ходит " + currentPlayer.getSymbol();
    }

    private boolean checkWinner() {
        int size = board.getSize();
        String p = currentPlayer.getSymbol();

        for (int i = 0; i < size; i++) {
            if (p.equals(board.getCell(i, 0))
                    && p.equals(board.getCell(i, 1))
                    && p.equals(board.getCell(i, 2))) return true;
            if (p.equals(board.getCell(0, i))
                    && p.equals(board.getCell(1, i))
                    && p.equals(board.getCell(2, i))) return true;
        }

        if (p.equals(board.getCell(0, 0))
                && p.equals(board.getCell(1, 1))
                && p.equals(board.getCell(2, 2))) return true;
        if (p.equals(board.getCell(0, 2))
                && p.equals(board.getCell(1, 1))
                && p.equals(board.getCell(2, 0))) return true;

        return false;
    }

    public void reset() {
        board.reset();
        currentPlayer = Player.X;
        gameOver = false;
    }
}
