import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.EventListener;

public class GameWindow extends JLabel{
    Player P1, P2;
    private final JPanel upperPanel;
    private final JFrame frame;
    private final GameManager gameManager;
    private final BoardBlock[][] board;
    private final int[] playerScore;
    private final JButton saveButton;

    GameWindow(String p1Name, String p2Name, String inputBoard, boolean boardInitialize){
        frame = new JFrame("Quoridor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveEventListener());
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
        southPanel.add(saveButton);

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(upperPanel, BorderLayout.CENTER);

        P1 = new Player(p1Name, 1);
        P2 = new Player(p2Name, 2);
        //boardInitialize = true;

        if(boardInitialize){
            gameManager = new GameManager(new File(inputBoard + ".txt"));
            playerScore = gameManager.getPlayerScore();
            board = initializeBoardBlocks(true);
        }else{
            board = initializeBoardBlocks(false);
            playerScore = new int[2];
            gameManager = new GameManager(board, playerScore);
        }


        KeyHandler keyHandler = new KeyHandler(P1, P2, this, gameManager);



        frame.addKeyListener(keyHandler);
        frame.setFocusable(true);
        frame.setVisible(true);


    }
    private BoardBlock[][] initializeBoardBlocks(boolean boardInitialize){
        if(boardInitialize){
            BoardBlock[][] board = gameManager.getBoard();
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    upperPanel.add(board[i][j]);
                }
            }
            return board;
        }else{
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

    }

    public void repaintBoard(int tempP1x, int tempP1y, int tempP2x, int tempP2y, int whichPlayer, boolean illegalMove){
        if(illegalMove){
            return;
        }
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9;j++){
                switch (whichPlayer){
                    case 1: {
                        if(i == P1.getX() && j == P1.getY()){
                            board[i][j].setState("P1");
                            board[tempP1x][tempP1y].setState("E");
                        }
                        break;
                    }
                    case 2: {
                        if(i == P2.getX() && j == P2.getY()){
                            board[i][j].setState("P2");
                            board[tempP2x][tempP2y].setState("E");
                        }
                        break;
                    }
                }
            }
        }
    }
    public class SaveEventListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            SaveWindow save = new SaveWindow(board, playerScore, gameManager);
        }
    }
}
