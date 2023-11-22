import java.io.*;

public class GameManager {
    BoardBlock[][] board;
    int[] playerScore;
    GameManager(BoardBlock[][] board, int[] playerScore){
       this.board = board;
       this.playerScore = playerScore;
    }

    GameManager(File gameFile) {
        String wd = System.getProperty("user.dir");
        playerScore = new int[2];
        board = new BoardBlock[9][9];
        //String filePath = "../boards/" + gameFile.getName();
        String filePath = wd + "/boards/" + gameFile.getName();
        System.out.println(filePath);

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
                    board[rowCounter][i] = tmp;
                }
                rowCounter++;
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void save(String boardName, BoardBlock[][] saveBoard, int savePlayerScore[]) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(boardName + ".txt"));
            writer.write(savePlayerScore[0] + ";" + savePlayerScore[1]);
            writer.newLine();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    writer.write(saveBoard[i][j].getState());
                    if (j < saveBoard[i].length - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public BoardBlock[][] getBoard(){
        return board;
    }

    public int[] getPlayerScore(){
        return playerScore;
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
