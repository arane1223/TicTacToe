package ui;

import game.Game;

import javax.swing.*;
import java.awt.*;

public class GameController {
    private final Game game;
    private final GameFrame frame;
    private final JButton[][] buttons;

    public GameController(Game game, GameFrame frame) {
        this.game = game;
        this.frame = frame;
        this.buttons = frame.getButtons();

        initListeners();
        frame.updateStatus("Ходит " + game.getCurrentPlayer().getSymbol());
    }

    private void initListeners() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int row = i;
                int col = j;
                buttons[i][j].addActionListener(e -> handleMove(row, col));
            }
        }

        frame.getRestartButton().addActionListener(e -> restartGame());
    }

    private void handleMove(int row, int col) {
        String status = game.makeMove(row, col);
        if (status == null) return;

        buttons[row][col].setText(game.getBoard().getCell(row, col));
        frame.updateStatus(status);

        if (game.isGameOver()) {
            Color color = status.contains("Ничья") ? Color.orange : Color.green;
            frame.highlightBoard(color);
        }
    }

    private void restartGame() {
        game.reset();
        frame.resetBoard();
        frame.updateStatus("Ходит " + game.getCurrentPlayer().getSymbol());
    }
}
