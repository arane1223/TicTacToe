import game.Game;
import ui.GameFrame;
import ui.GameController;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(3);
        GameFrame frame = new GameFrame(3);
        new GameController(game, frame);
    }
}
