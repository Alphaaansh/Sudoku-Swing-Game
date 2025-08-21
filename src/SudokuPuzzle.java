import javax.swing.*;

public class SudokuPuzzle {
    private SudokuGrid grid;
    private int currentPuzzleIndex = 0;

    private int[][][] puzzles = {
            {
                    {5,3,0,0,7,0,0,0,0},
                    {6,0,0,1,9,5,0,0,0},
                    {0,9,8,0,0,0,0,6,0},
                    {8,0,0,0,6,0,0,0,3},
                    {4,0,0,8,0,3,0,0,1},
                    {7,0,0,0,2,0,0,0,6},
                    {0,6,0,0,0,0,2,8,0},
                    {0,0,0,4,1,9,0,0,5},
                    {0,0,0,0,8,0,0,7,9}
            },
            {
                    {0,0,0,2,6,0,7,0,1},
                    {6,8,0,0,7,0,0,9,0},
                    {1,9,0,0,0,4,5,0,0},
                    {8,2,0,1,0,0,0,4,0},
                    {0,0,4,6,0,2,9,0,0},
                    {0,5,0,0,0,3,0,2,8},
                    {0,0,9,3,0,0,0,7,4},
                    {0,4,0,0,5,0,0,3,6},
                    {7,0,3,0,1,8,0,0,0}
            }
    };

    public SudokuPuzzle(SudokuGrid grid) {
        this.grid = grid;
    }

    public void loadPuzzle() {
        loadPuzzle(currentPuzzleIndex);
    }

    private void loadPuzzle(int index) {
        JTextField[][] cells = grid.getCells();
        int[][] puzzle = puzzles[index];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(puzzle[row][col]));
                    cells[row][col].setEditable(false);
                    cells[row][col].setForeground(java.awt.Color.DARK_GRAY);
                } else {
                    cells[row][col].setText("");
                    cells[row][col].setEditable(true);
                    cells[row][col].setForeground(java.awt.Color.BLACK);
                }
            }
        }
    }

    public void loadNewPuzzle() {
        currentPuzzleIndex = (currentPuzzleIndex + 1) % puzzles.length;
        loadPuzzle(currentPuzzleIndex);
    }
}