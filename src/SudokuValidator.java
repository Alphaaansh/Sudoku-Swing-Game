import javax.swing.*;

public class SudokuValidator {
    private SudokuGrid grid;

    public SudokuValidator(SudokuGrid grid) {
        this.grid = grid;
    }

    public void checkSolution() {
        JTextField[][] cells = grid.getCells();
        boolean valid = true;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = cells[row][col].getText();
                if (text.isEmpty() || !text.matches("[1-9]")) {
                    valid = false;
                    cells[row][col].setBackground(java.awt.Color.PINK);
                } else {
                    cells[row][col].setBackground(java.awt.Color.WHITE);
                }
            }
        }

        if (valid && isSolved()) {
            JOptionPane.showMessageDialog(null, "Congratulations! You solved it!");
        } else if (valid) {
            JOptionPane.showMessageDialog(null, "Not solved yet, keep going!");
        } else {
            JOptionPane.showMessageDialog(null, "Some blocks are invalid!");
        }
    }

    private boolean isSolved() {
        JTextField[][] cells = grid.getCells();
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[10];
            boolean[] colCheck = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int rowVal = parseCell(cells[i][j].getText());
                int colVal = parseCell(cells[j][i].getText());
                if (rowVal == -1 || colVal == -1 || rowCheck[rowVal] || colCheck[colVal]) {
                    return false;
                }
                rowCheck[rowVal] = true;
                colCheck[colVal] = true;
            }
        }
        return true;
    }

    private int parseCell(String text) {
        if (!text.matches("[1-9]")) return -1;
        return Integer.parseInt(text);
    }
}