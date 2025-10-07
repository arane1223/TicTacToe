package game;

public enum Player {
    X("X"),
    O("O");

    private final String symbol;

    Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public Player opposite() {
        return this == X ? O : X;
    }
}
