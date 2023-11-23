import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverWindow {
    private final JTextArea winnerMessage;
    private final JButton saveButton;
    private final JPanel upperPanel, lowerPanel;
    private final JFrame frame;

    GameOverWindow(Player temp) {
        frame = new JFrame("Győzelem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 110);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 1));

        upperPanel = new JPanel(new FlowLayout());
        lowerPanel = new JPanel(new BorderLayout());

        saveButton = new JButton("Kilépés");
        saveButton.addActionListener(new OkActionListener());
        winnerMessage = new JTextArea();
        winnerMessage.setText(temp.getName() + " Győzőtt!");
        winnerMessage.setFocusable(false);
        upperPanel.add(winnerMessage);
        lowerPanel.add(saveButton);

        frame.add(upperPanel);
        frame.add(lowerPanel);

        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    public class OkActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
