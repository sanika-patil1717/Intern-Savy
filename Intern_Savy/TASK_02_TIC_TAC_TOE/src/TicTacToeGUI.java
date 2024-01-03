import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JLabel turnLabel;
    private char[][] space = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
    private char currentPlayer = 'X';

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                buttons[i][j].setName(i + "" + j);
                gridPanel.add(buttons[i][j]);
            }
        }

        turnLabel = new JLabel("Current Turn: Player X");
        add(turnLabel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String name = clickedButton.getName();
        int row = Character.getNumericValue(name.charAt(0));
        int col = Character.getNumericValue(name.charAt(1));

        if (space[row][col] != 'X' && space[row][col] != '0') {
            space[row][col] = currentPlayer;
            clickedButton.setText(Character.toString(currentPlayer));
            if (checkWin(row, col)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetGame();
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? '0' : 'X';
                turnLabel.setText("Current Turn: Player " + currentPlayer);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move!");
        }
    }

    private boolean checkWin(int row, int col) {
        return (space[row][0] == space[row][1] && space[row][0] == space[row][2]) ||
                (space[0][col] == space[1][col] && space[0][col] == space[2][col]) ||
                (space[0][0] == space[1][1] && space[0][0] == space[2][2] && (row == col || row + col == 2)) ||
                (space[0][2] == space[1][1] && space[0][2] == space[2][0] && (row + col == 2 || row == col));
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (space[i][j] != 'X' && space[i][j] != '0') {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        currentPlayer = 'X';
        turnLabel.setText("Current Turn: Player X");
        space = new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};

        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText(" ");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
