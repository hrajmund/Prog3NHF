import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //MenuWindow menu = new MenuWindow();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuWindow menuWindow = new MenuWindow();

            }
        });
        //GameWindow gw = new GameWindow("Player 1", "Player2");
    }
}
