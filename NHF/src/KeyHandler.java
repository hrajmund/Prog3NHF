import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
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
        // Player 1 controls
        int tempP1x = P1.getX();
        int tempP1y = P1.getY();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: // 'A' key for Player 1's left move
                leftPressed = true;
                if(gameManager.StepPlayer(P1, P1.getX(), P1.getY()-1))
                    P1.moveLeft();
                else
                    System.out.println("Illegal move!");

                break;
            case KeyEvent.VK_D: // 'D' key for Player 1's right move
                rightPressed = true;
                if(gameManager.StepPlayer(P1, P1.getX(), P1.getY()+1))
                    P1.moveRight();
                else
                    System.out.println("Illegal move!");

                break;
            case KeyEvent.VK_S: // 'S' key for Player 1's down move
                downPressed = true;
                if(gameManager.StepPlayer(P1, P1.getX(), P1.getY()-1))
                    P1.moveDown();
                else
                    System.out.println("Illegal move!");

                break;
            case KeyEvent.VK_W: // 'D' key for Player 1's up move
                upPressed = true;
                if(gameManager.StepPlayer(P1, P1.getX(), P1.getY()+1))
                    P1.moveUp();
                else
                    System.out.println("Illegal move!");

                break;
            case KeyEvent.VK_LEFT: // Left arrow key for Player 2's left move
                if(gameManager.StepPlayer(P2, P2.getX()-1, P2.getY()))
                    P2.moveLeft();
                else
                    System.out.println("Illegal move!");
                break;
            case KeyEvent.VK_RIGHT: // Right arrow key for Player 2's right move
                if(gameManager.StepPlayer(P2, P2.getX()+1, P2.getY()))
                    P2.moveRight();
                else
                    System.out.println("Illegal move!");
                break;
            case KeyEvent.VK_DOWN: // 'A' key for Player 1's left move
                if(gameManager.StepPlayer(P2, P2.getX(), P2.getY()-1))
                    P2.moveDown();
                else
                    System.out.println("Illegal move!");
                break;
            case KeyEvent.VK_UP: // 'D' key for Player 1's right move
                if(gameManager.StepPlayer(P2, P2.getX(), P2.getY()+1))
                    P2.moveUp();
                else
                    System.out.println("Illegal move!");
                break;
        }

        int tempP2x = P2.getX();
        int tempP2y = P2.getY();
        // Player 2 controls
        switch (e.getKeyCode()) {

        }
        gameWindow.repaintBoard(tempP1x, tempP1y, tempP2x, tempP2y);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }else if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
}
