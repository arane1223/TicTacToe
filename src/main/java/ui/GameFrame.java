package ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
    private final JButton restartButton = new JButton("Начать заново");
    private final JButton[][] buttons;

    public GameFrame(int size) {
        this.buttons = new JButton[size][size];
        setTitle("Крестики-нолики");
        setLayout(new BorderLayout());
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setupStatusPanel();
        setupBoard(size);
        setupRestartButton();

        setVisible(true);
    }

    private void setupStatusPanel() {
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.darkGray);
        statusLabel.setForeground(Color.white);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(statusLabel, BorderLayout.NORTH);
    }

    private void setupBoard(int size) {
        JPanel panel = new JPanel(new GridLayout(size, size));
        panel.setBackground(Color.darkGray);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton btn = new JButton();
                btn.setFont(new Font("Arial", Font.BOLD, 100));
                btn.setForeground(Color.darkGray);
                buttons[i][j] = btn;
                panel.add(btn);
            }
        }

        add(panel, BorderLayout.CENTER);
    }

    private void setupRestartButton() {
        restartButton.setFont(new Font("Arial", Font.BOLD, 25));
        add(restartButton, BorderLayout.SOUTH);
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public void highlightBoard(Color color) {
        for (JButton[] rowBtns : buttons)
            for (JButton b : rowBtns)
                b.setForeground(color);
    }

    public void resetBoard() {
        for (JButton[] rowBtns : buttons)
            for (JButton b : rowBtns) {
                b.setText("");
                b.setForeground(Color.darkGray);
            }
    }
}
