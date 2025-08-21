import javax.swing.*;
import java.awt.*;

public class SudokuUI extends JFrame {
    private SudokuGrid grid;
    private SudokuTimer timer;
    private SudokuPuzzle puzzleManager;
    private SudokuValidator validator;

    public SudokuUI() {
        setTitle("Sudoku Game");
        setSize(600, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        grid = new SudokuGrid();
        timer = new SudokuTimer();
        puzzleManager = new SudokuPuzzle(grid);
        validator = new SudokuValidator(grid);

        add(timer.getTimerLabel(), BorderLayout.NORTH);

        add(grid.getGridPanel(), BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton resetButton = new JButton("Reset");
        JButton newGameButton = new JButton("New Game");
        JButton checkButton = new JButton("Check");

        startButton.addActionListener(e -> timer.start());
        resetButton.addActionListener(e -> timer.reset());
        newGameButton.addActionListener(e -> puzzleManager.loadNewPuzzle());
        checkButton.addActionListener(e -> validator.checkSolution());

        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(newGameButton);
        buttonPanel.add(checkButton);

        add(buttonPanel, BorderLayout.SOUTH);

        puzzleManager.loadPuzzle();
        setVisible(true);
    }

}
