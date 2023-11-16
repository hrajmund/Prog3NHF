import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameManager {
    BoardBlock[][] board;
    int[] playerScore;
    GameManager(BoardBlock[][] board, int[] playerScore){
       this.board = board;
       this.playerScore = playerScore;
    }

    GameManager(File gameFile) {
        playerScore = new int[2];
        board = new BoardBlock[9][9];
        String filePath = "../boards/" + gameFile;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            String[] playerData = line.split(";");
            playerScore[0] = Integer.parseInt(playerData[0]);
            playerScore[1] = Integer.parseInt(playerData[1]);

            int rowCounter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] boardData = line.split(" ");
                for(int i = 0; i < 9; i++){
                    BoardBlock tmp = new BoardBlock(boardData[i]);
                    board[i][rowCounter] = tmp;
                }
                rowCounter++;
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public boolean StepPlayer(Player player, int x, int y){
        if(board[x][y].getState().equals("E")){
            player.setCoordinates(x,y);
            return true;
        }else{
            return false;
        }
    }
}
