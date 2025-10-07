package game;

import org.junit.jupiter.api.BeforeEach;

public abstract class TestBase {
    protected Game game;

    @BeforeEach
    void setUp() {
        game = new Game(3);
    }
}
