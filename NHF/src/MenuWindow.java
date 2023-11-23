import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame{
    private final JButton Start;
    private final JButton End;
    private final JPanel upperPanel;
    private final JPanel lowerPanel;
    private final JPanel middlePanel;
    private final JFrame frame;
    private final JTextField boardName;
    private final JTextField P1Name;
    private final JTextField P2Name;
    MenuWindow(){
        frame = new JFrame("Quoridor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new GridLayout(3,1));
        frame.setResizable(false);

        upperPanel = new JPanel(new FlowLayout());
        middlePanel = new JPanel(new FlowLayout());
        lowerPanel = new JPanel(new FlowLayout());

        P1Name = new JTextField(20);
        P2Name = new JTextField(20);
        Start = new JButton("Start");
        Start.setSize(100,50);
        End = new JButton("Quit");
        End.setSize(100,50);
        Start.addActionListener(new StartEventListener());
        End.addActionListener(new EndEventListener());
        boardName = new JTextField(20);

        upperPanel.add(Start);
        upperPanel.add(boardName);
        middlePanel.add(P1Name);
        middlePanel.add(P2Name);
        lowerPanel.add(End);

        frame.add(upperPanel);
        frame.add(middlePanel);
        frame.add(lowerPanel);
        frame.setVisible(true);
    }

    public class StartEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean boardInit = false;
            String inputBoard = "";
            if(!boardName.getText().isEmpty()){
                boardInit = true;
                inputBoard = boardName.getText();
            }
            GameWindow gameWindow = new GameWindow(P1Name.getText(), P2Name.getText(), inputBoard, boardInit);
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
