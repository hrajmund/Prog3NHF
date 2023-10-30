import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class GameWindow extends JFrame {
    private final JButton Start;
    private final JButton End;
    private final JPanel upperPanel;
    private final JPanel lowerPanel;
    GameWindow(){
        super("Quoridor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLayout(new GridLayout(2,1));
        this.setResizable(false);

        upperPanel = new JPanel(new FlowLayout());
        lowerPanel = new JPanel(new FlowLayout());


        Start = new JButton("Start");
        Start.setSize(100,50);
        End = new JButton("Quit");
        End.setSize(100,50);
        Start.addActionListener(new StartEventListener());
        End.addActionListener(new EndEventListener());

        upperPanel.add(Start);
        lowerPanel.add(End);

        this.add(upperPanel);
        this.add(lowerPanel);
    }

    public class StartEventListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO
        }
    }

    public class EndEventListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
