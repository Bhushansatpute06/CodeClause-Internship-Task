import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeStopwatchApp extends JFrame{
    
    private Timer timer;
    private int elapsedTime=0;
    private boolean isRunning=false;

    private JLabel timeLabel;

    public TimerStopwatchApp(){
        setTitle("Timer & Stopwatch");
        setSize(300,150);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timeLabel=new JLabel("0:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        JButton startButton=new JButton("Start/Stop");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(isRunning){
                    stop();
                }else{
                    start();
                }
            }
        });
        
        JButton resetButton = new JButton("Reset");
    }

}
