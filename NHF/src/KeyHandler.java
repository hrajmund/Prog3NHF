import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private int tempP1x, tempP1y, tempP2x, tempP2y, whichPlayer;
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
        whichPlayer = 0;
        illegalMove = false;

        try {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A: {
                    whichPlayer = 1;
                    if (gameManager.StepPlayer(P1.getX(), P1.getY() - 1)) {
                        P1.moveLeft();
                    } else {
                        System.out.println("Illegal move!");
                        illegalMove = true;
                    }
                    break;
                }
                case KeyEvent.VK_D: {
                    whichPlayer = 1;
                    if (gameManager.StepPlayer(P1.getX(), P1.getY() + 1)) {
                        P1.moveRight();
                    } else {
                        System.out.println("Illegal move!");
                        illegalMove = true;
                    }
                    break;
                }
                case KeyEvent.VK_S: {
                    whichPlayer = 1;
                    if (gameManager.StepPlayer(P1.getX() + 1, P1.getY())) {
                        P1.moveDown();
                    } else {
                        System.out.println("Illegal move!");
                        illegalMove = true;
                    }
                    break;
                }
                case KeyEvent.VK_W: {
                    whichPlayer = 1;
                    if (gameManager.StepPlayer(P1.getX() - 1, P1.getY())) {
                        P1.moveUp();
                    } else {
                        System.out.println("Illegal move!");
                        illegalMove = true;
                    }
                    break;
                }
                case KeyEvent.VK_LEFT: {
                    whichPlayer = 2;
                    if (gameManager.StepPlayer(P2.getX(), P2.getY() - 1)) {
                        P2.moveLeft();
                    } else {
                        System.out.println("Illegal move!");
                        illegalMove = true;
                    }
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    whichPlayer = 2;
                    if (gameManager.StepPlayer(P2.getX(), P2.getY() + 1)) {
                        P2.moveRight();
                    } else {
                        System.out.println("Illegal move!");
                        illegalMove = true;
                    }
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    whichPlayer = 2;
                    if (gameManager.StepPlayer(P2.getX() + 1, P2.getY())) {
                        P2.moveDown();
                    } else {
                        System.out.println("Illegal move!");
                        illegalMove = true;
                    }
                    break;
                }
                case KeyEvent.VK_UP: {
                    whichPlayer = 2;
                    if (gameManager.StepPlayer(P2.getX() - 1, P2.getY())) {
                        P2.moveUp();
                    } else {
                        System.out.println("Illegal move!");
                        illegalMove = true;
                    }
                    break;
                }
            }
        }catch (Exception excep){
            System.out.println(excep);
        }
        gameWindow.repaintBoard(tempP1x, tempP1y, tempP2x, tempP2y, whichPlayer, illegalMove);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameWindow.repaintBoard(tempP1x, tempP1y, tempP2x, tempP2y, whichPlayer, illegalMove);
        illegalMove = false;
    }
}
