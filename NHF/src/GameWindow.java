import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class GameWindow extends JFrame {
    private final JPanel upperPanel;
    //private final JPanel lowerPanel;
    private final JFrame frame;
    private final KeyHandler keyHandler = new KeyHandler();
    GameWindow(){
        frame = new JFrame("Quoridor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(new BorderLayout());
        frame.setResizable(true);

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(100,100));
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(100,100));
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(150,100));
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(100,100));

        upperPanel = new JPanel(new GridLayout(9,9,-90,-6));
        upperPanel.setPreferredSize(new Dimension(100,100));
        initializeBoardBlocks();

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(upperPanel, BorderLayout.CENTER);

        frame.addKeyListener(keyHandler);
        frame.setFocusable(true);

        frame.setVisible(true);
    }
    private void initializeBoardBlocks(){
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9;j++){
                upperPanel.add(new BoardBlock("E"));
            }
        }
    }
}
