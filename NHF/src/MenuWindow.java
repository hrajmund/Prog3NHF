import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

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

        P1Name = new JTextField();
        P2Name = new JTextField();
        Start = new JButton("Start");
        End = new JButton("Quit");

        Start.addActionListener(new StartEventListener());
        End.addActionListener(new EndEventListener());
        boardName = new JTextField(20);
        boardName.setPreferredSize(new Dimension(20,20));
        upperPanel.add(Start);
        upperPanel.add(boardName);
        middlePanel.add(P1Name);
        middlePanel.add(P2Name);
        lowerPanel.add(End);

        frame.add(upperPanel);
        frame.add(middlePanel);
        frame.add(lowerPanel);
        frame.getContentPane().setBackground(new Color(47,72,88));
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
            new GameWindow(P1Name.getText(), P2Name.getText(), inputBoard, boardInit);
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
