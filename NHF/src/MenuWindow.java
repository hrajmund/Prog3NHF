import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

        P1Name = new JTextField("Player 1 Name", 10);
        P1Name.setPreferredSize(new Dimension(P1Name.getWidth(), P1Name.getHeight()+30));
        Font newTextFieldFont=new Font(P1Name.getFont().getName(),Font.BOLD,P1Name.getFont().getSize());
        P1Name.setFont(newTextFieldFont);
        P1Name.setForeground(Color.GRAY);
        P1Name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (P1Name.getText().equals("Player 1 Name")) {
                    P1Name.setText("");
                    P1Name.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (P1Name.getText().isEmpty()) {
                    P1Name.setForeground(Color.GRAY);
                    P1Name.setText("Player 1 Name");
                }
            }
        });

        P2Name = new JTextField("Player 2 Name", 10);
        P2Name.setPreferredSize(new Dimension(P2Name.getWidth(), P2Name.getHeight()+30));
        P2Name.setFont(newTextFieldFont);
        P2Name.setForeground(Color.GRAY);
        P2Name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (P2Name.getText().equals("Player 2 Name")) {
                    P2Name.setText("");
                    P2Name.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (P2Name.getText().isEmpty()) {
                    P2Name.setForeground(Color.GRAY);
                    P2Name.setText("Player 2 Name");
                }
            }
        });

        Start = new JButton("Start");

        End = new JButton("Quit");

        Start.addActionListener(new StartEventListener());
        End.addActionListener(new EndEventListener());
        boardName = new JTextField("Board Name", 20);
        boardName.setPreferredSize(new Dimension(20,20));
        boardName.setFont(newTextFieldFont);
        boardName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (boardName.getText().equals("Board Name")) {
                    boardName.setText("");
                    boardName.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (boardName.getText().isEmpty()) {
                    boardName.setForeground(Color.GRAY);
                    boardName.setText("Board Name");
                }
            }
        });

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
            if(!boardName.getText().equals("Board Name")){
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
