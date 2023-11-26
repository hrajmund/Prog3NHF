import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameManagerTest gameManagerTest = new GameManagerTest();
        BoardBlockTest boardBlockTest = new BoardBlockTest();
        PlayerTest playerTest = new PlayerTest();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuWindow menuWindow = new MenuWindow();
            }
        });
    }
}
