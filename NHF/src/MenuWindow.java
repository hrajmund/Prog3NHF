import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame{
    private final JButton Start;
    private final JButton End;
    private final JPanel upperPanel;
    private final JPanel lowerPanel;
    private final JFrame frame;
    MenuWindow(){

        frame = new JFrame("Quoridor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new GridLayout(2,1));
        frame.setResizable(false);

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

        frame.add(upperPanel);
        frame.add(lowerPanel);
        frame.setVisible(true);
    }

    public class StartEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameWindow gameWindow = new GameWindow();
            frame.dispose();
        }
    }

    public class EndEventListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
