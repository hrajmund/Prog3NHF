import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveWindow extends JPanel{
    private final JTextField boardName;
    private final JButton saveButton;
    private final JPanel upperPanel, lowerPanel;
    private final JFrame frame;
    private final GameManager saveGameManager;
    private final BoardBlock[][] board;
    private final int playerScore[];

    SaveWindow(BoardBlock[][] saveBoard, int[] savePlayerScore, GameManager gameManager){
        frame = new JFrame("Save");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,110);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2,1));

        board = saveBoard;
        playerScore = savePlayerScore;
        saveGameManager = gameManager;

        upperPanel = new JPanel(new FlowLayout());
        lowerPanel = new JPanel(new BorderLayout());

        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveActionListener());
        boardName = new JTextField(20);

        upperPanel.add(boardName);
        lowerPanel.add(saveButton);

        frame.add(upperPanel);
        frame.add(lowerPanel);

        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    public class SaveActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            saveGameManager.save(boardName.getText(), board, playerScore);
            frame.dispose();
        }
    }

}