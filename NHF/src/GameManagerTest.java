import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    public GameManager gameManager;
    public BoardBlock[][] board = new BoardBlock[9][9];
    int[] playerScore;
    Player P1;
    Player P2;

    public GameManagerTest(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                BoardBlock tmp = new BoardBlock("E");
                board[i][j] = tmp;
            }
        }
        playerScore = new int[] {10, 10};
        P1 = new Player("TestA", 1);
        P2 = new Player("TestB", 2);

        gameManager = new GameManager(board, playerScore, P1, P2);
    }
    @Test
    void stepPlayer() {
        int x = 1;
        int y = 2;

        assertTrue(gameManager.StepPlayer(x, y));
    }

    @Test
    void gameOver() {
        P1.setX(8);
        P2.setX(0);

        assertTrue(gameManager.GameOver(P1));
        assertTrue(gameManager.GameOver(P2));
    }

    @Test
    void findPlayer() {
        P1.setCoordinates(3,3);
        assertEquals("(3,3)", gameManager.findPlayer(P1));
    }
}