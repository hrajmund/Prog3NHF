import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class GameWindow extends JLabel{
    Player P1, P2;
    private final JPanel upperPanel;
    private final JFrame frame;
    private final GameManager gameManager;
    private final BoardBlock[][] board;
    private final int[] playerScore;
    GameWindow(String p1Name, String p2Name){
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

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(upperPanel, BorderLayout.CENTER);

        P1 = new Player(p1Name, 1);
        P2 = new Player(p2Name, 2);
        board = initializeBoardBlocks();
        playerScore = new int[2];
        gameManager = new GameManager(board, playerScore);
        KeyHandler keyHandler = new KeyHandler(P1, P2, this, gameManager);



        frame.addKeyListener(keyHandler);
        frame.setFocusable(true);
        frame.setVisible(true);


    }
    private BoardBlock[][] initializeBoardBlocks(){
        BoardBlock[][] board = new BoardBlock[9][9];
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9;j++){
                if(i == 0 && j == 4){
                    BoardBlock tmp = new BoardBlock("P1");
                    upperPanel.add(tmp);
                    board[i][j] = tmp;
                    P1.setCoordinates(i, j);
                }
                else if(i == 8 && j == 4){
                    BoardBlock tmp = new BoardBlock("P2");
                    upperPanel.add(tmp);
                    board[i][j] = tmp;
                    P2.setCoordinates(i, j);
                }else if(i == 4 && j == 5){
                    BoardBlock tmp = new BoardBlock("B");
                    upperPanel.add(tmp);
                    board[i][j] = tmp;
                }
                else{
                    BoardBlock tmp = new BoardBlock("E");
                    upperPanel.add(tmp);
                    board[i][j] = tmp;
                }

            }
        }
        return board;
    }

    public void repaintBoard(int tempP1x, int tempP1y, int tempP2x, int tempP2y){
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9;j++){
                if((i == tempP1x && j == tempP1y) || (i == tempP2x && j == tempP2y)){
                    board[i][j].setState("E");
                }else if(i == P1.getX() && j == P1.getY()){
                    board[i][j].setState("P1");
                }else if(i == P2.getX() && j == P2.getY()){
                    board[i][j].setState("P2");
                }
            }
        }
    }
    /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        // Draw player based on its current state
        g.fillOval(P1.getX(), P1.getY(), 20, 20); // Example: draw a circle representing the player
        g.fillOval(P2.getX(), P2.getY(), 20, 20); // Example: draw a circle representing the player
    }*/
}
