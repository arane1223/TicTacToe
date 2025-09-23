
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    TicTacToe game;

    @BeforeEach
    void setUp() {
        game = new TicTacToe();
        game.frame.setVisible(false);
    }
}
