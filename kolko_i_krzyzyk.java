import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class kolko_i_krzyzyk extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean isXTurn = true; // Zaczyna gracz X

    public kolko_i_krzyzyk() {
        setTitle("Kółko i Krzyżyk");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3)); // Ustawienie siatki 3x3

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 80));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        
        if (clickedButton.getText().equals("")) {
            clickedButton.setText(isXTurn ? "X" : "O");
            isXTurn = !isXTurn;
            checkWin();
        }
    }

    public void checkWin() {
        String[][] patterns = {
            {buttons[0].getText(), buttons[1].getText(), buttons[2].getText()},
            {buttons[3].getText(), buttons[4].getText(), buttons[5].getText()},
            {buttons[6].getText(), buttons[7].getText(), buttons[8].getText()},
            {buttons[0].getText(), buttons[3].getText(), buttons[6].getText()},
            {buttons[1].getText(), buttons[4].getText(), buttons[7].getText()},
            {buttons[2].getText(), buttons[5].getText(), buttons[8].getText()},
            {buttons[0].getText(), buttons[4].getText(), buttons[8].getText()},
            {buttons[2].getText(), buttons[4].getText(), buttons[6].getText()}
        };

        for (String[] pattern : patterns) {
            if (pattern[0].equals(pattern[1]) && pattern[1].equals(pattern[2]) && !pattern[0].equals("")) {
                JOptionPane.showMessageDialog(this, "Wygrywa: " + pattern[0]);
                resetGame();
                return;
            }
        }

        // Sprawdzanie, czy wszystkie przyciski są zajęte (remis)
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }
        
        if (draw) {
            JOptionPane.showMessageDialog(this, "Remis!");
            resetGame();
        }
    }

    public void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        isXTurn = true;
    }

    public static void main(String[] args) {
        new kolko_i_krzyzyk();
    }
}
