import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IllegalMoveWindow {
    private final JTextArea errorMessage;
    private final JButton saveButton;
    private final JPanel upperPanel, lowerPanel;
    private final JFrame frame;

    IllegalMoveWindow(int whichError) {
        frame = new JFrame("Error");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 110);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 1));

        upperPanel = new JPanel(new FlowLayout());
        lowerPanel = new JPanel(new BorderLayout());

        saveButton = new JButton("Ok");
        saveButton.addActionListener(new OkActionListener());
        errorMessage = new JTextArea();
        if (whichError == 1){
            errorMessage.setText("Egy játékos nem léphet kétszer egymás után!");
        }else if (whichError == 2){
            errorMessage.setText("Nem léphetsz arra a mezőre!");
        }else if(whichError == 3){
            errorMessage.setText("Nincs több akadályod!");
        }
        errorMessage.setFocusable(false);
        upperPanel.add(errorMessage);
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
            frame.setVisible(false);
        }
    }
}
