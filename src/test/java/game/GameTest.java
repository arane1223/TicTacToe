package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты игровой логики (Game)")
public class GameTest extends TestBase {

    @Test
    @DisplayName("После запуска игры первый игрок — X, поле пустое, игра не завершена")
    void initialStateTest() {
        assertEquals(Player.X, game.getCurrentPlayer());
        assertFalse(game.isGameOver());
        assertEquals(3, game.getBoard().getSize());
    }

    @Test
    @DisplayName("Игрок X выигрывает по горизонтали")
    void horizontalWinTest() {
        game.makeMove(0, 0);
        game.makeMove(1, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);
        String result = game.makeMove(0, 2);

        assertTrue(game.isGameOver());
        assertEquals("X победил!", result);
    }

    @Test
    @DisplayName("Игрок O выигрывает по вертикали")
    void verticalWinTest() {
        game.makeMove(0, 1);
        game.makeMove(0, 0);
        game.makeMove(1, 1);
        game.makeMove(1, 0);
        game.makeMove(2, 2);
        String result = game.makeMove(2, 0);

        assertTrue(game.isGameOver());
        assertEquals("O победил!", result);
    }

    @Test
    @DisplayName("Игрок X выигрывает по диагонали")
    void diagonalWinTest() {
        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);
        game.makeMove(2, 1);
        String result = game.makeMove(2, 2);

        assertTrue(game.isGameOver());
        assertEquals("X победил!", result);
    }

    @Test
    @DisplayName("Игрок O выигрывает по побочной диагонали")
    void antiDiagonalWinTest() {
        game.makeMove(0, 0);
        game.makeMove(0, 2);
        game.makeMove(1, 0);
        game.makeMove(1, 1);
        game.makeMove(2, 1);
        String result = game.makeMove(2, 0);

        assertTrue(game.isGameOver());
        assertEquals("O победил!", result);
    }

    @Test
    @DisplayName("Игра завершается ничьей")
    void tieTest() {
        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(0, 2);
        game.makeMove(1, 2);
        game.makeMove(1, 0);
        game.makeMove(2, 0);
        game.makeMove(1, 1);
        game.makeMove(2, 2);
        String result = game.makeMove(2, 1);

        assertTrue(game.isGameOver());
        assertEquals("Ничья!", result);
    }

    @Test
    @DisplayName("После reset() игра возвращается в начальное состояние")
    void restartGameTest() {
        game.makeMove(0, 0);
        game.makeMove(1, 1);
        game.makeMove(0, 1);
        game.makeMove(2, 2);
        game.makeMove(0, 2);

        assertTrue(game.isGameOver());

        game.reset();

        assertFalse(game.isGameOver());
        assertEquals(Player.X, game.getCurrentPlayer());
        assertNull(game.getBoard().getCell(0, 0));
    }
}
