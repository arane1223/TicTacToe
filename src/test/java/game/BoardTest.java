package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты логики доски (Board)")
public class BoardTest {

    @Test
    @DisplayName("Можно поставить метку и получить её из клетки")
    void testPlaceMarkAndGetCell() {
        Board board = new Board(3);
        assertTrue(board.placeMark(0, 0, "X"));
        assertEquals("X", board.getCell(0, 0));
        assertFalse(board.placeMark(0, 0, "O"));
    }

    @Test
    @DisplayName("Поле считается полным, когда все клетки заняты")
    void testBoardIsFull() {
        Board board = new Board(3);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board.placeMark(i, j, "X");

        assertTrue(board.isFull());
    }

    @Test
    @DisplayName("Метод reset() очищает поле")
    void testBoardReset() {
        Board board = new Board(3);
        board.placeMark(0, 0, "X");
        board.reset();

        assertNull(board.getCell(0, 0));
        assertFalse(board.isFull());
    }
}