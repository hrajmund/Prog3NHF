import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private BoardBlock block;
    private GameManager gameManager;
    private GameWindow gameWindow;

    public MouseHandler(BoardBlock block, GameManager gameManager, GameWindow gameWindow) {
        this.block = block;
        this.gameManager = gameManager;
        this.gameWindow = gameWindow;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2){
            gameManager.PlayerPickBlockOffTheBoard(gameManager.getNextPlayer());
            gameManager.setWhichPlayer(gameManager.getNextPlayer());
            gameWindow.setPickBlockPlayerScore(gameManager.getWhichPlayer());
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (block.getRow() == i && block.getColumn() == j) {
                        if (j == 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/) {
                            gameManager.board[0][j].setState("E");
                            System.out.println("First: " + i + " " + j);
                            gameManager.board[1][j].setState("E");
                            System.out.println("Second: " + i + " " + j);
                        } else if (j > 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/) {
                            gameManager.board[i][j + 1].setState("E");
                            gameManager.board[i+1][j + 1].setState("E");
                        }
                        //gameManager.board[i][j+1].setState("B");
                    }
                }
            }
        }else if(e.getButton() == MouseEvent.BUTTON3){
            gameManager.PlayerPutBlockOnTheBoard(gameManager.getNextPlayer());
            gameManager.setWhichPlayer(gameManager.getNextPlayer());
            gameWindow.setPutBlockPlayerScore(gameManager.getWhichPlayer());
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (block.getRow() == i && block.getColumn() == j) {
                        if (j == 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/) {
                            gameManager.board[0][j].setState("B");
                            System.out.println("First: " + i + " " + j);
                            gameManager.board[1][j].setState("B");
                            System.out.println("Second: " + i + " " + j);
                        } else if (j > 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/) {
                            gameManager.board[i][j + 1].setState("B");
                            gameManager.board[i+1][j + 1].setState("B");
                        }
                        //gameManager.board[i][j+1].setState("B");
                    }
                }
            }
        }else if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2){
            gameManager.PlayerPickBlockOffTheBoard(gameManager.getNextPlayer());
            gameManager.setWhichPlayer(gameManager.getNextPlayer());
            gameWindow.setPickBlockPlayerScore(gameManager.getWhichPlayer());
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (block.getRow() == i && block.getColumn() == j) {
                        if (j == 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/) {
                            gameManager.board[i][0].setState("E");
                            System.out.println("First: " + i + " " + j);
                            gameManager.board[i][1].setState("E");
                            System.out.println("Second: " + i + " " + j);
                        } else if (j > 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/) {
                            gameManager.board[i][j + 1].setState("E");
                            gameManager.board[i][j + 2].setState("E");
                        }
                        //gameManager.board[i][j+1].setState("B");
                    }
                }
            }
        }else if(e.getButton() == MouseEvent.BUTTON1){
            gameManager.PlayerPutBlockOnTheBoard(gameManager.getNextPlayer());
            gameManager.setWhichPlayer(gameManager.getNextPlayer());
            gameWindow.setPutBlockPlayerScore(gameManager.getWhichPlayer());
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (block.getRow() == i && block.getColumn() == j) {
                        if(gameManager.canBlockBeChanged(i,j,"B",gameManager.getWhichPlayer(), gameManager.getP1(), gameManager.getP2())){
                            if (j == 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/) {
                                gameManager.board[i][0].setState("B");
                                System.out.println("First: " + i + " " + j);
                                gameManager.board[i][1].setState("B");
                                System.out.println("Second: " + i + " " + j);
                            } else if (j > 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/) {
                                gameManager.board[i][j + 1].setState("B");
                                gameManager.board[i][j + 2].setState("B");
                            }
                        }else{
                            System.out.println("no");
                        }

                        //gameManager.board[i][j+1].setState("B");
                    }
                }
            }
        }

        if(gameManager.getNextPlayer() == 1){
            gameManager.setNextPlayer(2);
        }else{
            gameManager.setNextPlayer(1);
        }

    }
}