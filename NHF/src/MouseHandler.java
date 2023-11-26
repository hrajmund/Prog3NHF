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
                        } else if(j == 8){
                            gameManager.board[i][j - 1].setState("E");
                            gameManager.board[i+1][j - 1].setState("E");
                        }
                        //gameManager.board[i][j+1].setState("B");
                    }
                }
            }
        }else if(e.getButton() == MouseEvent.BUTTON3){
            if(gameManager.playerScore[gameManager.getWhichPlayer()-1] < 1){
                return;
            }
            gameManager.PlayerPutBlockOnTheBoard(gameManager.getNextPlayer());
            gameManager.setWhichPlayer(gameManager.getNextPlayer());
            gameWindow.setPutBlockPlayerScore(gameManager.getWhichPlayer());

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (block.getRow() == i && block.getColumn() == j) {
                        if(j == 0){
                            gameManager.board[i][0].setState("B");
                            gameManager.board[i+1][0].setState("B");
                        }else if(j == 1){
                            gameManager.board[i][1].setState("B");
                            gameManager.board[i+1][1].setState("B");
                        } else if (j > 1 && gameManager.findPath(gameManager.board, gameManager.getP1(), gameManager.getP2(), i, j+1, i+1,j+1)
                                && (!gameManager.board[i][j+1].getState().equals("B") || !gameManager.board[i][j+2].getState().equals("B"))) {
                            gameManager.board[i][j + 1].setState("B");
                            gameManager.board[i+1][j + 1].setState("B");
                        } else if(j == 8 && gameManager.findPath(gameManager.board, gameManager.getP1(), gameManager.getP2(), i, j-1,i+1,j-1)
                                && (!gameManager.board[i][j-1].getState().equals("B") || !gameManager.board[i+1][j-1].equals("B"))){
                            gameManager.board[i][j - 1].setState("B");
                            gameManager.board[i+1][j - 1].setState("B");
                        }else if(i > 7 && gameManager.findPath(gameManager.board, gameManager.getP1(), gameManager.getP2(), 7, j,8,j)
                                && (!gameManager.board[i][j-1].getState().equals("B") || !gameManager.board[i+1][j-1].equals("B"))) {
                                gameManager.board[7][j].setState("B");
                                gameManager.board[8][j].setState("B");
                        }

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
                        if (j == 0) {
                            gameManager.board[i][0].setState("E");
                            gameManager.board[i][1].setState("E");
                        } else if (j > 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/) {
                            gameManager.board[i][j + 1].setState("E");
                            gameManager.board[i][j + 2].setState("E");
                        } else if(j == 8){
                            gameManager.board[i][j].setState("E");
                            gameManager.board[i][j +1 ].setState("E");
                        }
                    }
                }
            }
        }else if(e.getButton() == MouseEvent.BUTTON1){
            if(gameManager.playerScore[gameManager.getNextPlayer()-1] < 1){
                return;
            }
            gameManager.PlayerPutBlockOnTheBoard(gameManager.getNextPlayer());
            gameManager.setWhichPlayer(gameManager.getNextPlayer());
            gameWindow.setPutBlockPlayerScore(gameManager.getWhichPlayer());

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (block.getRow() == i && block.getColumn() == j) {
                            if(j == 0){
                                gameManager.board[i][0].setState("B");
                                gameManager.board[i][1].setState("B");
                            }else if (j > 0 && gameManager.findPath(gameManager.board, gameManager.getP1(), gameManager.getP2(), i, j+1, i,j+2)
                                && (!gameManager.board[i][j+1].getState().equals("B") || !gameManager.board[i][j+2].getState().equals("B"))) {
                                gameManager.board[i][j + 1].setState("B");
                                gameManager.board[i][j + 2].setState("B");
                                System.out.println(gameManager.findPath(gameManager.board, gameManager.getP1(), gameManager.getP2(), i, j+1, i,j+2));
                            } else if(j == 8 && gameManager.findPath(gameManager.board, gameManager.getP1(), gameManager.getP2(), i, j-1, i,j-2)
                                && (!gameManager.board[i][j-1].getState().equals("B") || !gameManager.board[i][j-2].getState().equals("B"))){
                                gameManager.board[i][j - 1].setState("B");
                                gameManager.board[i][j - 2].setState("B");
                            }
                        //System.out.println(gameManager.findPath(gameManager.board, gameManager.getP1(), gameManager.getP2(), i, j+1, i,j+2));
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