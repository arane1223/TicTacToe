import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeigh = 700; //50 px для текстовой панели и кнопки перезапуска

    JFrame frame = new JFrame("Крестики-нолики");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel restartPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    JButton restartButton = new JButton("Начать заново");

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeigh);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Крестики-нолики");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        restartPanel.setLayout(new GridLayout());
        restartPanel.add(restartButton);
        restartButton.setForeground(Color.darkGray);
        restartButton.setFont(new Font("Arial", Font.BOLD, 25));
        frame.add(restartButton, BorderLayout.SOUTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);

                tile.setForeground(Color.darkGray);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")) {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText("Xодит " + currentPlayer);
                            }
                        }
                    }
                });
            }
        }

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
    }


    void checkWinner() {
        // горизонтально
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals("")) continue;

            if (board[i][0].getText().equals(board[i][1].getText()) &&
                    board[i][0].getText().equals(board[i][2].getText())) {
                for (int i1 = 0; i1 < 3; i1++) {
                    setWinner(board[i][i1]);
                }
                gameOver = true;
                return;
            }
        }
        // вертикально
        for (int j = 0; j < 3; j++) {
            if (board[0][j].getText().equals("")) continue;

            if (board[0][j].getText().equals(board[1][j].getText()) &&
                    board[0][j].getText().equals(board[2][j].getText())) {
                for (int j1 = 0; j1 < 3; j1++) {
                    setWinner(board[j1][j]);
                }
                gameOver = true;
                return;
            }
        }
        // диагонали
        if (board[0][0].getText().equals(board[1][1].getText()) &&
                board[0][0].getText().equals(board[2][2].getText()) &&
                !Objects.equals(board[0][0].getText(), "")) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        if (board[0][2].getText().equals(board[1][1].getText()) &&
                board[0][2].getText().equals(board[2][0].getText()) &&
                !Objects.equals(board[0][2].getText(), "")) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        textLabel.setText(currentPlayer + " победил!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        textLabel.setText("Ничья!");
    }

    void restartGame() {
        gameOver = false;
        turns = 0;
        currentPlayer = playerX;
        textLabel.setText("Крестики-нолики");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setForeground(Color.darkGray);
            }
        }
    }
}
