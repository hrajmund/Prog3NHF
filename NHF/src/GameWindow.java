import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private int startingPlayer = 1;
    private final JButton saveButton;
    private final JTextArea p1BlockScore;
    private final JTextArea p2BlockScore;
    private final JTextArea p1NameScore;
    private final JTextArea p2NameScore;

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



        if(boardInitialize && !inputBoard.equals("Board Name")){
            gameManager = new GameManager(new File(inputBoard + ".txt"));
            playerScore = gameManager.getPlayerScore();
            board = initializeBoardBlocks(true);
            P1 = gameManager.getP1();
            P2 = gameManager.getP2();
            p1NameScore = new JTextArea(gameManager.getP1().getName());
            p2NameScore = new JTextArea(gameManager.getP2().getName());
        }else{
            P1 = new Player(p1Name, 1);
            P2 = new Player(p2Name, 2);
            board = initializeBoardBlocks(false);
            playerScore = new int[2];
            playerScore[0] = playerScore[1] = 10;
            gameManager = new GameManager(board, playerScore, P1, P2);
            p1NameScore = new JTextArea(p1Name);
            p2NameScore = new JTextArea(p2Name);
        }


        p1BlockScore = new JTextArea(Integer.toString(playerScore[0]));
        p2BlockScore = new JTextArea(Integer.toString(playerScore[1]));

        p1BlockScore.setFocusable(false);
        p2BlockScore.setFocusable(false);

        p1NameScore.setFocusable(false);
        p2NameScore.setFocusable(false);

        westPanel.add(p1BlockScore);
        westPanel.add(p1NameScore);

        eastPanel.add(p2BlockScore);
        eastPanel.add(p2NameScore);

        KeyHandler keyHandler = new KeyHandler(P1, P2, this, gameManager);
        AddMouseListenerToEachBoardBlock();

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
                        tmp.setRow(i);
                        tmp.setColumn(j);
                        upperPanel.add(tmp);
                        board[i][j] = tmp;
                        P1.setCoordinates(i, j);
                    }
                    else if(i == 8 && j == 4){
                        BoardBlock tmp = new BoardBlock("P2");
                        tmp.setRow(i);
                        tmp.setColumn(j);
                        upperPanel.add(tmp);
                        board[i][j] = tmp;
                        P2.setCoordinates(i, j);
                    } else {
                        BoardBlock tmp = new BoardBlock("E");
                        tmp.setRow(i);
                        tmp.setColumn(j);
                        upperPanel.add(tmp);
                        board[i][j] = tmp;

                    }

                }
            }
            return board;
        }

    }

    public void AddMouseListenerToEachBoardBlock(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j].addMouseListener(new MouseHandler(board[i][j], gameManager, this));
            }
        }
    }

    public int repaintBoard(int tempP1x, int tempP1y, int tempP2x, int tempP2y, int whichPlayer, boolean illegalMove, int nextPlayer){
        if(nextPlayer != whichPlayer){
            IllegalMoveWindow illegalMoveWindow = new IllegalMoveWindow(1);
            return nextPlayer;
        }else if(illegalMove){
            IllegalMoveWindow illegalMoveWindow = new IllegalMoveWindow(2);
            return nextPlayer;
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
        return nextPlayer = (nextPlayer == 1) ? 2 : 1;
    }
    public class SaveEventListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            SaveWindow save = new SaveWindow(board, playerScore, gameManager);
        }
    }

    public void setPutBlockPlayerScore(int whichPlayer){
       if (whichPlayer == 1){
           int temp = Integer.parseInt(p1BlockScore.getText()) - 1;
           p1BlockScore.setText(Integer.toString(temp));
       }else{
           int temp = Integer.parseInt(p2BlockScore.getText()) - 1;
           p2BlockScore.setText(Integer.toString(temp));
       }
    }
    public void setPickBlockPlayerScore(int whichPlayer){
       if (whichPlayer == 1){
           int temp = Integer.parseInt(p1BlockScore.getText()) + 1;
           p1BlockScore.setText(Integer.toString(temp));
       }else{
           int temp = Integer.parseInt(p2BlockScore.getText()) + 1;
           p2BlockScore.setText(Integer.toString(temp));
       }
    }
}
