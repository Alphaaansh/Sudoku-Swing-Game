import javax.swing.*;
import java.awt.*;

public class SudokuGrid {
    private JTextField[][] cells = new JTextField[9][9];
    private JPanel gridPanel;

    public SudokuGrid() {
        gridPanel = new JPanel(new GridLayout(9, 9));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));

                int top = (row % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == 8) ? 3 : 1;
                int right = (col == 8) ? 3 : 1;
                cells[row][col].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                gridPanel.add(cells[row][col]);
            }
        }
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public JTextField[][] getCells() {
        return cells;
    }
}