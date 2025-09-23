import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit тесты для игры крестики-нолики")
class TicTacToeTest extends TestBase {

    @DisplayName("При запуске игры первый игрок - Х, количество ходов 0, вверху написан текст «Крестики-нолики»")
    @Test
    void initialStatTest() {
        assertEquals("X", game.currentPlayer);
        assertEquals(0, game.turns);
        assertFalse(game.gameOver);
        assertEquals("Крестики-нолики", game.textLabel.getText());
    }

    @DisplayName("При заполнении горизонтальной линии символами Х, символы становятся зелеными и выходит надпись «X победил!»")
    @Test
    void horizontalWinTest() {
        game.board[0][0].setText("X");
        game.board[0][1].setText("X");
        game.board[0][2].setText("X");
        game.turns = 3;

        game.checkWinner();

        assertTrue(game.gameOver);
        assertEquals("X победил!", game.textLabel.getText());
        assertEquals(Color.green, game.board[0][0].getForeground());
    }

    @DisplayName("При заполнении Вертикальной линии символами O, символы становятся зелеными и выходит надпись «O победил!»")
    @Test
    void verticalWinTest() {
        game.board[0][1].setText("O");
        game.board[1][1].setText("O");
        game.board[2][1].setText("O");
        game.currentPlayer = "O";
        game.turns = 3;

        game.checkWinner();

        assertTrue(game.gameOver);
        assertEquals("O победил!", game.textLabel.getText());
        assertEquals(Color.green, game.board[0][1].getForeground());
    }

    @DisplayName("При заполнении диагонали символами Х, символы становятся зелеными и выходит надпись «X победил!»")
    @Test
    void diagonalWinTest() {
        game.board[0][0].setText("X");
        game.board[1][1].setText("X");
        game.board[2][2].setText("X");
        game.turns = 3;

        game.checkWinner();

        assertTrue(game.gameOver);
        assertEquals("X победил!", game.textLabel.getText());
        assertEquals(Color.green, game.board[1][1].getForeground());
    }

    @DisplayName("При заполнении анти диагонали символами O, символы становятся зелеными и выходит надпись «O победил!»")
    @Test
    void antiDiagonalWinTest() {
        game.board[0][2].setText("O");
        game.board[1][1].setText("O");
        game.board[2][0].setText("O");
        game.currentPlayer = "O";
        game.turns = 3;

        game.checkWinner();

        assertTrue(game.gameOver);
        assertEquals("O победил!", game.textLabel.getText());
        assertEquals(Color.green, game.board[1][1].getForeground());
    }

    @DisplayName("При заполнении всех полей, символы становятся оранжевыми и выходит надпись «Ничья!»")
    @Test
    void tieGameTest() {
        String[][] moves = {
                {"X", "O", "X"},
                {"X", "X", "O"},
                {"O", "X", "O"}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game.board[i][j].setText(moves[i][j]);
            }
        }
        game.turns = 9;

        game.checkWinner();

        assertTrue(game.gameOver);
        assertEquals("Ничья!", game.textLabel.getText());
        assertEquals(Color.orange, game.board[0][0].getForeground());
    }

    @DisplayName("При нажатии на кнопку «Начать заново», игра перезапустится")
    @Test
    void restartGameTest() {
        game.board[0][0].setText("X");
        game.board[0][0].setForeground(Color.green);
        game.turns = 5;
        game.gameOver = true;
        game.textLabel.setText("X победил!");

        game.restartGame();

        assertFalse(game.gameOver);
        assertEquals(0, game.turns);
        assertEquals("X", game.currentPlayer);
        assertEquals("Крестики-нолики", game.textLabel.getText());
        assertEquals("", game.board[0][0].getText());
        assertEquals(Color.darkGray, game.board[0][0].getForeground());
    }
}