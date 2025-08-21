import javax.swing.*;
import java.awt.event.ActionEvent;

public class SudokuTimer {
    private JLabel timerLabel = new JLabel("Time: 00:00", SwingConstants.CENTER);
    private Timer timer;
    private int elapsedSeconds = 0;

    public SudokuTimer() {
        timerLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
    }

    public void start() {
        if (timer != null && timer.isRunning()) return;
        timer = new Timer(1000, (ActionEvent e) -> {
            elapsedSeconds++;
            int minutes = elapsedSeconds / 60;
            int seconds = elapsedSeconds % 60;
            timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
        });
        timer.start();
    }

    public void reset() {
        if (timer != null) timer.stop();
        elapsedSeconds = 0;
        timerLabel.setText("Time: 00:00");
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }
}