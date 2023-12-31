import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TimerApp extends JFrame {

    private Timer timer;
    private long startTime;
    private JLabel timerLabel;

    public TimerApp() {
        setTitle("Timer and Stopwatch");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timerLabel = new JLabel("0.00");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        setLayout(new BorderLayout());
        add(timerLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startTimer() {
        timer = new Timer();
        startTime = System.currentTimeMillis();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTimerLabel();
            }
        }, 0, 10); // Update every 10 milliseconds
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void updateTimerLabel() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        double seconds = elapsedTime / 1000.0;
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedSeconds = df.format(seconds);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                timerLabel.setText(formattedSeconds);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimerApp().setVisible(true);
            }
        });
    }
}
