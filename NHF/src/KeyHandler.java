import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private int tempP1x, tempP1y, tempP2x, tempP2y;
    private boolean illegalMove;
    private Player P1;
    private Player P2;
    private GameWindow gameWindow;
    private GameManager gameManager;
    public KeyHandler(Player P1, Player P2, GameWindow gameWindow, GameManager gameManager){
        this.P1 = P1;
        this.P2 = P2;
        this.gameWindow = gameWindow;
        this.gameManager = gameManager;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        tempP1x = P1.getX();
        tempP1y = P1.getY();
        tempP2x = P2.getX();
        tempP2y = P2.getY();
        illegalMove = false;
        if(gameManager.getNextPlayer() == 1){
            try{
                switch (e.getKeyCode()){
                    case KeyEvent.VK_A: {
                        gameManager.setWhichPlayer(1);
                        if (gameManager.StepPlayer(P1.getX(), P1.getY() - 1)) {
                            gameManager.getP1().moveLeft();
                        } else {
                            illegalMove = true;
                        }
                        break;
                    }
                    case KeyEvent.VK_D: {
                        gameManager.setWhichPlayer(1);
                        if (gameManager.StepPlayer(P1.getX(), P1.getY() + 1)) {
                            gameManager.getP1().moveRight();
                        } else {
                            illegalMove = true;
                        }
                        break;
                    }
                    case KeyEvent.VK_S: {
                        gameManager.setWhichPlayer(1);
                        if (gameManager.StepPlayer(P1.getX() + 1, P1.getY())) {
                            gameManager.getP1().moveDown();
                        } else {
                            illegalMove = true;
                        }
                        break;
                    }
                    case KeyEvent.VK_W: {
                        gameManager.setWhichPlayer(1);
                        if (gameManager.StepPlayer(P1.getX() - 1, P1.getY())) {
                            gameManager.getP1().moveUp();
                        } else {
                            illegalMove = true;
                        }
                        break;
                    }
                    default: break;
                }
            }catch (Exception excep){
                System.out.println(excep);
            }
        }else if(gameManager.getNextPlayer() == 2){
            //whichPlayer = 2;
            try{
                switch (e.getKeyCode()){
                    case KeyEvent.VK_LEFT: {
                        gameManager.setWhichPlayer(2);
                        if (gameManager.StepPlayer(P2.getX(), P2.getY() - 1)) {
                            gameManager.getP2().moveLeft();
                        } else {
                            illegalMove = true;
                        }
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        gameManager.setWhichPlayer(2);
                        if (gameManager.StepPlayer(P2.getX(), P2.getY() + 1)) {
                            gameManager.getP2().moveRight();
                        } else {
                            illegalMove = true;
                        }
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        gameManager.setWhichPlayer(2);
                        if (gameManager.StepPlayer(P2.getX() + 1, P2.getY())) {
                            gameManager.getP2().moveDown();
                        } else {
                            illegalMove = true;
                        }
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        gameManager.setWhichPlayer(2);
                        if (gameManager.StepPlayer(P2.getX() - 1, P2.getY())) {
                            gameManager.getP2().moveUp();
                        } else {
                            illegalMove = true;
                        }
                        break;
                    }
                    default: break;
                }
            }catch (Exception excep){
                System.out.println(excep);
            }
        }

        gameManager.setNextPlayer(gameWindow.repaintBoard(tempP1x, tempP1y, tempP2x, tempP2y, gameManager.getWhichPlayer(), illegalMove, gameManager.getNextPlayer()));
        illegalMove = false;

        System.out.println(gameManager.getPlayer().getY());
        if(gameManager.GameOver(gameManager.getPlayer())) {
            GameOverWindow gameOverWindow = new GameOverWindow(gameManager.getPlayer());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
