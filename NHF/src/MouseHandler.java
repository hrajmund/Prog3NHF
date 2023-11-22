import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private BoardBlock block;
    private GameManager gameManager;

    public MouseHandler(BoardBlock block, GameManager gameManager) {
        this.block = block;
        this.gameManager = gameManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(block.getRow() == i && block.getColumn() == j){
                    if(j == 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/){
                        gameManager.board[i][0].setState("B");
                        System.out.println("First: " + i + " " + j);
                        gameManager.board[i][1].setState("B");
                        System.out.println("Second: " + i + " " + j);
                    }else if(j > 0 /*&& (!block.getState().equals("B") && !gameManager.board[block.getRow()][block.getColumn()].getState().equals("B"))*/){
                        gameManager.board[i][j+1].setState("B");
                        gameManager.board[i][j+2].setState("B");
                    }
                    //gameManager.board[i][j+1].setState("B");
                }
            }
        }

        //block.setState("B");

        /*for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if (block.hashCode() == gameManager.board[i][0].hashCode()){
                    gameManager.board[i][0].setState("B");
                    gameManager.board[i][1].setState("B");
                }else if(block.hashCode() == gameManager.board[i][2].hashCode()){
                    gameManager.board[i][2].setState("B");
                    gameManager.board[i][3].setState("B");
                }else if(block.hashCode() == gameManager.board[i][1].hashCode()){
                    gameManager.board[i][3].setState("B");
                    gameManager.board[i][4].setState("B");
                }else if (block.hashCode() == gameManager.board[i][j].hashCode()){
                    gameManager.board[i][j+2].setState("B");
                    gameManager.board[i][j+3].setState("B");
                }
            }
        }
        /*int x = e.getX() / 50;
        int y = e.getY() / 50;
        System.out.println(x + " " + y);
        if(gameManager.getBoard()[x-2][y].getState().equals("E")){
            block.setState("B");
        }*/
        /*
        if ("E".equals(block.getState())) {

            block.setState("B");
            // Assuming setState method already calls repaint
        }*/
    }
}