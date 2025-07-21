import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class SudokuUI {
    private static final int SIZE = 9;
    private final JTextField[][] cells = new JTextField[SIZE][SIZE];
    private final int[][][] puzzles = {
            {

                    {5, 0, 6, 0, 0, 3, 4, 7, 8},
                    {1, 8, 2, 4, 5, 0, 3, 6, 9},
                    {7, 4, 0, 6, 0, 0, 0, 5, 0},
                    {0, 1, 5, 7, 3, 0, 9, 4, 6},
                    {3, 7, 4, 9, 0, 6, 2, 8, 5},
                    {0, 6, 9, 2, 4, 5, 7, 3, 0},
                    {6, 3, 1, 8, 0, 4, 0, 0, 0},
                    {4, 2, 0, 0, 7, 1, 6, 0, 0},
                    {9, 0, 0, 3, 6, 2, 8, 0, 0}
            },
            {

                    {5, 3, 0, 0, 7, 0, 0, 0, 0},
                    {6, 0, 0, 1, 9, 5, 0, 0, 0},
                    {0, 9, 8, 0, 0, 0, 0, 6, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 3},
                    {4, 0, 0, 8, 0, 3, 0, 0, 1},
                    {7, 0, 0, 0, 2, 0, 0, 0, 6},
                    {0, 6, 0, 0, 0, 0, 2, 8, 0},
                    {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 9}
            },
            {
                    {0, 2, 0, 6, 0, 8, 0, 0, 0},
                    {5, 8, 0, 0, 0, 9, 7, 0, 0},
                    {0, 0, 0, 0, 4, 0, 0, 0, 0},
                    {3, 7, 0, 0, 0, 0, 5, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 8, 0, 0, 0, 0, 1, 3},
                    {0, 0, 0, 0, 2, 0, 0, 0, 0},
                    {0, 0, 9, 8, 0, 0, 0, 3, 6},
                    {0, 0, 0, 3, 0, 6, 0, 9, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 2, 0, 0},
                    {0, 8, 0, 0, 0, 7, 0, 9, 0},
                    {6, 0, 2, 0, 0, 0, 5, 0, 0},
                    {0, 7, 0, 0, 6, 0, 0, 0, 0},
                    {0, 0, 0, 9, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 2, 0, 0, 4, 0},
                    {0, 0, 5, 0, 0, 0, 6, 0, 3},
                    {0, 9, 0, 4, 0, 0, 0, 7, 0},
                    {0, 0, 6, 0, 0, 0, 0, 0, 0}
            }
    };

    public SudokuUI() {
        JFrame frame = new JFrame("Sudoku Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        Font font = new Font("SansSerif", Font.BOLD, 20);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(font);

                int top = (row % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == 8) ? 3 : 1;
                int right = (col == 8) ? 3 : 1;

                Border border = new MatteBorder(top, left, bottom, right, Color.BLACK);
                cell.setBorder(border);

                cells[row][col] = cell;
                boardPanel.add(cell);
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton newGameBtn = new JButton("New Game");
        JButton checkBtn = new JButton("Check");

        newGameBtn.addActionListener(e -> loadRandomPuzzle());
        checkBtn.addActionListener(e -> {
            int[][] grid = new int[SIZE][SIZE];
            try {
                for (int row = 0; row < SIZE; row++) {
                    for (int col = 0; col < SIZE; col++) {
                        String text = cells[row][col].getText().trim();
                        grid[row][col] = text.isEmpty() ? 0 : Integer.parseInt(text);
                    }
                }

                if (isValidSudoku(grid)) {
                    JOptionPane.showMessageDialog(frame, "✅ Correct Solution!");
                } else {
                    JOptionPane.showMessageDialog(frame, "❌ Incorrect. Try Again!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "⚠️ Only digits 1-9 allowed.");
            }
        });

        buttonPanel.add(newGameBtn);
        buttonPanel.add(checkBtn);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void loadRandomPuzzle() {
        int randomIndex = new Random().nextInt(puzzles.length);
        int[][] puzzle = puzzles[randomIndex];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = puzzle[row][col];
                JTextField cell = cells[row][col];

                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                } else {
                    cell.setText("");
                    cell.setEditable(true);
                    cell.setBackground(Color.WHITE);
                }
            }
        }
    }

    private boolean isValidSudoku(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            if (!isValidSet(grid[i])) return false;

            int[] col = new int[SIZE];
            for (int j = 0; j < SIZE; j++) {
                col[j] = grid[j][i];
            }
            if (!isValidSet(col)) return false;
        }

        for (int boxRow = 0; boxRow < SIZE; boxRow += 3) {
            for (int boxCol = 0; boxCol < SIZE; boxCol += 3) {
                int[] box = new int[SIZE];
                int index = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        box[index++] = grid[boxRow + i][boxCol + j];
                    }
                }
                if (!isValidSet(box)) return false;
            }
        }

        return true;
    }

    private boolean isValidSet(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (num == 0) return false;
            if (seen.contains(num)) return false;
            seen.add(num);
        }
        return true;
    }
}